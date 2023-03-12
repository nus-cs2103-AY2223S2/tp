package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ApplicationParserUtil {

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
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedName = role.trim();
        if (!Role.isValidRole(trimmedName)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedName);
    }

    /**
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedCompanyName = companyName.trim();
        if (!CompanyName.isValidName(trimmedCompanyName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(companyName);
    }

    /**
     * Parses a {@code String status} into an {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(status)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(status);
    }

    /**
     * Parses a {@code String companyEmail} into an {@code CompanyEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyEmail} is invalid.
     */
    public static CompanyEmail parseCompanyEmail(String companyEmail) throws ParseException {
        requireNonNull(companyEmail);
        String trimmedCompanyEmail = companyEmail.trim();
        if (!CompanyEmail.isValidEmail(trimmedCompanyEmail)) {
            throw new ParseException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        return new CompanyEmail(trimmedCompanyEmail);
    }

}
