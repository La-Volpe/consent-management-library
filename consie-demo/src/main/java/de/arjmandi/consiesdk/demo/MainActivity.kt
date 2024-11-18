package de.arjmandi.consiesdk.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.arjmandi.consiesdk.demo.ui.theme.ConsieTheme
import de.arjmandi.consiesdk.ui.screens.ConsentOverviewScreen
import de.arjmandi.consiesdk.ui.states.ConsentCategoryCardState
import de.arjmandi.consiesdk.ui.states.ConsentOverviewScreenState
import de.arjmandi.consiesdk.ui.states.ConsentSwitchState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConsieTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                        onServiceToggle = { _, _, _ -> },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConsieTheme {
        Greeting("Android")
    }
}