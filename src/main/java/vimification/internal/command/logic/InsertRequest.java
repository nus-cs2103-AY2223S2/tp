package vimification.internal.command.logic;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a structure that stores relevant information for the execution of
 * {@link InsertCommand}.
 */
public class InsertRequest {

    private LocalDateTime insertedDeadline = null;
    private Set<String> insertedLabels = new HashSet<>();

    public LocalDateTime getInsertedDeadline() {
        return insertedDeadline;
    }

    public void setInsertedDeadline(LocalDateTime insertedDeadline) {
        this.insertedDeadline = insertedDeadline;
    }

    public Set<String> getInsertedLabels() {
        return insertedLabels;
    }

    public void setInsertedLabels(Set<String> insertedLabels) {
        this.insertedLabels = insertedLabels;
    }

    @Override
    public int hashCode() {
        return Objects.hash(insertedDeadline, insertedLabels);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InsertRequest)) {
            return false;
        }
        InsertRequest otherRequest = (InsertRequest) other;
        return Objects.equals(insertedDeadline, otherRequest.insertedDeadline)
                && Objects.equals(insertedLabels, otherRequest.insertedLabels);
    }

    @Override
    public String toString() {
        return "InsertRequest [insertedDeadline=" + insertedDeadline + ", insertedLabels="
                + insertedLabels + "]";
    }
}
