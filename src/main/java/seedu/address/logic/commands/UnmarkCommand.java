package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Attendance;

/**
 * Marks a tutee as absent on a given date
 */
public class UnmarkCommand extends Command {
  public static final String COMMAND_WORD = "unmark";

  private final Tutee toMarkAttendance;
  private final LocalDate date;

  public UnmarkCommand(Tutee tutee, LocalDate date) {
    requireNonNull(tutee);
    this.toMarkAttendance = tutee;
    this.date = date;
  }

  @Override
  public CommandResult execute(Model model) throws CommandException {
    requireNonNull(model);
    TuteeBuilder modified = TuteeBuilder.fromExistingTutee(toMarkAttendance);
    Attendance attendance = toMarkAttendance.getAttendance();
    try {
      modified.withAttendance(attendance.unmarkAttendance(date));
      model.setTutee(toMarkAttendance, modified.build());
      
      return new CommandResult(
        String.format("Marked %s's attendance for %s as absent",
        toMarkAttendance.getName(),
        date.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"))
        )
      );
    } catch (NoSuchElementException e) {
      return new CommandResult(String.format(
        "%s was not present on %s!",
        toMarkAttendance.getName(),
        date.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"))
      ));
    }
  }
}
