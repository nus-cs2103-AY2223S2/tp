package seedu.address.logic.injector;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOT;

import java.util.regex.Matcher;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.navigation.NavCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.TrackerParser;
import seedu.address.model.Model;
import seedu.address.model.navigation.NavigationContext;

/**
 * Injects context-sensitive prefixes into the user command
 */
public class NavigationInjector extends Injector {

    public static final String[] WHITELIST =
            {NavCommand.COMMAND_WORD, ImportCommand.COMMAND_WORD, ExportCommand.COMMAND_WORD};

    @Override
    public String inject(String commandText, Model model) {
        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(commandText.trim());

        if (shouldIgnoreCommand(matcher)) {
            return commandText;
        }

        final String arguments = matcher.group("arguments");

        commandText = removeRootPrefix(commandText);
        return injectMissingArgs(commandText, arguments, model).trim();
    }

    private boolean shouldIgnoreCommand(Matcher matcher) {
        if (!matcher.matches()) {
            return true;
        }

        final String commandWord = matcher.group("commandWord");
        if (isCommandWhitelisted(commandWord) || !isValidCommandWord(commandWord)) {
            return true;
        }

        return false;
    }

    private String injectMissingArgs(String commandText, String arguments, Model model) {
        NavigationContext navContext = model.getCurrentNavContext();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_ROOT, PREFIX_MODULE, PREFIX_LECTURE);

        boolean hasRootPrefix = argMultimap.getValue(PREFIX_ROOT).isPresent();
        boolean hasLecturePrefix = argMultimap.getValue(PREFIX_LECTURE).isPresent();
        boolean hasModulePrefix = argMultimap.getValue(PREFIX_MODULE).isPresent();

        // Don't inject context prefixes if only root prefix present.
        if (hasRootPrefix && !hasLecturePrefix) {
            return commandText;
        }

        // Inject context prefixes.
        if (!hasModulePrefix) {
            commandText = injectModulePrefixArg(commandText, navContext);
            if (!hasLecturePrefix) {
                commandText = injectLecturePrefixArg(commandText, navContext);
            }
        }

        return commandText;
    }

    private String removeRootPrefix(String commandText) {
        return commandText.replaceAll("\\" + PREFIX_ROOT.getPrefix() + "[^\\/]*", "");
    }

    private String injectModulePrefixArg(String commandText, NavigationContext navContext) {
        return commandText + " " + navContext.getModulePrefixArg();
    }

    private String injectLecturePrefixArg(String commandText, NavigationContext navContext) {
        return commandText + " " + navContext.getLecturePrefixArg();
    }

    private boolean isValidCommandWord(String commandWord) {
        return !commandWord.matches(PREFIX_ROOT.toString());
    }

    private boolean isCommandWhitelisted(String commandWord) {
        for (var w : WHITELIST) {
            if (commandWord.equals(w)) {
                return true;
            }
        }
        return false;
    }
}
