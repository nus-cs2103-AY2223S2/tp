package seedu.internship.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_END;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_START;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.internship.commons.util.CollectionUtil;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ResultType;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;

/**
 * Finds events from the event catalogue based on parameters given.
 */
public class EventFindCommand extends EventCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = EventCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Finds events from the catalogue based on parameters given.\n"
            + "Parameters: [" + PREFIX_EVENT_NAME + "NAME] "
            + "[" + PREFIX_EVENT_START + "START] "
            + "[" + PREFIX_EVENT_END + "END] "
            + "Example: " + COMMAND_WORD + " na/Technical Interview " + "st/10/09/2023 1500 ";

    public static final String MESSAGE_SUCCESS = "Found events : %1$s";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";

    private final FilterEventDescriptor filterEventDescriptor;

    /**
     * Creates an Event Find Command.
     * @param filterEventDescriptor Filter created using descriptors provided.
     */
    public EventFindCommand(FilterEventDescriptor filterEventDescriptor) {
        requireNonNull(filterEventDescriptor);
        this.filterEventDescriptor = filterEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!filterEventDescriptor.isAnyFieldEdited()) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventFindCommand.MESSAGE_USAGE));
        }
        requireNonNull(model);
        Predicate<Event> filterName = unused -> true;
        Predicate<Event> filterStart = unused -> true;
        Predicate<Event> filterEnd = unused -> true;
        if (filterEventDescriptor.getName().isPresent()) {
            filterName = x -> x.getName().name.toLowerCase().contains(filterEventDescriptor.getName()
                    .get().name.toLowerCase());
        }
        if (filterEventDescriptor.getStart().isPresent()) {
            filterStart = x -> x.getStart().equals(filterEventDescriptor.getStart().get());
        }
        if (filterEventDescriptor.getEnd().isPresent()) {
            filterEnd = x -> x.getEnd().equals(filterEventDescriptor.getEnd().get());
        }
        Predicate<Event> finalFilterName = filterName;
        Predicate<Event> finalFilterStart = filterStart;
        Predicate<Event> finalFilterEnd = filterEnd;
        Predicate<Event> filter = x -> finalFilterName.test(x) && finalFilterStart.test(x) && finalFilterEnd.test(x);
        model.updateFilteredEventList(filter);
        model.updateSelectedInternship(null);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredEventList().size()),
                ResultType.FIND_EVENT, model.getFilteredEventList());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventFindCommand)) {
            return false;
        }

        // state check
        EventFindCommand f = (EventFindCommand) other;
        return filterEventDescriptor.equals(f.filterEventDescriptor);
    }

    /**
     * Creates a Filter based on inputs for Event Catalogue.
     */
    public static class FilterEventDescriptor {
        private Name name;
        private Start start;
        private End end;

        public FilterEventDescriptor() {
        }

        /**
         * Returns true if at least one field is filtered.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, start, end);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStart(Start start) {
            this.start = start;
        }

        public Optional<Start> getStart() {
            return Optional.ofNullable(start);
        }

        public void setEnd(End end) {
            this.end = end;
        }

        public Optional<End> getEnd() {
            return Optional.ofNullable(end);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EventFindCommand.FilterEventDescriptor)) {
                return false;
            }

            // state check
            FilterEventDescriptor f = (EventFindCommand.FilterEventDescriptor) other;

            return getName().equals(f.getName())
                    && getStart().equals(f.getStart())
                    && getEnd().equals(f.getEnd());
        }
    }
}


