package seedu.dengue.logic.analyst;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dengue.testutil.PersonBuilder;

public class DataBinTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder();
    private static final String TEST_NAME = "test";

    private DataBin bin;

    @BeforeEach
    public void refreshBin() {
        bin = new DataBin(TEST_NAME);
    }

    @Test
    public void addPerson_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bin.addPerson(null));
    }

    @Test
    public void addPerson_validPerson_success() {
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals(1, bin.getSize());
    }

    @Test
    public void addPerson_randomPerson_success() {
        bin.addPerson(PERSON_BUILDER.buildRandom());

        assertEquals(1, bin.getSize());
    }

    @Test
    public void addPerson_multipleSameValidPersons_success() {
        bin.addPerson(PERSON_BUILDER.build());
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals(2, bin.getSize());
    }

    @Test
    public void addPerson_randomValidPersons_success() {
        bin.addPerson(PERSON_BUILDER.buildRandom());
        bin.addPerson(PERSON_BUILDER.buildRandom());

        assertEquals(2, bin.getSize());
    }

    @Test
    public void getName_newDataBin_correctName() {
        assertEquals(TEST_NAME, bin.getName());
    }

    @Test
    public void getSize_newDataBin_correctStartingSize() {
        assertEquals(0, bin.getSize());
    }

    @Test
    public void isEmpty_newDataBin_true() {
        assertTrue(bin.isEmpty());
    }

    @Test
    public void isEmpty_withOnePerson_false() {
        bin.addPerson(PERSON_BUILDER.build());

        assertFalse(bin.isEmpty());
    }

    @Test
    public void isEmpty_withMultiplePersons_false() {
        bin.addPerson(PERSON_BUILDER.build());
        bin.addPerson(PERSON_BUILDER.build());

        assertFalse(bin.isEmpty());
    }

    @Test
    public void equals_withSelf_true() {
        assertTrue(bin.equals(bin));
    }

    @Test
    public void equals_bothNew_true() {
        DataBin otherBin = new DataBin(TEST_NAME);

        assertTrue(bin.equals(otherBin));
    }

    @Test
    public void equals_differentPersonInEach_true() {
        DataBin otherBin = new DataBin(TEST_NAME);

        bin.addPerson(PERSON_BUILDER.buildRandom());
        otherBin.addPerson(PERSON_BUILDER.buildRandom());

        assertTrue(bin.equals(otherBin));
    }

    @Test
    public void equals_differentSize_false() {
        DataBin otherBin = new DataBin(TEST_NAME);

        bin.addPerson(PERSON_BUILDER.build());
        otherBin.addPerson(PERSON_BUILDER.build());
        otherBin.addPerson(PERSON_BUILDER.build());

        assertFalse(bin.equals(otherBin));
    }

    @Test
    public void equals_differentName_false() {
        DataBin thisBin = new DataBin("this");
        DataBin otherBin = new DataBin("other");

        assertFalse(thisBin.equals(otherBin));
    }

    @Test
    public void compareTo_withSelf_same() {
        assertEquals(0, bin.compareTo(bin));
    }

    @Test
    public void compareTo_bothNew_same() {
        DataBin otherBin = new DataBin(TEST_NAME);

        assertEquals(0, bin.compareTo(otherBin));
    }

    @Test
    public void compareTo_differentName_same() {
        DataBin thisBin = new DataBin("this");
        DataBin otherBin = new DataBin("other");

        assertEquals(0, thisBin.compareTo(otherBin));
    }

    @Test
    public void compareTo_largerThanOther_greater() {
        DataBin otherBin = new DataBin(TEST_NAME);

        bin.addPerson(PERSON_BUILDER.build());

        assertTrue(bin.compareTo(otherBin) > 0);
    }

    @Test
    public void compareTo_moreLargerThanOther_greater() {
        DataBin otherBin = new DataBin(TEST_NAME);

        bin.addPerson(PERSON_BUILDER.build());
        bin.addPerson(PERSON_BUILDER.build());
        otherBin.addPerson(PERSON_BUILDER.build());

        assertTrue(bin.compareTo(otherBin) > 0);
    }

    @Test
    public void compareTo_smallerThanOther_less() {
        DataBin otherBin = new DataBin(TEST_NAME);

        otherBin.addPerson(PERSON_BUILDER.build());

        assertTrue(bin.compareTo(otherBin) < 0);
    }

    @Test
    public void compareTo_moreSmallerThanOther_greater() {
        DataBin otherBin = new DataBin(TEST_NAME);

        bin.addPerson(PERSON_BUILDER.build());
        otherBin.addPerson(PERSON_BUILDER.build());
        otherBin.addPerson(PERSON_BUILDER.build());

        assertTrue(bin.compareTo(otherBin) < 0);
    }

    @Test
    public void toString_newDataBin_correct() {
        assertEquals("Bin '" + TEST_NAME + "': 0", bin.toString());
    }

    @Test
    public void toString_sizeOfOne_correct() {
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals("Bin '" + TEST_NAME + "': 1", bin.toString());
    }

    @Test
    public void toCsvString_newDataBin_correct() {
        String[] strArr = {TEST_NAME, "0"};

        assertArrayEquals(strArr, bin.toCsvString());
    }

    @Test
    public void toCsvString_sizeOfOne_correct() {
        bin.addPerson(PERSON_BUILDER.build());
        String[] strArr = {TEST_NAME, "1"};

        assertArrayEquals(strArr, bin.toCsvString());
    }
}
