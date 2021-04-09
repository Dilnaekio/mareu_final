package dfmareu.com.api;

import java.util.ArrayList;
import java.util.Arrays;

import dfmareu.com.models.Reunion;

public abstract class FakeApiServiceGenerator {

    public static final ArrayList<String> FakeUsersList = new ArrayList<String>() {{
        add("delcourtfabian@gmail.com");
        add("beranger.nynga@gmail.com");
        add("ludovic.c.roland@gmail.com");
    }};
    public static final ArrayList<Reunion> FakeReunions = new ArrayList<>(Arrays.asList(
            new Reunion(FakeUsersList, "Les crocodiles", "Salle A", "25/4/2021", "10h00"),
            new Reunion(FakeUsersList, "Les singes", "Salle B", "26/4/2021", "11h00"),
            new Reunion(FakeUsersList, "Les chiens", "Salle C", "27/4/2021", "12h00"),
            new Reunion(FakeUsersList, "Les chats", "Salle D", "28/4/2021", "13h00"),
            new Reunion(FakeUsersList, "Les éléphants", "Salle E", "29/4/2021", "14h00"),
            new Reunion(FakeUsersList, "Les oiseaux", "Salle F", "30/4/2021", "15h00"),
            new Reunion(FakeUsersList, "Les kangourous", "Salle G", "01/5/2021", "16h00"),
            new Reunion(FakeUsersList, "Les poissons", "Salle H", "02/5/2021", "17h00"),
            new Reunion(FakeUsersList, "Les dauphins", "Salle I", "03/5/2021", "18h00"),
            new Reunion(FakeUsersList, "Les animaux", "Salle J", "04/5/2021", "19h00")
    ));

    static ArrayList<Reunion> generateReunion() {
        return new ArrayList<>(FakeReunions);
    }
}

