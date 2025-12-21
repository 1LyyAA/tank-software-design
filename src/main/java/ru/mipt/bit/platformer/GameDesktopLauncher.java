package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mipt.bit.platformer.Commands.Command;
import ru.mipt.bit.platformer.Controllers.*;
import ru.mipt.bit.platformer.Graphics.LevelGraphics;
import ru.mipt.bit.platformer.LevelLoaders.FileLevelGenerator;
import ru.mipt.bit.platformer.LevelLoaders.LevelData;
import ru.mipt.bit.platformer.LevelLoaders.RandomLevelGenerator;
import ru.mipt.bit.platformer.Observers.*;
import ru.mipt.bit.platformer.config.SpringConfig;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import java.util.ArrayList;
import java.util.List;


public class GameDesktopLauncher implements ApplicationListener {
    private Batch batch;
    private Level level;
    private LevelGraphics levelGraphics;

    private PlayerController playerController;
    private AIController aiController;



    @Override
    public void create() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        batch = context.getBean(Batch.class);
        level = context.getBean(Level.class);
        levelGraphics = context.getBean(LevelGraphics.class);
        BulletGraphicsObserver bulletGraphicsObserver = context.getBean(BulletGraphicsObserver.class);
        TankGraphicsObserver tankGraphicsObserver = context.getBean(TankGraphicsObserver.class);
        playerController = context.getBean(PlayerController.class);
        aiController = context.getBean(AIController.class);

        level.addListener(Bullet.class, bulletGraphicsObserver);
        level.addListener(Tank.class, tankGraphicsObserver);
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
        level.removeObjectsMarkedForRemoval();
        
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
