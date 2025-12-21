package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HealthBarDecorator implements Graphics {
    TankGraphics tankGraphics;

    public HealthBarDecorator(TankGraphics tankGraphics) {
        this.tankGraphics = tankGraphics;
    }

    public void render(Batch batch) {
        tankGraphics.render(batch);
    
        if (UIState.getInstance().isShowHealthBars()) {
            batch.end();
            drawHealthBar();
            batch.begin();
        }
    }
    
    private void drawHealthBar() {
        float barWidth = tankGraphics.getTankRectangle().width;
        float barHeight = 5;
        float barX = tankGraphics.getTankRectangle().x;
        float barY = tankGraphics.getTankRectangle().y + tankGraphics.getTankRectangle().height + 5;
        
        float healthPercent = tankGraphics.getTank().getHitPoints() / 100.0f;
        
        tankGraphics.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        
        tankGraphics.getShapeRenderer().setColor(Color.RED);
        tankGraphics.getShapeRenderer().rect(barX, barY, barWidth, barHeight);
        
        tankGraphics.getShapeRenderer().setColor(Color.GREEN);
        tankGraphics.getShapeRenderer().rect(barX, barY, barWidth * healthPercent, barHeight);
        
        tankGraphics.getShapeRenderer().end();
    }

    public void dispose() {
        tankGraphics.dispose();
    }


}
