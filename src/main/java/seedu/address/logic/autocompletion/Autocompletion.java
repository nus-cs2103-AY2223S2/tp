package seedu.address.logic.autocompletion;

import static java.lang.Integer.parseInt;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT_WITH_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.Listing;

public class Autocompletion {
    private static final List<String> POSSIBLE_COMMAND_WORDS = Arrays.asList(
            AddApplicantCommand.COMMAND_WORD,
            AddCommand.COMMAND_WORD,
            DeleteApplicantCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            ViewCommand.COMMAND_WORD
    );

    private static final Map<String, String> COMMAND_WORD_USAGES = new HashMap<>() {{
        put(AddApplicantCommand.COMMAND_WORD,
                AddApplicantCommand.COMMAND_WORD + " INDEX "
                        + PREFIX_APPLICANT + "APPLICANT");
        put(AddCommand.COMMAND_WORD,
                AddCommand.COMMAND_WORD + " "
                        + PREFIX_TITLE + "TITLE "
                        + PREFIX_DESCRIPTION + "DESCRIPTION "
                        + PREFIX_APPLICANT + "APPLICANT");
        put(DeleteApplicantCommand.COMMAND_WORD,
                DeleteApplicantCommand.COMMAND_WORD + " INDEX "
                        + PREFIX_APPLICANT_WITH_ID + "APPLICANT#ID");
        put(DeleteCommand.COMMAND_WORD,
                DeleteCommand.COMMAND_WORD + " INDEX");
        put(EditCommand.COMMAND_WORD,
                EditCommand.COMMAND_WORD + " INDEX "
                        + PREFIX_TITLE + "TITLE "
                        + PREFIX_DESCRIPTION + "DESCRIPTION "
                        + PREFIX_APPLICANT + "APPLICANT");
        put(FindCommand.COMMAND_WORD,
                FindCommand.COMMAND_WORD + "QUERY");
    }};

    public static List<String> getListOfSuggestions(String query, Model model) {
        String trimmedQuery = query.trim();
        if (trimmedQuery.length() == 0) {
            return new ArrayList<>();
        }

        // Suggest usage
        if (POSSIBLE_COMMAND_WORDS.stream()
                .filter(commandWord -> commandWord.equalsIgnoreCase(trimmedQuery)).count() == 1) {
            if (!COMMAND_WORD_USAGES.containsKey(trimmedQuery.toLowerCase())) {
                return new ArrayList<>();
            }

            return Arrays.asList(COMMAND_WORD_USAGES.get(trimmedQuery.toLowerCase()));
        }

        // Special case: handle edits
        if (query.toLowerCase().startsWith(EditCommand.COMMAND_WORD)) {
            try {
                String [] splitQuery = trimmedQuery.split(" ");
                if (splitQuery.length != 2) {
                    return new ArrayList<>();
                }

                String indexString = splitQuery[1];
                int oneBasedIndex = parseInt(indexString);

                // TODO: change to get displayed listing book
                Listing listingToEdit = model.getFilteredListingList().get(oneBasedIndex - 1);

                String suggestion = EditCommand.COMMAND_WORD + " " + oneBasedIndex + " ";
                suggestion += PREFIX_TITLE + listingToEdit.getTitle().fullTitle + " ";
                suggestion += PREFIX_DESCRIPTION + listingToEdit.getDescription().fullDescription + " ";
                for (Applicant applicant: listingToEdit.getApplicants()) {
                    suggestion += PREFIX_APPLICANT + applicant.getName().fullName + " ";
                }

                return Arrays.asList(suggestion.trim());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return new ArrayList<>();
            }
        }

        return POSSIBLE_COMMAND_WORDS.stream()
                .filter(commandWord -> commandWord.startsWith(trimmedQuery.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Model model = new ModelManager();
        model.addListing(new Listing(new JobTitle("Chicken rice uncle"), new JobDescription("test"),
                new ArrayList<>(Arrays.asList(new Applicant(new Name("John"))))));
        model.addListing(new Listing(new JobTitle("Chicken rice auntie"), new JobDescription("test"),
                new ArrayList<>(Arrays.asList(
                        new Applicant(new Name("John")),
                        new Applicant(new Name("John")),
                        new Applicant(new Name("Jane"))))));
        model.addListing(new Listing(new JobTitle("Software Developer"), new JobDescription("unique"),
                new ArrayList<>(Arrays.asList(
                        new Applicant(new Name("Ali")),
                        new Applicant(new Name("Xiao Ming")),
                        new Applicant(new Name("Muthu"))))));

        String query = "DELEtE";
        System.out.println("Query: " + query);
        System.out.println("---------------------------");
        for (String suggestion: getListOfSuggestions(query, model)) {
            System.out.println(suggestion);
        }
    }
}