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
        // Логика отрисовки полоски здоровья поверх танка
        if (UIState.getInstance().isShowHealthBars()) {
            batch.end(); // Завершаем batch для ShapeRenderer
            drawHealthBar();
            batch.begin(); // Возобновляем batch
        }
    }
    
    private void drawHealthBar() {
        float barWidth = tankGraphics.getTankRectangle().width;
        float barHeight = 5;
        float barX = tankGraphics.getTankRectangle().x;
        float barY = tankGraphics.getTankRectangle().y + tankGraphics.getTankRectangle().height + 5;
        
        // Предполагаем, что у танка 100 HP (можно добавить поле health в Tank)
        float healthPercent = tankGraphics.getTank().getHitPoints() / 100.0f; // 100% здоровья
        
        tankGraphics.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        
        // Фон полоски (красный)
        tankGraphics.getShapeRenderer().setColor(Color.RED);
        tankGraphics.getShapeRenderer().rect(barX, barY, barWidth, barHeight);
        
        // Текущее здоровье (зеленый)
        tankGraphics.getShapeRenderer().setColor(Color.GREEN);
        tankGraphics.getShapeRenderer().rect(barX, barY, barWidth * healthPercent, barHeight);
        
        tankGraphics.getShapeRenderer().end();
    }

    public void dispose() {
        tankGraphics.dispose();
    }


}
