package teambuilder.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import teambuilder.model.team.Team;

import java.util.ArrayList;
import java.util.List;

public class JsonAdaptedTeam {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String teamName;
    private final String teamDesc;
    private final List<JsonAdaptedTag> skillTags = new ArrayList<>();
    private final List<String> members = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedTeam(@JsonProperty("teamName") String teamName,
                           @JsonProperty("teamDesc") String teamDesc,
                           @JsonProperty("skills") List<JsonAdaptedTag> skillTags,
                           @JsonProperty("members") List<String> members) {
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        if (skillTags != null) {
            this.skillTags.addAll(skillTags);
        }
        if (members != null) {
            this.members.addAll(members);
        }
    }

    public JsonAdaptedTeam(Team source) {
        teamName = source.getTeamName().toString();
        teamDesc = source.getTeamDesc().toString();
        skillTags.addAll(source.getSkillTags())

    }
}
