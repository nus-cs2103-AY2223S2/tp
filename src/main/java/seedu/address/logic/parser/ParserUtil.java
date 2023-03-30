package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.LastFedDateTime;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.Temperature;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String lastFedDate} into a {@code LastFedDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lastFedDate} is invalid.
     */
    public static LastFedDateTime parseLastFedDate(String lastFedDate) throws ParseException {
        requireNonNull(lastFedDate);
        String trimmedLastFedDate = lastFedDate.trim();
        System.out.println(trimmedLastFedDate);
        if (!LastFedDateTime.isValidLastFedDateTime(trimmedLastFedDate)) {
            throw new ParseException(LastFedDateTime.MESSAGE_CONSTRAINTS);
        }
        return new LastFedDateTime(trimmedLastFedDate);
    }

    /**
     * Parses a {@code String feedingInterval} into an {@code FeedingInterval}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code feedingInterval} is invalid.
     */
    public static FeedingInterval parseFeedingInterval(String feedingInterval) throws ParseException {
        requireNonNull(feedingInterval);
        String trimmedFeedingInterval = feedingInterval.trim();
        if (!FeedingInterval.isValidFeedingInterval(trimmedFeedingInterval)) {
            throw new ParseException(FeedingInterval.MESSAGE_CONSTRAINTS);
        }
        return new FeedingInterval(trimmedFeedingInterval);
    }

    /**
     * Parses a {@code String species} into an {@code Species}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code species} is invalid.
     */
    public static Species parseSpecies(String species) throws ParseException {
        requireNonNull(species);
        String trimmedSpecies = species.trim();
        if (!Species.isValidSpecies(trimmedSpecies)) {
            throw new ParseException(Species.MESSAGE_CONSTRAINTS);
        }
        return new Species(trimmedSpecies);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
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
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String} into an {@code Priority}.
     *
     * @throws ParseException If the given {@code description} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        if (!Priority.isValidPriority(priority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(priority);
    }

    /**
     * Parses a {@code String} into an {@code TankName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code fullTankName} is invalid.
     */
    public static TankName parseTankName(String fullTankName) throws ParseException {
        requireNonNull(fullTankName);
        String trimmedDescription = fullTankName.trim();
        if (!Description.isValidDescription(fullTankName)) {
            throw new ParseException(TankName.MESSAGE_CONSTRAINTS);
        }
        return new TankName(trimmedDescription);
    }

    /**
     * Parses a {@code String} into an {@code Tank}.
     * Leading and trailing whitespaces will be trimmed.
     *
     *
     * @throws ParseException If the given {@code tank} is invalid.
     */
    public static Tank parseTankForEditCommand(String tank) throws ParseException {
        requireNonNull(tank);
        String trimmedTank = tank.trim();
        if (!TankName.isValidTankName(trimmedTank)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS
            );
        }
        Tank retTank = new Tank(new TankName(trimmedTank), new AddressBook(), new UniqueIndividualReadingLevels());
        return retTank;
    }

    /**
     * Parses a {@code String} into an {@code Index}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Index parseTank(String strTankIndex) {
        requireNonNull(strTankIndex);
        String trimmedStrTankIndex = strTankIndex.trim();
        int index = Integer.valueOf(trimmedStrTankIndex);
        Index newTankIndex = Index.fromOneBased(index);
        return newTankIndex;
        //        if (!TankName.isValidTankName(trimmedTank)) {
        //            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        //        }
        //        Tank retTank = new Tank(new TankName(trimmedTank), new AddressBook(),
        //        new UniqueIndividualReadingLevels());
        //        return retTank;
    }

    /**
     * Parses a {@code String} into an {@code AmmoniaLevel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code ammoniaLevelValue} or {@code date} is invalid.
     */
    public static AmmoniaLevel parseAmmoniaLevel(String ammoniaLevelValue, String date) throws ParseException {
        requireNonNull(ammoniaLevelValue);
        String trimmedValue = ammoniaLevelValue.trim();
        if (!AmmoniaLevel.isValidAmmoniaLevel(trimmedValue, date)) {
            throw new ParseException(AmmoniaLevel.MESSAGE_CONSTRAINTS);
        }
        return new AmmoniaLevel(trimmedValue, date, null);
    }

    /**
     * Parses a {@code String} into an {@code PH}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code pHLevelValue} or {@code date} is invalid.
     */
    public static PH parsePH(String pHLevelValue, String date) throws ParseException {
        requireNonNull(pHLevelValue);
        String trimmedValue = pHLevelValue.trim();
        if (!PH.isValidPH(trimmedValue, date)) {
            throw new ParseException(PH.MESSAGE_CONSTRAINTS);
        }
        return new PH(trimmedValue, date, null);
    }

    /**
     * Parses a {@code String} into an {@code Temperature}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code tempLevelValue} or {@code date} is invalid.
     */
    public static Temperature parseTemperature(String tempLevelValue, String date) throws ParseException {
        requireNonNull(tempLevelValue);
        String trimmedValue = tempLevelValue.trim();
        if (!Temperature.isValidTemperature(trimmedValue, date)) {
            throw new ParseException(Temperature.MESSAGE_CONSTRAINTS);
        }
        return new Temperature(trimmedValue, date, null);
    }
}
