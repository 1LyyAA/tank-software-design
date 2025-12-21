package ru.mipt.bit.platformer;
import com.badlogic.gdx.math.GridPoint2;

public class CollisionManager {
    Level level;
  
    public CollisionManager(Level level) {
        this.level = level;
    }

    public boolean isCollided(GridPoint2 CellCoordinates) {
        for (GameObject object : level.getObjects()) {
            if (object.getCoordinates().equals(CellCoordinates) ||
                object.getDestinationCoordinates().equals(CellCoordinates)) {
                return true;
            }
        }        
        // check if in map bounds 10 by 8
        if (CellCoordinates.x < 0 || CellCoordinates.x >= 10 ||
            CellCoordinates.y < 0 || CellCoordinates.y >= 8) {
            return true;
        }
        return false;
    }

    public boolean checkBulletCollisions(GridPoint2 position) {
        for (GameObject obj : level.getObjects()) {
            if (obj != this && obj.isAlive()) {
                if (obj instanceof Tank) {
                    Tank tank = (Tank) obj;
                    if (tank.getCoordinates().equals(position)) {
                        tank.takeDamage();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}



