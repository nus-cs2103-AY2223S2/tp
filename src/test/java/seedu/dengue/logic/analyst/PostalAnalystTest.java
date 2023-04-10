package seedu.dengue.logic.analyst;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dengue.testutil.PersonBuilder;
import seedu.dengue.testutil.PersonListBuilder;

public class PostalAnalystTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder();
    private static final List<String> VALID_POSTALS = List.of("S123456", "S321321", "S598765", "S314159", "S918887");
    private static final PersonListBuilder LIST_BUILDER = new PersonListBuilder().withPostals(VALID_POSTALS);

    private PostalAnalyst analyst;

    private void setAnalyst() {
        analyst = new PostalAnalyst(LIST_BUILDER.build());
    }

    private void setEmptyAnalyst() {
        analyst = new PostalAnalyst(LIST_BUILDER.buildEmpty());
    }

    private void setSingleAnalyst() {
        analyst = new PostalAnalyst(LIST_BUILDER.buildSingle());
    }

    @Test
    public void getTotal_emptyList_zero() {
        setEmptyAnalyst();

        assertEquals(0, analyst.getTotal());
    }

    @Test
    public void getTotal_singleList_one() {
        setSingleAnalyst();

        assertEquals(1, analyst.getTotal());
    }

    @Test
    public void getTotal_normalList_correct() {
        setAnalyst();

        assertEquals(VALID_POSTALS.size(), analyst.getTotal());
    }

    @Test
    public void getSortedBins_emptyList_empty() {
        setEmptyAnalyst();

        assertEquals(new ArrayList<>(), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_true() {
        setSingleAnalyst();
        DataBin bin = new DataBin("[65 66 67 68]\nHillview\nDairy Farm\nBukit Panjang\nChoa Chu Kang");
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals(List.of(bin), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_false() {
        setSingleAnalyst();

        assertNotEquals(List.of(new DataBin("[49 50 81 91]\nLoyang\nChangi")), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_true() {
        setAnalyst();

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
        setAnalyst();

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
        setEmptyAnalyst();

        assertTrue(analyst.equals(analyst));
    }

    @Test
    public void equals_emptyList_true() {
        setEmptyAnalyst();

        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of());

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_emptyList_false() {
        setEmptyAnalyst();

        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(PERSON_BUILDER.build()));

        assertFalse(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_true() {
        setSingleAnalyst();

        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(PERSON_BUILDER.build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_trueWithOtherName() {
        setSingleAnalyst();

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(tempBuilder.withName("different name").build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_falseWithOtherPostal() {
        setSingleAnalyst();

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        PostalAnalyst otherAnalyst = new PostalAnalyst(List.of(tempBuilder.withPostal("S111111").build()));

        assertFalse(analyst.equals(otherAnalyst));
    }
}
