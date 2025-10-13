package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
//import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.objects.GameObject;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.InputHandler;

//import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
//import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

import java.util.List;

public class GameDesktopLauncher implements ApplicationListener {


    private Batch batch;

    //private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;

    private Level level;

    Tree tree;
    Tank tank;
    CollisionManager collisionManager;



    @Override
    public void create() {
        
        batch = new SpriteBatch();

        // load level tiles
        level = new Level("level.tmx");

        levelRenderer = createSingleLayerMapRenderer(level.getMap(), batch);

        tileMovement = new TileMovement(level.getGroundLayer(), Interpolation.smooth);

        tree = new Tree("images/greenTree.png", level.getGroundLayer(), new GridPoint2(1, 3));

        collisionManager = new CollisionManager(List.of(tree));
        tank = new Tank(collisionManager, tileMovement);
    }

    @Override
    public void render() {
        clearScreen();
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        tank.tryMove(InputHandler.getInput());
        tank.update(deltaTime);

        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render player
        tank.render(batch);

        // render tree obstacle
        tree.render(batch);

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
        for (GameObject object : List.of(tank, tree)) {
            object.dispose();
        }
        
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
