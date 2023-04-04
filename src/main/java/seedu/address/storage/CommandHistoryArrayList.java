package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

/**
 * The {@code ArrayList} backing the {@code CommandHistory} class.
 */
public class CommandHistoryArrayList {
    private final ArrayList<String> arrayList;
    private String temporaryUserInput;
    private int currentPosition;

    /**
     * Instantiates a new {@code CommandHistoryArrayList}.
     */
    public CommandHistoryArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        temporaryUserInput = "";
        currentPosition = arrayList.size();
    }

    public String getPreviousUserInput(String currentUserInput) {
        requireNonNull(currentUserInput);
        assert currentPosition >= 0 && currentPosition <= arrayList.size();

        if (currentPosition == 0) {
            return null;
        }

        if (currentPosition == arrayList.size()) {
            temporaryUserInput = currentUserInput;
        }

        currentPosition -= 1;
        return arrayList.get(currentPosition);
    }

    public String getNextUserInput() {
        assert currentPosition >= 0 && currentPosition <= arrayList.size();

        if (currentPosition == arrayList.size()) {
            return null;
        }

        if (currentPosition == arrayList.size() - 1) {
            currentPosition += 1;
            return temporaryUserInput;
        }

        currentPosition += 1;
        return arrayList.get(currentPosition);
    }

    /**
     * Commits the {@code currentUserInput} to the {@code CommandHistoryArrayList}.
     *
     * @param currentUserInput The user's input in the {@code CommandBox}.
     */
    public void commitUserInput(String currentUserInput) {
        requireNonNull(currentUserInput);

        arrayList.add(currentUserInput);
        temporaryUserInput = "";
        currentPosition = arrayList.size();
    }

    public ArrayList<String> getInternalArrayList() {
        return arrayList;
    }
}
