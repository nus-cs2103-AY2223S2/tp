package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.model.Filter;

/**
 * Tests that a {@code Person}'s fields all match at least one of the field-respective regexes,
 * if any such field-respective regexes were supplied.
 */
public class FieldsMatchRegexPredicate implements Predicate<Person> {
    private final List<String> names;
    private final List<String> phones;
    private final List<String> emails;
    private final List<String> addresses;
    private final List<String> incomes;
    private final List<String> tags;

    /**
     * Creates a FieldsMatchRegexPredicate requiring that at least one regex of each non-empty field list matches.
     *
     * @param names     Regexes for the name field
     * @param phones    Regexes for the phone field
     * @param emails    Regexes for the email field
     * @param addresses Regexes for the address field
     * @param tags      Regexes for the tags field
     */
    public FieldsMatchRegexPredicate(List<String> names, List<String> phones, List<String> emails,
                                     List<String> addresses, List<String> incomes, List<String> tags) {
        this.names = names;
        this.phones = phones;
        this.emails = emails;
        this.addresses = addresses;
        this.incomes = incomes;
        this.tags = tags;
    }

    private boolean inRegexList(List<String> regexes, String str) {
        return regexes.isEmpty() || regexes.stream().anyMatch(str::matches);
    }

    @Override
    public boolean test(Person person) {
        return inRegexList(names, person.getName().fullName)
                && inRegexList(phones, person.getPhone().value)
                && inRegexList(emails, person.getEmail().value)
                && inRegexList(addresses, person.getAddress().value)
                && inRegexList(incomes, person.getIncome().toString())
                && (tags.isEmpty() || person.getTags().stream().anyMatch(tag -> inRegexList(tags, tag.tagName)));
    }

    /**
     * Returns a {@code ArrayList} of {@coed Filter} from this {@code FieldsMatchRegexPredicate} object.
     */
    public Stream<Filter> getFilterList() {
        Stream<Filter> listOfNameFilters = names.stream()
                .map(name -> new Filter("n/" + name.substring(2, name.length() - 2)));
        Stream<Filter> listOfPhoneFilters = phones.stream()
                .map(phone -> new Filter("p/" + phone.substring(2, phone.length() - 2)));
        Stream<Filter> listOfEmailFilters = emails.stream()
                .map(email -> new Filter("e/" + email.substring(2, email.length() - 2)));
        Stream<Filter> listOfAddressFilters = addresses.stream()
                .map(address -> new Filter("a/" + address.substring(2, address.length() - 2)));
        Stream<Filter> listOfIncomeFilters = incomes.stream()
                .map(income -> new Filter("i/" + income.substring(2, income.length() - 2)));
        Stream<Filter> listOfTagFilters = tags.stream()
                .map(tag -> new Filter("t/" + tag.substring(2, tag.length() - 2)));
        Stream<Filter> result = Stream
                .of(listOfNameFilters, listOfPhoneFilters, listOfEmailFilters,
                        listOfAddressFilters, listOfIncomeFilters, listOfTagFilters)
                .reduce(Stream.empty(), (x, y) -> Stream.concat(x, y));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldsMatchRegexPredicate // instanceof handles nulls
                && Objects.equals(names, ((FieldsMatchRegexPredicate) other).names) // state check
                && Objects.equals(phones, ((FieldsMatchRegexPredicate) other).phones)
                && Objects.equals(emails, ((FieldsMatchRegexPredicate) other).emails)
                && Objects.equals(addresses, ((FieldsMatchRegexPredicate) other).addresses)
                && Objects.equals(incomes, ((FieldsMatchRegexPredicate) other).incomes)
                && Objects.equals(tags, ((FieldsMatchRegexPredicate) other).tags));
    }

}
