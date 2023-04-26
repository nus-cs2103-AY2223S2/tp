package seedu.careflow.logic.parser.drugparser;

import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.drugcommands.AddCommand;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.testutil.DrugBuilder;

class AddCommandParserTest {

    private final AddCommandParser addCommandParser = new AddCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Drug expectedDrug = new DrugBuilder().build();
        AddCommand addDrugCommand = new AddCommand(expectedDrug);
        assertParseSuccess(addCommandParser,
                " -tn Tylenol -ai Acetaminophen -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -se Common side effects of Tylenol may include "
                        + "nausea, vomiting, stomach pain, and constipation. Less common side effects may include "
                        + "rash, itching, and hives. -sc 20", addDrugCommand);
    }

    @Test
    void parse_invalidStorageCount_failure() {
        assertParseFailure(addCommandParser,
                 " -tn Tylenol -ai Acetaminophen -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -se Common side effects of Tylenol may include "
                        + "nausea, vomiting, stomach pain, and constipation. Less common side effects may include "
                        + "rash, itching, and hives. -sc 99999999",
                "Storage Count should only contain positive integers, it should be at least 1 digit long "
                        + "but no more than 3 digits and be less than 500"

        );
    }

    @Test
    void parse_invalidTradeName_failure() {
        assertParseFailure(addCommandParser,
                " -tn $$$ -ai Acetaminophen -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -se Common side effects of Tylenol may include "
                        + "nausea, vomiting, stomach pain, and constipation. Less common side effects may include "
                        + "rash, itching, and hives. -sc 99999999",
                "Trade names should only contain alphanumeric characters and spaces, it should not be "
                        + "blank and less than 50 characters"

        );
    }

    @Test
    void parse_invalidActiveIngredient_failure() {
        assertParseFailure(addCommandParser,
                " -tn Tylenol -ai ? -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -se Common side effects of Tylenol may include "
                        + "nausea, vomiting, stomach pain, and constipation. Less common side effects may include "
                        + "rash, itching, and hives. -sc 99999999",
                "Active ingredient should only contain alphanumeric characters, spaces and dashes, "
                        + "and it should not be blank but less than 200 characters long"

        );
    }

    @Test
    void parse_missingTradeName_failure() {
        assertParseFailure(addCommandParser,
                "-ai Acetaminophen -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -se Common side effects of Tylenol may include "
                        + "nausea, vomiting, stomach pain, and constipation. Less common side effects may include "
                        + "rash, itching, and hives. -sc 20",
                "Invalid command format! \nd add: Adds a drug to the drug inventory.\nParameters: -tn TRADE NAME -ai "
                        + "ACTIVE INGREDIENT -pur PURPOSE(S)... -se SIDE EFFECT(S)... -dir DIRECTION -sc STORAGE COUNT"
                        + "\nExample: d add -tn Panadol -ai Paracetamol -pur relieve pain / relieve fever / relieve "
                        + "headache -se skin rash / swelling of the lips, tongue, throat or face / nausea / "
                        + "unexplained bruising or bleeding -dir Adults and children over 12 years: 1-2 caplets taken "
                        + "every 4 to 6 hours. Not recommended for children under 12 years. -sc 50"

        );
    }

    @Test
    void parse_missingDirections_failure() {
        assertParseFailure(addCommandParser,
                 " -tn Tylenol -ai Acetaminophen -pur Tylenol is a pain reliever and fever reducer that is used to "
                        + "treat mild to moderate pain, such as headaches, toothaches, menstrual cramps, back pain, "
                        + "and arthritis. -se Common side effects of Tylenol may include nausea, vomiting, stomach "
                        + "pain, and constipation. Less common side effects may include rash, itching, and hives. "
                        + "-sc 20",
                "Invalid command format! \nd add: Adds a drug to the drug inventory.\nParameters: -tn TRADE NAME -ai "
                        + "ACTIVE INGREDIENT -pur PURPOSE(S)... -se SIDE EFFECT(S)... -dir DIRECTION -sc STORAGE COUNT"
                        + "\nExample: d add -tn Panadol -ai Paracetamol -pur relieve pain / relieve fever / relieve "
                        + "headache -se skin rash / swelling of the lips, tongue, throat or face / nausea / "
                        + "unexplained bruising or bleeding -dir Adults and children over 12 years: 1-2 caplets taken "
                        + "every 4 to 6 hours. Not recommended for children under 12 years. -sc 50"
        );
    }

    @Test
    void parse_missingPurpose_failure() {
        assertParseFailure(addCommandParser,
                 " -tn Tylenol -ai Acetaminophen -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -se Common side effects of Tylenol may "
                        + "include nausea, vomiting, stomach pain, and constipation. Less common side effects may "
                        + "include rash, itching, and hives. -sc 20",
                "Invalid command format! \nd add: Adds a drug to the drug inventory.\nParameters: -tn TRADE NAME -ai "
                        + "ACTIVE INGREDIENT -pur PURPOSE(S)... -se SIDE EFFECT(S)... -dir DIRECTION -sc STORAGE COUNT"
                        + "\nExample: d add -tn Panadol -ai Paracetamol -pur relieve pain / relieve fever / relieve "
                        + "headache -se skin rash / swelling of the lips, tongue, throat or face / nausea / "
                        + "unexplained bruising or bleeding -dir Adults and children over 12 years: 1-2 caplets taken "
                        + "every 4 to 6 hours. Not recommended for children under 12 years. -sc 50"
        );
    }

    @Test
    void parse_missingActiveIngredient_failure() {
        assertParseFailure(addCommandParser,
                 " -tn Tylenol -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -se Common side effects of Tylenol may include "
                        + "nausea, vomiting, stomach pain, and constipation. Less common side effects may include "
                        + "rash, itching, and hives. -sc 20",
                "Invalid command format! \nd add: Adds a drug to the drug inventory.\nParameters: -tn TRADE NAME -ai "
                        + "ACTIVE INGREDIENT -pur PURPOSE(S)... -se SIDE EFFECT(S)... -dir DIRECTION -sc STORAGE COUNT"
                        + "\nExample: d add -tn Panadol -ai Paracetamol -pur relieve pain / relieve fever / relieve "
                        + "headache -se skin rash / swelling of the lips, tongue, throat or face / nausea / "
                        + "unexplained bruising or bleeding -dir Adults and children over 12 years: 1-2 caplets taken "
                        + "every 4 to 6 hours. Not recommended for children under 12 years. -sc 50"
        );
    }

    @Test
    void parse_missingSideEffect_failure() {
        assertParseFailure(addCommandParser,
                " -tn Tylenol -ai Acetaminophen -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -sc 20",
                "Invalid command format! \nd add: Adds a drug to the drug inventory.\nParameters: -tn TRADE NAME -ai "
                        + "ACTIVE INGREDIENT -pur PURPOSE(S)... -se SIDE EFFECT(S)... -dir DIRECTION -sc STORAGE COUNT"
                        + "\nExample: d add -tn Panadol -ai Paracetamol -pur relieve pain / relieve fever / relieve "
                        + "headache -se skin rash / swelling of the lips, tongue, throat or face / nausea / "
                        + "unexplained bruising or bleeding -dir Adults and children over 12 years: 1-2 caplets taken "
                        + "every 4 to 6 hours. Not recommended for children under 12 years. -sc 50"
        );
    }

    @Test
    void parse_missingStorageCount_failure() {
        assertParseFailure(addCommandParser,
                " -tn Tylenol -ai Acetaminophen -dir The usual recommended dose of Tylenol for adults is 325 to 1000 "
                        + "mg taken every 4 to 6 hours, up to a maximum of 4000 mg per day. Dosage should be adjusted "
                        + "based on age, weight, and other medical conditions. -pur Tylenol is a pain reliever and "
                        + "fever reducer that is used to treat mild to moderate pain, such as headaches, toothaches, "
                        + "menstrual cramps, back pain, and arthritis. -se Common side effects of Tylenol may include "
                        + "nausea, vomiting, stomach pain, and constipation. Less common side effects may include "
                        + "rash, itching, and hives.",
                "Invalid command format! \nd add: Adds a drug to the drug inventory.\nParameters: -tn TRADE NAME -ai "
                        + "ACTIVE INGREDIENT -pur PURPOSE(S)... -se SIDE EFFECT(S)... -dir DIRECTION -sc STORAGE COUNT"
                        + "\nExample: d add -tn Panadol -ai Paracetamol -pur relieve pain / relieve fever / relieve "
                        + "headache -se skin rash / swelling of the lips, tongue, throat or face / nausea / "
                        + "unexplained bruising or bleeding -dir Adults and children over 12 years: 1-2 caplets taken "
                        + "every 4 to 6 hours. Not recommended for children under 12 years. -sc 50"
        );
    }
}
