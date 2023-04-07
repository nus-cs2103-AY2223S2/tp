package vimification.internal.parser;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a flag that is composed from different literal flags.
 */
public class ComposedArgumentFlag implements ArgumentFlag {

    private final Set<LiteralArgumentFlag> flags;
    private LiteralArgumentFlag actualFlag = null;

    /**
     * Creates a new {@code ComposedArgumentFlag} instance.
     *
     * @param flags different literals that can be used for this flag
     */
    public ComposedArgumentFlag(LiteralArgumentFlag... flags) {
        this.flags = Set.of(flags);
    }

    /**
     * Creates a new {@code ComposedArgumentFlag} instance.
     *
     * @param flags different literals that can be used for this flag
     */
    public ComposedArgumentFlag(Set<LiteralArgumentFlag> flags) {
        this.flags = flags;
    }

    public Set<LiteralArgumentFlag> getFlags() {
        return flags;
    }

    /**
     * Clones this composed flag, and sets the actual literal of the created flag.
     *
     * @param actualFlag the literal flag used to represents this composed flag
     * @return a cloned version of this flag, with the actual flag being set
     */
    public ComposedArgumentFlag cloneWith(LiteralArgumentFlag actualFlag) {
        ComposedArgumentFlag clonedFlag = new ComposedArgumentFlag(flags);
        clonedFlag.actualFlag = actualFlag;
        return clonedFlag;
    }

    public LiteralArgumentFlag getActualFlag() {
        return actualFlag;
    }

    public void setActualFlag(LiteralArgumentFlag actualFlag) {
        if (!flags.contains(actualFlag)) {
            throw new IllegalArgumentException("Invalid flag used: " + actualFlag);
        }
        this.actualFlag = actualFlag;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(flags);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComposedArgumentFlag)) {
            return false;
        }
        ComposedArgumentFlag otherFlag = (ComposedArgumentFlag) other;
        return Objects.equals(flags, otherFlag.flags);
    }

    @Override
    public String toString() {
        return "ComposedArgumentFlag [flags=" + flags + ", actualFlag=" + actualFlag + "]";
    }
}
