package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Tracker;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.Video;
import seedu.address.testutil.EditLectureDescriptorBuilder;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditVideoDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_MODULE_CODE_2103 = "CS2103";
    public static final String VALID_MODULE_CODE_2040 = "CS2040S";
    public static final String VALID_MODULE_NAME_2103 = "Software Engineering";
    public static final String VALID_MODULE_NAME_2040 = "Data Structures and Algorithms";
    public static final String VALID_LECTURE_NAME_L1 = "lecture1";
    public static final String VALID_LECTURE_NAME_L2 = "lecture2";
    public static final String VALID_VIDEO_NAME_V1 = "video1";
    public static final String VALID_VIDEO_NAME_V2 = "video2";

    public static final String INVALID_MODULE_CODE = "404CS";
    public static final String INVALID_LECTURE_NAME = "l337ure!";
    public static final String INVALID_VIDEO_NAME = "v*deo";

    public static final String MODULE_CODE_DESC_2103 = " " + PREFIX_MODULE + " " + VALID_MODULE_CODE_2103;
    public static final String MODULE_CODE_DESC_2040 = " " + PREFIX_MODULE + " " + VALID_MODULE_CODE_2040;
    public static final String MODULE_NAME_DESC_2103 = " " + PREFIX_NAME + " " + VALID_MODULE_NAME_2103;
    public static final String MODULE_NAME_DESC_2040 = " " + PREFIX_NAME + " " + VALID_MODULE_NAME_2040;
    public static final String LECTURE_NAME_DESC_L1 = " " + PREFIX_LECTURE + " " + VALID_LECTURE_NAME_L1;
    public static final String LECTURE_NAME_DESC_L2 = " " + PREFIX_LECTURE + " " + VALID_LECTURE_NAME_L2;

    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULE + INVALID_MODULE_CODE;
    public static final String INVALID_LECTURE_NAME_DESC = " " + PREFIX_LECTURE + INVALID_LECTURE_NAME;

    public static final String EDIT_MODULE_CODE_DESC_2103 = " " + PREFIX_CODE + " " + VALID_MODULE_CODE_2103;
    public static final String EDIT_MODULE_CODE_DESC_2040 = " " + PREFIX_CODE + " " + VALID_MODULE_CODE_2040;
    public static final String EDIT_MODULE_NAME_DESC_2103 = MODULE_NAME_DESC_2103;
    public static final String EDIT_MODULE_NAME_DESC_2040 = MODULE_NAME_DESC_2040;
    public static final String EDIT_LECTURE_NAME_DESC_L1 = " " + PREFIX_NAME + " " + VALID_LECTURE_NAME_L1;
    public static final String EDIT_LECTURE_NAME_DESC_L2 = " " + PREFIX_NAME + " " + VALID_LECTURE_NAME_L2;
    public static final String EDIT_VIDEO_NAME_DESC_V1 = " " + PREFIX_NAME + " " + VALID_VIDEO_NAME_V1;
    public static final String EDIT_VIDEO_NAME_DESC_V2 = " " + PREFIX_NAME + " " + VALID_VIDEO_NAME_V2;

    public static final String INVALID_EDIT_MODULE_CODE_DESC = " " + PREFIX_CODE + " " + INVALID_MODULE_CODE;
    public static final String INVALID_EDIT_LECTURE_NAME_DESC = " " + PREFIX_NAME + " " + INVALID_LECTURE_NAME;
    public static final String INVALID_EDIT_VIDEO_NAME_DESC = " " + PREFIX_NAME + " " + INVALID_VIDEO_NAME;

    // TODO: Remove this
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    // TODO: Remove this
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    // TODO: Remove this
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String VALID_MODULE_CODE = "CS2103T";
    public static final String VALID_LECTURE_NAME = "Class Diagrams";
    public static final String VALID_VIDEO_NAME = "Test Video";
    public static final String MODULE_DESC = " " + PREFIX_MODULE + " " + VALID_MODULE_CODE;
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditModuleDescriptor EDIT_MODULE_DESC_CS2103 = new EditModuleDescriptorBuilder()
            .withCode(VALID_MODULE_CODE_2103).withName(VALID_MODULE_NAME_2103).build();
    public static final EditModuleDescriptor EDIT_MODULE_DESC_CS2040S = new EditModuleDescriptorBuilder()
            .withCode(VALID_MODULE_CODE_2040).withName(VALID_MODULE_NAME_2040).build();

    public static final EditLectureDescriptor EDIT_LECTURE_DESC_L1 = new EditLectureDescriptorBuilder()
            .withName(VALID_LECTURE_NAME_L1).build();
    public static final EditLectureDescriptor EDIT_LECTURE_DESC_L2 = new EditLectureDescriptorBuilder()
            .withName(VALID_LECTURE_NAME_L2).build();

    public static final EditVideoDescriptor EDIT_VIDEO_DESC_V1 = new EditVideoDescriptorBuilder()
            .withName(VALID_VIDEO_NAME_V1).build();
    public static final EditVideoDescriptor EDIT_VIDEO_DESC_V2 = new EditVideoDescriptorBuilder()
            .withName(VALID_VIDEO_NAME_V2).build();

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
        Tracker expectedTracker = new Tracker(actualModel.getTracker());
        List<ReadOnlyModule> expectedFilteredModuleList = new ArrayList<>(actualModel.getFilteredModuleList());
        List<ReadOnlyLecture> expectedFilteredLectureList = actualModel.getFilteredLectureList() == null
                ? null : new ArrayList<>(actualModel.getFilteredLectureList());
        List<Video> expectedFilteredVideoList = actualModel.getFilteredVideoList() == null
                ? null : new ArrayList<>(actualModel.getFilteredVideoList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTracker, actualModel.getTracker());
        assertEquals(expectedFilteredModuleList, actualModel.getFilteredModuleList());
        assertEquals(expectedFilteredLectureList, actualModel.getFilteredLectureList());
        assertEquals(expectedFilteredVideoList, actualModel.getFilteredVideoList());
    }

}
