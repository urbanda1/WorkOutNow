package cz.uhk.workOutNow.ui.mainMenu

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cz.uhk.workOutNow.R
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import cz.uhk.workOutNow.ui.*
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun LaunchTrainingScreen(
    parentController: NavHostController,
    id: Long,
    viewModel: LaunchTrainingViewModel = getViewModel()
) {

    val trainings = viewModel.selectAllTrainings(id).collectAsState(initial = emptyList()).value

    val trainingList = viewModel.selectTrainingPlan(id)
        .collectAsState(initial = TrainingListEntity(0, "", "", "")).value

    //timer
    var timeLeft = remember { mutableStateOf(1) }

    var isRunning = remember { mutableStateOf(false) }

    var firstRun = remember { mutableStateOf(true) }

    var iterationRun = remember { mutableStateOf(-1) }

    //časovač
    LaunchedEffect(isRunning.value) {
        while (isRunning.value) {


            if (timeLeft.value == 0 && iterationRun.value < trainings.size - 1) {

                iterationRun.value++

                timeLeft.value =
                    trainings.get(iterationRun.value).minutes * 60 + trainings.get(iterationRun.value).seconds

                //přepnutí UI
                firstRun.value = false
            }

            if (timeLeft.value == 0 && iterationRun.value == trainings.size - 1) {
                parentController.navigateWorkOutPlanMainMenu()
            }

            timeLeft.value--
            delay(1000)
        }
    }

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

    if (firstRun.value) {

        Column(
        ) {
            Row(
                modifier = Modifier
                    .offset(0.dp, 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {

                Text(
                    text = trainingList.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.sp,
                        shadow = Shadow(
                            color = Color.Gray, Offset(5.0f, 10.0f), blurRadius = 3f
                        )
                    )
                )
            }

            Row(
                modifier = Modifier
                    .offset(0.dp, 60.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {

                Text(
                    text = "Get Ready!",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.sp,
                        shadow = Shadow(
                            color = Color.Gray, Offset(5.0f, 10.0f), blurRadius = 3f
                        )
                    )
                )
            }

            Row(
                modifier = Modifier.offset(0.dp, 80.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.mainlogo),
                    "Main Logo",
                    modifier = Modifier.offset(0.dp, 0.dp),
                    tint = Color.Unspecified
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(text = "Time left")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = formatTime(timeLeft.value))
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {

                Button(onClick = {
                    isRunning.value = !isRunning.value

                }) {
                    if (isRunning.value) {

                        Text(text = "Stop")
                    } else {
                        Text(text = "Start")
                    }
                }

                Button(
                    onClick = {
                        parentController.navigateWorkOutPlanMainMenu()
                    },
                    modifier = Modifier
                        .offset(10.dp, 0.dp)
                ) {


                    Text(text = "Back")

                }
            }
        }

    } else

        Column(
        ) {
            Row(
                modifier = Modifier
                    .offset(0.dp, 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {

                Text(
                    text = trainingList.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.sp,
                        shadow = Shadow(
                            color = Color.Gray, Offset(5.0f, 10.0f), blurRadius = 3f
                        )
                    )
                )
            }


            Row(
                modifier = Modifier
                    .offset(30.dp, 140.dp),
                horizontalArrangement = Arrangement.Start

            ) {
                Text(
                    text = "Current Training: ",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.sp,
                        shadow = Shadow(
                            color = Color.Gray, Offset(5.0f, 10.0f), blurRadius = 3f
                        )
                    )
                )
            }

            Row(
                modifier = Modifier
                    .offset(30.dp, 160.dp),
                horizontalArrangement = Arrangement.Start

            ) {
                Text(
                    text = trainings.get(iterationRun.value).name,
                )
            }

            Row(
                modifier = Modifier.offset(0.dp, 80.dp)
            ) {
                //zobrazení ikony při vytváření plánu work outu
                when (trainings.get(iterationRun.value).icon) {
                    "Plank" -> Icon(
                        painterResource(id = R.drawable.trainingplanklaunch),
                        "Plank",
                        modifier = Modifier.offset(0.dp, 0.dp),
                        tint = Color.Unspecified
                    )
                    "Push Ups" -> Icon(
                        painterResource(id = R.drawable.trainingpushupslaunch),
                        "Push Ups",
                        modifier = Modifier.offset(0.dp, 0.dp),
                        tint = Color.Unspecified
                    )
                    "Resting" -> Icon(
                        painterResource(id = R.drawable.trainingrestinglaunch),
                        "Resting",
                        modifier = Modifier.offset(0.dp, 0.dp),
                        tint = Color.Unspecified
                    )
                    "Bench Press" -> Icon(
                        painterResource(id = R.drawable.trainingbenchpresslaunch),
                        "Bench Press",
                        modifier = Modifier.offset(0.dp, 0.dp),
                        tint = Color.Unspecified
                    )
                    "Weights" -> Icon(
                        painterResource(id = R.drawable.trainingweightsbiglaunch),
                        "Big Weights",
                        modifier = Modifier.offset(0.dp, 0.dp),
                        tint = Color.Unspecified
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(text = "Time left")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = formatTime(timeLeft.value))
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {

                Button(onClick = {
                    isRunning.value = !isRunning.value

                }) {
                    if (isRunning.value) {

                        Text(text = "Stop")
                    } else {
                        Text(text = "Start")
                    }
                }

                Button(
                    onClick = {
                        parentController.navigateWorkOutPlanMainMenu()
                    },
                    modifier = Modifier
                        .offset(10.dp, 0.dp)
                ) {


                    Text(text = "Back")

                }
            }
        }
}

fun formatTime(time: Int): String {
    val minutes = time / 60
    val seconds = time % 60
    return "${"%02d".format(minutes)}:${"%02d".format(seconds)}"
}






