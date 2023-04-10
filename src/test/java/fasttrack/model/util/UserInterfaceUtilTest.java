package fasttrack.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class UserInterfaceUtilTest {

    @Test
    public void testParseDate_validDate_success() {
        LocalDate date1 = LocalDate.of(2023, 4, 10);
        String formattedDate1 = UserInterfaceUtil.parseDate(date1);
        assertEquals("10/04/23", formattedDate1);
        LocalDate date2 = LocalDate.of(2023, 12, 31);
        String formattedDate2 = UserInterfaceUtil.parseDate(date2);
        assertEquals("31/12/23", formattedDate2);
    }

    @Test
    public void testParsePrice_validInput_success() {
        double amount1 = 10.00;
        String formattedAmount1 = UserInterfaceUtil.parsePrice(amount1);
        assertEquals("$10.00", formattedAmount1);

        double amount2 = -5.50;
        String formattedAmount2 = UserInterfaceUtil.parsePrice(amount2);
        assertEquals("$-5.50", formattedAmount2);

        double amount3 = 3.14159;
        String formattedAmount3 = UserInterfaceUtil.parsePrice(amount3);
        assertEquals("$3.14", formattedAmount3);
    }

    @Test
    public void testCapitalizeFirstLetter_validInput_success() {
        String input1 = "hello";
        String capitalized1 = UserInterfaceUtil.capitalizeFirstLetter(input1);
        assertEquals("Hello", capitalized1);

        String input2 = "wORLD";
        String capitalized2 = UserInterfaceUtil.capitalizeFirstLetter(input2);
        assertEquals("WORLD", capitalized2);

        String input3 = "TEST";
        String capitalized3 = UserInterfaceUtil.capitalizeFirstLetter(input3);
        assertEquals("TEST", capitalized3);
    }

}

