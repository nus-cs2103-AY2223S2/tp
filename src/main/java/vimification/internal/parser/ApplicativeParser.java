package vimification.internal.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Basic implementation of a generic {@code ApplicativeParser}, based on functional applicative
 * parser from the <b>Haskell programming language</b>.
 * <p>
 * Uses static methods to create instances of this class.
 *
 * @param <T> the type of the parser result
 */
public final class ApplicativeParser<T> {

    private static final Void VOID = null;
    private static final CharPredicate IS_WHITESPACE = Character::isWhitespace;
    private static final CharPredicate IS_NOT_WHITESPACE = IS_WHITESPACE.negate();

    ///////////////////////////////////
    // PREDEFINED PARSER COMBINATORS //
    ///////////////////////////////////

    private static final ApplicativeParser<?> ALWAYS_FAILED_PARSER = fromRunner(
            input -> Optional.empty());

    private static final ApplicativeParser<Void> EOF_PARSER = fromRunner(
            input -> input.isEmpty() ? Optional.of(Pair.of(input, VOID)) : Optional.empty());

    private static final ApplicativeParser<Void> SKIP_WHITESPACES_PARSER =
            discard(IS_WHITESPACE);

    private static final ApplicativeParser<Void> SKIP_WHITESPACES_1_PARSER =
            discard1(IS_WHITESPACE);

    private static final ApplicativeParser<String> NON_WHITESPACES_PARSER =
            munch(IS_NOT_WHITESPACE);

    private static final ApplicativeParser<String> NON_WHITESPACES_1_PARSER =
            munch1(IS_NOT_WHITESPACE);

    private static final ApplicativeParser<String> UNTIL_EOF_PARSER = fromRunner(input -> {
        int length = input.length();
        return Optional.of(Pair.of(input.subview(length), input.substringTo(length)));
    });

    /////////////////////////////////////
    // INSTANCE FIELDS AND CONSTRUCTOR //
    /////////////////////////////////////

    /**
     * The wrapped function.
     * <p>
     * The parser can be made more powerful by changing the container used. For now,
     * {@code Optional} is enough.
     */
    private final Function<StringView, Optional<Pair<StringView, T>>> runner;

    /**
     * Constructs a new {@code ApplicativeParser} instance that wraps a function.
     */
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

