package com.royaldigital.phonemonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF071426)
private val CardColor = Color(0xFF12243B)
private val Gold = Color(0xFFE5BC69)
private val White = Color(0xFFFFFFFF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PhoneMonitorApp()
        }
    }
}

data class AppUsage(
    val name: String,
    val duration: String
)

@Composable
fun PhoneMonitorApp() {

    val apps = listOf(
        AppUsage("Instagram", "Not tracked yet"),
        AppUsage("WhatsApp", "Not tracked yet"),
        AppUsage("YouTube", "Not tracked yet"),
        AppUsage("Chrome", "Not tracked yet")
    )

    var selectedTab by remember {
        mutableStateOf("Dashboard")
    }

    MaterialTheme {
        Scaffold(
            containerColor = Navy,
            bottomBar = {
                NavigationBar(
                    containerColor = CardColor
                ) {
                    listOf(
                        "Dashboard",
                        "Apps",
                        "History",
                        "Control"
                    ).forEach { tab ->
                        NavigationBarItem(
                            selected = selectedTab == tab,
                            onClick = {
                                selectedTab = tab
                            },
                            icon = {},
                            label = {
                                Text(tab)
                            },
                            colors = NavigationBarItemDefaults
                                .colors(
                                    selectedIconColor = Gold,
                                    selectedTextColor = Gold,
                                    indicatorColor = Navy,
                                    unselectedTextColor = White
                                )
                        )
                    }
                }
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Text(
                        "PHONE MONITOR",
                        color = Gold,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Personal Usage & Control",
                        color = White.copy(alpha = 0.7f)
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                CardColor,
                                RoundedCornerShape(20.dp)
                            )
                            .padding(22.dp)
                    ) {
                        Text(
                            "TODAY'S SCREEN TIME",
                            color = White.copy(alpha = 0.7f)
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            "-- h -- m",
                            color = Gold,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "Real tracking will start in Step 3",
                            color = White.copy(alpha = 0.6f),
                            fontSize = 12.sp
                        )
                    }
                }

                item {
                    Text(
                        selectedTab,
                        color = White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(apps) { app ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                CardColor,
                                RoundedCornerShape(14.dp)
                            )
                            .padding(18.dp),
                        horizontalArrangement =
                            Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            app.name,
                            color = White,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            app.duration,
                            color = Gold,
                            fontSize = 12.sp
                        )
                    }
                }

                item {
                    Text(
                        "ROYAL DIGITAL HUB",
                        color = Gold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                }
            }
        }
    }
}
