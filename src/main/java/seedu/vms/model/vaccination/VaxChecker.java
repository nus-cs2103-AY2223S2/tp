package seedu.vms.model.vaccination;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


/**
 * A utility class to check if a person meets the requirements to take a
 * vaccination.
 */
public class VaxChecker {
    /**
     * Checks if the given attributes of a person meets the requirements to
     * take the specified vaccination.
     *
     * @param vaxType - the vaccination type to check.
     * @param age - the age ot the person.
     * @param allergies - allergies of the person.
     * @param records - the list of {@code VaxRecord} of the person.
     * @param time - the time of the shot.
     * @return {@code true} if the person meets the requirements and
     *      {@code false} otherwise.
     */
    public static boolean check(VaxType vaxType,
                int age, HashSet<String> allergies, List<VaxRecord> records, LocalDateTime time) {
        boolean isWithinAge = vaxType.getMinAge() <= age && age <= vaxType.getMaxAge();

        boolean isSpaced = records.isEmpty();
        if (!isSpaced) {
            isSpaced = checkSpacing(vaxType, records, time);
        }

        boolean isAllergiesSatisfied = checkReq(vaxType.getAllergyReqs(), allergies);
        boolean isHistorySatisfied = checkHistReq(vaxType.getHistoryReqs(), records);

        return isWithinAge && isSpaced && isAllergiesSatisfied && isHistorySatisfied;
    }


    private static boolean checkSpacing(VaxType vaccination,
                List<VaxRecord> records, LocalDateTime time) {
        ArrayList<VaxRecord> recordsCopy = new ArrayList<>(records);
        recordsCopy.sort(Comparator.naturalOrder());
        LocalDateTime lastDoseTime = recordsCopy.get(recordsCopy.size() - 1).getTimeTaken();
        LocalDateTime earliestDoseTime = lastDoseTime.plus(vaccination.getMinSpacing(), ChronoUnit.DAYS);
        return earliestDoseTime.isBefore(time) || earliestDoseTime.isEqual(time);
    }


    private static boolean checkHistReq(List<VaxRequirement> reqs, List<VaxRecord> records) {
        HashSet<String> takenGroups = records.stream()
                .map(record -> record.getVaccination().getGroups())
                .collect(() -> new HashSet<>(),
                        (takenSet, vaxSet) -> takenSet.addAll(vaxSet),
                        (set1, set2) -> set1.addAll(set2));
        return checkReq(reqs, takenGroups);
    }


    private static boolean checkReq(List<VaxRequirement> reqs, HashSet<String> set) {
        for (VaxRequirement req : reqs) {
            if (!req.check(set)) {
                return false;
            }
        }
        return true;
    }
}
