package vimification.internal.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;
import java.util.function.Supplier;

import vimification.common.core.Index;
import vimification.model.task.Priority;
import vimification.model.task.Status;

/**
 * Common utilities for parsing different commands.
 * <p>
 * The utilities is provided using static methods.
 */
public class CommandParserUtil {

    //////////////////////
    // DATE-TIME FORMAT //
    //////////////////////

    private static final DateTimeFormatter DAY_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("EEE");
    private static final DateTimeFormatter DATE_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm");
    private static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);

    ///////////////////////
    // PRIMITIVE PARSERS //
    ///////////////////////

    public static final ApplicativeParser<String> WORD_PARSER = ApplicativeParser
            .munch1(c -> !Character.isWhitespace(c) && c != '"' && c != '\'');

    public static final ApplicativeParser<String> STRING_PARSER = ApplicativeParser
            .choice(ApplicativeParser.character('"'),
                    ApplicativeParser.character('\''))
            .flatMap(q -> ApplicativeParser
                    .munch1(c -> c != q)
                    .filter(s -> !s.isEmpty())
                    .dropNext(ApplicativeParser.character(q)))
            .or(WORD_PARSER);

    public static final ApplicativeParser<Integer> INT_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .optionalMap(CommandParserUtil::parseInt);

    public static final ApplicativeParser<LocalDate> DATE_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .optionalMap(CommandParserUtil::parseDate);

    public static final ApplicativeParser<LocalDate> DAY_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .map(input -> {
                String head = input.substring(0, 1).toUpperCase();
                String tail = input.substring(1).toLowerCase();
                return head + tail;
            })
            .optionalMap(CommandParserUtil::parseDay);

    public static final ApplicativeParser<LocalTime> TIME_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .optionalMap(CommandParserUtil::parseTime);

    public static final ApplicativeParser<LocalDateTime> DATE_TIME_PARSER = ApplicativeParser
            .choice(DATE_PARSER, DAY_PARSER)
            .combine(ApplicativeParser
                    .skipWhitespaces1()
                    .takeNext(TIME_PARSER)
                    .orElse(DEFAULT_TIME),
                    LocalDateTime::of);

    ///////////
    // FLAGS //
    ///////////

    public static final LiteralArgumentFlag TITLE_FLAG =
            new LiteralArgumentFlag("-t", "--title");
    public static final LiteralArgumentFlag LABEL_FLAG =
            new LiteralArgumentFlag("-l", "--label");
    public static final LiteralArgumentFlag STATUS_FLAG =
            new LiteralArgumentFlag("-s", "--status");
    public static final LiteralArgumentFlag PRIORITY_FLAG =
            new LiteralArgumentFlag("-p", "--priority");
    public static final LiteralArgumentFlag DEADLINE_FLAG =
            new LiteralArgumentFlag("-d", "--deadline");
    public static final LiteralArgumentFlag KEYWORD_FLAG =
            new LiteralArgumentFlag("-w", "--keyword");

    public static final LiteralArgumentFlag BEFORE_FLAG =
            new LiteralArgumentFlag(null, "--before");
    public static final LiteralArgumentFlag AFTER_FLAG =
            new LiteralArgumentFlag(null, "--after");

    public static final LiteralArgumentFlag ADD_MACRO_FLAG =
            new LiteralArgumentFlag("-a", "--add");
    public static final LiteralArgumentFlag DELETE_MACRO_FLAG =
            new LiteralArgumentFlag("-d", "--delete");
    public static final LiteralArgumentFlag LIST_MACRO_FLAG =
            new LiteralArgumentFlag("-l", "--list");

    public static final LiteralArgumentFlag OR_FLAG =
            new LiteralArgumentFlag("-o", "--or");
    public static final LiteralArgumentFlag AND_FLAG =
            new LiteralArgumentFlag("-a", "--and");

    public static final ComposedArgumentFlag MACRO_FLAG =
            new ComposedArgumentFlag(ADD_MACRO_FLAG, DELETE_MACRO_FLAG, LIST_MACRO_FLAG);
    public static final ComposedArgumentFlag FILTER_FLAG =
            new ComposedArgumentFlag(AND_FLAG, OR_FLAG);
    public static final ComposedArgumentFlag SORT_FLAG =
            new ComposedArgumentFlag(DEADLINE_FLAG, PRIORITY_FLAG, STATUS_FLAG);

    //////////////////
    // FLAG PARSERS //
    //////////////////

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

    ///////////////////
    // FIELD PARSERS //
    ///////////////////

    public static final ApplicativeParser<String> TITLE_PARSER =
            STRING_PARSER;
    public static final ApplicativeParser<String> LABEL_PARSER =
            STRING_PARSER;
    public static final ApplicativeParser<Status> STATUS_PARSER =
            INT_PARSER.map(Status::fromInt);
    public static final ApplicativeParser<Priority> PRIORITY_PARSER =
            INT_PARSER.map(Priority::fromInt);
    public static final ApplicativeParser<LocalDateTime> DEADLINE_PARSER =
            DATE_TIME_PARSER;

    public static final ApplicativeParser<Index> ONE_BASED_INDEX_PARSER =
            INT_PARSER.map(Index::fromOneBased);
    public static final ApplicativeParser<Index> ZERO_BASED_INDEX_PARSER =
            INT_PARSER.map(Index::fromZeroBased);

    public static final ApplicativeParser<Void> END_OF_COMMAND_PARSER =
            ApplicativeParser
                    .skipWhitespaces()
                    .dropNext(ApplicativeParser.eof());

    // ApplicativeParser.eof(),
    // ApplicativeParser.untilEof().consume(remaining -> {
    // throw new ParserException("Syntax error: " + remaining);
    // })));

    /**
     * Provides utilities with static fields and methods. Instance of this class cannot be
     * initialized.
     */
    private CommandParserUtil() {}

    ///////////////
    // UTILITIES //
    ///////////////

    /**
     * Creates a parser that parses a {@link LiteralArgumentFlag}.
     *
     * @param flag the literal flag
     * @return a parser that parses the flag
     */
    public static ApplicativeParser<LiteralArgumentFlag> parseFlag(LiteralArgumentFlag flag) {
        return ApplicativeParser.choice(
                flag.getForms()
                        .stream()
                        .map(ApplicativeParser::string))
                .constMap(flag);
    }

    /**
     * Creates a parser that parses a {@link ComposedArgumentFlag}.
     *
     * @param flag the composed flag
     * @return a parser that parses the flag
     */
    public static ApplicativeParser<ComposedArgumentFlag> parseFlag(ComposedArgumentFlag flag) {
        return ApplicativeParser.choice(
                flag.getFlags()
                        .stream()
                        .map(CommandParserUtil::parseFlag))
                .map(flag::cloneWith);
    }

    /**
     * Runs a supplier, and returns an {@code Optional} contains the result if no exception occured.
     * Otherwise, returns an empty {@code Optional}.
     *
     * @param <T> type of the object returned by the supplier
     * @param supplier the supplier that will creates the object
     * @return an {@code Optional} contains the result if succeeds, otherwise an empty
     *         {@code Optional}.
     */
    private static <T> Optional<T> optionallyParse(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
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
     * returned date will be the nearest day of week (that is represented by the input string) in
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

    /**
     * Converts an input string to a {@code LocalTime} instance, using {@code HH:mm} format. If the
     * conversion fails, an empty {@code Optional} is returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding the conversion result
     */
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
}
