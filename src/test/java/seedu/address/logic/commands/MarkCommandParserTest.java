package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalTuteeManagingSystem;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.MarkCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.testutil.TuteeTestBuilder;

class MarkCommandParserTest {

    @Test
    public void execute_withGivenDate() throws ParseException {
        Tutee amy = new TuteeTestBuilder(AMY)
            .withAttendance(new Attendance(Set.of(LocalDate.of(2020, 03, 04))))
            .build();

        Model model = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
        expectedModel.addTutee(amy);

        MarkCommandParser parser = new MarkCommandParser();
        MarkCommand cmd = parser.parse("0 2020-03-04");

        assertCommandSuccess(
            cmd,
            model,
            "Marked" + AMY.getName() + "'s attendance for 2020-03-04 as present",
            expectedModel
        );
    }

    // TODO Add this test that needs LocalDate.now() to be mocked
    // @Test
    // public void execute_withoutGivenDate() throws ParseException {
    //     Tutee amy = new TuteeTestBuilder(AMY)
    //         .withAttendance(new Attendance(Set.of(LocalDate.of(2020, 03, 04))))
    //         .build();

    //     Model model = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
    //     Model expectedModel = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
    //     expectedModel.addTutee(amy);

    //     MarkCommandParser parser = new MarkCommandParser();
    //     MarkCommand cmd = parser.parse("0");

    //     assertCommandSuccess(
    //         cmd,
    //         model,
    //         "Expected attendance for " + amy.getName() + " to have been marked as present for 2020-03-04",
    //         expectedModel
    //     );
    // }
}
