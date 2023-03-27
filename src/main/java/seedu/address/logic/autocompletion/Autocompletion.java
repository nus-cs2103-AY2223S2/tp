package seedu.address.logic.autocompletion;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT_WITH_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.Model;

public class Autocompletion {
    private static final List<String> POSSIBLE_COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            AddApplicantCommand.COMMAND_WORD,
            DeleteApplicantCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            ViewCommand.COMMAND_WORD
    );

    private static final Map<String, String> COMMAND_WORD_USAGES = new HashMap<>() {{
        put(AddApplicantCommand.COMMAND_WORD, "INDEX " + PREFIX_APPLICANT + "APPLICANT");
        put(AddCommand.COMMAND_WORD, PREFIX_TITLE + "TITLE "
                + PREFIX_DESCRIPTION + "DESCRIPTION "
                + PREFIX_APPLICANT + "APPLICANT");
        put(DeleteApplicantCommand.COMMAND_WORD, "INDEX " + PREFIX_APPLICANT_WITH_ID + "APPLICANT#ID");
        put(DeleteCommand.COMMAND_WORD, "INDEX");
        put(EditCommand.COMMAND_WORD, "INDEX "
                + PREFIX_TITLE + "TITLE "
                + PREFIX_DESCRIPTION + "DESCRIPTION "
                + PREFIX_APPLICANT + "APPLICANT");
        put(FindCommand.COMMAND_WORD, "QUERY");
    }};

    private String query;
    private Model model;
    private List<String> result;

    public Autocompletion(String query, Model model) {
        this.query = query;
        this.model = model;
        this.result = new ArrayList<>();
    }

    public List<String> getListOfSuggestions() {
        addSuggestionsForCommandWord();
        addSuggestionsForCommandWordUsage();

        result.sort((a,b) -> a.length() - b.length());

        return result;
    }

//    public static void main(String[] args) {
//        Model model = new ModelManager();
//        model.addListing(new Listing(new JobTitle("Chicken rice uncle"), new JobDescription("test"),
//                new ArrayList<>(Arrays.asList(new Applicant(new Name("John"))))));
//        model.addListing(new Listing(new JobTitle("Chicken rice auntie"), new JobDescription("test"),
//                new ArrayList<>(Arrays.asList(
//                        new Applicant(new Name("John")),
//                        new Applicant(new Name("John")),
//                        new Applicant(new Name("Jane"))))));
//        model.addListing(new Listing(new JobTitle("Software Developer"), new JobDescription("unique"),
//                new ArrayList<>(Arrays.asList(
//                        new Applicant(new Name("Ali")),
//                        new Applicant(new Name("Xiao Ming")),
//                        new Applicant(new Name("Muthu"))))));
//
//        String query = "  add";
//        System.out.println("Query: " + query);
//        System.out.println("---------------------------");
//        Autocompletion autocompletion = new Autocompletion(query, model);
//        for (String suggestion : autocompletion.getListOfSuggestions()) {
//            System.out.println(suggestion);
//        }
//    }

    private void addSuggestionsForCommandWord() {
        if (query.matches("(?i)^\\s*[a-zA-Z]+\\s*$")) {
            String trimmedToLowerQuery = query.trim().toLowerCase();
            for (String command : POSSIBLE_COMMAND_WORDS) {
                if (command.toLowerCase().equals(trimmedToLowerQuery) &&
                        COMMAND_WORD_USAGES.containsKey(trimmedToLowerQuery)) {
                    result.add(" " + COMMAND_WORD_USAGES.get(trimmedToLowerQuery));
                } else if (command.toLowerCase().startsWith(trimmedToLowerQuery)) {
                    result.add(command.substring(trimmedToLowerQuery.length()));
                }
            }
        }
    }

    private void addSuggestionsForCommandWordUsage() {
        for (String key : COMMAND_WORD_USAGES.keySet()) {
            if (query.matches("(?i)^\\s*" + key + "\\s+$")) {
                result.add(COMMAND_WORD_USAGES.get(key));
            }
        }
    }
}