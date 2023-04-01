package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.DeleteApplicantCommandParser.HASHCODE_MESSAGE_CONSTRAINTS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.comparator.ListingComparator;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.platform.Platform;
import seedu.address.model.platform.PlatformName;

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
     * Parses a {@code String jobTitle} into a {@code JobTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public static JobTitle parseTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedTitle = jobTitle.trim();
        if (!JobTitle.isValidTitle(trimmedTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedTitle);
    }

    /**
     * Parses a {@code String jobDescription} into a {@code JobDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobDescription} is invalid.
     */
    public static JobDescription parseDescription(String jobDescription) throws ParseException {
        requireNonNull(jobDescription);
        String trimmedDescription = jobDescription.trim();
        if (!JobDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        return new JobDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String applicantName} into a {@code Applicant}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code applicantName} is invalid.
     */
    public static Applicant parseApplicant(String applicantName) throws ParseException {
        requireNonNull(applicantName);
        String trimmedName = applicantName.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Applicant(new Name(trimmedName));
    }

    /**
     * Parses a {@code String platformName} into a {@code Platform}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code platformName} is invalid.
     */
    public static Platform parsePlatform(String platformName) throws ParseException {
        requireNonNull(platformName);
        String trimmedName = platformName.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Platform(new PlatformName(trimmedName));
    }



    /**
     * Parses {@code List<String> allPlatforms} into a {@code ArrayList<Platform>}
     *
     * @throws ParseException if the given {@code allPlatforms} is invalid.
     */
    public static ArrayList<Platform> parsePlatforms(List<String> allPlatforms) throws ParseException {
        requireNonNull(allPlatforms);
        final ArrayList<Platform> platformArrayList = new ArrayList<>();
        for (String platformName : allPlatforms) {
            platformArrayList.add(parsePlatform(platformName));
        }
        return platformArrayList;
    }

    /**
     * Parses {@code List<String> allApplicants} into a {@code ArrayList<Applicant>}
     *
     * @throws ParseException if the given {@code allApplicants} is invalid.
     */
    public static ArrayList<Applicant> parseApplicants(List<String> allApplicants) throws ParseException {
        requireNonNull(allApplicants);
        final ArrayList<Applicant> applicantArrayList = new ArrayList<>();
        for (String applicantName : allApplicants) {
            applicantArrayList.add(parseApplicant(applicantName));
        }
        return applicantArrayList;
    }

    /**
     * Parses a {@code String applicantNameWithId} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code applicantNameWithId} is invalid.
     */
    public static String parseApplicantWithId(String applicantName) throws ParseException {
        requireNonNull(applicantName);
        String trimmedName = applicantName.trim();

        if (trimmedName.length() > 5 && trimmedName.charAt(trimmedName.length() - 5) == '#') {
            if (!trimmedName.substring(trimmedName.length() - 4).matches("\\d{4}")) {
                throw new ParseException(HASHCODE_MESSAGE_CONSTRAINTS);
            }
            if (!Name.isValidName(trimmedName.substring(0, trimmedName.length() - 5))) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            return trimmedName;
        } else {
            if (!Name.isValidName(trimmedName)) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            return trimmedName;
        }
    }

    /**
     * Parses a {@code String field} into a relevant {@code ListingComparator}.
     * @param field input string
     * @return A ListingComparator to be used to sort the listings.
     * @throws ParseException if the given {@code field} is not in the list of accepted fields.
     */
    public static ListingComparator parseFieldToCompare(String field) throws ParseException {
        requireNonNull(field);
        String trimmedAndCapitalisedField = field.trim().toUpperCase();

        try {
            ListingComparator listingComparator = ListingComparator.valueOf(trimmedAndCapitalisedField);
            return listingComparator;
        } catch (IllegalArgumentException e) {
            throw new ParseException(ListingComparator.MESSAGE_CONSTRAINTS);
        }
    }
}
