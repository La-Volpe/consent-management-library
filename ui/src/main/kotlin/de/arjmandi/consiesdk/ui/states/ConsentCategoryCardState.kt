package de.arjmandi.consiesdk.ui.states

data class ConsentCategoryCardState(
    val categoryName: String,
    val isMandatory: Boolean,
    val services: List<ConsentSwitchState>
)