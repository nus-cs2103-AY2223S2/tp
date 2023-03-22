package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemindCommand.
 */
public class RemindCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    }

    @Test
    public void execute_remind_noPersonFound() {

        Person personToRemind = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder personInList = new PersonBuilder(personToRemind);

        LocalDateTime currentDate = LocalDateTime.now();
        //Set the interview time to four days from now
        LocalDateTime newDate = currentDate.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String interviewTime = newDate.format(formatter);

        Person remindPerson = personInList
                .withStatus(String.valueOf(Status.SHORTLISTED))
                .withInterviewDateTime(interviewTime)
                .build();

        RemindCommand remindCommand = new RemindCommand();

        expectedModel.setPerson(personToRemind, remindPerson);
        expectedModel.updateFilteredPersonList(RemindCommand.INTERVIEW_IN_THREE_DAYS_PREDICATE);

        String expectedMessage = RemindCommand.MESSAGE_SUCCESS_FORMAT_WITHOUT_APPLICANTS;
        CommandResult result = remindCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);
        assertEquals(expectedResult, result);

    }

    @Test
    public void execute_remind_showsEverything() {
        Person personToRemind = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getOneBased());
        PersonBuilder personInList = new PersonBuilder(personToRemind);

        LocalDateTime currentDate = LocalDateTime.now();
        //Set the interview date to within three days
        LocalDateTime newDate = currentDate.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String interviewTime = newDate.format(formatter);

        Person remindPerson = personInList
                .withStatus(String.valueOf(Status.SHORTLISTED))
                .withInterviewDateTime(interviewTime)
                .build();

        RemindCommand remindCommand = new RemindCommand();

        model.setPerson(personToRemind, remindPerson);
        model.updateFilteredPersonList(RemindCommand.INTERVIEW_IN_THREE_DAYS_PREDICATE);

        String expectedMessage = RemindCommand.MESSAGE_SUCCESS_FORMAT_WITH_APPLICANTS;

        CommandResult result = remindCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);
        assertEquals(expectedResult, result);
    }
}
