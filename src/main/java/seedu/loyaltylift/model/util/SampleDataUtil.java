package seedu.loyaltylift.model.util;

import java.time.LocalDate;
import java.util.List;

import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.ReadOnlyAddressBook;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.order.CreatedDate;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.order.Status;
import seedu.loyaltylift.model.order.StatusUpdate;
import seedu.loyaltylift.model.order.StatusValue;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(CustomerType.INDIVIDUAL, new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40")),
            new Customer(CustomerType.INDIVIDUAL, new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Customer(CustomerType.INDIVIDUAL, new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
            new Customer(CustomerType.INDIVIDUAL, new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
            new Customer(CustomerType.INDIVIDUAL, new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35")),
            new Customer(CustomerType.INDIVIDUAL, new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        return sampleAb;
    }

    /**
     * Returns a Quantity from a given string.
     */
    public static Quantity getQuantity(String quantity) {
        return new Quantity(Integer.parseInt(quantity));
    }

    /**
     * Returns a new Status which StatusValue is Pending.
     */
    public static Status getInitialStatus(String date) {
        LocalDate statusDate = LocalDate.parse(date, StatusUpdate.DATE_FORMATTER);
        return new Status(List.of(
                new StatusUpdate(StatusValue.PENDING, statusDate)
        ));
    }

    /**
     * Returns a new Status with StatueValue one ahead of the given Status.
     */
    public static Status getNextStatus(Status status, String date) {
        LocalDate statusDate = LocalDate.parse(date, StatusUpdate.DATE_FORMATTER);
        return status.newStatusWithNewUpdate(statusDate);
    }

    /**
     * Returns a new Status without the latest StatusUpdate.
     */
    public static Status getPrevStatus(Status status) {
        return status.newStatusWithRemoveLatest();
    }

    /**
     * Returns a new Status with StatueValue one ahead of the given Status.
     */
    public static Status getCancelledStatus(Status status, String date) {
        LocalDate statusDate = LocalDate.parse(date, StatusUpdate.DATE_FORMATTER);
        return status.newStatusForCancelledOrder(statusDate);
    }

    /**
     * Returns a CreatedDate from a given string.
     */
    public static CreatedDate getCreatedDate(String createdDate) {
        return new CreatedDate(LocalDate.parse(createdDate, CreatedDate.DATE_FORMATTER));
    }

}
