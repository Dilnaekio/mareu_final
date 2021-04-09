package dfmareu.com.api;

import java.util.ArrayList;

import dfmareu.com.models.Reunion;


public class FakeApiReunion implements ApiReunion{

    private final ArrayList<Reunion> reunions = new ArrayList<>();
    //To test with fake list
    //private final ArrayList<Reunion> reunions = generateReunion();

    public FakeApiReunion() {
    }

    @Override
    public ArrayList<Reunion> getReunions() {
        return reunions;
    }

    @Override
    public ArrayList<Reunion> getFilteredDate(String day) {
        ArrayList<Reunion> filteredDate = new ArrayList<>();
        String filterPattern = day.toLowerCase().trim();
        for(Reunion reunion : reunions){
            if(reunion.getChosenDay().toLowerCase().contains(filterPattern)){
                filteredDate.add(reunion);
            }
        }
        return filteredDate;
    }

    @Override
    public ArrayList<Reunion> getFilteredRoom(String room) {
        ArrayList<Reunion> filteredDate = new ArrayList<>();
        String filterPattern = room.toLowerCase().trim();
        for (Reunion reunion : reunions) {
            if (reunion.getRoom().toLowerCase().contains(filterPattern)) {
                filteredDate.add(reunion);
            }
        }
        return filteredDate;
    }

    @Override
    public void addReunion(Reunion reunion) {
    reunions.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
    reunions.remove(reunion);
    }
}
