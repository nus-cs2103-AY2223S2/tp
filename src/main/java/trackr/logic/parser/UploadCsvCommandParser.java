package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_CSV_FILE;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_COST;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_PRICE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.logic.commands.UploadCsvCommand;
import trackr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UploadCsvCommand object.
 */
public class UploadCsvCommandParser implements Parser<UploadCsvCommand> {
    private List<String> listOfCommands = new ArrayList<String>();
    /**
     * Parses the given {@code String} of arguments in the context of the UploadCsvCommand
     * and returns an UploadCsvCommand object for execution.
     */
    public UploadCsvCommand parse(String args) throws ParseException {
        String[] raw = args.split(",");
        String[] components = Arrays.copyOfRange(raw, 1, raw.length);
        for (int i = 0; i < components.length; i++) {
            if (components[i].equals("Orders")) {
                listOfCommands.addAll(parseOrders(Arrays.copyOfRange(components, i + 1, components.length)));
            } else if (components[i].equals("Tasks")) {
                listOfCommands.addAll(parseTasks(Arrays.copyOfRange(components, i + 1, components.length)));
            } else if (components[i].equals("Suppliers")) {
                listOfCommands.addAll(parseSuppliers(Arrays.copyOfRange(components, i + 1, components.length)));
            } else if (components[i].equals("MenuItems")) {
                listOfCommands.addAll(parseItems(Arrays.copyOfRange(components, i + 1, components.length)));
            }
        }
        return new UploadCsvCommand(listOfCommands);
    }

    private List<String> parseOrders(String[] components) throws ParseException {
        List<String> orderCommands = new ArrayList<String>();
        List<String> pattern = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            switch(components[i]) {
            case "OrderName":
                pattern.add(PREFIX_ORDERNAME.getPrefix());
                break;
            case "Quantity":
                pattern.add(PREFIX_ORDERQUANTITY.getPrefix());
                break;
            case "Deadline":
                pattern.add(PREFIX_DEADLINE.getPrefix());
                break;
            case "Status":
                pattern.add(PREFIX_STATUS.getPrefix());
                break;
            case "CustomerName":
                pattern.add(PREFIX_NAME.getPrefix());
                break;
            case "CustomerPhone":
                pattern.add(PREFIX_PHONE.getPrefix());
                break;
            case "CustomerAddress":
                pattern.add(PREFIX_ADDRESS.getPrefix());
                break;
            default:
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }

        String orderCommand = "add_order ";
        for (int i = 7; i < components.length; i++) {
            if (components[i] == null || components[i].equals("Suppliers")
                || components[i].equals("Tasks") || components[i].equals("MenuItems")) {
                break;
            } else if (i % 7 == 6) {
                if (!components[i].equals("-")) {
                    orderCommand = orderCommand + pattern.get(i % 7) + components[i];
                }
                orderCommands.add(orderCommand);
                orderCommand = "add_order ";
            } else if (!components[i].equals("-")) {
                orderCommand = orderCommand + pattern.get(i % 7) + components[i] + " ";
            } else {
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }
        return orderCommands;
    }

    private List<String> parseTasks(String[] components) throws ParseException {
        List<String> taskCommands = new ArrayList<String>();
        List<String> pattern = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            switch(components[i]) {
            case "TaskName":
                pattern.add(PREFIX_NAME.getPrefix());
                break;
            case "Deadline":
                pattern.add(PREFIX_DEADLINE.getPrefix());
                break;
            case "Status":
                pattern.add(PREFIX_STATUS.getPrefix());
                break;
            default:
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }

        String taskCommand = "add_task ";
        for (int i = 3; i < components.length; i++) {
            if (components[i] == null || components[i].equals("Suppliers")
                || components[i].equals("Orders") || components[i].equals("MenuItems")) {
                break;
            } else if (i % 3 == 2) {
                if (!components[i].equals("-")) {
                    taskCommand = taskCommand + pattern.get(i % 3) + components[i];
                }
                taskCommands.add(taskCommand);
                taskCommand = "add_task ";
            } else if (!components[i].equals("-")) {
                taskCommand = taskCommand + pattern.get(i % 3) + components[i] + " ";
            } else {
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }
        return taskCommands;
    }

    private List<String> parseSuppliers(String[] components) throws ParseException {
        List<String> supplierCommands = new ArrayList<String>();
        List<String> pattern = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            switch(components[i]) {
            case "Name":
                pattern.add(PREFIX_NAME.getPrefix());
                break;
            case "Phone":
                pattern.add(PREFIX_PHONE.getPrefix());
                break;
            case "Email":
                pattern.add(PREFIX_EMAIL.getPrefix());
                break;
            case "Address":
                pattern.add(PREFIX_ADDRESS.getPrefix());
                break;
            case "Tag":
                pattern.add(PREFIX_TAG.getPrefix());
                break;
            default:
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }

        String supplierCommand = "add_supplier ";
        for (int i = 5; i < components.length; i++) {
            if (components[i] == null || components[i].equals("Tasks")
                || components[i].equals("Orders") || components[i].equals("MenuItems")) {
                break;
            } else if (i % 5 == 4) {
                if (!components[i].equals("-")) {
                    supplierCommand = supplierCommand + pattern.get(i % 5) + components[i];
                }
                supplierCommands.add(supplierCommand);
                supplierCommand = "add_supplier ";
            } else if (!components[i].equals("-")) {
                supplierCommand = supplierCommand + pattern.get(i % 5) + components[i] + " ";
            } else {
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }
        return supplierCommands;
    }

    private List<String> parseItems(String[] components) throws ParseException {
        List<String> menuItemsCommands = new ArrayList<String>();
        List<String> pattern = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            switch(components[i]) {
            case "ItemName":
                pattern.add(PREFIX_NAME.getPrefix());
                break;
            case "Cost":
                pattern.add(PREFIX_COST.getPrefix());
                break;
            case "Price":
                pattern.add(PREFIX_PRICE.getPrefix());
                break;
            default:
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }

        String menuItemCommand = "add_item ";
        for (int i = 3; i < components.length; i++) {
            if (components[i] == null || components[i].equals("Tasks")
                || components[i].equals("Orders") || components[i].equals("Suppliers")) {
                break;
            } else if (i % 3 == 2) {
                if (!components[i].equals("-")) {
                    menuItemCommand = menuItemCommand + pattern.get(i % 3) + components[i];
                }
                menuItemsCommands.add(menuItemCommand);
                menuItemCommand = "add_item ";
            } else if (!components[i].equals("-")) {
                menuItemCommand = menuItemCommand + pattern.get(i % 3) + components[i] + " ";
            } else {
                throw new ParseException(MESSAGE_INVALID_CSV_FILE);
            }
        }
        return menuItemsCommands;
    }
}
