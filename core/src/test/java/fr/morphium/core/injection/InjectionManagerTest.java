package fr.morphium.core.injection;

import fr.morphium.api.providers.Provider;
import fr.morphium.api.repository.Repository;
import static org.junit.jupiter.api.Assertions.*;

import fr.morphium.core.injection.entities.ClassRepository;
import fr.morphium.core.injection.entities.InvalidRepository;
import fr.morphium.core.injection.entities.ValidRepository;
import fr.morphium.core.providers.RepositoryProviderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InjectionManagerTest {

    private InjectionManager manager;

    @BeforeEach
    public void setup()  {
        manager = new InjectionManager();
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void getProvider() {
        Provider<Repository> provider = manager.getProvider(Repository.class).orElse(null);

        assertNotNull(provider);
        assertInstanceOf(RepositoryProviderImpl.class, provider);
    }

    @Test
    public void invalidCreate() {
        assertThrows(IllegalArgumentException.class, () -> manager.create(ClassRepository.class));
        assertThrows(IllegalArgumentException.class, () -> manager.create(InvalidRepository.class));
    }

    @Test
    public void create() {
        ValidRepository repository = assertDoesNotThrow(() -> manager.create(ValidRepository.class));

        System.out.println(repository);
    }
}
