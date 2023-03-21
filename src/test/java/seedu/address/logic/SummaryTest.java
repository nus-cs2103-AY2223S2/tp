package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.aggregatefunction.AggregateFunction;

public class SummaryTest {
    @Test
    public void toString_emptySummary_success() {
        Summary summary = new Summary();
        assertEquals(summary.toString(), Summary.MESSAGE_NO_DATA);
    }

    @Test
    public void toString_givenAggregateFunction_success() {
        Summary summary = new Summary();
        summary.describe(new AggregateFunctionStub());
        String expectedDescription = Summary.TITLE + String.format(
                Summary.STATISTICS_DESCRIPTION, AggregateFunctionStub.description, AggregateFunctionStub.result);
        assertEquals(summary.toString(), expectedDescription);
    }

    /**
     * A stub AggregateFunction with a dummy description and value.
     */
    private static class AggregateFunctionStub extends AggregateFunction {

        private static String description = "Dummy description";

        private static String result = "Dummy result";
        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public String getResult() {
            return result;
        }
    }
}
