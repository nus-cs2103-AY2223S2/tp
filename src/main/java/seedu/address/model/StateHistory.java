package seedu.address.model;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.TreeMap;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Records past (& future) {@code Model} states and {@code Command}s, such that the {@code Model} state at any point
 * on the timeline can be reconstructed.
 */
public class StateHistory {

    /**
     * Default number of commands between {@code Model} state snapshots.
     * Snapshots may be taken at smaller intervals to handle non-deterministic {@code Command}s.
     */
    public static final int HISTORY_DEFAULT_INTERVAL = 10;

    private int now;
    private final TreeMap<Integer, Model> modelHistory;
    private final TreeMap<Integer, Command> commandHistory;

    /**
     * Creates a {@code StateHistory} with timeline rooted at the given {@code Model}.
     *
     * @param initial {@code Model} to begin timeline with
     */
    public StateHistory(Model initial) {
        now = 0;
        modelHistory = new TreeMap<>();
        modelHistory.put(0, initial.stateDetachedCopy());
        commandHistory = new TreeMap<>();
    }

    /**
     * Clears all future events after {@code now}, to make way for a split Redo timeline.
     */
    private void clearFuture() {
        while (modelHistory.ceilingKey(now + 1) != null) {
            modelHistory.remove(modelHistory.ceilingKey(now + 1));
        }
        while (commandHistory.ceilingKey(now + 1) != null) {
            commandHistory.remove(commandHistory.ceilingKey(now + 1));
        }
    }

    /**
     * Adds {@code command} to the timeline, immediately after the present time.
     * Any existing future events will be discarded and can no longer be redone.
     *
     * @param command {@code Command} to add to timeline
     * @param model {@code Model} after the execution of {@code command}
     * @param result {@code CommandResult} of the execution of {@code command}
     */
    public void offerCommand(Command command, Model model, CommandResult result) {
        if (!result.affectsModel()) {
            return;
        }
        clearFuture();
        now++;
        commandHistory.put(now, command.deepCopy());
        assert !modelHistory.isEmpty() : "modelHistory should never be empty";
        if (!result.isDeterministic() || modelHistory.lastKey() <= now - HISTORY_DEFAULT_INTERVAL) {
            modelHistory.put(now, model.stateDetachedCopy());
        }
    }

    /**
     * Retrieves the state of the Model at the effective present time.
     * The returned Model is a deep copy and may be mutated freely.
     *
     * @return the Model state at the present time
     * */
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

    /**
     * Undoes {@code steps} commands, moving the effective present time as many commands backwards.
     * If fewer than {@code steps} commands can be undone, the maximum possible number smaller than {@code steps}
     * will be undone instead.
     *
     * @param steps number of commands to undo
     * @return number of commands which were successfully undone
     */
    public int undo(int steps) {
        int stop = max(now - steps, modelHistory.firstKey());
        int res = now - stop;
        now = stop;
        return res;
    }

    /**
     * Redoes {@code steps} commands, moving the effective present time as many commands forwards.
     * If fewer than {@code steps} commands can be redone, the maximum possible number smaller than {@code steps}
     * will be redone instead.
     *
     * @param steps number of commands to redo
     * @return number of commands which were successfully redone
     */
    public int redo(int steps) {
        int stop = min(now + steps, !commandHistory.isEmpty() ? commandHistory.lastKey() : 0);
        int res = stop - now;
        now = stop;
        return res;
    }
}
