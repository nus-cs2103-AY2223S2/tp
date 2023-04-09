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

    /**
     * Creates an {@code InputHistory} using the given {@code past} and {@code future}.
     *
     * @param past the list of previous command inputs
     * @param future the list of undone following command inputs
     */
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
        return historyStorageFilePath;
    }

    /** Sets the history storage file path */
    public void setHistoryStoragePath(Path newPath) {
        this.historyStorageFilePath = newPath;
    }

    /** Returns the list of previously executed command inputs */
    public List<String> getPast() {
        return new ArrayList<>(past);
    }

    /** Returns the list of undone following command inputs */
    public List<String> getFuture() {
        List<String> outFuture = new ArrayList<>(future);
        Collections.reverse(outFuture);
        return outFuture;
    }

    /**
     * Undoes the last {@code num} command inputs.
     *
     * @param num number of command inputs to undo
     * @throws InputHistoryTimelineException if fewer than {@code num} previous command inputs exist
     */
    public void undo(int num) throws InputHistoryTimelineException {
        if (num > past.size()) {
            throw new InputHistoryTimelineException("Attempted to undo more inputs than exists");
        }
        while (num-- > 0) {
            future.add(past.get(past.size() - 1));
            past.remove(past.size() - 1);
        }
    }

    /**
     * Redoes the next {@code num} command inputs.
     *
     * @param num number of command inputs to redo
     * @throws InputHistoryTimelineException if fewer than {@code num} future command inputs exist
     */
    public void redo(int num) throws InputHistoryTimelineException {
        if (num > future.size()) {
            throw new InputHistoryTimelineException("Attempted to redo more inputs than exists");
        }
        while (num-- > 0) {
            past.add(future.get(future.size() - 1));
            future.remove(future.size() - 1);
        }
    }

    /**
     * Adds {@code commandString} to the list of previous command inputs,
     * if deemed appropriate given {@code commandResult}.
     *
     * @param commandString the command input to add
     * @param commandResult the result of executing {@code commandString}
     */
    public void offerCommand(String commandString, CommandResult commandResult) {
        if (!commandResult.affectsModel()) {
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

    /** Returns the contents of this InputHistory in a loadable String format. */
    public String toDataString() {
        String dataString = past.size() + "\n" + future.size() + "\n";
        for (String s : past) {
            String[] lines = s.split("\n");
            dataString += lines.length + "\n";
            for (String t : lines) {
                dataString += t + "\n";
            }
        }
        List<String> orderedFuture = getFuture();
        for (String s : orderedFuture) {
            String[] lines = s.split("\n");
            dataString += lines.length + "\n";
            for (String t : lines) {
                dataString += t + "\n";
            }
        }
        return dataString;
    }

    /** Creates an InputHistory based on a loadable String {@code s}. */
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
