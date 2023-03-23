package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Attendance;

/**
 * Mark a tutee as present on a given date
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String EXPECTED_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the tutee identified "
                + "by the index number used in the last tutee listing as present on a given date.\n"
                + "If no date is provided for this command, then the current date will be used\n"
                + "Example: " + COMMAND_WORD + " 1 2020-01-02";

    private final Index index;
    private final List<LocalDate> dates;

    /**
     * Creates a MarkCommand for the tutee at the specified index and date.
     */
    public MarkCommand(Index tuteeIndex, List<LocalDate> date) {
        requireNonNull(tuteeIndex);
        this.index = tuteeIndex;
        this.dates = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutee> lastShownList = model.getFilteredTuteeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Tutee toMarkAttendance = lastShownList.get(index.getZeroBased());
        TuteeBuilder modified = new TuteeBuilder(toMarkAttendance);
        Attendance attendance = toMarkAttendance.getAttendance();

        for (LocalDate date : dates) {
            attendance = attendance.markAttendance(date);
        }
        modified.withAttendance(attendance);
        model.setTutee(toMarkAttendance, modified.build());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EXPECTED_DATE_FORMAT);
        return new CommandResult(
            dates.stream()
                .map(formatter::format)
                .collect(Collectors.joining(
                    ", ",
                    String.format(
                        "Marked %s's attendance as present for the following dates: ",
                        toMarkAttendance.getName()
                    ),
                    ""
                )
            )
        );
    }
}
