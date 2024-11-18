package de.arjmandi.consiesdk.ui.states

data class ConsentSwitchState(
    val serviceName: String,
    val isGranted: Boolean,
    val isDisabled: Boolean = false
)