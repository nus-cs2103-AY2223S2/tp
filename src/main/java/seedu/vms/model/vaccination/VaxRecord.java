package seedu.vms.model.vaccination;

import java.time.LocalDateTime;


/** Represents a vaccination record of a person. */
public class VaxRecord implements Comparable<VaxRecord> {
    private final VaxType vaccination;
    private final LocalDateTime timeTaken;


    /**
     * Constructs a {@code VaxRecord}.
     *
     * @param vaccination - the type of vaccination taken.
     * @param timeTaken - the time the vaccination was taken.
     */
    public VaxRecord(VaxType vaccination, LocalDateTime timeTaken) {
        this.vaccination = vaccination;
        this.timeTaken = timeTaken;
    }


    public VaxType getVaccination() {
        return vaccination;
    }


    public LocalDateTime getTimeTaken() {
        return timeTaken;
    }


    @Override
    public int compareTo(VaxRecord other) {
        int diff = timeTaken.compareTo(other.timeTaken);
        if (diff == 0) {
            diff = vaccination.getName().compareTo(other.vaccination.getName());
        }
        return diff;
    }
}
