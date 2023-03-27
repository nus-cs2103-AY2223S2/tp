package vimification.internal.parser;

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

    // public static final ApplicativeParser<String> SHORT_FLAG_PARSER = ApplicativeParser
    //         .string("-")
    //         .combine(ApplicativeParser.satisfy(Character::isLetter), s -> c -> s + c);

    // public static final ApplicativeParser<String> LONG_FLAG_PARSER = ApplicativeParser
    //         .string("--")
    //         .combine(ApplicativeParser.letters1(), String::concat);

    // public static final ApplicativeParser<Pair<String, String>> ARG_PARSER = ApplicativeParser
    //         .choice(SHORT_FLAG_PARSER, LONG_FLAG_PARSER)
    //         .dropNext(ApplicativeParser.skipWhitespaces1())
    //         .combine(STRING_PARSER, Pair::of);

    // public static final ApplicativeParser<ArgumentMultimap> ARG_MAP_PARSER = ARG_PARSER
    //         .sepBy(ApplicativeParser.skipWhitespaces1())
    //         .map(list -> {
    //             ArgumentMultimap map = new ArgumentMultimap();
    //             for (Pair<String, String> entry : list) {
    //                 String key = entry.getFirst();
    //                 String value = entry.getSecond();
    //                 map.put(key, value);
    //             }
    //             return map;
    //         });

    private CommandParserUtil() {}
}
