package fr.morphium.api.providers;

import org.jetbrains.annotations.NotNull;

public interface Provider<T> {

    @NotNull T create(@NotNull Class<T> type);

}

