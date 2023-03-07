package seedu.address.testutil;

import java.util.List;

import seedu.address.model.FriendlyLink;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

/**
 * A utility class to help with building FriendlyLink objects.
 * Example usage: <br>
 *     {@code FriendlyLink fl = new FriendlyLinkBuilder().withElderly(elderly).build();}
 */
public class FriendlyLinkBuilder {

    private final FriendlyLink friendlyLink;

    public FriendlyLinkBuilder() {
        friendlyLink = new FriendlyLink();
    }

    public FriendlyLinkBuilder(FriendlyLink friendlyLink) {
        this.friendlyLink = friendlyLink;
    }

    /**
     * Adds a new {@code Volunteer} to the {@code FriendlyLink} that we are building.
     */
    public FriendlyLinkBuilder withVolunteer(Volunteer volunteer) {
        friendlyLink.addVolunteer(volunteer);
        return this;
    }

    /**
     * Adds all {@code volunteers} to the {@code FriendlyLink} that we are building.
     */
    public FriendlyLinkBuilder withVolunteers(List<Volunteer> volunteers) {
        for (Volunteer volunteer : volunteers) {
            friendlyLink.addVolunteer(volunteer);
        }
        return this;
    }

    /**
     * Adds a new {@code Elderly} to the {@code FriendlyLink} that we are building.
     */
    public FriendlyLinkBuilder withElderly(Elderly elderly) {
        friendlyLink.addElderly(elderly);
        return this;
    }

    /**
     * Adds all {@code elderly} to the {@code FriendlyLink} that we are building.
     */
    public FriendlyLinkBuilder withElderly(List<Elderly> elderly) {
        for (Elderly elderlyMember : elderly) {
            friendlyLink.addElderly(elderlyMember);
        }
        return this;
    }

    /**
     * Adds a new {@code Pair} to the {@code FriendlyLink} that we are building.
     */
    public FriendlyLinkBuilder withPair(Pair pair) {
        friendlyLink.addPair(pair);
        return this;
    }

    /**
     * Adds all {@code pairs} to the {@code FriendlyLink} that we are building.
     */
    public FriendlyLinkBuilder withPairs(List<Pair> pairs) {
        for (Pair pair : pairs) {
            friendlyLink.addPair(pair);
        }
        return this;
    }

    public FriendlyLink build() {
        return friendlyLink;
    }
}
