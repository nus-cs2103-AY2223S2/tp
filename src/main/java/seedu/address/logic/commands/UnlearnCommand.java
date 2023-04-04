package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Lesson;

/**
 * Marks a tutee as absent on a given date
 */
public class UnlearnCommand extends Command {
    public static final String COMMAND_WORD = "unlearn";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits learning process of the tutee identified "
            + "by the index number used in the last tutee listing.\n"
            + "Example: " + COMMAND_WORD + " 1 l/Rational Number";

    private final Index index;
    private final String lesson;

    /**
     * Create an unlearn command for the tutee
     */
    public UnlearnCommand (Index tuteeIndex, String lesson) {
        requireNonNull(tuteeIndex);
        this.index = tuteeIndex;
        this.lesson = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutee> lastShownList = model.getFilteredTuteeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Tutee tuteeToEducate = lastShownList.get(index.getZeroBased());
        TuteeBuilder builder = new TuteeBuilder(tuteeToEducate);
        Lesson lessons = tuteeToEducate.getLessons();
        try {
            Lesson newLessons = lessons.unlearn(lesson);
            builder.withLessons(newLessons);
            model.setTutee(tuteeToEducate, builder.build());
            return new CommandResult(
                    String.format("Edit %s have not learned %s", tuteeToEducate.getName(), lesson));
        } catch (NoSuchElementException e) {
            return new CommandResult(String.format(
                "%s have not learned %s!",
                tuteeToEducate.getName(),
                lesson));
        }
    }
}
