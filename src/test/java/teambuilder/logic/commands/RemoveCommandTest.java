package teambuilder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static teambuilder.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.ModelManager;
import teambuilder.model.UserPrefs;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

class RemoveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allArgumentsSpecifiedSuccessfullyNoTag_success() throws CommandException {
        TeamName validTeamName = new TeamName("Team A");
        Team team = new Team(validTeamName, new Desc("This is a description."), new HashSet<>());
        new CreateCommand(team).execute(model);
        CommandResult commandResult = new RemoveCommand(team.getName()).execute(model);
        assertEquals(String.format(RemoveCommand.MESSAGE_DELETE_TEAM_SUCCESS, validTeamName),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_allArgumentsSpecifiedSuccessfullyTagPresent_success() throws CommandException {
        TeamName validTeamName = new TeamName("Team A");
        Team team = new Team(validTeamName, new Desc("This is a description."),
                new HashSet<>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
        new CreateCommand(team).execute(model);
        CommandResult commandResult = new RemoveCommand(team.getName()).execute(model);
        assertEquals(String.format(RemoveCommand.MESSAGE_DELETE_TEAM_SUCCESS, validTeamName),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        TeamName validTeamName = new TeamName("Team A");
        Team team = new Team(validTeamName, new Desc("This is a description."),
                new HashSet<>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
        final RemoveCommand standardCommand = new RemoveCommand(team.getName());

        // same values -> returns true
        RemoveCommand sameCommand = new RemoveCommand(team.getName());
        assertTrue(standardCommand.equals(sameCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different order -> returns false
        TeamName anotherValidTeamName = new TeamName("Team B");
        Team anotherTeam = new Team(anotherValidTeamName, new Desc("This is a description."),
                new HashSet<>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
        RemoveCommand diffCommand = new RemoveCommand(anotherTeam.getName());
        assertFalse(standardCommand.equals(diffCommand));

    }

}
