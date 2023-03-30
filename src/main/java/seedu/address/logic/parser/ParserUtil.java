package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.ResumeLink;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.ProgrammingLanguage;
import seedu.address.model.person.Qualification;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Reflection;
import seedu.address.model.person.Review;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.NoteContent;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INTERVAL = "The start date must be before or equal to the end date.";

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
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedName = companyName.trim();
        if (!CompanyName.isValidCompanyName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
    }

    /**
     * Parses a {@code String jobTitle} into a {@code JobTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public static JobTitle parseJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();
        if (!JobTitle.isValidJobTitle(trimmedJobTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedJobTitle);
    }

    /**
     * Parses a {@code String Review} into a {@code Review}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code review} is invalid.
     */
    public static Review parseReview(String review) throws ParseException {
        requireNonNull(review);
        String trimmedReview = review.trim();
        if (!Review.isValidReview(trimmedReview)) {
            throw new ParseException(Review.MESSAGE_CONSTRAINTS);
        }
        return new Review(trimmedReview);
    }

    /**
     * Parses {@code Collection<String> reviews} into a {@code Set<Review>}.
     */
    public static Set<Review> parseReviews(Collection<String> reviews) throws ParseException {
        requireNonNull(reviews);
        final Set<Review> reviewList = new HashSet<>();
        for (String review : reviews) {
            reviewList.add(parseReview(review));
        }
        return reviewList;
    }

    /**
     * Parses a {@code String Programming Language} into a {@code Programming Language}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code programmingLanguage} is invalid.
     */
    public static ProgrammingLanguage parseProgrammingLanguage(String programmingLanguage) throws ParseException {
        requireNonNull(programmingLanguage);
        String trimmedProgrammingLanguage = programmingLanguage.trim();
        if (!ProgrammingLanguage.isValidProgrammingLanguage(trimmedProgrammingLanguage)) {
            throw new ParseException(ProgrammingLanguage.MESSAGE_CONSTRAINTS);
        }
        return new ProgrammingLanguage(trimmedProgrammingLanguage);
    }

    /**
     * Parses {@code Collection<String> programming languages} into a {@code Set<ProgrammingLanguage>}.
     */
    public static Set<ProgrammingLanguage> parseProgrammingLanguages(
                                                    Collection<String> programmingLanguages) throws ParseException {
        requireNonNull(programmingLanguages);
        final Set<ProgrammingLanguage> programmingLanguageList = new HashSet<>();
        for (String programmingLanguage : programmingLanguages) {
            programmingLanguageList.add(parseProgrammingLanguage(programmingLanguage));
        }
        return programmingLanguageList;
    }

    /**
     * Parses a {@code String Qualification} into a {@code Qualification}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code qualification} is invalid.
     */
    public static Qualification parseQualification(String qualification) throws ParseException {
        requireNonNull(qualification);
        String trimmedQualification = qualification.trim();
        if (!Qualification.isValidQualification(trimmedQualification)) {
            throw new ParseException(Qualification.MESSAGE_CONSTRAINTS);
        }
        return new Qualification(trimmedQualification);
    }

    /**
     * Parses {@code Collection<String> qualifications} into a {@code Set<Qualification>}.
     */
    public static Set<Qualification> parseQualifications(Collection<String> qualifications) throws ParseException {
        requireNonNull(qualifications);
        final Set<Qualification> qualificationList = new HashSet<>();
        for (String qualification : qualifications) {
            qualificationList.add(parseQualification(qualification));
        }
        return qualificationList;
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        if (location == null | location == "") {
            return new Location(null);
        }
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String salary} into a {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        if (salary == null) {
            return new Salary(null);
        }
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses a {@code String Note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    /**
     * Parses {@code Collection<String> notes} into a {@code Set<Note>}.
     */
    public static Set<Note> parseNotes(Collection<String> notes) throws ParseException {
        requireNonNull(notes);
        final Set<Note> noteList = new HashSet<>();
        for (String note : notes) {
            noteList.add(parseNote(note));
        }
        return noteList;
    }

    /**
     * Parses a {@code String rating} into a {@code Rating}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rating} is invalid.
     */
    public static Rating parseRating(String rating) throws ParseException {
        if (rating == null) {
            return new Rating(null);
        }
        String trimmedRating = rating.trim();
        if (!Rating.isValidRating(trimmedRating)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }
        return new Rating(trimmedRating);
    }

    /**
     * Parses a {@code String Reflection} into a {@code Reflection}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reflection} is invalid.
     */
    public static Reflection parseReflection(String reflection) throws ParseException {
        requireNonNull(reflection);
        String trimmedReflection = reflection.trim();
        if (!Reflection.isValidReflection(trimmedReflection)) {
            throw new ParseException(Reflection.MESSAGE_CONSTRAINTS);
        }
        return new Reflection(trimmedReflection);
    }

    /**
     * Parses {@code Collection<String> reflections} into a {@code Set<Reflection>}.
     */
    public static Set<Reflection> parseReflections(Collection<String> reflections) throws ParseException {
        requireNonNull(reflections);
        final Set<Reflection> reflectionList = new HashSet<>();
        for (String reflection : reflections) {
            reflectionList.add(parseReflection(reflection));
        }
        return reflectionList;
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
     * Parses a {@code String interviewDate} into an {@code InterviewDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewDate} is invalid.
     */
    public static InterviewDate parseInterviewDate(String interviewDate) throws ParseException {
        requireNonNull(interviewDate);
        String trimmedInterviewDate = interviewDate.trim();
        if (!InterviewDate.isValidInterviewDate(trimmedInterviewDate)) {
            throw new ParseException(InterviewDate.MESSAGE_CONSTRAINTS);
        }
        return new InterviewDate(interviewDate);
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
     * Parses a {@code String status} into a {@code InternshipStatus}.
     * Leading and trailing whitespaces will be trimmed. It's case-insensitive
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static InternshipStatus parseInternshipStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim().toUpperCase();
        if (!InternshipStatus.isValidStatus(trimmedStatus)) {
            throw new ParseException(InternshipStatus.MESSAGE_CONSTRAINTS);
        }
        return InternshipStatus.valueOf(trimmedStatus);
    }

    /**
     * Parses a {@code String deadline} into a {@code ApplicationDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static ApplicationDeadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);

        String trimmedDeadline = deadline.trim();
        LocalDate formattedDate = LocalDate.parse(trimmedDeadline);

        if (!ApplicationDeadline.isValidDate(formattedDate)) {
            throw new ParseException(ApplicationDeadline.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationDeadline(formattedDate);
    }

    /**
     * Parses a {@code String content} into a {@code NoteContent}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code content} is invalid.
     */
    public static NoteContent parseContent(String content) throws ParseException {
        requireNonNull(content);

        String trimmedContent = content.trim();

        if (!NoteContent.isValidContent(content)) {
            throw new ParseException(NoteContent.MESSAGE_CONSTRAINTS);
        }
        return new NoteContent(trimmedContent);
    }

    /**
     * Parses a {@code String status} into a {@code ResumeLink}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static ResumeLink parseResumeLink(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!ResumeLink.isValidResumeLink(trimmedStatus)) {
            throw new ParseException(ResumeLink.MESSAGE_CONSTRAINTS);
        }
        return new ResumeLink(trimmedStatus);
    }

    /**
     * Parses a {@code String status} into a {@code CoverLetterLink}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static CoverLetterLink parseCoverLetterLink(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!CoverLetterLink.isValidCoverLetterLink(trimmedStatus)) {
            throw new ParseException(CoverLetterLink.MESSAGE_CONSTRAINTS);
        }
        return new CoverLetterLink(trimmedStatus);
    }
}
