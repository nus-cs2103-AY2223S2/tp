package teambuilder.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static teambuilder.testutil.Assert.assertThrows;
import static teambuilder.testutil.TypicalTeams.TEAM_A;

import org.junit.jupiter.api.Test;

import teambuilder.model.team.UniqueTeamList;
import teambuilder.model.team.exceptions.DuplicateTeamException;

public class UniqueTeamListTest {

    private final UniqueTeamList uniqueTeamList = new UniqueTeamList();

    @Test
    public void contains_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueTeamList.contains(TEAM_A));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueTeamList.add(TEAM_A);
        assertTrue(uniqueTeamList.contains(TEAM_A));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueTeamList.add(TEAM_A);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.add(TEAM_A));
    }


}
