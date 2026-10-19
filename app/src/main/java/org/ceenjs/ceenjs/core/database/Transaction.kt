package org.ceenjs.ceenjs.core.database

class Transaction(val database: Database) {

    fun end() {
        database.endTransaction()
    }

    fun succeed() {
        database.setTransactionSuccessful()
    }

}
