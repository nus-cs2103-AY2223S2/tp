package seedu.vms.model.vaccination;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.vms.commons.util.AppUtil;


/**
 * Represents a vaccination name. The validity of the name is enforced.
 */
public class VaxName {
    public static final String MESSAGE_CONSTRAINT = "Vaccination name should not be blank, "
            + "and should only contain alphanumeric characters including brackets and dashes";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\(\\)\\[\\]\\{\\}<>\\-_ ]{1,30}";


    private final String name;


    /**
     * Constructs a {@code VaxName}.
     *
     * @param name - name of vaccination.
     */
    @JsonCreator
    public VaxName(String name) {
        Objects.requireNonNull(name);
        AppUtil.checkArgument(isValidName(name), MESSAGE_CONSTRAINT);
        this.name = name.strip();
    }

    public static boolean isValidName(String name) {
        return name.strip().matches(VALIDATION_REGEX);
    }


    @JsonValue
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other instanceof VaxName
                && name.equals(((VaxName) other).name);
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
