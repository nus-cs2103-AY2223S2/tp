package seedu.vms.storage.vaccination;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxTestingUtil;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeStorage;
import seedu.vms.model.vaccination.Requirement.RequirementType;


public class VaxTypeLoaderTest {
    // Expected parameter values of dose 1 pfizer
    private static final String NAME = "Dose 1 (Pfizer)";
    private static final HashSet<String> GROUPS =
            new HashSet<>(List.of("DOSE 1", "Pfizer", "Vaccination"));
    private static final int MIN_AGE = 5;
    private static final int MAX_AGE = Integer.MAX_VALUE;
    private static final int MIN_SPACING = 56;
    private static final List<Requirement> HISTORY_REQS = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of("DOSE 1"))));
    private static final List<Requirement> ALLERGY_REQS = List.of(
            new Requirement(RequirementType.NONE, new HashSet<>(List.of(
                    "((4-Hydroxybutyl)azanediyl)bis(hexane-6,1-diyl)bis(2-hexyldecanoate)",
                    "2-[(polyethylene glycol)-2000]-N,N-ditetradecylacetamide",
                    "1,2-Distearoyl-sn-glycero-3-phosphocholine",
                    "Cholesterol",
                    "Sucrose",
                    "Phosphate",
                    "Tromethamine",
                    "Tris(hydroxymethyl)aminoethane hydrochloride"))));

    private static VaxTypeStorage storage;


    @BeforeAll
    public static void load() throws Exception {
        storage = VaxTypeLoader.load();
    }


    @Test
    public void loadTest() throws Exception {
        VaxType vaxType = storage.get(NAME).get();
        VaxTestingUtil.assertVaxType(vaxType,
                NAME, GROUPS,
                MIN_AGE, MAX_AGE, MIN_SPACING,
                HISTORY_REQS, ALLERGY_REQS);
    }
}
