package de.arjmandi.consiesdk.feature.repository

import android.content.Context
import android.content.SharedPreferences
import de.arjmandi.consiesdk.domain.ConsentRepository
import de.arjmandi.consiesdk.domain.model.ConsentCategory
import de.arjmandi.consiesdk.domain.model.ConsentStatus

class SharedPrefsConsentRepository(
    private val preferences: SharedPreferences,
    private val defaultCategories: List<ConsentCategory>
) : ConsentRepository {

    override fun getConsentCategories(): List<ConsentCategory> {
        return defaultCategories.map { category ->
            category.copy(
                services = category.services.map { service ->
                    val status = preferences.getString("${category.name}_${service.name}", null)?.let {
                        ConsentStatus.valueOf(it)
                    } ?: service.status
                    service.copy(status = status)
                }
            )
        }
    }

    override fun updateConsent(categoryName: String, serviceName: String, status: ConsentStatus) {
        preferences.edit()
            .putString("${categoryName}_${serviceName}", status.name)
            .apply()
    }

    override fun exportConsentData(): Map<String, ConsentStatus> {
        return defaultCategories.flatMap { category ->
            category.services.map { service ->
                service.name to (preferences.getString("${category.name}_${service.name}", null)?.let {
                    ConsentStatus.valueOf(it)
                } ?: service.status)
            }
        }.toMap()
    }
}