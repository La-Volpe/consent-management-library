package de.arjmandi.consiesdk.domain.model

data class ConsentCategory(
    val name: String,
    val isMandatory: Boolean, // e.g., "Essential" services
    val services: List<ConsentService>
)