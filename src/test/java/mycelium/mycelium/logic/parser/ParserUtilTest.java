package mycelium.mycelium.logic.parser;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.testutil.Assert;

public class ParserUtilTest {
    private static final String INVALID_NAME = "   ";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseName_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        Assertions.assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        Assertions.assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        Assertions.assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        Assertions.assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        Assertions.assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        Assertions.assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseYearOfBirth_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseYearOfBirth(null));
    }

    @Test
    public void parseYearOfBirth_invalidValue_throwsParseException() {
        Map<String, String> tests = Map.of(
            "negative", "-1",
            "zero", "0",
            "too large", "10000",
            "not a number", "not a number",
            "empty", "",
            "whitespace", " "
        );
        tests.forEach((key, value) -> {
            Assert.assertThrows(ParseException.class, YearOfBirth.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseYearOfBirth(value));
        });
    }

    @Test
    public void parseYearOfBirth_validValue_returnsYearOfBirth() throws Exception {
        String[] tests = {"2000", "0000", "9999", "0001"};
        for (String tt : tests) {
            YearOfBirth expectedYearOfBirth = new YearOfBirth(tt);
            Assertions.assertEquals(expectedYearOfBirth, ParserUtil.parseYearOfBirth(tt));
        }
    }

    @Test
    public void parseProjectStatus_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseProjectStatus(null));
    }

    @Test
    public void parseProjectStatus_invalidValue_throwsParseException() {
        Map<String, String> tests = Map.of(
            "empty", "",
            "whitespace", " ",
            "not a status", "not a status"
        );
        tests.forEach((key, value) -> {
            Assert.assertThrows(ParseException.class,
                ProjectStatus.MESSAGE_CONSTRAINTS, () -> ParserUtil.parseProjectStatus(value));
        });
    }

    @Test
    public void parseProjectStatus_validValue_returnsProjectStatus() throws Exception {
        String[] tests = {"in_progress", "done", "not_started", "IN_PROGRESS", "iN_pRoGrEsS"};
        for (String tt : tests) {
            ProjectStatus expectedProjectStatus = ProjectStatus.fromString(tt);
            Assertions.assertEquals(expectedProjectStatus, ParserUtil.parseProjectStatus(tt));
        }
    }

    @Test
    public void parseNonEmptyString_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseNonEmptyString(null));
    }

    @Test
    public void parseNonEmptyString_invalidValue_throwsParseException() {
        Map<String, String> tests = Map.of(
            "empty", "",
            "whitespace", "    "
        );
        tests.forEach((key, value) -> {
            Assert.assertThrows(ParseException.class,
                NonEmptyString.MESSAGE_CONSTRAINTS, () -> ParserUtil.parseNonEmptyString(value));
        });
    }

    @Test
    public void parseNonEmptyString_validValue_returnsNonEmptyString() throws Exception {
        Map<String, String> tests = Map.of(
            "single word", "foo",
            "multiple words", "foo bar",
            "special characters", "!@#$%^&*()_+"
        );
        for (var tt : tests.entrySet()) {
            String input = tt.getValue();
            NonEmptyString want = new NonEmptyString(input);
            Assertions.assertEquals(want, ParserUtil.parseNonEmptyString(input));
        }
    }

    @Test
    public void parseLocalDate_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseLocalDate(null, Project.DATE_FMT));
    }

    @Test
    public void parseLocalDate_invalidValue_throwsParseException() {
        Map<String, String> tests = Map.ofEntries(
            Map.entry("empty", ""),
            Map.entry("whitespace", " "),
            Map.entry("not a date", "not a date"),
            Map.entry("invalid format", "2020-01-01"),

            Map.entry("day doesn't exist", "29/02/2001"),

            Map.entry("year less than 4 digits", "01/01/01"),
            Map.entry("month less than 2 digits", "01/1/2001"),
            Map.entry("day less than 2 digits", "1/01/2001"),

            Map.entry("year > 9999", "01/01/10000"),
            Map.entry("month > 12", "01/13/2001"),
            Map.entry("day > 31", "32/01/2001"),

            Map.entry("zero month", "01/00/2001"),
            Map.entry("zero day", "00/01/2001"),

            Map.entry("negative month", "01/-01/2001"),
            Map.entry("negative day", "-01/01/2001")
        );
        tests.forEach((key, value) -> {
            Assert.assertThrows(ParseException.class,
                Messages.MESSAGE_INVALID_DATE, () -> ParserUtil.parseLocalDate(value, Project.DATE_FMT));
        });
    }

    @Test
    public void parseLocalDate_validValue_returnsLocalDate() throws Exception {
        String[] tests = {
            "01/01/2000",
            "01/01/-0001",
            "01/01/0000",
            "01/01/0001",
            "01/01/9999",
            "01/01/-9999"
        };
        for (String tt : tests) {
            LocalDate want = LocalDate.parse(tt, Project.DATE_FMT);
            Assertions.assertEquals(want, ParserUtil.parseLocalDate(tt, Project.DATE_FMT));
        }
    }

    @Test
    public void parseProjectName_illegalValue_throwsParseException() {
        for (var name : ParserUtil.ILLEGAL_PROJECT_NAMES) {
            var msg = String.format(Messages.MESSAGE_ILLEGAL_PROJECT_NAME_FMT, name);
            Assert.assertThrows(ParseException.class, msg, () -> ParserUtil.parseProjectName(name));
        }
    }
}
