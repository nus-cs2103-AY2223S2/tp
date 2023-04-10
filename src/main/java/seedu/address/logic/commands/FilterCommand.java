package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tutee.fields.FieldContainsKeywordsPredicate;

import java.util.Collections;
import java.util.List;

/**
 * Filters and lists all tutees in address book whose field matches
 * any of the argument keywords. Keyword matching is case insensitive.
 */
// Solution adapted from https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/logic/commands/FilterCommand.java
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters through all persons in the tutee managing system, "
            + "showing only the tutees that matches the parameters that have been provided\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_SCHEDULE + "SCHEDULE "
            + PREFIX_STARTTIME + "START TIME "
            + PREFIX_ENDTIME + "END TIME "
            + PREFIX_TAG + "TAG \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_PHONE + "98765432"
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi "
            + PREFIX_SUBJECT + "Math "
            + PREFIX_SCHEDULE + "monday "
            + PREFIX_STARTTIME + "08:30 "
            + PREFIX_ENDTIME + "10:30 "
            + PREFIX_TAG + "friends ";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";

    private final FieldContainsKeywordsPredicate predicate;
    private final FilterTuteeDescription filterTuteeDescription;

    public FilterCommand(FilterTuteeDescription filterTuteeDescription) {
        this.filterTuteeDescription = filterTuteeDescription;
        this.predicate = new FieldContainsKeywordsPredicate(filterTuteeDescription.getNameToFilter(),
                filterTuteeDescription.getPhoneToFilter(), filterTuteeDescription.getEmailToFilter(),
                filterTuteeDescription.getAddressToFilter(), filterTuteeDescription.getSubjectToFilter(),
                filterTuteeDescription.getScheduleToFilter(), filterTuteeDescription.getStartTimeToFilter(),
                filterTuteeDescription.getEndTimeToFilter(), filterTuteeDescription.getTagToFilter());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTuteeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredTuteeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && filterTuteeDescription.equals(((FilterCommand) other).filterTuteeDescription)); // state check
    }

    /**
     * Stores the details to filter the tutee with. Each non-empty field value will replace the
     * corresponding field value of the tutee.
     */
    public static class FilterTuteeDescription {
        public List<String> nameToFilter;
        public String phoneToFilter;
        public String emailToFilter;
        public List<String> addressToFilter;
        public String subjectToFilter;
        public String scheduleToFilter;
        public String startTimeToFilter;
        public String endTimeToFilter;
        public List<String> tagToFilter;

        /**
         * FilterTuteeDescription constructor.
         */
        public FilterTuteeDescription() {
            this.nameToFilter = Collections.emptyList();
            this.phoneToFilter = "";
            this.emailToFilter = "";
            this.addressToFilter = Collections.emptyList();
            this.subjectToFilter = "";
            this.scheduleToFilter = "";
            this.startTimeToFilter = "";
            this.endTimeToFilter = "";
            this.tagToFilter = Collections.emptyList();
        }

        /**
         * Returns true if at least one field is filtered.
         */
        public boolean isAllFieldEmpty() {
            return nameToFilter.isEmpty() && phoneToFilter.isEmpty()
                    && emailToFilter.isEmpty() && addressToFilter.isEmpty()
                    && subjectToFilter.isEmpty() && scheduleToFilter.isEmpty()
                    && startTimeToFilter.isEmpty() && endTimeToFilter.isEmpty()
                    && tagToFilter.isEmpty();
        }

        public void setNameToFilter(List<String> name) {
            nameToFilter = name;
        }

        public List<String> getNameToFilter() {
            return nameToFilter;
        }

        public void setPhoneToFilter(String phone) {
            phoneToFilter = phone;
        }

        public String getPhoneToFilter() {
            return phoneToFilter;
        }

        public void setEmailToFilter(String email) {
            emailToFilter = email;
        }

        public String getEmailToFilter() {
            return emailToFilter;
        }

        public void setAddressToFilter(List<String> address) {
            addressToFilter = address;
        }

        public List<String> getAddressToFilter() {
            return addressToFilter;
        }

        public void setSubjectToFilter(String subject) {
            subjectToFilter = subject;
        }

        public String getSubjectToFilter() {
            return subjectToFilter;
        }

        public void setScheduleToFilter(String schedule) {
            scheduleToFilter = schedule;
        }

        public String getScheduleToFilter() {
            return scheduleToFilter;
        }

        public void setStartTimeToFilter(String startTime) {
            startTimeToFilter = startTime;
        }

        public String getStartTimeToFilter() {
            return startTimeToFilter;
        }

        public void setEndTimeToFilter(String endTime) {
            endTimeToFilter = endTime;
        }

        public String getEndTimeToFilter() {
            return endTimeToFilter;
        }

        public void setTagToFilter(List<String> tag) {
            tagToFilter = tag;
        }

        public List<String> getTagToFilter() {
            return tagToFilter;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterTuteeDescription)) {
                return false;
            }

            // state check
            FilterTuteeDescription f = (FilterTuteeDescription) other;

            return getNameToFilter().equals(f.getNameToFilter())
                    && getPhoneToFilter().equals(f.getPhoneToFilter())
                    && getEmailToFilter().equals(f.getEmailToFilter())
                    && getAddressToFilter().equals(f.getAddressToFilter())
                    && getSubjectToFilter().equals(f.getSubjectToFilter())
                    && getScheduleToFilter().equals(f.getScheduleToFilter())
                    && getStartTimeToFilter().equals(f.getStartTimeToFilter())
                    && getEndTimeToFilter().equals(f.getEndTimeToFilter())
                    && getTagToFilter().equals(f.getTagToFilter());
        }
    }
}
