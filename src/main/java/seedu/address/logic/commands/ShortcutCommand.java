package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ShortcutCommandParser;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Changes the remark of an existing person in the e-lister.
 * [shortcut/s] COMMAND SHORT_FORM
 */
public class ShortcutCommand extends Command {

    public static final List<String> COMMAND_WORDS = List.of(new String[]{"shortcut", "s"});

    public static final String MESSAGE_USAGE = COMMAND_WORDS
            + ": Adds a shortcut to a command.\n"
            + "Parameters: COMMAND SHORT_FORM \n"
            + "Example: " + "shortcut edit e";

    public static final String MESSAGE_SUCCESS = "New shortcut added: Command: %1$s, Shortcut: %2$s";
    public static final String MESSAGE_INVALID_SHORTCUT = "Shortcut does not exist.";

    private final Command command;
    private final String shortForm;

    /**
     * Creates a ShortcutCommand to add a shortcut alternative to a certain command.
     *
     * @param command The index of the person to add the tag to
     * @param shortForm The tag to add
     */
    public ShortcutCommand(Command command, String shortForm) {
        this.command = command;
        this.shortForm = shortForm;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (command instanceof AddCommand) {
            AddCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof ClearCommand) {
            ClearCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof DeleteCommand) {
            DeleteCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof DeleteTagCommand) {
            DeleteTagCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof EditCommand) {
            EditCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof ExitCommand) {
            ExitCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof ExportCommand) {
            ExportCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof FilterCommand) {
            FilterCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof FindCommand) {
            FindCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof HelpCommand) {
            HelpCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof ImportCommand) {
            ImportCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof RedoCommand) {
            RedoCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof UndoCommand) {
            UndoCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof ListCommand) {
            ListCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof ShortcutCommand) {
            ShortcutCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command instanceof TagCommand) {
            TagCommand.COMMAND_WORDS.add(this.shortForm);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, command, shortForm), true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutCommand // instanceof handles nulls
                && command.equals(((ShortcutCommand) other).command)
                && shortForm.equals(((ShortcutCommand) other).shortForm));
    }
}
