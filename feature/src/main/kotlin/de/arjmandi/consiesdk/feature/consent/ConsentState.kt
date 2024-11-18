package de.arjmandi.consiesdk.feature.consent

import de.arjmandi.consiesdk.domain.model.ConsentCategory

data class ConsentState(
    val categories: List<ConsentCategory> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)