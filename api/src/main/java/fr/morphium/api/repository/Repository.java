package fr.morphium.api.repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Repository<T, ID> {

    @NotNull CompletableFuture<T> findById(@NotNull ID id);

    @NotNull CompletableFuture<T> save(@NotNull T entity);

    @NotNull CompletableFuture<Void> delete(@NotNull T entity);

    @NotNull CompletableFuture<List<T>> findAll();

}
