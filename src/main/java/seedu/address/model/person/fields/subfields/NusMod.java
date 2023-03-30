package seedu.address.model.person.fields.subfields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.fields.Field;

/**
 * Represents a Person's singular mod taken in the address book.
 */
public class NusMod extends Field {

    public static final String MESSAGE_CONSTRAINTS = "Module codes should exist in NUS' NUSMods list";
    public static final String MODULES_FILE_PATH = "/data/modules.json";
    private static final Set<String> MODULE_MAP = new HashSet<>();

    static {
        try (Reader reader = new InputStreamReader(Objects
                .requireNonNull(NusMod.class.getResourceAsStream(MODULES_FILE_PATH)))) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("\"moduleCode\":")) {
                    String moduleCode = line.split(":")[1].trim().replace("\"", "");
                    MODULE_MAP.add(moduleCode);
                }
            }
            bufferedReader.close();
        } catch (IOException | NullPointerException e) {
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
     * Checks whether a module is a valid NUSMod by comparing with the module list data.
     *
     * @param moduleCode The module code to be checked.
     * @return true if the module exists in NUSMods;
     *         false otherwise.
     */
    public static boolean isValidModName(String moduleCode) {
        return MODULE_MAP.contains(moduleCode);
    }


    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof NusMod
                && this.value.equals(((NusMod) other).value));
    }

}
