package com.silentdev.whetherkmm.persentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp)
        ) {
            Text("Hyderabad", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("25°C", fontSize = 48.sp, fontWeight = FontWeight.ExtraBold)
            Text("Mostly sunny", fontSize = 18.sp, color = Color.Gray)

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoTile("UV Index", "7 High")
                InfoTile("Humidity", "61%")
                InfoTile("Precipitation", "4mm")
            }

            Spacer(Modifier.height(24.dp))
            Text("Next days", fontWeight = FontWeight.Medium)

            ForecastRow("22°", "Friday, 1 Nov", Icons.Default.Cloud)
            ForecastRow("19°", "Sunday, 3 Nov", Icons.Default.WaterDrop)
            ForecastRow("25°", "Saturday, 2 Nov", Icons.Default.Cloud)
            ForecastRow("20°", "Monday, 4 Nov", Icons.Default.WaterDrop)
        }
    }

    @Composable
    fun InfoTile(title: String, value: String) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier) {
            Text(title, color = Color.Gray)
            Text(value, fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    fun ForecastRow(temp: String, day: String, icon: ImageVector) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(temp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Text(day)
        }
    }
}
