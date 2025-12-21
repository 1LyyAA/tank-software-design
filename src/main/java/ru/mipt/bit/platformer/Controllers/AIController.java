package ru.mipt.bit.platformer.Controllers;

import java.util.ArrayList;
import java.util.List;

import ru.mipt.bit.platformer.Directions;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Commands.Command;
import ru.mipt.bit.platformer.Commands.MoveCommand;
import ru.mipt.bit.platformer.Commands.ShootCommand;

public class AIController {
    private final List<Tank> npcTanks;

    public AIController(List<Tank> tanks) {
        this.npcTanks = tanks;
    }

    // public Command updateAI() {
    //     for (Tank npcTank : npcTanks) {
    //         if (!npcTank.isMoving()) {

    //             return new MoveCommand(npcTank, Directions.random());
    //         }
    //     }

    //     return null;
    // }

    public ArrayList<Command> pollCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        for (Tank npc : npcTanks) {
            if (!npc.isMoving()) {
                commands.add(new MoveCommand(npc,  Directions.random()));
            }
        }

        for (Tank npc : npcTanks) {
            if (npc.isAlive()) {
                if (Math.random() < 0.01) {
                commands.add(new ShootCommand(npc));
                }
            }
        }
        return commands;
    }
}
