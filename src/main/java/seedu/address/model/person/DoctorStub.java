package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.tag.Tag;

public class DoctorStub extends Doctor {

    public DoctorStub() {
        super(new Name("John Doe"), new Phone("88887777"),
                new Email("prawn@gmail.com"), new Specialty("GP"), new Yoe("1"),
                new HashSet<Tag>(List.of(new Tag("Friend"), new Tag("College"))));
    }

}
