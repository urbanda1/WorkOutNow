package cz.uhk.workOutNow.ui.mainMenu.subMenus.workOutPlanCrud

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import kotlinx.coroutines.flow.Flow

class WorkOutPlanEditViewModel(
    private val trainingEntityDao: TrainingEntityDao
) : BaseViewModel() {

    fun selectTrainingEntityForEdit(trainingListEntityId: Long): Flow<TrainingListEntity> {
        return trainingEntityDao.selectTrainingEntityForEdit(trainingListEntityId)
    }

    fun updateTrainingListEntity(
        title: String,
        trainingListEntityId: Long,
        description: String,
        icon: String
    ) {
        launch {
            trainingEntityDao.updateTrainingListEntityQuery(
                title,
                trainingListEntityId,
                description,
                icon
            )
        }
    }
}
