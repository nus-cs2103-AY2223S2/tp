package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalTuteeManagingSystem;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.UnmarkCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.testutil.TuteeTestBuilder;


public class UnmarkCommandTest {
    @Test
    public void execute_withGivenDate() throws ParseException {
        Model expectedModel = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
        expectedModel.addTutee(AMY);

        Tutee amy = new TuteeTestBuilder(AMY)
            .withAttendance(new Attendance(Set.of(LocalDate.of(2020, 03, 04))))
            .build();

        Model model = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
        model.addTutee(amy);

        UnmarkCommandParser parser = new UnmarkCommandParser();
        UnmarkCommand cmd = parser.parse("0 2020-03-04");

        assertCommandSuccess(
            cmd,
            model,
            "Marked" + AMY.getName() + "'s attendance for 2020-03-04 as absent",
            expectedModel
        );
    }

    @Test
    public void execute_withGivenDateAndTuteeAlreadyAbsent() throws ParseException {
        Model expectedModel = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
        expectedModel.addTutee(AMY);

        Model model = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
        model.addTutee(AMY);

        UnmarkCommandParser parser = new UnmarkCommandParser();
        UnmarkCommand cmd = parser.parse("0 2020-03-04");

        assertCommandSuccess(
            cmd,
            model,
            AMY.getName() + "was not present on 2020-03-04!",
            expectedModel
        );
    }

    @Test
    public void execute_withInvalidDateFormat() throws ParseException {
        UnmarkCommandParser parser = new UnmarkCommandParser();
        assertThrows(ParseException.class, () -> parser.parse("0 2020/03/04"));
    }
}
