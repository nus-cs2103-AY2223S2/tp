package arb.model.client.predicates;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import arb.model.client.Client;
import arb.model.tag.Tag;

/**
 * Tests that a {@code Client} contains any of the tags given.
 */
public class ClientContainsTagsPredicate implements Predicate<Client> {
    private final Set<Tag> tags;

    public ClientContainsTagsPredicate(List<String> keywords) {
        this.tags = keywords.stream().map(s -> new Tag(s.toLowerCase())).collect(Collectors.toSet());
    }

    @Override
    public boolean test(Client client) {
        return tags.stream()
                .anyMatch(t -> client.getTags().contains(t));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientContainsTagsPredicate // instanceof handles nulls
                && tags.equals(((ClientContainsTagsPredicate) other).tags)); // state check
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tags: ");
        Iterator<Tag> iterator = tags.iterator();
        iterator.forEachRemaining(t -> sb.append(t + ", "));
        return sb.delete(sb.length() - 2, sb.length() + 1).toString();
    }
}
