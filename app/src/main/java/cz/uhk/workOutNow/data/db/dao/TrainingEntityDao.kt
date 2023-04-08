package cz.uhk.workOutNow.data.db.dao

import androidx.room.*
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface TrainingEntityDao {

    //read
    //TrainingEntity
    @Query("SELECT * FROM TrainingEntity WHERE trainingListEntityId = :trainingListEntityId")
    fun selectAllTrainingsOfSpecificList(trainingListEntityId: Long): Flow<List<TrainingEntity>>

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateTrainingEntity(trainingEntity: TrainingEntity)

    //update custom
    @Query("UPDATE TrainingEntity set name = :name, duration = :duration, icon = :icon WHERE trainingEntityId = :trainingEntityId")
    fun updateTrainingEntityQuery(
        name: String,
        duration: Long,
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

}