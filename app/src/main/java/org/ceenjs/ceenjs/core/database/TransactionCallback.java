package org.ceenjs.ceenjs.core.database;

public interface TransactionCallback {

    void handleEvent(Transaction transaction);

}
