package cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutListOfTrainings.crud

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cz.uhk.workOutNow.R
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import cz.uhk.workOutNow.ui.*
import org.koin.androidx.compose.getViewModel

@Composable
fun TrainingCreateScreen(
    parentController: NavHostController,
    id: Long,
    viewModel: TrainingCreateViewModel = getViewModel()
) {

    val name = remember { mutableStateOf("") }
    val minutes = remember { mutableStateOf("") }
    val seconds = remember { mutableStateOf("") }
    val icon = remember { mutableStateOf("") }

    var displayWarningMessage = remember { mutableStateOf(false) }

    var valueMenu by remember { mutableStateOf(false) }

    val items = listOf("Plank", "Push Ups", "Resting", "Bench Press", "Weights")
    var selectedItem by remember { mutableStateOf(items[0]) }

    // Ikony chci schovat, pokud bude zobrazena, pokud uživatel něco napíše
    val isVisible = menuVisible(name, minutes, seconds)

    if (!displayWarningMessage.value) {
        Row(
            modifier = Modifier.offset(0.dp, 10.dp)

        ) {

            Canvas(
                modifier = Modifier.fillMaxWidth()
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
    } else {

        Row(
            modifier = Modifier
                .offset(0.dp, -3.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Minutes and seconds must be numbers (0-9)!",
                modifier = Modifier.offset(-(0.dp), (0.dp)),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFFDB0A13)
                )
            )
        }
    }

    Column(
        modifier = Modifier.offset(60.dp, 20.dp).fillMaxSize()

    ) {

        Row(

        ) {
            Text(
                text = "Create new training", modifier = Modifier.offset(50.dp, 0.dp)
            )
        }

        OutlinedTextField(value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Set the name") })

        OutlinedTextField(value = minutes.value,
            onValueChange = { minutes.value = it },
            label = { Text("Set the duration - minutes") })

        OutlinedTextField(value = seconds.value,
            onValueChange = { seconds.value = it },
            label = { Text("Set the duration - seconds") })

        icon.value = selectedItem

        Row(
            modifier = Modifier
                .offset(0.dp, 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            IconButton(onClick = {
                valueMenu = true
            }) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    "ArrowDropDown",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )

                Text(
                    text = "" + selectedItem, modifier = Modifier.offset(80.dp, 0.dp)
                )
            }

            DropdownMenu(
                expanded = valueMenu, onDismissRequest = { }, Modifier.offset(0.dp)

            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedItem = item
                        valueMenu = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .offset(100.dp, 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            //zobrazení ikony při vytváření plánu work outu
            when (icon.value) {
                "Plank" -> Icon(
                    painterResource(id = R.drawable.trainingplank),
                    "Plank",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
                "Push Ups" -> Icon(
                    painterResource(id = R.drawable.trainingpushups),
                    "Push Ups",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
                "Resting" -> Icon(
                    painterResource(id = R.drawable.trainingresting),
                    "Resting",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
                "Bench Press" -> Icon(
                    painterResource(id = R.drawable.trainingbenchpress),
                    "Bench Press",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
                "Weights" -> Icon(
                    painterResource(id = R.drawable.trainingweightsbig),
                    "Big Weights",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
            }
        }

        Row(
        ) {
            Button(
                onClick = {

                    // kontrola vstupních hodnot
                    val regex = Regex("[0-9]*")
                    if (!((regex.matches(minutes.value)) && (regex.matches(seconds.value)))) {
                        displayWarningMessage.value = true
                    } else {
                        val trainingEntity = TrainingEntity(
                            name = name.value,
                            minutes = minutes.value.toInt(),
                            seconds = seconds.value.toInt(),
                            icon = icon.value,
                            trainingListEntityId = id
                        )
                        viewModel.createTrainingListEntity(trainingEntity)
                        parentController.navigate("workOutPlan/${id}")
                    }
                },
                enabled = name.value.isNotEmpty()
                        && minutes.value.isNotEmpty()
                        && seconds.value.isNotEmpty()
                        && icon.value.isNotEmpty(),


                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(10.dp, -20.dp)
            ) {
                Text("Save")
            }

            Button(
                onClick = {
                    name.value = ""
                    minutes.value = ""
                    seconds.value = ""
                },
                enabled = name.value.isNotEmpty()
                        || minutes.value.isNotEmpty()
                        || seconds.value.isNotEmpty(),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(30.dp, -20.dp)
            ) {
                Text("Delete")
            }

            Button(
                onClick = {
                    parentController.navigate("workOutPlan/${id}")
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(50.dp, -20.dp)
            ) {
                Text("Back")
            }

        }
    }

    if (isVisible) {
        Row(
            modifier = Modifier.offset(0.dp, -(100.dp))
        ) {

            Canvas(
                modifier = Modifier.fillMaxSize()
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
                    painterResource(R.drawable.planmenufilled), contentDescription = "",
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
                    painterResource(R.drawable.settings), contentDescription = "",
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
                modifier = Modifier.fillMaxSize()
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
    }
}


fun menuVisible(
    name: MutableState<String>, minutes: MutableState<String>, seconds: MutableState<String>
): Boolean {
    return !(name.value.isNotEmpty() || minutes.value.isNotEmpty() || seconds.value.isNotEmpty())
}


