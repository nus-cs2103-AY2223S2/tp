package seedu.address.model.event;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.event.exceptions.NoteLengthException;


/**
 * Allows the TA to create notes in an event
 */
public class Note {

    public static final String EMPTY_CONTENT = "This note is empty";
    public static final int LENGTH_LIMIT = 200;
    private static final String PATTERN = "@[a-z]+";
    private String content;
    private final List<String> names = new ArrayList<>(20); // referenced up to 20 people in the notes

    /**
     * Initiates the note object with actual content and parsed referees
     * @param content {@code String} object that represents the note contents
     */
    public Note(String content) throws NoteLengthException {
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
                String name = matcher.group();
                name = name.substring(1); // get rid of @ char
                this.names.add(name);
            }
        } catch (Exception e) { // TODO: better exception handling
            e.printStackTrace();
        }
    }

    private String validateContent(String note) throws NoteLengthException {
        // utf-8 check left to ui input handler
        if (note.isEmpty()) {
            note = EMPTY_CONTENT;
        } else if (note.length() > LENGTH_LIMIT) {
            throw new NoteLengthException(LENGTH_LIMIT, note.length());
        }
        return note;
    }

    private boolean isEmpty() {
        return this.content.equals(EMPTY_CONTENT);
    }

    public String getContent() {
        return this.content.replace("\n", "");
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && content.equals(((Note) other).content));
    }

    /**
     * Copy current note and return a new one
     * @return A copied note
     */
    public Note copy() {
        if (isEmpty()) {
            return new Note();
        }
        return new Note(getContent());
    }
}
