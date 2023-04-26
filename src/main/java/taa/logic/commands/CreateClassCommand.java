package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;

import taa.logic.commands.exceptions.CommandException;
import taa.model.ClassList;
import taa.model.Model;

/**
 * Creates a new class list.
 */
public class CreateClassCommand extends Command {

    public static final String COMMAND_WORD = "create_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new class "
            + "Parameters: "
            + "[" + PREFIX_CLASS_TAG + "CLASS_NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS_TAG + "T01 ";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists";

    private final ClassList toAdd;

    /**
     * Creates an CreateClassCommand to add the specified ClassList
     */
    public CreateClassCommand(ClassList classList) {
        requireNonNull(classList);
        toAdd = classList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClassList(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.addClassList(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    //Credits: Solution below adapted from ChatGPT3.5 codes.
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CreateClassCommand
                && toAdd.equals(((CreateClassCommand) other).toAdd));
    }
}
