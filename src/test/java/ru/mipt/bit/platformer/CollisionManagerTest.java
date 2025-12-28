package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.GridPoint2;

public class CollisionManagerTest {
    private Level level;
    private CollisionManager collisionManager;

    @BeforeEach
    public void setUp() {
        level = new Level();
        collisionManager = level.getCollisionManager();
    }

    @Test
    public void testOutOfBoundsCollision() {
        // Проверяем границы карты (10x8)
        assertTrue(collisionManager.isCollided(new GridPoint2(-1, 0)));
        assertTrue(collisionManager.isCollided(new GridPoint2(0, -1)));
        assertTrue(collisionManager.isCollided(new GridPoint2(10, 0)));
        assertTrue(collisionManager.isCollided(new GridPoint2(0, 8)));
    }

    @Test
    public void testNoCollisionOnEmptyTile() {
        // Проверяем, что на пустой клетке нет коллизии
        assertFalse(collisionManager.isCollided(new GridPoint2(5, 5)));
    }

    @Test
    public void testCollisionWithObject() {
        Tank tank = new Tank(level, new GridPoint2(3, 3));
        level.addObject(tank);
        
        // Должна быть коллизия на позиции танка
        assertTrue(collisionManager.isCollided(new GridPoint2(3, 3)));
    }

    @Test
    public void testBulletCollisionWithTank() {
        Tank tank = new Tank(level, new GridPoint2(4, 4));
        level.addObject(tank);
        
        // Пуля попадает в танк
        assertTrue(collisionManager.checkBulletCollisions(new GridPoint2(4, 4)));
        
        // Пуля не попадает в танк
        assertFalse(collisionManager.checkBulletCollisions(new GridPoint2(5, 5)));
    }
}
