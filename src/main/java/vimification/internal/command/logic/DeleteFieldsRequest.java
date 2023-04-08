package vimification.internal.command.logic;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a structure that stores relevant information for the execution of
 * {@link DeleteFieldsCommand}.
 */
public class DeleteFieldsRequest {

    private boolean deleteDeadline = false;
    private Set<String> deletedLabels = new HashSet<>();

    /**
     * Initialize a new instance, with default values.
     */
    public DeleteFieldsRequest() {}

    public boolean shouldDeleteDeadline() {
        return deleteDeadline;
    }

    public void setDeleteDeadline(boolean deleteDeadline) {
        this.deleteDeadline = deleteDeadline;
    }

    public Set<String> getDeletedLabels() {
        return deletedLabels;
    }

    public void setDeletedLabels(Set<String> deletedLabels) {
        this.deletedLabels = deletedLabels;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteDeadline, deletedLabels);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteFieldsRequest)) {
            return false;
        }
        DeleteFieldsRequest otherRequest = (DeleteFieldsRequest) other;
        return deleteDeadline == otherRequest.deleteDeadline
                && Objects.equals(deletedLabels, otherRequest.deletedLabels);
    }

    @Override
    public String toString() {
        return "DeleteFieldsRequest [deleteDeadline=" + deleteDeadline + ", deletedLabels="
                + deletedLabels + "]";
    }
}
