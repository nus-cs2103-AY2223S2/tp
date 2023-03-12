package seedu.careflow.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.drug.ActiveIngredient;
import seedu.careflow.model.drug.Direction;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.Purpose;
import seedu.careflow.model.drug.SideEffect;
import seedu.careflow.model.drug.StorageCount;
import seedu.careflow.model.drug.TradeName;
import seedu.careflow.model.person.Address;
import seedu.careflow.model.person.DateOfBirth;
import seedu.careflow.model.person.DrugAllergy;
import seedu.careflow.model.person.Email;
import seedu.careflow.model.person.Gender;
import seedu.careflow.model.person.Ic;
import seedu.careflow.model.person.Name;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.person.Phone;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;
import seedu.careflow.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new DateOfBirth("01-01-2000"),
                    new Gender("female"), new Ic("T1234567J"), new DrugAllergy("Penicillin"),
                    new Phone("12345678")),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new DateOfBirth("01-01-2001"),
                    new Gender("female"), new Ic("T7654321K")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new DateOfBirth("31-12-1999"),
                    new Gender("female"), new Ic("F1117777P"), new DrugAllergy("sulfonamides"),
                    new Phone("00000000")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new DateOfBirth("05-05-1995"),
                    new Gender("male"), new Ic("P9998888C")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new DateOfBirth("02-02-2002"),
                    new Gender("male"), new Ic("J0001111F"), new DrugAllergy("Aspirin"), new Phone("78965432")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new DateOfBirth("03-03-2003"),
                    new Gender("male"), new Ic("E3334444B"))
        };
    }
    public static Drug[] getSampleDrugs() {
        return new Drug[] {
            new Drug(new TradeName("Panadol"), new ActiveIngredient("Paracetamol"), new Direction("Adults and "
                    + "children over 12 years: 1-2 capsules taken every 4-6 hours as required"), new Purpose(
                            "relief of headache, pain and fever"), new SideEffect("Skin rash or itching. Breathing"
                    + " problems or swelling of lips, throat or face. Mouth ulcers. Unexplained bruising or "
                    + "bleeding."), new StorageCount("30")),
            new Drug(new TradeName("Lipitor"), new ActiveIngredient("Atorvastatin"), new Direction("10 mg once a "
                    + "day in adults and children aged 10"), new Purpose("Prevent cardiovascular disease in those at "
                    + "high risk and to treat abnormal lipid levels."), new SideEffect("Joint pain, diarrhea, "
                    + "heartburn, nausea, and muscle pains"), new StorageCount("20")),
            new Drug(new TradeName("Xanax"), new ActiveIngredient("Alprazolam"), new Direction("varies"),
                    new Purpose("Treat anxiety and panic disorders."), new SideEffect("Drowsiness, dizziness, "
                    + "increased saliva production, or change in sex drive/ability may occur."),
                    new StorageCount("10")),
            new Drug(new TradeName("Ultram"), new ActiveIngredient("Tramadol"), new Direction("Take every 4 to 6 "
                    + "hours as needed for pain relief"), new Purpose("Help relieve moderate to moderately "
                    + "severe pain."), new SideEffect("Nausea, vomiting, constipation, lightheadedness, "
                    + "dizziness, drowsiness, or headache may occur."), new StorageCount("17"))
        };
    }

    public static ReadOnlyDrugInventory getSampleDrugInventory() {
        DrugInventory sampleDI = new DrugInventory();
        for (Drug sampleDrug: getSampleDrugs()) {
            sampleDI.addDrug(sampleDrug);
        }
        return sampleDI;
    }

    public static ReadOnlyPatientRecord getSamplePatientRecord() {
        PatientRecord samplePR = new PatientRecord();
        for (Patient samplePatient: getSamplePatients()) {
            samplePR.addPatient(samplePatient);
        }
        return samplePR;
    }
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
