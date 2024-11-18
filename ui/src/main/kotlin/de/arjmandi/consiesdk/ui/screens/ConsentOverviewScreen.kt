package de.arjmandi.consiesdk.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.arjmandi.consiesdk.ui.states.ConsentCategoryCardState
import de.arjmandi.consiesdk.ui.states.ConsentOverviewScreenState
import de.arjmandi.consiesdk.ui.states.ConsentSwitchState

@Composable
fun ConsentOverviewScreen(
    state: ConsentOverviewScreenState,
    onServiceToggle: (String, String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(state.categories.size) { index ->
            ConsentCategoryCard(
                state = state.categories[index],
                onServiceToggle = { serviceName, isGranted ->
                    onServiceToggle(state.categories[index].categoryName, serviceName, isGranted)
                }
            )
        }
    }
}
@Preview
@Composable
fun ConsentOverviewScreenPreview() {
    ConsentOverviewScreen(
        state = ConsentOverviewScreenState(
            categories = listOf(
                ConsentCategoryCardState(
                    categoryName = "Analytics",
                    isMandatory = true,
                    services = listOf(
                        ConsentSwitchState(
                            serviceName = "Firebase Analytics",
                            isGranted = true,
                            isDisabled = false
                        ),
                        ConsentSwitchState(
                            serviceName = "Mixpanel",
                            isGranted = false,
                            isDisabled = false
                        )
                    )
                ),
                ConsentCategoryCardState(
                    categoryName = "Marketing",
                    isMandatory = false,
                    services = listOf(
                        ConsentSwitchState(
                            serviceName = "Facebook Ads",
                            isGranted = true,
                            isDisabled = false
                        ),
                        ConsentSwitchState(
                            serviceName = "Google Ads",
                            isGranted = false,
                            isDisabled = false
                        )
                    )
                )
            )
        ),
        onServiceToggle = { _, _, _ -> }
    )
}