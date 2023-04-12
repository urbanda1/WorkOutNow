package cz.uhk.workOutNow.ui.mainMenu

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.SettingsEntity
import kotlinx.coroutines.flow.Flow

class SettingsViewModel(
    private val trainingEntityDao: TrainingEntityDao
): BaseViewModel() {

    fun sellectSetting(): Flow<SettingsEntity> {
        return trainingEntityDao.selectSetting()
    }

    fun editSettings(notificationSettings: Int) {

        launch {
            trainingEntityDao.updateSettingsEntityQuery(notificationSettings)
        }
    }

}