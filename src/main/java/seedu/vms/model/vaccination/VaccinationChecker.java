package seedu.vms.model.vaccination;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A utility class to check if a person meets the requirements to take a
 * vaccination.
 */
public class VaccinationChecker {
    /**
     * Checks if the given attributes of a person meets the requirements to
     * take the specified vaccination.
     *
     * @param vaccination - the vaccination to check
     * @param age - the age ot the person
     * @param records - the list of {@code VaccinationRecord} of the person.
     * @param time - the time of the shot.
     * @return {@code true} if the person meets the requirements and
     *      {@code false} otherwise.
     */
    public static boolean check(Vaccination vaccination,
                int age, List<VaccinationRecord> records, LocalDateTime time) {
        boolean isWithinAge = vaccination.getMinAge() <= age && age <= vaccination.getMaxAge();

        boolean isSpaced = records.isEmpty();
        if (!isSpaced) {
            isSpaced = checkSpacing(vaccination, records, time);
        }

        boolean hasPrereq = checkPrereq(vaccination, records);

        return isWithinAge && isSpaced && hasPrereq;
    }


    private static boolean checkSpacing(Vaccination vaccination,
                List<VaccinationRecord> records, LocalDateTime time) {
        ArrayList<VaccinationRecord> recordsCopy = new ArrayList<>(records);
        recordsCopy.sort(Comparator.naturalOrder());
        LocalDateTime lastDoseTime = recordsCopy.get(recordsCopy.size() - 1).getTimeTaken();
        LocalDateTime earliestDoseTime = lastDoseTime.plus(vaccination.getMinSpacing(), ChronoUnit.DAYS);
        return earliestDoseTime.isBefore(time) || earliestDoseTime.isEqual(time);
    }


    private static boolean checkPrereq(Vaccination vaccination, List<VaccinationRecord> records) {
        List<DoseType> takenTypes = records
                .stream()
                .map(record -> record.getVaccination().getDoseType())
                .collect(Collectors.toList());

        if (takenTypes.contains(vaccination.getDoseType())) {
            // type already taken
            return false;
        }

        if (vaccination.getPrereqs().isEmpty()) {
            // account for no prereq
            return true;
        }

        for (DoseType doseType : vaccination.getPrereqs()) {
            if (takenTypes.contains(doseType)) {
                return true;
            }
        }
        return false;
    }
}
