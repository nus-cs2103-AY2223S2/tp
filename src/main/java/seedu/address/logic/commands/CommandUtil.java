package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Contains utility methods used for commands.
 */
public class CommandUtil {

    /**
     * Checks if the student name exists in the address book.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if the student name does not exist in the address book.
     */
    public static void handleNonExistName(Model model, List<String> names) throws CommandException {
        StringBuilder nonExistNames = new StringBuilder();
        for (String name : names) {
            if (model.noSuchStudent(name)) {
                nonExistNames.append(name).append(", ");
            }
        }
        if (nonExistNames.length() != 0) {
            nonExistNames = new StringBuilder(nonExistNames.substring(0, nonExistNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_NO_SUCH_STUDENT, nonExistNames));
        }
    }

    /**
     * Checks if the student name exists in the address book.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if the student name does not exist in the address book.
     */
    public static void handleDuplicateName(Model model, List<String> names) throws CommandException {
        StringBuilder dupNames = new StringBuilder();
        for (String name : names) {
            if (model.hasDuplicateName(name)) {
                dupNames.append(name).append(", ");
            }
        }
        if (dupNames.length() != 0) {
            dupNames = new StringBuilder(dupNames.substring(0, dupNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_HAS_DUPLICATE_NAMES, dupNames));
        }
    }
}
