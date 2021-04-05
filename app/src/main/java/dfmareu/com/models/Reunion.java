package dfmareu.com.models;

import java.util.ArrayList;
import java.util.Random;

import static dfmareu.com.api.FakeApiServiceGenerator.FakeReunions;

public class Reunion {

    private final ArrayList<String> reunionUsers;
    private final String subject;
    private final int day;
    private final int month;
    private final int year;
    private final String hours;
    private final String room;

    public Reunion(ArrayList<String> reunionUsers, String subject, String room, int day, int month, int year, String hours) {
        this.reunionUsers = reunionUsers;
        this.subject = subject;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.room = room;
    }

    public static Reunion addRandomFakeReunion(){
        return FakeReunions.get(new Random().nextInt(FakeReunions.size()));
    }

    public String getChosenDay(){
        return day + "/" + month + "/" + year;
    }

    public String[] getParticipants(){
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