package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.model.application.InternshipApplication;

/**
 * Represents an internship application with the most imminent interview
 */
public class Reminder {
    private InternshipApplication upcomingApplication;
    private final List<InternshipApplication> applications;

    /**
     * Every field must be present and not null.
     */
    public Reminder(List<InternshipApplication> applications) {
        requireAllNonNull(applications);
        this.applications = applications;
        this.upcomingApplication = null;
    }

    public InternshipApplication getClosestUpcomingInterview() {
        return upcomingApplication;
    }

    public void setClosestUpcomingInterview() {
        LocalDateTime now = LocalDateTime.now();
        InternshipApplication closestApplication = null;
        if (applications == null || applications.isEmpty()) {
            this.upcomingApplication = null;
        } else {
            for (int i = 0; i < applications.size(); i++) {
                InternshipApplication application = applications.get(i);
                if (application.getInterviewDate() != null) {
                    if (application.getInterviewDate().getDateTime().isAfter(now)) {
                        closestApplication = application;
                        break;
                    }
                }
            }
            for (int i = 0; i < applications.size(); i++) {
                InternshipApplication application = applications.get(i);
                if (application.getInterviewDate() != null) {
                    if (application.getInterviewDate().getDateTime().isAfter(now)) {
                        if (closestApplication.getInterviewDate() == null
                                || application.getInterviewDate()
                                .isBeforeInclusive(closestApplication.getInterviewDate())) {
                            closestApplication = application;
                        }
                    }
                }
            }
            if (closestApplication == null) {
                this.upcomingApplication = null;
            } else {
                this.upcomingApplication = closestApplication;
            }
        }
    }
}

