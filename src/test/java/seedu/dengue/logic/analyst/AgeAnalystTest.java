package seedu.dengue.logic.analyst;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.person.Person;
import seedu.dengue.testutil.PersonBuilder;

public class AgeAnalystTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder();
    private static final List<Person> EMPTY_PERSON_LIST = new ArrayList<>();
    private static final List<Person> SINGLE_PERSON_LIST = List.of(PERSON_BUILDER.build());
    private static final String[] VALID_AGES = {"3", "14", "159", "26", "53", "58", "97", "93", "23", "84"};

    private AgeAnalyst analyst;

    private List<Person> makePersonList() {
        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        List<Person> personList = new ArrayList<>();

        for (String age : VALID_AGES) {
            personList.add(tempBuilder.withAge(age).build());
        }

        return personList;
    }

    private void setAnalystWith(List<Person> personList) {
        analyst = new AgeAnalyst(personList);
    }

    @Test
    public void getTotal_emptyList_zero() {
        setAnalystWith(EMPTY_PERSON_LIST);

        assertEquals(0, analyst.getTotal());
    }

    @Test
    public void getTotal_singleList_one() {
        setAnalystWith(SINGLE_PERSON_LIST);

        assertEquals(1, analyst.getTotal());
    }

    @Test
    public void getTotal_normalList_correct() {
        setAnalystWith(makePersonList());

        assertEquals(VALID_AGES.length, analyst.getTotal());
    }

    @Test
    public void getSortedBins_emptyList_empty() {
        setAnalystWith(EMPTY_PERSON_LIST);

        assertEquals(new ArrayList<>(), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_true() {
        setAnalystWith(SINGLE_PERSON_LIST);
        DataBin bin = new DataBin("20 - 29");
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals(List.of(bin), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_false() {
        setAnalystWith(SINGLE_PERSON_LIST);

        assertNotEquals(List.of(new DataBin("10 - 19")), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_true() {
        setAnalystWith(makePersonList());

        DataBin bin0 = new DataBin("0 - 9");
        bin0.addPerson(PERSON_BUILDER.build());

        DataBin bin1 = new DataBin("10 - 19");
        bin1.addPerson(PERSON_BUILDER.build());

        DataBin bin2 = new DataBin("20 - 29");
        bin2.addPerson(PERSON_BUILDER.build());
        bin2.addPerson(PERSON_BUILDER.build());

        DataBin bin5 = new DataBin("50 - 59");
        bin5.addPerson(PERSON_BUILDER.build());
        bin5.addPerson(PERSON_BUILDER.build());

        DataBin bin8 = new DataBin("80 - 89");
        bin8.addPerson(PERSON_BUILDER.build());

        DataBin bin9 = new DataBin("90 - 99");
        bin9.addPerson(PERSON_BUILDER.build());
        bin9.addPerson(PERSON_BUILDER.build());

        DataBin bin15 = new DataBin("150 - 159");
        bin15.addPerson(PERSON_BUILDER.build());

        List<DataBin> bins = List.of(bin2, bin5, bin9, bin0, bin1, bin8, bin15);

        assertEquals(bins, analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_falseBecauseUnsorted() {
        setAnalystWith(makePersonList());

        DataBin bin0 = new DataBin("0 - 9");
        bin0.addPerson(PERSON_BUILDER.build());

        DataBin bin1 = new DataBin("10 - 19");
        bin1.addPerson(PERSON_BUILDER.build());

        DataBin bin2 = new DataBin("20 - 29");
        bin2.addPerson(PERSON_BUILDER.build());
        bin2.addPerson(PERSON_BUILDER.build());

        DataBin bin5 = new DataBin("50 - 59");
        bin5.addPerson(PERSON_BUILDER.build());
        bin5.addPerson(PERSON_BUILDER.build());

        DataBin bin8 = new DataBin("80 - 89");
        bin8.addPerson(PERSON_BUILDER.build());

        DataBin bin9 = new DataBin("90 - 99");
        bin9.addPerson(PERSON_BUILDER.build());
        bin9.addPerson(PERSON_BUILDER.build());

        DataBin bin15 = new DataBin("150 - 159");
        bin15.addPerson(PERSON_BUILDER.build());

        List<DataBin> bins = List.of(bin0, bin1, bin2, bin5, bin8, bin9, bin15);

        assertNotEquals(bins, analyst.getSortedBins());
    }

    @Test
    public void equals_emptyList_trueWithSelf() {
        setAnalystWith(EMPTY_PERSON_LIST);

        assertTrue(analyst.equals(analyst));
    }

    @Test
    public void equals_emptyList_true() {
        setAnalystWith(EMPTY_PERSON_LIST);

        AgeAnalyst otherAnalyst = new AgeAnalyst(List.of());

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_emptyList_false() {
        setAnalystWith(EMPTY_PERSON_LIST);

        AgeAnalyst otherAnalyst = new AgeAnalyst(List.of(PERSON_BUILDER.build()));

        assertFalse(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_true() {
        setAnalystWith(SINGLE_PERSON_LIST);

        AgeAnalyst otherAnalyst = new AgeAnalyst(List.of(PERSON_BUILDER.build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_trueWithOtherName() {
        setAnalystWith(SINGLE_PERSON_LIST);

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        AgeAnalyst otherAnalyst = new AgeAnalyst(List.of(tempBuilder.withName("different name").build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_falseWithOtherAge() {
        setAnalystWith(SINGLE_PERSON_LIST);

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        AgeAnalyst otherAnalyst = new AgeAnalyst(List.of(tempBuilder.withAge("33").build()));

        assertFalse(analyst.equals(otherAnalyst));
    }
}
