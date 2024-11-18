package de.arjmandi.consiesdk.domain.usecase

import de.arjmandi.consiesdk.domain.ConsentRepository
import de.arjmandi.consiesdk.domain.model.ConsentStatus

class UpdateConsentStatus(private val repository: ConsentRepository) {
    operator fun invoke(categoryName: String, serviceName: String, status: ConsentStatus) {
        repository.updateConsent(categoryName, serviceName, status)
    }
}