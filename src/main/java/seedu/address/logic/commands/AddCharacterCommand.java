package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.entity.Character;
import seedu.address.model.Model;

public class AddCharacterCommand extends Command {
    public static final String COMMAND_WORD = "char";

    private final Character newCharacter;

    public AddCharacterCommand(Character ch) {
        this.newCharacter = ch;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("");
    }
}
