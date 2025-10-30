package com.example.ispine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
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
fun HomeScreen(
    modifier: Modifier = Modifier,
    onPostureButtonClick: () -> Unit = {},
    onConnectionStatusClick: () -> Unit = {}  // Add this new callback
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Row: Pie Chart and Device State
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PieChartSection(modifier = Modifier.weight(0.5f))
            Spacer(modifier = Modifier.width(16.dp))
            DeviceStateSection(modifier = Modifier.weight(0.5f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Row: Connection Status and Battery Status
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Make Connection Status clickable
            ConnectionStatusSection(
                modifier = Modifier.weight(0.5f),
                onClick = onConnectionStatusClick  // Pass the click callback
            )

            Spacer(modifier = Modifier.width(16.dp))

            BatteryStatusSection(modifier = Modifier.weight(0.5f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "Total Posture" Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onPostureButtonClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text("Total Posture")
            }
        }
    }
}

// Update ConnectionStatusSection to be clickable - KEEP THIS ONE
@Composable
fun ConnectionStatusSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}  // Add click handler
) {
    Card(
        onClick = onClick,  // Make the whole card clickable
        modifier = modifier.fillMaxHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Connection Status",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color.Green)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Connected",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Bluetooth LE",
                fontSize = 12.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Tap to manage →",  // Add hint that it's clickable
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PieChartSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Usage Analytics",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Simple Pie Chart representation
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pie Chart",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "75% Used",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DeviceStateSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Device State",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Device State Items
            DeviceStateItem("Status", "Connected", Color.Green)
            Spacer(modifier = Modifier.height(8.dp))
            DeviceStateItem("Signal", "Strong", Color.Blue)
            Spacer(modifier = Modifier.height(8.dp))
            DeviceStateItem("Mode", "Normal", Color.Magenta)
            Spacer(modifier = Modifier.height(8.dp))
            DeviceStateItem("Version", "v2.1.4", Color.Gray)
        }
    }
}

@Composable
fun DeviceStateItem(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = color
        )
    }
}

// DELETE THIS DUPLICATE ConnectionStatusSection FUNCTION - IT'S THE ONE WITHOUT onClick
/*
@Composable
fun ConnectionStatusSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Connection Status",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Connection status indicator
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color.Green)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Connected",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Bluetooth LE",
                fontSize = 12.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Last sync: 2 min ago",
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }
    }
}
*/

@Composable
fun BatteryStatusSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Battery Status",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Battery level indicator
            Column {
                Text(
                    text = "85%",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = 0.85f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = Color(0xFF4CAF50),
                    trackColor = Color.LightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Charging",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}