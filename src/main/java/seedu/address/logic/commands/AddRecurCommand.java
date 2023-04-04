package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddConsultationCommand.MESSAGE_DUPLICATE_CONSULTATION;
import static seedu.address.logic.commands.AddLabCommand.MESSAGE_DUPLICATE_LAB;
import static seedu.address.logic.commands.AddTutorialCommand.MESSAGE_DUPLICATE_TUTORIAL;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Adds a recurring event to the events that the teaching assistant would like to schedule.
 */
public class AddRecurCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE_NO_STUDENT_PREFIX = "Cannot have prefixes related to students!";
    public static final String MESSAGE_USAGE_MISSING_RECUR_PREFIX = "Please enter the Recur/ prefix";
    public static final String MESSAGE_USAGE_MISSING_EVENT_PREFIX =
            "Please enter the Tutorial/ or Consultation/ or Lab/ prefix";

    public static final String MESSAGE_SUCCESS = "New recurring event added: %1$s";

    private final Event toAdd;
    private final int count;
    private final boolean lab;
    private final boolean tutorial;
    private final boolean consultation;

    /**
     * Creates an AddRecur to add the specified {@code Recur}.
     *
     * @param recurring         the recurring event.
     * @param lab               if the event is a lab.
     * @param tutorial          if the event is a tutorial.
     * @param consultation      if the event is a consultation.
     * @param count             the number of recurrences of the event.
     */
    public AddRecurCommand(Event recurring, boolean lab, boolean tutorial, boolean consultation, int count) {
        requireNonNull(recurring);
        toAdd = recurring;
        this.count = count;
        this.lab = lab;
        this.tutorial = tutorial;
        this.consultation = consultation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (tutorial && model.hasTutorial((Tutorial) toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        if (lab && model.hasLab((Lab) toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LAB);
        }

        if (consultation && model.hasConsultation((Consultation) toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        addEvents(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, false, true);
    }

    /**
     * Adds valid recurring events to the list of events.
     *
     * @param model the model too add the events to.
     */
    void addEvents(Model model) {
        Event newEvent = toAdd.copy();
        for (int i = 0; i < count; i++) {
            LocalDateTime[] range = new LocalDateTime[2];
            if (lab) {
                model.addLab((Lab) newEvent);
                range[0] = newEvent.getDate();
                range[1] = newEvent.getDate().plusHours(2);
            } else if (tutorial) {
                model.addTutorial((Tutorial) newEvent);
                range[0] = newEvent.getDate();
                range[1] = newEvent.getDate().plusHours(1);
            } else {
                model.addConsultation((Consultation) newEvent);
                range[0] = newEvent.getDate();
                range[1] = newEvent.getDate().plusHours(1);
            }
            if (!ParserUtil.MASTER_TIME.contains(range)) {
                ParserUtil.MASTER_TIME.add(range);
            }
            newEvent = newEvent.copy();
            LocalDateTime currDate = newEvent.getDate();
            LocalDateTime newDate = currDate.plus(1, ChronoUnit.WEEKS);
            newEvent.changeDate(newDate);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecurCommand // instanceof handles nulls
                && toAdd.equals(((AddRecurCommand) other).toAdd));
    }
}
