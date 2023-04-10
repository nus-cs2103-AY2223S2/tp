package vimification.internal.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ApplicativeParserTest {

    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS = ParserException.class;

    @Test
    public void string_validInput_shouldSucceed() {
        String input = "Hidden Seasons will pass you by";
        ApplicativeParser<String> parser = ApplicativeParser.string(input);
        Pair<String, String> result = parser.parsePartOf(input);
        assertEquals("", result.getFirst());
        assertEquals(input, result.getSecond());
    }

    @Test
    public void string_invalidInput_shouldThrow() {
        String input = "Witching Dream Battle";
        ApplicativeParser<String> parser = ApplicativeParser.string("Maiden's Capriccio");
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parser.parse(input));
    }

    @Test
    public void until_validInput_shouldSucceed() {
        String input = "Red, White and Black Butterfly";
        ApplicativeParser<String> parser = ApplicativeParser.until("Butterfly");
        Pair<String, String> result = parser.parsePartOf(input);
        assertEquals("Butterfly", result.getFirst());
        assertEquals("Red, White and Black ", result.getSecond());
    }

    @Test
    public void until_invalidInput_shouldThrow() {
        String input = "Dichromatic Lotus Butterfly";
        ApplicativeParser<String> parser = ApplicativeParser.until("Red and White");
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parser.parse(input));
    }

    @Test
    public void shouldLiftCorrectly() {
        String input = "The Rabbit Has Landed";
        ApplicativeParser<String> leftParser = ApplicativeParser
                .string("The Rabbit")
                .dropNext(ApplicativeParser.skipWhitespaces());
        ApplicativeParser<String> rightParser = ApplicativeParser.string("Has Landed");
        ApplicativeParser<String> parser = ApplicativeParser.lift((
                left, right) -> String.join(" ", left, right),
                leftParser,
                rightParser);
        Pair<String, String> result = parser.parsePartOf(input);
        assertEquals("", result.getFirst());
        assertEquals(input, result.getSecond());
    }

    @Test
    public void shouldMapCorrectly() {
        String input = "Legacy of Lunatic Kingdom";
        ApplicativeParser<String> parser = ApplicativeParser
                .string(input)
                .map(result -> result + " (LoLK)");
        Pair<String, String> result = parser.parsePartOf(input);
        assertEquals("", result.getFirst());
        assertEquals(input + " (LoLK)", result.getSecond());
    }

    @Test
    public void shouldFlatMapCorrectly() {
        String input = "Tonight Stars an Easygoing Egoist";
        ApplicativeParser<String> parser = ApplicativeParser
                .string("Tonight Stars")
                .flatMap(left -> ApplicativeParser
                        .skipWhitespaces()
                        .takeNext(ApplicativeParser.string("an"))
                        .flatMap(middle -> ApplicativeParser
                                .skipWhitespaces()
                                .takeNext(ApplicativeParser.string("Easygoing Egoist"))
                                .flatMap(right -> ApplicativeParser
                                        .of(String.join(" ", left, middle, right)))));
        Pair<String, String> result = parser.parsePartOf(input);
        assertEquals("", result.getFirst());
        assertEquals(input, result.getSecond());
    }

    @Test
    public void of_anyInput_shouldNotConsume() {
        String input = "The Girl's Secret Room";
        String output = "Locked Girl";
        ApplicativeParser<String> parser = ApplicativeParser.of(output);
        Pair<String, String> result = parser.parsePartOf(input);
        assertEquals(input, result.getFirst());
        assertEquals(output, result.getSecond());
    }

    @Test
    public void many1_validInput_shouldStopAtCorrectPosition() {
        String input = "Shoot the Bullet";
        ApplicativeParser<List<String>> parser = ApplicativeParser
                .nonWhitespaces1()
                .filter(str -> !str.equals("Bullet"))
                .dropNext(ApplicativeParser.skipWhitespaces())
                .many1();
        Pair<String, List<String>> result = parser.parsePartOf(input);
        assertEquals("Bullet", result.getFirst());
        assertEquals(List.of("Shoot", "the"), result.getSecond());
    }

    @Test
    public void munch1_invalidInput_shouldFail() {
        String input = "Night Falls ~ Evening Star";
        ApplicativeParser<String> parser = ApplicativeParser.munch1(c -> c == 'n');
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parser.parse(input));
    }

    @Test
    public void constMapLazyVer_invalidInput_shouldNotEvaluateOnFail() {
        String input = "bad apple";
        boolean[] wasExecuted = {false};
        ApplicativeParser<String> parser = ApplicativeParser
                .string("Bad Apple")
                .constMap(() -> {
                    wasExecuted[0] = true;
                    return "bad apple";
                });
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parser.parse(input));
        assertFalse(wasExecuted[0]);
    }
}
