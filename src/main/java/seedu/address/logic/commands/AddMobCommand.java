package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Mob;

public class AddMobCommand extends Command {
    public static final String COMMAND_WORD = "mob";

    private final Mob newMob;

    public AddMobCommand(Mob m) {
        this.newMob = m;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("");
    }
}
