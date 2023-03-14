package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.entity.person.*;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(1, new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("regular"), getIntegerSet(1, 2)),
            new Customer(2, new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("difficult", "new driver"), getIntegerSet(3)),
            new Customer(3, new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new HashSet<>(), getIntegerSet(4, 5)),
            new Customer(4, new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("regular"), getIntegerSet(6)),
            new Customer(5, new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new HashSet<>(), new HashSet<>()),
        };
    }

    public static Vehicle[] getSampleVehicles() {
        return new Vehicle[] {
            new Vehicle(1, 1, "SKA1234A", "Red", "Toyota Corolla", VehicleType.CAR),
            new Vehicle(2, 1, "SGP5678B", "Blue", "Toyota Prius", VehicleType.CAR),
            new Vehicle(3, 2, "SLK9123C", "Grey", "BMW X4", VehicleType.CAR),
            new Vehicle(4, 3, "SBF4567D", "Grey", "Suzuki SV650X ABS", VehicleType.MOTORBIKE),
            new Vehicle(5, 3, "SGC2345F", "Black and White", "Honda Adv 150", VehicleType.MOTORBIKE),
            new Vehicle(5, 4, "SLM5678K", "Dark Blue", "Hyundai Tucson SUV", VehicleType.CAR),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyShop getSampleShop() {
        Shop sampleSh = new Shop();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleSh.addCustomer(sampleCustomer);
        }
        for (Vehicle sampleVehicle : getSampleVehicles()) {
            sampleSh.addVehicle(sampleVehicle);
        }
        return sampleSh;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a integer set containing the list of integers given.
     */
    public static Set<Integer> getIntegerSet(Integer... integers) {
        return Arrays.stream(integers)
                .map(Integer::new)
                .collect(Collectors.toSet());
    }

}
