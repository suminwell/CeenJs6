package org.ceenjs.ceenjs.event

interface IEventEmitter {

    fun emitEvent(event: String, vararg args: Any?): EventResult

}

data class EventResult(val result: Any?, val callSuper: Boolean)