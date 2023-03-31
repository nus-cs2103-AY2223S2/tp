package vimification.internal.command.ui;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import vimification.model.task.Priority;
import vimification.model.task.Status;

public class FilterRequest {

    public enum Mode {
        DEFAULT, AND, OR
    }

    private Status searchedStatus = null;
    private Priority searchedPriority = null;
    private String searchedKeyword = null;
    private Set<String> searchedLabels = new HashSet<>();
    private LocalDateTime searchedDeadlineBefore;
    private LocalDateTime searchedDeadlineAfter;
    private Mode mode = Mode.DEFAULT;

    public FilterRequest() {}

    public Status getSearchedStatus() {
        return searchedStatus;
    }

    public void setSearchedStatus(Status searchedStatus) {
        this.searchedStatus = searchedStatus;
    }

    public Priority getSearchedPriority() {
        return searchedPriority;
    }

    public void setSearchedPriority(Priority searchedPriority) {
        this.searchedPriority = searchedPriority;
    }

    public String getSearchedKeyword() {
        return searchedKeyword;
    }

    public void setSearchedKeyword(String searchedKeyword) {
        this.searchedKeyword = searchedKeyword;
    }

    public Set<String> getSearchedLabels() {
        return searchedLabels;
    }

    public void setSearchedLabels(Set<String> searchedLabels) {
        this.searchedLabels = searchedLabels;
    }

    public LocalDateTime getSearchedDeadlineBefore() {
        return searchedDeadlineBefore;
    }

    public void setSearchedDeadlineBefore(LocalDateTime searchedDeadlineBefore) {
        this.searchedDeadlineBefore = searchedDeadlineBefore;
    }

    public LocalDateTime getSearchedDeadlineAfter() {
        return searchedDeadlineAfter;
    }

    public void setSearchedDeadlineAfter(LocalDateTime searchedDeadlineAfter) {
        this.searchedDeadlineAfter = searchedDeadlineAfter;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
