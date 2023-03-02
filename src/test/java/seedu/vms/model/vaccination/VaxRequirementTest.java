package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class VaxRequirementTest {
    @Test
    public void construction_emptySubSet_throwsException() {
        try {
            new VaxRequirement(false, new HashSet<>());
        } catch (RuntimeException rtEx) {
            // expected exception
            return;
        }
        fail("Exception not thrown");
    }


    @Test
    public void constructionTest() {
        new VaxRequirement(false, new HashSet<>(List.of("a")));
    }


    @Test
    public void equalsTest() {
        Object vaxReq1 = new VaxRequirement(false, new HashSet<>(List.of("a")));
        Object vaxReq1c = new VaxRequirement(false, new HashSet<>(List.of("a")));
        Object vaxReq2 = new VaxRequirement(true, new HashSet<>(List.of("a")));
        Object vaxReq3 = new VaxRequirement(false, new HashSet<>(List.of("b")));
        Object random = Integer.valueOf(0);

        assertTrue(vaxReq1.equals(vaxReq1));
        assertTrue(vaxReq1.equals(vaxReq1c));

        assertFalse(vaxReq1.equals(vaxReq2));
        assertFalse(vaxReq1.equals(vaxReq3));
        assertFalse(vaxReq3.equals(random));
    }
}
