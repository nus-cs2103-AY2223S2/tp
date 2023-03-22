package seedu.address.model;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * An address book that allows for undo and redo operations.
 */
public class VersionedAddressBook extends AddressBook {

    public static final String UNDO_FAILURE = "No more commands left to undo";
    public static final String REDO_FAILURE = "No more commands left to redo";

    private final ArrayList<AddressBook> addressBookStateList;
    private int currentStatePointer;

    /**
     * Creates a new VersionedAddressBook object with the given AddressBook as the initial state.
     * @param addressBook The initial state of the address book.
     */
    public VersionedAddressBook(AddressBook addressBook) {
        super(addressBook);
        addressBookStateList = new ArrayList<>();
        AddressBook newAddition = new AddressBook(addressBook);
        addressBookStateList.add(newAddition);
        currentStatePointer = 0;
    }

    /**
     * Commits the current state of the address book.
     * Removes all elements of the addressBookStateList beyond the current pointer.
     */
    public void commit() {
        // removes ALL elements of the addressBookStateList from beyond the current pointer onwards.
        if (currentStatePointer < addressBookStateList.size() - 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
        AddressBook newAddition = new AddressBook(this);
        addressBookStateList.add(newAddition);
        currentStatePointer = addressBookStateList.size() - 1;
    }

    /**
     * Undoes the previous command by resetting the state of the address book to the previous state.
     * @throws CommandException If there are no more commands left to undo.
     */
    public void undo() throws CommandException {
        if (canUndo()) {
            currentStatePointer--;
            resetData(getCurrentState());
        } else {
            throw new CommandException(UNDO_FAILURE);
        }
    }

    /**
     * Redoes the previous undone command by resetting the state of the address book to the next state.
     * @throws CommandException If there are no more commands left to redo.
     */
    public void redo() throws CommandException {
        if (canRedo()) {
            currentStatePointer++;
            resetData(getCurrentState());
        } else {
            throw new CommandException(REDO_FAILURE);
        }
    }

    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    public boolean canRedo() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }

    public AddressBook getCurrentState() {
        return addressBookStateList.get(currentStatePointer);
    }
}

