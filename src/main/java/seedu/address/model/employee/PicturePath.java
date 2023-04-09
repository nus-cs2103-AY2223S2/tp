package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the filepath of an employee's picture, that can be used to locate the picture in the file system.
 * Guarantees: is valid as declared in {@link #isValidPicturePath(String)}.
 */
public class PicturePath {

    public static final String MESSAGE_CONSTRAINTS =
            "All employee pictures should be stored in data/employeepictures in .png format.";

    // The filepath must start with "data/employeepictures/" and the file name must end in ".png".
    public static final String VALID_DIRECTORY = "data/employeepictures/";
    public static final String VALID_EXTENSION = ".png";

    public final String value;

    /**
     * Constructs a {@code PicturePath}.
     *
     * @param picturePath A valid filepath.
     */
    public PicturePath(String picturePath) {
        requireNonNull(picturePath);
        checkArgument(isValidPicturePath(picturePath), MESSAGE_CONSTRAINTS);
        this.value = picturePath;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPicturePath(String test) {
        return ((test.startsWith(VALID_DIRECTORY) && test.endsWith(VALID_EXTENSION)) || test.equals(""));
    }

    /**
     * Converts the filepath from a String object into a Path object.
     *
     * @return A Path object representing the filepath.
     */
    public Path toPath() {
        return Paths.get(value);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PicturePath // instanceof handles nulls
                && value.equals(((PicturePath) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
