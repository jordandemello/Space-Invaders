package GameUnits;

import GameInterface.App.SpaceShooter;
import GameInterface.Dialogs.EndGameDialog;
import GameInterface.Dialogs.NextLevelDialog;
import GameInterface.Panels.GameArea;
import GameInterface.Panels.GameUnitArea;
import GameInterface.Panels.ScoreArea;
import GameInterface.Popups.GamePausedPopup;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Jordan on 2017-07-17.
 */
public class SpaceGame extends Observable{

    private PlayerUnit player;

    private int gameLevel;
    private boolean levelIsOver;

    private int enemyLevel;
    private int projectileLevel;
    public static final int MAX_PLAYER_UNIT_LEVEL = 5;
    public static final int MAX_PROJECTILE_LEVEL = 5;

    private int invadersSpawnedThisRound;
    private int killCount;
    private int invadersOnScreen;
    private int invadersLeftInRound;
    private boolean bossSpawned;
    private final int[] invadersByRound =           {10, 15, 30, 40, 1, 50, 15, 50, 70, 10, 200,  1};
    private final int[] enemyProbabilityByRound =   {50, 50, 40, 40, 1, 30,  1, 30, 30, 50,  40, 10};

    private int currency;

    private int numberOfMissiles;
    public static final int MAX_MISSILES_MULTIPLIER = 4;                // max number of missiles is level * this number

    private static final int PROJECTILE_COST_MULTIPLIER = 5;           // cost of projectile is level * this number
    private static final int PLAYER_UNIT_COST_MULTIPLIER = 5;          // cost of player unit upgrade is level ^

    private boolean gameIsOver;
    private Random rand;
    private List<GameItem> gameItems;
    private SpaceShooter app;

    public SpaceGame(SpaceShooter app) {
        this.app = app;
        gameItems = new ArrayList<>();
        player = new PlayerUnit(SpaceShooter.SCREEN_WIDTH / 2, app);
        gameItems.add(player);
        gameLevel = 1;
        projectileLevel = 1;
        numberOfMissiles = 0;
        enemyLevel = 1;
        rand = new Random();
        gameIsOver = false;
        killCount = 0;
        invadersLeftInRound = invadersByRound[gameLevel - 1];
        levelIsOver = false;
    }

    public void setPlayerColour(String colour){
        player.setColour(colour);
    }

    public void updateGame() {
        generateEnemy();
        moveGameItems();
        checkMissileOutOfBounds();
        checkMissileCollision();
        checkEnemyVictory();
        checkNoInvadersLeft();
        setChanged();
        notifyObservers(GameArea.UPDATE_GAME_AREA);
    }

    public void loadObservers(Observer[] observers){
        for(Observer o : observers){
            addObserver(o);
        }
    }

    public void keyPressed(KeyEvent event) {
        int i = event.getKeyCode();
        if (i == KeyEvent.VK_LEFT)
            player.moveLeft();
        else if (i == KeyEvent.VK_RIGHT)
            player.moveRight();
        else if (i == KeyEvent.VK_SPACE)
            shootProjectile();
        else if (i == KeyEvent.VK_ESCAPE) {
            app.stopTimer();
            new GamePausedPopup(app);
        }
    }

    public void keyReleased(KeyEvent event) {
        int i = event.getKeyCode();
        if (i == KeyEvent.VK_LEFT)
            player.stopMovement();
        else if (i == KeyEvent.VK_RIGHT)
            player.stopMovement();
    }

    public void shootProjectile() {
        if (numberOfMissiles < (player.getLevel() * MAX_MISSILES_MULTIPLIER) && (invadersLeftInRound != 0 || invadersOnScreen != 0)) {
            Projectile proj = new Projectile(player.xpos, player.ypos, projectileLevel);
            gameItems.add(proj);
            numberOfMissiles++;
            setChanged();
            notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
        }
    }

    public void moveGameItems() {
        for (GameItem item : gameItems) {
            item.move();
        }
    }

    public void generateEnemy() {
        if(gameLevel % 5 == 0 && !bossSpawned && !gameIsOver && invadersSpawnedThisRound < invadersByRound[gameLevel - 1]) {
            EnemyBoss enemyBoss = new EnemyBoss(SpaceShooter.SCREEN_WIDTH / 2, 0, (gameLevel / 5) - 1, app);
            gameItems.add(enemyBoss);
            bossSpawned = true;
            invadersOnScreen++;
            invadersSpawnedThisRound++;
            setChanged();
            notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
        }
        else {
            int r = rand.nextInt(enemyProbabilityByRound[gameLevel - 1]);
            if (r == 0 && !gameIsOver && invadersSpawnedThisRound < invadersByRound[gameLevel - 1]) {
                int r1 = rand.nextInt(SpaceShooter.SCREEN_WIDTH - 20);
                EnemyBasic enemyBasic = new EnemyBasic(r1 + 10, 0, enemyLevel, app);
                gameItems.add(enemyBasic);
                invadersOnScreen++;
                invadersSpawnedThisRound++;
                setChanged();
                notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
            }
        }
    }

    public void checkMissileOutOfBounds() {
        List<Projectile> projs = new ArrayList<>();
        for (GameItem item : gameItems) {
            if (item instanceof Projectile) {
                if (((Projectile) item).isOutOfBounds()) {
                    projs.add((Projectile) item);
                    numberOfMissiles--;
                    setChanged();
                    notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
                }
            }
        }
        gameItems.removeAll(projs);
    }

