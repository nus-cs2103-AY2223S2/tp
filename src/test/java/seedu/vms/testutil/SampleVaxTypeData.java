package seedu.vms.testutil;

import java.util.HashSet;
import java.util.List;

import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.Requirement.RequirementType;

/** Utility class to store sample vaccination type data for tests. */
public class SampleVaxTypeData {
    public static final String NAME_REAL = "Dose 1 (Pfizer)";
    public static final HashSet<String> GROUPS_REAL =
            new HashSet<>(List.of("DOSE 1", "Pfizer", "Vaccination"));
    public static final int MIN_AGE_REAL = 5;
    public static final int MAX_AGE_REAL = Integer.MAX_VALUE;
    public static final int MIN_SPACING_REAL = 56;
    public static final List<Requirement> HISTORY_REQS_REAL = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of("DOSE 1"))));
    public static final List<Requirement> ALLERGY_REQS_REAL = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    "ALC-0315",
                    "ALC-0159",
                    "DSPC",
                    "Cholesterol",
                    "Sucrose",
                    "Phosphate",
                    "Tromethamine",
                    "Tromethamine hydrochloride"))));

    public static final String NAME_1 = "UNCHI";
    public static final HashSet<String> GROUPS_1 =
            new HashSet<>(List.of("UNCHI"));
    public static final int MIN_AGE_1 = 35;
    public static final int MAX_AGE_1 = 45;
    public static final int MIN_SPACING_1 = 3545;
    public static final List<Requirement> HISTORY_REQS_1 = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of("UNCHI"))));
    public static final List<Requirement> ALLERGY_REQS_1 = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    "UNCHI"))));
}
