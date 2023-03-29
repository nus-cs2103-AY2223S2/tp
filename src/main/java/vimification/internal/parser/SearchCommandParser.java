package vimification.internal.parser;

import vimification.internal.command.view.SearchCommand;

public class SearchCommandParser implements CommandParser<SearchCommand> {

    private static final ApplicativeParser<SearchCommand> COMMAND_PARSER = null;

    private static final ApplicativeParser<ApplicativeParser<SearchCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("s") // search
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final SearchCommandParser INSTANCE = new SearchCommandParser();

    private SearchCommandParser() {}

    public static SearchCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<SearchCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
