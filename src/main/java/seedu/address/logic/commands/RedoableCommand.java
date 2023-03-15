package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * This class enables command to be able to undo / redo
 */
public abstract class RedoableCommand extends Command {

    private ReadOnlyAddressBook previousAddressBook;
    private String saveSuccessMessage = "";

    protected abstract CommandResult executeUndoableCommand(Model model) throws CommandException;

    /**
     * Stores the current state of {@code model#addressBook}.
     */
    private void saveAddressBookSnapshot(Model model) {
        requireNonNull(model);
        this.previousAddressBook = new AddressBook(model.getAddressBook());
    }

    /**
     * Reverts the AddressBook to the state before this command
     * was executed and updates the filtered person list to
     * show all persons.
     */
    protected final void undo(Model model) {
        requireAllNonNull(model, this.previousAddressBook);

        model.setAddressBook(this.previousAddressBook);

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Executes the command and updates the filtered person
     * list to show all persons.
     */
    protected final void redo(Model model) {
        requireNonNull(model);
        try {
            executeUndoableCommand(model);
        } catch (CommandException ce) {
            throw new AssertionError("The command has been successfully executed previously; "
                                             + "it should not fail now");
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public final CommandResult execute(Model model) throws CommandException {
        saveAddressBookSnapshot(model);
        return executeUndoableCommand(model);
    }

}
