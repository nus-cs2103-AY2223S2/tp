package vimification.internal.parser;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

import vimification.commons.core.Index;
import vimification.model.task.Priority;
import vimification.model.task.Status;

public class CommandParserUtil {

    public static final ApplicativeParser<String> WORD_PARSER = ApplicativeParser.nonWhitespaces1();

    public static final ApplicativeParser<String> STRING_PARSER = ApplicativeParser
            .choice(ApplicativeParser.string("\""), ApplicativeParser.string("'"))
            .flatMap(quote -> ApplicativeParser
                    .until(quote)
                    .dropNext(ApplicativeParser.string(quote)))
            .or(WORD_PARSER);

    public static final ApplicativeParser<Integer> INT_PARSER =
            ApplicativeParser.nonWhitespaces1().flatMap(str -> {
                try {
                    return ApplicativeParser.of(Integer.parseInt(str));
                } catch (NumberFormatException ex) {
                    return ApplicativeParser.fail();
                }
            });

    public static final ApplicativeParser<LocalDateTime> DATE_TIME_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .map(ignore -> LocalDateTime.now());

    public static final LiteralArgumentFlag TITLE_FLAG = new LiteralArgumentFlag("-t", "--title");
    public static final LiteralArgumentFlag LABEL_FLAG = new LiteralArgumentFlag("-l", "--label");
    public static final LiteralArgumentFlag STATUS_FLAG = new LiteralArgumentFlag("-s", "--status");
    public static final LiteralArgumentFlag PRIORITY_FLAG =
            new LiteralArgumentFlag("-p", "--priority");
    public static final LiteralArgumentFlag DEADLINE_FLAG =
            new LiteralArgumentFlag("-d", "--deadline");
    public static final LiteralArgumentFlag KEYWORD_FLAG =
            new LiteralArgumentFlag("-w", "--keyword");

    public static final LiteralArgumentFlag BEFORE_FLAG = new LiteralArgumentFlag(null, "--before");
    public static final LiteralArgumentFlag AFTER_FLAG = new LiteralArgumentFlag(null, "--after");

    public static final LiteralArgumentFlag ADD_MACRO_FLAG =
            new LiteralArgumentFlag("-a", "--add");
    public static final LiteralArgumentFlag DELETE_MACRO_FLAG =
            new LiteralArgumentFlag("-d", "--delete");
    public static final ComposedArgumentFlag MACRO_FLAG =
            new ComposedArgumentFlag(ADD_MACRO_FLAG, DELETE_MACRO_FLAG);

    public static final LiteralArgumentFlag OR_FLAG = new LiteralArgumentFlag("-o", "--or");
    public static final LiteralArgumentFlag AND_FLAG = new LiteralArgumentFlag("-a", "--and");

    public static final ApplicativeParser<LiteralArgumentFlag> TITLE_FLAG_PARSER =
            parseFlag(TITLE_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> LABEL_FLAG_PARSER =
            parseFlag(LABEL_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> STATUS_FLAG_PARSER =
            parseFlag(STATUS_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> PRIORITY_FLAG_PARSER =
            parseFlag(PRIORITY_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> DEADLINE_FLAG_PARSER =
            parseFlag(DEADLINE_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> KEYWORD_FLAG_PARSER =
            parseFlag(KEYWORD_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> BEFORE_FLAG_PARSER =
            parseFlag(BEFORE_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> AFTER_FLAG_PARSER =
            parseFlag(AFTER_FLAG);

    public static final ApplicativeParser<ComposedArgumentFlag> MACRO_FLAG_PARSER =
            parseFlag(MACRO_FLAG);

    public static final ApplicativeParser<LiteralArgumentFlag> OR_FLAG_PARSER =
            parseFlag(OR_FLAG);
    public static final ApplicativeParser<LiteralArgumentFlag> AND_FLAG_PARSER =
            parseFlag(AND_FLAG);


    public static final ApplicativeParser<String> TITLE_PARSER = STRING_PARSER;
    public static final ApplicativeParser<String> LABEL_PARSER = STRING_PARSER;
    public static final ApplicativeParser<Status> STATUS_PARSER =
            INT_PARSER.map(Status::fromInt);
    public static final ApplicativeParser<Priority> PRIORITY_PARSER =
            INT_PARSER.map(Priority::fromInt);
    public static final ApplicativeParser<LocalDateTime> DEADLINE_PARSER = DATE_TIME_PARSER;

    public static final ApplicativeParser<Index> ONE_BASED_INDEX_PARSER =
            INT_PARSER.filter(i -> i >= 1).map(Index::fromOneBased);
    public static final ApplicativeParser<Index> ZERO_BASED_INDEX_PARSER =
            INT_PARSER.filter(i -> i >= 0).map(Index::fromZeroBased);

    public static ApplicativeParser<LiteralArgumentFlag> parseFlag(LiteralArgumentFlag flag) {
        return Stream.of(flag.getShortForm(), flag.getLongForm())
                .filter(Objects::nonNull)
                .map(ApplicativeParser::string)
                .reduce(ApplicativeParser::or)
                .orElseGet(ApplicativeParser::fail)
                .constMap(flag);
    }

    public static ApplicativeParser<ComposedArgumentFlag> parseFlag(ComposedArgumentFlag flag) {
        return flag.getFlags().stream()
                .map(CommandParserUtil::parseFlag)
                .reduce(ApplicativeParser::or)
                .orElseGet(ApplicativeParser::fail)
                .map(literalFlag -> {
                    ComposedArgumentFlag clonedFlag = flag.clone();
                    clonedFlag.setActualFlag(literalFlag);
                    return clonedFlag;
                });
    }

    private CommandParserUtil() {}
}
