package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Deck} with sample cards.
 */
public class SampleCardUtil {

    // TODO: add tag to each card
    /*
    public static PowerCard[] getSampleCards() {
        return new PowerCard[] {
            new PowerCard(new Question("Who let the dogs out?"), new Answer("Woof Woof")),
            new PowerCard(new Question("Who let the cats out?"), new Answer("Meow Meow")),
            new PowerCard(new Question("What happened in Beijing, China in 1989?"), new Answer("_Retracted_"))
        };
    }

    public static ReadOnlyPowerDeck getSampleDeck() {
        PowerDeck sampleDeck = new PowerDeck();
        for (PowerCard card : getSampleCards()) {
            sampleDeck.addCard(card);
        }
        return sampleDeck;
    }
     */

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
