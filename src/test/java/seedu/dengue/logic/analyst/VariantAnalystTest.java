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

public class VariantAnalystTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder().withVariants("DENV1");
    private static final List<String[]> VALID_VARIANTS = List.of(
            new String[]{},
            new String[]{"DENV1"},
            new String[]{"DENV2"},
            new String[]{"DENV1", "DENV2"},
            new String[]{"DENV2", "DENV3"});
    private static final PersonListBuilder LIST_BUILDER = new PersonListBuilder()
            .withDefaultPerson(PERSON_BUILDER.build())
            .withVariants(VALID_VARIANTS);

    private VariantAnalyst analyst;

    private void setAnalyst() {
        analyst = new VariantAnalyst(LIST_BUILDER.build());
    }

    private void setEmptyAnalyst() {
        analyst = new VariantAnalyst(LIST_BUILDER.buildEmpty());
    }

    private void setSingleAnalyst() {
        analyst = new VariantAnalyst(LIST_BUILDER.buildSingle());
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

        assertEquals(VALID_VARIANTS.size(), analyst.getTotal());
    }

    @Test
    public void getSortedBins_emptyList_empty() {
        setEmptyAnalyst();

        assertEquals(new ArrayList<>(), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_true() {
        setSingleAnalyst();
        DataBin bin = new DataBin("DENV1");
        bin.addPerson(PERSON_BUILDER.build());

        assertEquals(List.of(bin), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_singleList_false() {
        setSingleAnalyst();

        assertNotEquals(List.of(new DataBin("DENV2")), analyst.getSortedBins());
    }

    @Test
    public void getSortedBins_normalList_true() {
        setAnalyst();

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
        setAnalyst();

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
        setEmptyAnalyst();

        assertTrue(analyst.equals(analyst));
    }

    @Test
    public void equals_emptyList_true() {
        setEmptyAnalyst();

        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of());

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_emptyList_false() {
        setEmptyAnalyst();

        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(PERSON_BUILDER.build()));

        assertFalse(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_true() {
        setSingleAnalyst();

        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(PERSON_BUILDER.build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_trueWithOtherName() {
        setSingleAnalyst();

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(tempBuilder.withName("different name").build()));

        assertTrue(analyst.equals(otherAnalyst));
    }

    @Test
    public void equals_singleList_falseWithOtherVariant() {
        setSingleAnalyst();

        PersonBuilder tempBuilder = new PersonBuilder(PERSON_BUILDER.build());
        VariantAnalyst otherAnalyst = new VariantAnalyst(List.of(tempBuilder.withVariants("DENV2").build()));

        assertFalse(analyst.equals(otherAnalyst));
    }
}
