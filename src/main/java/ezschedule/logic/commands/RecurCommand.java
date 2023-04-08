package ezschedule.logic.commands;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_EVERY;
import static java.util.Objects.requireNonNull;

import java.util.List;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.RecurFactor;

/**
 * Recurs an existing {@code Event} in the {@code Scheduler}.
 */
public class RecurCommand extends Command {

    public static final String COMMAND_WORD = "recur";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Recurs event in the scheduler by "
            + "the index number used in the displayed event list. "
            + "\nParameters: INDEX (must exist)\n "
            + PREFIX_DATE + "ending date\n "
            + PREFIX_EVERY + "{day, week, month} "
            + "\nExample: \n" + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "2024-02-20 "
            + PREFIX_EVERY + "month ";

    public static final String MESSAGE_SUCCESS = "Recurring event added: %1$s";
    public static final String MESSAGE_FAILURE_PAST_DATE = "End date indicated is in the past\n"
            + "Ensure end date of recurrence has not past.";
    public static final String MESSAGE_RECUR_FACTOR_CAP = "Recur factor '%s' may only be used for the next %d %ss";
    public static final String MESSAGE_FAILURE_DAY_NOT_EXIST_MONTH = "Unable to recur by month.\n"
            + "%s does not have day %d";
    public static final String MESSAGE_FAILURE_EVENT_CLASH = "Unable to recur.\n"
            + "%d %s has a clashing event.";

    private final Index index;
    private final Date endDate;
    private final RecurFactor factor;

