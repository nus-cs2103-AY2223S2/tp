package seedu.address.model.person.fields.subfields;


import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import seedu.address.model.person.fields.Field;

/**
 * Represents a Person's singular mod taken in the address book.
 */
public class NusMod extends Field {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";
    public static final String MODULELIST_FILE_PATH = "data/moduleList.json";
    private static final Map<String, Boolean> MODULE_MAP = new HashMap<>();

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

    /**
     * Constructs an {@code NusMod}.
     * @param modsString A valid module code.
     */
    public NusMod(String modsString) {
        super(modsString);
        checkArgument(isValidModName(modsString), MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks whether a module is a valid NUSMod by comparing the String name to the module list data
     *
     * @param trimmedMod The module name to be checked
     * @return false if the module does not exist in NUSMods
     */
    public static boolean isValidModName(String trimmedMod) {
        return MODULE_MAP.containsKey(trimmedMod);
    }


    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof NusMod
                && this.value.equals(((NusMod) other).value));
    }

}
