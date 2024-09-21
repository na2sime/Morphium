package fr.morphium.api.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Map;

@AllArgsConstructor
public class Schema {

    private final String tableName;
    private final Map<String, SchemaField> fields;

    interface SchemaField {

        SchemaFieldCaracteristics caracteristics();

    }

    @Getter
    @Accessors(fluent = true)
    public static class SchemaFieldImpl implements SchemaField {

        private int length;
        private boolean nullable;
        private boolean unique;

        private SchemaFieldCaracteristics caracteristics;

    }

    interface SchemaFieldCaracteristics {}
}
