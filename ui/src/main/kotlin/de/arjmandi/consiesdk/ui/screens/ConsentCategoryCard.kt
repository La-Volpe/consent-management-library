package de.arjmandi.consiesdk.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.arjmandi.consiesdk.ui.components.ConsentSwitch
import de.arjmandi.consiesdk.ui.states.ConsentCategoryCardState
import de.arjmandi.consiesdk.ui.states.ConsentSwitchState

@Composable
fun ConsentCategoryCard(
    state: ConsentCategoryCardState,
    onServiceToggle: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = state.categoryName)
            state.services.forEach { serviceState ->
                ConsentSwitch(
                    state = serviceState,
                    onToggle = { isGranted -> onServiceToggle(serviceState.serviceName, isGranted) }
                )
            }
        }
    }
}

@Preview
@Composable
fun ConsentCategoryCardPreview() {
    Column {
        ConsentCategoryCard(
            state = ConsentCategoryCardState(
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
            onServiceToggle = { _, _ -> }
        )
        ConsentCategoryCard(
            state = ConsentCategoryCardState(
                categoryName = "Marketing",
                isMandatory = false,
                services = listOf(
                    ConsentSwitchState(
                        serviceName = "Facebook Ads",
                        isGranted = false,
                        isDisabled = false
                    ),
                    ConsentSwitchState(
                        serviceName = "Adjust",
                        isGranted = true,
                        isDisabled = false
                    )
                )
            ),
            onServiceToggle = { _, _ -> }
        )
    }
}