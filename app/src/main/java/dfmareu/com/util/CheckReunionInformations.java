package dfmareu.com.util;

import java.util.ArrayList;

public class CheckReunionInformations {
    private final String subject;
    private final ArrayList<String> participants;
    private final String hour;
    private final String day;

    Boolean isNotSubjectEmpty, isNotParticipantsEmpty, isNotHourEmpty, isNotDayEmpty = false;

    public CheckReunionInformations(String subject, ArrayList<String> participants, String hour, String day) {
        this.subject = subject;
        this.participants = participants;
        this.hour = hour;
        this.day = day;
    }

    public boolean areInformationsCompleted() {
        isNotSubjectEmpty = subject.length() == 0;
        isNotParticipantsEmpty = participants.isEmpty();
        isNotHourEmpty = hour.contains("Aucune heure");
        isNotDayEmpty = day.contains("Aucune date");
        return (!isNotSubjectEmpty && !isNotParticipantsEmpty && !isNotHourEmpty && !isNotDayEmpty);
    }

    public Boolean getNotSubjectEmpty() {
        isNotSubjectEmpty = subject.length() == 0;
        return isNotSubjectEmpty;
    }

    public Boolean getNotParticipantsEmpty() {
        isNotParticipantsEmpty = participants.isEmpty();
        return isNotParticipantsEmpty;
    }

    public Boolean getNotHourEmpty() {
        isNotHourEmpty = hour.contains("Aucune heure");
        return isNotHourEmpty;
    }

    public Boolean getNotDayEmpty() {
        isNotDayEmpty = day.contains("Aucune date");
        return isNotDayEmpty;
    }
}