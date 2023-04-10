package arb.model.client.predicates;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import arb.commons.util.CollectionUtil;
import arb.commons.util.StringUtil;
import arb.model.client.Client;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Client> {
    private final Set<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = new HashSet<>(keywords);
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return keywords.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name keywords: ");
        Iterator<String> iterator = keywords.iterator();
        iterator.forEachRemaining(k -> sb.append(k + ", "));
        return sb.delete(sb.length() - 2, sb.length() + 1).toString();
    }

    public String keywordsToString() {
        return CollectionUtil.keywordsToString(keywords);
    }
}
