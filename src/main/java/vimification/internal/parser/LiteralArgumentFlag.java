package vimification.internal.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a literal flag that can be set on different commands.
 */
public class LiteralArgumentFlag implements ArgumentFlag {

    private final String shortForm;
    private final String longForm;

    /**
     * Creates a new {@code LiteralArgumentFlag} with the specified forms
     *
     * @param shortForm the short form of the flag, can be null to represent that the flag has no
     *        short form
     * @param longForm the long form of the flag, can be null to represent that the flag has no long
     *        form
     */
    public LiteralArgumentFlag(String shortForm, String longForm) {
        this.shortForm = shortForm;
        this.longForm = longForm;
    }

    public String getShortForm() {
        return shortForm;
    }

    public String getLongForm() {
        return longForm;
    }

    /**
     * Gets all possible forms of this flag.
     *
     * @return a list contains all possible forms of this flag.
     */
    public List<String> getForms() {
        List<String> forms = new ArrayList<>(2);
        if (shortForm != null) {
            forms.add(shortForm);
        }
        if (longForm != null) {
            forms.add(longForm);
        }
        return forms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortForm, longForm);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LiteralArgumentFlag)) {
            return false;
        }
        LiteralArgumentFlag otherFlag = (LiteralArgumentFlag) other;
        return Objects.equals(shortForm, otherFlag.shortForm)
                && Objects.equals(longForm, otherFlag.longForm);
    }

    @Override
    public String toString() {
        return "LiteralArgumentFlag [shortForm=" + shortForm + ", longForm=" + longForm + "]";
    }
}
