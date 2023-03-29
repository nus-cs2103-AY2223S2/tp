package vimification.internal.parser;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import vimification.commons.core.Index;
import vimification.model.task.Priority;

public class CommandParserUtil {

    public static final ApplicativeParser<Integer> INT_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .flatMap(str -> {
                try {
                    int val = Integer.parseInt(str);
                    return ApplicativeParser.of(val);
                } catch (NumberFormatException ex) {
                    return ApplicativeParser.fail();
                }
            });

    public static final ApplicativeParser<Index> ONE_BASED_INDEX_PARSER =
            INT_PARSER.flatMap(val -> {
                try {
                    Index index = Index.fromOneBased(val);
                    return ApplicativeParser.of(index);
                } catch (IndexOutOfBoundsException ex) {
                    return ApplicativeParser.fail();
                }
            });

    public static final ApplicativeParser<Index> ZERO_BASED_INDEX_PARSER =
            INT_PARSER.flatMap(val -> {
                try {
                    Index index = Index.fromZeroBased(val);
                    return ApplicativeParser.of(index);
                } catch (IndexOutOfBoundsException ex) {
                    return ApplicativeParser.fail();
                }
            });

    public static final ApplicativeParser<Priority> PRIORITY_PARSER =
            INT_PARSER.map(Priority::fromInt);

    public static final ApplicativeParser<String> STRING_PARSER = ApplicativeParser
            .choice(ApplicativeParser.string("\""), ApplicativeParser.string("'"))
            .flatMap(quote -> ApplicativeParser
                    .until(quote)
                    .dropNext(ApplicativeParser.string(quote)))
            .or(ApplicativeParser.nonWhitespaces1());

    // public static ApplicativeParser<ArgumentCounter> arguments(ArgumentFlag... flags) {
    // ArgumentCounter map = new ArgumentCounter(flags);
    // /**
    // * This parser parses a single flag.
    // */
    // ApplicativeParser<Void> flagParser = Arrays.stream(flags).map(flag -> {
    // String shortForm = flag.getShortForm();
    // String longForm = flag.getLongForm();
    // return ApplicativeParser
    // .string(shortForm)
    // .or(ApplicativeParser.string(longForm))
    // .dropNext(ApplicativeParser.skipWhitespaces1())
    // .takeNext(STRING_PARSER)
    // .<Void>map(value -> {
    // map.put(flag, value);
    // return null;
    // });
    // }).reduce(ApplicativeParser::or).orElseGet(ApplicativeParser::fail);
    // return flagParser.sepBy(ApplicativeParser.skipWhitespaces1()).constMap(map);
    // }

    public static ApplicativeParser<ArgumentFlag> flag(ArgumentFlag flag) {
        return Stream.of(flag.getShortForm(), flag.getLongForm())
                .filter(Objects::nonNull)
                .map(ApplicativeParser::string)
                .reduce(ApplicativeParser::or)
                .orElseGet(ApplicativeParser::fail)
                .constMap(flag);
    }

    private CommandParserUtil() {}
}
