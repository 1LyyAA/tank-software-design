package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.mipt.bit.platformer.Commands.Command;
import ru.mipt.bit.platformer.Controllers.*;
import ru.mipt.bit.platformer.Graphics.LevelGraphics;
import ru.mipt.bit.platformer.LevelLoaders.LevelData;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.LevelLoaders.LevelBuilder.createLevel;
import static ru.mipt.bit.platformer.LevelLoaders.LevelBuilder.createRandomLevel;

import java.util.ArrayList;
import java.util.List;;

public class GameDesktopLauncher implements ApplicationListener {
    private Batch batch;
    private Level level;
    private LevelGraphics levelGraphics;

    private PlayerController playerController;
    private AIController aiController;



    @Override
    public void create() {
        
        //level = createRandomLevel("level.tmx");

        batch = new SpriteBatch();
        LevelData levelData = createLevel("level.tmx", "src/main/resources/images/level.txt");
        level = levelData.getLevel();
        Tank playerTank = levelData.getPlayerTank();
        List<Tank> enemyTanks = levelData.getEnemyTanks();

        levelGraphics = new LevelGraphics(level, batch);
        playerController = new PlayerController(playerTank);
        aiController = new AIController(enemyTanks);
    }

    @Override
    public void render() {
        clearScreen();

        float deltaTime = Gdx.graphics.getDeltaTime();

        ArrayList<Command> commands = new ArrayList<>();
        commands.addAll(playerController.pollCommands());
        commands.addAll(aiController.pollCommands());
        for (Command command : commands) {
            command.execute();
        }

        for (GameObject object : level.getObjects()) {
            object.update(deltaTime);
        }
        
        levelGraphics.renderMap();
        batch.begin();
        levelGraphics.renderObjects(batch);
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
