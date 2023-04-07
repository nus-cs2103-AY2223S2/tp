package vimification.internal.command.logic;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import vimification.internal.parser.Pair;
import vimification.model.task.Priority;
import vimification.model.task.Status;

/**
 * Represents a structure that stores relevant information for the execution of {@link EditCommand}.
 */
public class EditRequest {

    private String editedTitle = null;
    private LocalDateTime editedDeadline = null;
    private Set<Pair<String, String>> editedLabels = new HashSet<>();
    private Priority editedPriority = null;
    private Status editedStatus = null;

    /**
     * Creates a new {@code EditRequest} instance with default values.
     */
    public EditRequest() {}

    public String getEditedTitle() {
        return editedTitle;
    }

    public void setEditedTitle(String editedTitle) {
        this.editedTitle = editedTitle;
    }

    public LocalDateTime getEditedDeadline() {
        return editedDeadline;
    }

    public void setEditedDeadline(LocalDateTime editedDeadline) {
        this.editedDeadline = editedDeadline;
    }

    public Set<Pair<String, String>> getEditedLabels() {
        return editedLabels;
    }

    public void setEditedLabels(Set<Pair<String, String>> editedLabels) {
        this.editedLabels = editedLabels;
    }

    public Priority getEditedPriority() {
        return editedPriority;
    }

    public void setEditedPriority(Priority editedPriority) {
        this.editedPriority = editedPriority;
    }

    public Status getEditedStatus() {
        return editedStatus;
    }

    public void setEditedStatus(Status editedStatus) {
        this.editedStatus = editedStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(editedTitle, editedDeadline, editedLabels, editedPriority,
                editedStatus);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditRequest)) {
            return false;
        }
        EditRequest otherRequest = (EditRequest) other;
        return Objects.equals(editedTitle, otherRequest.editedTitle)
                && Objects.equals(editedDeadline, otherRequest.editedDeadline)
                && Objects.equals(editedLabels, otherRequest.editedLabels)
                && Objects.equals(editedPriority, otherRequest.editedPriority)
                && Objects.equals(editedStatus, otherRequest.editedStatus);
    }

    @Override
    public String toString() {
        return "EditRequest [editedTitle=" + editedTitle + ", editedDeadline=" + editedDeadline
                + ", editedLabels=" + editedLabels + ", editedPriority=" + editedPriority
                + ", editedStatus=" + editedStatus + "]";
    }
}
