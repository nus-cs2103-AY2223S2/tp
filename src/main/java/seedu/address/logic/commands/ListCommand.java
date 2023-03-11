package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.model.Model;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.module.ModuleLectureContainsKeywordsPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

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
}
