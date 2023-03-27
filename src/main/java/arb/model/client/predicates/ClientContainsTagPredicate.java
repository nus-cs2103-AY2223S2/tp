package arb.model.client.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import arb.model.client.Client;
import arb.model.tag.Tag;

/**
 * Tests that a {@code Client}'s contains any of the tags given.
 */
public class ClientContainsTagPredicate implements Predicate<Client> {
    private final Set<Tag> tags;

    public ClientContainsTagPredicate(List<String> keywords) {
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
                || (other instanceof ClientContainsTagPredicate // instanceof handles nulls
                && tags.equals(((ClientContainsTagPredicate) other).tags)); // state check
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }
}
