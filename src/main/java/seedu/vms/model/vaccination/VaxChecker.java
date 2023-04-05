package seedu.vms.model.vaccination;

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

        boolean isAllergiesSatisfied = checkAllergies(allergies, vaxType.getIngredients());
        boolean isHistorySatisfied = checkHistReqs(vaxType.getHistoryReqs(), takenTypes);

        return isWithinAge && isAllergiesSatisfied && isHistorySatisfied;
    }


    private static boolean checkAllergies(HashSet<GroupName> allergies, HashSet<GroupName> ingredients) {
        for (GroupName allergy : allergies) {
            if (ingredients.contains(allergy)) {
                return false;
            }
        }
        return true;
    }


    private static boolean checkHistReqs(List<Requirement> reqs, List<VaxType> records) {
        List<HistReqChecker> checkers = records.stream()
                .map(HistReqChecker::new)
                .collect(Collectors.toList());
        for (Requirement req : reqs) {
            if (!checkHistReq(req, checkers)) {
                return false;
            }
        }
        return true;
    }


    private static boolean checkHistReq(Requirement req, List<HistReqChecker> checkers) {
        for (HistReqChecker checker : checkers) {
            switch (checker.check(req)) {
            case SATISFIED:
                return true;
            case PASS:
                continue;
            case VIOLATED:
                return false;
            default:
                assert false : "Unexpected satisfaction state";
            }
        }
        return req.getReqType().equals(Requirement.RequirementType.NONE);
    }





    private static class HistReqChecker {
        private final VaxType vaxType;
        private final HashSet<Requirement> satisfiedReqs = new HashSet<>();


        HistReqChecker(VaxType vaxType) {
            this.vaxType = vaxType;
        }


        SatisfactionState check(Requirement req) {
            SatisfactionState state = SatisfactionState.PASS;
            if (satisfiedReqs.contains(req)) {
                return state;
            }

            if (req.check(vaxType.getGroups())) {
                if (!req.getReqType().equals(Requirement.RequirementType.NONE)) {
                    state = SatisfactionState.SATISFIED;
                    satisfiedReqs.add(req);
                }
            } else if (req.getReqType().equals(Requirement.RequirementType.NONE)) {
                state = SatisfactionState.VIOLATED;
            }

            return state;
        }
    }





    private static enum SatisfactionState {
        SATISFIED, PASS, VIOLATED;
    }
}
