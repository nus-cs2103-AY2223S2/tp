package vimification.internal.parser;

import java.util.Objects;

public class ArgumentFlag {

    private final String shortForm;
    private final String longForm;
    private final int maxCount; // sigh, Java don't have unsigned integer

    public ArgumentFlag(String shortForm, String longForm, int maxCount) {
        this.shortForm = shortForm;
        this.longForm = longForm;
        this.maxCount = maxCount;
    }

    public ArgumentFlag(String shortForm, String longForm) {
        this(shortForm, longForm, 1);
    }

    public String getShortForm() {
        return shortForm;
    }

    public String getLongForm() {
        return longForm;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public ArgumentFlag withMaxCount(int maxCount) {
        return this.maxCount == maxCount
                ? this
                : new ArgumentFlag(shortForm, longForm, maxCount);
    }

    @Override
    public String toString() {
        return String.format(
                "Prefix [shortForm: %s; longForm: %s; maxCount: %d]",
                shortForm, longForm, maxCount);
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
