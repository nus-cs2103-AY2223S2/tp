package seedu.task.commons.core.index;

import java.util.ArrayList;

import seedu.task.commons.core.Messages;
import seedu.task.logic.commands.exceptions.CommandException;

/**
 * A list of indices to support commands that can take in multiple indices.
 */
public class IndexList {
    private ArrayList<Index> indexList;

    /**
     * Constructs an empty {@code IndexList} to process multiple indices entered.
     */
    public IndexList() {
        this.indexList = new ArrayList<Index>();
    }

    /**
     * Adds an index into this list.
     */
    public void add(Index parseIndex) {
        indexList.add(parseIndex);
    }

    /**
     * Returns the number of indices stored in this list.
     */
    public int size() {
        return indexList.size();
    }

    public int getZeroBasedIndex(int position) {
        return indexList.get(position).getZeroBased();
    }

    /**
     * Checks that every index keyed in is a valid index
     * @param lastIndex invalid index value
     * @throws CommandException invalid index detected
     */
    public boolean isValidIndex(int lastIndex) throws CommandException {
        int n = this.indexList.size();

        for (int i = 0; i < n; i++) {
            if (this.indexList.get(i).getZeroBased() >= lastIndex) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if (this.indexList.get(i).getZeroBased() >= this.indexList.get(i + 1).getZeroBased()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
        }

        return true;
    }

    /**
     * Prepares indices to be deleted by accounting for shifts in indices after deletion.
     */
    public void modifyForDelete() {
        int n = this.indexList.size();
        for (int i = 0; i < n; i++) {
            this.indexList.get(i).modifyIndex(-i);
        }
    }

    /**
     * Takes in a list of user int and change it to zero-based for program to interpret.
     * @param numbers indices user keys in
     * @return encapsulated zero-based indices
     */
    public static IndexList fromOneBasedList(int[] numbers) {
        IndexList il = new IndexList();
        int n = numbers.length;

        for (int i = 0; i < n; i++) {
            il.add(Index.fromOneBased(numbers[i]));
        }
        return il;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof IndexList) {
            IndexList checked = (IndexList) other;
            int n = checked.size();
            if (n != this.indexList.size()) {
                return false;
            }
            for (int i = 0; i < n; i++) {
                if (!checked.indexList.get(i).equals(this.indexList.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
