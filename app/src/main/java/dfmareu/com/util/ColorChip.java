package dfmareu.com.util;

import dfmareu.com.R;
import dfmareu.com.models.Reunion;

public class ColorChip {

    public static int setColorChip(Reunion reunion){
       String roomLetter = reunion.getRoom();
       int color;
       switch (roomLetter) {
           case "Salle B": color = R.drawable.pastille_2;
               break;
           case "Salle C": color = R.drawable.pastille_3;
               break;
           case "Salle D": color = R.drawable.pastille_4;
               break;
           case "Salle E": color = R.drawable.pastille_5;
               break;
           case "Salle F": color = R.drawable.pastille_6;
               break;
           case "Salle G": color = R.drawable.pastille_7;
               break;
           case "Salle H": color = R.drawable.pastille_8;
               break;
           case "Salle I": color = R.drawable.pastille_9;
               break;
           case "Salle J": color = R.drawable.pastille_10;
               break;
           default:
               color = R.drawable.pastille_1;
       }
       return color;
    }
}
