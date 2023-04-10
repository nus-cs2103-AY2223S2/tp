package seedu.dengue.model.overview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.logic.analyst.VariantAnalyst;
import seedu.dengue.testutil.PersonBuilder;
import seedu.dengue.testutil.PersonListBuilder;

public class VariantOverviewTest {
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

    private VariantOverview overview;

    private void setOverview() {
        overview = new VariantOverview(LIST_BUILDER.build());
    }

    private void setEmptyOverview() {
        overview = new VariantOverview();
    }

    @Test
    public void getOverviewTitle_newOverview_success() {
        setEmptyOverview();

        assertEquals("Overview by Variant", overview.getOverviewTitle());
    }

    @Test
    public void getOverviewTitle_normalOverview_success() {
        setOverview();

        assertEquals("Overview by Variant", overview.getOverviewTitle());
    }

    @Test
    public void getOverviewSubtitle_newOverview_success() {
        setEmptyOverview();

        assertEquals("Total Cases: 0", overview.getOverviewSubtitle());
    }

    @Test
    public void getOverviewSubtitle_normalOverview_success() {
        setOverview();

        assertEquals("Total Cases: 5", overview.getOverviewSubtitle());
    }

    @Test
    public void getAnalyst_newOverview_success() {
        setEmptyOverview();

        assertEquals(new VariantAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void getAnalyst_normalOverview_success() {
        setOverview();

        assertEquals(new VariantAnalyst(LIST_BUILDER.build()), overview.getAnalyst());
    }

    @Test
    public void getAnalyst_normalOverviewEmptyAnalyst_failure() {
        setOverview();

        assertNotEquals(new VariantAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void getOverviewContent_newOverview_success() {
        setEmptyOverview();

        assertEquals(new ArrayList<DataBin>(), overview.getOverviewContent());
    }

    @Test
    public void getOverviewContent_normalOverview_success() {
        setOverview();

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

        assertEquals(bins, overview.getOverviewContent());
    }

    @Test
    public void update_newOverviewToNormal_correctSubtitle() {
        setEmptyOverview();
        overview.update(LIST_BUILDER.build());

        assertEquals("Total Cases: 5", overview.getOverviewSubtitle());
    }

    @Test
    public void update_normalOverviewToNew_correctSubtitle() {
        setOverview();
        overview.update(LIST_BUILDER.buildEmpty());

        assertEquals("Total Cases: 0", overview.getOverviewSubtitle());
    }

    @Test
    public void update_newOverviewToNormal_correctAnalyst() {
        setEmptyOverview();
        overview.update(LIST_BUILDER.build());

        assertEquals(new VariantAnalyst(LIST_BUILDER.build()), overview.getAnalyst());
    }

    @Test
    public void update_normalOverviewToNew_correctAnalyst() {
        setOverview();
        overview.update(LIST_BUILDER.buildEmpty());

        assertEquals(new VariantAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void update_newOverviewToNormal_correctContent() {
        setEmptyOverview();
        overview.update(LIST_BUILDER.build());

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

        assertEquals(bins, overview.getOverviewContent());
    }

    @Test
    public void update_normalOverviewToNew_correctContent() {
        setOverview();
        overview.update(LIST_BUILDER.buildEmpty());

        assertEquals(new ArrayList<DataBin>(), overview.getOverviewContent());
    }
}
