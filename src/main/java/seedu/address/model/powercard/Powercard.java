package seedu.address.model.powercard;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Powercard in the Deck
 */
public class Powercard {

    private Question question;
    private Answer answer;
    private int numCorrect;
    private int numWrong;

    /**
     * Constructor for Powercard Class
     * Every field must be present and not null.
     *
     * @param question
     * @param answer
     */
    public Powercard(Question question, Answer answer) {
        requireAllNonNull(question, answer);
        this.question = question;
        this.answer = answer;
        this.numCorrect = 0;
        this.numWrong = 0;
    }

    public Question getQuestion() {
        return this.question;
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public void changeQuestion(Question question) {
        this.question = question;
    }

    public void changeAnswer(Answer answer) {
        this.answer = answer;
    }

    public void markCorrect() {
        this.numCorrect++;
    }

    public void markWrong() {
        this.numWrong++;
    }

    /**
     * Return question to user. To be used during review sessions.
     *
     * @return question to be reviewed.
     */
    public String showQuestion() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Question: ")
                .append(getQuestion());

        return builder.toString();
    }

    /**
     * Return answer to user. To be used during review sessions.
     *
     * @return answer to be reviewed.
     */
    public String showAnswer() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Answer: ")
                .append(getAnswer());

        return builder.toString();
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Question: ")
                .append(getQuestion())
                .append("; Answer: ")
                .append(getAnswer());

        return builder.toString();
    }
}
