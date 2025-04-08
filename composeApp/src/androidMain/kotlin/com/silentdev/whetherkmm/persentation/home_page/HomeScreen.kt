package com.silentdev.whetherkmm.persentation.home_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.silentdev.whetherkmm.R
import com.silentdev.whetherkmm.persentation.state.WeatherUiState

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { HomeScreenViewModel() }
        val state = screenModel.uiState.collectAsState()

        if (
            state.value is WeatherUiState.Success
        ) {

            val data = (state.value as WeatherUiState.Success).weather

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .alpha(0.8f)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Good Morning",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Start
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(data.current.location, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text(
                            data.current.temperatureCelsius,
                            fontSize = 52.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(data.current.weatherInfo, fontSize = 18.sp, color = Color.Gray)
                    }


                    Spacer(Modifier.height(16.dp))

                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.large
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            InfoTile(data.current.uv, "7 High")
                            InfoTile(data.current.humidity, "61%")
                            InfoTile(data.current.precipitation, "4mm")
                        }
                    }


                    Spacer(Modifier.height(24.dp))

                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Text("Next days", fontWeight = FontWeight.Normal)

                            data.forecast.forEach { forcast ->
                                ForecastRow(
                                    forcast.temperatureCelsius,
                                    forcast.date,
                                    Icons.Default.WaterDrop
                                )
                            }

//                            ForecastRow("22°", "Friday, 1 Nov", Icons.Default.Cloud)
//                            ForecastRow("19°", "Sunday, 3 Nov", Icons.Default.WaterDrop)
//                            ForecastRow("25°", "Saturday, 2 Nov", Icons.Default.Cloud)
//                            ForecastRow("20°", "Monday, 4 Nov", Icons.Default.WaterDrop)
                        }
                    }
                }
            }
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