    public void checkMissileCollision() {
        Set<Enemy> enemies = new HashSet<>();
        for (GameItem item1 : gameItems) {
            if (item1 instanceof Enemy)
                enemies.add((Enemy) item1);
        }
        Set<GameItem> toRemove = new HashSet<>();
        for (GameItem item2 : gameItems) {
            if (item2 instanceof Projectile) {
                Projectile proj = (Projectile) item2;
                handleCollisions(proj, enemies, toRemove);
            }
        }
        gameItems.removeAll(toRemove);
    }

    public void handleCollisions(Projectile proj, Set<Enemy> enemies, Set<GameItem> toRemove){
        for(Enemy enemy : enemies){
            if(proj.isColliding(enemy)){
                if(!toRemove.contains(enemy) && !toRemove.contains(proj)){
                    int projDamage = proj.getDamage();
                    if(enemy.getHealth() <= projDamage) {
                        toRemove.add(enemy);
                        killCount++;
                        currency++;
                        invadersOnScreen--;
                        invadersLeftInRound--;
                    }
                    else
                        enemy.damage(projDamage);
                    toRemove.add(proj);
                    numberOfMissiles--;
                    setChanged();
                    notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
                }
            }
        }
    }


    public void checkEnemyVictory(){
        for (GameItem item : gameItems){
            if(item instanceof Enemy){
                if (item.ypos > SpaceShooter.GAME_AREA_HEIGHT) {
                    gameIsOver = true;
                }
            }
        }
        if(gameIsOver) {
            endGame();
        }
    }

    public void endGame(){
        gameItems.clear();
        player = new PlayerUnit(SpaceShooter.SCREEN_WIDTH / 2, app);
        gameItems.add(player);
        app.getGameArea().repaint();
        new EndGameDialog(app);
    }

    public void restartGame(){
        resetStats();
        gameIsOver = false;
    }

    public void resetStats(){
        projectileLevel = 1;
        enemyLevel = 1;
        gameLevel = 1;
        killCount = 0;
        currency = 0;
        invadersOnScreen = 0;
        numberOfMissiles = 0;
        invadersSpawnedThisRound = 0;
        invadersLeftInRound = invadersByRound[gameLevel - 1];
        setChanged();
        notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
        setChanged();
        notifyObservers(GameUnitArea.UPDATE_GAME_UNIT_AREA);
    }

    public void checkNoInvadersLeft(){
        if(invadersLeftInRound == 0 && invadersOnScreen == 0 && numberOfMissiles == 0)
            levelIsOver = true;
    }

    public void checkLevelOver(){
        if(levelIsOver) {
            new NextLevelDialog(app);
        }
    }

    public void nextLevel(){
        gameLevel++;
        invadersSpawnedThisRound = 0;
        invadersLeftInRound = invadersByRound[gameLevel - 1];
        numberOfMissiles = 0;
        levelIsOver = false;
        if(gameLevel % 2 == 1)
            enemyLevel++;
        setChanged();
        notifyObservers(GameUnitArea.UPDATE_GAME_UNIT_AREA);
        setChanged();
        notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
    }

    public void draw(Graphics graphics){
        for(GameItem item : gameItems){
            item.draw(graphics);
        }
    }

    public boolean tryLevelUpProjectile(){
        int cost = projectileLevel * PROJECTILE_COST_MULTIPLIER;
        if(currency >= cost && projectileLevel < MAX_PROJECTILE_LEVEL){
            projectileLevel++;
            currency -= cost;
            setChanged();
            notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
            setChanged();
            notifyObservers(GameUnitArea.UPDATE_GAME_UNIT_AREA);
            return true;
        }
        else
            return false;
    }

    public boolean tryLevelUpPlayerUnit(){
        int playerUnitLevel = player.getLevel();
        int cost = playerUnitLevel * PLAYER_UNIT_COST_MULTIPLIER;
        if(currency >= cost && playerUnitLevel < MAX_PLAYER_UNIT_LEVEL){
            player.levelUp();
            player.updateStats();
            currency -= cost;
            setChanged();
            notifyObservers(ScoreArea.UPDATE_SCORE_AREA);
            setChanged();
            notifyObservers(GameUnitArea.UPDATE_GAME_UNIT_AREA);
            return true;
        }
        else
            return false;
    }

    public PlayerUnit getPlayer(){
        return player;
    }

    public int getGameLevel(){
        return gameLevel;
    }

    public int getPlayerUnitLevel(){
        return player.getLevel();
    }

    public int getEnemyLevel(){
        return enemyLevel;
    }

    public int getProjectileLevel(){
        return projectileLevel;
    }

    public int getKillCount(){
        return killCount;
    }

    public int getInvadersLeftInRound(){
        return invadersLeftInRound;
    }

    public int getInvadersOnScreen(){
        return invadersOnScreen;
    }

    public int getNumberOfMissiles(){
        return numberOfMissiles;
    }

    public int getCurrency(){
        return currency;
    }

    public int getProjectileCost(){
        return projectileLevel * PROJECTILE_COST_MULTIPLIER;
    }

    public int getPlayerUnitCost(){
        return player.getLevel()  * PLAYER_UNIT_COST_MULTIPLIER;
    }
}