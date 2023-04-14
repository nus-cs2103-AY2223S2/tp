package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to the module tracker.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to the module tracker. \n"
            + "Parameters: "
            + PREFIX_NAME + "MODULE_NAME "
            + PREFIX_TAG + "DESCRIPTION (A description of your choice. Eg. Lecture/Tutorial/Lab) "
            + "[" + PREFIX_TIMESLOT + "TIMESLOT] "
            + "[" + PREFIX_ADDRESS + "VENUE] "
            + "[" + PREFIX_RESOURCE + "RESOURCE] "
            + "[" + PREFIX_TEACHER + "TEACHER] "
            + "[" + PREFIX_DEADLINE + "DEADLINES] "
            + "[" + PREFIX_REMARK + "REMARKS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS1101S "
            + PREFIX_TAG + "Lecture "
            + PREFIX_TIMESLOT + "Monday 12:00 14:00 "
            + PREFIX_ADDRESS + "COM1 ";


    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module tracker";

    private final Module toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
