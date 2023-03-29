package vimification.internal.parser;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

import vimification.commons.core.Index;
import vimification.model.task.Priority;
import vimification.model.task.Status;

public class CommandParserUtil {

    public static final ApplicativeParser<String> STRING_PARSER = ApplicativeParser
            .choice(ApplicativeParser.string("\""), ApplicativeParser.string("'"))
            .flatMap(quote -> ApplicativeParser
                    .until(quote)
                    .dropNext(ApplicativeParser.string(quote)))
            .or(ApplicativeParser.nonWhitespaces1());

    public static final ApplicativeParser<Integer> INT_PARSER =
            ApplicativeParser.nonWhitespaces1().flatMap(str -> {
                try {
                    return ApplicativeParser.of(Integer.parseInt(str));
                } catch (NumberFormatException ex) {
                    return ApplicativeParser.fail();
                }
            });

    public static final ArgumentFlag TITLE_FLAG = new ArgumentFlag("-t", "--title");
    public static final ArgumentFlag LABEL_FLAG = new ArgumentFlag("-l", "--label");
    public static final ArgumentFlag STATUS_FLAG = new ArgumentFlag("-s", "--status");
    public static final ArgumentFlag PRIORITY_FLAG = new ArgumentFlag("-p", "--priority");
    public static final ArgumentFlag DEADLINE_FLAG = new ArgumentFlag("-d", "--deadline");

    public static final ApplicativeParser<ArgumentFlag> TITLE_FLAG_PARSER = parseFlag(TITLE_FLAG);
    public static final ApplicativeParser<ArgumentFlag> LABEL_FLAG_PARSER = parseFlag(LABEL_FLAG);
    public static final ApplicativeParser<ArgumentFlag> STATUS_FLAG_PARSER = parseFlag(STATUS_FLAG);
    public static final ApplicativeParser<ArgumentFlag> PRIORITY_FLAG_PARSER =
            parseFlag(PRIORITY_FLAG);
    public static final ApplicativeParser<ArgumentFlag> DEADLINE_FLAG_PARSER =
            parseFlag(DEADLINE_FLAG);

    public static final ApplicativeParser<String> TITLE_PARSER = STRING_PARSER;
    public static final ApplicativeParser<String> LABEL_PARSER = STRING_PARSER;
    public static final ApplicativeParser<Status> STATUS_PARSER =
            INT_PARSER.map(Status::fromInt);
    public static final ApplicativeParser<Priority> PRIORITY_PARSER =
            INT_PARSER.map(Priority::fromInt);
    public static final ApplicativeParser<LocalDateTime> DEADLINE_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .map(ignore -> LocalDateTime.now());

    public static final ApplicativeParser<Index> ONE_BASED_INDEX_PARSER =
            INT_PARSER.filter(i -> i >= 1).map(Index::fromOneBased);
    public static final ApplicativeParser<Index> ZERO_BASED_INDEX_PARSER =
            INT_PARSER.filter(i -> i >= 0).map(Index::fromZeroBased);

    public static ApplicativeParser<ArgumentFlag> parseFlag(ArgumentFlag flag) {
        return Stream.of(flag.getShortForm(), flag.getLongForm())
                .filter(Objects::nonNull)
                .map(ApplicativeParser::string)
                .reduce(ApplicativeParser::or)
                .orElseGet(ApplicativeParser::fail)
                .constMap(flag);
    }

    private CommandParserUtil() {}
}
