package seedu.address.model.time;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.joda.time.Hours;
import org.joda.time.LocalTime;

import seedu.address.logic.recommender.timing.exceptions.CommitmentClashException;
import seedu.address.model.commitment.Commitment;
import seedu.address.model.time.exceptions.WrongTimeException;
import seedu.address.model.time.util.TimeUtil;

/**
 * Represents an hour timeslot in a Timetable.
 */
public class HourBlock extends TimePeriod {

    public static final String WRONG_TIME_MESSAGE = "Timing does not match!";
    public static final String ALREADY_FILLED_MESSAGE = "Slot is already filled by a class!";

    private Optional<Commitment> commitment = Optional.empty();

    public HourBlock(LocalTime startTime, Day schoolDay) {
        super(startTime, startTime.plusHours(1), schoolDay);
    }

    /**
     * Copys a HourBlock to another HourBlock.
     */
    public HourBlock(HourBlock hourBlock) {
        super(hourBlock.getStartTime(), hourBlock.getEndTime(), hourBlock.getSchoolDay());
        this.commitment = hourBlock.getCommitment();
    }

    public LocalTime getStartTime() {
        return super.getStartTime();
    }

    public LocalTime getEndTime() {
        return super.getEndTime();
    }

    @Override
    public TimeBlock merge(TimePeriod timePeriod) throws WrongTimeException {
        if (this.isConsecutiveWith(timePeriod) && timePeriod.getSchoolDay().equals(getSchoolDay())) {
            if (this.getStartTime().isBefore(timePeriod.getStartTime())
                    && getSchoolDay().equals(timePeriod.getSchoolDay())) {
                return new TimeBlock(this.getStartTime(), timePeriod.getEndTime(), getSchoolDay());
            } else {
                return new TimeBlock(timePeriod.getStartTime(), this.getEndTime(), getSchoolDay());
            }
        } else {
            throw new WrongTimeException("Must be consecutive timeblocks together!");
        }
    }

    @Override
    public Hours getHoursBetween() {
        return Hours.ONE;
    }

    public Optional<Commitment> getCommitment() {
        return commitment;
    }

    public boolean isFree() {
        return commitment.isEmpty();
    }

    /**
     * Sets this slot to hold the current lesson.
     * @param commitment
     */
    public void setCommitment(Commitment commitment) {
        requireNonNull(commitment);
        if (!isFree()) {
            throw new CommitmentClashException(ALREADY_FILLED_MESSAGE);
        } else if (canFitCommitment(commitment)) {
            this.commitment = Optional.ofNullable(commitment);
        } else {
            throw new WrongTimeException(WRONG_TIME_MESSAGE);
        }
    }

    /**
     * Removes the commitment from the HourBlock.
     */
    public void removeCommitment() {
        commitment = Optional.empty();
    }

    /**
     * Verifies if the timeslot is suitable to input that lesson.
     */
    public boolean canFitCommitment(Commitment commitment) {
        // timeslot must either start match with start or end match with end
        // or that timeslot start is after lesson start AND timeslot end is before lesson end.
        LocalTime lessonStartTime = commitment.getStartTime();
        LocalTime lessonEndTime = commitment.getEndTime();
        if (!commitment.getDay().equals(getSchoolDay())) {
            return false;
        }
        if (getStartTime().isEqual(lessonStartTime) || getEndTime().isEqual(lessonEndTime)) {
            return true;
        } else if (getStartTime().isAfter(lessonStartTime) && getEndTime().isBefore(lessonEndTime)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]\nClass: %s\n%s",
            TimeUtil.formatLocalTime(getStartTime()),
            TimeUtil.formatLocalTime(getEndTime()),
            commitment.isPresent() ? commitment.get() : "", getSchoolDay());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        HourBlock hourBlock = (HourBlock) o;
        return commitment.equals(hourBlock.getCommitment());
    }
}
