package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement.RequirementType;
import seedu.vms.testutil.TestUtil;

public class RequirementTest {
    private static final HashSet<GroupName> SET_1_A = new HashSet<>(List.of(
            new GroupName("unchi1")));
    private static final HashSet<GroupName> SET_1_B = new HashSet<>(List.of(
            new GroupName("banana1")));

    private static final HashSet<GroupName> SET_3_A = new HashSet<>(List.of(
            new GroupName("unchi1"),
            new GroupName("unchi2"),
            new GroupName("unchi3")));
    private static final HashSet<GroupName> SET_3_B = new HashSet<>(List.of(
            new GroupName("banana1"),
            new GroupName("banana2"),
            new GroupName("banana3")));
    private static final HashSet<GroupName> SET_3_C = new HashSet<>(List.of(
            new GroupName("unchi1"),
            new GroupName("unchi2"),
            new GroupName("banana1")));
    private static final HashSet<GroupName> SET_4_A = new HashSet<>(List.of(
            new GroupName("unchi1"),
            new GroupName("unchi2"),
            new GroupName("unchi3"),
            new GroupName("unchi4")));


    @Test
    public void construction_nullReqType_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> new Requirement(null, SET_1_A));
    }


    @Test
    public void construction_invalidReqSet_exceptionThrown() {
        // empty requirement set
        assertThrows(IllegalArgumentException.class,
                () -> new Requirement(RequirementType.ALL, new HashSet<>()));

        // null requirement set
        assertThrows(NullPointerException.class,
                () -> new Requirement(RequirementType.ALL, null));

        // requirement set over limit
        assertThrows(IllegalArgumentException.class,
                () -> new Requirement(
                        RequirementType.ALL,
                        TestUtil.generateGroupSet(Requirement.LIMIT + 1)));
    }


    @Test
    void construction_validSet_requirementConstructed() {
        // check if any exception is thrown
        new Requirement(RequirementType.ALL, TestUtil.generateGroupSet(Requirement.LIMIT - 1));
        new Requirement(RequirementType.ALL, TestUtil.generateGroupSet(Requirement.LIMIT));
    }


    @Test
    public void check_all() {
        // Sample 0 | Req multiple | None matching
        assertFalse(new Requirement(RequirementType.ALL, SET_1_A)
                .check(new HashSet<>()),
                "Sample 0 | Req multiple | None matching");

        // Sample 1 | Req 1 | All matching
        assertTrue(new Requirement(RequirementType.ALL, SET_1_A)
                .check(SET_1_A),
                "Sample 1 | Req 1 | All matching");
        // Sample 1 | Req 1 | None matching
        assertFalse(new Requirement(RequirementType.ALL, SET_1_A)
                .check(SET_1_B),
                "Sample 1 | Req 1 | None matching");

        // Sample 1 | Req Multiple | S < R
        assertFalse(new Requirement(RequirementType.ALL, SET_3_A)
                .check(SET_1_A),
                "Sample 1 | Req Multiple | S < R");
        // Sample 1 | Req Multiple | None matching
        assertFalse(new Requirement(RequirementType.ALL, SET_3_B)
                .check(SET_1_A),
                "Sample 1 | Req Multiple | None matching");

        // Sample Multiple | Req 1 | S > R
        assertTrue(new Requirement(RequirementType.ALL, SET_1_A)
                .check(SET_3_A),
                "Sample Multiple | Req 1 | S > R");
        // Sample Multiple | Req 1 | None matching
        assertFalse(new Requirement(RequirementType.ALL, SET_1_A)
                .check(SET_3_B),
                "Sample 1 | Req 1 | None matching");

        // Sample multiple | Req multiple | All matching
        assertTrue(new Requirement(RequirementType.ALL, SET_3_A)
                .check(SET_3_A),
                "Sample multiple | Req multiple | All matching");
        // Sample multiple | Req multiple | Some matching
        assertFalse(new Requirement(RequirementType.ALL, SET_3_A)
                .check(SET_3_C),
                "Sample multiple | Req multiple | Some matching");
        // Sample multiple | Req multiple | None matching
        assertFalse(new Requirement(RequirementType.ALL, SET_3_A)
                .check(SET_3_B),
                "Sample multiple | Req multiple | None matching");
        // Sample Multiple | Req Multiple | S < R
        assertFalse(new Requirement(RequirementType.ALL, SET_4_A)
                .check(SET_3_A),
                "Sample Multiple | Req Multiple | S < R");
        // Sample Multiple | Req Multiple | S > R
        assertTrue(new Requirement(RequirementType.ALL, SET_3_A)
                .check(SET_4_A),
                "Sample Multiple | Req Multiple | S < R");
    }


    @Test
    public void check_any() {
        // Sample 0 | Req multiple | None matching
        assertFalse(new Requirement(RequirementType.ANY, SET_1_A)
                .check(new HashSet<>()),
                "Sample 0 | Req multiple | None matching");

        // Sample 1 | Req 1 | All matching
        assertTrue(new Requirement(RequirementType.ANY, SET_1_A)
                .check(SET_1_A),
                "Sample 1 | Req 1 | All matching");
        // Sample 1 | Req 1 | None matching
        assertFalse(new Requirement(RequirementType.ANY, SET_1_A)
                .check(SET_1_B),
                "Sample 1 | Req 1 | None matching");

        // Sample 1 | Req Multiple | S < R
        assertTrue(new Requirement(RequirementType.ANY, SET_3_A)
                .check(SET_1_A),
                "Sample 1 | Req Multiple | S < R");
        // Sample 1 | Req Multiple | None matching
        assertFalse(new Requirement(RequirementType.ANY, SET_3_B)
                .check(SET_1_A),
                "Sample 1 | Req Multiple | None matching");

        // Sample Multiple | Req 1 | S > R
        assertTrue(new Requirement(RequirementType.ANY, SET_1_A)
                .check(SET_3_A),
                "Sample Multiple | Req 1 | S > R");
        // Sample Multiple | Req 1 | None matching
        assertFalse(new Requirement(RequirementType.ANY, SET_1_A)
                .check(SET_3_B),
                "Sample 1 | Req 1 | None matching");

        // Sample multiple | Req multiple | All matching
        assertTrue(new Requirement(RequirementType.ANY, SET_3_A)
                .check(SET_3_A),
                "Sample multiple | Req multiple | All matching");
        // Sample multiple | Req multiple | Some matching
        assertTrue(new Requirement(RequirementType.ANY, SET_3_A)
                .check(SET_3_C),
                "Sample multiple | Req multiple | Some matching");
        // Sample multiple | Req multiple | None matching
        assertFalse(new Requirement(RequirementType.ANY, SET_3_A)
                .check(SET_3_B),
                "Sample multiple | Req multiple | None matching");
        // Sample Multiple | Req Multiple | S < R
        assertTrue(new Requirement(RequirementType.ANY, SET_4_A)
                .check(SET_3_A),
                "Sample Multiple | Req Multiple | S < R");
        // Sample Multiple | Req Multiple | S > R
        assertTrue(new Requirement(RequirementType.ANY, SET_3_A)
                .check(SET_4_A),
                "Sample Multiple | Req Multiple | S < R");
    }


    @Test
    public void check_none() {
        // Sample 0 | Req multiple | None matching
        assertTrue(new Requirement(RequirementType.NONE, SET_1_A)
                .check(new HashSet<>()),
                "Sample 0 | Req multiple | None matching");

        // Sample 1 | Req 1 | All matching
        assertFalse(new Requirement(RequirementType.NONE, SET_1_A)
                .check(SET_1_A),
                "Sample 1 | Req 1 | All matching");
        // Sample 1 | Req 1 | None matching
        assertTrue(new Requirement(RequirementType.NONE, SET_1_A)
                .check(SET_1_B),
                "Sample 1 | Req 1 | None matching");

        // Sample 1 | Req Multiple | S < R
        assertFalse(new Requirement(RequirementType.NONE, SET_3_A)
                .check(SET_1_A),
                "Sample 1 | Req Multiple | S < R");
        // Sample 1 | Req Multiple | None matching
        assertTrue(new Requirement(RequirementType.NONE, SET_3_B)
                .check(SET_1_A),
                "Sample 1 | Req Multiple | None matching");

        // Sample Multiple | Req 1 | S > R
        assertFalse(new Requirement(RequirementType.NONE, SET_1_A)
                .check(SET_3_A),
                "Sample Multiple | Req 1 | S > R");
        // Sample Multiple | Req 1 | None matching
        assertTrue(new Requirement(RequirementType.NONE, SET_1_A)
                .check(SET_3_B),
                "Sample 1 | Req 1 | None matching");

        // Sample multiple | Req multiple | All matching
        assertFalse(new Requirement(RequirementType.NONE, SET_3_A)
                .check(SET_3_A),
                "Sample multiple | Req multiple | All matching");
        // Sample multiple | Req multiple | Some matching
        assertFalse(new Requirement(RequirementType.NONE, SET_3_A)
                .check(SET_3_C),
                "Sample multiple | Req multiple | Some matching");
        // Sample multiple | Req multiple | None matching
        assertTrue(new Requirement(RequirementType.NONE, SET_3_A)
                .check(SET_3_B),
                "Sample multiple | Req multiple | None matching");
        // Sample Multiple | Req Multiple | S < R
        assertFalse(new Requirement(RequirementType.NONE, SET_4_A)
                .check(SET_3_A),
                "Sample Multiple | Req Multiple | S < R");
        // Sample Multiple | Req Multiple | S > R
        assertFalse(new Requirement(RequirementType.NONE, SET_3_A)
                .check(SET_4_A),
                "Sample Multiple | Req Multiple | S < R");
    }


    @Test
    public void equalsTest() {
        Requirement req1 = new Requirement(RequirementType.ALL, SET_1_A);
        Requirement req1a = new Requirement(RequirementType.ALL, SET_1_A);
        Requirement req2 = new Requirement(RequirementType.ANY, SET_1_A);
        Requirement req3 = new Requirement(RequirementType.ALL, SET_1_B);
        Integer unrelated = Integer.valueOf(0);

        assertTrue(req1.equals(req1), "Same instance");
        assertTrue(req1.equals(req1a), "Same but different instance");
        assertFalse(req1.equals(req2), "Different type");
        assertFalse(req1.equals(req3), "Different sets");
        assertFalse(req1.equals(unrelated), "Unrelated");
    }


    @Test
    public void copy_single() {
        Requirement req = new Requirement(RequirementType.ALL, SET_1_A);
        Requirement copy = req.copy();

        assertEquals(req, copy);
    }


    @Test
    public void copy_list() {
        List<Requirement> refReqs = List.of(new Requirement(RequirementType.ALL, SET_1_A));
        ArrayList<Requirement> reqs = new ArrayList<>(refReqs);
        List<Requirement> copyReqs = Requirement.copy(reqs);

        assertEquals(refReqs, copyReqs);

        // immutability
        reqs.remove(0);
        assertEquals(refReqs, copyReqs);
    }


    @Test
    public void hashCodeTest() {
        Requirement req1 = new Requirement(RequirementType.ALL, SET_1_A);
        Requirement req2 = new Requirement(RequirementType.ALL, SET_1_A);

        // assume equals to work from above
        HashSet<Requirement> reqs = new HashSet<>();
        reqs.add(req1);
        assertTrue(reqs.contains(req2));
    }


    @Test
    public void getReqSetTest() {
        Requirement req = new Requirement(RequirementType.ALL, SET_1_A);

        assertEquals(SET_1_A, req.getReqSet());

        // immutability
        req.getReqSet().addAll(SET_1_B);
        assertEquals(SET_1_A, req.getReqSet());
    }
}
