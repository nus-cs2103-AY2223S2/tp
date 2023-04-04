package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PolicyExistInPersonPredicateTest {

    private final List<String> firstPredicateKeywordList = Collections.singletonList("Insurance");
    private final List<String> secondPredicateKeywordList = Collections.singletonList("Investment");

    private final Person dudeWithInsurancePolicy = new PersonBuilder().withTags("Insurance").build();
    private final Person dudeWithInvestmentPolicy = new PersonBuilder().withTags("Investment").build();
    private final Person dudewithInsuranceAndInvestment = new PersonBuilder()
        .withTags("Insurance", "Investment")
        .build();
    private final Person dudeWithInvestmentAndRandomPolicy = new PersonBuilder()
        .withTags("Investment", "Random")
        .build();

    @Test
    public void test_validPolicyNameMatches_returnTrue() {
        PolicyExistInPersonPredicate insurancePolicyPredicate =
            new PolicyExistInPersonPredicate(firstPredicateKeywordList);

        assertTrue(insurancePolicyPredicate.test(dudeWithInsurancePolicy));
        assertTrue(insurancePolicyPredicate.test(dudewithInsuranceAndInvestment));

        PolicyExistInPersonPredicate investmentPolicyPredicate =
            new PolicyExistInPersonPredicate(secondPredicateKeywordList);

        assertTrue(investmentPolicyPredicate.test(dudeWithInvestmentPolicy));
        assertTrue(insurancePolicyPredicate.test(dudewithInsuranceAndInvestment));
        assertTrue(investmentPolicyPredicate.test(dudeWithInvestmentAndRandomPolicy));
    }

    @Test
    public void test_validPolicyNameDoesNotMatch_returnFalse() {
        PolicyExistInPersonPredicate insurancePolicyPredicate =
            new PolicyExistInPersonPredicate(firstPredicateKeywordList);

        assertFalse(insurancePolicyPredicate.test(dudeWithInvestmentPolicy));
        assertFalse(insurancePolicyPredicate.test(dudeWithInvestmentAndRandomPolicy));

        PolicyExistInPersonPredicate investmentPolicyPredicate =
            new PolicyExistInPersonPredicate(secondPredicateKeywordList);

        assertFalse(investmentPolicyPredicate.test(dudeWithInsurancePolicy));
    }
}
