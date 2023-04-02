package seedu.address.logic.injector;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOT;

import java.util.regex.Matcher;

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

    private static final String[] WHITELIST = {NavCommand.COMMAND_WORD};

    @Override
    public String inject(String commandText, Model model) {
        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(commandText.trim());

        // If input does not match syntax, return unmodified user input.
        if (!matcher.matches()) {
            return commandText;
        }

        final String commandWord = matcher.group("commandWord");

        // If command is whitelisted, return unmodified user input.
        if (isCommandWhitelisted(commandWord)) {
            return commandText;
        }

        final String arguments = matcher.group("arguments");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_ROOT, PREFIX_MODULE, PREFIX_LECTURE);
        NavigationContext navContext = model.getCurrentNavContext();
        return injectMissingArgs(commandText, argMultimap, navContext).trim();
    }

    private String injectMissingArgs(String commandText, ArgumentMultimap argMultimap, NavigationContext navContext) {
        boolean rootSpecified = argMultimap.getValue(PREFIX_ROOT).isPresent();
        boolean lectureArgSpecified = argMultimap.getValue(PREFIX_LECTURE).isPresent();
        boolean moduleArgSpecified = argMultimap.getValue(PREFIX_MODULE).isPresent();

        commandText = removeRootPrefix(commandText);
        if (rootSpecified && !lectureArgSpecified && !moduleArgSpecified) {
            return commandText;
        }

        if (!moduleArgSpecified) {
            commandText = injectModulePrefixArg(commandText, navContext);
            if (!lectureArgSpecified) {
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

    private boolean isCommandWhitelisted(String commandWord) {
        for (var w : WHITELIST) {
            if (commandWord.equals(w)) {
                return true;
            }
        }
        return false;
    }
}
