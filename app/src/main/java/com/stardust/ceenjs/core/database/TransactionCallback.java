package com.stardust.ceenjs.core.database;

public interface TransactionCallback {
    void handleEvent(Transaction transaction);
}
