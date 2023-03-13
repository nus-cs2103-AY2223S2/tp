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

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FieldContainsPartialKeywordsPredicate;
import seedu.address.model.person.PersonDescriptor;

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
    private final PersonDescriptor personDescriptor;
    private final FieldContainsPartialKeywordsPredicate predicate;

    /**
     * @param personDescriptor descriptor containing details used to filter
     */
    public FilterCommand(PersonDescriptor personDescriptor) {
        requireNonNull(personDescriptor);

        this.personDescriptor = new PersonDescriptor(personDescriptor);
        this.predicate = createFilterPredicate(personDescriptor);

    }

    /**
     * Creates and returns a {@code FieldContainsKeywordsPredicatePartial} object with
     * the field values of {@code filterDescriptor}. Replaces empty fields with blanks.
     */
    private static FieldContainsPartialKeywordsPredicate createFilterPredicate(
            PersonDescriptor personDescriptor) {

        String blank = "";
        String nameString = personDescriptor.getName().map(name -> name.toString()).orElse(blank);

        String phoneString = personDescriptor.getPhone().map(phone -> phone.toString()).orElse(blank);

        String emailString = personDescriptor.getEmail().map(email -> email.toString()).orElse(blank);

        String addressString = personDescriptor.getAddress().map(address -> address.toString()).orElse(blank);

        List<String> tagKeywords = personDescriptor.getTags().orElse(Collections.emptySet())
                .stream().map(tag -> tag.tagName).collect(Collectors.toList());

        return new FieldContainsPartialKeywordsPredicate(nameString, phoneString, emailString,
                addressString, tagKeywords);
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
        return personDescriptor.equals(f.personDescriptor);
    }

}
