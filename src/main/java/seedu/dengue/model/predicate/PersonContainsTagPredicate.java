//package seedu.dengue.model.predicate;
//
//import java.util.function.Predicate;
//
//import seedu.dengue.commons.util.StringUtil;
//import seedu.dengue.model.person.Person;
//import seedu.dengue.model.variant.Variant;
//
//public class PersonContainsTagPredicate implements Predicate<Person> {
//
//    private final Variant variant;
//
//    public PersonContainsTagPredicate(Variant variant) {
//        this.variant = variant;
//    }
//
//    @Override
//    public boolean test(Person person) {
//        Predicate<String> containsVariant =
//                keyword -> StringUtil.containsWordIgnoreCase(person.getPostal().value, keyword);
//
//        return containsVariant.test;
//    }
//}
