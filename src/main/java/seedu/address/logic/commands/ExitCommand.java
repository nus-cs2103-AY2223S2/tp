package seedu.address.logic.commands;

import java.util.ArrayList;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>();

    private final boolean isModifying = false;
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public boolean checkModifiable() {
        return isModifying;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, null);
    }

}
