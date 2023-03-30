package teambuilder.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static teambuilder.logic.commands.CommandTestUtil.VALID_SKILLTAG_TEAM;
import static teambuilder.logic.commands.CommandTestUtil.VALID_TEAMDESC_A;
import static teambuilder.logic.commands.CommandTestUtil.VALID_TEAMNAME_A;
import static teambuilder.testutil.Assert.assertThrows;
import static teambuilder.testutil.TypicalTeams.TEAM_A;
import static teambuilder.testutil.TypicalTeams.TEAM_B;
import static teambuilder.testutil.TypicalTeams.TEAM_C;

import org.junit.jupiter.api.Test;

import teambuilder.testutil.TeamBuilder;

import java.util.Collections;

class TeamTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Team team = new TeamBuilder().build();
        assertThrows(UnsupportedOperationException.class,
                () -> Collections.unmodifiableSet(team.getTags()).remove(0));
    }

    @Test
    public void isSameTeam() {
        // same object -> returns true
        assertTrue(TEAM_A.isSameTeam(TEAM_A));

        // null -> returns false
        assertFalse(TEAM_A.isSameTeam(null));

        // same name, all other attributes different -> returns true
        Team editedTeamA = new TeamBuilder(TEAM_A).withDesc(VALID_TEAMDESC_A).build();
        assertTrue(TEAM_A.isSameTeam(editedTeamA));

        // different name, all other attributes same -> returns false
        editedTeamA = new TeamBuilder(TEAM_A).withName(VALID_TEAMNAME_A).build();
        assertFalse(TEAM_A.isSameTeam(editedTeamA));

        // name differs in case, all other attributes same -> returns false
        Team editedTeamB = new TeamBuilder(TEAM_B).withName(VALID_TEAMNAME_A.toLowerCase()).build();
        assertFalse(TEAM_B.isSameTeam(editedTeamB));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Team teamCopy = new TeamBuilder(TEAM_C).build();
        assertTrue(TEAM_C.equals(teamCopy));

        // same object -> returns true
        assertTrue(TEAM_C.equals(TEAM_C));

        // null -> returns false
        assertFalse(TEAM_C.equals(null));

        // different type -> returns false
        assertFalse(TEAM_C.equals(5));

        // different person -> returns false
        assertFalse(TEAM_C.equals(TEAM_A));

        // different name -> returns false
        Team editedTeamA = new TeamBuilder(TEAM_A).withName(VALID_TEAMNAME_A).build();
        assertFalse(TEAM_A.equals(editedTeamA));

        // different desc -> returns true
        editedTeamA = new TeamBuilder(TEAM_A).withDesc(VALID_TEAMDESC_A).build();
        assertTrue(TEAM_A.equals(editedTeamA));

        // different tags -> returns true
        editedTeamA = new TeamBuilder(TEAM_A).withSkillTags(VALID_SKILLTAG_TEAM).build();
        assertTrue(TEAM_A.equals(editedTeamA));
    }

}
