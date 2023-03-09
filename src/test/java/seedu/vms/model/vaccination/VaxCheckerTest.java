package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement.RequirementType;

public class VaxCheckerTest {
    private static final int MIN_AGE = 5;
    private static final int MAX_AGE = 35;
    private static final int MIN_SPACING = 445;

    private static final HashSet<GroupName> GRP_NONE = new HashSet<>(List.of());
    private static final HashSet<GroupName> GRP_ONE_A_1 = new HashSet<>(List.of(
            new GroupName("UNCHI1")));
    private static final HashSet<GroupName> GRP_ONE_A_2 = new HashSet<>(List.of(
            new GroupName("UNCHI2")));
    private static final HashSet<GroupName> GRP_ONE_B = new HashSet<>(List.of(
            new GroupName("BANANA1")));
    private static final HashSet<GroupName> GRP_TWO_A = new HashSet<>(List.of(
            new GroupName("UNCHI1"),
            new GroupName("UNCHI2")));

    private static final LocalDateTime TIME_1_VALID = LocalDateTime.of(2023, 3, 5, 4, 55);
    private static final LocalDateTime TIME_2_VALID = TIME_1_VALID.plusDays(MIN_SPACING);
    private static final LocalDateTime TIME_3_VALID = TIME_2_VALID.plusDays(MIN_SPACING);

