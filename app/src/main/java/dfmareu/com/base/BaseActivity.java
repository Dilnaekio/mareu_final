package dfmareu.com.base;

import androidx.appcompat.app.AppCompatActivity;

import dfmareu.com.MareuApplication;
import dfmareu.com.repository.ReunionRepository;

public abstract class BaseActivity extends AppCompatActivity {

    public ReunionRepository getReunionRepository() {
        return ((MareuApplication) getApplication()).getReunionRepository();
    }
}