package tfifteenfour.clipboard.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileLoader {
    public static String loadJsonFileAsString(String filePath) {
        StringBuilder jsonString = new StringBuilder();

        try  {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                jsonString.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString.toString();
    }

    public static String readJsonFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filename);
        Object obj = mapper.readValue(file, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
