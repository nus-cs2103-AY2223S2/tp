package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.appointment.AppointmentName;
import seedu.address.model.client.appointment.MeetupDate;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        assert Integer.parseInt(trimmedIndex) > 0;
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
     * @throws ParseException if the given {@code address} is invalid.
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
     * @throws ParseException if the given {@code email} is invalid.
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
     * Parses a {@code String appointmentName} into an {@code AppointmentName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static AppointmentName parseAppointmentName(String appointmentName) throws ParseException {
        requireNonNull(appointmentName);
        String trimmedAppointmentName = appointmentName.trim();
        if (!AppointmentName.isValidName(trimmedAppointmentName)) {
            throw new ParseException(AppointmentName.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentName(trimmedAppointmentName);
    }


    /**
     * Parses a {@code String meetupDate} into an {@code MeetupDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static MeetupDate parseMeetupDate(String meetupDate) throws ParseException {
        requireNonNull(meetupDate);
        String trimmedMeetupDate = meetupDate.trim();
        if (!MeetupDate.isValidDate(trimmedMeetupDate)) {
            throw new ParseException(MeetupDate.MESSAGE_CONSTRAINTS);
        }
        if (!MeetupDate.isFutureDate(trimmedMeetupDate)) {
            throw new ParseException(MeetupDate.MESSAGE_PAST_DATE);
        }
        return new MeetupDate(trimmedMeetupDate);
    }

    /**
     * Parses a {@code String policyName} into a {@code PolicyName}.
     *
     * @param policyName String to be parsed
     * @return PolicyName
     * @throws ParseException if the given {@code policyName} is invalid.
     */
    public static PolicyName parsePolicyName(String policyName) throws ParseException {
        requireNonNull(policyName);
        String trimmedPolicyName = policyName.trim();
        if (!PolicyName.isValidName(trimmedPolicyName)) {
            throw new ParseException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        if (!PolicyName.isValidNameEnum(trimmedPolicyName)) {
            throw new ParseException(PolicyName.MESSAGE_CONSTRAINTS_ENUM);
        }
        return new PolicyName(trimmedPolicyName);
    }

    /**
     * Parses a {@code String customDate} into a {@code CustomDate}.
     *
     * @param customDate
     * @return CustomDate
     * @throws ParseException if the given {@code customDate} is invalid.
     */
    public static CustomDate parseCustomDate(String customDate) throws ParseException {
        requireNonNull(customDate);
        String trimmedCustomDate = customDate.trim();
        if (!CustomDate.isValidDate(trimmedCustomDate)) {
            throw new ParseException(CustomDate.MESSAGE_CONSTRAINTS);
        }
        return new CustomDate(trimmedCustomDate);
    }

    /**
     * Parses a {@code String premium} into a {@code Premium}.
     *
     * @param premium String to be parsed
     * @return Premium
     * @throws ParseException if the given {@code premium} is invalid.
     */
    public static Premium parsePremium(String premium) throws ParseException {
        requireNonNull(premium);
        String trimmedPremium = premium.trim();
        if (!Premium.isValidPremium(trimmedPremium)) {
            throw new ParseException(Premium.MESSAGE_CONSTRAINTS);
        }
        return new Premium(trimmedPremium);
    }

    /**
     * Parses a {@code String frequency} into a {@code Frequency}.
     *
     * @param frequency String to be parsed
     * @return Frequency
     * @throws ParseException if the given {@code frequency} is invalid.
     */
    public static Frequency parseFrequency(String frequency) throws ParseException {
        requireNonNull(frequency);
        String trimmedFrequency = frequency.trim();
        if (!Frequency.isValidFrequency(trimmedFrequency)) {
            throw new ParseException(Frequency.MESSAGE_CONSTRAINTS);
        }
        return new Frequency(trimmedFrequency);
    }
}
