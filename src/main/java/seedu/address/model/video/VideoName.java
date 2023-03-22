package seedu.address.model.video;

import seedu.address.model.Name;

/**
 * Represents a video's name in the tracker.<p>
 * Guarantees: immutable, is valid as declared in {@link #isValidName(String)}.
 */
public class VideoName extends Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Video names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code VideoName}.
     *
     * @param name A valid name.
     */
    public VideoName(String name) {
        super(name, MESSAGE_CONSTRAINTS);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof VideoName;
    }

}
