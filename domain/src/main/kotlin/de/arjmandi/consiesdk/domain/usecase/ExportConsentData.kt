package de.arjmandi.consiesdk.domain.usecase

import de.arjmandi.consiesdk.domain.ConsentRepository

class ExportConsentData(private val repository: ConsentRepository) {
    operator fun invoke(): Map<String, String> {
        return repository.exportConsentData().mapValues { it.value.name }
    }
}