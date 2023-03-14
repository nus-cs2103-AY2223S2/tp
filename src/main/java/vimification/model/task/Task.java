package vimification.model.task;

import java.util.HashSet;
import java.util.Set;

public class Task {
    private final Title title;
    private final Description description;
    private String deadline;

    private Set<String> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, Description description) {
        // requireAllNonNull(name, phone, email, address, tags);
        this.title = title;
        this.description = description;
        // this.deadline = deadline;
        // this.tags.addAll(tags);
    }


    public Title getTitle() {
        return title;
    }


    public Description getDescription() {
        return description;
    }


    public String getDeadline() {
        return deadline;
    }


    public Set<String> getTags() {
        return tags;
    }


}
