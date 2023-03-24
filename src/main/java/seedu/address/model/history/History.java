package seedu.address.model.history;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Encapsulates the executed input command that the user entered.
 */
public class History {
    private String historyString = "";
    private Path historyStorageFilePath = Paths.get("data", "history.txt");

    public History() {}

    public History(String historyString) {
        this.historyString = historyString;
    }

    /**
     * Creates a History with the contents from {@code toBeCopied}
     */
    public History(History toBeCopied) {
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
    public void resetData(History newHistory) {
        requireNonNull(newHistory);

        setHistoryStoragePath(newHistory.getHistoryStoragePath());
        setHistoryString(newHistory.getHistoryString());
    }

    @Override
    public int hashCode() {
        return historyString.hashCode();
    }
}
