package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.testutil.StatisticsBuilder;



public class StatisticsTest {

    private Model model;
    private Model expectedModel;
    private Statistics statistics;
    private Statistics expectedStatistics;

    @BeforeEach
    public void setUp() {
        int statusIdInterested = 0;
        int statusIdApplied = 1;
        int statusIdOffered = 2;
        int statusIdRejected = 3;

        Predicate<Internship> predicateNumInterested = internship -> internship.getStatusId() == statusIdInterested;
        Predicate<Internship> predicateNumApplied = internship -> internship.getStatusId() == statusIdApplied;
        Predicate<Internship> predicateNumOffered = internship -> internship.getStatusId() == statusIdOffered;
        Predicate<Internship> predicateNumRejected = internship -> internship.getStatusId() == statusIdRejected;

        model = new ModelManager(getTypicalInternshipCatalogue(), getTypicalEventCatalogue(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(), new UserPrefs());
        StatisticsBuilder statisticsBuilder = new StatisticsBuilder();

        // total internships
        statisticsBuilder.withTotalInternships(expectedModel.getFilteredInternshipList().size());

        // num interested
        expectedModel.updateFilteredInternshipList(predicateNumInterested);
        statisticsBuilder.withNumInterested(expectedModel.getFilteredInternshipList().size());

        // num applied
        expectedModel.updateFilteredInternshipList(predicateNumApplied);
        statisticsBuilder.withNumApplied(expectedModel.getFilteredInternshipList().size());

        // num offered
        expectedModel.updateFilteredInternshipList(predicateNumOffered);
        statisticsBuilder.withNumOffered(expectedModel.getFilteredInternshipList().size());

        //num rejected
        expectedModel.updateFilteredInternshipList(predicateNumRejected);
        statisticsBuilder.withNumRejected(expectedModel.getFilteredInternshipList().size());

        // num events
        statisticsBuilder.withNumEvents(expectedModel.getFilteredEventList().size());

        expectedStatistics = statisticsBuilder.build();
    }

    @Test
    public void constructor() {
        // tests parsing of model's internship and event lists
        statistics = new Statistics(model.getFilteredInternshipList(), model.getFilteredEventList());
        assertEquals(statistics, expectedStatistics);
    }
}
