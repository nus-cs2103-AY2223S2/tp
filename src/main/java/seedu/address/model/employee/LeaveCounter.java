package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Class represents an employee's payroll details in the database.
 */
public class LeaveCounter {
    public static final String MESSAGE_CONSTRAINTS =
            "Leave count has to be between 0 and 365 (inclusive), please try again.";
    public static final String FILTER_PARAMETER = "l";
    private static final int DEFAULT_LEAVE_COUNT = 21;
    private int leaveCount;

    /**
     * Constructs a {@code LeaveCounter} with the given number of days of leaves.
     */
    public LeaveCounter(int leaveCount) {
        requireNonNull(leaveCount);
        checkArgument(leaveCount >= 0, MESSAGE_CONSTRAINTS);
        this.leaveCount = leaveCount;
    }

    /**
     * Constructs a {@code LeaveCounter} with the default number of days of leave.
     */
    public LeaveCounter() {
        this.leaveCount = DEFAULT_LEAVE_COUNT;
    }

    public LeaveCounter(String leaveCounter) {
        this(Integer.parseInt(leaveCounter));
    }

    /**
     * Returns true if a given leave number is valid .
     */
    public static boolean isValidLeaveCount(String test) {
        if (test == null) {
            return false;
        }
        try {
            long leaveCount = Long.parseLong(test.trim());
            return (leaveCount >= 0 && leaveCount <= 365);
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Gets the number of days of leave.
     */
    public int getLeaveCount() {
        return leaveCount;
    }

    /**
     * Checks if there are enough days of leave.
     */
    public boolean hasEnoughLeave(int numberOfDays) {
        return leaveCount >= numberOfDays;
    }

    /**
     * Returns a new leave with the reduced number of days of leave.
     */
    public LeaveCounter takeLeave(int numberOfDays) throws CommandException {
        if (!hasEnoughLeave(numberOfDays)) {
            throw new CommandException("Not enough days of leave.");
        }
        return new LeaveCounter(leaveCount - numberOfDays);
    }

    @Override
    public String toString() {
        return String.valueOf(leaveCount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof LeaveCounter
                && this.leaveCount == ((LeaveCounter) other).getLeaveCount());
    }
}
