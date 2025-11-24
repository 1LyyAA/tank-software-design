package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;

import ru.mipt.bit.platformer.Graphics.LevelGraphics;
import ru.mipt.bit.platformer.util.KeyboardListener;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

import java.util.List;

import static ru.mipt.bit.platformer.LevelBuilder.createLevel;
import static ru.mipt.bit.platformer.LevelBuilder.createRandomLevel;;

public class GameDesktopLauncher implements ApplicationListener {

    private Batch batch;
    // private MapRenderer levelRenderer;
    private Level level;
    private LevelGraphics levelGraphics;
    private KeyboardListener keyboardListener;
    

    // public Level(String path, List<Tree> trees, Tank tank, CollisionManager collisionManager) {
    //     map = new TmxMapLoader().load(path);
    //     groundLayer = (TiledMapTileLayer) map.getLayers().get(0);
    //     this.trees = trees;
    //     this.tank = tank;
    //     this.collisionManager = collisionManager;
    //     this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    // }





    @Override
    public void create() {
        
        //level = createRandomLevel("level.tmx");

        batch = new SpriteBatch();
        LevelData levelData = createLevel("level.tmx", "src/main/resources/images/level.txt");
        level = levelData.getLevel();
        Tank playerTank = levelData.getPlayerTank();
        List<Tank> enemyTanks = levelData.getEnemyTanks();

        levelGraphics = new LevelGraphics(level, batch);
        keyboardListener = new KeyboardListener(playerTank);
        Gdx.input.setInputProcessor(keyboardListener);

    }

    @Override
    public void render() {
        clearScreen();
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        // update game state
        for (GameObject object : level.getObjects()) {
            object.update(deltaTime);
        }

        levelGraphics.renderMap();
        // start recording all drawing commands
        batch.begin();

        levelGraphics.renderObjects(batch);

        // submit all drawing requests
        batch.end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        levelGraphics.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
