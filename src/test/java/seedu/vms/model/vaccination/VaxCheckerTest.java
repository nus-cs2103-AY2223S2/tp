package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;


public class VaxCheckerTest {
    private static final int MIN_AGE = 5;
    private static final int MAX_AGE = 60;
    private static final int MIN_SPACING = 7;

    private static final HashSet<String> ALLERGIES_NONE = new HashSet<>();
    private static final HashSet<String> ALLERGIES_ONE = new HashSet<>(List.of("BANANA1"));
    private static final HashSet<String> ALLERGIES_MULTIPLE = new HashSet<>(
            List.of("BANANA1", "BANANA2", "BANANA3"));
    private static final HashSet<String> ALLERGIES_ONE_NONE = new HashSet<>(List.of("UNCHI1"));
    private static final HashSet<String> ALLERGIES_MULTIPLE_NONE = new HashSet<>(
            List.of("UNCHI1", "UNCHI2", "UNCHI3"));
    private static final HashSet<String> ALLERGIES_MULTIPLE_SOME = new HashSet<>(
            List.of("UNCHI1", "UNCHI2", "BANANA1"));

    /** No allergies. */
    private static final VaxType TYPE_1_A = VaxType.Builder.of("TYPE_1_A")
            .setGroups("TYPE_1")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setHistoryReq()
            .build();
    /** One allergy. */
    private static final VaxType TYPE_1_B = VaxType.Builder.of("TYPE_1_B")
            .setGroups("TYPE_1", "B")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setHistoryReq(new VaxRequirement(true, new HashSet<>(List.of("TYPE_1"))))
            .setAllergyReqs(new VaxRequirement(true, ALLERGIES_ONE))
            .build();
    /** One allergy not matching. */
    private static final VaxType TYPE_2_A = VaxType.Builder.of("TYPE_2_A")
            .setGroups("TYPE_2", "A")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setHistoryReq(
                    new VaxRequirement(false, new HashSet<>(List.of("TYPE_1"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_2"))))
            .setAllergyReqs(new VaxRequirement(true, ALLERGIES_ONE_NONE))
            .build();
    /** Multiple allergies. */
    private static final VaxType TYPE_2_B = VaxType.Builder.of("TYPE_2_B")
            .setGroups("TYPE_2", "B")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setHistoryReq(
                    new VaxRequirement(false, new HashSet<>(List.of("B", "TYPE_1"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_2"))))
            .setAllergyReqs(new VaxRequirement(true, ALLERGIES_MULTIPLE))
            .build();
    /** Multiple allergies none matching. */
    private static final VaxType TYPE_3 = VaxType.Builder.of("TYPE_3")
            .setGroups("TYPE_3")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setHistoryReq(
                    new VaxRequirement(false, new HashSet<>(List.of("TYPE_2"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_3"))))
            .setAllergyReqs(new VaxRequirement(true, ALLERGIES_MULTIPLE_NONE))
            .build();
    /** Multiple allergies some matching. */
    private static final VaxType TYPE_3_A = VaxType.Builder.of("TYPE_3_A")
            .setGroups("TYPE_3", "A")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setHistoryReq(
                    new VaxRequirement(false, new HashSet<>(List.of("TYPE_2"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_3"))))
            .setAllergyReqs(new VaxRequirement(true, ALLERGIES_MULTIPLE_SOME))
            .build();
    private static final VaxType TYPE_4 = VaxType.Builder.of("TYPE_4")
            .setGroups("TYPE_4")
            .setMinAge(MIN_AGE)
            .setMaxAge(MAX_AGE)
            .setMinSpacing(MIN_SPACING)
            .setHistoryReq(
                    new VaxRequirement(false, new HashSet<>(List.of("TYPE_3"))),
                    new VaxRequirement(false, new HashSet<>(List.of("A"))),
                    new VaxRequirement(true, new HashSet<>(List.of("TYPE_4"))))
            .build();

    private static final LocalDateTime TIME_1_VALID = LocalDateTime.of(2023, 3, 5, 4, 55);
    private static final LocalDateTime TIME_2_VALID = TIME_1_VALID.plus(MIN_SPACING, ChronoUnit.DAYS);
    private static final LocalDateTime TIME_3_VALID = TIME_2_VALID.plus(MIN_SPACING + 1, ChronoUnit.DAYS);
    private static final LocalDateTime TIME_4_VALID = TIME_3_VALID.plus(MIN_SPACING, ChronoUnit.DAYS);

    private static final LocalDateTime TIME_2_INVALID = TIME_2_VALID.minus(1, ChronoUnit.SECONDS);

    private static final List<VaxRecord> RECORD_1 = List.of();
    private static final List<VaxRecord> RECORD_2 = List.of(
            new VaxRecord(TYPE_1_A, TIME_1_VALID));
    private static final List<VaxRecord> RECORD_3 = List.of(
            new VaxRecord(TYPE_1_A, TIME_1_VALID),
            new VaxRecord(TYPE_2_A, TIME_2_VALID));
    private static final List<VaxRecord> RECORD_4_VALID = List.of(
                new VaxRecord(TYPE_1_A, TIME_1_VALID),
                new VaxRecord(TYPE_2_A, TIME_2_VALID),
                new VaxRecord(TYPE_3_A, TIME_3_VALID));
    private static final List<VaxRecord> RECORD_4_INVALID = List.of(
                new VaxRecord(TYPE_1_A, TIME_1_VALID),
                new VaxRecord(TYPE_2_A, TIME_2_VALID),
                new VaxRecord(TYPE_3, TIME_3_VALID));


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
                ALLERGIES_NONE,
                RECORD_1,
                TIME_1_VALID));
    }


    @Test
    public void check_satisfiedReqEmpty_true() {
        assertTrue(VaxChecker.check(
                TYPE_1_A,
                MAX_AGE,
                ALLERGIES_MULTIPLE_NONE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_inclusionPresent_true() {
        assertTrue(VaxChecker.check(
                TYPE_2_A,
                MIN_AGE,
                ALLERGIES_MULTIPLE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_substitutePresent_true() {
        assertTrue(VaxChecker.check(
                TYPE_2_B,
                MIN_AGE,
                ALLERGIES_MULTIPLE_NONE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_multipleRecordsPresent_true() {
        assertTrue(VaxChecker.check(
                TYPE_3,
                MIN_AGE,
                ALLERGIES_NONE,
                RECORD_3,
                TIME_3_VALID));
    }


    @Test
    public void check_multipleNonSubReqPresent_true() {
        assertTrue(VaxChecker.check(
                TYPE_4,
                MIN_AGE,
                ALLERGIES_NONE,
                RECORD_4_VALID,
                TIME_4_VALID));
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
                ALLERGIES_NONE,
                RECORD_2,
                TIME_2_VALID));
    }


    @Test
    public void check_underAge_false() {
        assertFalse(VaxChecker.check(
                TYPE_1_A,
                MIN_AGE - 1,
                ALLERGIES_NONE,
                RECORD_1,
                TIME_1_VALID));
    }


    @Test
    public void check_overAge_false() {
        assertFalse(VaxChecker.check(
                TYPE_1_A,
                MAX_AGE + 1,
                ALLERGIES_NONE,
                RECORD_1,
                TIME_1_VALID));
    }


    @Test
    public void check_tooEarly_false() {
        assertFalse(VaxChecker.check(
                TYPE_2_A,
                MIN_AGE,
                ALLERGIES_NONE,
                RECORD_2,
                TIME_2_INVALID));
    }


    @Test
    public void check_nonSubReqAbsent_false() {
        assertFalse(VaxChecker.check(
                TYPE_4,
                MIN_AGE,
                ALLERGIES_NONE,
                RECORD_4_INVALID,
                TIME_4_VALID));
    }


    @Test
    public void check_matchingAllergies_false() {
        // PERSON 1 : VAX 1
        assertFalse(VaxChecker.check(
                TYPE_1_B,
                MIN_AGE,
                ALLERGIES_ONE,
                RECORD_1,
                TIME_1_VALID));
        // PERSON MULTIPLE : VAX 1
        assertFalse(VaxChecker.check(
                TYPE_1_B,
                MIN_AGE,
                ALLERGIES_MULTIPLE,
                RECORD_1,
                TIME_1_VALID));
        // PERSON 1 : VAX MULTIPLE
        assertFalse(VaxChecker.check(
                TYPE_2_B,
                MIN_AGE,
                ALLERGIES_ONE,
                RECORD_1,
                TIME_1_VALID));
        // PERSON SOME : VAX MULTIPLE
        assertFalse(VaxChecker.check(
                TYPE_2_B,
                MIN_AGE,
                ALLERGIES_MULTIPLE_SOME,
                RECORD_1,
                TIME_1_VALID));
        // PERSON MULTIPLE : VAX SOME
        assertFalse(VaxChecker.check(
                TYPE_3_A,
                MIN_AGE,
                ALLERGIES_MULTIPLE,
                RECORD_1,
                TIME_1_VALID));
    }
}
