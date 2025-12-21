package ru.mipt.bit.platformer;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.Observers.*;


public class Tank implements GameObject {
    private GridPoint2 Coordinates;
    private GridPoint2 DestinationCoordinates;
    private float TankSpeed = 0.4f;
    private float TankRotation;
    private float TankMovementProggress = 1f;
    private Level level;
    private int HitPoints = 100;
    

    public static Tank makeTankAtTile(Level level, GridPoint2 coordinates) {
        return new Tank(level, coordinates);
    }

    public Tank(Level level, GridPoint2 coordinates) {
        this.level = level;
        DestinationCoordinates = coordinates;
        Coordinates = new GridPoint2(DestinationCoordinates);
        TankRotation = 0f;
    }

    public static Tank makeEnemyTank(Level level, GridPoint2 coordinates) {
        Tank tank = new Tank(level, coordinates);
        return tank;
    }

    public boolean isMoving() {
        return !isEqual(TankMovementProggress, 1f);
    }

    public Bullet shoot() {
        Bullet bullet = new Bullet(getCoordinates(), getTankRotation(), level);
        level.addObject(bullet);
        return bullet;
    }
    public void takeDamage() {
        HitPoints -= 25;
        if (HitPoints <= 0) {
            HitPoints = 0;
            for (Observer<?> observer : level.getObserversFor(Tank.class)) {
                TankGraphicsObserver obs = (TankGraphicsObserver) observer;
                obs.onRemove(this);
            }
            level.removeObject(this);
        }
    }

    public void tryMove(Directions direction) {
        if (isMoving() || direction == null) {
            return;
        }

        GridPoint2 destinationCoordinates = new GridPoint2(
                Coordinates.x + direction.dx,
                Coordinates.y + direction.dy);

        if (level.getCollisionManager().isCollided(destinationCoordinates)) {
            return;
        }

        DestinationCoordinates.set(destinationCoordinates);
        setRotation(direction.rotation);
        setMovementProggress(0f);
    }

    @Override
    public boolean isAlive() {
        if (HitPoints > 0) {
            return true;
        }
        return false;
    }

    public GridPoint2 getCoordinates() {
        return Coordinates;
    }
    
    
    public float getTankRotation() {
        return TankRotation;
    }

    public float getMovementProggress() {
        return TankMovementProggress;
    }


    public GridPoint2 getDestinationCoordinates() {
        return DestinationCoordinates;
    }

    public int getHitPoints() {
        return HitPoints;
    }

    @Override
    public void update(float deltaTime) {
        // обновляем прогресс движения от 0 до 1
        TankMovementProggress = continueProgress(TankMovementProggress, deltaTime, TankSpeed);
        // если достигли цели, фиксируем координаты
        if (!isMoving()) {
            Coordinates.set(DestinationCoordinates);
        }
    }

    public void setRandomPosition() {
        // randomly place the tank on the map 10x8
        Coordinates = new GridPoint2((int)(Math.random() * 10), (int)(Math.random() * 8));
        DestinationCoordinates = new GridPoint2(Coordinates);
        TankRotation = 0f;
    }

    public void setMovementProggress(float x) {
        TankMovementProggress = x;
    }

    public void setRotation(float angle) {
        this.TankRotation = angle;
    }
    
    public void setCoordinates(GridPoint2 coordinates) {
        Coordinates = coordinates;
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        DestinationCoordinates = destinationCoordinates;
    }

    public void RandomMove() {
        Directions[] directions = Directions.values();
        Directions randomDirection = directions[(int) (Math.random() * directions.length)];
        tryMove(randomDirection);
    }
}

