package dfmareu.com.api;

import java.util.ArrayList;

import dfmareu.com.models.Reunion;

public interface ApiReunion {

    ArrayList<Reunion> getReunions();
    ArrayList<Reunion> getFilteredDate(String day);
    ArrayList<Reunion> getFilteredRoom(String room);
    void addReunion(Reunion reunion);
    void deleteReunion(Reunion reunion);
}
