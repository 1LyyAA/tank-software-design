package ru.mipt.bit.platformer.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.Directions;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Commands.Command;
import ru.mipt.bit.platformer.Commands.MoveCommand;
import ru.mipt.bit.platformer.Commands.ShootCommand;
import ru.mipt.bit.platformer.Graphics.ToggleHealthCommand;

public class PlayerController {
    private Tank tank;

    public PlayerController(Tank tank) {
        this.tank = tank;
    }
    
    /**
     * Проверяет нажатые клавиши и двигает танк.
     * Вызывается каждый кадр для поддержки удержания клавиш.
     */
    public List<Command> pollCommands() {
        List<Command> commands = new ArrayList<>();

        // движение
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            commands.add(new MoveCommand(tank, Directions.UP));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            commands.add(new MoveCommand(tank, Directions.DOWN));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            commands.add(new MoveCommand(tank, Directions.LEFT));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            commands.add(new MoveCommand(tank, Directions.RIGHT));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            commands.add(new ShootCommand(tank));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            commands.add(new ToggleHealthCommand());
        }

        return commands;
    }
}
