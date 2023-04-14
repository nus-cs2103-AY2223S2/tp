package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_FEB_OA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOpenings.GOOGLE;
import static seedu.address.testutil.TypicalOpenings.SHOPEE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OpeningBuilder;
import seedu.ultron.model.opening.Opening;

public class OpeningTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Opening opening = new OpeningBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> opening.getKeydates().remove(0));
    }

    @Test
    public void isSameOpening() {
        // same object -> returns true
        assertTrue(SHOPEE.isSameOpening(SHOPEE));

        // null -> returns false
        assertFalse(SHOPEE.isSameOpening(null));

        // same company and position, all other attributes different -> returns true
        Opening editedShopee = new OpeningBuilder(SHOPEE).withEmail(VALID_EMAIL_GOOGLE).withStatus(VALID_STATUS_GOOGLE)
                .withRemark(VALID_REMARK_GOOGLE).withKeydates(VALID_KEYDATE_FEB_OA).build();
        assertTrue(SHOPEE.isSameOpening(editedShopee));

        // same company and different position, all other attributes same -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withPosition(VALID_POSITION_GOOGLE).build();
        assertFalse(SHOPEE.isSameOpening(editedShopee));

        // same position and different company, all other attributes same -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withCompany(VALID_COMPANY_GOOGLE).build();
        assertFalse(SHOPEE.isSameOpening(editedShopee));

        // different company and position, all other attributes same -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withCompany(VALID_COMPANY_GOOGLE)
                .withPosition(VALID_POSITION_GOOGLE).build();
        assertFalse(SHOPEE.isSameOpening(editedShopee));

        // company differs in case, all other attributes same -> returns true
        Opening editedGoogle = new OpeningBuilder(GOOGLE).withCompany(VALID_COMPANY_GOOGLE.toLowerCase()).build();
        assertTrue(GOOGLE.isSameOpening(editedGoogle));

        // company differs in case, all other attributes same -> returns true
        editedGoogle = new OpeningBuilder(GOOGLE).withPosition(VALID_POSITION_GOOGLE.toLowerCase()).build();
        assertTrue(GOOGLE.isSameOpening(editedGoogle));

        // company has trailing spaces, all other attributes same -> returns true
        String companyWithTrailingSpaces = VALID_COMPANY_GOOGLE + " ";
        editedGoogle = new OpeningBuilder(GOOGLE).withCompany(companyWithTrailingSpaces).build();
        assertTrue(GOOGLE.isSameOpening(editedGoogle));

        // position has trailing spaces, all other attributes same -> returns true
        String positionWithTrailingSpaces = VALID_POSITION_GOOGLE + " ";
        editedGoogle = new OpeningBuilder(GOOGLE).withPosition(positionWithTrailingSpaces).build();
        assertTrue(GOOGLE.isSameOpening(editedGoogle));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Opening shopeeCopy = new OpeningBuilder(SHOPEE).build();
        assertTrue(SHOPEE.equals(shopeeCopy));

        // same object -> returns true
        assertTrue(SHOPEE.equals(SHOPEE));

        // null -> returns false
        assertFalse(SHOPEE.equals(null));

        // different type -> returns false
        assertFalse(SHOPEE.equals(5));

        // different opening -> returns false
        assertFalse(SHOPEE.equals(GOOGLE));

        // different company -> returns false
        Opening editedShopee = new OpeningBuilder(SHOPEE).withCompany(VALID_COMPANY_GOOGLE).build();
        assertFalse(SHOPEE.equals(editedShopee));

        // different position -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withPosition(VALID_POSITION_GOOGLE).build();
        assertFalse(SHOPEE.equals(editedShopee));

        // different email -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withEmail(VALID_EMAIL_GOOGLE).build();
        assertFalse(SHOPEE.equals(editedShopee));

        // different status -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withStatus(VALID_STATUS_GOOGLE).build();
        assertFalse(SHOPEE.equals(editedShopee));

        // different keydates -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withKeydates(VALID_KEYDATE_FEB_OA).build();
        assertFalse(SHOPEE.equals(editedShopee));

        // different remarks -> returns false
        editedShopee = new OpeningBuilder(SHOPEE).withRemark(VALID_REMARK_GOOGLE).build();
        assertFalse(SHOPEE.equals(editedShopee));
    }
}
