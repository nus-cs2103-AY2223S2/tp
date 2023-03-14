package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allows the TA to create notes in an event
 */
public class Note {

    private static String EMPTY_CONTENT = "This note is empty";
    private String content;
    private List<String> referees = new ArrayList<>(20); // referenced up to 20 students in the notes

    /**
     * Initiates the note object with empty content that can be filled up later
     */
    public Note() {
        this.content = EMPTY_CONTENT;
    }

    /**
     * Initates the note object with actual content and parsed referees
     * @param note {@code String} object that represents the note contents
     */
    public Note(String note) {
        requireNonNull(note);
        this.content = note;
        decomposePersons(note);
    }

    private void decomposePersons(String note) {
        try{
            Pattern pattern = Pattern.compile("@[a-z]+", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(note);
            while (matcher.find()) {
                this.referees.add(matcher.group());
            }
        } catch (Exception e) { // TODO: better exception handling
            e.printStackTrace();
        }
    }

    private boolean isEmpty() {
        return this.content.equals(EMPTY_CONTENT);
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getReferees() {
        return this.referees;
    }

    /**
     * Makes guide of how to leave a note for an event with focus on mentioning people
     * @return A {@code String} of note-adding guide
     */
    public String help() {
        return "Hint: Use @ to refer to students in the event"; // TODO: Help format to be determined later. @Jiatong
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return EMPTY_CONTENT;
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("Notes: \n")
                .append(getContent());
        List<String> referees = getReferees();
        if (!referees.isEmpty()) {
            builder.append(";\n Referees: ");
            referees.forEach(builder::append);
        }
        return builder.toString();
    }
}
