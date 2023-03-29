package seedu.address.model.history;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Encapsulates the executed input command that the user entered.
 */
public class InputHistory {
    private String historyString = "";
    private Path historyStorageFilePath = Paths.get("data", "inputHistory.txt");

    public InputHistory() {}

    public InputHistory(String historyString) {
        this.historyString = historyString;
    }

    /**
     * Creates a History with the contents from {@code toBeCopied}
     */
    public InputHistory(InputHistory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /** Returns the history storage file path */
    public Path getHistoryStoragePath() {
        return this.historyStorageFilePath;
    }

    /** Sets the history storage file path */
    public void setHistoryStoragePath(Path newPath) {
        this.historyStorageFilePath = newPath;
    }

    /** Sets the history string of previous executed commands */
    public void setHistoryString(String historyString) {
        this.historyString = historyString;
    }

    /** Returns the history string of previous executed commands */
    public String getHistoryString() {
        return this.historyString;
    }

    /**
     * Resets the existing data of this {@code History} with {@code newHistory}.
     */
    public void resetData(InputHistory newInputHistory) {
        requireNonNull(newInputHistory);

        setHistoryStoragePath(newInputHistory.getHistoryStoragePath());
        setHistoryString(newInputHistory.getHistoryString());
    }

    @Override
    public int hashCode() {
        return historyString.hashCode();
    }
}
