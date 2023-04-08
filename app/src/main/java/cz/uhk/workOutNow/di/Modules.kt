package cz.uhk.workOutNow.di

import androidx.room.Room
import cz.uhk.workOutNow.data.db.AppDatabase
import cz.uhk.workOutNow.ui.mainMenu.WorkOutTrainingsViewModel
import cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutPlanCrud.WorkOutPlanCreateViewModel
import cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutPlanCrud.WorkOutPlanEditViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = AppDatabase::class.java,
            name = AppDatabase.Name
        ).build()
    }

    single { get<AppDatabase>().trainingEntityDao() }
}

val uiModule = module {
    viewModel { WorkOutPlanCreateViewModel(get()) }
    viewModel { WorkOutTrainingsViewModel(get()) }
    viewModel { WorkOutPlanEditViewModel(get()) }
}