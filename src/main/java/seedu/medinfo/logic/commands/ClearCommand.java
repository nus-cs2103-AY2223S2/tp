package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.Model;

/**
 * Clears the medinfo book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "MedInfo has been cleared!";

    /**
     * Executes the {@code ClearCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        MedInfo newMedInfo = new MedInfo();
        newMedInfo.newMedInfo();
        model.setMedInfo(newMedInfo);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
