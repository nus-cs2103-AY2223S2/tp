package teambuilder.model.team;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.AppUtil.checkArgument;

/**
 * Represents a Team's name in TeamBuilder.
 */
public class TeamName {

    public static final String MESSAGE_CONSTRAINTS = "Team names should be alphanumeric,"
            + " with only a single whitespace between words if there are multiple words";

    public static final String VALIDATION_REGEX = "^([a-zA-Z0-9]+\\s)*[a-zA-Z0-9]+$";

    public final String teamName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param teamName A valid tag name.
     */
    public TeamName(String teamName) {
        requireNonNull(teamName);
        checkArgument(isValidTeamName(teamName), MESSAGE_CONSTRAINTS);
        this.teamName = teamName;
    }

    /**
     * Returns true if a given string is a valid team name.
     */
    public static boolean isValidTeamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamName // instanceof handles nulls
                && teamName.trim().equalsIgnoreCase(((TeamName) other).teamName.trim())); // state check
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return teamName;
    }
}
