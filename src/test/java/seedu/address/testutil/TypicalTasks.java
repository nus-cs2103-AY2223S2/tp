package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Repository;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Tasks} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task SEND_EMAIL_TO_CLIENT = new TaskBuilder().withSubject("Send email to client")
            .withContent("Email Mr. Chan about the recent updates on Project X.").build();
    public static final Task COMPLETE_SLIDES = new TaskBuilder().withSubject("Complete slides")
            .withContent("Finish up slides for meeting on 1st March.").build();
    public static final Task STOCK_PANTRY = new TaskBuilder().withSubject("Stock pantry")
            .withContent("Purchase food for the pantry on level 6.").build();
    public static final Task CHECK_BALANCES = new TaskBuilder().withSubject("Check balances")
            .withContent("Check all bank statements.").build();
    public static final Task DEPOSIT_CASH = new TaskBuilder().withSubject("Deposit cash")
            .withContent("Deposit cash from last week at the bank.").build();
    public static final Task SET_APPOINTMENT = new TaskBuilder().withSubject("Set appointment")
            .withContent("Set up meeting with Mdm Tay for next Monday").withStatus(true).build();
    public static final Task DATA_ENTRY = new TaskBuilder().withSubject("Data entry")
            .withContent("Update spreadsheet with client information.").withStatus(true).build();

    private TypicalTasks() {}

    /**
     * Returns a {@code Repository} with all the typical tasks.
     */
    public static Repository<Task> getTypicalTaskRepository() {
        Repository<Task> tl = new Repository<>();
        for (Task task : getTypicalTasks()) {
            tl.addItem(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(SEND_EMAIL_TO_CLIENT, COMPLETE_SLIDES, STOCK_PANTRY,
                CHECK_BALANCES, DEPOSIT_CASH, SET_APPOINTMENT, DATA_ENTRY));
    }

}
