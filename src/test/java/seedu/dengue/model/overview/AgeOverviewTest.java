package seedu.dengue.model.overview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.analyst.AgeAnalyst;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.testutil.PersonBuilder;
import seedu.dengue.testutil.PersonListBuilder;

public class AgeOverviewTest {
    private static final PersonBuilder PERSON_BUILDER = new PersonBuilder();
    private static final List<String> VALID_AGES = List.of("3", "14", "159", "26", "53", "58", "97", "93", "23", "84");
    private static final PersonListBuilder LIST_BUILDER = new PersonListBuilder().withAges(VALID_AGES);

    private AgeOverview overview;

    private void setOverview() {
        overview = new AgeOverview(LIST_BUILDER.build());
    }

    private void setEmptyOverview() {
        overview = new AgeOverview();
    }

    @Test
    public void getOverviewTitle_newOverview_success() {
        setEmptyOverview();

        assertEquals("Overview by Age", overview.getOverviewTitle());
    }

    @Test
    public void getOverviewTitle_normalOverview_success() {
        setOverview();

        assertEquals("Overview by Age", overview.getOverviewTitle());
    }

    @Test
    public void getOverviewSubtitle_newOverview_success() {
        setEmptyOverview();

        assertEquals("Total Cases: 0", overview.getOverviewSubtitle());
    }

    @Test
    public void getOverviewSubtitle_normalOverview_success() {
        setOverview();

        assertEquals("Total Cases: 10", overview.getOverviewSubtitle());
    }

    @Test
    public void getAnalyst_newOverview_success() {
        setEmptyOverview();

        assertEquals(new AgeAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void getAnalyst_normalOverview_success() {
        setOverview();

        assertEquals(new AgeAnalyst(LIST_BUILDER.build()), overview.getAnalyst());
    }

    @Test
    public void getAnalyst_normalOverviewEmptyAnalyst_failure() {
        setOverview();

        assertNotEquals(new AgeAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void getOverviewContent_newOverview_success() {
        setEmptyOverview();

        assertEquals(new ArrayList<DataBin>(), overview.getOverviewContent());
    }

    @Test
    public void getOverviewContent_normalOverview_success() {
        setOverview();

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

        assertEquals(bins, overview.getOverviewContent());
    }

    @Test
    public void update_newOverviewToNormal_correctSubtitle() {
        setEmptyOverview();
        overview.update(LIST_BUILDER.build());

        assertEquals("Total Cases: 10", overview.getOverviewSubtitle());
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

        assertEquals(new AgeAnalyst(LIST_BUILDER.build()), overview.getAnalyst());
    }

    @Test
    public void update_normalOverviewToNew_correctAnalyst() {
        setOverview();
        overview.update(LIST_BUILDER.buildEmpty());

        assertEquals(new AgeAnalyst(LIST_BUILDER.buildEmpty()), overview.getAnalyst());
    }

    @Test
    public void update_newOverviewToNormal_correctContent() {
        setEmptyOverview();
        overview.update(LIST_BUILDER.build());

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

        assertEquals(bins, overview.getOverviewContent());
    }

    @Test
    public void update_normalOverviewToNew_correctContent() {
        setOverview();
        overview.update(LIST_BUILDER.buildEmpty());

        assertEquals(new ArrayList<DataBin>(), overview.getOverviewContent());
    }
}
