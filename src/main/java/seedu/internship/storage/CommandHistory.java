package seedu.internship.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Solution below adapted from
// https://github.com/AY2122S2-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/address/storage/UserInputHistory.java
/**
 * CommandHistory keeps track of past commands inputted.
 */
public class CommandHistory {
    private final List<String> list;
    private int index;
    private final String emptyString = "";

    /**
     * Creates new CommandHistory object
     */
    public CommandHistory() {
        this.list = new ArrayList<>(Arrays.asList(this.emptyString));
        this.index = 0;
    }

    /**
     * Add new input to CommandHistory object
     * @param newInput The string representing the new input
     */
    public void addInput(String newInput) {
        this.list.add(this.list.size() - 1, newInput);
        this.index = this.list.size() - 1;
    }

    /**
     * Gets the next older input. If there is not older input, the oldest input is returned again.
     * @return The next older input or if that doesn't exist, the oldest input.
     */
    public String getOlderInput() {
        if (this.index > 0) {
            this.index--;
        }
        return this.list.get(this.index);
    }

    /**
     * Gets the next most recent input. If there isn't any more recent inputs, the most recent input is returned again.
     * @return The next most recent input or if that doesn't exist, the most recent input.
     */
    public String getNewerInput() {
        if (this.index < this.list.size() - 1) {
            this.index++;
        }
        return this.list.get(this.index);
    }
}
