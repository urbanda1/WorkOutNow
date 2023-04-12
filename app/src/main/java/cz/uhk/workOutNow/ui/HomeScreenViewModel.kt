package cz.uhk.workOutNow.ui

import cz.uhk.workOutNow.base.BaseViewModel
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.SettingsEntity
import kotlinx.coroutines.flow.Flow

class HomeScreenViewModel(
    private val trainingEntityDao: TrainingEntityDao
): BaseViewModel() {

    // na home screen se vloží do databáze defaultní nastavení, pokud ještě tam nebylo nic vloženo
    fun selectAllSettings(): Flow<List<SettingsEntity>> {
        return trainingEntityDao.selectAllSettings()
    }

    fun createSettings(settingsEntity: SettingsEntity) {

        launch {
            trainingEntityDao.insertOrUpdateSettings(settingsEntity)
        }
    }
}