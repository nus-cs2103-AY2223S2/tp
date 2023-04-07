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

public class VariantAnalystTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder().withVariants("DENV1");
    private static final List<Person> EMPTY_PERSON_LIST = new ArrayList<>();
    private static final List<Person> SINGLE_PERSON_LIST = List.of(PERSON_BUILDER.build());

    private VariantAnalyst analyst;

    private List<Person> makePersonList() {
        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        List<Person> personList = new ArrayList<>();

        personList.add(tempBuilder.withVariants().build());
        personList.add(tempBuilder.withVariants("DENV1").build());
        personList.add(tempBuilder.withVariants("DENV2").build());
        personList.add(tempBuilder.withVariants("DENV1", "DENV2").build());
        personList.add(tempBuilder.withVariants("DENV2", "DENV3").build());

        return personList;
    }

    private void setAnalystWith(List<Person> personList) {
        analyst = new VariantAnalyst(personList);
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

        assertEquals(5, analyst.getTotal());
    }

    @Test
    public void getSortedBins_emptyList_empty() {
        setAnalystWith(EMPTY_PERSON_LIST);

        assertEquals(new ArrayList<>(), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_true() {
        setAnalystWith(SINGLE_PERSON_LIST);
        DataBin bin = new DataBin("DENV1");
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals(List.of(bin), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_false() {
        setAnalystWith(SINGLE_PERSON_LIST);

        assertNotEquals(List.of(new DataBin("DENV2")), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_true() {
        setAnalystWith(makePersonList());

        DataBin bin1 = new DataBin("DENV1");
        bin1.addPerson(PERSON_BUILDER.build());
        bin1.addPerson(PERSON_BUILDER.build());

        DataBin bin2 = new DataBin("DENV2");
        bin2.addPerson(PERSON_BUILDER.build());
        bin2.addPerson(PERSON_BUILDER.build());
        bin2.addPerson(PERSON_BUILDER.build());

        DataBin bin3 = new DataBin("DENV3");
        bin3.addPerson(PERSON_BUILDER.build());

        List<DataBin> bins = List.of(bin2, bin1, bin3);

        assertEquals(bins, analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_falseBecauseUnsorted() {
        setAnalystWith(makePersonList());

        DataBin bin1 = new DataBin("DENV1");
        bin1.addPerson(PERSON_BUILDER.build());
        bin1.addPerson(PERSON_BUILDER.build());

        DataBin bin2 = new DataBin("DENV2");
        bin2.addPerson(PERSON_BUILDER.build());
        bin2.addPerson(PERSON_BUILDER.build());
        bin2.addPerson(PERSON_BUILDER.build());

        DataBin bin3 = new DataBin("DENV3");
        bin3.addPerson(PERSON_BUILDER.build());

        List<DataBin> bins = List.of(bin1, bin2, bin3);

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

        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of());

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_emptyList_false() {
        setAnalystWith(EMPTY_PERSON_LIST);

        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(PERSON_BUILDER.build()));

        assertFalse(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_true() {
        setAnalystWith(SINGLE_PERSON_LIST);

        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(PERSON_BUILDER.build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_trueWithOtherName() {
        setAnalystWith(SINGLE_PERSON_LIST);

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(tempBuilder.withName("different name").build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_falseWithOtherVariant() {
        setAnalystWith(SINGLE_PERSON_LIST);

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(tempBuilder.withVariants("DENV2").build()));

        assertFalse(analyst.equals(otherAnalyst));
    }
}
