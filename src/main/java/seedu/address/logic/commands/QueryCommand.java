package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;

/**
 * Command for displaying the attendance data of a student
 */
public class QueryCommand extends Command {
    public static final String COMMAND_WORD = "query";
    public static final String EXPECTED_DATE_FORMAT = "yyyy-MM-dd";

    private final Optional<LocalDate> searchDate;
    private final Index index;

    /**
     * The command will return whether the tutee at the given index was
     * present on the given date
     */
    public QueryCommand(Index index, LocalDate date) {
        this.searchDate = Optional.of(date);
        this.index = index;
    }

    /**
     * The command will list all of the tutee's attendance information
     */
    public QueryCommand(Index index) {
        this.searchDate = Optional.empty();
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutee> lastShownList = model.getFilteredTuteeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Tutee tutee = lastShownList.get(index.getZeroBased());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EXPECTED_DATE_FORMAT);

        String output;
        if (searchDate.isEmpty()) {
            output = tutee.getAttendance()
                .stream()
                .sorted()
                .map(formatter::format)
                .collect(Collectors.joining(
                    ", ",
                    String.format("%s was present on the following dates: ", tutee.getName().toString()),
                    ""
                ));
        } else {
            LocalDate date = searchDate.get();
            output = String.format(
                "%s was %ssent on %s",
                tutee.getName().toString(),
                tutee.getAttendance().didAttend(date) ? "pre" : "ab",
                formatter.format(date)
            );
        }

        return new CommandResult(output);
    }
}
