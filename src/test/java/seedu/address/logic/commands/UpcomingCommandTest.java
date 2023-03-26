package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_INTERNSHIPS;
import static seedu.address.testutil.TypicalInternshipsVariableDates.FOODPANDA;
import static seedu.address.testutil.TypicalInternshipsVariableDates.GOLDMAN;
import static seedu.address.testutil.TypicalInternshipsVariableDates.SUPERCELLGAMES;
import static seedu.address.testutil.TypicalInternshipsVariableDates.getTypicalInternBuddyVariableDates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;




/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class UpcomingCommandTest {

    private Model model = new ModelManager(getTypicalInternBuddyVariableDates(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternBuddyVariableDates(), new UserPrefs());
    private LocalDate currentDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void dateInternships() {
        Internship today = new InternshipBuilder().withDate(currentDate.format(formatter)).build();
        Internship yesterday = new InternshipBuilder().withDate(currentDate.plusDays(-1)
                .format(formatter)).build();
        Internship oneday = new InternshipBuilder().withDate(currentDate.plusDays(1)
                .format(formatter)).build();
        Internship sixdays = new InternshipBuilder().withDate(currentDate.plusDays(6)
                .format(formatter)).build();
        Internship sevendays = new InternshipBuilder().withDate(currentDate.plusDays(7)
                .format(formatter)).build();

        assertTrue(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(today));
        assertTrue(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(sixdays));
        assertTrue(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(oneday));
        assertFalse(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(yesterday));
        assertFalse(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(sevendays));
    }

    @Test
    public void statusInternships() {
        String dateToday = currentDate.format(formatter).toString();
        Internship interview = new InternshipBuilder().withDate(dateToday).withStatus("interview").build();
        Internship offered = new InternshipBuilder().withDate(dateToday).withStatus("offered").build();
        Internship neww = new InternshipBuilder().withDate(dateToday).withStatus("new").build();
        Internship rejected = new InternshipBuilder().withDate(dateToday).withStatus("rejected").build();
        Internship assessment = new InternshipBuilder().withDate(dateToday).withStatus("assessment").build();
        Internship applied = new InternshipBuilder().withDate(dateToday).withStatus("applied").build();
        Internship accepted = new InternshipBuilder().withStatus("accepted").build();

        assertTrue(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(interview));
        assertTrue(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(offered));
        assertTrue(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(neww));
        assertTrue(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(assessment));
        assertFalse(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(rejected));
        assertFalse(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(applied));
        assertFalse(PREDICATE_SHOW_UPCOMING_INTERNSHIPS.test(accepted));


    }

    @Test
    public void multipleUpcomingInternships() {
        UpcomingCommand command = new UpcomingCommand();
        expectedModel.updateFilteredInternshipList(PREDICATE_SHOW_UPCOMING_INTERNSHIPS);
        assertEquals(Arrays.asList(FOODPANDA, GOLDMAN, SUPERCELLGAMES), expectedModel.getFilteredInternshipList());
        assertCommandSuccess(command, model, UpcomingCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
