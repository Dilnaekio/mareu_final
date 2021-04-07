package dfmareu.com;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import dfmareu.com.di.Injection;
import dfmareu.com.models.Reunion;
import dfmareu.com.repository.ReunionRepository;

import static dfmareu.com.api.FakeApiServiceGenerator.FakeReunions;
import static dfmareu.com.api.FakeApiServiceGenerator.FakeUsersList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnitTestP4 {
    ReunionRepository reunionRepository;
    Reunion reunionTest;
    String filterPattern;
    ArrayList<Reunion> filteredList;
    Reunion filteredReunion;

    @Before
    public void setUp(){
        reunionRepository = Injection.createReunionRepository();
        reunionTest = new Reunion (FakeUsersList, "Sujet", "Salle Test", 999, 3, 2021, "18h00");
    }

    @Test
    public void getReunions(){
        ArrayList<Reunion> realReunions = reunionRepository.getReunions();

        assertEquals(FakeReunions.size(), realReunions.size());
    }

    @Test
    public void addReunion(){
        reunionRepository.addReunion(reunionTest);

        assertTrue(reunionRepository.getReunions().contains(reunionTest));
    }

    @Test
    public void deleteReunion(){
        Reunion reunionToDelete = reunionRepository.getReunions().get(0);
        reunionRepository.deleteReunion(reunionToDelete);

        assertFalse(reunionRepository.getReunions().contains(reunionToDelete));
    }

    @Test
    public void filteredDateReunion(){
    reunionRepository.addReunion(reunionTest);
    filterPattern = "999";
    filteredList = reunionRepository.getFilteredDate(filterPattern);
    filteredReunion = filteredList.get(0);

    assertTrue(filteredReunion.getChosenDay().contains(filterPattern));
    }

    @Test
    public void filteredRoomReunion(){
        reunionRepository.addReunion(reunionTest);
        filterPattern = "Salle Test";
        filteredList = reunionRepository.getFilteredRoom(filterPattern);
        filteredReunion = filteredList.get(0);

        assertTrue(filteredReunion.getRoom().contains(filterPattern));
    }
}
