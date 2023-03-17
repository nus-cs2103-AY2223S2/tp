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

    public static final Card LOOP = new CardBuilder().withName("What is a loop")
            .withAddress("A construct that repeats instructions until a condition is met")
            .withTags("Hard").withDeck("Programming Concepts").build();
    public static final Card VARIABLE = new CardBuilder().withName("What is a variable")
            .withAddress("A named memory location that stores a value")
            .withTags("Hard").withDeck("Programming Concepts").build();
    public static final Card ATOM = new CardBuilder().withName("What is the structure of an atom")
            .withAddress("Atoms consist of a nucleus containing protons and neutrons"
                    + ", surrounded by electrons in shells or energy levels").withDeck("Science").build();
    public static final Card LIFE = new CardBuilder().withName("What is the basic unit of life")
            .withAddress("The cell is the basic unit of life").withTags("Medium")
            .withDeck("Science").build();
    public static final Card PRESIDENT = new CardBuilder()
            .withName("Who was the first president of the United States")
            .withAddress("George Washington").withDeck("History").build();
    public static final Card INDEPENDENCE = new CardBuilder().withName("When did Singapore gain independence")
            .withAddress("9 August 1965").withDeck("History").build();
    public static final Card EARTHQUAKE = new CardBuilder().withName("What is an earthquake")
            .withAddress("An earthquake is a sudden and rapid shaking "
                    + "of the earth caused by the shifting of tectonic plates")
            .withDeck("Geography").build();

    // Manually added
    public static final Card SMOG = new CardBuilder().withName("What is smog")
            .withAddress("A mixture of smoke and fog, caused by pollutants in the atmosphere")
            .withDeck("Geography").build();
    public static final Card FRACTION = new CardBuilder().withName("What is a fraction")
            .withAddress("A part of a whole expressed as a ratio of two numbers")
            .withDeck("Math").build();

    // Manually added - Card's details found in {@code CommandTestUtil}
    public static final Card GRAVITY = new CardBuilder().withName(VALID_QUESTION_GRAVITY)
            .withAddress(VALID_ANSWER_GRAVITY).withTags(VALID_TAG_MEDIUM).withDeck(VALID_DECK_SCIENCE).build();
    public static final Card PHOTOSYNTHESIS = new CardBuilder().withName(VALID_NAME_PHOTOSYNTHESIS)
            .withAddress(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_HARD, VALID_TAG_MEDIUM)
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
