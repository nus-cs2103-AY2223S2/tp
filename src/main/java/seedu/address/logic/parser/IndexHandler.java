package seedu.address.logic.parser;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.recommendation.Recommendation;

/**
 * Assigns a valid contact index for a newly-added contact to EduMate.
 */
public class IndexHandler {
    private Model model;

    public IndexHandler(Model model) {
        this.model = model;
    }

    /**
     * Assigns a contact index to a new contact.
     * @return new Contact Index for new contact.
     */
    public ContactIndex assignIndex() {
        List<Person> personList = model.getObservablePersonList();
        if (personList.isEmpty() || personList.stream()
                .noneMatch(person -> person.getContactIndex().equals(new ContactIndex(1)))) {
            return new ContactIndex(1);
        }
        OptionalInt takenIndices = IntStream.iterate(1, x -> x + 1)
                .takeWhile(integer -> personList.stream()
                        .anyMatch(person -> person.getContactIndex().equals(new ContactIndex(integer)))).max();
        Integer availableIndex = takenIndices.getAsInt() + 1;
        return new ContactIndex(availableIndex);
    }

    /**
     * Gets the first person who satisfies the index, if any.
     */
    public Optional<Person> getPersonByIndex(ContactIndex index) {
        if (index.getContactIndex() == 0) {
            return Optional.of(model.getUser());
        }

        List<Person> personList = model.getObservablePersonList();
        return personList.stream().filter(person -> person.getContactIndex().equals(index)).findFirst();
    }

    /**
     * Assigns a contact index for a Recommendation.
     * @return new Contact Index for new Recommendation.
     */
    public ContactIndex assignRecommendationIndex() {
        List<Recommendation> recommendationList = model.getObservableRecommendationList();
        if (recommendationList.isEmpty() || recommendationList.stream()
                .noneMatch(recommendation -> recommendation.getContactIndex().equals(new ContactIndex(1)))) {
            return new ContactIndex(1);
        }
        OptionalInt takenIndices = IntStream.iterate(1, integer -> recommendationList.stream()
                        .anyMatch(recommendation -> recommendation
                                .getContactIndex().equals(new ContactIndex(integer))), x -> x + 1).max();

        assert takenIndices.isPresent();

        int availableIndex = takenIndices.getAsInt() + 1;
        return new ContactIndex(availableIndex);
    }

    /**
     * Returns the recommendation at the given index.
     */
    public Optional<Recommendation> getRecommendationByIndex(ContactIndex index) {
        List<Recommendation> recommendationList = model.getObservableRecommendationList();
        return recommendationList.stream()
                .filter(recommendation -> recommendation.getContactIndex().equals(index))
                .findFirst();
    }

}
