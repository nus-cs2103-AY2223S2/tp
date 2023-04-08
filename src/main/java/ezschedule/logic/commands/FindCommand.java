package ezschedule.logic.commands;

import static ezschedule.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import ezschedule.model.Model;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.EventContainsKeywordsPredicate;
import ezschedule.model.event.EventMatchesDatePredicate;
import ezschedule.model.event.EventMatchesKeywordsAndDatePredicate;
import ezschedule.model.event.Name;

/**
 * Finds and lists all {@code Event} in {@code Scheduler} whose {@code Name} is partially matched to the given name
 * or {@code Date} is matched to the given date.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds events based on name and/or date "
            + "and displays them as a list with index number. \n"
            + "The name can be partially matched, while the date has to match \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + "\nExample: " + COMMAND_WORD + " "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "basketball court "
            + PREFIX_DATE + "2023-01-01 ";

    private final FindEventDescriptor findEventDescriptor;

    public FindCommand(FindEventDescriptor findEventDescriptor) {
        this.findEventDescriptor = findEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Event> predicate;
        if (findEventDescriptor.getName().isPresent() && findEventDescriptor.getDate().isPresent()) {
            String[] names = findEventDescriptor.getName().get().toString().split("\\s+");
            Date date = findEventDescriptor.getDate().get();
            predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList(names), date);

        } else if (findEventDescriptor.getName().isPresent()) {
            String[] names = findEventDescriptor.getName().get().toString().split("\\s+");
            predicate = new EventContainsKeywordsPredicate(Arrays.asList(names));

        } else {
            Date date = findEventDescriptor.getDate().get();
            predicate = new EventMatchesDatePredicate(date);
        }
        model.updateFilteredEventList(predicate);
        model.updateFindEventList(predicate);

        return new CommandResult(
                String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && findEventDescriptor.equals(((FindCommand) other).findEventDescriptor)); // state check
    }

    /**
     * Stores the details to find the event with.
     * Each non-empty field value will replace the corresponding field value of the event.
     */
    public static class FindEventDescriptor {

        private Name name;
        private Date date;

        public FindEventDescriptor() {}

        /**
         * Copy constructor.
         */
        public FindEventDescriptor(FindEventDescriptor toCopy) {
            setName(toCopy.name);
            setDate(toCopy.date);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindEventDescriptor)) {
                return false;
            }

            // state check
            FindEventDescriptor e = (FindEventDescriptor) other;

            return getName().equals(e.getName())
                    && getDate().equals(e.getDate());
        }
    }
}
