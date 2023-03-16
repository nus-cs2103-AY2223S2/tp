package seedu.address.model.person.fields;

import java.io.*;

/**
 * Represents a Person's singular mod taken in the address book.
 */
public class NusMod {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";
    public static final String MODULELIST_FILE_PATH = "data/moduleList.json";
    public final String name;

    public NusMod(String modsString) {
        this.name = modsString;
    }

    /**
     * Checks whether a module is a valid NUSMod by comapring the String name to the module list data
     *
     * @param trimmedTag The module name to be checked
     * @return false if the module does not exist in NUSMods
     */
    public static boolean isValidModName(String trimmedTag) {
        try {
            FileInputStream inputStream = new FileInputStream(MODULELIST_FILE_PATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"moduleCode\":")) {
                    String moduleCode = line.split(":")[1].trim().replace("\"", "");
                    if (moduleCode.equals(trimmedTag)) {
                        reader.close();
                        return true;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
