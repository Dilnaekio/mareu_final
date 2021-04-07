package dfmareu.com.util;

import android.util.Patterns;

public class IsMailValid {
    public static boolean isMailValid(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return false;
        }
    }
}
