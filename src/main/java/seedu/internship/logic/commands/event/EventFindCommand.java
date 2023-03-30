package seedu.internship.logic.commands.event;

import seedu.internship.commons.util.CollectionUtil;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.commands.ResultType;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.event.*;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.ModelManager.*;

import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.*;

public class EventFindCommand extends EventCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = EventCommand.COMMAND_WORD + COMMAND_WORD
            + ": Finds internships from the catalogue based on predicates provided by the user.\n"
            + "Parameters: [" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "Example: " + COMMAND_WORD + " p/ Software Engineer" + "c/ Grab" ;

    public static final String MESSAGE_SUCCESS = "Found events : %1$s";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";


    private final FilterEventDescriptor filterEventDescriptor;

    public EventFindCommand(FilterEventDescriptor filterEventDescriptor) {
        requireNonNull(filterEventDescriptor);
        this.filterEventDescriptor = filterEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
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
        if (!(other instanceof FindCommand)) {
            return false;
        }

        // state check
        EventFindCommand f = (EventFindCommand) other;
        return filterEventDescriptor.equals(f.filterEventDescriptor);
    }


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
            if (!(other instanceof FindCommand.FilterInternshipDescriptor)) {
                return false;
            }

            // state check
            FilterEventDescriptor f = (FilterEventDescriptor) other;

            return getName().equals(f.getName())
                    && getStart().equals(f.getStart())
                    && getEnd().equals(f.getEnd());
        }
    }
}