    private static final VaxType TYPE_NONE = new VaxType(new GroupName("TYPE_1"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of());
    private static final VaxType TYPE_ONE_A_1 = new VaxType(new GroupName("TYPE_1_A_2"),
            GRP_ONE_A_1,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of(new Requirement(RequirementType.NONE, GRP_ONE_A_1)));
    private static final VaxType TYPE_ONE_A_2 = new VaxType(new GroupName("TYPE_1_A_2"),
            GRP_ONE_A_2,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of());
    private static final VaxType TYPE_ONE_B = new VaxType(new GroupName("TYPE_1_B"),
            GRP_ONE_B,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of());
    private static final VaxType TYPE_TWO_A = new VaxType(new GroupName("TYPE_TWO_A"),
            GRP_TWO_A,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of());

    private static final VaxType TYPE_ONE_A_REQ = new VaxType(new GroupName("TYPE_ONE_A_REQ"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of(new Requirement(RequirementType.ALL, GRP_ONE_A_1)));
    private static final VaxType TYPE_TWO_A_REQ = new VaxType(new GroupName("TYPE_ONE_A_REQ"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of(new Requirement(RequirementType.ALL, GRP_TWO_A)));
    private static final VaxType TYPE_TWO_A_SUB = new VaxType(new GroupName("TYPE_TWO_A_SUB"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(),
            List.of(new Requirement(RequirementType.ANY, GRP_TWO_A)));
    private static final VaxType TYPE_COMBI_REQ = new VaxType(new GroupName("TYPE_TWO_A_SUB"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            MIN_SPACING,
            List.of(new Requirement(RequirementType.NONE, GRP_TWO_A)),
            List.of(new Requirement(RequirementType.ALL, GRP_TWO_A),
                    new Requirement(RequirementType.ANY, GRP_ONE_B)));

    private static final List<VaxRecord> RECORDS_NONE_1 = List.of();
    private static final List<VaxRecord> RECORDS_NONE_2 = List.of(
            new VaxRecord(TYPE_NONE, TIME_1_VALID));
    private static final List<VaxRecord> RECORDS_ONE_A_2 = List.of(
            new VaxRecord(TYPE_ONE_A_1, TIME_1_VALID));
    private static final List<VaxRecord> RECORDS_ONE_B_2 = List.of(
            new VaxRecord(TYPE_ONE_B, TIME_1_VALID));
    private static final List<VaxRecord> RECORDS_SEP_A = List.of(
            new VaxRecord(TYPE_ONE_A_2, TIME_2_VALID),
            new VaxRecord(TYPE_ONE_A_1, TIME_1_VALID));
    private static final List<VaxRecord> RECORDS_TWO_A = List.of(
            new VaxRecord(TYPE_TWO_A, TIME_1_VALID));
    private static final List<VaxRecord> RECORDS_COMBI = List.of(
            new VaxRecord(TYPE_ONE_B, TIME_1_VALID),
            new VaxRecord(TYPE_TWO_A, TIME_2_VALID));


    @Test
    public void check_age() {
        // Min age
        assertTrue(VaxChecker.check(
                TYPE_NONE,
                MIN_AGE,
                GRP_NONE,
                RECORDS_NONE_1,
                TIME_1_VALID),
                "Min age");
        // Max age
        assertTrue(VaxChecker.check(
                TYPE_NONE,
                MAX_AGE,
                GRP_NONE,
                RECORDS_NONE_1,
                TIME_1_VALID),
                "Max age");
        // Min age - 1
        assertFalse(VaxChecker.check(
                TYPE_NONE,
                MIN_AGE - 1,
                GRP_NONE,
                RECORDS_NONE_1,
                TIME_1_VALID),
                "Min age - 1");
        // Max age + 1
        assertFalse(VaxChecker.check(
                TYPE_NONE,
                MAX_AGE + 1,
                GRP_NONE,
                RECORDS_NONE_1,
                TIME_1_VALID),
                "Max age - 1");
    }


    @Test
    public void check_spacing() {
        // Min spacing
        assertTrue(VaxChecker.check(
                TYPE_NONE,
                MIN_AGE,
                GRP_NONE,
                RECORDS_NONE_2,
                TIME_2_VALID),
                "Min spacing");
        // Min spacing + 1
        assertTrue(VaxChecker.check(
                TYPE_NONE,
                MIN_AGE,
                GRP_NONE,
                RECORDS_NONE_2,
                TIME_2_VALID.plusSeconds(1)),
                "Min spacing + 1");
        // Min spacing - 1
        assertFalse(VaxChecker.check(
                TYPE_NONE,
                MIN_AGE,
                GRP_NONE,
                RECORDS_NONE_2,
                TIME_2_VALID.minusSeconds(1)),
                "Min spacing - 1");
    }


    @Test
    public void check_historyNoReq() {
        // Vax no req | Rec 1 group
        assertTrue(VaxChecker.check(
            TYPE_NONE,
            MIN_AGE,
            GRP_NONE,
            RECORDS_ONE_A_2,
            TIME_2_VALID));
        // Vax one negative req | Rec 1 group | Matching
        assertFalse(VaxChecker.check(
            TYPE_ONE_A_1,
            MIN_AGE,
            GRP_NONE,
            RECORDS_ONE_A_2,
            TIME_2_VALID));
    }


    @Test
    public void check_historyOneReq() {
        // Vax one non-sub req | Rec 1 group | Matching
        assertTrue(VaxChecker.check(
                TYPE_ONE_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_A_2,
                TIME_2_VALID));
        // Vax one non-sub req | Rec 1 group | Not matching
        assertFalse(VaxChecker.check(
                TYPE_ONE_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_B_2,
                TIME_2_VALID));
    }


    @Test
    public void check_historyTwoNonSubReq() {
        // Vax two non-sub req | Rec 1 group | Some matching
        assertFalse(VaxChecker.check(
                TYPE_TWO_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_A_2,
                TIME_2_VALID));
        // Vax two non-sub req | Rec 2 separate group | Matching
        assertFalse(VaxChecker.check(
                TYPE_TWO_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_SEP_A,
                TIME_3_VALID));
        // Vax two non-sub req | Rec 2 groups 1 set | Matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_TWO_A,
                TIME_3_VALID));
    }


    @Test
    public void check_historyTwoSub() {
        // Vax two sub | Rec 1 group | Some matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_A_2,
                TIME_2_VALID));
        // Vax two sub | Rec 2 separate group | Matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_SEP_A,
                TIME_3_VALID));
        // Vax two sub | Rec 2 groups 1 set | Matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_TWO_A,
                TIME_3_VALID));
        // Vax two sub | Rec 1 group | Not matching
        assertFalse(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_B_2,
                TIME_3_VALID));
    }


    @Test
    public void check_historyCombiReq() {
        // Vax combi | Rec 1 group | Some matching
        assertFalse(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_B_2,
                TIME_3_VALID));
        // Vax combi | Rec 2 group sets | Matching
        assertTrue(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_COMBI,
                TIME_3_VALID));
    }


    @Test
    public void check_allergy() {
        // Vax two | Allergy one | Some matching
        assertFalse(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_ONE_A_2,
                RECORDS_COMBI,
                TIME_3_VALID));
        // Vax two | Allergy one | No matching
        assertTrue(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_ONE_B,
                RECORDS_COMBI,
                TIME_3_VALID));
    }
}
