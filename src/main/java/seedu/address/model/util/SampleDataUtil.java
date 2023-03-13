package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MasterDeck;
import seedu.address.model.ReadOnlyMasterDeck;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Deck} with sample data.
 */
public class SampleDataUtil {
    public static Card[] getSamplePersons() {
        return new Card[] {
            new Card(new Question("Alex Yeoh"),
                    new Answer("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), Optional.of(new Deck("Default Deck"))),
            new Card(new Question("Bernice Yu"),
                new Answer("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), Optional.of(new Deck("Default Deck"))),
            new Card(new Question("Charlotte Oliveiro"),
                new Answer("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), Optional.of(new Deck("Default Deck"))),
            new Card(new Question("David Li"),
                new Answer("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), Optional.of(new Deck("Default Deck"))),
            new Card(new Question("Irfan Ibrahim"),
                new Answer("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), Optional.of(new Deck("Default Deck"))),
            new Card(new Question("Roy Balakrishnan"),
                new Answer("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), Optional.of(new Deck("Default Deck")))
        };
    }

    public static ReadOnlyMasterDeck getSampleAddressBook() {
        MasterDeck sampleAb = new MasterDeck();
        for (Card sampleCard : getSamplePersons()) {
            sampleAb.addCard(sampleCard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
