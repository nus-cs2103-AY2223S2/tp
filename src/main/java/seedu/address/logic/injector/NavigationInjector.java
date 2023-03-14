package seedu.address.logic.injector;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;

import java.util.regex.Matcher;

import seedu.address.logic.commands.navigation.NavCommand;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.navigation.NavigationContext;

public class NavigationInjector extends Injector {

    private final String[] WHITELIST = {NavCommand.COMMAND_WORD};

    @Override
    public String inject(String commandText, Model model) {
        final Matcher matcher = AddressBookParser.BASIC_COMMAND_FORMAT.matcher(commandText.trim());

        // If input does not match syntax, return unmodified user input.
        if (!matcher.matches()) {
            return commandText;
        }

        final String commandWord = matcher.group("commandWord");

        // If command is whitelisted, return unmodified user input.
        for (var w : WHITELIST) {
            if (commandWord.equals(w)) {
                return commandText;
            }
        }

        final String arguments = matcher.group("arguments");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_MODULE, PREFIX_LECTURE);

        NavigationContext navContext = model.getCurrentNavContext();

        if (!argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            commandText += navContext.getModulePrefixArg();
        }

        if (!argMultimap.getValue(PREFIX_LECTURE).isPresent()) {
            commandText += navContext.getLecturePrefixArg();
        }

        return commandText;
    }

}
