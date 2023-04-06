package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Attendance;

/**
 * Marks a tutee as absent on a given date
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final Index index;
    private final List<LocalDate> dates;

    /**
     * Create an unmark command for the tutee and the specified index and date
     */
    public UnmarkCommand(Index index, List<LocalDate> dates) {
        requireNonNull(dates);
        this.index = index;
        this.dates = dates;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Tutee toMarkAttendance = model.getFilteredTuteeList().get(index.getZeroBased());
        TuteeBuilder modified = new TuteeBuilder(toMarkAttendance);
        Attendance attendance = toMarkAttendance.getAttendance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MarkCommand.EXPECTED_DATE_FORMAT);

        List<LocalDate> invalidDates = new ArrayList<>();
        List<LocalDate> validDates = new ArrayList<>();

        for (LocalDate date : dates) {
            try {
                attendance = attendance.unmarkAttendance(date);
                validDates.add(date);
            } catch (NoSuchElementException e) {
                invalidDates.add(date);
            }
        }

        modified.withAttendance(attendance);
        model.setTutee(toMarkAttendance, modified.build());

        StringJoiner joiner = new StringJoiner("\n");

        if (validDates.size() > 0) {
            joiner.add(validDates.stream()
                .map(formatter::format)
                .collect(Collectors.joining(
                    ", ",
                    String.format("Marked %s as absent for the following dates: ", toMarkAttendance.getName()),
                    ""
                )));
        }

        if (invalidDates.size() > 0) {
            joiner.add(invalidDates.stream()
                .map(formatter::format)
                .collect(Collectors.joining(
                    ", ",
                    String.format("%s was already marked absent for the following dates: ", toMarkAttendance.getName()),
                    ""
                )));
        }

        return new CommandResult(joiner.toString());
    }
}
