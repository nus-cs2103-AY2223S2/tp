package seedu.address.model.tag;

/**
 * Represents medical conditions that an elderly might have.
 *
 * @author wz2k
 */
public class MedicalConditionTag extends Tag {
    /**
     * Represents the level of importance.
     */
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    /* Additional description of the condition */
    private String notes;

    /* Signifies whether attention is needed */
    private boolean requiresAttention;

    /* The importance of the condition */
    private Priority priority;

    /**
     * Constructs a {@code MedicalConditionTag}
     *
     * @param tagName A valid tag name.
     * @param notes Additional description.
     * @param requiresAttention Whether attention is needed.
     * @param priority Importance level.
     */
    public MedicalConditionTag(String tagName, String notes, boolean requiresAttention, Priority priority) {
        super(tagName);
        this.notes = notes;
        this.requiresAttention = requiresAttention;
        this.priority = priority;
    }

    /**
     * Returns the notes of the medical condition, if present.
     *
     * @return Medical condition notes.
     */
    public String getNotes() {
        if (notes == null) {
            return "No notes added.";
        }

        return notes;
    }

    /**
     * Returns whether attention is required.
     *
     * @return True if attention is required and false otherwise.
     */
    public boolean isRequiresAttention() {
        return requiresAttention;
    }

    /**
     * Returns the priority level of the medical condition.
     *
     * @return Priority level.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Changes the notes of the medical condition to the given notes.
     *
     * @param notes The new notes.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Changes attention level to required.
     */
    public void setAttentionRequired() {
        this.requiresAttention = true;
    }

    /**
     * Changes attention level to not required.
     */
    public void setAttentionNotRequired() {
        this.requiresAttention = false;
    }

    /**
     * Changes the priority of the medical condition to the given priority.
     *
     * @param priority The new priority.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns the full details of the medical condition.
     *
     * @return Full details.
     */
    public String toFullString() {
        String requiresAttentionString = String.valueOf(requiresAttention);
        String priorityString = priority.name();

        return super.toString() + " "
                + requiresAttentionString + " "
                + priorityString + " "
                + notes;
    }
}
