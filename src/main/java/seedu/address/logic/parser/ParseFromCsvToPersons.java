package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.files.Csv;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Platoon;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Unit;
import seedu.address.model.tag.Tag;

/**
 * Used for parsing all information in a {@code Csv} object to create new {@code Person}.
 */
public class ParseFromCsvToPersons {

    public static final String MESSAGE_MISSING_FIELDS = "CSV file given must contain all fields (Name, Phone, Email, "
            + "Address, Rank, Unit, Company, Platoon, Tags) for each entry. The first row has to contain the headers "
            + "for each column";
    private static final String[] necessaryFields = {"name", "phone", "email", "address", "rank", "unit", "company",
        "platoon", "tags"};
    private Csv csv;
    private int numOfPersons;

    /**
     * Checks if the file given can be read, and if the CSV file's headers are in the correct format.
     *
     * @param csv Csv object containing data from given CSV file.
     * @throws FileNotFoundException if file does not exist at given path
     * @throws IOException if error occurs while reading the file
     * @throws ParseException if user input does not conform to the expected format
     */
    public ParseFromCsvToPersons(Csv csv) throws ParseException {
        requireNonNull(csv);
        this.csv = csv;
        numOfPersons = csv.getNumOfRows() - 1;

        if (!csv.isColNumFixedPerRow()) {
            throw new ParseException("Each row in the CSV file given must have the same number of columns");
        }
        requireAllFieldsPresent();
    }

    /**
     * Parses and converts given {@code Csv} object to a list of {@code Person}s.
     * Each row of the {@code Csv} object represents one {@code Person}'s information.
     *
     * @throws ParseException if the CSV file has values that do not conform to the expected format for each field
     */
    public List<Person> parse() throws ParseException {
        List<Person> persons = new ArrayList<>();
        for (int i = 1; i <= numOfPersons; i++) {
            Person newPerson = getPersonFromRow(i);
            persons.add(newPerson);
        }

        return persons;
    }

    private Person getPersonFromRow(int rowNumber) throws ParseException {
        // compulsory fields
        Name name = ParserUtil.parseName(csv.getEntry(rowNumber, "name"));
        Rank rank = ParserUtil.parseRank(csv.getEntry(rowNumber, "rank"));
        Phone phone = ParserUtil.parsePhone(csv.getEntry(rowNumber, "phone"));
        Email email = ParserUtil.parseEmail(csv.getEntry(rowNumber, "email"));
        Address address = ParserUtil.parseAddress(csv.getEntry(rowNumber, "address"));

        // optional fields
        Unit unit = ParserUtil.parseUnit(orElseNa(csv.getEntry(rowNumber, "unit")));
        Company company = ParserUtil.parseCompany(orElseNa(csv.getEntry(rowNumber, "company")));
        Platoon platoon = ParserUtil.parsePlatoon(orElseNa(csv.getEntry(rowNumber, "platoon")));

        Set<Tag> tags = getTagsFromRow(rowNumber);

        return new Person(rank, name, unit, company, platoon, phone, email, address, tags);
    }

    private void requireAllFieldsPresent() throws ParseException {
        String[] headers = csv.getRow(0);

        if (!Arrays.stream(necessaryFields).allMatch(f -> Arrays.stream(headers)
                .anyMatch(h -> h.equalsIgnoreCase(f)))) {
            throw new ParseException(MESSAGE_MISSING_FIELDS);
        }

        int tagsIndex = csv.getColumnIndex("tags");
        for (int i = tagsIndex + 1; i < csv.getNumOfCols(); i++) {
            if (!headers[i].isBlank()) {
                throw new ParseException("CSV file given must leave all columns headers after the Tags header blank");
            }
        }
    }

    private Set<Tag> getTagsFromRow(int rowIndex) {
        Set<Tag> tags = new HashSet<Tag>();
        for (int i = csv.getColumnIndex("tags"); i < csv.getNumOfCols(); i++) {
            String tagValue = csv.getEntry(rowIndex, i);
            if (!tagValue.equals("")) {
                Tag tag = new Tag(tagValue);
                tags.add(tag);
            }
        }
        return tags;
    }

    private String orElseNa(String string) {
        if (string.isBlank()) {
            return "N/A";
        }
        return string;
    }
}
