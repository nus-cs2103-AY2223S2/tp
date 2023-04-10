package seedu.address.model.person.fields.subfields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
        InputStream inputStream = Objects.requireNonNull(NusMod.class.getResourceAsStream(MODULES_FILE_PATH));
        try (Reader reader = new InputStreamReader(inputStream)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.lines().forEach(line -> {
                if (line == null || !line.contains("moduleCode")) {
                    // not a line with a module code
                    return;
                }
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    return;
                }

                String moduleCode = parts[1].replace("\"", "").trim();
                if (moduleCode.isBlank()) {
                    return;
                }
                MODULE_MAP.add(moduleCode);
            });
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
        checkArgument(isModuleCodeValid(modsString), MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks whether a module is a valid NUSMod by comparing with the module list data.
     *
     * @param moduleCode The module code to be checked.
     * @return true if the module exists in NUSMods;
     *         false otherwise.
     */
    public static boolean isModuleCodeValid(String moduleCode) {
        return MODULE_MAP.contains(moduleCode);
    }


    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof NusMod
                && this.value.equals(((NusMod) other).value));
    }

}
