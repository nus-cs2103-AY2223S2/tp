package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Lesson;

/**
 * Add new lesson to a tutee
 */
public class LearnCommand extends Command {
    public static final String COMMAND_WORD = "learn";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits learning process of the tutee identified "
                + "by the index number used in the last tutee listing.\n"
                + "Example: " + COMMAND_WORD + " 1 l/Rational Number";

    private final Index index;
    private final String lesson;

    /**
     * Creates a LearnCommand for the tutee.
     */
    public LearnCommand(Index tuteeIndex, String lesson) {
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
        Lesson newLessons = lessons.learn(lesson);
        builder.withLessons(newLessons);
        model.setTutee(tuteeToEducate, builder.build());
        return new CommandResult(
            String.format("Edit %s learn %s", tuteeToEducate.getName(), lesson));
    }

}
