package seedu.powercards.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.model.util.SampleDataUtil;

public class SampleDataUtilTest {

    @Test
    public void getSampleMasterDeck_validMasterDeck_success() {
        ReadOnlyMasterDeck sampleMasterDeck = SampleDataUtil.getSampleMasterDeck();
        MasterDeck expectedSampleMasterDeck = new MasterDeck();
        assertEquals(6, sampleMasterDeck.getCardList().size());
        assertEquals(4, sampleMasterDeck.getDeckList().size());
        Deck programmingDeck = new Deck("Programming Concepts");
        expectedSampleMasterDeck.addCard(new Card(
                new Question("What is a variable"),
                new Answer("A named storage location in a program where data can be stored and retrieved."),
                new Tag(Tag.TagName.EASY), programmingDeck));
        expectedSampleMasterDeck.addCard(new Card(
                new Question("What is a loop"),
                new Answer("A programming construct that repeats a set of instructions a specified number "
                    + "of times or until a specific condition is met."),
                new Tag(Tag.TagName.EASY), programmingDeck));
        expectedSampleMasterDeck.addCard(new Card(
                new Question("What is an array"),
                new Answer("A collection of data items of the same type that are stored together in "
                    + "contiguous memory locations and accessed by a single name."),
                new Tag(Tag.TagName.MEDIUM), programmingDeck));
        expectedSampleMasterDeck.addCard(new Card(
                new Question("What is a function"),
                new Answer("A self-contained block of code that performs a specific task and can be "
                    + "called from other parts of a program."),
                new Tag(Tag.TagName.MEDIUM), programmingDeck));
        expectedSampleMasterDeck.addCard(new Card(
                new Question("What is recursion"),
                new Answer("A programming technique where a function calls itself in order to solve "
                    + "a problem that can be broken down into smaller instances of the same problem."),
                new Tag(Tag.TagName.HARD), programmingDeck));
        expectedSampleMasterDeck.addCard(new Card(
                new Question("What is a class"),
                new Answer("A blueprint for creating objects that defines the data and methods that "
                    + "the objects will have."),
                new Tag(Tag.TagName.HARD), programmingDeck));
        Deck scienceDeck = new Deck("Science");
        Deck historyDeck = new Deck("History");
        Deck geographyDeck = new Deck("Geography");
        assertEquals(programmingDeck, sampleMasterDeck.getDeckList().get(0));
        assertEquals(scienceDeck, sampleMasterDeck.getDeckList().get(1));
        assertEquals(historyDeck, sampleMasterDeck.getDeckList().get(2));
        assertEquals(geographyDeck, sampleMasterDeck.getDeckList().get(3));
    }

}
