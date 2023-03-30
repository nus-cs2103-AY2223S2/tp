package seedu.address.model.history;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.exceptions.InputHistoryTimelineException;

/**
 * Encapsulates the executed input command that the user entered.
 */
public class InputHistory {
    private final List<String> past = new ArrayList<>();
    private final List<String> future = new ArrayList<>();
    private Path historyStorageFilePath = Paths.get("data", "inputHistory.txt");

    public InputHistory() {}

    public InputHistory(List<String> past, List<String> future) {
        this.past.addAll(past);
        this.future.addAll(future);
        Collections.reverse(this.future);
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

    /** Returns the history string of previous executed commands */
    public List<String> getPast() {
        return new ArrayList<>(past);
    }

    public List<String> getFuture() {
        List<String> outFuture = new ArrayList<>(future);
        Collections.reverse(outFuture);
        return outFuture;
    }

    public void undo(int num) throws InputHistoryTimelineException {
        if (num > past.size()) {
            throw new InputHistoryTimelineException("Attempted to undo more inputs than exists");
        }
        while (num-- > 0) {
            future.add(past.get(past.size() - 1));
            past.remove(past.size() - 1);
        }
    }

    public void redo(int num) throws InputHistoryTimelineException {
        if (num > future.size()) {
            throw new InputHistoryTimelineException("Attempted to redo more inputs than exists");
        }
        while (num-- > 0) {
            past.add(future.get(future.size() - 1));
            future.remove(future.size() - 1);
        }
    }

    public void offerCommand(String commandString, CommandResult commandResult) {
        if (!commandResult.isDeterministic()) { // not exactly accurate, <exportCommand> - to fix
            return;
        }
        future.clear();
        past.add(commandString);
    }

    /**
     * Resets the existing data of this {@code History} with {@code newHistory}.
     */
    public void resetData(InputHistory newInputHistory) {
        requireNonNull(newInputHistory);

        setHistoryStoragePath(newInputHistory.getHistoryStoragePath());
        past.clear();
        past.addAll(newInputHistory.past);
        future.clear();
        future.addAll(newInputHistory.future);
    }

    public String toDataString() {
        String dataString = past.size() + "\n" + future.size() + "\n";
        for (String s : past) {
            String[] lines = s.split("\n");
            dataString += lines.length + "\n";
            for (String t : lines) {
                dataString += t + "\n";
            }
        }
        for (String s : future) {
            String[] lines = s.split("\n");
            dataString += lines.length + "\n";
            for (String t : lines) {
                dataString += t + "\n";
            }
        }
        return dataString;
    }

    public static InputHistory fromDataString(String s) {
        String[] lines = s.split("\n");
        int pastCount = Integer.parseInt(lines[0]);
        int futureCount = Integer.parseInt(lines[1]);
        List<String> past = new ArrayList<>();
        List<String> future = new ArrayList<>();
        int seek = 2;
        for (int i = 0; i < pastCount; i++) {
            int lineCount = Integer.parseInt(lines[seek++]);
            String commandString = "";
            for (int j = 0; j < lineCount; j++) {
                commandString += lines[seek++];
            }
            past.add(commandString);
        }
        for (int i = 0; i < futureCount; i++) {
            int lineCount = Integer.parseInt(lines[seek++]);
            String commandString = "";
            for (int j = 0; j < lineCount; j++) {
                commandString += lines[seek++];
            }
            future.add(commandString);
        }
        return new InputHistory(past, future);
    }

    @Override
    public int hashCode() {
        return Objects.hash(past, future);
    }
}
