package teambuilder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static teambuilder.testutil.Assert.assertThrows;
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

public class CreateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allArgumentsSpecifiedSuccessfullyNoTag_success() throws CommandException {
        TeamName validTeamName = new TeamName("Team A");
        Team team = new Team(validTeamName, new Desc("This is a description."), new HashSet<>());
        CommandResult commandResult = new CreateCommand(team).execute(model);
        assertEquals(String.format(CreateCommand.MESSAGE_SUCCESS, validTeamName), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_allArgumentsSpecifiedSuccessfullyTagPresent_success() throws CommandException {
        TeamName validTeamName = new TeamName("Team A");
        Team team = new Team(validTeamName, new Desc("This is a description."),
                new HashSet<>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
        CommandResult commandResult = new CreateCommand(team).execute(model);
        assertEquals(String.format(CreateCommand.MESSAGE_SUCCESS, validTeamName), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_throw() throws CommandException {
        TeamName validTeamName = new TeamName("Team A");
        Team team = new Team(validTeamName, new Desc("This is a description."),
                new HashSet<>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
        new CreateCommand(team).execute(model);
        CreateCommand command = new CreateCommand(team);
        assertThrows(CommandException.class, CreateCommand.MESSAGE_DUPLICATE_TEAM, () -> command.execute(model));
    }

    @Test
    public void equals() {
        TeamName validTeamName = new TeamName("Team A");
        Team team = new Team(validTeamName, new Desc("This is a description."),
                new HashSet<>(Arrays.asList(new Tag("Python"), new Tag("ReactNative"))));
        final CreateCommand standardCommand = new CreateCommand(team);

        // same values -> returns true
        CreateCommand sameCommand = new CreateCommand(team);
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
        CreateCommand diffCommand = new CreateCommand(anotherTeam);
        assertFalse(standardCommand.equals(diffCommand));

    }
}
