package seedu.address.model.person;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    ContactContainsEmailPredicate is a predicate that filters the model based on address

    @author Haiqel Bin Hanaffi
 */
public class ContactContainsDescriptionPredicate implements Predicate<Person> {
    private final String description;

    public ContactContainsDescriptionPredicate(String description) {
        this.description = description;
    }

    @Override
    public boolean test(Person person) {
        // perform filtering based on description
        String contactDescription = person.getAddress().toString();
        String[] matchDescription = this.description.split(" ");
        String regex = String.join("|", matchDescription);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contactDescription);
        boolean isMatch = matcher.find();
        if (isMatch) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsDescriptionPredicate // instanceof handles nulls
                && description.equals(((ContactContainsDescriptionPredicate) other).description)); // state check
    }
}
