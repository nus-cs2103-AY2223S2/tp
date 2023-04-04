package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Defines the functional interface that the argument parsers should follow.
     *
     * @param <R> The return type.
     */
    @FunctionalInterface
    public interface ArgumentParser<String, R> {
        R apply(String t) throws ParseException;
    }

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex Index starting from 1 in string form.
     * @return Index starting from 1
     * @throws ParseException If the specified index is invalid (not non-zero unsigned integer).
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
     * @param name Name of the person.
     * @return {@code Name} object.
     * @throws ParseException If the given {@code name} is invalid.
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
     * @param phone Phone number of the person.
     * @return {@code Phone} object.
     * @throws ParseException If the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
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
     * @param address Address of the person.
     * @return {@code Address} object.
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
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
     * @param email Email of the person.
     * @return {@code Email} object.
     * @throws ParseException If the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String nric} into an {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param nric Nric of the person.
     * @return {@code Nric} object.
     * @throws ParseException If the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(nric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
    }

    /**
     * Parses a {@code String birthDate} into an {@code BirthDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param birthDate BirthDate of the person
     * @return {@code BirthDate} object.
     * @throws ParseException If the given {@code birthDate} is invalid.
     */
    public static BirthDate parseBirthDate(String birthDate) throws ParseException {
        requireNonNull(birthDate);
        String trimmedBod = birthDate.trim();
        if (!BirthDate.isValidBirthDate(trimmedBod)) {
            throw new ParseException(BirthDate.MESSAGE_CONSTRAINTS);
        }
        return new BirthDate(trimmedBod);
    }

    /**
     * Parses a {@code String region} into an {@code Region}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param region Region of the person.
     * @return {@code Region} object.
     * @throws ParseException If the given {@code region} is invalid.
     */
    public static Region parseRegion(String region) throws ParseException {
        requireNonNull(region);
        if (!Region.isValidRegion(region)) {
            throw new ParseException(Region.MESSAGE_CONSTRAINTS);
        }
        return new Region(region);
    }

    /**
     * Parses a {@code String risk} into an {@code Risk}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param riskLevel Risk level of the elderly.
     * @return {@code RiskLevel} object.
     * @throws ParseException If the given {@code riskLevel} is invalid.
     */
    public static RiskLevel parseRiskLevel(String riskLevel) throws ParseException {
        requireNonNull(riskLevel);
        if (!RiskLevel.isValidRisk(riskLevel)) {
            throw new ParseException(RiskLevel.MESSAGE_CONSTRAINTS);
        }
        return new RiskLevel(riskLevel);
    }

    /**
     * Parses a {@code String datePair} into a {@code AvailableDate}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param datePair Available date of the person.
     * @return {@code AvailableDate} object.
     * @throws ParseException If the given {@code datePair} is invalid.
     */
    public static AvailableDate parseDateRange(String datePair) throws ParseException {
        requireNonNull(datePair);

        String[] dates = datePair.split(",");

        if (dates.length != 2) {
            throw new ParseException(AvailableDate.INVALID_NUMBER_OF_DATES);
        }
        String startDate = dates[0];
        String endDate = dates[1];

        requireNonNull(startDate, endDate);
        String trimmedStartDate = startDate.trim();
        String trimmedEndDate = endDate.trim();

        try {
            return new AvailableDate(trimmedStartDate, trimmedEndDate);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Parses {@code Collection<String> datePairs} into a {@code Set<AvailableDate>}.
     *
     * @param datePairs Available dates of the person.
     * @return A set of available dates.
     * @throws ParseException If any of the date pairs are invalid.
     */
    public static Set<AvailableDate> parseDateRanges(Collection<String> datePairs) throws ParseException {
        requireNonNull(datePairs);
        final Set<AvailableDate> datesSet = new HashSet<>();
        for (String startEndDate : datePairs) {
            datesSet.add(parseDateRange(startEndDate));
        }
        return datesSet;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param tag Tag of the person.
     * @return {@code Tag} object.
     * @throws ParseException If the given {@code tag} is invalid.
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
     * Parses a {@code String medicalTag} into a {@code MedicalQualificationTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param medicalTag Medical tag of the volunteer.
     * @return {@code MedicalQualificationTag} object.
     * @throws ParseException If the given {@code medicalTag} is invalid.
     */
    public static MedicalQualificationTag parseMedicalTag(String medicalTag) throws ParseException {
        requireNonNull(medicalTag);
        String skillset;
        String level;
        try {
            String[] trimmedList = medicalTag.trim().split(",");
            skillset = trimmedList[0].trim();
            level = trimmedList[1].trim();
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParseException(MedicalQualificationTag.MESSAGE_CONSTRAINTS);
        }
        if (!MedicalQualificationTag.isValidQualification(level)) {
            throw new ParseException(MedicalQualificationTag.MESSAGE_CONSTRAINTS_SKILL);
        }
        return new MedicalQualificationTag(skillset, level);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     *
     * @param tags Tags of the person.
     * @return A set of tags.
     * @throws ParseException If any of the tags are invalid.
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
     * Parses {@code Collection<String> medicalTags} into a {@code Set<MedicalQualificationTag>}.
     *
     * @param medicalTags Medical tags of the elderly.
     * @return A set of medical tags.
     * @throws ParseException If any of the medical tags are invalid.
     */
    public static Set<MedicalQualificationTag> parseMedicalTags(Collection<String> medicalTags)
            throws ParseException {
        requireNonNull(medicalTags);
        final Set<MedicalQualificationTag> tagSet = new HashSet<>();
        for (String tagName: medicalTags) {
            tagSet.add(parseMedicalTag(tagName));
        }
        return tagSet;
    }
}