    /**
     * Lifts a binary function to work on parsers.
     *
     * @param <T> the type of the first parser result
     * @param <U> the type of the second parser result
     * @param <V> the type of the resultant parser result
     * @param combiner the given binary function
     * @param left the first parser
     * @param right the second parser
     * @return a new parser, that first runs the first parser, then the second parser, and finally
     *         combines the result of these parsers using the binary function
     *
     * @see #lift(Function, ApplicativeParser, ApplicativeParser)
     */
    public static <T, U, V> ApplicativeParser<V> lift(
            BiFunction<? super T, ? super U, ? extends V> combiner,
            ApplicativeParser<? extends T> left,
            ApplicativeParser<? extends U> right) {
        return lift(leftValue -> rightValue -> combiner.apply(leftValue, rightValue), left, right);
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
     * Returns a parser that succeeds when reaches eof, and fails otherwise. The result of this
     * parser should be discarded.
     *
     * @return the eof parser
     */
    public static ApplicativeParser<Void> eof() {
        return EOF_PARSER;
    }

    /**
     * Returns a parser that skips leading whitespaces.
     *
     * @return a parser that skips leading whitespaces
     */
    public static ApplicativeParser<Void> skipWhitespaces() {
        return SKIP_WHITESPACES_PARSER;
    }

    /**
     * Returns a parser that skips at least one leading whitespace.
     *
     * @return a parser that skips at least one leading whitespace
     */
    public static ApplicativeParser<Void> skipWhitespaces1() {
        return SKIP_WHITESPACES_1_PARSER;
    }

    /**
     * Returns a parser that consumes input until it reaches a whitespace character.
     *
     * @return a parser that parses until a whitespace character
     */
    public static ApplicativeParser<String> nonWhitespaces() {
        return NON_WHITESPACES_PARSER;
    }

    /**
     * Returns a parser that consumes at least one non-whitespace character. The parser stops when
     * it reaches a whitespace character.
     *
     * @return a parser that consumes at least one non-whitespaces character
     */
    public static ApplicativeParser<String> nonWhitespaces1() {
        return NON_WHITESPACES_1_PARSER;
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
     * Returns a parser that parses until the given substring is encountered. The substring will not
     * be consumed from the input. This parser will returns the substring between the start of the
     * input, and the last character before the given substring.
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
     * Returns a parser that consumes and returns the next character, if it satisfies the specified
     * predicate.
     *
     * @param predicate the predicate to test the character
     * @return a parser that consumes and returns the next character
     */
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

    /**
     * Returns a parser that consumes and returns the specified character.
     *
     * @param character the specified character
     * @return a parser that consumes and returns the specified character
     */
    public static ApplicativeParser<Character> character(char character) {
        return satisfy(c -> c == character);
    }

    /**
     * Returns a parser that consumes at least {@code n} characters that satisfy the specified
     * predicate. The characters will be joined into a single string.
     *
     * @param predicate the predicate to test the character
     * @param n the minimum number of characters that must be consumed
     * @return a parser that consumes at least {@code n} characters and joins them into a string
     */
    public static ApplicativeParser<String> munchN(CharPredicate predicate, int n) {
        return fromRunner(input -> {
            int length = input.length();
            int offset = 0;
            while (offset < length && predicate.test(input.charAt(offset))) {
                offset++;
            }
            return offset >= n
                    ? Optional.of(Pair.of(input.subview(offset), input.substringTo(offset)))
                    : Optional.empty();
        });
    }

    /**
     * Returns a parser that consumes characters that satisfy the specified predicate, and joins
     * them into a string.
     *
     * @param predicate the predicate to test the character
     * @return a parser that consumes characters and joins them into a string
     *
     * @see #munchN(CharPredicate, int)
     */
    public static ApplicativeParser<String> munch(CharPredicate predicate) {
        return munchN(predicate, 0);
    }

    /**
     * Returns a parser that consumes one or more characters that satisfy the specified predicate
     * and joins them into a string.
     *
     * @param predicate the predicate to test the character
     * @return a parser that consumes one or more characters and joins them into a string
     *
     * @see #munchN(CharPredicate, int)
     */
    public static ApplicativeParser<String> munch1(CharPredicate predicate) {
        return munchN(predicate, 1);
    }

    /**
     * Returns a parser that consumes and discards at least {@code n} characters that satisfy the
     * specified predicate.
     *
     * @param predicate the predicate to test the character
     * @param n the minimum number of characters that must be consumed
     * @return a parser that consumes at least {@code n} characters and discards them
     */
    public static ApplicativeParser<Void> discardN(CharPredicate predicate, int n) {
        return fromRunner(input -> {
            int length = input.length();
            int offset = 0;
            while (offset < length && predicate.test(input.charAt(offset))) {
                offset++;
            }
            return offset >= n
                    ? Optional.of(Pair.of(input.subview(offset), VOID))
                    : Optional.empty();
        });
    }

    /**
     * Returns a parser that consumes and discards characters that satisfy the specified predicate.
     *
     * @param predicate the predicate to test the character
     * @return a parser that consumes and discards characters
     *
     * @see #discardN(CharPredicate, int)
     */
    public static ApplicativeParser<Void> discard(CharPredicate predicate) {
        return discardN(predicate, 0);
    }

    /**
     * Returns a parser that consumes and discards one or more characters that satisfy the specified
     * predicate.
     *
     * @param predicate the predicate to test the character
     * @return a parser that consumes and discards one or more characters
     *
     * @see #discardN(CharPredicate, int)
     */
    public static ApplicativeParser<Void> discard1(CharPredicate predicate) {
        return discardN(predicate, 1);
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
        return choice(Arrays.stream(parsers));
    }

    /**
     * Reduces a stream of parsers into a single parser. The parsers in the original stream will be
     * tried sequentially when the returned parser runs. If the stream is empty, the resultant
     * parser will always fail.
     *
     * @param <T> the type of the parser results
     * @param parsers a stream of parsers
     * @return a parser that tries the given parsers, until one succeeds
     */
    public static <T> ApplicativeParser<T> choice(
            Stream<? extends ApplicativeParser<? extends T>> parsers) {
        return parsers.map(ApplicativeParser::<T>cast)
                .reduce(ApplicativeParser::or)
                .orElse(ApplicativeParser.fail());
    }

    //////////////////////
    // INSTANCE METHODS //
    //////////////////////

    private Optional<Pair<StringView, T>> run(StringView input) {
        return runner.apply(input);
    }

    /**
     * Casts the type argument of this parser to another type. This is an unsafe operation and
     * should be used with caution.
     *
     * @param <U> the type argument to cast to
     * @return the current parser, with the new type argument
     */
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
        return fromRunner(input -> run(input).map(pair -> {
            StringView nextInput = pair.getFirst();
            T value = pair.getSecond();
            U newValue = mapper.apply(value);
            return Pair.of(nextInput, newValue);
        }));
    }

    /**
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

    /**
     * In place version of {@link #lift(Function, ApplicativeParser, ApplicativeParser)}.
     *
     * @param <U> the type of the other parser result
     * @param <V> the type of the resultant parser result
     * @param combiner the (curried) binary function to combine the parser results
     * @param that the other parser
     * @return a new parser, that first runs this parser, then the other parser, and finally
     *         combines the result of these parsers using the (curried) binary function
     */
    public <U, V> ApplicativeParser<V> combine(
            ApplicativeParser<? extends U> that,
            Function<? super T, Function<? super U, ? extends V>> combiner) {
        return lift(combiner, this, that);
    }

    /**
     * In place version of {@link #lift(BiFunction, ApplicativeParser, ApplicativeParser)}.
     *
     * @param <U> the type of the other parser result
     * @param <V> the type of the resultant parser result
     * @param combiner the binary function to combine the parser results
     * @param that the other parser
     * @return a new parser, that first runs this parser, then the other parser, and finally
     *         combines the result of these parsers using the binary function
     */
    public <U, V> ApplicativeParser<V> combine(
            ApplicativeParser<? extends U> that,
            BiFunction<? super T, ? super U, ? extends V> combiner) {
        return lift(combiner, this, that);
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

    /**
     * Runs this parser, then runs the other parsers multiple times. The results are collected into
     * a list.
     *
     * @param that the other parser
     * @return a parser that runs this parser once and the other parser multiple times, then
     *         collects the results into a list.
     */
    public ApplicativeParser<List<T>> thenMany(ApplicativeParser<? extends T> that) {
        return fromRunner(input -> run(input).map(pair -> {
            ApplicativeParser<T> parser = that.cast();
            Pair<StringView, T> nextPair = pair;
            List<T> result = new ArrayList<>();
            while (true) {
                StringView nextInput = nextPair.getFirst();
                T value = nextPair.getSecond();
                result.add(value);
                Optional<Pair<StringView, T>> opt = parser.run(nextInput);
                if (opt.isEmpty()) {
                    return Pair.of(nextInput, result);
                }
                nextPair = opt.get();
            }
        }));
    }

    /**
     * Runs this parser one or more times, and collects the results into a list.
     *
     * @return a parser that runs this parser one or more times
     */
    public ApplicativeParser<List<T>> many1() {
        return thenMany(this);
    }

    /**
     * Runs this parser zero or more times, and collects the results into a list.
     *
     * @return a parser that runs this parser zero or more times
     */
    public ApplicativeParser<List<T>> many() {
        return many1().orElse(ArrayList::new);
    }

    /**
     * Runs this parser one or more times, separated by running the other parsers between
     * consecutive runs. The results of the other parser will be discarded, and the results of this
     * parser will be collected into a single list.
     *
     * @param <U> the type of the other parser result
     * @param that the other parser
     * @return a parser that runs this parser one or more times, separated by running the other
     *         parser
     */
    public <U> ApplicativeParser<List<T>> sepBy1(ApplicativeParser<U> that) {
        return thenMany(that.takeNext(this));
    }

    /**
     * Runs this parser zero or more times, separated by running the other parsers between
     * consecutive runs. The results of the other parser will be discarded, and the results of this
     * parser will be collected into a single list.
     *
     * @param <U> the type of the other parser result
     * @param that the other parser
     * @return a parser that runs this parser zero or more times, separated by running the other
     *         parser
     */
    public <U> ApplicativeParser<List<T>> sepBy(ApplicativeParser<U> that) {
        return sepBy1(that).orElse(ArrayList::new);
    }

    /**
     * Replaces the result of this parser with the given value.
     *
     * @param <U> type of the given value
     * @param otherValue the given value
     * @return a parser that discards the result of this parser, and replaces it with the given
     *         value
     */
    public <U> ApplicativeParser<U> constMap(U otherValue) {
        return fromRunner(input -> run(input).map(pair -> {
            StringView nextInput = pair.getFirst();
            return Pair.of(nextInput, otherValue);
        }));
    }

    /**
     * Replaces the result of this parser with a new value. The value will be lazily produced.
     *
     * @param <U> type of the new value
     * @param supplier a supplier that will produce the new value, when necessary
     * @return a parser that discards the result of this parser, and replaces it with the new value
     *         (if this parser succeeds)
     */
    public <U> ApplicativeParser<U> constMap(Supplier<? extends U> supplier) {
        return fromRunner(input -> run(input).map(pair -> {
            StringView nextInput = pair.getFirst();
            U otherValue = supplier.get();
            return Pair.of(nextInput, otherValue);
        }));
    }

    /**
     * Returns a parser that succeeds if this parser succeeds, and returns the given value if this
     * parser fails.
     *
     * @param otherValue the given value
     * @return a parser that succeeds if this parser succeeds, and returns the given value otherwise
     */
    public ApplicativeParser<T> orElse(T otherValue) {
        return fromRunner(input -> run(input).or(() -> Optional.of(Pair.of(input, otherValue))));
    }

    /**
     * Returns a parser that succeeds if this parser succeeds, produces and returns another value if
     * this parser fails.
     *
     * @param supplier a supplier that will produce a value when necessary
     * @return a parser that succeeds if this parser succeeds, produces and returns another value
     *         otherwise
     */
    public ApplicativeParser<T> orElse(Supplier<? extends T> supplier) {
        return fromRunner(input -> run(input).or(() -> {
            T otherValue = supplier.get();
            return Optional.of(Pair.of(input, otherValue));
        }));
    }

    /**
     * Returns a parser that will call the visiter with the result of this parser to create some
     * side effects. The visiter will not be called if this parser fails. The result of this parser
     * will not be modified.
     *
     * @param visiter the visiter to be called
     * @return a parser that will call the visiter if this parser succeeds
     */
    public ApplicativeParser<T> visit(Consumer<? super T> visiter) {
        return fromRunner(input -> run(input).map(pair -> {
            T value = pair.getSecond();
            visiter.accept(value);
            return pair;
        }));
    }

    /**
     * Returns a parser that will call the consumer with the result of this parser to create some
     * side effects. The consumer will not be called if this parser fails. The result of this parser
     * will be discarded.
     *
     * @param consumer the consumer to be called
     * @return a parser that will call the consumer if this parser succeeds
     */
    public ApplicativeParser<Void> consume(Consumer<? super T> consumer) {
        return fromRunner(input -> run(input).map(pair -> {
            StringView nextInput = pair.getFirst();
            T value = pair.getSecond();
            consumer.accept(value);
            return Pair.of(nextInput, VOID);
        }));
    }

    /**
     * Returns a parser that optionally runs this parser. If this parser succeeds, the result will
     * be discarded, otherwise, the returned parser will not consume any input.
     *
     * @return a parser that optionally runs this parser
     */
    public ApplicativeParser<Void> optional() {
        return fromRunner(input -> run(input).map(pair -> {
            StringView nextInput = pair.getFirst();
            return Pair.of(nextInput, VOID);
        }).or(() -> Optional.of(Pair.of(input, VOID))));
    }

    /**
     * Returns a new parser that throws an exception (immediately) if this parser fails. The thrown
     * exception will stop a parsing pipeline.
     * <p>
     * This method should be used with care, as some other methods will retry with another execution
     * path if this parser fails. Using this method prevents that behavior.
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
