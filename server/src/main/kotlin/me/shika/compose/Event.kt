package me.shika.compose

data class EventPayload<T : Event.Payload<*>>(val targetId: Long, val payload: T)

interface Event {
    val type: String

    interface Payload<E : Event> {
        val descriptor: E
    }

    interface Callback<E : Event, P : Payload<E>> {
        val descriptor: E
        val onReceive: (payload: P) -> Unit
    }
}

object Click : Event {
    override val type: String = "click"

    object Payload : Event.Payload<Click> {
        override val descriptor: Click = Click
    }

    class Callback(override val onReceive: (payload: Payload) -> Unit) : Event.Callback<Click, Payload> {
        override val descriptor: Click = Click
    }
}

object InputChange : Event {
    override val type: String = "change"

    data class Payload(val value: String) : Event.Payload<InputChange> {
        override val descriptor: InputChange = InputChange
    }

    class Callback(override val onReceive: (payload: Payload) -> Unit) : Event.Callback<InputChange, Payload> {
        override val descriptor: InputChange = InputChange
    }
}

object Input : Event {
    override val type: String = "input"

    data class Payload(val value: String) : Event.Payload<Input> {
        override val descriptor: Input = Input
    }

    class Callback(override val onReceive: (payload: Payload) -> Unit) : Event.Callback<Input, Payload> {
        override val descriptor: Input = Input
    }
}