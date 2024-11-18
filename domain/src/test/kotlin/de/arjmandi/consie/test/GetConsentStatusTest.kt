package de.arjmandi.consie.test

import de.arjmandi.consiesdk.domain.ConsentRepository
import de.arjmandi.consiesdk.domain.model.ConsentCategory
import de.arjmandi.consiesdk.domain.model.ConsentService
import de.arjmandi.consiesdk.domain.model.ConsentStatus
import de.arjmandi.consiesdk.domain.usecase.GetConsentStatus
import org.junit.Assert.*

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class GetConsentStatusTest {

    private val mockRepository: ConsentRepository = mockk()
    private val getConsentStatus = GetConsentStatus(mockRepository)

    @Test
    fun getConsentCategories_repositoryHasData_returnsAllCategories() {
        val expectedCategories = listOf(
            ConsentCategory("Analytics", false, listOf(ConsentService("Firebase Analytics", ConsentStatus.PENDING))),
            ConsentCategory("Marketing", false, listOf(ConsentService("Facebook Ads", ConsentStatus.GRANTED)))
        )
        every { mockRepository.getConsentCategories() } returns expectedCategories

        val result = getConsentStatus()

        assertEquals(expectedCategories, result)
    }

    @Test
    fun getConsentCategories_repositoryHasNoData_returnsEmptyList() {
        every { mockRepository.getConsentCategories() } returns emptyList()
        val result = getConsentStatus()

        assertEquals(0, result.size)
    }

    @Test
    fun getConsentCategories_repositoryReturnsOnlyMandatoryCategories_returnsMandatoryCategories() {
        val expectedCategories = listOf(
            ConsentCategory("Essential", true, listOf(ConsentService("Firebase Messaging", ConsentStatus.GRANTED)))
        )
        every { mockRepository.getConsentCategories() } returns expectedCategories

        val result = getConsentStatus()

        assertEquals(expectedCategories, result)
    }

    @Test
    fun getConsentCategories_repositoryThrowsException_propagatesException() {
        every { mockRepository.getConsentCategories() } throws RuntimeException("Repository Error")

        try {
            getConsentStatus()
            assert(false) { "Expected an exception to be thrown." }
        } catch (e: RuntimeException) {
            assertEquals("Repository Error", e.message)
        }
    }
}
