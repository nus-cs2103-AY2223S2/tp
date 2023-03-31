package vimification.internal.parser;

import vimification.internal.command.ui.SortCommand;
import vimification.internal.command.ui.SortRequest;

public class SortCommandParser implements CommandParser<SortCommand> {

    private static final ApplicativeParser<ApplicativeParser<SortCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("s") // sort
                    .map(SortCommandParser::parseArguments);

    private static final SortCommandParser INSTANCE = new SortCommandParser();

    private SortCommandParser() {}

    private static ApplicativeParser<SortCommand> parseArguments(Object ignore) {
        SortRequest request = new SortRequest();
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.SORT_FLAG, 1));

        ApplicativeParser<Void> flagParser = CommandParserUtil.SORT_FLAG_PARSER
                .consume(flag -> {
                    counter.add(flag);
                    LiteralArgumentFlag literalFlag = flag.getActualFlag();
                    if (literalFlag.equals(CommandParserUtil.DEADLINE_FLAG)) {
                        request.setMode(SortRequest.Mode.DEADLINE);
                        return;
                    }
                    if (literalFlag.equals(CommandParserUtil.PRIORITY_FLAG)) {
                        request.setMode(SortRequest.Mode.PRIORITY);
                        return;
                    }
                    if (literalFlag.equals(CommandParserUtil.STATUS_FLAG)) {
                        request.setMode(SortRequest.Mode.STATUS);
                        return;
                    }
                    throw new ParserException("Should not reach here!");
                });


        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(flagParser)
                .constMap(new SortCommand(request))
                .dropNext(ApplicativeParser.skipWhitespaces())
                .dropNext(ApplicativeParser.eof());
    }

    public static SortCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<SortCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
