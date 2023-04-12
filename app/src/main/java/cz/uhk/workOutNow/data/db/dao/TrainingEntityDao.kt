package cz.uhk.workOutNow.data.db.dao

import androidx.room.*
import cz.uhk.workOutNow.data.db.entities.SettingsEntity
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingEntityDao {

    //read
    //TrainingEntity
    @Query("SELECT * FROM TrainingEntity WHERE trainingListEntityId = :trainingListEntityId")
    fun selectAllTrainingsOfSpecificList(trainingListEntityId: Long): Flow<List<TrainingEntity>>

    @Query("SELECT * FROM TrainingEntity WHERE trainingEntityId = :trainingEntityId")
    fun selectTrainingForEdit(trainingEntityId: Long): Flow<TrainingEntity>

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateTrainingEntity(trainingEntity: TrainingEntity)

    //update custom
    @Query("UPDATE TrainingEntity set name = :name, minutes = :minutes, seconds = :seconds, icon = :icon WHERE trainingEntityId = :trainingEntityId")
    fun updateTrainingEntityQuery(
        name: String,
        minutes: Int,
        seconds: Int,
        icon: String,
        trainingEntityId: Long
    )

    //delete
    @Delete
    fun deleteTrainingEntity(trainingEntity: TrainingEntity)





    //read
    //TrainingEntity
    @Query("SELECT * FROM TrainingListEntity")
    fun selectAllTrainingEntityList(): Flow<List<TrainingListEntity>>

    @Query("SELECT * FROM TrainingListEntity WHERE trainingListEntityId = :trainingListEntityId")
    fun selectTrainingEntityForEdit(trainingListEntityId: Long): Flow<TrainingListEntity>

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateTrainingListEntity(trainingListEntity: TrainingListEntity)

    //custom update
    @Query("UPDATE TrainingListEntity set title = :title, description = :description, icon = :icon WHERE trainingListEntityId = :trainingListEntityId")
    fun updateTrainingListEntityQuery(
        title: String,
        trainingListEntityId: Long,
        description: String,
        icon: String
    )

    //delete
    @Delete
    fun deleteTrainingListEntity(trainingListEntity: TrainingListEntity)





    //Settings
    @Query("SELECT * FROM SettingsEntity where settingsID = 1")
    fun selectSetting(): Flow<SettingsEntity>

    //Settings
    @Query("SELECT * FROM SettingsEntity")
    fun selectAllSettings(): Flow<List<SettingsEntity>>

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateSettings(settingsEntity: SettingsEntity)

    //custom update
    @Query("UPDATE SettingsEntity set notificationsSettings = :notificationsSettings WHERE settingsID = 1")
    fun updateSettingsEntityQuery(
        notificationsSettings: Int,
    )

    @Delete
    fun deleteSettingsEntity(settingsEntity: SettingsEntity)

}