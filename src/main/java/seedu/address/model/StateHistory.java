package seedu.address.model;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.TreeMap;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

public class StateHistory {
    private int now;
    private final TreeMap<Integer, Model> modelHistory;
    private final TreeMap<Integer, Command> commandHistory;

    /** Default number of commands between Model snapshots, when not forced to take additional snapshots. */
    public static final int HISTORY_DEFAULT_INTERVAL = 10;

    public StateHistory(Model initial) {
        now = 0;
        modelHistory = new TreeMap<>();
        modelHistory.put(0, initial.stateDetachedCopy());
        commandHistory = new TreeMap<>();
    }

    private void clearFuture() {
        while (modelHistory.ceilingKey(now + 1) != null) {
            modelHistory.remove(modelHistory.ceilingKey(now + 1));
        }
        while (commandHistory.ceilingKey(now + 1) != null) {
            commandHistory.remove(commandHistory.ceilingKey(now + 1));
        }
    }

    public void addCommand(Command command, Model model, CommandResult result) {
        if (!result.isUndoable()) {
            return;
        }
        clearFuture();
        now++;
        commandHistory.put(now, command);
        assert !modelHistory.isEmpty() : "modelHistory should never be empty";
        if (result.isDeterministic() || modelHistory.lastKey() <= now - HISTORY_DEFAULT_INTERVAL) {
            modelHistory.put(now, model.stateDetachedCopy());
        }
    }

    public Model presentModel() {
        int time = modelHistory.floorKey(now);
        Model model = modelHistory.get(time).stateDetachedCopy();
        while (time < now) {
            time++;
            try {
                commandHistory.get(time).execute(model);
            } catch (CommandException ex) {
                assert false : "Deterministic command failed on playback";
            }
        }
        return model;
    }

    public int undo(int steps) {
        int stop = max(now - steps, modelHistory.firstKey());
        int res = now - stop;
        now = stop;
        return res;
    }

    public int redo(int steps) {
        int stop = min(now + steps, commandHistory.lastKey());
        int res = stop - now;
        now = stop;
        return res;
    }
}
