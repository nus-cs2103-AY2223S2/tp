package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.location.DistanceUtil;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

public class MeetCommand extends Command {

    public static final String EAT_COMMAND_WORD = "eat";
    public static final String STUDY_COMMAND_WORD = "study";
    public static final String MEET_COMMAND_WORD = "meet";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final int NUMBER_OF_RECOMMENDATIONS = 10;

    private final Set<Index> indices;
    private final HashMap<String, Location> locationHashMap;

    public MeetCommand(Set<Index> indices, HashMap<String, Location> locationHashMap) {
        this.indices = indices;
        this.locationHashMap = locationHashMap;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Location> locationsOfPersons = new ArrayList<>();

        for (Index index : indices) {
            locationsOfPersons.add(getPersonFromIndex(index, lastShownList).getAddress().getValue());
        }

        List<? extends Location> recommendations = giveRecommendations(locationsOfPersons);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    private Person getPersonFromIndex(Index index, List<? extends Person> lastShownList) throws CommandException {
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownList.get(index.getZeroBased());
    }

    private List<? extends Location> giveRecommendations(List<? extends Location> locationsOfPersons) {
        Location midpoint = DistanceUtil.getMidpoint(locationsOfPersons);
        return DistanceUtil.getClosestPoints(midpoint, NUMBER_OF_RECOMMENDATIONS, locationsOfPersons);
    }
}
