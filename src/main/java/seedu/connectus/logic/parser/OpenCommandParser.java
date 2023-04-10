package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.OpenCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.socialmedia.Instagram;
import seedu.connectus.model.socialmedia.Telegram;
import seedu.connectus.model.socialmedia.WhatsApp;

/**
 * Parses OpenCommand
 */
public class OpenCommandParser implements Parser<OpenCommand> {
    @Override
    public OpenCommand parse(String userInput) throws ParseException {
        var argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_SOCMED_INSTAGRAM, PREFIX_SOCMED_TELEGRAM,
            PREFIX_SOCMED_WHATSAPP);

        Index personIndex;

        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE), pe);
        }

        var command = new OpenCommand(personIndex,
            argMultimap.getValue(PREFIX_SOCMED_INSTAGRAM).isPresent() ? Instagram::getUserLink : null,
            argMultimap.getValue(PREFIX_SOCMED_TELEGRAM).isPresent() ? Telegram::getUserLink : null,
            argMultimap.getValue(PREFIX_SOCMED_WHATSAPP).isPresent() ? WhatsApp::getUserLink : null);

        if (command.isBlank()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
        }

        return command;
    }
}
