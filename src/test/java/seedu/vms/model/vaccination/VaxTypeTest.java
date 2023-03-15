package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.testutil.SampleVaxTypeData;

public class VaxTypeTest {
    private static final Age INVALID_MIN_AGE = new Age(100);
    private static final Age INVALID_MAX_AGE = new Age(99);


    @Test
    public void constructor_invalidAge_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new VaxType(
                SampleVaxTypeData.NAME_1,
                SampleVaxTypeData.GROUPS_1,
                INVALID_MIN_AGE,
                INVALID_MAX_AGE,
                SampleVaxTypeData.ALLERGY_REQS_1,
                SampleVaxTypeData.HISTORY_REQS_1));
    }


    @Test
    public void compareToTest() {
        // test already done in GroupName so this test is to check if
        // the comparator is correctly set
        VaxType t1 = new VaxType(
                new GroupName("aZ"),
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_ALLERGY_REQS,
                VaxType.DEFAULT_HISTORY_REQS);
        VaxType t2 = new VaxType(
                new GroupName("Aa"),
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_ALLERGY_REQS,
                VaxType.DEFAULT_HISTORY_REQS);
        assertTrue(t1.compareTo(t2) > 0);
    }


    @Test
    public void equalsTest() {
        VaxType testing = new VaxType(
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL);
        List<VaxType> vaxTypes = permutateVaxType();

        assertTrue(testing.equals(testing));
        assertTrue(testing.equals(vaxTypes.get(0)));
        for (int i = 1; i < vaxTypes.size(); i++) {
            assertFalse(testing.equals(vaxTypes.get(i)));
        }

        assertFalse(testing.equals(Integer.valueOf(0)));
    }


    @Test
    public void hashCodeTest() {
        List<VaxType> vaxTypes = permutateVaxType();
        HashSet<VaxType> vaxTypeSet = new HashSet<>(vaxTypes);
        vaxTypeSet.addAll(vaxTypes);

        assertTrue(vaxTypeSet.size() == vaxTypes.size());
    }


    private List<VaxType> permutateVaxType() {
        VaxType t1 = new VaxType(
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL);
        VaxType t2 = new VaxType(
                SampleVaxTypeData.NAME_1,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL);
        VaxType t3 = new VaxType(
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_1,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL);
        VaxType t4 = new VaxType(
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_1,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL);
        VaxType t5 = new VaxType(
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_1,
                SampleVaxTypeData.ALLERGY_REQS_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL);
        VaxType t6 = new VaxType(
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.ALLERGY_REQS_1,
                SampleVaxTypeData.HISTORY_REQS_REAL);
        VaxType t7 = new VaxType(
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.ALLERGY_REQS_REAL,
                SampleVaxTypeData.HISTORY_REQS_1);
        return List.of(t1, t2, t3, t4, t5, t6, t7);
    }
}
