package de.arjmandi.consiesdk.feature.consent

import de.arjmandi.consiesdk.domain.model.ConsentCategory

fun List<ConsentCategory>.toFeatureState(): List<ConsentState> {
    return map { category ->
        ConsentState(
            categories = listOf(category),
            isLoading = false,
            errorMessage = null
        )
    }
}