package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.Model;
import seedu.sprint.model.application.Application;

/**
 * Adds an application to the internship book.
 */
public class AddApplicationCommand extends Command {

    public static final String COMMAND_WORD = "add-app";

    public static final String MESSAGE_USAGE = "Format: "
            + COMMAND_WORD + " "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_COMPANY_EMAIL + "COMPANY_EMAIL "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_TAG + "[TAG(s)] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROLE + "SWE Intern "
            + PREFIX_COMPANY_NAME + "Goggle "
            + PREFIX_COMPANY_EMAIL + "gogglehiring@goggletalents.com "
            + PREFIX_STATUS + "interested "
            + PREFIX_TAG + "highSalary "
            + PREFIX_TAG + "bestWelfare";

    public static final String MESSAGE_SUCCESS = "New application added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application already exists in the internship book";

    private final Application toAdd;

    /**
     * Creates an AddApplicationCommand to add the specified {@code Application}.
     */
    public AddApplicationCommand(Application application) {
        requireNonNull(application);
        toAdd = application;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        if (model.hasApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.addApplication(toAdd);
        model.commitInternshipBookChange();
        commandHistory.setLastCommandAsModify();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddApplicationCommand // instanceof handles nulls
                && toAdd.equals(((AddApplicationCommand) other).toAdd));
    }
}
