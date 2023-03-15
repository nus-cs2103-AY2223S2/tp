package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.Application;

/**
 * Adds an application to the internship book.
 */
public class AddApplicationCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an application to the internship book. "
            + "Parameters: "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_COMPANY_NAME + "COMPANY NAME "
            + PREFIX_COMPANY_EMAIL + "COMPANY EMAIL "
            + PREFIX_STATUS + "STATUS \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROLE + "SWE Intern "
            + PREFIX_COMPANY_NAME + "Goggle "
            + PREFIX_COMPANY_EMAIL + "gogglehiring@goggletalents.com "
            + PREFIX_STATUS + "interested";


    public static final String MESSAGE_SUCCESS = "New application added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application already exists in the internship book";

    private final Application toAdd;

    /**
     * Creates an AddApplicationCommand to add the specified {@code Application}
     */
    public AddApplicationCommand(Application application) {
        requireNonNull(application);
        toAdd = application;
    }

    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.addApplication(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddApplicationCommand // instanceof handles nulls
                && toAdd.equals(((AddApplicationCommand) other).toAdd));
    }
}
