package vimification.internal.command.logic;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
}
