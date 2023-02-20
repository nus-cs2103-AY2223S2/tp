package seedu.address.model.person.fields;

import java.util.ArrayList;

public class NusMod {

    public static final String MESSAGE_CONSTRAINTS = "Must have 4 numbers";
    private final String name;

    public NusMod(String modsString) {
        this.name = modsString;
    }

    public static boolean isValidModName(String trimmedTag) {
        return true;
    }
}
