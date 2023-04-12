package cz.uhk.workOutNow.ui.mainMenu

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cz.uhk.workOutNow.R
import cz.uhk.workOutNow.ui.navigateDestinationSettings
import cz.uhk.workOutNow.ui.navigateHomeScreen
import cz.uhk.workOutNow.ui.navigateWorkOutPlanMainMenu
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    parentController: NavHostController,
    viewModel: SettingsViewModel = getViewModel()
) {

    val currentSetting = viewModel.sellectSetting()

    var valueMenu by remember { mutableStateOf(false) }
    val items = listOf("On", "Off")
    var selectedItem by remember { mutableStateOf(items[0]) }

    LaunchedEffect(currentSetting) {
        currentSetting.collect { currentSettingSelected ->
            selectedItem = currentSettingSelected.notificationsSettings.toString()
        }
    }

    Row(
        modifier = Modifier.offset(0.dp, 10.dp)

    ) {

        Canvas(
            modifier =
            Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width
            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = canvasWidth, y = 0f),
                color = Color(0xFFDB0A13),
                strokeWidth = 10f
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(0.dp, (25.dp)),
        contentAlignment = Alignment.TopCenter
    )
    {
        Text(
            text = "Settings",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                letterSpacing = 0.sp,
                shadow = Shadow(
                    color = Color.Gray, Offset(5.0f, 10.0f), blurRadius = 3f
                )

            )
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, (70.dp)),
        horizontalArrangement = Arrangement.Center
    )
    {
        Text(
            text = "Notifications after completed exercise?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp,
                letterSpacing = 0.sp,
                shadow = Shadow(
                    color = Color.LightGray, Offset(5.0f, 10.0f), blurRadius = 3f
                ),
                color = Color(0xFFFFDB0A13)


            )
        )
    }

    Row(
        modifier = Modifier
            .offset(0.dp, 100.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = {
            valueMenu = true
        }) {
            Icon(
                Icons.Default.ArrowDropDown,
                "ArrowDropDown",
                modifier = Modifier.offset(-(80.dp), 0.dp)
            )
            if (selectedItem == "1") {
                Text(
                    text = "On", modifier = Modifier.offset(0.dp, 0.dp)
                )
            } else {
                Text(
                    text = "Off", modifier = Modifier.offset(0.dp, 0.dp)
                )
            }
        }
    }

    Row(
        modifier = Modifier
            .offset(180.dp, 140.dp)
            .fillMaxWidth()
    ) {

        DropdownMenu(
            expanded = valueMenu, onDismissRequest = { }, Modifier.offset(0.dp, 0.dp)

        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {

                    selectedItem = item
                    valueMenu = false

                    if (selectedItem == "On") {
                        viewModel.editSettings(1)
                    }

                    if (selectedItem == "Off") {
                        viewModel.editSettings(0)
                    }

                }) {
                    Text(text = item)
                }
            }
        }
    }


    Row(
        modifier = Modifier.offset(0.dp, -(100.dp))

    ) {

        Canvas(
            modifier =
            Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                start = Offset(x = 0f, y = canvasHeight),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = Color(0xFFDB0A13),
                strokeWidth = 10f
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(10.dp, -(18.dp)),
        contentAlignment = Alignment.BottomStart,
    ) {
        IconButton(onClick = {
            parentController.navigateHomeScreen()
        }) {

            Icon(
                painterResource(R.drawable.housebig), contentDescription = "",
                modifier = Modifier.offset(-(10.dp), (4.dp)),
                tint = Color.Unspecified // toto nastavení zachová původní barvu
            )

            Text(
                text = "Home",
                modifier = Modifier.offset(-(10.dp), (41.dp)),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF000000)
                )
            )

        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(-0.dp, (28.dp)),
        contentAlignment = Alignment.BottomCenter,
    ) {
        IconButton(onClick = {
            parentController.navigateWorkOutPlanMainMenu()
        }) {
            Icon(
                painterResource(R.drawable.planmenu), contentDescription = "",
                modifier = Modifier.offset(-(15.dp), (0.dp)),
                tint = Color.Unspecified // toto nastavení zachová původní barvu
            )

            Text(
                text = "Set up plan",
                modifier = Modifier.offset(-(20.dp), (35.dp)),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF000000)
                )
            )

        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(0.dp, (0.dp)),
        contentAlignment = Alignment.BottomEnd,
    ) {
        IconButton(onClick = {
            parentController.navigateDestinationSettings()
        }) {
            Icon(
                painterResource(R.drawable.settingsfilled), contentDescription = "",
                modifier = Modifier.offset(0.dp, (10.dp)),
                tint = Color.Unspecified // toto nastavení zachová původní barvu
            )

            Text(
                text = "Settings",
                modifier = Modifier.offset(0.dp, (50.dp)),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF000000)
                )
            )
        }
    }

    Row(
        modifier = Modifier.offset(0.dp, -(10.dp))

    ) {

        Canvas(
            modifier =
            Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                start = Offset(x = 0f, y = canvasHeight),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = Color(0xFFFFDB0A13),
                strokeWidth = 10f
            )
        }
    }
}

