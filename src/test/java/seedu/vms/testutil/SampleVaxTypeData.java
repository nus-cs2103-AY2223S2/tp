package seedu.vms.testutil;

import java.util.HashSet;
import java.util.List;

import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.Requirement.RequirementType;

/** Utility class to store sample vaccination type data for tests. */
public class SampleVaxTypeData {
    public static final String NAME = "Dose 1 (Pfizer)";
    public static final HashSet<String> GROUPS =
            new HashSet<>(List.of("DOSE 1", "Pfizer", "Vaccination"));
    public static final int MIN_AGE = 5;
    public static final int MAX_AGE = Integer.MAX_VALUE;
    public static final int MIN_SPACING = 56;
    public static final List<Requirement> HISTORY_REQS = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of("DOSE 1"))));
    public static final List<Requirement> ALLERGY_REQS = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    "((4-Hydroxybutyl)azanediyl)bis(hexane-6,1-diyl)bis(2-hexyldecanoate)",
                    "2-[(polyethylene glycol)-2000]-N,N-ditetradecylacetamide",
                    "1,2-Distearoyl-sn-glycero-3-phosphocholine",
                    "Cholesterol",
                    "Sucrose",
                    "Phosphate",
                    "Tromethamine",
                    "Tris(hydroxymethyl)aminoethane hydrochloride"))));
}
