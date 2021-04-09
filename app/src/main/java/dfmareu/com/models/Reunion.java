package dfmareu.com.models;

import java.util.ArrayList;

public class Reunion {

    private final ArrayList<String> reunionUsers;
    private final String subject;
    private final String day;
    private final String hours;
    private final String room;

    public Reunion(ArrayList<String> reunionUsers, String subject, String room, String day, String hours) {
        this.reunionUsers = reunionUsers;
        this.subject = subject;
        this.day = day;
        this.hours = hours;
        this.room = room;
    }

    public String getChosenDay() {
        return day;
    }

    public String[] getParticipants() {
        return reunionUsers.toArray(new String[0]);
    }

    public String getSubject() {
        return subject;
    }

    public String getRoom() {
        return room;
    }

    public String getHours() {
        return hours;
    }
}