    /**
     * Creates an {@code AddCommand} to add the specified {@code Event}
     */
    public RecurCommand(Index index, Date endDate, RecurFactor factor) {
        requireNonNull(index);
        requireNonNull(endDate);
        requireNonNull(factor);
        this.index = index;
        this.endDate = endDate;
        this.factor = factor;
    }

    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, index.getZeroBased() + 1));
        }

        if (endDate.isPastDate()) {
            throw new CommandException(MESSAGE_FAILURE_PAST_DATE);
        }

        Event eventToRecur = lastShownList.get(index.getZeroBased());
        model.clearRecent();

        switch (factor.toString()) {

        case "day":
            addEventPerDay(model, eventToRecur);
            break;

        case "week":
            addEventPerWeek(model, eventToRecur);
            break;

        case "month":
            addEventPerMonth(model, eventToRecur);
            break;

        default:
            break;
        }
        model.addRecentCommand(this);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToRecur));
    }

    /**
     * Adds new events to every day until endDate.
     *
     * @param model        model to add
     * @param eventToRecur event to recur in the model
     */
    public void addEventPerDay(Model model, Event eventToRecur) throws CommandException {

        String month;
        int maxDaysInMonth = 30;

        Date baseDate = eventToRecur.getDate();
        int daysDiff = (int) baseDate.getDaysBetween(endDate.date);

        Event[] eventsToAdd = new Event[daysDiff];

        if (daysDiff > maxDaysInMonth) {
            throw new CommandException(String.format(MESSAGE_RECUR_FACTOR_CAP,
                    factor.toString(), maxDaysInMonth, factor));
        }

        Date newDate = new Date(eventToRecur.getDate().date.plusDays(1).toString());
        Event nextEventToRecur =
                new Event(eventToRecur.getName(), newDate,
                        eventToRecur.getStartTime(), eventToRecur.getEndTime());

        for (int i = 0; i < daysDiff; i++) {

            month = intToStringMonth(newDate.getMonth());

            //for undo
            model.addRecentEvent(nextEventToRecur);

            if (model.hasEventAtTime(nextEventToRecur)) {
                throw new CommandException(String.format(MESSAGE_FAILURE_EVENT_CLASH, newDate.getDay(), month));
            }

            eventsToAdd[i] = nextEventToRecur;
            newDate = new Date(nextEventToRecur.getDate().date.plusDays(1).toString());
            nextEventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());
        }

        // if successful, add all events
        for (int i = 0; i < daysDiff; i++) {
            model.addEvent(eventsToAdd[i]);
        }
    }

    /**
     * Adds new events to every week until endDate.
     *
     * @param model        model to add
     * @param eventToRecur event to recur in the model
     */
    public void addEventPerWeek(Model model, Event eventToRecur) throws CommandException {

        String month;
        int daysPerWeek = 7;
        int maxWeeksInYear = 52;

        Date baseDate = eventToRecur.getDate();
        int weeksDiff = (int) baseDate.getDaysBetween(endDate.date) / daysPerWeek;

        Event[] eventsToAdd = new Event[weeksDiff];

        Date newDate = new Date(eventToRecur.getDate().date.plusWeeks(1).toString());
        Event nextEventToRecur =
                new Event(eventToRecur.getName(), newDate,
                        eventToRecur.getStartTime(), eventToRecur.getEndTime());

        if (weeksDiff > maxWeeksInYear) {
            throw new CommandException(String.format(MESSAGE_RECUR_FACTOR_CAP,
                    factor.toString(), maxWeeksInYear, factor));
        }

        for (int i = 0; i < weeksDiff; i++) {

            month = intToStringMonth(newDate.getMonth());

            // for undo
            model.addRecentEvent(nextEventToRecur);

            if (model.hasEventAtTime(nextEventToRecur)) {
                throw new CommandException(String.format(MESSAGE_FAILURE_EVENT_CLASH, newDate.getDay(), month));
            }

            eventsToAdd[i] = nextEventToRecur;
            newDate = new Date(nextEventToRecur.getDate().date.plusWeeks(1).toString());
            nextEventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());
        }

        // if successful, add all events
        for (int i = 0; i < weeksDiff; i++) {
            model.addEvent(eventsToAdd[i]);
        }
    }

    /**
     * Adds new events to every month until endDate.
     *
     * @param model        model to add
     * @param eventToRecur event to recur in the model
     */
    public void addEventPerMonth(Model model, Event eventToRecur) throws CommandException {

        String month;
        int maxMonthInYear = 12;

        Date baseDate = eventToRecur.getDate();
        int dayOfMonth = baseDate.getDay();
        int monthsDiff = (int) baseDate.getMonthsBetween(endDate.date);

        Event[] eventsToAdd = new Event[monthsDiff];

        Date newDate = new Date(eventToRecur.getDate().date.plusMonths(1).toString());
        Event nextEventToRecur =
                new Event(eventToRecur.getName(), newDate,
                        eventToRecur.getStartTime(), eventToRecur.getEndTime());

        if (monthsDiff > maxMonthInYear) {
            throw new CommandException(String.format(MESSAGE_RECUR_FACTOR_CAP,
                    factor.toString(), maxMonthInYear, factor));
        }

        for (int i = 0; i < monthsDiff; i++) {

            month = intToStringMonth(newDate.getMonth());

            // for undo
            model.addRecentEvent(nextEventToRecur);

            // if curr day does not exist for next month
            if (newDate.getDay() != dayOfMonth) {
                throw new CommandException(String.format(MESSAGE_FAILURE_DAY_NOT_EXIST_MONTH, month, dayOfMonth));
            } else {

                if (model.hasEventAtTime(nextEventToRecur)) {
                    throw new CommandException(String.format(MESSAGE_FAILURE_EVENT_CLASH, newDate.getDay(), month));
                }

                eventsToAdd[i] = nextEventToRecur;
                newDate = new Date(nextEventToRecur.getDate().date.plusMonths(1).toString());
                nextEventToRecur =
                        new Event(eventToRecur.getName(), newDate,
                                eventToRecur.getStartTime(), eventToRecur.getEndTime());
            }
        }

        // if successful, add all events
        for (int i = 0; i < monthsDiff; i++) {
            model.addEvent(eventsToAdd[i]);
        }
    }

    /**
     * Returns a String of month with the given month int.
     *
     * @param month int of month to convert
     * @return String name of month
     */
    public String intToStringMonth(int month) {
        switch (month) {

        case 1:
            return "January";

        case 2:
            return "February";

        case 3:
            return "March";

        case 4:
            return "April";

        case 5:
            return "May";

        case 6:
            return "June";

        case 7:
            return "July";

        case 8:
            return "August";

        case 9:
            return "September";

        case 10:
            return "October";

        case 11:
            return "November";

        case 12:
            return "December";

        default:
            return "No such month";
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecurCommand)) {
            return false;
        }

        // state check
        RecurCommand e = (RecurCommand) other;
        return index.equals(e.index)
                && endDate.equals(e.endDate)
                && factor.equals(e.factor);
    }
}
