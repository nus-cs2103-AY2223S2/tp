package seedu.dengue.model.overview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.logic.analyst.PostalAnalyst;
import seedu.dengue.testutil.PersonBuilder;
import seedu.dengue.testutil.PersonListBuilder;

public class PostalOverviewTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder();
    private static final List<String> VALID_POSTALS = List.of("S123456", "S321321", "S598765", "S314159", "S918887");
    private static final PersonListBuilder LIST_BUILDER = new PersonListBuilder().withPostals(VALID_POSTALS);

    private PostalOverview overview;

    private void setOverview() {
        overview = new PostalOverview(LIST_BUILDER.build());
    }

    private void setEmptyOverview() {
        overview = new PostalOverview();
    }

    @Test
    public void getOverviewTitle_newOverview_success() {
        setEmptyOverview();

        assertEquals("Overview by Location", overview.getOverviewTitle());
    }

    @Test
    public void getOverviewTitle_normalOverview_success() {
        setOverview();

        assertEquals("Overview by Location", overview.getOverviewTitle());
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

        assertEquals(new PostalAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void getAnalyst_normalOverview_success() {
        setOverview();

        assertEquals(new PostalAnalyst(LIST_BUILDER.build()), overview.getAnalyst());
    }

    @Test
    public void getAnalyst_normalOverviewEmptyAnalyst_failure() {
        setOverview();

        assertNotEquals(new PostalAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void getOverviewContent_newOverview_success() {
        setEmptyOverview();

        assertEquals(new ArrayList<DataBin>(), overview.getOverviewContent());
    }

    @Test
    public void getOverviewContent_normalOverview_success() {
        setOverview();

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

        assertEquals(new PostalAnalyst(LIST_BUILDER.build()), overview.getAnalyst());
    }

    @Test
    public void update_normalOverviewToNew_correctAnalyst() {
        setOverview();
        overview.update(LIST_BUILDER.buildEmpty());

        assertEquals(new PostalAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void update_newOverviewToNormal_correctContent() {
        setEmptyOverview();
        overview.update(LIST_BUILDER.build());

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

        assertEquals(bins, overview.getOverviewContent());
    }

    @Test
    public void update_normalOverviewToNew_correctContent() {
        setOverview();
        overview.update(LIST_BUILDER.buildEmpty());

        assertEquals(new ArrayList<DataBin>(), overview.getOverviewContent());
    }
}
