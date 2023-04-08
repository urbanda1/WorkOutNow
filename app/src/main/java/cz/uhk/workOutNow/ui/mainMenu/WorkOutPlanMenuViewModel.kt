package cz.uhk.workOutNow.ui.mainMenu

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity

class WorkOutPlanMenuViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    val trainingListEntity = trainingEntityDao.selectAllTrainingEntityList()

    fun deleteTrainingListEntity(trainingListEntity: TrainingListEntity) {

        launch {
            trainingEntityDao.deleteTrainingListEntity(trainingListEntity)
        }
    }
}