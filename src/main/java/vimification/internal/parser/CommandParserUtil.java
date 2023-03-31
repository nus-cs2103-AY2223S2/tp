package vimification.internal.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import vimification.commons.core.Index;
import vimification.model.task.Priority;
import vimification.model.task.Status;

public class CommandParserUtil {

    private static final DateTimeFormatter DAY_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("EEE");
    private static final DateTimeFormatter DATE_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm");
    private static final LocalTime TIME_ZERO_ZERO = LocalTime.of(0, 0);

    public static final ApplicativeParser<String> STRING_PARSER = ApplicativeParser
            .choice(ApplicativeParser.string("\""), ApplicativeParser.string("'"))
            .flatMap(quote -> ApplicativeParser
                    .until(quote)
                    .filter(str -> !str.isEmpty())
                    .dropNext(ApplicativeParser.string(quote)))
            .or(ApplicativeParser.munch1(ch -> !Character.isWhitespace(ch)
                    && ch != '"' && ch != '\''));

    public static final ApplicativeParser<Integer> INT_PARSER =
            ApplicativeParser
                    .nonWhitespaces1()
                    .optionalMap(CommandParserUtil::parseInt);

    public static final ApplicativeParser<LocalDate> DATE_PARSER =
            ApplicativeParser
                    .nonWhitespaces1()
                    .optionalMap(CommandParserUtil::parseDate);

    public static final ApplicativeParser<LocalDate> DAY_PARSER =
            ApplicativeParser
                    .nonWhitespaces1()
                    .map(input -> {
                        String head = input.substring(0, 1).toUpperCase();
                        String tail = input.substring(1).toLowerCase();
                        return head + tail;
                    })
                    .optionalMap(CommandParserUtil::parseDay);

    public static final ApplicativeParser<LocalTime> TIME_PARSER =
            ApplicativeParser
                    .nonWhitespaces1()
                    .optionalMap(CommandParserUtil::parseTime);

    public static final ApplicativeParser<LocalDateTime> DATE_TIME_PARSER =
            ApplicativeParser.choice(DATE_PARSER, DAY_PARSER)
                    .combine(ApplicativeParser
                            .skipWhitespaces1()
                            .takeNext(TIME_PARSER)
                            .orElse(TIME_ZERO_ZERO),
                            LocalDateTime::of);

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
    public static final LiteralArgumentFlag LIST_MACRO_FLAG =
            new LiteralArgumentFlag("-l", "--list");
    public static final ComposedArgumentFlag MACRO_FLAG =
            new ComposedArgumentFlag(ADD_MACRO_FLAG, DELETE_MACRO_FLAG, LIST_MACRO_FLAG);

    public static final LiteralArgumentFlag OR_FLAG = new LiteralArgumentFlag("-o", "--or");
    public static final LiteralArgumentFlag AND_FLAG = new LiteralArgumentFlag("-a", "--and");
    public static final ComposedArgumentFlag FILTER_FLAG =
            new ComposedArgumentFlag(AND_FLAG, OR_FLAG);
    public static final ComposedArgumentFlag SORT_FLAG = new ComposedArgumentFlag(
            DEADLINE_FLAG, PRIORITY_FLAG, STATUS_FLAG);

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
    public static final ApplicativeParser<ComposedArgumentFlag> FILTER_FLAG_PARSER =
            parseFlag(FILTER_FLAG);
    public static final ApplicativeParser<ComposedArgumentFlag> SORT_FLAG_PARSER =
            parseFlag(SORT_FLAG);

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

    private static <T> Optional<T> optionallyParse(Supplier<T> parser) {
        T result;
        try {
            result = parser.get();
        } catch (RuntimeException ex) {
            result = null;
        }
        return Optional.ofNullable(result);
    }

    /**
     * Converts an input string to a {@code LocalDate} instance, using {@code yyyy-MM-dd} format. If
     * the conversion fails, an empty {@code Optional} is returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding the conversion result
     */
    public static Optional<LocalDate> parseDate(String input) {
        return optionallyParse(() -> LocalDate.from(DATE_INPUT_FORMAT.parse(input)));
    }

    /**
     * Converts an input string to a {@code LocalDate} instance, using {@code EEE} format. The
     * returned date will be the nearest day of week (that is represented by the input string), in
     * the future. If the conversion fails, an empty {@code Optional} is returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding the conversion result
     */
    public static Optional<LocalDate> parseDay(String input) {
        return optionallyParse(() -> LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.from(DAY_INPUT_FORMAT.parse(input)))));
    }

    public static Optional<LocalTime> parseTime(String input) {
        return optionallyParse(() -> LocalTime.from(TIME_INPUT_FORMAT.parse(input)));
    }

    /**
     * Converts an input string to an integer. If the conversion fails, an empty {@code Optional} is
     * returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding conversion result
     */
    public static Optional<Integer> parseInt(String input) {
        return optionallyParse(() -> Integer.parseInt(input));
    }

    private CommandParserUtil() {}
}
