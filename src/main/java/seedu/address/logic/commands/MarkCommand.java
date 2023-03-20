package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Attendance;

/**
 * Mark a tutee as present on a given date
 */
public class MarkCommand extends Command {
  public static final String COMMAND_WORD = "mark";

  private final Tutee toMarkAttendance;
  private final LocalDate date;

  public MarkCommand(Tutee tutee, LocalDate date) {
    requireNonNull(tutee);
    this.toMarkAttendance = tutee;
    this.date = date;
  }
  @Override
  public CommandResult execute(Model model) throws CommandException {
    requireNonNull(model);
    TuteeBuilder modified = TuteeBuilder.fromExistingTutee(toMarkAttendance);
    Attendance attendance = toMarkAttendance.getAttendance();
    modified.setAttendance(attendance.markAttendance(date));
    model.setTutee(toMarkAttendance, modified.build());

    return new CommandResult(
      String.format("Marked %s's attendance for %s",
        toMarkAttendance.getName(),
        date.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"))
      )
    );
  }
}
