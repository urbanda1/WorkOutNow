package cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutListOfTrainings.crud

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import kotlinx.coroutines.flow.Flow

class TrainingEditViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    fun selectTrainingForEdit(trainingEntityId: Long): Flow<TrainingEntity> {
        return trainingEntityDao.selectTrainingForEdit(trainingEntityId)
    }

    fun updateTrainingEntityQuery(
        name: String,
        minutes: Int,
        seconds: Int,
        icon: String,
        trainingEntityId: Long
    ) {
        launch {
            trainingEntityDao.updateTrainingEntityQuery(
                name,
                minutes,
                seconds,
                icon,
                trainingEntityId
            )
        }
    }
}