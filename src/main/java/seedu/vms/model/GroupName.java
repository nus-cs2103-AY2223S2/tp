package seedu.vms.model;

import java.text.Collator;
import java.util.Objects;

import seedu.vms.commons.util.AppUtil;


/**
 * Represents a group name. Ensures that the name is valid.
 */
public class GroupName implements Comparable<GroupName> {
    public static final String MESSAGE_CONSTRAINTS = "Group name should not be blank, "
            + "and should only contain alphanumeric characters including brackets and dashes";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\(\\)\\[\\]\\{\\}\\-_ ]{1,30}";

    private final Collator collator;

    private final String name;


    /**
     * Constructs a {@code GroupName}.
     *
     * @param name - name of the group.
     */
    public GroupName(String name) {
        Objects.requireNonNull(name);
        AppUtil.checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name.strip();
        collator = Collator.getInstance();
        collator.setStrength(Collator.TERTIARY);
    }

    public static boolean isValidName(String name) {
        return name.strip().matches(VALIDATION_REGEX);
    }


    public String getName() {
        return name;
    }


    @Override
    public int compareTo(GroupName other) {
        return collator.compare(name, other.name);
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
        return other instanceof GroupName
                && name.equals(((GroupName) other).name);
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
