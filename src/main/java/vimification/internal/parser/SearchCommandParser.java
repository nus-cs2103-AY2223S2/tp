package vimification.internal.parser;

import java.util.ArrayList;
import java.util.List;

import vimification.internal.command.view.AndComposedSearchCommand;
import vimification.internal.command.view.OrComposedSearchCommand;
import vimification.internal.command.view.SearchByDeadlineAfterCommand;
import vimification.internal.command.view.SearchByDeadlineBeforeCommand;
import vimification.internal.command.view.SearchByKeywordCommand;
import vimification.internal.command.view.SearchByLabelCommand;
import vimification.internal.command.view.SearchByPriorityCommand;
import vimification.internal.command.view.SearchByStatusCommand;
import vimification.internal.command.view.SearchCommand;

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
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.KEYWORD_FLAG, 1),
                Pair.of(CommandParserUtil.STATUS_FLAG, 1),
                Pair.of(CommandParserUtil.LABEL_FLAG, Integer.MAX_VALUE),
                Pair.of(CommandParserUtil.PRIORITY_FLAG, 1),
                Pair.of(CommandParserUtil.BEFORE_FLAG, 1),
                Pair.of(CommandParserUtil.AFTER_FLAG, 1),
                Pair.of(CommandParserUtil.OR_FLAG, 1)); // TODO: and and flag

        List<SearchCommand> commands = new ArrayList<>();
        ApplicativeParser<Void> flagParser = ApplicativeParser.choice(
                CommandParserUtil.KEYWORD_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.WORD_PARSER)
                        .map(SearchByKeywordCommand::new)
                        .consume(commands::add),
                CommandParserUtil.STATUS_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.STATUS_PARSER)
                        .map(SearchByStatusCommand::new)
                        .consume(commands::add),
                CommandParserUtil.LABEL_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.LABEL_PARSER)
                        .map(SearchByLabelCommand::new)
                        .consume(commands::add),
                CommandParserUtil.PRIORITY_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.PRIORITY_PARSER)
                        .map(SearchByPriorityCommand::new)
                        .consume(commands::add),
                CommandParserUtil.BEFORE_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DATE_TIME_PARSER)
                        .map(SearchByDeadlineBeforeCommand::new)
                        .consume(commands::add),
                CommandParserUtil.AFTER_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DATE_TIME_PARSER)
                        .map(SearchByDeadlineAfterCommand::new)
                        .consume(commands::add),
                CommandParserUtil.OR_FLAG_PARSER
                        .consume(counter::add));

        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(flagParser.sepBy1(ApplicativeParser.skipWhitespaces1()))
                .map(ignore1 -> {
                    if (counter.get(CommandParserUtil.OR_FLAG) == 0) {
                        return new OrComposedSearchCommand(commands);
                    } else {
                        return new AndComposedSearchCommand(commands);
                    }
                });
    }

    public static SearchCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<SearchCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
