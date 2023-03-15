package trackr.testutil;

import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.logic.commands.AddOrderCommand;
import trackr.model.order.Order;
import trackr.model.order.OrderDescriptor;
import trackr.model.order.OrderNameContainsKeywordPredicate;

/**
 * utility calss for Order
 */
public class OrderUtil {

    /**
     * returns an AddOrdrCommand string for adding the {@code order}.
     */
    public static String getAddOrderCommand(Order order) {
        return AddOrderCommand.COMMAND_WORD + " " + getOrderDetails(order);
    }

    /**
     * Returns an AddOrderCommand string with COMMAND_WORD_SHORTCUT for adding the {@code task}.
     */
    public static String getAddOrderCommandShortcut(Order task) {
        return AddOrderCommand.COMMAND_WORD_SHORTCUT + " " + getOrderDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code order}'s details.
     */
    public static String getOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ORDERNAME + order.getOrderName().value + " ");
        sb.append(PREFIX_ORDERQUANTITY + order.getOrderQuantity().value + " ");
        sb.append(PREFIX_DEADLINE + order.getOrderDeadline().toJsonString() + " ");
        sb.append(PREFIX_STATUS + order.getOrderStatus().toJsonString() + " ");
        sb.append(PREFIX_NAME + order.getCustomer().getCustomerName().fullName + " ");
        sb.append(PREFIX_PHONE + order.getCustomer().getCustomerPhone().value + " ");
        sb.append(PREFIX_ADDRESS + order.getCustomer().getCustomerAddress().value + " ");
        return sb.toString();
    }

    // Returns the part of command string for the given {@code TaskDescriptor}'s details.
    public static String getTaskDescriptorDetails(OrderDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getOrderName()
                .ifPresent(orderName -> sb.append(PREFIX_ORDERNAME).append(orderName.value).append(" "));
        descriptor.getOrderDeadline()
                .ifPresent(orderDeadline -> sb.append(PREFIX_DEADLINE)
                    .append(orderDeadline.toJsonString()).append(" "));
        descriptor.getOrderStatus()
                .ifPresent(orderStatus -> sb.append(PREFIX_STATUS).append(orderStatus.toJsonString()).append(" "));
        descriptor.getOrderQuantity()
                .ifPresent(orderQuantity -> sb.append(PREFIX_ORDERQUANTITY).append(orderQuantity.value).append(" "));
        descriptor.getCustomerName()
                .ifPresent(customerName -> sb.append(PREFIX_NAME).append(customerName.fullName).append(" "));
        descriptor.getCustomerPhone()
                .ifPresent(customerPhone -> sb.append(PREFIX_PHONE).append(customerPhone.value).append(" "));
        descriptor.getCustomerAddress()
                .ifPresent(customerAddress -> sb.append(PREFIX_ADDRESS).append(customerAddress.value).append(" "));
        return sb.toString();
    }

    // Returns the part of command string for the given {@code TaskContainsKeywordsPredicate}'s details.
    public static String getTaskPredicateDetails(OrderNameContainsKeywordPredicate descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getOrderNameKeywords()
                .ifPresent(orderNameKeywords -> sb.append(PREFIX_ORDERNAME)
                        .append(String.join(" ", orderNameKeywords)).append(" "));
        descriptor.getOrderDeadline()
                .ifPresent(orderDeadline -> sb.append(PREFIX_DEADLINE)
                    .append(orderDeadline.toJsonString()).append(" "));
        descriptor.getOrderStatus()
                .ifPresent(orderStatus -> sb.append(PREFIX_STATUS).append(orderStatus.toJsonString()).append(" "));
        descriptor.getOrderQuantity()
                .ifPresent(orderQuantity -> sb.append(PREFIX_ORDERQUANTITY).append(orderQuantity.value).append(" "));
        descriptor.getCustomerName()
                .ifPresent(customerName -> sb.append(PREFIX_NAME).append(customerName.fullName).append(" "));
        descriptor.getCustomerPhone()
                .ifPresent(customerPhone -> sb.append(PREFIX_PHONE).append(customerPhone.value).append(" "));
        descriptor.getCustomerAddress()
                .ifPresent(customerAddress -> sb.append(PREFIX_ADDRESS).append(customerAddress.value).append(" "));
        return sb.toString();
    }


}
