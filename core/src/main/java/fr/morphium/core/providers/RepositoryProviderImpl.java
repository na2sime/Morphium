package fr.morphium.core.providers;

import fr.morphium.api.annotations.Entity;
import fr.morphium.api.annotations.services.ServiceProvider;
import fr.morphium.api.providers.Provider;
import fr.morphium.api.repository.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.*;

@ServiceProvider(Repository.class)
public class RepositoryProviderImpl implements Provider<Repository<?, ?>> {

    @Override
    public @NotNull Repository<?, ?> create(@NotNull Class<Repository<?, ?>> type) {
        if (!type.isInterface())
            throw new IllegalArgumentException("Repository must be an interface");
        ParameterizedType repositoryType = getRepositoryType(type);

        if (repositoryType != null) {
            // MyRepository extends Repository<Any, Any> {}
            Type[] args = repositoryType.getActualTypeArguments();

            if (!(args[0] instanceof Class<?> objectType))
                throw new IllegalArgumentException("Object type must be a Class");
            if (!objectType.isAnnotationPresent(Entity.class))
                throw new IllegalArgumentException("Object type must be annotated with @Entity");

            System.out.println("CREATE REPOSITORY: " + repositoryType + " " + objectType + " " + args[1]);

            return type.cast(Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return null;
                }
            }));
        }
        throw new IllegalArgumentException("Invalid repository");
    }

    private @Nullable ParameterizedType getRepositoryType(@NotNull Class<?> type) {
        for (Type interfaceType : type.getGenericInterfaces()) {
            if (!(interfaceType instanceof ParameterizedType parameterizedType)) continue;
            if (parameterizedType.getRawType() instanceof Class<?> rawType && Repository.class.isAssignableFrom(rawType))
                return parameterizedType;
        }
        return null;
    }
}
