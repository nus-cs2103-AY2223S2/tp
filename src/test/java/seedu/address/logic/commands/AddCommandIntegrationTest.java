//package seedu.address.logic.commands;
//
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.person.CompanyName;
//import seedu.address.model.person.InternshipApplication;
//import seedu.address.model.person.JobTitle;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.PersonBuilder;
//
///**
// * Contains integration tests (interaction with the Model) for {@code AddCommand}.
// */
//public class AddCommandIntegrationTest {
//
//    private Model model;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_newPerson_success() {
//        CompanyName c = new CompanyName("LinkedIn");
//        JobTitle j = new JobTitle("Data Scientist");
//        InternshipApplication expectedApplication = new InternshipApplication(c, j);
//        Person validPerson = new PersonBuilder().build();
//
//        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.addPerson(validPerson);
//
//        assertCommandSuccess(new AddCommand(expectedApplication), model,
//                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        CompanyName c = new CompanyName("LinkedIn");
//        JobTitle j = new JobTitle("Data Scientist");
//        InternshipApplication expectedApplication = new InternshipApplication(c, j);
//        Person personInList = model.getAddressBook().getPersonList().get(0);
//        assertCommandFailure(new AddCommand(expectedApplication), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//}
