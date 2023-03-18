package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;

/**
 * Finds and lists all volunteers and elderly in FriendlyLink whose attributes contains any of
 * the specified attributes and their relevant pairs.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();

    static {
        COMMAND_PROMPTS.put(PREFIX_NAME, "<name>");
        COMMAND_PROMPTS.put(PREFIX_NRIC, "<nric>");
        COMMAND_PROMPTS.put(PREFIX_ADDRESS, "<address>");
        COMMAND_PROMPTS.put(PREFIX_PHONE, "<phone>");
        COMMAND_PROMPTS.put(PREFIX_EMAIL, "<email>");
        COMMAND_PROMPTS.put(PREFIX_TAG, "<tag>");
        COMMAND_PROMPTS.put(PREFIX_REGION, "<region>");
        COMMAND_PROMPTS.put(PREFIX_AGE, "<age>");
        COMMAND_PROMPTS.put(PREFIX_RISK, "<risk>");
        COMMAND_PROMPTS.put(PREFIX_AVAILABILITY, "<start_date,end_date>");
    }

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all volunteers and elderly whose attributes"
            + "matches the given attributes (case-insensitive) and displays them in their respective lists. "
            + "Related pairs will be displayed in the pair list. "
            + "All attributes are optional but at least 1 needs to be specified.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_NRIC + "NRIC] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_RISK + "RISK LEVEL] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_AVAILABILITY + "START_DATE,END_DATE]...\n"
            + "Example: " + COMMAND_WORD + " n/John";

    private final List<Predicate<Person>> sharedFilterList;
    private final List<Predicate<Elderly>> elderlyOnlyFilterList;
    private final List<Predicate<Volunteer>> volunteerOnlyFilterList;

    /**
     * Finds the relevant entities in the lists.
     *
     * @param sharedFilterList List of predicates relevant to both elderly and volunteers.
     * @param elderlyOnlyFilterList List of predicates relevant to only elderly.
     * @param volunteerOnlyFilterList List of predicates relevant to only volunteers.
     */
    public FindCommand(List<Predicate<Person>> sharedFilterList,
            List<Predicate<Elderly>> elderlyOnlyFilterList, List<Predicate<Volunteer>> volunteerOnlyFilterList) {
        this.sharedFilterList = sharedFilterList;
        this.elderlyOnlyFilterList = elderlyOnlyFilterList;
        this.volunteerOnlyFilterList = volunteerOnlyFilterList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Predicate<Person> combinedSharedPredicate = sharedFilterList.stream()
                .reduce(x -> true, Predicate::and);

        Predicate<Elderly> combinedElderlyPredicate = elderlyOnlyFilterList.stream()
                .reduce(x -> true, Predicate::and)
                .and(combinedSharedPredicate);

        Predicate<Volunteer> combinedVolunteerPredicate = volunteerOnlyFilterList.stream()
                .reduce(x -> true, Predicate::and)
                .and(combinedSharedPredicate);

        model.updateFilteredElderlyList(combinedElderlyPredicate);
        model.updateFilteredVolunteerList(combinedVolunteerPredicate);
        model.updateFilteredPairList(getPairPredicate(model));

        return new CommandResult(
                String.format(Messages.MESSAGE_LISTED_OVERVIEW, model.getFilteredVolunteerList().size(),
                        model.getFilteredElderlyList().size(), model.getFilteredPairList().size()));
    }

    /**
     * Returns the pair predicate based on the filtered volunteers and elderly.
     *
     * @param model FriendlyLink model.
     */
    private Predicate<Pair> getPairPredicate(Model model) {
        List<Elderly> listOfFilteredElderly = model.getFilteredElderlyList();
        List<Volunteer> listOfFilteredVolunteers = model.getFilteredVolunteerList();

        Predicate<Pair> predicate = pair -> (listOfFilteredElderly.contains(pair.getElderly())
                || listOfFilteredVolunteers.contains(pair.getVolunteer()));

        return predicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && sharedFilterList.equals(((FindCommand) other).sharedFilterList)
                && elderlyOnlyFilterList.equals(((FindCommand) other).elderlyOnlyFilterList)
                && volunteerOnlyFilterList.equals(((FindCommand) other).volunteerOnlyFilterList)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedFilterList,
                elderlyOnlyFilterList, volunteerOnlyFilterList);
    }
}
