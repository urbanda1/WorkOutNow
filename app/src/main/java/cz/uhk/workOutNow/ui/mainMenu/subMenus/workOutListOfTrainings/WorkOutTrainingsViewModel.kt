package cz.uhk.workOutNow.ui.mainMenu

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import kotlinx.coroutines.flow.Flow

class WorkOutTrainingsViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    fun selectAllTrainings(trainingListEntityId: Long): Flow<List<TrainingEntity>> {
        return trainingEntityDao.selectAllTrainingsOfSpecificList(trainingListEntityId)
    }
    fun deleteTraining(trainingEntity: TrainingEntity) {
        launch {
            trainingEntityDao.deleteTrainingEntity(trainingEntity)
        }
    }
}
