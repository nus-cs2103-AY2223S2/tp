package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Volunteer;
import seedu.address.storage.volunteer.JsonSerializableVolunteer;

public class JsonSerializableVolunteerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableVolunteerTest");
    private static final Path TYPICAL_VOLUNTEERS_FILE = TEST_DATA_FOLDER.resolve("typicalVolunteers.json");
    private static final Path INVALID_VOLUNTEER_FILE = TEST_DATA_FOLDER.resolve("invalidVolunteer.json");
    private static final Path DUPLICATE_VOLUNTEERS_FILE = TEST_DATA_FOLDER.resolve("duplicateVolunteers.json");
    private static final FriendlyLink appTestCache = new FriendlyLink();

    @Test
    public void toModelType_typicalVolunteersFile_success() throws Exception {
        FriendlyLink appTestCache = new FriendlyLink();
        JsonSerializableVolunteer dataFromFile = JsonUtil.readJsonFile(TYPICAL_VOLUNTEERS_FILE,
                JsonSerializableVolunteer.class).get();
        FriendlyLink friendlyLinkFromFile = dataFromFile.toModelType(appTestCache);
        List<Volunteer> typicalVolunteers = getTypicalFriendlyLink().getVolunteerList();
        assertEquals(typicalVolunteers, friendlyLinkFromFile.getVolunteerList());
    }

    @Test
    public void toModelType_invalidVolunteerFile_throwsIllegalValueException() throws Exception {
        FriendlyLink appTestCache = new FriendlyLink();
        JsonSerializableVolunteer dataFromFile = JsonUtil.readJsonFile(INVALID_VOLUNTEER_FILE,
                JsonSerializableVolunteer.class).get();
        assertThrows(IllegalValueException.class, () -> dataFromFile.toModelType(appTestCache));
    }

    @Test
    public void toModelType_duplicateVolunteers_throwsIllegalValueException() throws Exception {
        FriendlyLink appTestCache = new FriendlyLink();
        JsonSerializableVolunteer dataFromFile = JsonUtil.readJsonFile(DUPLICATE_VOLUNTEERS_FILE,
                JsonSerializableVolunteer.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableVolunteer.MESSAGE_DUPLICATE_VOLUNTEER, () -> dataFromFile.toModelType(appTestCache));
    }

}
