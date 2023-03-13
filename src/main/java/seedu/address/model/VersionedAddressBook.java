package seedu.address.model;

import java.util.LinkedList;

/**
 * Stores a addressBookStateList which holds history of AddressBook
 */
public class VersionedAddressBook extends AddressBook {
    private LinkedList<AddressBook> addressBookStateList = new LinkedList<>();
    private int currentStatePointer;

    public VersionedAddressBook() {}

    /**
     * Constructs a VersionedAddressBook.
     * @param ab
     */
    public VersionedAddressBook(AddressBook ab) {
        addressBookStateList.add(ab.clone());
        currentStatePointer = addressBookStateList.size() - 1;
    }

    /**
     * Insert node (address book) after currentStatePointer
     * 1. Delete the following node at currentStatePointer
     * 2. Add node
     * 3. Reset currentStatePointer
     * @param ab
     */
    public void commit(AddressBook ab) {
        // Step 1. Delete the following nodes at currentStatePointer
        for (int i = currentStatePointer + 1; i < addressBookStateList.size(); i++) {
            addressBookStateList.remove(i);
        }
        // Step 2. Add node
        addressBookStateList.add(ab.clone());
        // Step 3. Increment currentStatePointer by 1
        currentStatePointer = addressBookStateList.size() - 1;
    }

    /**
     * Move currentStatePointer one before
     * Return the target AddressBook (Not clone yet)
     */
    public AddressBook undo() {
        currentStatePointer--;
        return addressBookStateList.get(currentStatePointer);
    }

    /**
     * Move currentStatePointer one after
     * Return the target AddressBook (Not clone yet)
     */
    public AddressBook redo() {
        currentStatePointer++;
        return addressBookStateList.get(currentStatePointer);
    }

    //=============== helper function ===========================================================

    /**
     * @return true if we can undo AddressBook
     */
    public boolean canUndo() {
        if (currentStatePointer == 0) {
            return false;
        }
        return true;
    }

    /**
     * @return true if we can redo AddressBook
     */
    public boolean canRedo() {
        if (currentStatePointer == addressBookStateList.size() - 1) {
            return false;
        }
        return true;
    }
}
