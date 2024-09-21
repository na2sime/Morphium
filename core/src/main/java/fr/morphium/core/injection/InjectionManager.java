package fr.morphium.core.injection;

import fr.morphium.api.annotations.services.ServiceProvider;
import fr.morphium.api.providers.Provider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class InjectionManager {

    private final Map<Class<?>, Object> cached;

    @SuppressWarnings("rawtypes")
    private ServiceLoader<Provider> providerServiceLoader;

    public InjectionManager() {
        cached = new WeakHashMap<>();
    }

    public <T> T getInstance(Class<T> type) {
        return type.cast(cached.computeIfAbsent(type, this::create));
    }

    @SuppressWarnings("unchecked")
    public <T> @NotNull Optional<Provider<T>> getProvider(@NotNull Class<T> type) {
        if (providerServiceLoader == null)
            providerServiceLoader = ServiceLoader.load(Provider.class);

        return providerServiceLoader.stream()
                .filter(provider -> {
                    ServiceProvider serviceProvider = provider.type().getDeclaredAnnotation(ServiceProvider.class);

                    if (serviceProvider == null) return false;
                    return serviceProvider.value().isAssignableFrom(type);
                }).findFirst()
                .map(ServiceLoader.Provider::get)
                .map(provider -> (Provider<T>) provider);
    }

    public <T> @Nullable T create(@NotNull Class<T> type) {
        return getProvider(type)
                .map(provider -> provider.create(type))
                .map(type::cast)
                .orElse(null);
    }
}
