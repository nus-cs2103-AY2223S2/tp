package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.Module;

/**
 * Adds a module to ModTrek
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds module to ModTrek.\n\n"
            + "Parameters: "
            + PREFIX_CODE + " <MODULE_CODE> "
            + PREFIX_CREDIT + " <MODULE_CREDITS> "
            + PREFIX_SEMYEAR + " <SEMESTER_YEAR> "
            + "(" + PREFIX_GRADE + " <GRADE>) "
            + "(" + PREFIX_TAG + " <TAG>...)\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CODE + " CS1101S "
            + PREFIX_CREDIT + " 4 "
            + PREFIX_SEMYEAR + " Y1S1 "
            + PREFIX_GRADE + " A "
            + PREFIX_TAG + " ULR "
            + PREFIX_TAG + " CSF";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module has already been added. Try using edit instead.";
    public static final String MESSAGE_MISSING_PREFIXES =
            "Missing code prefix /m, credit prefix /c, and/or sem-year prefix /y.";

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
