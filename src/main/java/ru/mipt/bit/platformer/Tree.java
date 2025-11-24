package ru.mipt.bit.platformer;
import com.badlogic.gdx.math.GridPoint2;



public class Tree implements GameObject {
    private GridPoint2 treeObstacleCoordinates;
    
    public static Tree makeTreeAt(GridPoint2 tileCoordinates) {
        return new Tree(tileCoordinates);
    }

    public Tree(GridPoint2 tileCoordinates) {
        this.treeObstacleCoordinates = tileCoordinates;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return treeObstacleCoordinates;
    }

    @Override
    public void update(float deltaTime) {
        // no update needed for tree
    }

    public GridPoint2 getDestinationCoordinates() {
        return treeObstacleCoordinates;
    }

}
