package bookopedia.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import bookopedia.commons.core.index.Index;
import bookopedia.commons.util.StringUtil;
import bookopedia.logic.parser.exceptions.ParseException;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.ParcelStatus;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Address;
import bookopedia.model.person.Email;
import bookopedia.model.person.Name;
import bookopedia.model.person.Phone;

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
     * Parses a {@code String parcelName} into a {@code Parcel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code parcelName} is invalid.
     */
    public static Parcel parseParcel(String parcelName) throws ParseException {
        requireNonNull(parcelName);
        String trimmedParcelName = parcelName.trim();
        if (!Parcel.isValidParcelName(trimmedParcelName)) {
            throw new ParseException(Parcel.MESSAGE_CONSTRAINTS);
        }
        return new Parcel(trimmedParcelName);
    }

    /**
     * Parses {@code Collection<String> parcels} into a {@code Set<Parcel>}.
     */
    public static Set<Parcel> parseParcels(Collection<String> parcels) throws ParseException {
        requireNonNull(parcels);
        final Set<Parcel> parcelSet = new HashSet<>();
        for (String parcelName : parcels) {
            parcelSet.add(parseParcel(parcelName));
        }
        return parcelSet;
    }

    /**
     * Parses a {@code String status} into a {@code DeliveryStatus}.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static DeliveryStatus parseDeliveryStatus(String status) throws ParseException {
        requireNonNull(status);
        try {
            return DeliveryStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ParseException(DeliveryStatus.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String status} into a {@code ParcelStatus}.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static ParcelStatus parseParcelStatus(String status) throws ParseException {
        requireNonNull(status);
        try {
            return ParcelStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ParseException(ParcelStatus.MESSAGE_CONSTRAINTS);
        }
    }
}
