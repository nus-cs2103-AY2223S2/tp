package seedu.address.logic.commands.reminder;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.reminder.Reminder;


public class AddReminderCommandTest {
    @Test
    public void constructor_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddReminderCommand((Reminder) null));
    }

}
