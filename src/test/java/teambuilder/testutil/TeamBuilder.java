package teambuilder.testutil;

import java.util.HashSet;
import java.util.Set;

import teambuilder.model.person.Name;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;
import teambuilder.model.util.SampleDataUtil;

/**
 * A utility class to help with building Team objects.
 */
public class TeamBuilder {

    public static final String DEFAULT_NAME = "NUS";

    public static final String DEFAULT_DESC = "description";

    private TeamName name;

    private Desc desc;

    private Set<Tag> skillTags;

    private Set<Name> members;

    /**
     * Creates a {@code TeamBuilder} with the default details.
     */
    public TeamBuilder() {
        name = new TeamName(DEFAULT_NAME);
        desc = new Desc(DEFAULT_DESC);
        skillTags = new HashSet<>();
        members = new HashSet<>();
    }

    /**
     * Initializes the TeamBuilder with the data of {@code teamToCopy}.
     */
    public TeamBuilder(Team teamToCopy) {
        name = teamToCopy.getName();
        desc = teamToCopy.getDesc();
        skillTags = new HashSet<>(teamToCopy.getTags());
        members = new HashSet<>(teamToCopy.getMembers());
    }

    /**
     * Sets the {@code name} of the {@code Team} that we are building.
     */
    public TeamBuilder withName(String name) {
        this.name = new TeamName(name);
        return this;
    }

    /**
     * Sets the {@code desc} of the {@code Team} that we are building.
     */
    public TeamBuilder withDesc(String desc) {
        this.desc = new Desc(desc);
        return this;
    }

    /**
     * Parses the {@code skillTags} into a {@code Set<Tag>} and set it
     * to the {@code Team} that we are building.
     */
    public TeamBuilder withSkillTags(String ... skillTags) {
        this.skillTags = SampleDataUtil.getTagSet(skillTags);
        return this;
    }

    /**
     * Parses the {@code members} into a {@code Set<Tag>} and set it
     * to the {@code Team} that we are building.
     */
    public TeamBuilder withMembers(String ... members) {
        this.members = SampleDataUtil.getNameSet(members);
        return this;
    }

    public Team build() {
        return new Team(name, desc, skillTags);
    }

}
