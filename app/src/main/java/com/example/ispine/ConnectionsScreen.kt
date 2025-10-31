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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothConnected
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.SignalCellular4Bar
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConnectionsScreen(onBackClick: () -> Unit) {
    // Simulated connection state - change to false to see disconnected state
    var isConnected by remember { mutableStateOf(true) }
    var isScanning by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Device Connection",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            // Connection status indicator
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(if (isConnected) Color.Green else Color.Red)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isConnected) "Connected" else "Disconnected",
                    fontSize = 14.sp,
                    color = if (isConnected) Color.Green else Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isConnected) {
            // Connected Device View
            ConnectedDeviceView(
                onDisconnect = { isConnected = false },
                onSync = { /* Handle sync */ }
            )
        } else {
            // Disconnected - Show available devices
            DisconnectedDevicesView(
                isScanning = isScanning,
                onScanToggle = { isScanning = !isScanning },
                onConnect = { isConnected = true }
            )
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
fun ConnectedDeviceView(
    onDisconnect: () -> Unit,
    onSync: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Device Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.BluetoothConnected,
                    contentDescription = "Connected",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Green
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "iSPINE Pro Device",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "ID: ISP-2024-001",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Device Info Cards
            Row(modifier = Modifier.fillMaxWidth()) {
                // Battery Info
                InfoCard(
                    title = "Battery",
                    value = "85%",
                    icon = Icons.Default.BatteryFull,
                    iconColor = Color(0xFF4CAF50),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Connection Strength
                InfoCard(
                    title = "Signal",
                    value = "Strong",
                    icon = Icons.Default.SignalCellular4Bar,
                    iconColor = Color.Blue,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Battery Progress
            Text(
                text = "Battery Level",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = 0.85f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color(0xFF4CAF50),
                trackColor = Color.LightGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onSync,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = "Sync",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sync Device")
                }

                Spacer(modifier = Modifier.width(12.dp))

                OutlinedButton(
                    onClick = onDisconnect,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Disconnect")
                }
            }
        }
    }
}

@Composable
fun DisconnectedDevicesView(
    isScanning: Boolean,
    onScanToggle: () -> Unit,
    onConnect: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Scan Button
        Button(
            onClick = onScanToggle,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            if (isScanning) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Scanning...")
            } else {
                Icon(
                    imageVector = Icons.Default.Bluetooth,
                    contentDescription = "Scan",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Scan for Devices")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Available Devices Section
        Text(
            text = "AVAILABLE DEVICES",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(getAvailableDevices()) { device ->
                DeviceItem(
                    device = device,
                    onConnect = onConnect
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Previously Connected Devices Section
        Text(
            text = "PREVIOUSLY CONNECTED",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(getPreviousDevices()) { device ->
                DeviceItem(
                    device = device,
                    onConnect = onConnect
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun DeviceItem(
    device: BluetoothDevice,
    onConnect: () -> Unit
) {
    Card(
        onClick = onConnect,
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
                Icon(
                    imageVector = Icons.Default.Bluetooth,
                    contentDescription = "Bluetooth",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Blue
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = device.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = device.id,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }

            // Signal strength indicator
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Wifi,
                    contentDescription = "Signal",
                    modifier = Modifier.size(16.dp),
                    tint = when (device.signalStrength) {
                        "Strong" -> Color.Green
                        "Medium" -> Color.Yellow
                        else -> Color.Red
                    }
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = device.signalStrength,
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = iconColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color.DarkGray
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Data classes and sample data
data class BluetoothDevice(
    val name: String,
    val id: String,
    val signalStrength: String // "Strong", "Medium", "Weak"
)

fun getAvailableDevices(): List<BluetoothDevice> {
    return listOf(
        BluetoothDevice("iSPINE Pro", "ISP-2024-002", "Strong"),
        BluetoothDevice("iSPINE Lite", "ISP-2024-003", "Medium"),
        BluetoothDevice("Smart Back Brace", "SBB-2024-001", "Weak")
    )
}

fun getPreviousDevices(): List<BluetoothDevice> {
    return listOf(
        BluetoothDevice("iSPINE Pro", "ISP-2024-001", "Strong"),
        BluetoothDevice("Office Chair Sensor", "OCS-2023-005", "Medium")
    )
}