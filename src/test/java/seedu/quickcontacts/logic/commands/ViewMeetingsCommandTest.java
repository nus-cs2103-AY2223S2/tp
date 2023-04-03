package seedu.quickcontacts.logic.commands;

import static seedu.quickcontacts.testutil.TypicalQuickBooks.getTypicalQuickBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewMeetingsCommand.
 */
public class ViewMeetingsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalQuickBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getQuickBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String expected = "Dinner with Alice\n"
                + "Description: Weekly catchup\n"
                + "At: 01/02/2023 19:00\n"
                + "Attendees: [Alice Pauline; Phone: 94351253; Email: alice@example.com; Address: 123, Jurong West Ave "
                + "6, #08-111; Tags: [friends]]\n"
                + "Location: NUS\n\n"
                + ""
                + "Study session with Benson and "
                + "Carl\n"
                + "Description: Study for finals\n"
                + "At: 02/03/2023 15:00\n"
                + "Attendees: [Carl Kurz; "
                + "Phone: 95352563; Email: heinz@example.com; Address: wall street, Benson Meier; Phone: 98765432; "
                + "Email: johnd@example.com; Address: 311, Clementi Ave 2, #02-25; Tags: [owesMoney][friends]]\n"
                + "Location: Central Library\n\n"
                + "Zoom meeting for agenda planning\n"
                + "Description: Plan for "
                + "project work\n"
                + "At: 13/03/2023 12:45\n"
                + "Attendees: [Alice Pauline; Phone: 94351253; Email: "
                + "alice@example.com; Address: 123, Jurong West Ave 6, #08-111; Tags: [friends]]\n"
                + "Location: "
                + "https://us02web.zoom.us/j/99999999999?pwd=ABCdEfGHiJkYkRuYW5WTLmNopQrSt12\n\n";
        CommandTestUtil.assertCommandSuccess(new ViewMeetingsCommand(), model, expected, expectedModel);
    }
}
