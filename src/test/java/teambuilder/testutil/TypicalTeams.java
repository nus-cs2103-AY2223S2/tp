package teambuilder.testutil;

import teambuilder.model.person.Person;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

import java.util.Arrays;
import java.util.HashSet;

public class TypicalTeams {
    public static final Team TEAM_A = new Team(new TeamName("Team A"), new Desc("This is Team A"),
            new HashSet<Tag>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
}
