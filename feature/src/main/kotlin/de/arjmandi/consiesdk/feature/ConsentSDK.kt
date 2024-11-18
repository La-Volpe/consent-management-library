package de.arjmandi.consiesdk.feature

import android.content.Context
import de.arjmandi.consiesdk.domain.ConsentRepository
import de.arjmandi.consiesdk.domain.model.ConsentCategory
import de.arjmandi.consiesdk.domain.model.ConsentStatus
import de.arjmandi.consiesdk.feature.di.featureModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.java.KoinJavaComponent.inject

object ConsentSDK {

    private var isInitialized = false

    private val consentRepository: ConsentRepository by inject(ConsentRepository::class.java)

    fun initialize(context: Context, categories: List<ConsentCategory>) {
        if (isInitialized) return

        validateCategories(categories)

        startKoin {
            androidContext(context)
            modules(featureModule(categories))
        }

        isInitialized = true
    }

    suspend fun updateConsents(consents: Map<String, ConsentStatus>) {
        checkInitialization()

        withContext(Dispatchers.IO) {
            consents.forEach { (serviceName, status) ->
                val category = consentRepository.getConsentCategories().find { category ->
                    category.services.any { it.name == serviceName }
                }

                if (category != null) {
                    consentRepository.updateConsent(
                        categoryName = category.name,
                        serviceName = serviceName,
                        status = status
                    )
                } else {
                    throw IllegalArgumentException("Service $serviceName not found in any consent category.")
                }
            }
        }
    }

    fun getConsents(): List<ConsentCategory> {
        checkInitialization()
        return consentRepository.getConsentCategories()
    }

    private fun validateCategories(categories: List<ConsentCategory>) {
        val categoryNames = mutableSetOf<String>()
        val serviceNames = mutableSetOf<String>()

        categories.forEach { category ->
            if (!categoryNames.add(category.name)) {
                throw IllegalArgumentException("Duplicate category name found: ${category.name}")
            }

            category.services.forEach { service ->
                if (!serviceNames.add(service.name)) {
                    throw IllegalArgumentException("Duplicate service name found: ${service.name}")
                }
            }
        }
    }

    private fun checkInitialization() {
        if (!isInitialized) {
            throw IllegalStateException("ConsentSDK is not initialized. Call ConsentSDK.initialize(context, categories) first.")
        }
    }

    fun shutDown() {
        if (!isInitialized) return
        stopKoin()
        isInitialized = false
    }
}