package vimification.internal.parser;

import vimification.internal.command.ui.SearchCommand;
import vimification.internal.command.ui.SearchRequest;

public class SearchCommandParser implements CommandParser<SearchCommand> {

    private static final ApplicativeParser<ApplicativeParser<SearchCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("s") // search
                    .map(SearchCommandParser::parseArguments)
                    .dropNext(ApplicativeParser.skipWhitespaces())
                    .dropNext(ApplicativeParser.eof());

    private static final SearchCommandParser INSTANCE = new SearchCommandParser();

    private SearchCommandParser() {}

    private static ApplicativeParser<SearchCommand> parseArguments(Object ignore) {
        SearchRequest request = new SearchRequest();
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.KEYWORD_FLAG, 1),
                Pair.of(CommandParserUtil.STATUS_FLAG, 1),
                Pair.of(CommandParserUtil.LABEL_FLAG, Integer.MAX_VALUE),
                Pair.of(CommandParserUtil.PRIORITY_FLAG, 1),
                Pair.of(CommandParserUtil.BEFORE_FLAG, 1),
                Pair.of(CommandParserUtil.AFTER_FLAG, 1),
                Pair.of(CommandParserUtil.OR_FLAG, 1)); // TODO: and and flag

        ApplicativeParser<Void> flagParser = ApplicativeParser.choice(
                CommandParserUtil.KEYWORD_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.WORD_PARSER)
                        .consume(request::setSearchedKeyword),
                CommandParserUtil.STATUS_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.STATUS_PARSER)
                        .consume(request::setSearchedStatus),
                CommandParserUtil.LABEL_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.LABEL_PARSER)
                        .consume(label -> request.getSearchedLabels().add(label)),
                CommandParserUtil.PRIORITY_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.PRIORITY_PARSER)
                        .consume(request::setSearchedPriority),
                CommandParserUtil.BEFORE_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DATE_TIME_PARSER)
                        .consume(request::setSearchedDeadlineBefore),
                CommandParserUtil.AFTER_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DATE_TIME_PARSER)
                        .consume(request::setSearchedDeadlineAfter));

        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(flagParser.sepBy1(ApplicativeParser.skipWhitespaces1()))
                .constMap(new SearchCommand(request));
    }

    public static SearchCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<SearchCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
