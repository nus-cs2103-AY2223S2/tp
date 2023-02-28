package seedu.address.model;

import java.util.List;

import seedu.address.model.person.Volunteer;

/**
 * Unmodifiable list of volunteers
 */
public interface ReadOnlyVolunteer {
    /**
     * Returns an unmodifiable view of the volunteer list.
     * This list will not contain any duplicate volunteers.
     */
    List<Volunteer> getVolunteerList();

}
