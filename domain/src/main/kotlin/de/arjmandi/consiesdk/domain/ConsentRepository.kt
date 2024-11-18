package de.arjmandi.consiesdk.domain

import de.arjmandi.consiesdk.domain.model.ConsentCategory
import de.arjmandi.consiesdk.domain.model.ConsentStatus

interface ConsentRepository {
    fun getConsentCategories(): List<ConsentCategory>
    fun updateConsent(categoryName: String, serviceName: String, status: ConsentStatus)
    fun exportConsentData(): Map<String, ConsentStatus>
}