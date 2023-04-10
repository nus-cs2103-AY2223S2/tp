package seedu.quickcontacts.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_FORCE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.quickcontacts.commons.exceptions.IllegalValueException;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.ImportMeetingsCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.storage.JsonAdaptedMeeting;

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
        return new AutocompleteResult();
    }
}
