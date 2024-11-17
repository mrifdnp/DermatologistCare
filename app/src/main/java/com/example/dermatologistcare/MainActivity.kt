package com.example.dermatologistcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dermatologistcare.ui.theme.DermatologistCareTheme
import com.example.dermatologistcare.ui.theme.fabColor
import com.rahad.riobottomnavigation.composables.RioBottomNavItemData
import com.rahad.riobottomnavigation.composables.RioBottomNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()

        }
    }
}

@Composable
fun MainScreen() {
    val items = listOf(
        R.drawable.ic_home,
        R.drawable.ic_home,
        R.drawable.ic_home,
        R.drawable.ic_home,
    )
    val labels = listOf(
        "Home",
        "Track",
        "Resource",
        "Explore",
    )
    val selectedIndex= rememberSaveable { mutableIntStateOf(0) }

    val buttons = items.mapIndexed { index, iconData ->
        RioBottomNavItemData(
            imageVector = ImageVector.vectorResource(iconData),
            selected = index == selectedIndex.intValue,
            onClick = {selectedIndex.intValue = index},
            label = labels[index]
        )
    }
    // Main Scaffold setup
    Scaffold(
        bottomBar = {
            BottomNavigationBar(buttons = buttons)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Handle the screen content based on the selected index
        ScreenContent(selectedIndex.intValue, modifier = Modifier.padding(innerPadding))
    }
}
@Composable
fun BottomNavigationBar(buttons: List<RioBottomNavItemData>) {
    RioBottomNavigation(
        fabIcon = ImageVector.vectorResource(id = R.drawable.ic_camera),
        buttons = buttons,
        fabSize = 70.dp,
        barHeight = 70.dp,
        selectedItemColor = fabColor,
        fabBackgroundColor = fabColor,
    )
}

@Composable
fun ScreenContent(selectedIndex: Int, modifier: Modifier = Modifier) {
    when (selectedIndex) {
        0 -> ShowText("This is Home")
        1 -> ShowText("This is Track")
        2 -> ShowText("This is Resource")
        3 -> ShowText("This is Explore")
    }
}

@Composable
fun ShowText(screenName: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            screenName,
            style = MaterialTheme.typography.titleLarge
        )
    }
}