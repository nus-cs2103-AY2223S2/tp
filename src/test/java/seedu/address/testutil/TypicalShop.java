package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import seedu.address.logic.idgen.IdGenerator;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Person;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.CaseInsensitiveHashMap;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceStatus;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Shop} with sample data.
 * Questions you might have:
 *  1. How is this different from SampleDataUtil?
 *     Ans: None.
 *
 *  2. What is the point of this?
 *     Ans: To create an easily accessible stub to test the commands.
 *
 *  3. Why can't we create PersonBuilder etc. to stimulate the stub?
 *     Ans: Because we need IdGen. If we make IdGen into a stub, the question would be, then do we need to
 *          make shop (model) into a stub (because shop requires idgen)
 *          And also need to ask if the next item above requires a stub or not.
 *
 *  4. Fundamentally, this is not a pure stub!
 *     Ans: Yes, but AB3 doesn't use pure stubs for all cases either. AddCommandTest creates its own model,
 *          but rest don't.
 *
 */
public class TypicalShop {
    private static final IdGenerator idGenerator = new IdGenerator();
    public static Person[] getSamplePersons() {
        return new Person[]{
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
        return new Customer[]{
                new Customer(idGenerator.generateCustomerId(), new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("regular"), getIntegerSet(1, 2), getIntegerSet(1, 2, 3, 4)),
                new Customer(idGenerator.generateCustomerId(), new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("difficult", "new driver"), getIntegerSet(3), getIntegerSet(5, 6)),
                new Customer(idGenerator.generateCustomerId(), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new HashSet<>(), getIntegerSet(4, 5)),
                new Customer(idGenerator.generateCustomerId(), new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("regular"), getIntegerSet(6)),
                new Customer(idGenerator.generateCustomerId(), new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), new HashSet<>(), new HashSet<>(),
                        getIntegerSet(7)),
        };
    }

    public static Technician[] getSampleTechnicians() {
        return new Technician[]{
                new Technician(idGenerator.generateStaffId(), new Name("James Tan"), new Phone("89764362"),
                        new Email("jamestan@example.com"), new Address("Blk 586 Bedok Street 23, #08-46"),
                        getTagSet("big boss"), getIntegerSet(1, 4, 5, 6), getIntegerSet(1, 4)),
                new Technician(idGenerator.generateStaffId(), new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("good technician"), getIntegerSet(3, 7), getIntegerSet(2, 5)),
                new Technician(idGenerator.generateStaffId(), new Name("Loh Jia Yu"), new Phone("968685152"),
                        new Email("lohjy@example.com"), new Address("Blk 73 Hahn Quay Street 67, #04-45"),
                        getTagSet("new technician"), getIntegerSet(3, 6), getIntegerSet(4, 5)),
        };
    }

