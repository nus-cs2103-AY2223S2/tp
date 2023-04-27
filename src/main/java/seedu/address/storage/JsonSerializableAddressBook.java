package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "You have already added the student into your list.";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "Consultation list contains duplicate consultation(s).";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "Tutorial list contains duplicate tutorial(s).";
    public static final String MESSAGE_DUPLICATE_LAB = "Lab list contains duplicate lab(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedConsultation> consultations = new ArrayList<>();
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();
    private final List<JsonAdaptedLab> labs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials,
                                       @JsonProperty("labs") List<JsonAdaptedLab> labs,
                                       @JsonProperty("consultations") List<JsonAdaptedConsultation> consultations) {
        this.persons.addAll(persons);
        this.consultations.addAll(consultations);
        this.tutorials.addAll(tutorials);
        this.labs.addAll(labs);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        consultations.addAll(source.getConsultationList()
                .stream()
                .map(JsonAdaptedConsultation::new)
                .collect(Collectors.toList()));
        tutorials.addAll(source.getTutorialList().stream().map(JsonAdaptedTutorial::new).collect(Collectors.toList()));
        labs.addAll(source.getLabList().stream().map(JsonAdaptedLab::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedTutorial jsonAdaptedTutorial : tutorials) {
            Tutorial tutorial = jsonAdaptedTutorial.toModelType();
            ParserUtil.MASTER_TIME.add(new LocalDateTime[]{tutorial.getDate(), tutorial.getDate().plusHours(1)});
            if (addressBook.hasTutorial(tutorial)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL);
            }
            addressBook.addTutorial(tutorial);
        }
        for (JsonAdaptedLab jsonAdaptedLab : labs) {
            Lab lab = jsonAdaptedLab.toModelType();
            ParserUtil.MASTER_TIME.add(new LocalDateTime[]{lab.getDate(), lab.getDate().plusHours(2)});
            if (addressBook.hasLab(lab)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LAB);
            }
            addressBook.addLab(lab);
        }
        for (JsonAdaptedConsultation jsonAdaptedConsultation : consultations) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            ParserUtil.MASTER_TIME.add(new LocalDateTime[]{consultation.getDate(),
                    consultation.getDate().plusHours(1)});
            if (addressBook.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            addressBook.addConsultation(consultation);
        }
        return addressBook;
    }

}
