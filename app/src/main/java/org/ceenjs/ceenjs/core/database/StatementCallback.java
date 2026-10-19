package org.ceenjs.ceenjs.core.database;

public interface StatementCallback {

    void handleEvent(Transaction transaction, DatabaseResultSet resultSet);

}
