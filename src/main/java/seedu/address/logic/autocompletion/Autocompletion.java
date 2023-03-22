package seedu.address.logic.autocompletion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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

    public static List<String> getListOfSuggestions(String query) {
        List<String> suggestions = new ArrayList<>();
        if (query.trim().length() == 0) {
            return suggestions;
        }
        if (POSSIBLE_COMMAND_WORDS.stream().filter(commandWord -> commandWord.equalsIgnoreCase(query)).count() == 1) {
            return suggestions;
        }

        return POSSIBLE_COMMAND_WORDS.stream()
                .filter(commandWord -> commandWord.startsWith(query.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String query = "DELE";
        System.out.println("Query: " + query);
        System.out.println("---------------------------");
        for (String suggestion: getListOfSuggestions(query)) {
            System.out.println(suggestion);
        }
    }
}