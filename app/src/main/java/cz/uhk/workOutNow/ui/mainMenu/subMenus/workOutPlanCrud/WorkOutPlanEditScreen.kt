package cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutPlanCrud

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
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import cz.uhk.workOutNow.ui.*
import org.koin.androidx.compose.getViewModel

@Composable
fun WorkOutPlanEditScreen(
    parentController: NavHostController,
    id: Long,
    viewModel: WorkOutPlanEditViewModel = getViewModel()
) {

    val workOutPlan: State<TrainingListEntity> =
        viewModel.selectTrainingEntityForEdit(id).collectAsState(
            initial =
            TrainingListEntity(trainingListEntityId = 0, title = "", description = "", icon = "")
        )

    val title = remember { mutableStateOf("") }
    val helpTitle = remember { mutableStateOf("") }

    val description = remember { mutableStateOf("") }
    val helpDescription = remember { mutableStateOf("") }

    val icon = remember { mutableStateOf("") }
    val helpIcon = remember { mutableStateOf("") }

    var valueMenu by remember { mutableStateOf(false) }

    val items = listOf("Acrobatics", "Sprint", "Weights")
    var selectedItem by remember { mutableStateOf(items[0]) }

    // Ikony chci schovat, pokud bude zobrazena, pokud uživatel něco napíše
    val isVisible = isMenuVisible(title, description, helpTitle, helpDescription)

    Row(
        modifier = Modifier.offset(0.dp, 10.dp)

    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
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

    Column(
        modifier = Modifier.offset(60.dp, 20.dp)

    ) {

        Row(
            modifier = Modifier
                .offset(-(60.dp), 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Modify existing work out plan", modifier = Modifier.offset(0.dp, 0.dp)
            )
        }

        //z nějakého důvodu se proměnná workOutPlan.value... neukládá do mutableStateOf, řešeno takto amatérsky
        if (title.value == "" && helpTitle.value == "") {
            title.value = workOutPlan.value.title
        }

        if (description.value == "" && helpDescription.value == "") {
            description.value = workOutPlan.value.description
        }

        OutlinedTextField(value = title.value,
            onValueChange = {
                title.value = it
                helpTitle.value = "Done"
                helpDescription.value = "Done"
                helpIcon.value = "Done"
            },
            label = { Text("Set the title") })

        OutlinedTextField(value = description.value,
            onValueChange = {
                description.value = it
                helpTitle.value = "Done"
                helpDescription.value = "Done"
                helpIcon.value = "Done"
            },
            label = { Text("Set the description") })

        if (icon.value == "" && helpIcon.value == "") {
            selectedItem = workOutPlan.value.icon
        }

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
                    text = selectedItem, modifier = Modifier.offset(80.dp, 0.dp)
                )
            }

            DropdownMenu(
                expanded = valueMenu, onDismissRequest = { }, Modifier.offset(0.dp)

            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedItem = item
                        helpTitle.value = "Done"
                        helpDescription.value = "Done"
                        helpIcon.value = "Done"
                        valueMenu = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }

        //zobrazení ikony při vytváření plánu work outu
        Row(
            modifier = Modifier.offset(100.dp, 0.dp)
        ) {
            when (selectedItem) {
                "Acrobatics" -> Icon(
                    painterResource(id = R.drawable.workoutplanacrobatics),
                    "Acrobatics",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
                "Sprint" -> Icon(
                    painterResource(id = R.drawable.workoutplansprint),
                    "Sprint",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
                "Weights" -> Icon(
                    painterResource(id = R.drawable.workoutplanweights),
                    "Weights",
                    modifier = Modifier.offset(0.dp, 0.dp)
                )
            }
        }


        Row(
        ) {
            Button(
                onClick = {

                    viewModel.updateTrainingListEntity(
                        title.value,
                        workOutPlan.value.trainingListEntityId,
                        description.value,
                        icon.value
                    )

                    parentController.navigateWorkOutPlanMainMenu()
                },
                enabled = title.value.isNotEmpty() && description.value.isNotEmpty() && icon.value.isNotEmpty(),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(-20.dp, 0.dp)
            ) {
                Text("Save changes")
            }

            Button(
                onClick = {
                    title.value = ""
                    description.value = ""
                    icon.value = ""

                    helpTitle.value = "Done"
                    helpDescription.value = "Done"
                    helpIcon.value = "Done"
                },
                enabled = title.value.isNotEmpty() || description.value.isNotEmpty(),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(0.dp, 0.dp)
            ) {
                Text("Delete")
            }

            Button(
                onClick = {
                    parentController.navigateWorkOutPlanMainMenu()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(20.dp, 0.dp)
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

fun isMenuVisible(
    title: MutableState<String>,
    description: MutableState<String>,
    helpTitle: MutableState<String>,
    helpDescription: MutableState<String>
): Boolean {
    return !(title.value.isNotEmpty() && helpTitle.value == "Done" || description.value.isNotEmpty() && helpDescription.value == "Done")
}

