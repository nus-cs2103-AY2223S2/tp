package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.logic.recommender.Recommender;
import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;

/**
 * Based on a list of people, recommends a list of places to eat and/or study.
 */
public class MeetCommand extends Command {

    public static final String EAT_COMMAND_WORD = "eat";
    public static final String STUDY_COMMAND_WORD = "study";
    public static final String MEET_COMMAND_WORD = "meet";
    public static final String MESSAGE_NO_COMMON_TIME = "There are no common available timings"
        + " amongst selected parties";
    public static final String MESSAGE_SUCCESS = "Here are the recommendations!";
    public static final int DEFAULT_NUMBER_OF_RECOMMENDATIONS = 10;
    public static final String MESSAGE_USAGE =
            String.format("%s/%s/%s", EAT_COMMAND_WORD, STUDY_COMMAND_WORD, MEET_COMMAND_WORD)
                    + ": Recommends locations to eat/study/meet based on the indices of the people.";

    private final Set<ContactIndex> indices;
    private final Collection<Location> locations;
    private final int numberOfRecommendations;

    /**
     * Constructor for a {@code MeetCommand}.
     * @param indices The indices of people we want to meet.
     * @param locations The potential locations to meet.
     * @param numberOfRecommendations The maximum search result size.
     */
    public MeetCommand(
            Set<ContactIndex> indices, Collection<Location> locations, int numberOfRecommendations) {
        this.indices = indices;
        this.locations = locations;
        this.numberOfRecommendations = numberOfRecommendations;
    }

    /**
     * Constructor for a {@code MeetCommand}.
     * @param indices The indices of people we want to meet.
     * @param locations The potential locations to meet.
     */
    public MeetCommand(Set<ContactIndex> indices, Collection<Location> locations) {
        this(indices, locations, DEFAULT_NUMBER_OF_RECOMMENDATIONS);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert indices.contains(new ContactIndex(0));
        if (indices.size() == 1) {
            throw new CommandException("Contact index expected");
        }

        IndexHandler indexHandler = new IndexHandler(model);
        Set<ContactIndex> invalidContactIndices = indices.stream()
                .filter(i -> indexHandler.getPersonByIndex(i).isEmpty())
                .collect(Collectors.toSet());

        if (!invalidContactIndices.isEmpty()) {
            String message = invalidContactIndices.stream()
                    .map(ContactIndex::toString)
                    .collect(Collectors.joining(", "));
            throw new CommandException("Invalid contact indices given: " + message);
        }

        List<Recommendation> recommendations = new Recommender(model).recommend(indices, locations);

        model.setRecommendations(recommendations);
        model.updateObservableRecommendationList();

        model.setParticipants(indices);

        return new CommandResult("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetCommand // instanceof handles nulls
                && indices.equals(((MeetCommand) other).indices)
                && locations.equals(((MeetCommand) other).locations)
                && numberOfRecommendations == ((MeetCommand) other).numberOfRecommendations); // state check
    }
}
