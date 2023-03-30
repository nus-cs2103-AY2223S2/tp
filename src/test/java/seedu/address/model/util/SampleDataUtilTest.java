package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.person.status.LeadStatusName.QUALIFIED;
import static seedu.address.model.person.status.LeadStatusName.UNCONTACTED;
import static seedu.address.model.person.status.LeadStatusName.UNQUALIFIED;
import static seedu.address.model.person.status.LeadStatusName.WORKING;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.TaskList;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Value;
import seedu.address.model.transaction.status.TxnStatus;

public class SampleDataUtilTest {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static final TaskList EMPTY_TASK = new TaskList();
    private static Person[] samplePersons = new Person[] {
        new Person(new Name("Alex Yeoh"),
            new Gender("male"),
            new Phone("87438807"),
            new Email("alexyeoh@example.com"),
            new Company("Pizza House"),
            new Location("Singapore"),
            new Occupation("purchaser"),
            new JobTitle("procurement officer"),
            new Address("Blk 30 Geylang Street 29, #06-40"),
            EMPTY_REMARK,
            getTagSet("friends"),
            EMPTY_TASK,
            new LeadStatus(UNCONTACTED.getLabel())),
        new Person(new Name("Bernice Yu"),
            new Gender("female"),
            new Phone("99272758"),
            new Email("berniceyu@example.com"),
            new Company("Tesleh"),
            new Location("China"),
            new Occupation("engineer"),
            new JobTitle("industrial engineer"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            EMPTY_REMARK,
            getTagSet("colleagues", "friends"),
            EMPTY_TASK,
            new LeadStatus(WORKING.getLabel())),
        new Person(new Name("Charlotte Oliveiro"),
            new Gender("female"),
            new Phone("93210283"),
            new Email("charlotte@example.com"),
            new Company("Mac King"),
            new Location("Singapore"),
            new Occupation("entrepreneur"),
            new JobTitle("CEO"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            EMPTY_REMARK,
            getTagSet("neighbours"),
            EMPTY_TASK,
            new LeadStatus(UNCONTACTED.getLabel())),
        new Person(new Name("David Li"),
            new Gender("male"),
            new Phone("91031282"),
            new Email("lidavid@example.com"),
            new Company("Googol"),
            new Location("Japan"),
            new Occupation("data scientist"),
            new JobTitle("tech lead"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            EMPTY_REMARK,
            getTagSet("family"),
            EMPTY_TASK,
            new LeadStatus(UNQUALIFIED.getLabel())),
        new Person(new Name("Irfan Ibrahim"),
            new Gender("male"),
            new Phone("92492021"),
            new Email("irfan@example.com"),
            new Company("SalesPunch"),
            new Location("Singapore"),
            new Occupation("software developer"),
            new JobTitle("software developer"),
            new Address("Blk 47 Tampines Street 20, #17-35"),
            EMPTY_REMARK,
            getTagSet("classmates"),
            EMPTY_TASK,
            new LeadStatus(QUALIFIED.getLabel())),
        new Person(new Name("Roy Balakrishnan"),
            new Gender("male"),
            new Phone("92624417"),
            new Email("royb@example.com"),
            new Company("NUS"),
            new Location("North Korea"),
            new Occupation("professor"),
            new JobTitle("research instructor"),
            new Address("Blk 45 Aljunied Street 85, #11-31"),
            EMPTY_REMARK,
            getTagSet("colleagues"),
            EMPTY_TASK,
            new LeadStatus(QUALIFIED.getLabel()))
    };

    private static Transaction[] sampleTxns = new Transaction[] {
        new Transaction(new Description("18 AwfullyHot CoffeePots for Singapore National University"),
            new Value("2700"),
            new TxnStatus("Closed"),
            new Owner("Roy Balakrishnan")),
        new Transaction(new Description("3 KoffeeMaster Espresso Machines for SalesPUNCH Inc."),
            new Value("6000"),
            new TxnStatus("Open"),
            new Owner("Irfan Ibrahim")),
        new Transaction(new Description("150kg Grade 4 Busta Robusta Brazilian Coffe Beans for Singapore National "
                + "University"),
            new Value("750"),
            new TxnStatus("Open"),
            new Owner("Roy Balakrishnan")),
        new Transaction(new Description("5 KoffeeGuru Espresso Machines for Mac King"),
            new Value("12000"),
            new TxnStatus("Open"),
            new Owner("Charlotte Oliveiro")),
        new Transaction(new Description("3 FrappeBlaster Machine Rental for Corporate Event at Tesleh"),
            new Value("750"),
            new TxnStatus("Open"),
            new Owner("Bernice Yu"))
    };

    @Test
    void getSampleAddressBook() {
        AddressBook sampleTest = new AddressBook();
        for (Person p : SampleDataUtil.getSamplePersons()) {
            sampleTest.addPerson(p);
        }
        for (Transaction t : SampleDataUtil.getSampleTransactions()) {
            sampleTest.addTransaction(t);
        }
        assertEquals(SampleDataUtil.getSampleAddressBook(), sampleTest);
    }
}

