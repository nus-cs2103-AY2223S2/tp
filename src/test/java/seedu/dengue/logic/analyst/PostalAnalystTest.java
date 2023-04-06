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

public class PostalAnalystTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder();
    private static final List<Person> EMPTY_PERSON_LIST = new ArrayList<>();
    private static final List<Person> SINGLE_PERSON_LIST = List.of(PERSON_BUILDER.build());
    private static final String[] VALID_POSTALS = {
            "S123456",
            "S321321",
            "S598765",
            "S314159",
            "S918887"};

    private PostalAnalyst analyst;

    private List<Person> makePersonList() {
        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        List<Person> personList = new ArrayList<>();

        for (String postal : VALID_POSTALS) {
            personList.add(tempBuilder.withPostal(postal).build());
        }

        return personList;
    }

    private void setAnalystWith(List<Person> personList) {
        analyst = new PostalAnalyst(personList);
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

        assertEquals(VALID_POSTALS.length, analyst.getTotal());
    }

    @Test
    public void getSortedBins_emptyList_empty() {
        setAnalystWith(EMPTY_PERSON_LIST);

        assertEquals(new ArrayList<>(), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_true() {
        setAnalystWith(SINGLE_PERSON_LIST);
        DataBin bin = new DataBin("[65 66 67 68]\nHillview\nDairy Farm\nBukit Panjang\nChoa Chu Kang");
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals(List.of(bin), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_false() {
        setAnalystWith(SINGLE_PERSON_LIST);

        assertNotEquals(List.of(new DataBin("[49 50 81 91]\nLoyang\nChangi")), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_true() {
        setAnalystWith(makePersonList());

        DataBin bin11 = new DataBin("[11 12 13]\nPasir Panjang\nHong Leong Garden\nClementi New Town");
        bin11.addPerson(PERSON_BUILDER.build());

        DataBin bin31 = new DataBin("[31 32 33]\nBalestier\nToa Payoh\nSerangoon");
        bin31.addPerson(PERSON_BUILDER.build());
        bin31.addPerson(PERSON_BUILDER.build());

        DataBin bin49 = new DataBin("[49 50 81 91]\nLoyang\nChangi");
        bin49.addPerson(PERSON_BUILDER.build());

        DataBin bin58 = new DataBin("[58 59]\nUpper Bukit Timah\nClementi Park\nUlu Pandan");
        bin58.addPerson(PERSON_BUILDER.build());

        List<DataBin> bins = List.of(bin31, bin11, bin49, bin58);

        assertEquals(bins, analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_falseBecauseUnsorted() {
        setAnalystWith(makePersonList());

        DataBin bin11 = new DataBin("[11 12 13]\nPasir Panjang\nHong Leong Garden\nClementi New Town");
        bin11.addPerson(PERSON_BUILDER.build());

        DataBin bin31 = new DataBin("[31 32 33]\nBalestier\nToa Payoh\nSerangoon");
        bin31.addPerson(PERSON_BUILDER.build());
        bin31.addPerson(PERSON_BUILDER.build());

        DataBin bin49 = new DataBin("[49 50 81 91]\nLoyang\nChangi");
        bin49.addPerson(PERSON_BUILDER.build());

        DataBin bin58 = new DataBin("[58 59]\nUpper Bukit Timah\nClementi Park\nUlu Pandan");
        bin58.addPerson(PERSON_BUILDER.build());

        List<DataBin> bins = List.of(bin11, bin31, bin49, bin58);

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

        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of());

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_emptyList_false() {
        setAnalystWith(EMPTY_PERSON_LIST);

        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(PERSON_BUILDER.build()));

        assertFalse(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_true() {
        setAnalystWith(SINGLE_PERSON_LIST);

        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(PERSON_BUILDER.build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_trueWithOtherName() {
        setAnalystWith(SINGLE_PERSON_LIST);

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(tempBuilder.withName("different name").build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_falseWithOtherAge() {
        setAnalystWith(SINGLE_PERSON_LIST);

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(tempBuilder.withPostal("S111111").build()));

        assertFalse(analyst.equals(otherAnalyst));
    }
}
