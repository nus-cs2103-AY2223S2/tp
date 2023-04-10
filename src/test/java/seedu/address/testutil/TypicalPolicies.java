package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.client.policy.Policy;

/**
 * A utility class containing a typical set of policies for testing purposes.
 */
public class TypicalPolicies {
    public static final Policy HEALTH = new PolicyBuilder().withPolicyName("Health Insurance")
            .withStartDate("01.02.2023")
            .withPremium("80.50")
            .withFrequency("monthly").build();

    public static final Policy AMY_POLICY = new PolicyBuilder().withPolicyName("Life Insurance")
            .withStartDate("01.01.2020")
            .withPremium("1000")
            .withFrequency("monthly").build();
    public static final Policy LIFE = new PolicyBuilder().withPolicyName("Life Insurance")
            .withStartDate("02.03.2023")
            .withPremium("98.20")
            .withFrequency("weekly").build();
    public static final Policy PERSONAL_INJURY = new PolicyBuilder().withPolicyName("Medical Insurance")
            .withStartDate("03.04.2023")
            .withPremium("350.15")
            .withFrequency("yearly").build();


    // Manually added
    public static final Policy TRAVEL = new PolicyBuilder().withPolicyName("Travel Insurance")
            .withStartDate("04.05.2023")
            .withPremium("20.00")
            .withFrequency("monthly").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Policy DEATH = new PolicyBuilder().withPolicyName("Health Insurance")
            .withStartDate("05.06.2023")
            .withPremium("700.10")
            .withFrequency("yearly").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPolicies() {} // prevents instantiation


    public static List<Policy> getTypicalPolicies() {
        return new ArrayList<>(Arrays.asList(HEALTH, LIFE, PERSONAL_INJURY));
    }
}
