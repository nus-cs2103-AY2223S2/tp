package tfifteenfour.clipboard.model.util;

import static tfifteenfour.clipboard.storage.JsonRosterStorage.jsonToRoster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import tfifteenfour.clipboard.commons.util.FileUtil;
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
            FileUtil.createIfMissing(sampleFilePath);
            // Read json String from the json file stored in /resources/assets
            BufferedReader reader = new BufferedReader(new InputStreamReader(sampleResourceStream));
            String contents = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));

            // Write json String into /data/sampleRoster.json
            BufferedWriter writer = new BufferedWriter(new FileWriter(sampleFilePath.toString()));
            writer.write(contents);
            writer.close();

            // Create a roster from /data/sampleRoster.json
            File file = new File(sampleFilePath.toString());
            SerializedRoster jsonSampleRoster = mapper.readValue(file, SerializedRoster.class);
            sampleRoster = jsonToRoster(jsonSampleRoster);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sampleRoster;
    }

    public static ReadOnlyRoster getTestRoster(Path sampleFilePath, InputStream sampleResourceStream) {

        Roster sampleRoster = new Roster();

        try {
            File file = new File(sampleFilePath.toString());
            SerializedRoster jsonSampleRoster = mapper.readValue(file, SerializedRoster.class);
            sampleRoster = jsonToRoster(jsonSampleRoster);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sampleRoster;
    }
}
