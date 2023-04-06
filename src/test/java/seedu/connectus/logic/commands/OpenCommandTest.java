package seedu.connectus.logic.commands;

import static seedu.connectus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.connectus.testutil.TypicalPersons.getTypicalConnectUs;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.model.Model;
import seedu.connectus.model.ModelManager;
import seedu.connectus.model.UserPrefs;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.socialmedia.Telegram;

public class OpenCommandTest {

    private Model model = new ModelManager(getTypicalConnectUs(), new UserPrefs());
    @Test
    public void execute_PlatformNotExist_throwsCommandException() {
        Person nameOnlyPerson = new Person(new Name("unique name"));
        Person personToFiddle = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setPerson(personToFiddle, nameOnlyPerson);

        var command = new OpenCommand(INDEX_FIRST_PERSON, Telegram::getUserLink);

        assertCommandFailure(command, model, Messages.MESSAGE_PERSON_FIELD_NOT_PRESENT);
    }
}
