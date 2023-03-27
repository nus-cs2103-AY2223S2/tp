package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.AvailableDate;

/**
 * Auto-pairs unpaired volunteers and elderly together.
 */
public class AutoPairCommand extends Command {

    public static final String COMMAND_WORD = "auto_pair";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();
    public static final String MESSAGE_SUCCESS = "Paired the following elderly and volunteers together:\n";
    public static final String MESSAGE_SUCCESS_NO_PAIRS = "No pairs were formed.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Automatically pairs unpaired elderly and volunteers together. "
            + "The timings and the region of the the elderly and volunteers in each pair are guaranteed to match.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FriendlyLink friendlyLink = model.getFriendlyLink();
        List<Volunteer> unpairedVolunteers = getUnpairedVolunteers(friendlyLink);
        List<Elderly> unpairedElderly = getUnpairedElderly(friendlyLink);

        List<Pair> pairsToAdd = getGoodPairs(unpairedVolunteers, unpairedElderly);
        if (pairsToAdd.size() == 0) {
            return new CommandResult(MESSAGE_SUCCESS_NO_PAIRS);
        }
        StringBuilder messageDetails = new StringBuilder(MESSAGE_SUCCESS);
        for (Pair pair: pairsToAdd) {
            model.addPair(pair);
            Elderly elderlyInPair = pair.getElderly();
            Volunteer volunteerInPair = pair.getVolunteer();
            messageDetails.append(String.format("Elderly %s (%s) -- Volunteer %s (%s)\n",
                    elderlyInPair.getName().toString(),
                    elderlyInPair.getNric().toString(),
                    volunteerInPair.getName().toString(),
                    volunteerInPair.getNric().toString()));
        }
        return new CommandResult(messageDetails.toString());
    }

    private List<Volunteer> getUnpairedVolunteers(FriendlyLink friendlyLink) {
        List<Pair> pairList = friendlyLink.getPairList();
        List<Volunteer> allVolunteers = friendlyLink.getVolunteerList();
        List<Volunteer> pairedVolunteers = pairList.stream().map(Pair::getVolunteer).collect(Collectors.toList());
        return allVolunteers.stream()
                .filter(v -> !pairedVolunteers.contains(v)).collect(Collectors.toList());
    }

    private List<Elderly> getUnpairedElderly(FriendlyLink friendlyLink) {
        List<Pair> pairList = friendlyLink.getPairList();
        List<Elderly> allElderly = friendlyLink.getElderlyList();
        List<Elderly> pairedElderly = pairList.stream().map(Pair::getElderly).collect(Collectors.toList());
        return allElderly.stream().filter(e -> !pairedElderly.contains(e))
                .collect(Collectors.toList());
    }

    /**
     * Greedily pairs volunteers with elderly that have matching regions and availability. Pairs are
     * one-to-one.
     *
     * @param volunteerList List of volunteers.
     * @param elderlyList List of elderly.
     * @return List of pairs formed greedily.
     */
    private List<Pair> getGoodPairs(List<Volunteer> volunteerList, List<Elderly> elderlyList) {
        HashSet<Elderly> pairedElderly = new HashSet<>();
        ArrayList<Pair> goodPairs = new ArrayList<>();
        for (Volunteer volunteer: volunteerList) {
            for (Elderly elderly: elderlyList) {
                if (pairedElderly.contains(elderly)
                        || !volunteer.getRegion().isMatch(elderly.getRegion())
                        || !AvailableDate.isAvailableDatesIntersect(
                                volunteer.getAvailableDates(), elderly.getAvailableDates())) {
                    continue;
                }
                goodPairs.add(new Pair(elderly, volunteer));
                pairedElderly.add(elderly);
                break;
            }
        }
        return goodPairs;
    }
}
