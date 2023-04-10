package seedu.patientist.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.commons.util.StringUtil;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.patient.PatientStatusDetails;
import seedu.patientist.model.person.patient.PatientToDo;
import seedu.patientist.model.tag.PriorityTag;
import seedu.patientist.model.tag.Tag;
import seedu.patientist.model.ward.Ward;

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
     * Parses a {@code String id} into a {@code PatientIdNumber}.
     * Leading and trailing whitespaces will be trimmed.
     * @param id The string to be converted.
     * @return PatientIdNumber.
     *
     * @throws ParseException id the given {@code pid} is invalid.
     */
    public static IdNumber parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedPid = id.trim();
        if (!IdNumber.isValidPid(trimmedPid)) {
            throw new ParseException(IdNumber.MESSAGE_CONSTRAINTS);
        }
        return new IdNumber(trimmedPid);
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
     * Parses a {@code String patientist} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code patientist} is invalid.
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
     * Parses a {@code String status} into a {@code PatientStatusDetails}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static PatientStatusDetails parseDetail(String status) {
        requireNonNull(status);
        String trimmedStatus = status.trim();

        return new PatientStatusDetails(trimmedStatus);
    }

    /**
     * Parses a {@code String todo} into a {@code PatientToDo}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static PatientToDo parseToDo(String toDo) {
        requireNonNull(toDo);
        String trimmedToDo = toDo.trim();

        return new PatientToDo(trimmedToDo);
    }

    /**
     * Parses a {@code String severity} into a {@code SeverityTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static PriorityTag parsePriority(String priority) {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();

        return new PriorityTag(trimmedPriority);
    }

    /**
     * Parses {@code Collection<String> details} into a {@code List<PatientStatusDetail>}.
     */
    public static List<PatientStatusDetails> parseDetails(Collection<String> details) {
        requireNonNull(details);
        final List<PatientStatusDetails> detailsList = new ArrayList<>();
        for (String detail : details) {
            detailsList.add(parseDetail(detail));
        }
        return detailsList;
    }

    /**
     * Parses {@code Collection<String> todo} into a {@code List<PatientToDo>}.
     */
    public static List<PatientToDo> parseToDos(Collection<String> toDos) {
        requireNonNull(toDos);
        final List<PatientToDo> toDoList = new ArrayList<>();
        for (String toDo : toDos) {
            toDoList.add(parseToDo(toDo));
        }
        return toDoList;
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code wardName} is invalid.
     */
    public static Ward parseWard(String wardName) throws ParseException {
        requireNonNull(wardName);
        String trimmedWard = wardName.trim();
        if (!Ward.isValidWardName(trimmedWard)) {
            throw new ParseException(Ward.MESSAGE_CONSTRAINTS);
        }
        return new Ward(trimmedWard);
    }
}
