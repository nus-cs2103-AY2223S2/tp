package seedu.connectus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.connectus.commons.core.Messages.MESSAGE_PERSONS_UPCOMING_BIRTHDAY;
import static seedu.connectus.testutil.TypicalPersons.BENSON;
import static seedu.connectus.testutil.TypicalPersons.getTypicalConnectUs;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.Model;
import seedu.connectus.model.ModelManager;
import seedu.connectus.model.UserPrefs;
import seedu.connectus.model.person.Birthday;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.RecentBirthdayPredicate;



public class UpcomingBirthdayTest {

    @Test
    // returns 0 people when the original list is empty
    public void execute_empty_upcoming() {
        assertWithPeople(List.of(), List.of());
    }

    @Test
    public void execute_tomorrow_upcoming() {
        Person benson = BENSON;
        benson.setBirthday(new Birthday(LocalDate.now().plusDays(1).withYear(2017)));

        assertWithPeople(List.of(benson), List.of(benson));
    }

    @Test
    public void execute_notclose_upcoming() {
        Person benson = BENSON;
        benson.setBirthday(new Birthday(LocalDate.now().plusDays(100).withYear(2017)));

        assertWithPeople(List.of(benson), List.of());
    }

    private void assertWithPeople(
            List<Person> people,
            List<Person> expectedPeople) {
        Model model = new ModelManager(getTypicalConnectUs(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalConnectUs(), new UserPrefs());
        ConnectUs connectUs = new ConnectUs();
        for (Person person : people) {
            connectUs.addPerson(person);
        }
        model.setConnectUs(connectUs);
        expectedModel.setConnectUs(connectUs);
        UpcomingBirthdayCommand upcomingBirthdayCommand = new UpcomingBirthdayCommand();

        String expectedMessage = String.format(MESSAGE_PERSONS_UPCOMING_BIRTHDAY, expectedPeople.size());

        String message = upcomingBirthdayCommand.execute(model).getFeedbackToUser();
        assertEquals(message, expectedMessage);

        expectedModel.updateFilteredPersonList(new RecentBirthdayPredicate());
        assertEquals(expectedModel.getFilteredPersonList(), expectedPeople);
    }

}
