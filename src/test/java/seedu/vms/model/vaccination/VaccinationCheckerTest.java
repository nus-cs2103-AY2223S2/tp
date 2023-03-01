package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;


public class VaccinationCheckerTest {
    private static final int MIN_AGE = 5;
    private static final int MAX_AGE = 60;
    private static final int MIN_SPACING = 7;

    private static final VaxType TYPE_1_A = VaxType.Builder.of("TYPE_1_A")
            .setGroups("TYPE_1")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setRequirements()
            .build();
    private static final VaxType TYPE_1_B = VaxType.Builder.of("TYPE_1_B")
            .setGroups("TYPE_1", "B")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setRequirements(new VaxRequirement(true, new HashSet<>(List.of("TYPE_1"))))
            .build();
    private static final VaxType TYPE_2_A = VaxType.Builder.of("TYPE_2_A")
            .setGroups("TYPE_2", "A")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setRequirements(
                    new VaxRequirement(false, new HashSet<>(List.of("TYPE_1"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_2"))))
            .build();
    private static final VaxType TYPE_2_B = VaxType.Builder.of("TYPE_2_B")
            .setGroups("TYPE_2", "B")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setRequirements(
                    new VaxRequirement(false, new HashSet<>(List.of("B", "TYPE_1"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_2"))))
            .build();
    private static final VaxType TYPE_3 = VaxType.Builder.of("TYPE_3")
            .setGroups("TYPE_3")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setRequirements(
                    new VaxRequirement(false, new HashSet<>(List.of("TYPE_2"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_3"))))
            .build();

    private static final LocalDateTime TIME_1_VALID = LocalDateTime.of(2023, 3, 5, 4, 55);
    private static final LocalDateTime TIME_2_VALID = TIME_1_VALID.plus(MIN_SPACING, ChronoUnit.DAYS);
    private static final LocalDateTime TIME_3_VALID = TIME_2_VALID.plus(MIN_SPACING + 1, ChronoUnit.DAYS);

    private static final LocalDateTime TIME_2_INVALID = TIME_2_VALID.minus(1, ChronoUnit.SECONDS);

    private static final List<VaxRecord> RECORD_1 = List.of();
    private static final List<VaxRecord> RECORD_2 = List.of(
            new VaxRecord(TYPE_1_A, TIME_1_VALID));
    private static final List<VaxRecord> RECORD_3 = List.of(
            new VaxRecord(TYPE_1_A, TIME_1_VALID),
            new VaxRecord(TYPE_2_A, TIME_2_VALID));


    /*
     * ========================================================================
     * True checks
     * ========================================================================
     */


    @Test
    public void check_satisfiedAllEmpty_true() {
        assertTrue(VaxChecker.check(
                TYPE_1_A,
                MIN_AGE,
                RECORD_1,
                TIME_1_VALID));
    }


    @Test
    public void check_satisfiedReqEmpty_true() {
        assertTrue(VaxChecker.check(
                TYPE_1_A,
                MAX_AGE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_inclusionPresent_true() {
        assertTrue(VaxChecker.check(
                TYPE_2_A,
                MIN_AGE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_substitutePresent_true() {
        assertTrue(VaxChecker.check(
                TYPE_2_B,
                MIN_AGE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_multipleRecordsPresent_true() {
        assertTrue(VaxChecker.check(
                TYPE_3,
                MIN_AGE,
                RECORD_3,
                TIME_3_VALID));
    }


    /*
     * ========================================================================
     * False checks
     * ========================================================================
     */


    @Test
    public void check_exclusionPresent_false() {
        assertFalse(VaxChecker.check(
                TYPE_1_B,
                MIN_AGE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_underAge_false() {
        assertFalse(VaxChecker.check(
                TYPE_1_A,
                MIN_AGE - 1,
                RECORD_1,
                TIME_1_VALID));
    }


    @Test
    public void check_overAge_false() {
        assertFalse(VaxChecker.check(
                TYPE_1_A,
                MAX_AGE + 1,
                RECORD_1,
                TIME_1_VALID));
    }


    @Test
    public void check_tooEarly_false() {
        assertFalse(VaxChecker.check(
                TYPE_2_A,
                MIN_AGE,
                RECORD_2,
                TIME_2_INVALID));
    }
}
