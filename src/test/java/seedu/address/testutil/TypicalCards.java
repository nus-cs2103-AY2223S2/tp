package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_GRAVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_GRAVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MasterDeck;
import seedu.address.model.card.Card;

/**
 * A utility class containing a list of {@code Card} objects to be used in tests.
 */
public class TypicalCards {

    public static final Card LOOP = new CardBuilder().withQuestion("What is a loop")
            .withAnswer("A construct that repeats instructions until a condition is met")
            .withTags("Hard").withDeck("Programming Concepts").build();
    public static final Card VARIABLE = new CardBuilder().withQuestion("What is a variable")
            .withAnswer("A named memory location that stores a value")
            .withTags("Hard").withDeck("Programming Concepts").build();
    public static final Card ATOM = new CardBuilder().withQuestion("What is the structure of an atom")
            .withAnswer("Atoms consist of a nucleus containing protons and neutrons"
                    + ", surrounded by electrons in shells or energy levels").withDeck("Science").build();
    public static final Card LIFE = new CardBuilder().withQuestion("What is the basic unit of life")
            .withAnswer("The cell is the basic unit of life").withTags("Medium")
            .withDeck("Science").build();
    public static final Card PRESIDENT = new CardBuilder()
            .withQuestion("Who was the first president of the United States")
            .withAnswer("George Washington").withDeck("History").build();
    public static final Card INDEPENDENCE = new CardBuilder().withQuestion("When did Singapore gain independence")
            .withAnswer("9 August 1965").withDeck("History").build();
    public static final Card EARTHQUAKE = new CardBuilder().withQuestion("What is an earthquake")
            .withAnswer("An earthquake is a sudden and rapid shaking "
                    + "of the earth caused by the shifting of tectonic plates")
            .withDeck("Geography").build();

    // Manually added
    public static final Card SMOG = new CardBuilder().withQuestion("What is smog")
            .withAnswer("A mixture of smoke and fog, caused by pollutants in the atmosphere")
            .withDeck("Geography").build();
    public static final Card FRACTION = new CardBuilder().withQuestion("What is a fraction")
            .withAnswer("A part of a whole expressed as a ratio of two numbers")
            .withDeck("Math").build();

    // Manually added - Card's details found in {@code CommandTestUtil}
    public static final Card GRAVITY = new CardBuilder().withQuestion(VALID_QUESTION_GRAVITY)
            .withAnswer(VALID_ANSWER_GRAVITY).withTags(VALID_TAG_MEDIUM).withDeck(VALID_DECK_SCIENCE).build();
    public static final Card PHOTOSYNTHESIS = new CardBuilder().withQuestion(VALID_NAME_PHOTOSYNTHESIS)
            .withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_HARD, VALID_TAG_MEDIUM)
            .withDeck(VALID_DECK_SCIENCE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCards() {} // prevents instantiation

    /**
     * Returns an {@code Deck} with all the typical cards.
     */
    public static MasterDeck getTypicalMasterDeck() {
        MasterDeck ab = new MasterDeck();
        for (Card card : getTypicalCards()) {
            ab.addCard(card);
        }
        ab.initDecks();
        return ab;
    }

    public static List<Card> getTypicalCards() {
        return new ArrayList<>(Arrays.asList(LOOP, VARIABLE, ATOM, LIFE, PRESIDENT, INDEPENDENCE, EARTHQUAKE));
    }
}
