package tfifteenfour.clipboard.model.util;

import static tfifteenfour.clipboard.storage.JsonRosterStorage.jsonToRoster;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.tag.Tag;
import tfifteenfour.clipboard.storage.serializedclasses.SerializedRoster;

/**
 * Contains utility methods for populating {@code Roster} with sample data.
 */
public class SampleDataUtil {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static ReadOnlyRoster getSampleRoster(Path sampleFilePath, InputStream sampleResourceStream) {

        Roster sampleRoster = new Roster();

        try {
            // FileUtil.createIfMissing(sampleFilePath);
            // // Read json String from the json file stored in /resources/assets
            // BufferedReader reader = new BufferedReader(new InputStreamReader(sampleResourceStream));
            // String contents = reader.lines()
            //         .collect(Collectors.joining(System.lineSeparator()));

            // // Write json String into /data/sampleRoster.json
            // BufferedWriter writer = new BufferedWriter(new FileWriter(sampleFilePath.toString()));
            // writer.write(contents);
            // writer.close();

            // Create a roster from /data/sampleRoster.json
            File file = new File(sampleFilePath.toString());
            SerializedRoster jsonSampleRoster = mapper.readValue(file, SerializedRoster.class);
            sampleRoster = jsonToRoster(jsonSampleRoster);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sampleRoster;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a module set containing the list of strings given.
     */
    public static Set<Course> getModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(Course::new)
                .collect(Collectors.toSet());
    }
}
