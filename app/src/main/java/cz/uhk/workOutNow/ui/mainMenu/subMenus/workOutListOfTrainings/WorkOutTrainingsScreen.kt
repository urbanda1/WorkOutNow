package cz.uhk.workOutNow.ui.mainMenu

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cz.uhk.workOutNow.R
import cz.uhk.workOutNow.ui.*
import org.koin.androidx.compose.getViewModel

//zde budou všechny tréninky, které spadají pod vybraný seznam
@Composable
fun WorkOutTrainingsScreen(
    parentController: NavHostController,
    id: Long,
    viewModel: WorkOutTrainingsViewModel = getViewModel()
) {

    val trainings = viewModel.selectAllTrainings(id).collectAsState(emptyList())

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

    Column {
        Row(
            modifier = Modifier
                .offset(0.dp, 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {

            IconButton(onClick = { parentController.navigateWorkOutPlanMainMenu() })
            {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(2f)
                .offset(0.dp, 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(trainings.value) { training ->

                Text(
                    text = training.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.sp,
                        shadow = Shadow(
                            color = Color.Gray, Offset(5.0f, 10.0f), blurRadius = 3f
                        )
                    )
                )

                Text(
                    text = "Délka tréninku: " + training.minutes + ":" + training.seconds,
                    modifier = Modifier.padding(0.dp, 10.dp)
                )

                when (training.icon) {
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

                //řada možností pro lazy item
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                )

                {
                    //vymaz lazy item
                    IconButton(onClick = {
                        viewModel.deleteTraining(training)
                    }) {

                        Icon(
                            (Icons.Default.Delete), contentDescription = "",
                            modifier = Modifier.offset(-(0.dp), (0.dp)),
                            tint = Color.Unspecified // toto nastavení zachová původní barvu
                        )
                    }

                    //uprav lazy item
                    IconButton(onClick = {
                        parentController.navigate("workOutPlan/${id}/edit/${training.trainingEntityId}")
                    }) {

                        Icon(
                            (Icons.Default.Edit), contentDescription = "",
                            modifier = Modifier.offset(-(0.dp), (0.dp)),
                            tint = Color.Unspecified // toto nastavení zachová původní barvu
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.offset(0.dp, 30.dp)
        ) {
            Canvas(
                modifier =
                Modifier.fillMaxWidth()
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


        Row(
            modifier = Modifier
                .padding(16.dp)
                .offset(55.dp, 0.dp)

        )

        {

            IconButton(onClick = {
                parentController.navigateHomeScreen()

            }) {

                Icon(
                    painterResource(R.drawable.housebig), contentDescription = "",
                    modifier = Modifier.offset(-(10.dp), (0.dp)),
                    tint = Color.Unspecified // toto nastavení zachová původní barvu
                )

                Text(
                    text = "Home",
                    modifier = Modifier.offset(-(10.dp), (40.dp)),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        fontSize = 20.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFF000000)
                    )
                )

            }

            Button(
                onClick = { parentController.navigate("workOutPlan/${id}/create") },
                shape = CircleShape,
                modifier = Modifier
                    .size(64.dp)
                    .offset(0.dp, 27.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "")
            }

            IconButton(onClick = {
                parentController.navigateDestinationSettings()

            }) {

                Icon(
                    painterResource(R.drawable.settingsbig), contentDescription = "",
                    modifier = Modifier.offset(((10.dp)), (0.dp)),
                    tint = Color.Unspecified // toto nastavení zachová původní barvu
                )

                Text(
                    text = "Settings",
                    modifier = Modifier.offset(((10.dp)), (40.dp)),
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


        Row(
            modifier = Modifier.offset(0.dp, -10.dp)
        ) {
            Canvas(
                modifier =
                Modifier.fillMaxWidth()
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

    }
}
