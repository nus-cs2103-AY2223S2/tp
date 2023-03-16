//package seedu.address.testutil;
//
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
//
//import java.util.Set;
//
//import seedu.address.logic.commands.AddEntityCommand;
//import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
//import seedu.address.model.entity.Entity;
//import seedu.address.model.tag.Tag;
//
///**
// * A utility class for Person.
// */
//public class PersonUtil {
//
//    /**
//     * Returns an add command string for adding the {@code person}.
//     */
//    public static String getAddCommand(Entity entity) {
//        return AddEntityCommand.COMMAND_WORD + " " + getPersonDetails(entity);
//    }
//
//    /**
//     * Returns the part of command string for the given {@code person}'s details.
//     */
//    public static String getPersonDetails(Entity entity) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(PREFIX_NAME + entity.getName().fullName + " ");
//        entity.getTags().stream().forEach(
//            s -> sb.append(PREFIX_TAG + s.tagName + " ")
//        );
//        return sb.toString();
//    }
//
//    /**
//     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
//     */
//    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
//        StringBuilder sb = new StringBuilder();
//        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
//        if (descriptor.getTags().isPresent()) {
//            Set<Tag> tags = descriptor.getTags().get();
//            if (tags.isEmpty()) {
//                sb.append(PREFIX_TAG);
//            } else {
//                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
//            }
//        }
//        return sb.toString();
//    }
//}
