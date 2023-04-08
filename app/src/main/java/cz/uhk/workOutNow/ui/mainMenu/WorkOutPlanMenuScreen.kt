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

@Composable
fun WorkOutPlanMenuScreen(
    parentController: NavHostController,
    viewModel: WorkOutPlanMenuViewModel = getViewModel()
) {

    val workOutPlans = viewModel.trainingListEntity.collectAsState(emptyList())

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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(2f)
                .offset(0.dp, 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(workOutPlans.value) { workOutPlan ->

                Text(
                    text = workOutPlan.title,
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
                    text = workOutPlan.description,
                    modifier = Modifier.padding(0.dp, 10.dp)
                )

                when (workOutPlan.icon) {
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

                //řada možností pro lazy item
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                )

                {
                    //vymaz lazy item
                    IconButton(onClick = {
                        viewModel.deleteTrainingListEntity(workOutPlan)
                    }) {

                        Icon(
                            (Icons.Default.Delete), contentDescription = "",
                            modifier = Modifier.offset(-(0.dp), (0.dp)),
                            tint = Color.Unspecified // toto nastavení zachová původní barvu
                        )
                    }

                    //uprav lazy item
                    IconButton(onClick = {
                        parentController.navigate("edit/${workOutPlan.trainingListEntityId}")
                    }) {

                        Icon(
                            (Icons.Default.Edit), contentDescription = "",
                            modifier = Modifier.offset(-(0.dp), (0.dp)),
                            tint = Color.Unspecified // toto nastavení zachová původní barvu
                        )
                    }

                    //spravuj seznam cviků
                    IconButton(onClick = {
                        parentController.navigate("workOutPlan/${workOutPlan.trainingListEntityId}")
                    }) {

                        Icon(
                            (Icons.Default.List), contentDescription = "",
                            modifier = Modifier.offset(-(0.dp), (0.dp)),
                            tint = Color.Unspecified // toto nastavení zachová původní barvu
                        )
                    }

                    // spust cviky
                    IconButton(onClick = {

                    }) {
                        Icon(
                            (Icons.Default.PlayArrow), contentDescription = "",
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
                onClick = { parentController.navigateWorkOutPlanCreate() },
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
