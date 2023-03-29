package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Class represents an employee's payroll details in the database.
 */
public class LeaveCounter {
    public static final String MESSAGE_CONSTRAINTS =
            "Leave count has to be a non negative integer, please try again.";
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

    /**
     * Returns true if a given leave number is valid .
     */
    public static boolean isValidLeaveCount(String test) {
        if (test == null) {
            return true;
        } else {
            try {
                int leaveCount = Integer.parseInt(test.trim());
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
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
     * Checks if there are enough days of leave.
     */
    public LeaveCounter takeLeave(int numberOfDays) throws Exception {
        if (!hasEnoughLeave(numberOfDays)) {
            throw new Exception("Not enough days of leave.");
        }
        return new LeaveCounter(leaveCount - numberOfDays);
    }

    @Override
    public String toString() {
        return leaveCount + " days of leave";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof LeaveCounter
                && this.leaveCount == ((LeaveCounter) other).getLeaveCount());
    }
}
