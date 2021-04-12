package dfmareu.com.di;

import dfmareu.com.api.RealApiReunion;
import dfmareu.com.repository.ReunionRepository;

public class Injection {

    //FakeApiReunion is used for simulate data base
    public static ReunionRepository createReunionRepository() {
        return new ReunionRepository(new RealApiReunion());
    }
}
