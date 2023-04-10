package seedu.address.model;

/**
 * Unmodifiable view of FriendlyLink (main use is for dependency injection when testing).
 */
public interface ReadOnlyFriendlyLink extends ReadOnlyPair, ReadOnlyElderly, ReadOnlyVolunteer {
}
