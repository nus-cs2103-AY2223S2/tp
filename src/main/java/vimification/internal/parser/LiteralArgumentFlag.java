package vimification.internal.parser;

import java.util.Objects;

public class LiteralArgumentFlag implements ArgumentFlag {

    private final String shortForm;
    private final String longForm;

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
