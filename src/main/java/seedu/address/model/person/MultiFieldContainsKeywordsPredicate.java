package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

public class MultiFieldContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
//    private final List<Tag> tags;

    public MultiFieldContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
//        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
//        boolean hasMatchingTag = tags.stream()
//                .anyMatch(tag -> person.getTags().contains(tag));

        boolean hasMatchingKeyword = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getName().fullName, keyword));
 /*
                        || StringUtil.containsIgnoreCase(person.getAddress().value, keyword)
                        || StringUtil.containsIgnoreCase(person.getPhone().value, keyword));
  */

        return hasMatchingKeyword;
        //hasMatchingTag || ;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MultiFieldContainsKeywordsPredicate
                && keywords.equals(((MultiFieldContainsKeywordsPredicate) other).keywords));
//                && tags.equals(((MultiFieldContainsKeywordsPredicate) other).tags));
    }
}
