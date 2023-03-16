package seedu.socket.model;

import java.util.ArrayList;

/**
 * Facilitates undo/redo mechanism by maintaining SOCket states.
 */
public class VersionedSocket extends AddressBook {
    /** The initial value of currentStatePointer. */
    private static final int INIT_POINTER = 0;
    /** The {@code ArrayList<ReadOnlyAddressBook>} in which the {@code Socket} states are stored. */
    private final ArrayList<ReadOnlyAddressBook> socketStateList;
    /** The pointer to the current {@code Socket} state. */
    private int currentStatePointer;
    /** The associated {@code AddressBook} instance. */
    private final AddressBook addressBook;

    /**
     * Constructs a {@code VersionedSocket} with the given {@code AddressBook} and initializes the
     * {@code currenStatePointer}.
     *
     * @param initialAddressBook {@code AddressBook} to initialize the {@code VersionedSocket} with.
     */
    public VersionedSocket(AddressBook initialAddressBook) {
        addressBook = initialAddressBook;
        socketStateList = new ArrayList<>();
        socketStateList.add(new AddressBook(initialAddressBook));
        currentStatePointer = INIT_POINTER;
    }

    /**
     * Saves the current {@code SOCket} state.
     */
    public void commit(AddressBook addressBook) {
        assert currentStatePointer >= 0;
        if (currentStatePointer != (socketStateList.size() - 1)) {
            socketStateList.subList(currentStatePointer + 1, socketStateList.size()).clear();
        }
        socketStateList.add(new AddressBook(addressBook));
        currentStatePointer++;
    }
    /**
     * Restores the previous {@code SOCket} state.
     */
    public void undo() {
        --currentStatePointer;
        addressBook.resetData(socketStateList.get(currentStatePointer));
    }
    /**
     * Restores a previously undone {@code SOCket} state.
     */
    public void redo() {
        ++currentStatePointer;
        addressBook.resetData(socketStateList.get(currentStatePointer));
    }

    /**
     * Returns {@code false} if {@code currentStatePointer} is at index 0, {@code true} otherwise.
     *
     * @return {@code false} if {@code currentStatePointer} is at index 0, {@code true} otherwise.
     */
    public boolean canUndoSocket() {
        return currentStatePointer != 0;
    }

    /**
     * Returns {@code false} if {@code currentStatePointer} is at index {@code socketStateList.size() - 1},
     * {@code true} otherwise.
     *
     * @return {@code false} if {@code currentStatePointer} is at index {@code socketStateList.size() - 1},
     *     {@code true} otherwise.
     */
    public boolean canRedoSocket() {
        return currentStatePointer != (socketStateList.size() - 1);
    }
}
