package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_NO_PREAMBLE_REQUIRED = "There is no need for a preamble for this command";

    /**
     * Formatter for String to LocalDateTime.
     */
    private static DateTimeFormatter nonRecurringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Formatter for String to LocalDateTime for Daily Recurring Events
     */
    private static DateTimeFormatter dailyFormatter = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Formatter for String to LocalDateTime for Weekly Recurring Events
     */
    private static DateTimeFormatter weeklyFormatter = DateTimeFormatter.ofPattern("EEEE HHmm");

    /**
     * Formatter for String to LocalDateTime for Monthly Recurring Events
     */
    private static DateTimeFormatter monthlyFormatter = DateTimeFormatter.ofPattern("dd HHmm");

    /**
     * Formatter for String to LocalDateTime for Yearly Recurring Events
     */
    private static DateTimeFormatter yearlyFormatter = DateTimeFormatter.ofPattern("MM-dd HHmm");


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {

        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        if (phone == null) {
            return null;
        }
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        if (address == null) {
            return null;
        }
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        if (email == null) {
            return null;
        }
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        if (tag == null) {
            return null;
        }
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        if (tags.isEmpty()) {
            return new HashSet<>();
        }
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    //    public static Favorite parseFavorite(String favorite) throws ParseException{
    //        if (favorite == null) {
    //            return null;
    //        }
    //        String trimmedFavorite = favorite.trim();
    //        if (!Favorite.isValidFavorite(trimmedFavorite)) {
    //            throw new ParseException(Favorite.MESSAGE_CONSTRAINTS);
    //        }
    //        return new Favorite(false);
    //    }

    /**
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        if (gender == null) {
            return null;
        }
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String major} into an {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        if (major == null) {
            return null;
        }
        String trimmedMajor = major.trim();
        if (!Major.isValidMajor(trimmedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return new Major(trimmedMajor);
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Modules}.
     */
    public static Modules parseModules(Collection<String> modules) throws ParseException {
        if (modules.isEmpty()) {
            return new Modules(new HashSet<>());
        }
        final Set<NusMod> modulesSet = new HashSet<>();
        for (String module : modules) {
            modulesSet.add(parseModule(module));
        }
        return new Modules(modulesSet);
    }

    /**
     * Parses a {@code String module} into an {@code NusMod}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code module} is invalid.
     */
    private static NusMod parseModule(String module) throws ParseException {
        if (module == null) {
            return null;
        }
        String trimmedTag = module.trim();
        if (!NusMod.isValidModName(trimmedTag)) {
            throw new ParseException(NusMod.MESSAGE_CONSTRAINTS);
        }
        return new NusMod(trimmedTag);
    }

    /**
     * Parses a {@code String race} into an {@code Race}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code race} is invalid.
     */
    public static Race parseRace(String race) throws ParseException {
        if (race == null) {
            return null;
        }
        String trimmedRace = race.trim();
        if (!Race.isValidRace(trimmedRace)) {
            throw new ParseException(Race.MESSAGE_CONSTRAINTS);
        }
        return new Race(trimmedRace);
    }

    /**
     * Parses a {@code String comms} into an {@code CommunicationChannel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code comms} is invalid.
     */
    public static CommunicationChannel parseComms(String comms) throws ParseException {
        if (comms == null) {
            return null;
        }
        String trimmedComms = comms.trim();
        if (!CommunicationChannel.isValidComms(trimmedComms)) {
            throw new ParseException(CommunicationChannel.MESSAGE_CONSTRAINTS);
        }
        return new CommunicationChannel(comms);
    }

    /**
     * Parses a {@code String duration} into an {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static DateTime parseDateTime(String duration) throws ParseException {
        requireNonNull(duration);

        if (!DateTime.isValidDateTime(duration)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }

        return new DateTime(duration);
    }

    /**
     * Parses a {@code String interval} into a {@code Recurrence}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interval} is invalid.
     */
    public static Recurrence parseRecurrence(String interval) throws ParseException {
        String trimmedInterval = interval.trim();

        if (!Recurrence.isValidInterval(trimmedInterval)) {
            throw new ParseException(Recurrence.MESSAGE_CONSTRAINTS);
        }

        return new Recurrence(interval);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);

        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }

        return new Description(description);
    }
}
