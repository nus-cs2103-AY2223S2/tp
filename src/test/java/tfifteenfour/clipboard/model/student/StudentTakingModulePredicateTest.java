// package tfifteenfour.clipboard.model.student;

// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;

// import tfifteenfour.clipboard.testutil.StudentBuilder;

// public class StudentTakingModulePredicateTest {

//     @Test
//     public void equals() {
//         String firstPredicateModule = "CS2103T";
//         String secondPredicateModule = "CS2106";

//         StudentTakingModulePredicate firstPredicate = new StudentTakingModulePredicate(firstPredicateModule);
//         StudentTakingModulePredicate secondPredicate = new StudentTakingModulePredicate(secondPredicateModule);

//         // same object -> returns true
//         assertTrue(firstPredicate.equals(firstPredicate));
// //
//         // same values -> returns true
//         StudentTakingModulePredicate firstPredicateCopy = new StudentTakingModulePredicate(firstPredicateModule);
//         assertTrue(firstPredicate.equals(firstPredicateCopy));

//         // different types -> returns false
//         assertFalse(firstPredicate.equals(1));

//         // null -> returns false
//         assertFalse(firstPredicate.equals(null));

//         // different student -> returns false
//         assertFalse(firstPredicate.equals(secondPredicate));
//     }

//     @Test
//     public void test_studentTakingModule_returnsTrue() {
//         // One keyword
//         StudentTakingModulePredicate predicate = new StudentTakingModulePredicate("CS2103T");
//         assertTrue(predicate.test(new StudentBuilder().withModules("CS2103T").build()));

//     }

//     @Test
//     public void test_studentNotTakingModule_returnsFalse() {
//         // Zero module
//         StudentTakingModulePredicate predicate = new StudentTakingModulePredicate(" ");
//         assertFalse(predicate.test(new StudentBuilder().withModules("CS2103T").build()));

//         // Non-matching module
//         predicate = new StudentTakingModulePredicate("CS2105");
//         assertFalse(predicate.test(new StudentBuilder().withModules("CS2103T").build()));

//     }
// }

