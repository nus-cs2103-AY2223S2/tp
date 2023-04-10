package codoc.logic.commands;

import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static codoc.logic.parser.CliSyntax.PREFIX_GITHUB;
import static codoc.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_ADD;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;
import static codoc.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codoc.commons.core.index.Index;
import codoc.logic.commands.exceptions.CommandException;
import codoc.model.Codoc;
import codoc.model.Model;
import codoc.model.person.NameContainsKeywordsPredicate;
import codoc.model.person.Person;
import codoc.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_PROFILE_PICTURE_AMY = "src/main/resources/images/avataricons/001-bear.png";
    public static final String VALID_PROFILE_PICTURE_BOB = "src/main/resources/images/avataricons/002-rabbit.png";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_COURSE_AMY = "1";
    public static final String VALID_YEAR_AMY = "2";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_COURSE_BOB = "3";
    public static final String VALID_YEAR_BOB = "1";
    public static final String VALID_GITHUB_AMY = "amy-123";
    public static final String VALID_GITHUB_BOB = "bob-456";
    public static final String VALID_EMAIL_AMY = "amy@gmail.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_LINKEDIN_AMY = "linkedin.com/in/4my";
    public static final String VALID_LINKEDIN_BOB = "linkedin.com/in/b0b";
    public static final String VALID_SKILL_AMY = "java";
    public static final String VALID_SKILL_BOB = "C#";
    public static final String VALID_MODULE_AMY = "AY2223S2 CS2103T";
    public static final String VALID_MODULE_BOB = "AY2223S2 CS2109S";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String COURSE_DESC_AMY = " " + PREFIX_COURSE + VALID_COURSE_AMY;
    public static final String COURSE_DESC_BOB = " " + PREFIX_COURSE + VALID_COURSE_BOB;
    public static final String YEAR_DESC_AMY = " " + PREFIX_YEAR + VALID_YEAR_AMY;
    public static final String YEAR_DESC_BOB = " " + PREFIX_YEAR + VALID_YEAR_BOB;
    public static final String GITHUB_DESC_AMY = " " + PREFIX_GITHUB + VALID_GITHUB_AMY;
    public static final String GITHUB_DESC_BOB = " " + PREFIX_GITHUB + VALID_GITHUB_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String LINKEDIN_DESC_AMY = " " + PREFIX_LINKEDIN + VALID_LINKEDIN_AMY;
    public static final String LINKEDIN_DESC_BOB = " " + PREFIX_LINKEDIN + VALID_LINKEDIN_BOB;
    public static final String SKILL_DESC_AMY = " " + PREFIX_SKILL + VALID_SKILL_AMY;
    public static final String SKILL_DESC_BOB = " " + PREFIX_SKILL + VALID_SKILL_BOB;
    public static final String SKILL_ADD_DESC_AMY = " " + PREFIX_SKILL_ADD + VALID_SKILL_AMY;
    public static final String SKILL_ADD_DESC_BOB = " " + PREFIX_SKILL_ADD + VALID_SKILL_BOB;
    public static final String SKILL_REMOVE_DESC_AMY = " " + PREFIX_SKILL_DELETE + VALID_SKILL_AMY;
    public static final String SKILL_REMOVE_DESC_BOB = " " + PREFIX_SKILL_DELETE + VALID_SKILL_BOB;

    public static final String MOD_DESC_AMY = " " + PREFIX_MOD + VALID_MODULE_AMY;
    public static final String MOD_DESC_BOB = " " + PREFIX_MOD + VALID_MODULE_BOB;
    public static final String MOD_ADD_DESC_BOB = " " + PREFIX_MOD_ADD + VALID_MODULE_BOB;
    public static final String MOD_REMOVE_DESC_BOB = " " + PREFIX_MOD_DELETE + VALID_MODULE_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_GITHUB_DESC = " " + PREFIX_GITHUB + "-bob"; // GitHub usernames cannot start
    // with '-'
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_LINKEDIN_DESC = " " + PREFIX_LINKEDIN + "linkedin.com/in/4my sheesh"; // space
    // not allowed for linkedins
    public static final String INVALID_SKILL_DESC = " " + PREFIX_SKILL + "hubbyü"; // 'ü' not allowed in skills
    public static final String INVALID_SKILL_ADD_DESC = " " + PREFIX_SKILL_ADD + "hubbyü"; // 'ü' not allowed in skills

    // D must be replaced by number
    public static final String INVALID_MOD_DESC = " " + PREFIX_MOD + "AY22D3S2 CS2102";
    public static final String INVALID_MOD_ADD_DESC = " " + PREFIX_MOD_ADD + "AY22D3S2 CS2102";

    // last number following S must be replaced by 1 or 2
    public static final String INVALID_MOD_SEM_DESC = " " + PREFIX_MOD + "AY2223S3 CS2102";
    public static final String INVALID_MOD_ADD_SEM_DESC = " " + PREFIX_MOD_ADD + "AY2223S3 CS2102";


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withGithub(VALID_GITHUB_AMY).withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_BOB).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withGithub(VALID_GITHUB_BOB).withEmail(VALID_EMAIL_BOB).withLinkedin(VALID_LINKEDIN_BOB)
                .withSkills(VALID_SKILL_AMY, VALID_SKILL_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br> - the returned {@link CommandResult} matches
     * {@code expectedCommandResult} <br> - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)} that takes a string
     * {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br> - a {@code CommandException} is thrown <br> - the
     * CommandException message matches {@code expectedMessage} <br> - CoDoc, filtered person list and selected person
     * in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Codoc expectedCodoc = new Codoc(actualModel.getCodoc());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedCodoc, actualModel.getCodoc());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s CoDoc.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(splitName[0]));
        model.updateFilteredPersonList(namePredicate, namePredicate.toString());

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
