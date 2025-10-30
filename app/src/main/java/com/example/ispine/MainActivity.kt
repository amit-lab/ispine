package com.example.ispine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.ispine.ui.theme.ISPINETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ISPINETheme {
                ISPINEApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun ISPINEApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var showPostureScreen by rememberSaveable { mutableStateOf(false) }

    if (showPostureScreen) {
        PostureScreen(onBackClick = { showPostureScreen = false })
    } else {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach { destination ->
                    item(
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.label
                            )
                        },
                        label = { Text(destination.label) },
                        selected = destination == currentDestination,
                        onClick = { currentDestination = destination }
                    )
                }
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (currentDestination) {
                    AppDestinations.HOME -> HomeScreen(
                        modifier = Modifier.fillMaxSize(),
                        onPostureButtonClick = { showPostureScreen = true }
                    )
                    AppDestinations.SETTINGS -> SettingsScreen(modifier = Modifier.fillMaxSize())
                    AppDestinations.DEVICE -> DeviceScreen(modifier = Modifier.fillMaxSize())
                    AppDestinations.CUSTOMER_CARE -> CustomerCareScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    SETTINGS("Setting", Icons.Default.Settings),
    DEVICE("Device", Icons.Default.PhoneAndroid),
    CUSTOMER_CARE("Customer Care", Icons.Filled.Headset),
}

// Add these new Composable functions for each screen
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Home Screen",
        modifier = modifier
    )
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Settings Screen",
        modifier = modifier
    )
}

@Composable
fun DeviceScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Device Screen",
        modifier = modifier
    )
}

@Composable
fun CustomerCareScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Customer Care Screen",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ISPINETheme {
        ISPINEApp()
    }
}