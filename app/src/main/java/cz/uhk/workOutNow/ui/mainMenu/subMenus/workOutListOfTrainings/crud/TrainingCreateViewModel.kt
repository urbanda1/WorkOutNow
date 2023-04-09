package cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutListOfTrainings.crud

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
class TrainingCreateViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    fun createTrainingListEntity(trainingEntity: TrainingEntity) {

        launch {
            trainingEntityDao.insertOrUpdateTrainingEntity(trainingEntity)
        }
    }

}