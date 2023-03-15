package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.model.Model;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.module.ModuleLectureContainsKeywordsPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all modules, lectures or videos.\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "cs2103 "
            + PREFIX_LECTURE + "lecture-1 ";

    public static final String MESSAGE_SUCCESS = "Listed all modules";

    private ModuleContainsKeywordsPredicate modulePredicate;

    private ModuleLectureContainsKeywordsPredicate moduleLecturePredicate;

    /**
     * Creates an empty ListCommand
     */
    public ListCommand() {}

    /**
     * Creates an ListCommand to add the specified {@code ModuleContainsKeywordsPredicate}
     */
    public ListCommand(ModuleContainsKeywordsPredicate predicate) {
        modulePredicate = predicate;
    }

    /**
     * Creates an ListCommand to add the specified {@code ModuleLectureContainsKeywordsPredicate}
     */
    public ListCommand(ModuleLectureContainsKeywordsPredicate predicate) {
        moduleLecturePredicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (modulePredicate != null) {
            model.updateFilteredModuleList(modulePredicate);
        } else if (moduleLecturePredicate != null) {
            model.updateFilteredModuleList(moduleLecturePredicate);
        } else {
            model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }
        if (other instanceof ListCommand) {
            ListCommand otherCommand = (ListCommand) other;
            if (modulePredicate != null && otherCommand.modulePredicate != null) {
                return modulePredicate.equals(otherCommand.modulePredicate);
            }
            if (moduleLecturePredicate != null && otherCommand.moduleLecturePredicate != null) {
                return moduleLecturePredicate.equals(otherCommand.moduleLecturePredicate);
            }
            return (modulePredicate == null && moduleLecturePredicate == null)
                && (otherCommand.modulePredicate == null && otherCommand.moduleLecturePredicate == null);
        }
        return false;
    }
}
