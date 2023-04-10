package cz.uhk.workOutNow.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.uhk.workOutNow.ui.mainMenu.LaunchTrainingScreen
import cz.uhk.workOutNow.ui.mainMenu.SettingsScreen
import cz.uhk.workOutNow.ui.mainMenu.WorkOutPlanMenuScreen
import cz.uhk.workOutNow.ui.mainMenu.WorkOutTrainingsScreen
import cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutListOfTrainings.TrainingEditScreen
import cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutListOfTrainings.crud.TrainingCreateScreen

import cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutPlanCrud.WorkOutPlanCreateScreen
import cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutPlanCrud.WorkOutPlanEditScreen

@Composable
fun AppContainer(
    controller: NavHostController
) {

    // Main Menu
    NavHost(navController = controller, startDestination = DestinationHome) {
        composable(
            route = DestinationHome
        ) {
            HomeScreen(controller)
        }

        composable(
            route = DestinationWorkOutPlanMainMenu,
        ) {
            WorkOutPlanMenuScreen(controller)
        }

        composable(
            route = DestinationSettings,
        ) {
            SettingsScreen(parentController = controller)
        }


        //Other Screens
        //work out plan
        composable(
            route = DestinationWorkOutPlanCreate,
        ) {
            WorkOutPlanCreateScreen(parentController = controller)
        }

        composable(DestinationWorkOutPlanEdit) { backStackEntry ->
            val idTrainingString = backStackEntry.arguments?.getString("id")

            val idTraining = idTrainingString?.toLong()

            WorkOutPlanEditScreen(controller, idTraining!!)
        }

        composable(
            route = DestinationWorkOutTraining,
        ) { backStackEntry ->
            val idWorkOutPlanString = backStackEntry.arguments?.getString("id")

            val idWorkOutPlan = idWorkOutPlanString?.toLong()

            WorkOutTrainingsScreen(controller, idWorkOutPlan!!)
        }

        composable(
            route = DestinationWorkOutTrainingLaunch,
        ) { backStackEntry ->
            val idWorkOutPlanString = backStackEntry.arguments?.getString("idPlan")

            val idWorkOutPlan = idWorkOutPlanString?.toLong()

            LaunchTrainingScreen(controller, idWorkOutPlan!!)
        }

        composable(
            route = DestinationWorkOutTrainingCreate,
        ) { backStackEntry ->
            val idWorkOutPlanString = backStackEntry.arguments?.getString("idPlan")

            val idWorkOutPlan = idWorkOutPlanString?.toLong()

            TrainingCreateScreen(controller, idWorkOutPlan!!)
        }

        composable(
            route = DestinationWorkOutTrainingEdit,
        ) { backStackEntry ->
            val idWorkOutPlanString = backStackEntry.arguments?.getString("idPlan")

            val idTrainingString = backStackEntry.arguments?.getString("idTraining")

            val idWorkOutPlan = idWorkOutPlanString?.toLong()
            val idTraining = idTrainingString?.toLong()

            TrainingEditScreen(controller, idWorkOutPlan!!, idTraining!!)
        }



    }
}

// Main Menu
fun NavHostController.navigateHomeScreen() {
    navigate(DestinationHome)
}

fun NavHostController.navigateWorkOutPlanMainMenu() {
    navigate(DestinationWorkOutPlanMainMenu)
}

fun NavHostController.navigateDestinationSettings() {
    navigate(DestinationSettings)
}

//Other Screens
//work out plan
fun NavHostController.navigateWorkOutPlanCreate() {
    navigate(DestinationWorkOutPlanCreate)
}

// Main Menu
private const val DestinationHome = "Home"
private const val DestinationWorkOutPlanMainMenu = "Set up plan"
private const val DestinationSettings = "Settings"

//Other Screens
//Crud workout Plan
private const val DestinationWorkOutPlanCreate = "Create Work Out Plan Menu"
private const val DestinationWorkOutPlanEdit = "edit/{id}"

//crud work out
private const val DestinationWorkOutTraining = "workOutPlan/{id}"
private const val DestinationWorkOutTrainingCreate = "workOutPlan/{idPlan}/create"
private const val DestinationWorkOutTrainingEdit = "workOutPlan/{idPlan}/edit/{idTraining}"
private const val DestinationWorkOutTrainingLaunch = "workOutPlan/{idPlan}/launch"