    public static Appointment[] getSampleAppointments() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        return new Appointment[] {
                new Appointment(idGenerator.generateAppointmentId(), 1, LocalDateTime.parse("10/12/2022 02:00 PM", dtf),
                        getIntegerSet(1)),
                new Appointment(idGenerator.generateAppointmentId(), 1, LocalDateTime.parse("21/12/2022 02:30 PM", dtf),
                        getIntegerSet(2)),
                new Appointment(idGenerator.generateAppointmentId(), 1, LocalDateTime.parse("15/02/2023 03:00 PM", dtf),
                        getIntegerSet(3)),
                new Appointment(idGenerator.generateAppointmentId(), 1, LocalDateTime.parse("01/05/2023 06:00 PM", dtf),
                        getIntegerSet(1, 3)),
                new Appointment(idGenerator.generateAppointmentId(), 2, LocalDateTime.parse("19/06/2022 12:30 PM", dtf),
                        getIntegerSet(2, 3)),
                new Appointment(idGenerator.generateAppointmentId(), 2, LocalDateTime.parse("25/04/2023 11:00 AM", dtf)),
                new Appointment(idGenerator.generateAppointmentId(), 5, LocalDateTime.parse("28/05/2023 05:45 PM", dtf)),
        };
    }

    public static Vehicle[] getSampleVehicles() {
        return new Vehicle[] {
                new Vehicle(idGenerator.generateVehicleId(), 1, "SKA1234A", "Red", "Toyota Corolla",
                        VehicleType.CAR, getIntegerSet(1, 5)),
                new Vehicle(idGenerator.generateVehicleId(), 1, "SGP5678B", "Blue", "Toyota Prius",
                        VehicleType.CAR, getIntegerSet(3)),
                new Vehicle(idGenerator.generateVehicleId(), 2, "SLK9123C", "Grey", "BMW X4",
                        VehicleType.CAR, getIntegerSet(2)),
                new Vehicle(idGenerator.generateVehicleId(), 3, "SBF4567D", "Grey", "Suzuki SV650X ABS",
                        VehicleType.MOTORBIKE, getIntegerSet(4)),
                new Vehicle(idGenerator.generateVehicleId(), 3, "SGC2345F", "Black and White", "Honda Adv 150",
                        VehicleType.MOTORBIKE, getIntegerSet(7)),
                new Vehicle(idGenerator.generateVehicleId(), 4, "SLM5678K", "Dark Blue", "Hyundai Tucson SUV",
                        VehicleType.CAR, getIntegerSet(6)),
        };
    }

    public static Map<String, Integer> getSampleParts() {
        Map<String, Integer> map = new CaseInsensitiveHashMap<>();
        map.put("Frame", 50);
        map.put("Bolt", 1000);
        map.put("Horn", 100);
        map.put("Light", 100);
        map.put("Wheels", 500);
        map.put("Suspension", 100);
        map.put("Gearbox", 100);
        map.put("Headlamp", 20);
        map.put("Steering", 20);
        map.put("Engine", 20);
        map.put("Brake Pads", 50);
        map.put("Car Battery", 50);
        return map;
    }

    // NOTE: Ensure Parts added to Services match with the parts added to getSampleParts*
    public static Service[] getSampleServices() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Map<String, Integer> samplePartsA = new CaseInsensitiveHashMap<>();
        samplePartsA.put("Wheels", 4);
        samplePartsA.put("Suspension", 4);

        Map<String, Integer> samplePartsB = new CaseInsensitiveHashMap<>();
        samplePartsB.put("Gearbox", 1);

        Map<String, Integer> samplePartsC = new CaseInsensitiveHashMap<>();
        samplePartsC.put("Engine", 1);

        Map<String, Integer> samplePartsD = new CaseInsensitiveHashMap<>();
        samplePartsD.put("Car Battery", 1);

        Map<String, Integer> samplePartsE = new CaseInsensitiveHashMap<>();
        samplePartsE.put("Brake Pads", 1);

        return new Service[]{
                new Service(idGenerator.generateServiceId(), 1, LocalDate.parse("02/03/2022", dtf),
                        samplePartsC, "Engine oil leak",
                        LocalDate.parse("10/04/2022", dtf), ServiceStatus.COMPLETE, getIntegerSet(1)),
                new Service(idGenerator.generateServiceId(), 3, LocalDate.parse("15/01/2023", dtf),
                        samplePartsD, "Battery replacement",
                        LocalDate.parse("30/01/2023", dtf), ServiceStatus.CANCELLED),
                new Service(idGenerator.generateServiceId(), 2, LocalDate.parse("15/02/2023", dtf),
                        samplePartsB, "Gearbox dead",
                        LocalDate.parse("05/04/2023", dtf), ServiceStatus.IN_PROGRESS, getIntegerSet(2, 3)),
                new Service(idGenerator.generateServiceId(), 4, LocalDate.parse("02/03/2022", dtf),
                        samplePartsC, "Engine oil leak",
                        LocalDate.parse("10/04/2022", dtf), ServiceStatus.ON_HOLD, getIntegerSet(1)),
                new Service(idGenerator.generateServiceId(), 1, LocalDate.parse("16/03/2023", dtf),
                        samplePartsA, "Wheels and suspension got rekt",
                        LocalDate.parse("28/03/2023", dtf), ServiceStatus.IN_PROGRESS, getIntegerSet(1)),
                new Service(idGenerator.generateServiceId(), 6, LocalDate.parse("16/03/2023", dtf),
                        samplePartsE, "Brake pads worn out",
                        LocalDate.parse("02/04/2023", dtf), ServiceStatus.TO_REPAIR, getIntegerSet(1, 3)),
                new Service(idGenerator.generateServiceId(), 5, LocalDate.parse("16/03/2023", dtf),
                        new CaseInsensitiveHashMap<>(), "Just a simple inspection",
                        LocalDate.parse("18/03/2023", dtf), ServiceStatus.TO_REPAIR, getIntegerSet(2)),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyShop getTypicalShop() {
        Shop sampleSh = new Shop();
        sampleSh.initializeData(
                idGenerator,
                FXCollections.observableArrayList(getSampleCustomers()),
                FXCollections.observableArrayList(getSampleVehicles()),
                FXCollections.observableMap(getSampleParts()),
                FXCollections.observableArrayList(getSampleServices()),
                FXCollections.observableArrayList(getSampleTechnicians()),
                FXCollections.observableArrayList(getSampleAppointments()));
        return sampleSh;
    }

    public static Model getTypicalModel() {
        return new ModelManager(getSampleAddressBook(), new UserPrefs(), getTypicalShop());
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
                .collect(Collectors.toSet());
    }

}
