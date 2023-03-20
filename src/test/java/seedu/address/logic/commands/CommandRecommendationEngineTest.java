package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;

public class CommandRecommendationEngineTest {
    CommandRecommendationEngine commandRecommendationEngine = new CommandRecommendationEngine();

    @Test
    public void recommendCommand_null_failure() {
        assertThrows(AssertionError.class, () -> commandRecommendationEngine.recommendCommand(null));
    }

    @Test
    public void suggestVolunteerCommand_validCommand_success() {
        String expected = "add_volunteer n/<name> vnr/<nric> a/<address> p/<phone> "
                + "e/<email> t/<tag> re/<region> ag/<age> dr/<start_date,end_date>";
        try {
            String actual = commandRecommendationEngine.recommendCommand("add_volunteer");
            assertEquals(expected, actual);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void suggestVolunteerCommandArgument_validCommand_success() {
        String expected = "add_volunteer n/Zon vnr/<nric> a/<address> p/<phone> "
                + "e/<email> t/<tag> re/<region> ag/<age> dr/<start_date,end_date>";
        try {
            String actual = commandRecommendationEngine.recommendCommand("add_volunteer n/Zon");
            assertEquals(expected, actual);
        } catch (CommandException e) {
            fail();
        }
    }


}
