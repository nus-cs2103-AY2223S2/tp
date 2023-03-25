package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.cardcommands.AddCommand;
import seedu.address.logic.commands.cardcommands.DeleteCommand;
import seedu.address.logic.commands.cardcommands.EditCommand;
import seedu.address.logic.commands.deckcommands.AddDeckCommand;
import seedu.address.logic.commands.deckcommands.DeleteDeckCommand;
import seedu.address.logic.commands.deckcommands.EditDeckCommand;
import seedu.address.logic.commands.deckcommands.SelectDeckCommand;
import seedu.address.logic.commands.deckcommands.UnselectDeckCommand;
import seedu.address.logic.commands.reviewcommands.EndReviewCommand;
import seedu.address.logic.commands.reviewcommands.FlipCardCommand;
import seedu.address.logic.commands.reviewcommands.MarkCorrectCommand;
import seedu.address.logic.commands.reviewcommands.MarkWrongCommand;
import seedu.address.logic.commands.reviewcommands.NextCardCommand;
import seedu.address.logic.commands.reviewcommands.PreviousCardCommand;
import seedu.address.logic.commands.reviewcommands.ReviewCommand;
import seedu.address.logic.commands.reviewcommands.SetNumCardsPerReviewCommand;
import seedu.address.logic.commands.reviewcommands.TagCardDuringReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MasterDeckParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private String commandWord;
    private String arguments;


    /**
     * Parses the command word and the arguments from user input String.
     *
     * @param userInput The String input from user
     * @throws ParseException If the user input does not conform to the expected format
     */
    private void updateCommandWordAndArguments(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        this.commandWord = matcher.group("commandWord");
        this.arguments = matcher.group("arguments");
    }

    /**
     * Parses user input into command for execution when no deck is selected.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommandInMainMode(String userInput) throws ParseException {
        updateCommandWordAndArguments(userInput);

        switch (commandWord) {

        case AddDeckCommand.COMMAND_WORD:
            return new AddDeckCommandParser().parse(arguments);

        case DeleteDeckCommand.COMMAND_WORD:
            return new DeleteDeckCommandParser().parse(arguments);

        case EditDeckCommand.COMMAND_WORD:
            return new EditDeckCommandParser().parse(arguments);

        case SelectDeckCommand.COMMAND_WORD:
            return new SelectDeckCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ReviewCommand.COMMAND_WORD:
            return new ReviewCommandParser().parse(arguments);

        case SetNumCardsPerReviewCommand.COMMAND_WORD:
            return new SetNumCardsPerReviewCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution when a deck is selected.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommandInDeckMode(String userInput) throws ParseException {
        updateCommandWordAndArguments(userInput);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case UnselectDeckCommand.COMMAND_WORD:
            return new UnselectDeckCommand();

        case ReviewCommand.COMMAND_WORD:
            return new ReviewCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case SelectDeckCommand.COMMAND_WORD:
            return new SelectDeckCommandParser().parse(arguments);

        case SetNumCardsPerReviewCommand.COMMAND_WORD:
            return new SetNumCardsPerReviewCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution when a review is underway.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommandInReviewMode(String userInput) throws ParseException {
        updateCommandWordAndArguments(userInput);

        switch (commandWord) {

        case EndReviewCommand.COMMAND_WORD:
            return new EndReviewCommand();

        case PreviousCardCommand.COMMAND_WORD:
            return new PreviousCardCommand();

        case NextCardCommand.COMMAND_WORD:
            return new NextCardCommand();

        case FlipCardCommand.COMMAND_WORD:
            return new FlipCardCommand();

        case MarkCorrectCommand.COMMAND_WORD:
            return new MarkCorrectCommand();

        case MarkWrongCommand.COMMAND_WORD:
            return new MarkWrongCommand();

        case TagCardDuringReviewCommand.COMMAND_WORD:
            return new TagCardDuringReviewCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
