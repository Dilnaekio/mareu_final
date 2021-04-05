package dfmareu.com.api;

import java.util.ArrayList;
import java.util.Arrays;

import dfmareu.com.models.Reunion;

public abstract class FakeApiServiceGenerator {

   public static final ArrayList<String> FakeUsersList = new ArrayList<String>() {{
       add("Fabian");
       add("Lolita");
       add("Charles");
    }};
    public static ArrayList<Reunion> FakeReunions = new ArrayList<>(Arrays.asList(
        new Reunion(FakeUsersList, "Les crocodiles", "Salle A", 21, 3,21,"10h00"),
        new Reunion(FakeUsersList, "Les singes", "Salle B", 22, 3,21,"11h00"),
        new Reunion(FakeUsersList, "Les chiens", "Salle C", 23, 3,21,"12h00"),
        new Reunion(FakeUsersList, "Les chats", "Salle D", 24, 3,21,"13h00"),
        new Reunion(FakeUsersList, "Les éléphants", "Salle E", 25, 3,21,"14h00"),
        new Reunion(FakeUsersList, "Les oiseaux", "Salle F", 26, 3,21,"15h00"),
        new Reunion(FakeUsersList, "Les kangourous", "Salle G", 27, 3,21,"16h00"),
        new Reunion(FakeUsersList, "Les poissons", "Salle H", 28, 3,21,"17h00"),
        new Reunion(FakeUsersList, "Les dauphins", "Salle I", 29, 3,21,"18h00"),
        new Reunion(FakeUsersList, "Les animaux", "Salle J", 30, 3,21,"19h00")
    ));

    static ArrayList<Reunion> generateReunion(){
        return new ArrayList<>(FakeReunions);
    }
}

