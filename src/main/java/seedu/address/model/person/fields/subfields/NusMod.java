package seedu.address.model.person.fields.subfields;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Person's singular mod taken in the address book.
 */
public class NusMod {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";
    public static final String MODULELIST_FILE_PATH = "data/moduleList.json";
    private static final Map<String, Boolean> MODULE_MAP = new HashMap<>();
    public final String name;

    static {
        try {
            FileInputStream inputStream = new FileInputStream(MODULELIST_FILE_PATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"moduleCode\":")) {
                    String moduleCode = line.split(":")[1].trim().replace("\"", "");
                    MODULE_MAP.put(moduleCode, true);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        return MODULE_MAP.containsKey(trimmedTag);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof NusMod
                && this.name.equals(((NusMod) other).name));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
