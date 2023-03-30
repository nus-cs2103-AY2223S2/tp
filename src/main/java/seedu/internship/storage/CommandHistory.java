package seedu.internship.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHistory {
    private final List<String> list;
    private int index;
    private final String emptyString = "";

    public CommandHistory() {
        this.list = new ArrayList<>(Arrays.asList(this.emptyString));
        this.index = 0;
    }

    public void addInput(String newInput) {
        this.list.add(this.list.size() - 1, newInput);
        this.index = this.list.size() - 1;
    }

    public String getOlderInput() {
        if (this.index > 0) {
            this.index--;
        }
        return this.list.get(this.index);
    }

    public String getNewerInput() {
        if (this.index < this.list.size() - 1) {
            this.index++;
        }
        return this.list.get(this.index);
    }
}
