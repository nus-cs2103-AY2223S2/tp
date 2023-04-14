package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_FILTERED_OVERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATOON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FieldContainsPartialKeywordsPredicate;

/**
 * Lists all persons in the address book, filtering out persons whose fields do
 * not contain the given filters. Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters through all persons in the address "
            + "book, removing persons whose fields fail to contain the specified keywords (case-insensitive)\n"
            + "Parameters: "
            + "[" + PREFIX_RANK + "RANK] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_UNIT + "UNIT] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_PLATOON + "PLATOON] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RANK + "3SG "
            + PREFIX_NAME + "jo "
            + PREFIX_UNIT + "6SIR "
            + PREFIX_COMPANY + "Alpha "
            + PREFIX_PLATOON + "2 "
            + PREFIX_PHONE + "98 "
            + PREFIX_EMAIL + "example.com "
            + PREFIX_ADDRESS + "clementi "
            + PREFIX_TAG + "2ic "
            + PREFIX_TAG + "logistics ";
    public static final String MESSAGE_NO_FIELD_GIVEN = "At least one field must be provided to filter.";
    public static final String MESSAGE_EMPTY_FIELD = "Field values should not be blank";
    private final FilterDescriptor filterDescriptor;
    private final FieldContainsPartialKeywordsPredicate predicate;

    /**
     * @param filterDescriptor descriptor containing details used to filter
     */
    public FilterCommand(FilterDescriptor filterDescriptor) {
        requireNonNull(filterDescriptor);

        this.filterDescriptor = filterDescriptor;
        this.predicate = createFilterPredicate(filterDescriptor);

    }

    /**
     * Creates and returns a {@code FieldContainsKeywordsPredicatePartial} object
     * with the field values of {@code filterDescriptor}.
     */
    private static FieldContainsPartialKeywordsPredicate createFilterPredicate(
            FilterDescriptor filterDescriptor) {
        return new FieldContainsPartialKeywordsPredicate(
                filterDescriptor.rankValue,
                filterDescriptor.nameValue,
                filterDescriptor.unitValue,
                filterDescriptor.companyValue,
                filterDescriptor.platoonValue,
                filterDescriptor.phoneValue,
                filterDescriptor.emailValue,
                filterDescriptor.addressValue,
                filterDescriptor.tagValues.stream().collect(Collectors.toList()));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int originalCount = model.getAddressBook().getPersonList().size();
        model.updateFilteredPersonList(predicate);
        int currentCount = model.getFilteredPersonList().size();
        int hiddenCount = originalCount - currentCount;
        return new CommandResult(String.format(
                MESSAGE_PERSONS_FILTERED_OVERVIEW, currentCount, hiddenCount));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand f = (FilterCommand) other;
        return filterDescriptor.equals(f.filterDescriptor);
    }

    /**
     * Stores the details to filter with. Each non-empty field value will be used as
     * a filter, removing contacts that do not match.
     */
    public static class FilterDescriptor {
        private static final String BLANK = "";
        private String rankValue;
        private String nameValue;
        private String unitValue;
        private String companyValue;
        private String platoonValue;
        private String phoneValue;
        private String emailValue;
        private String addressValue;
        private List<String> tagValues;

        /**
         * Constructs default FilterDescriptor that is used to contain details of a
         * filter. Is created with blanks in all of its fields and an empty set for the
         * set of tag values.
         */
        public FilterDescriptor() {
            rankValue = BLANK;
            nameValue = BLANK;
            unitValue = BLANK;
            companyValue = BLANK;
            platoonValue = BLANK;
            phoneValue = BLANK;
            emailValue = BLANK;
            addressValue = BLANK;
            tagValues = Collections.emptyList();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean hasNonEmptyField() {
            return (CollectionUtil.isAnyNotBlank(
                    rankValue,
                    nameValue,
                    unitValue,
                    companyValue,
                    platoonValue,
                    phoneValue,
                    emailValue,
                    addressValue) || !(tagValues.isEmpty()));
        }

        public String getRankValue() {
            return rankValue;
        }

        public void setRankValue(String rankValue) {
            requireNonNull(rankValue);
            this.rankValue = rankValue;
        }

        public String getNameValue() {
            return nameValue;
        }

        public void setNameValue(String nameValue) {
            requireNonNull(nameValue);
            this.nameValue = nameValue;
        }

        public String getUnitValue() {
            return unitValue;
        }

        public void setUnitValue(String unitValue) {
            requireNonNull(unitValue);
            this.unitValue = unitValue;
        }

        public String getCompanyValue() {
            return companyValue;
        }

        public void setCompanyValue(String companyValue) {
            requireNonNull(companyValue);
            this.companyValue = companyValue;
        }

        public String getPlatoonValue() {
            return platoonValue;
        }

        public void setPlatoonValue(String platoonValue) {
            requireNonNull(platoonValue);
            this.platoonValue = platoonValue;
        }

        public String getPhoneValue() {
            return phoneValue;
        }

        public void setPhoneValue(String phoneValue) {
            requireNonNull(phoneValue);
            this.phoneValue = phoneValue;
        }

        public String getEmailValue() {
            return emailValue;
        }

        public void setEmailValue(String emailValue) {
            requireNonNull(emailValue);
            this.emailValue = emailValue;
        }

        public String getAddressValue() {
            return addressValue;
        }

        public void setAddressValue(String addressValue) {
            requireNonNull(addressValue);
            this.addressValue = addressValue;
        }

        public List<String> getTagValues() {
            return tagValues;
        }

        public void setTagValues(List<String> tagValues) {
            requireNonNull(tagValues);
            this.tagValues = tagValues;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterDescriptor)) {
                return false;
            }

            // state check
            FilterDescriptor e = (FilterDescriptor) other;

            return getRankValue().equals(e.getRankValue())
                    && getNameValue().equals(e.getNameValue())
                    && getUnitValue().equals(e.getUnitValue())
                    && getCompanyValue().equals(e.getCompanyValue())
                    && getPlatoonValue().equals(e.getPlatoonValue())
                    && getPhoneValue().equals(e.getPhoneValue())
                    && getEmailValue().equals(e.getEmailValue())
                    && getAddressValue().equals(e.getAddressValue())
                    && getTagValues().equals(e.getTagValues());
        }
    }
}
