package vimification.internal.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Basic implementation of a generic {@code ApplicativeParser}, based on functional applicative
 * parser from the <b>Haskell programming language</b>.
 * <p>
 * Uses static methods to create instances of this class.
 *
 * @param <T> the type of the parser result
 */
public final class ApplicativeParser<T> {

    ///////////////////////////////////
    // PREDEFINED PARSER COMBINATORS //
    ///////////////////////////////////

    private static final ApplicativeParser<?> ALWAYS_FAILED_PARSER =
            fromRunner(ignore -> Optional.empty());

    private static final ApplicativeParser<Void> EOF_PARSER = fromRunner(
            input -> input.isEmpty() ? Optional.of(Pair.of(input, null)) : Optional.empty());

    private static final ApplicativeParser<Void> SKIP_WHITESPACES_PARSER = fromRunner(input -> {
        int length = input.length();
        int offset = 0;
        while (offset < length && Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
        return Optional.of(Pair.of(input.subview(offset), null));
    });

    private static final ApplicativeParser<Void> SKIP_WHITESPACES_1_PARSER =
            satisfy(Character::isWhitespace).takeNext(SKIP_WHITESPACES_PARSER);

    private static final ApplicativeParser<String> NON_WHITESPACES_1_PARSER =
            munch1(c -> !Character.isWhitespace(c));

    private static final ApplicativeParser<String> LETTERS_1_PARSER =
            munch1(Character::isLetter);

    private static final ApplicativeParser<String> UNTIL_EOF_PARSER = fromRunner(input -> {
        int length = input.length();
        return Optional.of(Pair.of(input.subview(length), input.substringTo(length)));
    });

    /////////////////////////////////////
    // INSTANCE FIELDS AND CONSTRUCTOR //
    /////////////////////////////////////

    private final Function<StringView, Optional<Pair<StringView, T>>> runner;

    private ApplicativeParser(
            Function<StringView, Optional<Pair<StringView, T>>> runner) {
        this.runner = runner;
    }

    //////////////////////
    // STATIC UTILITIES //
    //////////////////////

    private static <T> ApplicativeParser<T> fromRunner(
            Function<StringView, Optional<Pair<StringView, T>>> runner) {
        return new ApplicativeParser<>(runner);
    }

    /**
     * Creates a parser that parses nothing and returns exactly the given value. This method is
     * equivalent to {@code return} function in <b>Haskell</b>.
     *
     * @param <T> the type of the given value
     * @param value the given value
     * @return a parser that parses nothing, and returns exactly the given value
     */
    public static <T> ApplicativeParser<T> of(T value) {
        return fromRunner(input -> Optional.of(Pair.of(input, value)));
    }

    /**
     * Returns a parser that always fails.
     *
     * @param <T> the type of the parser result (which does not exist, in this case)
     * @return a parser that always fails
     */
    public static <T> ApplicativeParser<T> fail() {
        return ALWAYS_FAILED_PARSER.cast();
    }

    /**
     * Lifts a (curried) binary function to work on parsers. The first parser is run on the input,
     * and may consume some characters. Then, the second parser is run on the remaining input.
     * Finally, the results of the two parsers are combined using the binary function.
     * <p>
     * This method is equivalent to {@code liftA2} function in <b>Haskell</b>.
     * <p>
     * This method proves that an {@code ApplicativeParser} is an {@code Applicative}. However,
     * there is no way to encode the behavior of an {@code Applicative} using an instance method in
     * <b>Java</b>.
     *
     * @param <T> the type of the first parser result
     * @param <U> the type of the second parser result
     * @param <V> the type of the resultant parser result
     * @param combiner the given (curried) binary function
     * @param left the first parser
     * @param right the second parser
     * @return a new parser, that first runs the first parser, then the second parser, and finally
     *         combines the result of these parsers using the binary function
     */
    public static <T, U, V> ApplicativeParser<V> lift(
            Function<? super T, ? extends Function<? super U, ? extends V>> combiner,
            ApplicativeParser<? extends T> left,
            ApplicativeParser<? extends U> right) {
        return left.flatMap(combiner.andThen(right::map));
    }

    public static <T, U, V> ApplicativeParser<V> lift(
            BiFunction<? super T, ? super U, ? extends V> combiner,
            ApplicativeParser<? extends T> left,
            ApplicativeParser<? extends U> right) {
        return lift(leftValue -> rightValue -> combiner.apply(leftValue, rightValue), left, right);
    }

    /**
     * Returns a parser that succeeds when reaches eof, and fails otherwise. The result of this
     * parser should be discarded.
     *
     * @return the eof parser
     */
    public static ApplicativeParser<Void> eof() {
        return EOF_PARSER;
    }

    /**
     * Returns a parser that skips all leading whitespaces.
     *
     * @return a parser that skips all leading whitespaces
     */
    public static ApplicativeParser<Void> skipWhitespaces() {
        return SKIP_WHITESPACES_PARSER;
    }

    public static ApplicativeParser<Void> skipWhitespaces1() {
        return SKIP_WHITESPACES_1_PARSER;
    }

    /**
     * Returns a parser that consumes input until it reaches a whitespace character.
     *
     * @return a parser that parses until a whitespace character
     */
    public static ApplicativeParser<String> nonWhitespaces1() {
        return NON_WHITESPACES_1_PARSER;
    }

    public static ApplicativeParser<String> letters1() {
        return LETTERS_1_PARSER;
    }

    /**
     * Returns a parser that consumes all remaining input.
     *
     * @return a parser that parses until eof
     */
    public static ApplicativeParser<String> untilEof() {
        return UNTIL_EOF_PARSER;
    }

    /**
     * Returns a parser that succeeds when the input starts with the given prefix, fails otherwise.
     * If the parser succeeds, the result of the parser will be the given prefix.
     *
     * @param prefix the given prefix
     * @return a parser that succeeds when the input starts with the given prefix, fails otherwise
     */
    public static ApplicativeParser<String> string(String prefix) {
        return fromRunner(input -> input.startsWith(prefix)
                ? Optional.of(Pair.of(input.subview(prefix.length()), prefix))
                : Optional.empty());
    }

    /**
     * Returns a parser that parses until the given substring is encountered. The substring will
     * also be consumed from the input. This parser will returns the substring between the start of
     * the input, and the last character before the given substring.
     *
     * @param end the substring to find
     * @return a parser that parses until the given substring is encountered
     */
    public static ApplicativeParser<String> until(String end) {
        return fromRunner(input -> {
            int offset = input.indexOf(end);
            if (offset < 0) {
                return Optional.empty();
            }
            return Optional.of(Pair.of(input.subview(offset), input.substringTo(offset)));
        });
    }

    public static ApplicativeParser<Character> character(char ch) {
        return satisfy(c -> c == ch);
    }

    public static ApplicativeParser<Character> satisfy(CharPredicate predicate) {
        return fromRunner(input -> {
            if (input.isEmpty()) {
                return Optional.empty();
            }
            char value = input.charAt(0);
            return predicate.test(value)
                    ? Optional.of(Pair.of(input.subview(1), value))
                    : Optional.empty();
        });
    }

    public static ApplicativeParser<String> munch(CharPredicate predicate) {
        return fromRunner(input -> {
            int length = input.length();
            int offset = 0;
            while (offset < length && predicate.test(input.charAt(offset))) {
                offset++;
            }
            return Optional.of(Pair.of(input.subview(offset), input.substringTo(offset)));
        });
    }

    public static ApplicativeParser<String> munch1(CharPredicate predicate) {
        return lift(c -> s -> c + s, satisfy(predicate), munch(predicate));
    }

    /**
     * Returns a parser that tries multiple parsers, until one succeeds.
     *
     * @param <T> the type of the parser results
     * @param parsers an array of parsers
     * @return a parser that tries the given parsers, until one succeeds
     */
    @SafeVarargs
    public static <T> ApplicativeParser<T> choice(ApplicativeParser<? extends T>... parsers) {
        return Arrays.stream(parsers)
                .map(ApplicativeParser::<T>cast)
                .reduce(ApplicativeParser::or)
                .orElseGet(ApplicativeParser::fail);
    }

    //////////////////////
    // INSTANCE METHODS //
    //////////////////////

    private Optional<Pair<StringView, T>> run(StringView input) {
        return runner.apply(input);
    }

    @SuppressWarnings("unchecked")
    private <U> ApplicativeParser<U> cast() {
        return (ApplicativeParser<U>) this;
    }

    /**
     * Returns a parser that runs this parser and another parser, but drops the result of that
     * parser. This method is equivalent to {@code <*} operator in <b>Haskell</b>.
     *
     * @param <U> the type of the other parser result
     * @param that the other parser
     * @return a new parser that runs both parsers, but keeps the result of only one parser
     */
    public <U> ApplicativeParser<T> dropNext(ApplicativeParser<U> that) {
        return lift(left -> right -> left, this, that);
    }

    /**
     * Returns a parser that runs this parser and another parser, but takes the result of that
     * parser. This method is equivalent to {@code *>} operator in <b>Haskell</b>.
     *
     * @param <U> the type of the other parser result
     * @param that the other parser
     * @return a new parser that runs both parsers, but keeps the result of only one parser
     */
    public <U> ApplicativeParser<U> takeNext(ApplicativeParser<U> that) {
        return lift(left -> right -> right, this, that);
    }

    /**
     * Maps a function over the result of this parser.
     * <p>
     * This method is equivalent to {@code fmap} function in <b>Haskell</b>.
     * <p>
     * This method proves that an {@code ApplicativeParser} is a {@code Functor}.
     *
     * @param <U> the type of the new result
     * @param mapper the function to map the result of this parser
     * @return a new parser that applies the mapper function, after running this parser
     */
    public <U> ApplicativeParser<U> map(Function<? super T, ? extends U> mapper) {
        return fromRunner(input -> run(input).map(pair -> pair.mapSecond(mapper)));
    }

    /**
     *
     * Runs this parser, then uses the given function to create another parser from the result of
     * this parser and run that parser.
     * <p>
     * This method is equivalent to {@code >>=} operator in <b>Haskell</b>.
     * <p>
     * This method proves that an {@code ApplicativeParser} is a {@code Monad}.
     *
     * @param <U> the type of the generated parser result
     * @param flatMapper the function to create another parser from the result of this parser
     * @return a parser that runs this parser, and then uses the mapper function to create and run
     *         another parser
     */
    public <U> ApplicativeParser<U> flatMap(
            Function<? super T, ? extends ApplicativeParser<? extends U>> flatMapper) {
        return fromRunner(input -> run(input).flatMap(pair -> {
            StringView nextInput = pair.getFirst();
            T value = pair.getSecond();
            ApplicativeParser<? extends U> nextParser = flatMapper.apply(value);
            return nextParser.<U>cast().run(nextInput);
        }));
    }

    /**
     * Maps a function that produces an {@code Optional} over the result of this parser, and unwraps
     * that {@code Optional}. If the {@code Optional} is empty, the parser fails.
     *
     * @param <U> the type of the new result
     * @param optionalMapper the function to map the result of this parser
     * @return a new parser that applies the mapper function, after running this parser
     */
    public <U> ApplicativeParser<U> optionalMap(
            Function<? super T, ? extends Optional<? extends U>> optionalMapper) {
        return fromRunner(input -> run(input).flatMap(pair -> {
            StringView nextInput = pair.getFirst();
            T value = pair.getSecond();
            return optionalMapper.apply(value).map(newValue -> Pair.of(nextInput, newValue));
        }));
    }

    public <U> ApplicativeParser<U> constMap(U otherValue) {
        return map(ignore -> otherValue);
    }

    public <U, V> ApplicativeParser<V> combine(
            ApplicativeParser<? extends U> that,
            BiFunction<? super T, ? super U, ? extends V> combiner) {
        return lift(combiner, this, that);
    }

    public <U, V> ApplicativeParser<V> combine(
            ApplicativeParser<? extends U> that,
            Function<? super T, Function<? super U, ? extends V>> combiner) {
        return lift(combiner, this, that);
    }

    public ApplicativeParser<Void> consume(Consumer<? super T> action) {
        return map(value -> {
            action.accept(value);
            return null;
        });
    }

    /**
     * Uses a predicate to filter the result of this parser. If the test fails, the parser also
     * fails.
     *
     * @param predicate the predicate to test the result of this parser
     * @return a new parser that runs this parser and uses the predicate to test the result
     */
    public ApplicativeParser<T> filter(Predicate<? super T> predicate) {
        return fromRunner(input -> run(input).filter(pair -> predicate.test(pair.getSecond())));
    }

    /**
     * Chooses between this parser, or another parser.
     *
     * @param that the other parser
     * @return a parser that chooses the result of the first succeeds parser
     */
    public ApplicativeParser<T> or(ApplicativeParser<? extends T> that) {
        return fromRunner(input -> run(input).or(() -> that.<T>cast().run(input)));
    }

    public ApplicativeParser<List<T>> many() {
        return fromRunner(input -> {
            List<T> result = new ArrayList<>();
            StringView nextInput = input;
            while (true) {
                Optional<Pair<StringView, T>> opt = run(nextInput);
                if (opt.isEmpty()) {
                    break;
                }
                Pair<StringView, T> pair = opt.get();
                nextInput = pair.getFirst();
                result.add(pair.getSecond());
            }
            return Optional.of(Pair.of(input, result));
        });
    }

    public ApplicativeParser<List<T>> many1() {
        return fromRunner(input -> run(input).map(pair -> {
            List<T> result = new ArrayList<>();
            StringView nextInput = pair.getFirst();
            result.add(pair.getSecond());
            while (true) {
                Optional<Pair<StringView, T>> opt = run(nextInput);
                if (opt.isEmpty()) {
                    break;
                }
                Pair<StringView, T> nextPair = opt.get();
                nextInput = nextPair.getFirst();
                result.add(nextPair.getSecond());
            }
            return Pair.of(nextInput, result);
        }));
    }

    public <U> ApplicativeParser<List<T>> sepBy(ApplicativeParser<U> that) {
        return sepBy1(that).orElse(new ArrayList<>());
    }

    public <U> ApplicativeParser<List<T>> sepBy1(ApplicativeParser<U> that) {
        ApplicativeParser<T> parser = that.takeNext(this);
        return fromRunner(input -> run(input).map(pair -> {
            List<T> result = new ArrayList<>();
            StringView nextInput = pair.getFirst();
            result.add(pair.getSecond());
            while (true) {
                Optional<Pair<StringView, T>> opt = parser.run(nextInput);
                if (opt.isEmpty()) {
                    break;
                }
                Pair<StringView, T> nextPair = opt.get();
                nextInput = nextPair.getFirst();
                result.add(nextPair.getSecond());
            }
            return Pair.of(nextInput, result);
        }));
    }

    public ApplicativeParser<T> orElse(T otherValue) {
        return or(of(otherValue));
    }

    public ApplicativeParser<Void> optional() {
        return this.<Void>constMap(null).orElse(null);
    }

    /**
     * Returns a new parser that throws an exception (immediately) if this parser fails. The thrown
     * exception will stop a parsing pipeline.
     *
     * @param errorMessage the error message of the exception
     * @return a new parser that throws if this parser fails
     */
    public ApplicativeParser<T> throwIfFail(String errorMessage) {
        return fromRunner(input -> {
            Optional<Pair<StringView, T>> opt = run(input);
            if (opt.isEmpty()) {
                throw new ParserException(errorMessage);
            }
            return opt;
        });
    }

    /**
     * Runs this parser on the given input.
     *
     * @param input the given input, to be parsed
     * @return a pair consists of the remaining input, and the parser result
     * @throws ParserException if this parser fails
     */
    public Pair<String, T> parsePartOf(String input) {
        return run(StringView.of(input))
                .map(pair -> pair.mapFirst(StringView::toString))
                .orElseThrow(() -> new ParserException("Unable to parse input: " + input));
    }

    /**
     * Runs this parser on the given input.
     *
     * @param input the given input, to be parsed
     * @return the parser result
     * @throws ParserException if this parser fails
     */
    public T parse(String input) {
        return run(StringView.of(input))
                .map(Pair::getSecond)
                .orElseThrow(() -> new ParserException("Unable to parse input: " + input));
    }
}
