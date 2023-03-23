package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement.RequirementType;

public class VaxCheckerTest {
    private static final Age MIN_AGE = new Age(5);
    private static final Age MAX_AGE = new Age(35);

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

    private static final VaxType TYPE_NONE = new VaxType(new GroupName("TYPE_1"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of());
    private static final VaxType TYPE_ONE_A_1 = new VaxType(new GroupName("TYPE_1_A_2"),
            GRP_ONE_A_1,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of(new Requirement(RequirementType.NONE, GRP_ONE_A_1)));
    private static final VaxType TYPE_ONE_A_2 = new VaxType(new GroupName("TYPE_1_A_2"),
            GRP_ONE_A_2,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of());
    private static final VaxType TYPE_ONE_B = new VaxType(new GroupName("TYPE_1_B"),
            GRP_ONE_B,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of());
    private static final VaxType TYPE_TWO_A = new VaxType(new GroupName("TYPE_TWO_A"),
            GRP_TWO_A,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of());

    private static final VaxType TYPE_ONE_A_REQ = new VaxType(new GroupName("TYPE_ONE_A_REQ"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of(new Requirement(RequirementType.ALL, GRP_ONE_A_1)));
    private static final VaxType TYPE_TWO_A_REQ = new VaxType(new GroupName("TYPE_ONE_A_REQ"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of(new Requirement(RequirementType.ALL, GRP_TWO_A)));
    private static final VaxType TYPE_TWO_A_SUB = new VaxType(new GroupName("TYPE_TWO_A_SUB"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            new HashSet<>(),
            List.of(new Requirement(RequirementType.ANY, GRP_TWO_A)));
    private static final VaxType TYPE_COMBI_REQ = new VaxType(new GroupName("TYPE_TWO_A_SUB"),
            GRP_NONE,
            MIN_AGE,
            MAX_AGE,
            GRP_TWO_A,
            List.of(new Requirement(RequirementType.ALL, GRP_TWO_A),
                    new Requirement(RequirementType.ANY, GRP_ONE_B)));

    private static final List<VaxType> RECORDS_NONE_1 = List.of();
    private static final List<VaxType> RECORDS_ONE_A_2 = List.of(
            TYPE_ONE_A_1);
    private static final List<VaxType> RECORDS_ONE_B_2 = List.of(
            TYPE_ONE_B);
    private static final List<VaxType> RECORDS_SEP_A = List.of(
            TYPE_ONE_A_2,
            TYPE_ONE_A_1);
    private static final List<VaxType> RECORDS_TWO_A = List.of(
            TYPE_TWO_A);
    private static final List<VaxType> RECORDS_COMBI = List.of(
            TYPE_ONE_B,
            TYPE_TWO_A);


    @Test
    public void check_age() {
        // Min age
        assertTrue(VaxChecker.check(
                TYPE_NONE,
                MIN_AGE,
                GRP_NONE,
                RECORDS_NONE_1),
                "Min age");
        // Max age
        assertTrue(VaxChecker.check(
                TYPE_NONE,
                MAX_AGE,
                GRP_NONE,
                RECORDS_NONE_1),
                "Max age");
        // Min age - 1
        assertFalse(VaxChecker.check(
                TYPE_NONE,
                new Age(MIN_AGE.getValue() - 1),
                GRP_NONE,
                RECORDS_NONE_1),
                "Min age - 1");
        // Max age + 1
        assertFalse(VaxChecker.check(
                TYPE_NONE,
                new Age(MAX_AGE.getValue() + 1),
                GRP_NONE,
                RECORDS_NONE_1),
                "Max age - 1");
    }


    @Test
    public void check_historyNoReq() {
        // Vax no req | Rec 1 group
        assertTrue(VaxChecker.check(
            TYPE_NONE,
            MIN_AGE,
            GRP_NONE,
            RECORDS_ONE_A_2));
        // Vax one negative req | Rec 1 group | Matching
        assertFalse(VaxChecker.check(
            TYPE_ONE_A_1,
            MIN_AGE,
            GRP_NONE,
            RECORDS_ONE_A_2));
    }


    @Test
    public void check_historyOneReq() {
        // Vax one non-sub req | Rec 1 group | Matching
        assertTrue(VaxChecker.check(
                TYPE_ONE_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_A_2));
        // Vax one non-sub req | Rec 1 group | Not matching
        assertFalse(VaxChecker.check(
                TYPE_ONE_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_B_2));
    }


    @Test
    public void check_historyTwoNonSubReq() {
        // Vax two non-sub req | Rec 1 group | Some matching
        assertFalse(VaxChecker.check(
                TYPE_TWO_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_A_2));
        // Vax two non-sub req | Rec 2 separate group | Matching
        assertFalse(VaxChecker.check(
                TYPE_TWO_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_SEP_A));
        // Vax two non-sub req | Rec 2 groups 1 set | Matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_TWO_A));
    }


    @Test
    public void check_historyTwoSub() {
        // Vax two sub | Rec 1 group | Some matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_A_2));
        // Vax two sub | Rec 2 separate group | Matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_SEP_A));
        // Vax two sub | Rec 2 groups 1 set | Matching
        assertTrue(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_TWO_A));
        // Vax two sub | Rec 1 group | Not matching
        assertFalse(VaxChecker.check(
                TYPE_TWO_A_SUB,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_B_2));
    }


    @Test
    public void check_historyCombiReq() {
        // Vax combi | Rec 1 group | Some matching
        assertFalse(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_ONE_B_2));
        // Vax combi | Rec 2 group sets | Matching
        assertTrue(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_NONE,
                RECORDS_COMBI));
    }


    @Test
    public void check_allergy() {
        // Vax two | Allergy one | Some matching
        assertFalse(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_ONE_A_2,
                RECORDS_COMBI));
        // Vax two | Allergy one | No matching
        assertTrue(VaxChecker.check(
                TYPE_COMBI_REQ,
                MIN_AGE,
                GRP_ONE_B,
                RECORDS_COMBI));
    }
}
