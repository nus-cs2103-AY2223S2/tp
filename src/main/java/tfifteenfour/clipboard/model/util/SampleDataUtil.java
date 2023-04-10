package tfifteenfour.clipboard.model.util;

import static tfifteenfour.clipboard.storage.JsonRosterStorage.jsonToRoster;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.storage.serializedclasses.SerializedRoster;

/**
 * Contains utility methods for populating {@code Roster} with sample data.
 */
public class SampleDataUtil {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static ReadOnlyRoster getSampleRoster(Path sampleFilePath, InputStream sampleResourceStream) {

        Roster sampleRoster = new Roster();

        try {
            // Create a roster from /data/sampleRoster.json
            File file = new File(sampleFilePath.toString());
            SerializedRoster jsonSampleRoster = mapper.readValue(file, SerializedRoster.class);
            sampleRoster = jsonToRoster(jsonSampleRoster);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sampleRoster;
    }
}
