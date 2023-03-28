package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.tag.MedicalQualificationTag;
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
    public static String getEditCommand(String nric, EditDescriptor descriptor) {
        return EditCommand.COMMAND_WORD + " " + nric
                + " " + getEditDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditDescriptorDetails(EditDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getNric().ifPresent(nric -> sb.append(PREFIX_NRIC).append(nric.value).append(" "));
        descriptor.getBirthDate().ifPresent(date ->
                sb.append(PREFIX_BIRTH_DATE).append(date.birthDate.toString()).append(" "));
        descriptor.getRegion().ifPresent(region -> sb.append(PREFIX_REGION).append(region).append(" "));
        descriptor.getRiskLevel().ifPresent(riskLevel ->
                sb.append(PREFIX_RISK).append(riskLevel).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }

        if (descriptor.getMedicalTags().isPresent()) {
            Set<MedicalQualificationTag> tags = descriptor.getMedicalTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_MEDICAL_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_MEDICAL_TAG).append(s.tagName).append(" ")
                        .append(s.getQualificationLevel()).append(" "));
            }
        }

        if (descriptor.getAvailableDates().isPresent()) {
            Set<AvailableDate> dates = descriptor.getAvailableDates().get();
            if (dates.isEmpty()) {
                sb.append(PREFIX_AVAILABILITY);
            } else {
                dates.forEach(s -> sb.append(PREFIX_AVAILABILITY).append(s.getStartDate().toString()).append(",")
                        .append(s.getEndDate().toString()).append(" "));
            }
        }

        return sb.toString();
    }

    /**
     * Returns a {@code FriendlyLink} with all the typical volunteers, elderly and pairs.
     */
    public static FriendlyLink getTypicalFriendlyLink() {
        return new FriendlyLinkBuilder()
                .withElderly(TypicalElderly.getTypicalElderly())
                .withVolunteers(TypicalVolunteers.getTypicalVolunteers())
                .withPairs(TypicalPairs.getTypicalPairs())
                .build();
    }

    /**
     * Returns a {@code FriendlyLink} with all the typical volunteers and elderly.
     */
    public static FriendlyLink getNoPairsTypicalFriendlyLink() {
        return new FriendlyLinkBuilder()
                .withElderly(TypicalElderly.getTypicalElderly())
                .withVolunteers(TypicalVolunteers.getTypicalVolunteers())
                .build();
    }

    /**
     * Returns a {@code ModelManager} with the typical FriendlyLink.
     */
    public static ModelManager getTypicalModelManager() {
        return new ModelManagerBuilder()
                .withFriendlyLink(getTypicalFriendlyLink())
                .build();
    }

}
