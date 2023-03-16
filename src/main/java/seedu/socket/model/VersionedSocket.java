package seedu.socket.model;

import java.util.ArrayList;

/**
 * Facilitates undo/redo mechanism by maintaining {@code Socket} states.
 */
public class VersionedSocket extends Socket {
    /** The initial value of currentStatePointer. */
    private static final int INIT_POINTER = 0;
    /** The {@code ArrayList<ReadOnlySocket>} in which the {@code Socket} states are stored. */
    private final ArrayList<ReadOnlySocket> socketStateList;
    /** The pointer to the current {@code Socket} state. */
    private int currentStatePointer;
    /** The associated {@code Socket} instance. */
    private final Socket socket;

    /**
     * Constructs a {@code VersionedSocket} with the given {@code Socket} and initializes the
     * {@code currenStatePointer}.
     *
     * @param initialSocket {@code Socket} to initialize the {@code VersionedSocket} with.
     */
    public VersionedSocket(Socket initialSocket) {
        socket = initialSocket;
        socketStateList = new ArrayList<>();
        socketStateList.add(new Socket(initialSocket));
        currentStatePointer = INIT_POINTER;
    }

    /**
     * Saves the current {@code Socket} state.
     */
    public void commit(Socket socket) {
        assert currentStatePointer >= 0;
        if (currentStatePointer != (socketStateList.size() - 1)) {
            socketStateList.subList(currentStatePointer + 1, socketStateList.size()).clear();
        }
        socketStateList.add(new Socket(socket));
        currentStatePointer++;
    }
    /**
     * Restores the previous {@code Socket} state.
     */
    public void undo() {
        --currentStatePointer;
        socket.resetData(socketStateList.get(currentStatePointer));
    }
    /**
     * Restores a previously undone {@code Socket} state.
     */
    public void redo() {
        ++currentStatePointer;
        socket.resetData(socketStateList.get(currentStatePointer));
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
