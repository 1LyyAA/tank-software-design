package ru.mipt.bit.platformer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.Controllers.AIController;
import ru.mipt.bit.platformer.Controllers.PlayerController;
import ru.mipt.bit.platformer.Graphics.LevelGraphics;
import ru.mipt.bit.platformer.LevelLoaders.FileLevelGenerator;
import ru.mipt.bit.platformer.LevelLoaders.LevelData;
import ru.mipt.bit.platformer.LevelLoaders.RandomLevelGenerator;
import ru.mipt.bit.platformer.Observers.BulletGraphicsObserver;
import ru.mipt.bit.platformer.Observers.TankGraphicsObserver;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

@Configuration
public class SpringConfig {
    @Bean
    public Batch batch() {
        return new SpriteBatch();
    }

    @Bean
    public FileLevelGenerator fileLevelGenerator() {
        return new FileLevelGenerator("level.tmx", "src/main/resources/images/Level.txt");
    }

    @Bean
    public LevelData levelData(FileLevelGenerator fileLevelGenerator) {
        return fileLevelGenerator.generate();
    }

    @Bean
    public Level level(LevelData levelData) {
        return levelData.getLevel();
    }

    @Bean
    public LevelGraphics levelGraphics(Level level, LevelData levelData, Batch batch) {
        return new LevelGraphics(level, levelData.getTmxFilePath(), batch);
    }

    @Bean
    public BulletGraphicsObserver bulletGraphicsObserver(LevelGraphics levelGraphics) {
        return new BulletGraphicsObserver(levelGraphics);
    }

    @Bean
    public TankGraphicsObserver tankGraphicsObserver(LevelGraphics levelGraphics) {
        return new TankGraphicsObserver(levelGraphics);
    }

    @Bean
    public PlayerController playerController(LevelData levelData) {
        return new PlayerController(levelData.getPlayerTank());
    }

    @Bean
    public AIController aiController(LevelData levelData) {
        return new AIController(levelData.getEnemyTanks());
    }

    @Bean
    public List<Controller> controllers(PlayerController playerController, AIController aiController) {
        return List.of(playerController, aiController);
    }

    @Bean
    public List<Observer> observers(BulletGraphicsObserver bulletGraphicsObserver, TankGraphicsObserver tankGraphicsObserver) {
        return List.of(bulletGraphicsObserver, tankGraphicsObserver);
    }

    @Bean
    public GameDesktopLauncher gameDesktopLauncher(Batch batch,
                                                   Level level,
                                                   LevelGraphics levelGraphics,
                                                   List<Controller> controllers,
                                                   List<Observer> observers) {
        return new GameDesktopLauncher(batch, level, levelGraphics, controllers, observers);
    }
}
