package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's Progress status in the Library.
 * Guarantees: immutable; is valid as declared in {@link #isValidProgress(String[])}
 */
public class Progress {



    public static final String MESSAGE_CONSTRAINTS =
            "Progress should have only 3 values. Each of which is either a numeric or ~. The values cannot all be ~.";
    public static final String VALIDATION_REGEX = "^(~|0|[1-9][0-9]*)$";

    public final String volume;
    public final String chapter;
    public final String page;

    /**
     * Constructs a {@code Progress}.
     *
     * @param progress a valid progress
     */
    public Progress(String[] progress) {
        requireNonNull(progress);
        checkArgument(isValidProgress(progress), MESSAGE_CONSTRAINTS);

        volume = progress[0];
        chapter = progress[1];
        page = progress[2];
    }

    /**
     * Returns true if a given string is a valid progress.
     */
    public static boolean isValidProgress(String[] tests) {
        if (tests.length != 3) {
            return false;
        }

        for (int i = 0; i != 3; ++i) {
            if (!tests[i].matches(VALIDATION_REGEX)) {
                return false;
            }
        }

        if (tests[0] == "~" && tests[1] == "~" && tests[2] == "~") {
            return false;
        }

        return true;
    }

    public String getVolume() {
        return volume;
    }

    public String getChapter() {
        return chapter;
    }
    public String getPage() {
        return page;
    }

    @Override
    public String toString() {
        String accumulator = "";

        if (!volume.equals("~")) {
            accumulator += "Vol. " + volume + " ";
        }

        if (!chapter.equals("~")) {
            accumulator += "Ch. " + chapter + " ";
        }

        if (!page.equals("~")) {
            accumulator += "Pg. " + page;
        }

        return accumulator.trim();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Progress // instanceof handles nulls
                && volume.equals(((Progress) other).volume)
                && chapter.equals(((Progress) other).chapter)
                && page.equals(((Progress) other).page)); // state check
    }

    /**
     * Gets the command string for this Progress object.
     *
     * @return the command string for this Progress object.
     */
    public String getDetails() {
        return volume + " " + chapter + " " + page;
    }

    @Override
    public int hashCode() {
        return (volume + chapter + page).hashCode();
    }

}
