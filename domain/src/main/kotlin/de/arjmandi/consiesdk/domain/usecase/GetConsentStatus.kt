package de.arjmandi.consiesdk.domain.usecase

import de.arjmandi.consiesdk.domain.ConsentRepository

class GetConsentStatus(private val repository: ConsentRepository) {
    operator fun invoke() = repository.getConsentCategories()
}