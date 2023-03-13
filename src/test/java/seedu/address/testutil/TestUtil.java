package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.util.EditPersonDescriptor;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.tag.Tag;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the volunteer in the {@code model}'s volunteer list.
     */
    public static Index getMidVolunteerIndex(Model model) {
        return Index.fromOneBased(model.getFilteredVolunteerList().size() / 2);
    }

    /**
     * Returns the last index of the volunteer in the {@code model}'s volunteer list.
     */
    public static Index getLastVolunteerIndex(Model model) {
        return Index.fromOneBased(model.getFilteredVolunteerList().size());
    }

    /**
     * Returns the volunteer in the {@code model}'s volunteer list at {@code index}.
     */
    public static Volunteer getVolunteer(Model model, Index index) {
        return model.getFilteredVolunteerList().get(index.getZeroBased());
    }

    /**
     * Returns the middle index of the elderly in the {@code model}'s elderly list.
     */
    public static Index getMidElderlyIndex(Model model) {
        return Index.fromOneBased(model.getFilteredElderlyList().size() / 2);
    }

    /**
     * Returns the last index of the elderly in the {@code model}'s elderly list.
     */
    public static Index getLastElderlyIndex(Model model) {
        return Index.fromOneBased(model.getFilteredElderlyList().size());
    }

    /**
     * Returns the elderly in the {@code model}'s elderly list at {@code index}.
     */
    public static Elderly getElderly(Model model, Index index) {
        return model.getFilteredElderlyList().get(index.getZeroBased());
    }

    /**
     * Returns the middle index of the pair in the {@code model}'s pair list.
     */
    public static Index getMidPairIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPairList().size() / 2);
    }

    /**
     * Returns the last index of the pair in the {@code model}'s pair list.
     */
    public static Index getLastPairIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPairList().size());
    }

    /**
     * Returns the pair in the {@code model}'s pair list at {@code index}.
     */
    public static Pair getPair(Model model, Index index) {
        return model.getFilteredPairList().get(index.getZeroBased());
    }

    /**
     * Returns an edit command string for editing the {@code person}.
     */
    public static String getEditCommand(String nric, EditPersonDescriptor descriptor) {
        return EditCommand.COMMAND_WORD + " " + nric
                + " " + getEditPersonDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getNric().ifPresent(nric -> sb.append(PREFIX_NRIC).append(nric.value).append(" "));
        descriptor.getAge().ifPresent(age -> sb.append(PREFIX_AGE).append(age.value).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns a {@code FriendlyLink} with all the typical volunteers, elderly and pairs.
     */
    public static FriendlyLink getTypicalFriendlyLink() {
        FriendlyLink fl = new FriendlyLink();
        for (Volunteer volunteer : TypicalVolunteers.getTypicalVolunteers()) {
            fl.addVolunteer(volunteer);
        }
        for (Elderly elderly : TypicalElderly.getTypicalElderly()) {
            fl.addElderly(elderly);
        }
        for (Pair pair : TypicalPairs.getTypicalPairs()) {
            fl.addPair(pair);
        }
        return fl;
    }
}
