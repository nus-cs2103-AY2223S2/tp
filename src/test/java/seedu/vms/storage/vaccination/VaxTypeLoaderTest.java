package seedu.vms.storage.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.model.vaccination.VaxTestingUtil.assertVaxType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.vms.model.vaccination.VaxChecker;
import seedu.vms.model.vaccination.VaxRecord;
import seedu.vms.model.vaccination.VaxRequirement;
import seedu.vms.model.vaccination.VaxType;


public class VaxTypeLoaderTest {
    private static final String TYPE_1_NAME = "Dose 1 (Pfizer)";
    private static final HashSet<String> TYPE_1_GROUPS = new HashSet<>(List.of("DOSE 1", "Pfizer", "Vaccination"));
    private static final int TYPE_1_MIN_AGE = 5;
    private static final int TYPE_1_MAX_AGE = Integer.MAX_VALUE;
    private static final int TYPE_1_MIN_SPACING = 56;
    private static final List<VaxRequirement> TYPE_1_REQUIREMENTS = List.of(
            new VaxRequirement(true, new HashSet<>(List.of("DOSE 1"))));
    private static final List<VaxRequirement> TYPE_1_ALLERGY_REQS = List.of(
            new VaxRequirement(true, new HashSet<>(List.of(
                    "((4-Hydroxybutyl)azanediyl)bis(hexane-6,1-diyl)bis(2-hexyldecanoate)",
                    "2-[(polyethylene glycol)-2000]-N,N-ditetradecylacetamide",
                    "1,2-Distearoyl-sn-glycero-3-phosphocholine",
                    "Cholesterol",
                    "Sucrose",
                    "Phosphate",
                    "Tromethamine",
                    "Tris(hydroxymethyl)aminoethane hydrochloride"))));

    private static final String TYPE_2_NAME = "Dose 2 (Pfizer)";
    private static final String TYPE_3_NAME = "Dose 1 (Moderna)";


    @BeforeAll
    public static void load() throws Exception {
        VaxTypeLoader.load();
    }


    @Test
    public void loadTest() throws Exception {
        assertVaxType(VaxType.get(TYPE_1_NAME),
                TYPE_1_NAME,
                TYPE_1_GROUPS,
                TYPE_1_MIN_AGE,
                TYPE_1_MAX_AGE,
                TYPE_1_MIN_SPACING,
                TYPE_1_REQUIREMENTS,
                TYPE_1_ALLERGY_REQS);
    }


    @Test
    public void logicalTest() {
        // first 2 doses must be the same brand
        VaxType doseP1 = VaxType.get(TYPE_1_NAME);
        VaxType doseP2 = VaxType.get(TYPE_2_NAME);
        VaxType doseM1 = VaxType.get(TYPE_3_NAME);

        LocalDateTime dose1Time = LocalDateTime.now();
        LocalDateTime dose2Time = dose1Time.plus(TYPE_1_MIN_SPACING, ChronoUnit.DAYS);

        List<VaxRecord> validRecs = List.of(new VaxRecord(doseP1, dose1Time));
        List<VaxRecord> invalidRecs = List.of(new VaxRecord(doseM1, dose1Time));

        assertTrue(VaxChecker.check(
                doseP2,
                TYPE_1_MIN_AGE,
                new HashSet<>(),
                validRecs,
                dose2Time));
        assertFalse(VaxChecker.check(
            doseP2,
            TYPE_1_MIN_AGE,
            new HashSet<>(),
            invalidRecs,
            dose2Time));
    }
}
