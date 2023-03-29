package vimification.internal.command.logic;

import java.util.HashSet;
import java.util.Set;

public class DeleteFieldsRequest {

    private boolean deleteDeadline = false;
    private Set<String> deletedLabels = new HashSet<>();

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
}
