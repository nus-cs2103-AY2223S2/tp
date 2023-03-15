package seedu.vms.model.vaccination;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import seedu.vms.model.Age;
import seedu.vms.model.GroupName;


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
     * @param takenTypes - the list of {@code VaxRecord} of the patient.
     * @return {@code true} if the patient meets the requirements and
     *      {@code false} otherwise.
     */
    public static boolean check(VaxType vaxType,
                Age age, HashSet<GroupName> allergies, List<VaxType> takenTypes) {
        boolean isWithinAge = age.compareTo(vaxType.getMinAge()) * vaxType.getMaxAge().compareTo(age) >= 0;

        boolean isAllergiesSatisfied = checkReq(vaxType.getAllergyReqs(), allergies);
        boolean isHistorySatisfied = checkHistReq(vaxType.getHistoryReqs(), takenTypes);

        return isWithinAge && isAllergiesSatisfied && isHistorySatisfied;
    }


    private static boolean checkHistReq(List<Requirement> reqs, List<VaxType> records) {
        List<HashSet<GroupName>> grpSets = getHistGrpSet(records);
        ArrayDeque<Requirement> pendingReqs = new ArrayDeque<>(reqs);
        for (HashSet<GroupName> grpSet : grpSets) {
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


    private static List<HashSet<GroupName>> getHistGrpSet(List<VaxType> records) {
        List<HashSet<GroupName>> grpSets = records.stream()
                .map(record -> record.getGroups())
                .collect(Collectors.toList());
        if (records.isEmpty()) {
            grpSets.add(new HashSet<>());
        }
        return grpSets;
    }


    private static boolean checkReq(List<Requirement> reqs, HashSet<GroupName> set) {
        for (Requirement req : reqs) {
            if (!req.check(set)) {
                return false;
            }
        }
        return true;
    }
}
