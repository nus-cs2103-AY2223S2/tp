package seedu.powercards.model.util;

import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Deck} with sample data.
 */
public class SampleDataUtil {
    private static final Deck PROGRAMMING = new Deck("Programming Concepts");
    private static final Deck SCIENCE = new Deck("Science");
    private static final Deck HISTORY = new Deck("History");
    private static final Deck GEOGRAPHY = new Deck("Geography");

    private static Card[] getSampleCards() {
        return new Card[]{
            new Card(new Question("What is a variable"),
                    new Answer("A named storage location in a program where data can be stored and retrieved."),
                    new Tag(Tag.TagName.EASY), PROGRAMMING),
            new Card(new Question("What is a loop"),
                    new Answer("A programming construct that repeats a set of instructions a specified number "
                            + "of times or until a specific condition is met."),
                    new Tag(Tag.TagName.EASY), PROGRAMMING),
            new Card(new Question("What is an array"),
                    new Answer("A collection of data items of the same type that are stored together in "
                            + "contiguous memory locations and accessed by a single name."),
                    new Tag(Tag.TagName.MEDIUM), PROGRAMMING),
            new Card(new Question("What is a function"),
                    new Answer("A self-contained block of code that performs a specific task and can be "
                            + "called from other parts of a program."),
                    new Tag(Tag.TagName.MEDIUM), PROGRAMMING),
            new Card(new Question("What is recursion"),
                    new Answer("A programming technique where a function calls itself in order to solve "
                             + "a problem that can be broken down into smaller instances of the same problem."),
                    new Tag(Tag.TagName.HARD), PROGRAMMING),
            new Card(new Question("What is a class"),
                    new Answer("A blueprint for creating objects that defines the data and methods that "
                            + "the objects will have."),
                    new Tag(Tag.TagName.HARD), PROGRAMMING),
        };
    }

    private static Deck[] getSampleDecks() {
        return new Deck[]{PROGRAMMING, SCIENCE, HISTORY, GEOGRAPHY};
    }


    public static ReadOnlyMasterDeck getSampleMasterDeck() {
        MasterDeck sampleMasterDeck = new MasterDeck();
        for (Card sampleCard : getSampleCards()) {
            sampleMasterDeck.addCard(sampleCard);
        }

        for (Deck sampleDeck : getSampleDecks()) {
            sampleMasterDeck.addDeck(sampleDeck);
        }

        return sampleMasterDeck;
    }
}
