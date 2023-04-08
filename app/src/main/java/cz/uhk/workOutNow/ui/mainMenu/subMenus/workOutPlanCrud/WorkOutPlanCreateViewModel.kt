package cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutPlanCrud

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity

class WorkOutPlanCreateViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    fun createTrainingListEntity(trainingListEntity: TrainingListEntity) {

        launch {
            trainingEntityDao.insertOrUpdateTrainingListEntity(trainingListEntity)
        }
    }

}