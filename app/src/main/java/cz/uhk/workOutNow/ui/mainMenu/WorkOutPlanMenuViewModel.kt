package cz.uhk.workOutNow.ui.mainMenu

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import kotlinx.coroutines.flow.Flow

class WorkOutPlanMenuViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    val trainingListEntity = trainingEntityDao.selectAllTrainingEntityList()

    fun selectAllTrainings(trainingListEntityId: Long): Flow<List<TrainingEntity>> {
        return trainingEntityDao.selectAllTrainingsOfSpecificList(trainingListEntityId)
    }
    fun deleteTrainingListEntity(trainingListEntity: TrainingListEntity) {

        launch {
            trainingEntityDao.deleteTrainingListEntity(trainingListEntity)
        }
    }
}