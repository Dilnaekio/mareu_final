package dfmareu.com.repository;

import java.util.ArrayList;

import dfmareu.com.api.ApiReunion;
import dfmareu.com.models.Reunion;


public class ReunionRepository implements ApiReunion {

    private final ApiReunion apiReunion;

    public ReunionRepository(ApiReunion apiReunion) {
        this.apiReunion = apiReunion;
    }

    public ArrayList<Reunion> getReunions() {
        return apiReunion.getReunions();
    }

    @Override
    public ArrayList<Reunion> getFilteredDate(String day) {
        return apiReunion.getFilteredDate(day);
    }

    @Override
    public ArrayList<Reunion> getFilteredRoom(String room) {
        return apiReunion.getFilteredRoom(room);
    }

    @Override
    public void addReunion(Reunion reunion) {
        this.apiReunion.addReunion(reunion);
    }

    public void deleteReunion(Reunion reunion) {
        apiReunion.deleteReunion(reunion);
    }
}
