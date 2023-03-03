package seedu.vms.model.vaccination;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A utility class to check if a patient meets the requirements to take a
 * vaccination.
 */
public class VaxChecker {
    /**
     * Checks if the given attributes of a patient meets the requirements to
     * take the specified vaccination.
     *
     * @param vaxType - the vaccination type to check.
     * @param age - the age ot the patient.
     * @param records - the list of {@code VaxRecord} of the patient.
     * @param time - the time of the shot.
     * @return {@code true} if the patient meets the requirements and
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


    private static boolean checkHistReq(List<Requirement> reqs, List<VaxRecord> records) {
        List<HashSet<String>> grpSets = getHistGrpSet(records);
        ArrayDeque<Requirement> pendingReqs = new ArrayDeque<>(reqs);
        for (HashSet<String> grpSet : grpSets) {
            ArrayDeque<Requirement> unsatisfiedReqs = new ArrayDeque<>();
            while (!pendingReqs.isEmpty()) {
                Requirement req = pendingReqs.pop();
                if (!req.check(grpSet)) {
                    unsatisfiedReqs.add(req);
                }
            }
            pendingReqs = unsatisfiedReqs;
            if (pendingReqs.isEmpty()) {
                return true;
            }
        }
        return false;
    }


    private static List<HashSet<String>> getHistGrpSet(List<VaxRecord> records) {
        List<HashSet<String>> grpSets = records.stream()
                .map(record -> record.getVaccination().getGroups())
                .collect(Collectors.toList());
        if (records.isEmpty()) {
            grpSets.add(new HashSet<>());
        }
        return grpSets;
    }


    private static boolean checkReq(List<Requirement> reqs, HashSet<String> set) {
        for (Requirement req : reqs) {
            if (!req.check(set)) {
                return false;
            }
        }
        return true;
    }
}
