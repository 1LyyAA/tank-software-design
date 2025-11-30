package ru.mipt.bit.platformer.Graphics;

public class UIState{
    private static UIState instance;
    private boolean showHealthBars = false;
    
    private UIState() {
        // Приватный конструктор для Singleton
    }
    
    public static UIState getInstance() {
        if (instance == null) {
            instance = new UIState();
        }
        return instance;
    }
    
    public boolean isShowHealthBars() {
        return showHealthBars;
    }
    
    public void setShowHealthBars(boolean show) {
        this.showHealthBars = show;
    }
    
    public void toggleHealthBars() {
        this.showHealthBars = !this.showHealthBars;
    }

}