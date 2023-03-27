package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORCE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.ImportMeetingsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.storage.JsonAdaptedMeeting;

/**
 * This class represents a parser for importing of meetings
 */
public class ImportMeetingsParser implements Parser<ImportMeetingsCommand> {
    static final Prefix[] PREFIXES = new Prefix[]{PREFIX_FORCE};
    static final String MALFORMED_JSON = "JSON input malformed";

    @Override
    public ImportMeetingsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        String json = argMultimap.getPreamble();
        boolean isForced = argMultimap.getValue(PREFIX_FORCE).isPresent();
        if (json.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ImportMeetingsCommand.MESSAGE_USAGE));
        }
        List<Meeting> meetings = new ArrayList<>();
        try {
            JsonAdaptedMeeting[] jsonAdaptedMeetings = JsonUtil.fromJsonString(json, JsonAdaptedMeeting[].class);
            for (JsonAdaptedMeeting jsonAdaptedMeeting : jsonAdaptedMeetings) {
                meetings.add(jsonAdaptedMeeting.toModelType());
            }
        } catch (IOException | IllegalValueException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MALFORMED_JSON));
        }
        return new ImportMeetingsCommand(meetings, isForced);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult(null, false);
    }
}
