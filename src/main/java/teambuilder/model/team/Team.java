package teambuilder.model.team;

import teambuilder.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.AppUtil.checkArgument;

/**
 * Represents a Team in the address book.
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS =
            "Team names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String teamName;
    private final String teamDesc;
    private final Set<Tag> skillTags = new HashSet<>();
    private final Set<String> members = new HashSet<>();


    /**
     * Constructs a {@code Team}.
     *
     * @param teamName A valid team name.
     * @param teamDesc A team description.
     * @param skillTags A set of skill tags.
     */
    public Team(String teamName, String teamDesc, Set<Tag> skillTags) {
        requireNonNull(teamName);
        checkArgument(isValidTeamName(teamName), MESSAGE_CONSTRAINTS);
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        this.skillTags.addAll(skillTags);
    }

    /**
     * Returns true if a given string is a valid team name.
     */
    public static boolean isValidTeamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

    public String toString() {
        return '[' + teamName + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Team // instanceof handles nulls
                && teamName.equals(((Team) other).teamName)); // state check
    }


}
