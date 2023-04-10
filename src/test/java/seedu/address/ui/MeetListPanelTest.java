package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.model.recommendation.TypicalRecommendations.getTypicalRecommendations;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysRecommendation;

import org.junit.jupiter.api.Test;

import guitests.guihandles.MeetCardHandle;
import guitests.guihandles.MeetListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.timetable.time.TimePeriodBuilder;

public class MeetListPanelTest extends GuiUnitTest {
    private static final ObservableList<Recommendation> TYPICAL_RECOMMEDNATIONS =
            FXCollections.observableList(getTypicalRecommendations());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private MeetListPanelHandle meetListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_RECOMMEDNATIONS);

        for (int i = 0; i < TYPICAL_RECOMMEDNATIONS.size(); i++) {
            meetListPanelHandle.navigateToCard(TYPICAL_RECOMMEDNATIONS.get(i));
            Recommendation expectedRecommendation = TYPICAL_RECOMMEDNATIONS.get(i);
            MeetCardHandle actualCard = meetListPanelHandle.getMeetCardHandle(i);

            assertCardDisplaysRecommendation(expectedRecommendation, actualCard);
        }
    }

    /**
     * Verifies that creating and deleting large number of recommendations in {@code MeetListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() {
        ObservableList<Recommendation> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of person cards exceeded time limit");
    }

    /**
     * Returns a list of recommendations containing {@code recommendationCount} recommendations that is
     * used to populate the {@code MeetListPanel}.
     */
    private ObservableList<Recommendation> createBackingList(int recommendationCount) {
        ObservableList<Recommendation> backingList = FXCollections.observableArrayList();
        for (int i = 0; i < recommendationCount; i++) {
            Location location = new Location("NUS", 1.29551, 103.77693);
            TimePeriod timePeriod = new TimePeriodBuilder().build();
            ContactIndex contactIndex = new ContactIndex(i + 1);
            Recommendation recommendation = new Recommendation(
                    location, timePeriod, contactIndex, true);
            backingList.add(recommendation);
        }
        return backingList;
    }

    /**
     * Initializes {@code meetListPanelHandle} with a {@code MeetListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code MeetListPanel}.
     */
    private void initUi(ObservableList<Recommendation> backingList) {
        MeetListPanel meetListPanel =
                new MeetListPanel(backingList);
        uiPartExtension.setUiPart(meetListPanel);

        meetListPanelHandle = new MeetListPanelHandle(getChildNode(meetListPanel.getRoot(),
                MeetListPanelHandle.MEET_LIST_VIEW_ID));
    }
}
