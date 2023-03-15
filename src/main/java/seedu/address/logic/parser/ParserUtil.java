package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.socialmedia.Instagram;
import seedu.address.model.socialmedia.SocialMedia;
import seedu.address.model.socialmedia.Telegram;
import seedu.address.model.socialmedia.WhatsApp;
import seedu.address.model.tag.Module;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
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
     * @throws ParseException
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
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
     * Parses a {@code String module} into a {@code module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code module} is invalid.
     */
    public static Module parseModule(String module) throws ParseException {
        requireNonNull(module);
        String trimmedModule = module.trim();
        if (!Module.isValidTagName(trimmedModule)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        return new Module(trimmedModule);
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
     * Parses {@code Collection<String> modules} into a {@code Set<Module>}.
     */
    public static Set<Module> parseModules(Collection<String> modules) throws ParseException {
        requireNonNull(modules);
        final Set<Module> moduleSet = new HashSet<>();
        for (String moduleName : modules) {
            moduleSet.add(parseModule(moduleName));
        }
        return moduleSet;
    }

    private static Instagram parseInstagram(String instagram) {
        if (instagram == null || instagram.isEmpty()) {
            return null;
        }
        return Instagram.of(instagram);
    }

    private static Telegram parseTelegram(String telegram) {
        if (telegram == null || telegram.isEmpty()) {
            return null;
        }
        return Telegram.of(telegram);
    }

    private static WhatsApp parseWhatsApp(String whatsApp) {
        if (whatsApp == null || whatsApp.isEmpty()) {
            return null;
        }
        return WhatsApp.of(whatsApp);
    }

    /**
     * Parses a {@code String socialMedia} into an {@code SocialMedia}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static SocialMedia parseSocialMedia(String socialMediaArgs) {
        if (socialMediaArgs == null || socialMediaArgs.isBlank()) {
            return null;
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(" " + socialMediaArgs.trim(),
                PREFIX_SOCMED_INSTAGRAM, PREFIX_SOCMED_TELEGRAM, PREFIX_SOCMED_WHATSAPP);

        return SocialMedia.create()
            .withInstagram(ParserUtil.parseInstagram(argMultimap.getValue(PREFIX_SOCMED_INSTAGRAM).orElse("")))
            .withTelegram(ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_SOCMED_TELEGRAM).orElse("")))
            .withWhatsapp(ParserUtil.parseWhatsApp(argMultimap.getValue(PREFIX_SOCMED_WHATSAPP).orElse("")));
    }
}
