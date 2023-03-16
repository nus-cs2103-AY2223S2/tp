package seedu.address.model.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allows the TA to create notes in an event
 */
public class Note {

    public static final String EMPTY_CONTENT = "This note is empty";
    protected static final int LENGTH_LIMIT = 200;
    private static final String PATTERN = "@[a-z]+";
    private String content;
    private List<String> names = new ArrayList<>(20); // referenced up to 20 students in the notes

    /**
     * Initates the note object with actual content and parsed referees
     * @param content {@code String} object that represents the note contents
     */
    public Note(String content) {
        this.content = validateContent(content);
        decomposePersonNames(content);
    }

    /**
     * Initiates the note object with empty content that can be filled up later
     */
    public Note() {
        this.content = EMPTY_CONTENT;
    }

    private void decomposePersonNames(String note) {
        try {
            Pattern pattern = Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(note);
            while (matcher.find()) {
                String name = matcher.group().toString();
                name = name.substring(1, name.length()); // get rid of @ char
                this.names.add(name);
            }
        } catch (Exception e) { // TODO: better exception handling
            e.printStackTrace();
        }
    }

    private boolean isEmpty() {
        return this.content.equals(EMPTY_CONTENT);
    }

    private String validateContent(String note) {
        // utf-8 check left to ui input handler
        if (note.isEmpty()) {
            note = EMPTY_CONTENT;
        } else if (note.length() >= LENGTH_LIMIT) {
            note = note.substring(0, LENGTH_LIMIT);
        }
        return note;
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getReferees() {
        return this.names;
    }

    /**
     * Resets the content with new note content
     * @param newContent New note content to replace the current content
     */
    public void setContent(String newContent) {
        this.content = validateContent(newContent);
        decomposePersonNames(newContent);
    }

    /**
     * Clears the content all at once but retains the object
     */
    public void clear() {
        this.content = EMPTY_CONTENT;
        this.names.clear();
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return EMPTY_CONTENT;
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("Contents: \n")
                .append(getContent());
        List<String> referees = getReferees();
        if (!referees.isEmpty()) {
            builder.append(";\n Relevant personnel:");
            referees.stream().map(s -> " " + s).forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Makes guide of how to leave a note for an event with focus on mentioning people
     * @return A {@code String} of note-adding guide
     */
    public String help() {
        return "Hint: Use @ to refer to students in the event"; // TODO: Help format to be determined later. @Jiatong
    }
}
