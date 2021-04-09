package dfmareu.com.util;

import android.util.Log;

import java.util.ArrayList;

public class CheckReunionInformations {
    private final String subject;
    private final ArrayList<String> participants;
    private final String hour;

    Boolean isNotSubjectEmpty, isNotParticipantsEmpty, isNotHourEmpty = false;

    public CheckReunionInformations(String subject, ArrayList<String> participants, String hour) {
        this.subject = subject;
        this.participants = participants;
        this.hour = hour;
    }

    public boolean areInformationsCompleted() {
        isNotSubjectEmpty = subject.length() == 0;
        isNotParticipantsEmpty = participants.isEmpty();
        isNotHourEmpty = hour.contains("Aucune heure");
        Log.i("MainActivityFile", "Subject is empty = " + isNotSubjectEmpty.toString() + "/ Participants is empty " + isNotParticipantsEmpty.toString() + "/ Hour is empty " + isNotHourEmpty.toString());
        return (!isNotSubjectEmpty && !isNotParticipantsEmpty && !isNotHourEmpty);
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
}