package cz.uhk.workOutNow.ui.mainMenu

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.SettingsEntity
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import kotlinx.coroutines.flow.Flow

class LaunchTrainingViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    fun sellectSetting(): Flow<SettingsEntity> {
        return trainingEntityDao.selectSetting()
    }

    fun selectAllTrainings(trainingListEntityId: Long): Flow<List<TrainingEntity>> {
        return trainingEntityDao.selectAllTrainingsOfSpecificList(trainingListEntityId)
    }

    fun selectTrainingPlan(trainingListEntityId: Long): Flow<TrainingListEntity> {
        return trainingEntityDao.selectTrainingEntityForEdit(trainingListEntityId)
    }
}


