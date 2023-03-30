package teambuilder.testutil;

import java.util.Arrays;
import java.util.HashSet;

import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

/**
 * Sample teams for testing
 */
public class TypicalTeams {
    public static final Team TEAM_A = new Team(new TeamName("TeamA"), new Desc("This is Team A"),
            new HashSet<Tag>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
}
