package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Wraps past commands entered by user.
 */
public class EduMateHistory implements ReadOnlyEduMateHistory {

    private static final Logger logger = LogsCenter.getLogger(EduMateHistory.class);
    private ArrayList<String> eduMateHistory;
    private int index = -1;
    private final int minIndex = -1;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        eduMateHistory = new ArrayList<>();
    }

    public EduMateHistory() {}

    /**
     * Creates an EduMateHistory using the ArrayList in the {@code toBeCopied}.
     */
    public EduMateHistory(ReadOnlyEduMateHistory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the command history list with {@code history}.
     */
    public void setList(ArrayList<String> history) {
        this.eduMateHistory = history;
    }

    /**
     * Replaces the exisitng contents of the current data of this {@code EduMateHistory} with {@code newData}.
     */
    public void resetData(ReadOnlyEduMateHistory newData) {
        requireNonNull(newData);

        setList(newData.getHistory());
    }

    /**
     * Adds a command to the current command history.
     * @param command Command string
     */
    public void addCommand(String command) {
        assert command != null;
        eduMateHistory.add(0, command);
        index = -1;
    }

    /**
     * Returns the previous command the user requests.
     * @param isUp Boolean to see whether the user wants to return the older command or the newer command
     * @return The previous command
     */
    @Override
    public String getPreviousCommand(boolean isUp) {
        if (eduMateHistory.size() == 0) {
            return null;
        }
        if (isUp) {
            if (index < eduMateHistory.size() - 1) {
                index++;
            }
        } else {
            if (index > minIndex) {
                index--;
            }
            if (index == -1) {
                return "";
            }
        }
        return eduMateHistory.get(index);
    }

    @Override
    public String getCurrentCommand() {
        return eduMateHistory.get(index);
    }

    @Override
    public boolean isUpPressedBefore() {
        return index != -1;
    }

    /**
     * Returns the command history.
     * @return The command history
     */
    @Override
    public ArrayList<String> getHistory() {
        return eduMateHistory;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof EduMateHistory)) {
            return false;
        }

        EduMateHistory otherEduMateHistory = (EduMateHistory) other;
        logger.info("History equality: "
                + eduMateHistory.equals(otherEduMateHistory.eduMateHistory));
        return eduMateHistory.equals(otherEduMateHistory.eduMateHistory);

    }

}
