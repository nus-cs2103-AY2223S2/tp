package vimification.internal.parser;

import java.util.Objects;

public class ArgumentFlag {

    private final String shortForm;
    private final String longForm;

    public ArgumentFlag(String shortForm, String longForm) {
        this.shortForm = shortForm;
        this.longForm = longForm;
    }

    public String getShortForm() {
        return shortForm;
    }

    public String getLongForm() {
        return longForm;
    }

    @Override
    public String toString() {
        return String.format("Prefix [shortForm: %s, longForm: %s]", shortForm, longForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortForm, longForm);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ArgumentFlag)) {
            return false;
        }
        ArgumentFlag otherFlag = (ArgumentFlag) other;
        return Objects.equals(shortForm, otherFlag.shortForm)
                && Objects.equals(longForm, otherFlag.longForm);
    }
}
