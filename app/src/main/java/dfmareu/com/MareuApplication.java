package dfmareu.com;

import android.app.Application;

import androidx.annotation.VisibleForTesting;

import dfmareu.com.di.Injection;
import dfmareu.com.repository.ReunionRepository;

public class MareuApplication extends Application {

    //This class is used for instance ReunionRepository once for all
    private ReunionRepository reunionRepository;

    public ReunionRepository getReunionRepository() {
        if (reunionRepository == null) reunionRepository = Injection.createReunionRepository();
        return reunionRepository;
    }

    @VisibleForTesting
    public void resetUserRepository() {
        reunionRepository = null;
    }
}