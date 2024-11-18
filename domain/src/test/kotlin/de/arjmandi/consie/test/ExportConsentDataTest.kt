import de.arjmandi.consiesdk.domain.ConsentRepository
import de.arjmandi.consiesdk.domain.model.ConsentStatus
import de.arjmandi.consiesdk.domain.usecase.ExportConsentData
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ExportConsentDataTest {

    private val mockRepository: ConsentRepository = mockk()
    private val exportConsentData = ExportConsentData(mockRepository)

    @Test
    fun exportConsentData_repositoryHasNoData_returnsEmptyMap() {
        every { mockRepository.exportConsentData() } returns emptyMap()

        val result = exportConsentData()

        assertEquals(0, result.size)
    }

    @Test
    fun exportConsentData_repositoryThrowsException_propagatesException() {
        every { mockRepository.exportConsentData() } throws RuntimeException("Repository Error")
        try {
            exportConsentData()
            assert(false) { "Expected an exception to be thrown." }
        } catch (e: RuntimeException) {
            assertEquals("Repository Error", e.message)
        }
    }
}
