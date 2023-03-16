package seedu.internship.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internship.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;

import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.InternshipByPositionCompanyPredicate;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_POSITION_ML1 = "Machine Learning";
    public static final String VALID_COMPANY_ML1 = "Tiktok";
    public static final String VALID_ID_ML1 = "1";
    public static final Integer VALID_STATUS_ML1 = 0;
    public static final String VALID_DESCRIPTION_ML1 = "Registration Deadline: 20 Mar 2023";

    public static final String VALID_POSITION_SE1 = "Software Engineer";
    public static final String VALID_COMPANY_SE1 = "Grab";
    public static final String VALID_ID_SE1 = "2";
    public static final Integer VALID_STATUS_SE1 = 1;
    public static final String VALID_DESCRIPTION_SE1 = "Interview Date: 20 May 2023";

    public static final String VALID_POSITION_DA1 = "Data Analytics";
    public static final String VALID_COMPANY_DA1 = "Google";
    public static final String VALID_ID_DA1 = "3";
    public static final Integer VALID_STATUS_DA1 = 2;
    public static final String VALID_DESCRIPTION_DA1 = "Internship Period: 01 May 2023 to 31 July 2023";

    public static final String VALID_POSITION_SD1 = "Software Developer";
    public static final String VALID_COMPANY_SD1 = "Shopee";
    public static final String VALID_ID_SD1 = "4";
    public static final Integer VALID_STATUS_SD1 = 3;
    public static final String VALID_DESCRIPTION_SD1 = "Rejected on 21 Feb 2023";

    public static final String VALID_TAG_FUN = "fun";
    public static final String VALID_TAG_IMPORTANT = "important";

    public static final String COMPANY_DESC_ML1 = " " + PREFIX_COMPANY + VALID_COMPANY_ML1;
    public static final String POSITION_DESC_ML1 = " " + PREFIX_POSITION + VALID_POSITION_ML1;
    public static final String STATUS_DESC_ML1 = " " + PREFIX_STATUS + VALID_STATUS_ML1;
    public static final String DESCRIPTION_DESC_ML1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_ML1;
    public static final String TAG_DESC_ML1 = " " + PREFIX_TAG + VALID_TAG_FUN;

    public static final String COMPANY_DESC_SE1 = " " + PREFIX_COMPANY + VALID_COMPANY_SE1;
    public static final String POSITION_DESC_SE1 = " " + PREFIX_POSITION + VALID_POSITION_SE1;
    public static final String STATUS_DESC_SE1 = " " + PREFIX_STATUS + VALID_STATUS_SE1;
    public static final String DESCRIPTION_DESC_SE1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_SE1;
    public static final String TAG_DESC_SE1 = " " + PREFIX_TAG + VALID_TAG_FUN;

    public static final String COMPANY_DESC_DA1 = " " + PREFIX_COMPANY + VALID_COMPANY_DA1;
    public static final String POSITION_DESC_DA1 = " " + PREFIX_POSITION + VALID_POSITION_DA1;
    public static final String STATUS_DESC_DA1 = " " + PREFIX_STATUS + VALID_STATUS_DA1;
    public static final String DESCRIPTION_DESC_DA1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_DA1;
    public static final String TAG_DESC_DA1 = " " + PREFIX_TAG + VALID_TAG_IMPORTANT;

    public static final String COMPANY_DESC_SD1 = " " + PREFIX_COMPANY + VALID_COMPANY_SD1;
    public static final String POSITION_DESC_SD1 = " " + PREFIX_POSITION + VALID_POSITION_SE1;
    public static final String STATUS_DESC_SD1 = " " + PREFIX_STATUS + VALID_STATUS_SD1;
    public static final String DESCRIPTION_DESC_SD1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_SD1;
    public static final String TAG_DESC_SD1 = " " + PREFIX_TAG + VALID_TAG_IMPORTANT;


    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION + "Engineer&"; // '&' not allowed in position
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + ""; // company cannot be empty
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "5"; // status can only contain 0, 1, 2 and 3
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "imp and fun"; // spaces not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternshipCatalogue expectedInternshipCatalogue = new InternshipCatalogue(actualModel.getInternshipCatalogue());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternshipCatalogue, actualModel.getInternshipCatalogue());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s internship catalogue.
     */
    public static void showInternshipAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

        Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
        model.updateFilteredInternshipList(new InternshipByPositionCompanyPredicate(internship.getPosition(), internship.getCompany()));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

}
