package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FieldContainsPartialKeywordsPredicate;

/**
 * Lists all persons in the address book, filtering out persons whose fields do not contain
 * the given filters. Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters through all persons in the address "
            + "book, removing persons whose fields fail to contain the specified keywords (case-insensitive)\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "j "
            + PREFIX_PHONE + "9 "
            + PREFIX_EMAIL + ".com "
            + PREFIX_ADDRESS + "clementi "
            + PREFIX_TAG + "2ic "
            + PREFIX_TAG + "logistics ";
    public static final String MESSAGE_FILTER_SUCCESS = "Filter applied!";
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
     * Creates and returns a {@code FieldContainsKeywordsPredicatePartial} object with
     * the field values of {@code filterDescriptor}.
     */
    private static FieldContainsPartialKeywordsPredicate createFilterPredicate(
            FilterDescriptor filterDescriptor) {
        return new FieldContainsPartialKeywordsPredicate(filterDescriptor.nameValue,
                filterDescriptor.phoneValue, filterDescriptor.emailValue,
                filterDescriptor.addressValue, filterDescriptor.rankValue,
                filterDescriptor.unitValue, filterDescriptor.companyValue,
                filterDescriptor.platoonValue, filterDescriptor.tagValues.stream().collect(Collectors.toList()));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_FILTER_SUCCESS);
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
     * Stores the details to filter with. Each non-empty field value will be used as a
     * filter, removing contacts that do not match.
     */
    public static class FilterDescriptor {
        private static final String BLANK = "";
        private String nameValue;
        private String phoneValue;
        private String emailValue;
        private String addressValue;
        private String rankValue;
        private String unitValue;
        private String companyValue;
        private String platoonValue;
        private List<String> tagValues;

        /**
         * Constructs default FilterDescriptor that is used to contain details of a filter.
         * Is created with blanks in all of its fields and an empty set for the set of tag
         * values.
         */
        public FilterDescriptor() {
            nameValue = BLANK;
            phoneValue = BLANK;
            emailValue = BLANK;
            addressValue = BLANK;
            rankValue = BLANK;
            unitValue = BLANK;
            companyValue = BLANK;
            platoonValue = BLANK;
            tagValues = Collections.emptyList();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean hasNonEmptyField() {
            return (CollectionUtil.isAnyNotBlank(nameValue, phoneValue, emailValue, addressValue, rankValue, unitValue,
                    companyValue, platoonValue) || !(tagValues.isEmpty()));
        }

        public String getNameValue() {
            return nameValue;
        }

        public void setNameValue(String nameValue) {
            this.nameValue = nameValue;
        }

        public String getPhoneValue() {
            return phoneValue;
        }

        public void setPhoneValue(String phoneValue) {
            this.phoneValue = phoneValue;
        }

        public String getEmailValue() {
            return emailValue;
        }

        public void setEmailValue(String emailValue) {
            this.emailValue = emailValue;
        }

        public String getAddressValue() {
            return addressValue;
        }

        public void setAddressValue(String addressValue) {
            this.addressValue = addressValue;
        }

        public String getRankValue() {
            return rankValue;
        }

        public void setRankValue(String rankValue) {
            this.rankValue = rankValue;
        }

        public String getUnitValue() {
            return unitValue;
        }

        public void setUnitValue(String unitValue) {
            this.unitValue = unitValue;
        }

        public String getCompanyValue() {
            return companyValue;
        }

        public void setCompanyValue(String companyValue) {
            this.companyValue = companyValue;
        }

        public String getPlatoonValue() {
            return platoonValue;
        }

        public void setPlatoonValue(String platoonValue) {
            this.platoonValue = platoonValue;
        }

        public List<String> getTagValues() {
            return tagValues;
        }

        public void setTagValues(List<String> tagValues) {
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

            return getNameValue().equals(e.getNameValue())
                    && getPhoneValue().equals(e.getPhoneValue())
                    && getEmailValue().equals(e.getEmailValue())
                    && getAddressValue().equals(e.getAddressValue())
                    && getRankValue().equals(e.getRankValue())
                    && getUnitValue().equals(e.getUnitValue())
                    && getCompanyValue().equals(e.getCompanyValue())
                    && getPlatoonValue().equals(e.getPlatoonValue())
                    && getTagValues().equals(e.getTagValues());
        }
    }
}
