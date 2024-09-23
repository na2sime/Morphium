package fr.morphium.core.injection.entities;

import fr.morphium.api.repository.Repository;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClassRepository implements Repository<Object, Object> {

    @Override
    public @NotNull CompletableFuture<Object> findById(@NotNull Object o) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public @NotNull CompletableFuture<Object> save(@NotNull Object entity) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public @NotNull CompletableFuture<Void> delete(@NotNull Object entity) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public @NotNull CompletableFuture<List<Object>> findAll() {
        return CompletableFuture.completedFuture(null);
    }
}
