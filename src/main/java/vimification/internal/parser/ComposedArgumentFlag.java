package vimification.internal.parser;

import java.util.Objects;
import java.util.Set;

public class ComposedArgumentFlag implements ArgumentFlag {

    private final Set<LiteralArgumentFlag> flags;
    private LiteralArgumentFlag actualFlag = null;

    public ComposedArgumentFlag(LiteralArgumentFlag... flags) {
        this.flags = Set.of(flags);
    }

    public ComposedArgumentFlag(Set<LiteralArgumentFlag> flags) {
        this.flags = flags;
    }

    public Set<LiteralArgumentFlag> getFlags() {
        return flags;
    }

    public ComposedArgumentFlag clone() {
        return new ComposedArgumentFlag(flags);
    }

    public LiteralArgumentFlag getActualFlag() {
        return actualFlag;
    }

    public void setActualFlag(LiteralArgumentFlag actualFlag) {
        this.actualFlag = actualFlag;
    }

    @Override
    public String toString() {
        return "ComposedArgumentFlag [flags=" + flags + "]";
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

}
