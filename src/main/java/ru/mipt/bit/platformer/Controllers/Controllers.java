package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Commands.Command;
import java.util.List;

public interface Controller {
    List<Command> pollCommands();
}