package teambuilder.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import teambuilder.commons.exceptions.IllegalValueException;
import teambuilder.model.person.Name;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;


/**
 * Jackson-friendly version of {@link Team}.
 */
public class JsonAdaptedTeam {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Team's %s field is missing!";

    private final String teamName;
    private final String teamDesc;
    private final List<JsonAdaptedTag> skills = new ArrayList<>();
    private final List<JsonAdaptedName> members = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTeam} with the given team details.
     */
    @JsonCreator
    public JsonAdaptedTeam(@JsonProperty("teamName") String teamName,
                           @JsonProperty("teamDesc") String teamDesc,
                           @JsonProperty("skills") List<JsonAdaptedTag> skillTags,
                           @JsonProperty("members") List<JsonAdaptedName> members) {
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        if (skillTags != null) {
            this.skills.addAll(skillTags);
        }
        if (members != null) {
            this.members.addAll(members);
        }
    }

    /**
     * Converts a given {@code Team} into this class for Jackson use.
     */
    public JsonAdaptedTeam(Team source) {
        teamName = source.getTeamName().toString();
        teamDesc = source.getDesc().toString();
        skills.addAll(source.getSkillTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        members.addAll(source.getMembers().stream()
                .map(JsonAdaptedName::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this Jackson-friendly adapted team object into the model's {@code team} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted team.
     */
    public Team toModelType() throws IllegalValueException {
        final List<Name> teamMembers = new ArrayList<>();
        for (JsonAdaptedName name : members) {
            teamMembers.add(name.toModelType());
        }
        final List<Tag> skilltags = new ArrayList<>();
        for (JsonAdaptedTag skillTag : skills) {
            skilltags.add(skillTag.toModelType());
        }
        if (teamName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TeamName.class.getSimpleName()));
        }
        if (!TeamName.isValidTeamName(teamName)) {
            throw new IllegalValueException(TeamName.MESSAGE_CONSTRAINTS);
        }
        final TeamName modelName = new TeamName(teamName);
        // TODO: check if members of team exists in team builder before adding team.
        if (teamDesc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Desc.class.getSimpleName()));
        }
        if (!Desc.isValidTeamDesc(teamDesc)) {
            throw new IllegalValueException(Desc.MESSAGE_CONSTRAINTS);
        }
        final Desc modelDesc = new Desc(teamDesc);

        final Set<Tag> modelSkillTags = new HashSet<>(skilltags);

        return new Team(modelName, modelDesc, modelSkillTags);

    }

}
