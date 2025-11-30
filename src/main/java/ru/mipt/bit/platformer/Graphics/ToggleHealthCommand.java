package ru.mipt.bit.platformer.Graphics;

import ru.mipt.bit.platformer.Commands.Command;

/**
 * Команда для переключения отображения полосок здоровья танков.
 * Следует паттерну Command.
 */
public class ToggleHealthCommand implements Command {
    
    @Override
    public void execute() {
        UIState.getInstance().toggleHealthBars();
        System.out.println("Health bars " + (UIState.getInstance().isShowHealthBars() ? "enabled" : "disabled"));
    }
}
