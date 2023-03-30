package vimification.internal.command.logic;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import vimification.internal.parser.Pair;
import vimification.model.task.Priority;
import vimification.model.task.Status;

public class EditRequest {

    private String editedTitle = null;
    private LocalDateTime editedDeadline = null;
    private Set<Pair<String, String>> editedLabels = new HashSet<>();
    private Priority editedPriority = null;
    private Status editedStatus = null;

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
}
