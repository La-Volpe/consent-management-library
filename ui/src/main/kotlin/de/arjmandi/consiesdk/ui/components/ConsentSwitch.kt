package de.arjmandi.consiesdk.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.arjmandi.consiesdk.ui.states.ConsentSwitchState


@Composable
fun ConsentSwitch(
    state: ConsentSwitchState,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.serviceName,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = state.isGranted,
            onCheckedChange = if (state.isDisabled) null else onToggle,
            enabled = !state.isDisabled
        )
    }
}

@Preview
@Composable
fun ConsentSwitchPreview() {
    ConsentSwitch(
        state = ConsentSwitchState(
            serviceName = "Firebase Analytics",
            isGranted = true,
            isDisabled = false
        ),
        onToggle = { }
    )
}