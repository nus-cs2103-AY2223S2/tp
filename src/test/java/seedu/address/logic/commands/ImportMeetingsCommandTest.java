package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAdaptedMeeting;
import seedu.address.testutil.TypicalAddressBooks;
import seedu.address.testutil.TypicalMeetings;

public class ImportMeetingsCommandTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportExportMeetingsTest");
    private static final Path VALID_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("validMeetings.json");

    @Test
    public void success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = Files.newBufferedReader(VALID_MEETINGS_FILE);
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        ImportMeetingsCommand command = new ImportMeetingsCommand(stringBuilder.toString(), false);
        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(),
                new UserPrefs());
        assertCommandSuccess(command, model, ImportMeetingsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicateForced_success() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(),
                new UserPrefs());
        JsonAdaptedMeeting firstMeeting = new JsonAdaptedMeeting(TypicalMeetings.getTypicalMeetings().get(0));
        JsonAdaptedMeeting secondMeeting = new JsonAdaptedMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        String json;
        try {
            json = JsonUtil.toJsonString(new JsonAdaptedMeeting[]{firstMeeting, secondMeeting});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        model.removeMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        ImportMeetingsCommand command = new ImportMeetingsCommand(json, true);
        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(),
                new UserPrefs());
        expectedModel.removeMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        expectedModel.addMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        assertCommandSuccess(command, model, ImportMeetingsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicate_failure() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(), new UserPrefs());
        JsonAdaptedMeeting firstMeeting = new JsonAdaptedMeeting(TypicalMeetings.getTypicalMeetings().get(0));
        String json;
        try {
            json = JsonUtil.toJsonString(new JsonAdaptedMeeting[]{firstMeeting});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ImportMeetingsCommand command = new ImportMeetingsCommand(json, false);
        assertCommandFailure(command, model, ImportMeetingsCommand.DUPLICATE_MEETING);
    }

    @Test
    public void malformedJson_failure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        String json = "abc";
        ImportMeetingsCommand command = new ImportMeetingsCommand(json, false);
        assertCommandFailure(command, model, ImportMeetingsCommand.MALFORMED_JSON);
    }


}
