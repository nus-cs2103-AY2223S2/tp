package arb.testutil;

import static arb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PHONE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import arb.logic.commands.client.AddClientCommand;
import arb.logic.commands.client.EditClientCommand.EditClientDescriptor;
import arb.logic.commands.client.FindClientCommand;
import arb.model.client.Client;
import arb.model.tag.Tag;

/**
 * A utility class for Client.
 */
public class ClientUtil {

    /**
     * Returns an add client command string for adding the {@code client}.
     */
    public static String getAddClientCommand(Client client, String commandWord) {
        assert AddClientCommand.getCommandWords().contains(commandWord);
        return commandWord + " " + getClientDetails(client);
    }

    /**
     * Returns a find client command string to find a client.
     */
    public static String getFindClientCommand(List<String> tags, List<String> names, String commandWord) {
        assert FindClientCommand.getCommandWords().contains(commandWord);
        StringBuilder sb = new StringBuilder();
        sb.append(commandWord + " ");
        tags.stream().forEach(
            t -> sb.append(PREFIX_TAG + t + " ")
        );
        names.stream().forEach(
            n -> sb.append(PREFIX_NAME + n + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().fullName + " ");
        sb.append(PREFIX_PHONE + client.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().value + " ");
        client.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(EditClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE
                + phone.map(p -> p.toString()).orElse("") + " "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL
                + email.map(e -> e.toString()).orElse("") + " "));
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
}
