package seedu.address.model.powercard;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Powercard in the Deck
 */
public class Powercard {

    private String question;
    private String answer;
    private int numCorrect;
    private int numWrong;

    public Powercard(String question, String answer) {
        requireAllNonNull(question, answer);
        this.question = question;
        this.answer = answer;
        this.numCorrect = 0;
        this.numWrong = 0;
    }
}
