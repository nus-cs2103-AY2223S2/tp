package tfifteenfour.clipboard.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.commons.util.FileUtil;
import tfifteenfour.clipboard.commons.util.JsonUtil;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.storage.serializedClasses.SerializedRoster;

/**
 * A class to access Roster data stored as a json file on the hard disk.
 */
public class JsonRosterStorage implements RosterStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRosterStorage.class);

    private Path filePath;

    public JsonRosterStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRosterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRoster> readRoster() throws DataConversionException {
        return readRoster(filePath);
    }

    /**
     * Similar to {@link #readRoster()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRoster> readRoster(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRoster> jsonRoster = JsonUtil.readJsonFile(
                filePath, JsonSerializableRoster.class);
        if (!jsonRoster.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRoster.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRoster(ReadOnlyRoster roster) throws IOException {
        saveRoster(roster, filePath);
    }

    /**
     * Similar to {@link #saveRoster(ReadOnlyRoster)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRoster(ReadOnlyRoster roster, Path filePath) throws IOException {
        requireNonNull(roster);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);

        //     IM USING MY OWN IMPLEMENTATION OF SAVING JSON BECAUSE I HAVE NO IDEA HOW THIS WORKS.
        // MEANS THIS COMMENTED CODE HAVE TO TRACE BACK ALL THE WAY CUZ ITS UNUSED
        //JsonUtil.saveJsonFile(new JsonSerializableRoster(roster), filePath);

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String rosterJson = rosterToJson(mapper, roster);
        writeJsonToFile(rosterJson, filePath);


    }

    private String rosterToJson(ObjectMapper mapper, ReadOnlyRoster roster) throws IOException {
        SerializedRoster wrapper = new SerializedRoster(roster);
        String rosterJson = mapper.writeValueAsString(wrapper);

        return rosterJson;
    }

    private void writeJsonToFile(String json, Path filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.toString());
        fileWriter.write(json);
        fileWriter.close();
    }

}