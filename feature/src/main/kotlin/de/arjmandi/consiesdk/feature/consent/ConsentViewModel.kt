package de.arjmandi.consiesdk.feature.consent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.arjmandi.consiesdk.domain.model.ConsentStatus
import de.arjmandi.consiesdk.domain.usecase.GetConsentStatus
import de.arjmandi.consiesdk.domain.usecase.UpdateConsentStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConsentViewModel(
    private val getConsentStatus: GetConsentStatus,
    private val updateConsentStatus: UpdateConsentStatus
) : ViewModel() {

    private val _state = MutableStateFlow(ConsentState())
    val state: StateFlow<ConsentState> get() = _state

    init {
        loadConsentCategories()
    }

    private fun loadConsentCategories() {
        viewModelScope.launch {
            try {
                val categories = getConsentStatus()
                _state.value = ConsentState(categories = categories, isLoading = false)
            } catch (e: Exception) {
                _state.value = ConsentState(isLoading = false, errorMessage = e.message)
            }
        }
    }

    fun updateConsent(categoryName: String, serviceName: String, status: ConsentStatus) {
        viewModelScope.launch {
            updateConsentStatus(categoryName, serviceName, status)
            loadConsentCategories() // Reload to reflect updated state
        }
    }
}