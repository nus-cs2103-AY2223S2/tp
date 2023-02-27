package seedu.vms.model.vaccination;

import java.time.LocalDateTime;


/** Represents a vaccination record of a person. */
public class VaccinationRecord implements Comparable<VaccinationRecord> {
    private final Vaccination vaccination;
    private final LocalDateTime timeTaken;


    public VaccinationRecord(Vaccination vaccination, LocalDateTime timeTaken) {
        this.vaccination = vaccination;
        this.timeTaken = timeTaken;
    }


    public Vaccination getVaccination() {
        return vaccination;
    }


    public LocalDateTime getTimeTaken() {
        return timeTaken;
    }


    @Override
    public int compareTo(VaccinationRecord other) {
        int diff = timeTaken.compareTo(other.timeTaken);
        if (diff == 0) {
            diff = vaccination.compareTo(other.vaccination);
        }
        return diff;
    }
}
