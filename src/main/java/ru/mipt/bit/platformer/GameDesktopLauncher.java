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
    private List<Controller> controllerList;
    private List<Observer> observerList;

    public GameDesktopLauncher(Batch batch,
                               Level level,
                               LevelGraphics levelGraphics,
                               List<Controller> controllerList,
                               List<Observer> observerList) {
        this.batch = batch;
        this.level = level;
        this.levelGraphics = levelGraphics;
        this.controllerList = controllerList;
        this.observerList = observerList;
    }

    @Override
    public void create() {
        addObserversToLevel();
    }



    private void addObserversToLevel() {
        for (Observer observer : observerList) {
            for (Class<?> observedClass : observer.getObservedClasses()) {
                level.addListener(observedClass, observer);
            }
        }
    }

    @Override
    public void render() {
        clearScreen();
        float deltaTime = Gdx.graphics.getDeltaTime();
        processCommands();
        updateWorld(deltaTime);
        renderWorld();
    }

    private void renderWorld() {
        levelGraphics.renderMap();
        batch.begin();
        levelGraphics.renderObjects(batch);
        batch.end();
    }

    private void processCommands() {
        for (Command command : collectCommands()) {
            command.execute();
        }
    }

    private List<Command> collectCommands() {
        List<Command> commands = new ArrayList<>();
        for (Controller controller : controllerList) {
            commands.addAll(controller.pollCommands());
        }

        return commands;
    }

    private void updateWorld(float deltaTime) {
        for (GameObject object : level.getObjects()) {
            object.update(deltaTime);
        }
        level.removeObjectsMarkedForRemoval();
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
        ApplicationContext context = new AnnotationConfigApplicationContext(ru.mipt.bit.platformer.config.SpringConfig.class);
        GameDesktopLauncher launcher = context.getBean(GameDesktopLauncher.class);

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(launcher, config);
    }
}
