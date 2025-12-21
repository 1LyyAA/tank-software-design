package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.Observers.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level implements Observable {
    private final Map<Class<?>, List<Observer<?>>> observers = new HashMap<>();

    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> objectsToRemove = new ArrayList<>();

    private CollisionManager collisionManager;
    
    public Level(GameObject... initialObjects) {
        this.objects = new ArrayList<>();
        this.objects.addAll(Arrays.asList(initialObjects));
        collisionManager = new CollisionManager(this);
    }

    public void removeObject(GameObject object) {
        objectsToRemove.add(object);
    }

    public void removeObjectsMarkedForRemoval() {
        objects.removeAll(objectsToRemove);
        objectsToRemove.clear();
    }

    public <T extends GameObject> void addListener(Class<T> type, Observer<? super T> observer) {
        observers.computeIfAbsent(type, k -> new ArrayList<>()).add(observer);
    }

   public void addObject(GameObject object) {
        objects.add(object);
    }

    public void addObjects(List<GameObject> objs) {
        this.objects.addAll(objs);
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }

    public List<Observer<?>> getObserversFor(Class<?> type) {
        return observers.getOrDefault(type, new ArrayList<>());
    }

    
}