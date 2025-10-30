package com.example.ispine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.SignalWifi4Bar
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConnectionsScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Connection Management",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Connection Options
        ConnectionOptionCard(
            icon = Icons.Default.Bluetooth,
            title = "Bluetooth Devices",
            subtitle = "Connect to iSPINE device via Bluetooth",
            status = "Connected",
            statusColor = Color.Green,
            onClick = { /* Handle Bluetooth connection */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ConnectionOptionCard(
            icon = Icons.Default.Usb,
            title = "USB Connection",
            subtitle = "Connect via USB cable",
            status = "Available",
            statusColor = Color.Blue,
            onClick = { /* Handle USB connection */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ConnectionOptionCard(
            icon = Icons.Default.SignalWifi4Bar,
            title = "Wi-Fi Connection",
            subtitle = "Connect to iSPINE cloud services",
            status = "Connected",
            statusColor = Color.Green,
            onClick = { /* Handle Wi-Fi connection */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ConnectionOptionCard(
            icon = Icons.Default.Devices,
            title = "Paired Devices",
            subtitle = "Manage your connected devices",
            status = "2 Devices",
            statusColor = Color.Magenta,
            onClick = { /* Handle device management */ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Connection Status Summary
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Connection Summary",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                ConnectionStatusItem("Main Device", "iSPINE-Pro-001", "Connected", Color.Green)
                Spacer(modifier = Modifier.height(8.dp))
                ConnectionStatusItem("Signal Strength", "Excellent", "85%", Color.Blue)
                Spacer(modifier = Modifier.height(8.dp))
                ConnectionStatusItem("Last Sync", "2 minutes ago", "Successful", Color.Green)
                Spacer(modifier = Modifier.height(8.dp))
                ConnectionStatusItem("Battery", "85%", "Charging", Color(0xFF4CAF50))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Back Button
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Back to Home")
        }
    }
}

@Composable
fun ConnectionOptionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    status: String,
    statusColor: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

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

            Text(
                text = status,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = statusColor
            )
        }
    }
}

@Composable
fun ConnectionStatusItem(label: String, value: String, status: String, statusColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }

        Text(
            text = status,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = statusColor
        )
    }
}