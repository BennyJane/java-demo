package org.exmaple.com.actualProject;

import java.util.UUID;

public interface AsyncTask<T> {

    default String taskName() {
        return UUID.randomUUID().toString();
    }

    T doExecute();
}
