package com.example.ispine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onCalibrationClick: () -> Unit = {},
    onDeviceConnectionClick: () -> Unit = {}
) {
    var dndMode by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(true) }
    var vibrationEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Text(
            text = "Settings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Notification Settings Section
        Text(
            text = "NOTIFICATION SETTINGS",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        SettingItemCard(
            title = "DND Mode",
            subtitle = "Silence all notifications and alerts",
            icon = Icons.Default.Notifications,
            iconColor = Color.Red,
            trailingContent = {
                Switch(
                    checked = dndMode,
                    onCheckedChange = { dndMode = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color.Red,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.Gray
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingItemCard(
            title = "Notifications",
            subtitle = "Enable push notifications",
            icon = Icons.Default.Notifications,
            iconColor = MaterialTheme.colorScheme.primary,
            trailingContent = {
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingItemCard(
            title = "Sound",
            subtitle = "Enable sound alerts",
            icon = Icons.Default.VolumeUp,
            iconColor = MaterialTheme.colorScheme.primary,
            trailingContent = {
                Switch(
                    checked = soundEnabled,
                    onCheckedChange = { soundEnabled = it }
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingItemCard(
            title = "Vibration",
            subtitle = "Enable vibration alerts",
            icon = Icons.Default.Warning,
            iconColor = MaterialTheme.colorScheme.primary,
            trailingContent = {
                Switch(
                    checked = vibrationEnabled,
                    onCheckedChange = { vibrationEnabled = it }
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Device Settings Section
        Text(
            text = "DEVICE SETTINGS",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        SettingItemCard(
            title = "Calibration",
            subtitle = "Calibrate your iSPINE device",
            icon = Icons.Default.Science,
            iconColor = Color.Blue,
            onClick = onCalibrationClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingItemCard(
            title = "Device Connection",
            subtitle = "Manage device connections",
            icon = Icons.Default.Bluetooth,
            iconColor = Color.Green,
            onClick = onDeviceConnectionClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Other Settings Section
        Text(
            text = "OTHER SETTINGS",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        SettingItemCard(
            title = "App Preferences",
            subtitle = "Customize app behavior",
            icon = Icons.Default.Settings,
            iconColor = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingItemCard(
            title = "Data & Privacy",
            subtitle = "Manage your data and privacy settings",
            icon = Icons.Default.Settings,
            iconColor = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingItemCard(
            title = "About",
            subtitle = "App version and information",
            icon = Icons.Default.Settings,
            iconColor = MaterialTheme.colorScheme.primary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingItemCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    onClick: (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Card(
        onClick = onClick ?: {},
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        enabled = onClick != null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(24.dp),
                    tint = iconColor
                )

                Column {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }

            // Show trailing content if provided (switch, arrow, etc.)
            trailingContent?.invoke()
        }
    }
}