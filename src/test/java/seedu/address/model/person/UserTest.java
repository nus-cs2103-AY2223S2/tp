package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void getUser_getTwice_isTheSame() {
        User user = User.getUser();
        User other = User.getUser();
        assertEquals(user, other);
    }
}
