package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's appointment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointment(LocalDateTime, LocalDateTime)}
 */
public class Appointment {
    public static final String MESSAGE_CONSTRAINTS =
            "Both startTime and endTime should in the format of yyyy-MM-dd HHmm";

    // treat age also as a string
    private LocalDateTime startTime = null;
    private LocalDateTime endTime = null;

    /**
     * Constructs a {@code Appoinment}.
     *
     * @param startTime A valid time
     * @param endTime A valid time
     */
    public Appointment(LocalDateTime startTime, LocalDateTime endTime) {
        requireNonNull(startTime);
        requireNonNull(endTime);
        checkArgument(isValidAppointment(startTime, endTime), MESSAGE_CONSTRAINTS);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns true if given two times are valid
     */
    public static boolean isValidAppointment(LocalDateTime startTime, LocalDateTime endTime) {
        // since all check have been done before time has been converted from String to LocalDateTime
        // a lot of checks should be done here, both independently and dependently
        /*
        1. should be on the same date
        2. endTime should not be before startTime
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (endTime.isBefore(startTime) || !startTime.format(formatter).equals(endTime.format(formatter))) {
            return false;
        }
        return true;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns true if given searchDate is the same as appointment Date
     */
    public boolean isOnSearchDate(LocalDate searchDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = startTime.format(formatter);
        return date.equals(searchDate.format(formatter));
    }

    public boolean hasClash(Appointment appointment) {
        return !(endTime.isBefore(appointment.startTime) || appointment.endTime.isBefore(startTime));
    }

    private boolean isPassed() {
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.isAfter(startTime);
    }

    @Override
    public String toString() {
        return "Next appointment time from: " + startTime + " to: " + endTime
                + (isPassed() ? " (Appointment passed already)" : "");
    }

    public String saveToAddressbook() {
        return "Next appointment time from: " + startTime + " to: " + endTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && startTime.equals(((Appointment) other).startTime)
                && endTime.equals(((Appointment) other).endTime)); // state check
    }
    @Override
    public int hashCode() {
        return startTime.hashCode() ^ endTime.hashCode();
    }
}
