package tfifteenfour.clipboard.logic.commands;
import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.student.StudentTakingModulePredicate;


/**
 * Finds and lists all persons who is taking that module
 * Module code is case insensitive.
 */
public class ModuleCommand extends Command {
    public static final String COMMAND_WORD = "module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who are taking the module "
            + "the specified module (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: MODULE \n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    private final StudentTakingModulePredicate predicate;

    /**
     * Creates a ModuleCommand to list only students taking the specified module
     *
     * @param predicate the predicate tester for checking if the student module matches the specified module
     */
    public ModuleCommand(StudentTakingModulePredicate predicate) {
        super(false);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(this,
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getUnmodifiableFilteredStudentList().size()), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCommand // instanceof handles nulls
                && predicate.equals(((ModuleCommand) other).predicate)); // state check
    }
}
