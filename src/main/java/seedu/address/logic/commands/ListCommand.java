package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

/**
 * Lists all elderly, volunteers and pairs in FriendlyLink based on the action given.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();
    public static final String MESSAGE_SUCCESS = "Listed all volunteers, elderly and pairs";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all volunteers, elderly and pairs if nothing is "
            + "specified after the command, else list all paired or unpaired volunteers and elderly if the \"paired\""
            + " or \"unpaired\" (case-insensitive) word is given respectively after the the list command word, "
            + "with all pairs being listed all the time\n"
            + "Parameters: [paired/unpaired]\n"
            + "Example: " + COMMAND_WORD + " paired";
    public static final String MESSAGE_SUCCESS_LIST_PAIRED =
            "Listed %1$s paired volunteer, %2$s paired elderly and %3$s pairs.";
    public static final String MESSAGE_SUCCESS_LIST_UNPAIRED =
            "Listed %1$s unpaired volunteer, %2$s unpaired elderly and %3$s pairs.";

    private final String action;

    /**
     * Constructs a ListCommand to display the lists of elderly, volunteers and pairs.
     *
     * @param trimmedArgs action of the {@code ListCommand}.
     */
    public ListCommand(String trimmedArgs) {
        this.action = trimmedArgs;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FriendlyLink friendlyLink = model.getFriendlyLink();

        @SuppressWarnings("unchecked")
        Predicate<Pair> showAllPairPredicate = (Predicate<Pair>) PREDICATE_SHOW_ALL;

        switch (action) {
        case "":
            model.refreshAllFilteredLists();
            return new CommandResult(MESSAGE_SUCCESS);
        case "unpaired":
            model.updateAllFilteredLists(
                    getUnPairedElderlyPredicate(friendlyLink),
                    getUnPairedVolunteerPredicate(friendlyLink),
                    showAllPairPredicate);
            return new CommandResult(String.format(MESSAGE_SUCCESS_LIST_UNPAIRED,
                    model.getFilteredVolunteerList().size(), model.getFilteredElderlyList().size(),
                    model.getFilteredPairList().size()));
        case "paired":
            model.updateAllFilteredLists(
                    getPairedElderlyPredicate(friendlyLink),
                    getPairedVolunteerPredicate(friendlyLink),
                    showAllPairPredicate);
            return new CommandResult(String.format(MESSAGE_SUCCESS_LIST_PAIRED,
                    model.getFilteredVolunteerList().size(), model.getFilteredElderlyList().size(),
                    model.getFilteredPairList().size()));
        default:
            throw new RuntimeException("unreachable");
        }
    }

    /**
     * Returns the paired elderly predicate based on the filtered pair list.
     *
     * @param friendlyLink Application cache.
     * @return Elderly predicate.
     */
    public Predicate<Elderly> getPairedElderlyPredicate(FriendlyLink friendlyLink) {
        requireNonNull(friendlyLink);
        List<Pair> pairList = friendlyLink.getPairList();
        List<Elderly> pairedElderlyList =
                pairList.stream().map(pair -> pair.getElderly()).collect(Collectors.toList());

        return elderly -> pairedElderlyList.contains(elderly);
    }

    /**
     * Returns the paired volunteer predicate based on the filtered pair list.
     *
     * @param friendlyLink Application cache.
     * @return Volunteer predicate.
     */
    public Predicate<Volunteer> getPairedVolunteerPredicate(FriendlyLink friendlyLink) {
        requireNonNull(friendlyLink);
        List<Pair> pairList = friendlyLink.getPairList();
        List<Volunteer> pairedVolunteerList =
                pairList.stream().map(pair -> pair.getVolunteer()).collect(Collectors.toList());

        return volunteer -> pairedVolunteerList.contains(volunteer);
    }

    /**
     * Returns the unpaired elderly predicate based on the filtered pair list.
     *
     * @param friendlyLink Application cache.
     * @return Elderly predicate.
     */
    public Predicate<Elderly> getUnPairedElderlyPredicate(FriendlyLink friendlyLink) {
        requireNonNull(friendlyLink);
        List<Pair> pairList = friendlyLink.getPairList();
        List<Elderly> pairedElderlyList =
                pairList.stream().map(pair -> pair.getElderly()).collect(Collectors.toList());

        return elderly -> !pairedElderlyList.contains(elderly);
    }

    /**
     * Returns the unpaired volunteer predicate based on the filtered pair list.
     *
     * @param friendlyLink Application cache.
     * @return Volunteer predicate.
     */
    public Predicate<Volunteer> getUnPairedVolunteerPredicate(FriendlyLink friendlyLink) {
        requireNonNull(friendlyLink);
        List<Pair> pairList = friendlyLink.getPairList();
        List<Volunteer> pairedVolunteerList =
                pairList.stream().map(pair -> pair.getVolunteer()).collect(Collectors.toList());

        return volunteer -> !pairedVolunteerList.contains(volunteer);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        // state check
        ListCommand listCommand = (ListCommand) other;
        return action.equals(listCommand.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action);
    }
}
