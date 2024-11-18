package de.arjmandi.consiesdk.feature.di

import android.content.Context
import android.content.SharedPreferences
import de.arjmandi.consiesdk.domain.ConsentRepository
import de.arjmandi.consiesdk.domain.model.ConsentCategory
import de.arjmandi.consiesdk.domain.usecase.ExportConsentData
import de.arjmandi.consiesdk.domain.usecase.GetConsentStatus
import de.arjmandi.consiesdk.domain.usecase.UpdateConsentStatus
import de.arjmandi.consiesdk.feature.consent.ConsentViewModel
import de.arjmandi.consiesdk.feature.repository.SharedPrefsConsentRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun featureModule(categories: List<ConsentCategory>) = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences("consent_preferences", Context.MODE_PRIVATE)
    }
    single<ConsentRepository> { SharedPrefsConsentRepository(preferences = get(), categories) }
    factory { GetConsentStatus(repository = get()) }
    factory { UpdateConsentStatus(repository = get()) }
    factory { ExportConsentData(repository = get()) }

    viewModel { ConsentViewModel(getConsentStatus = get(), updateConsentStatus = get()) }
}