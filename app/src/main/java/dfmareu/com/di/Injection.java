package dfmareu.com.di;

import dfmareu.com.api.FakeApiReunion;
import dfmareu.com.repository.ReunionRepository;

public class Injection {

    //FakeApiReunion is used for simulate data base
    public static ReunionRepository createReunionRepository() {
        return new ReunionRepository(new FakeApiReunion());
    }
}
