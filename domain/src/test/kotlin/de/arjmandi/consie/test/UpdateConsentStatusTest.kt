package de.arjmandi.consie.test

import de.arjmandi.consiesdk.domain.ConsentRepository
import de.arjmandi.consiesdk.domain.model.ConsentStatus
import de.arjmandi.consiesdk.domain.usecase.UpdateConsentStatus
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UpdateConsentStatusTest {

    private val mockRepository: ConsentRepository = mockk()
    private val updateConsentStatus = UpdateConsentStatus(mockRepository)

    @Test
    fun updateConsent_validCategoryAndService_updatesStatusSuccessfully() {
        // Arrange
        val categoryName = "Analytics"
        val serviceName = "Firebase Analytics"
        val newStatus = ConsentStatus.GRANTED
        every { mockRepository.updateConsent(categoryName, serviceName, newStatus) } just runs

        // Act
        updateConsentStatus(categoryName, serviceName, newStatus)

        // Assert
        verify { mockRepository.updateConsent(categoryName, serviceName, newStatus) }
    }

    @Test
    fun updateConsent_invalidService_throwsException() {
        // Arrange
        val categoryName = "Analytics"
        val serviceName = "Invalid Service"
        val newStatus = ConsentStatus.DENIED
        every { mockRepository.updateConsent(categoryName, serviceName, newStatus) } throws IllegalArgumentException("Service not found")

        // Act & Assert
        try {
            updateConsentStatus(categoryName, serviceName, newStatus)
            assert(false) { "Expected an exception to be thrown." }
        } catch (e: IllegalArgumentException) {
            assertEquals("Service not found", e.message)
        }
    }

    @Test
    fun updateConsent_emptyCategory_throwsException() {
        // Arrange
        val categoryName = ""
        val serviceName = "Firebase Analytics"
        val newStatus = ConsentStatus.GRANTED
        every { mockRepository.updateConsent(categoryName, serviceName, newStatus) } throws IllegalArgumentException("Category not found")

        // Act & Assert
        try {
            updateConsentStatus(categoryName, serviceName, newStatus)
            assert(false) { "Expected an exception to be thrown." }
        } catch (e: IllegalArgumentException) {
            assertEquals("Category not found", e.message)
        }
    }

    @Test
    fun updateConsent_repositoryThrowsException_propagatesException() {
        // Arrange
        val categoryName = "Analytics"
        val serviceName = "Firebase Analytics"
        val newStatus = ConsentStatus.GRANTED
        every { mockRepository.updateConsent(categoryName, serviceName, newStatus) } throws RuntimeException("Repository Error")

        // Act & Assert
        try {
            updateConsentStatus(categoryName, serviceName, newStatus)
            assert(false) { "Expected an exception to be thrown." }
        } catch (e: RuntimeException) {
            assertEquals("Repository Error", e.message)
        }
    }
}
