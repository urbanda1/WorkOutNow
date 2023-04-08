package cz.uhk.workOutNow.data.db.dao

import androidx.room.*
import cz.uhk.workOutNow.data.db.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    fun selectAll(): Flow<List<NoteEntity>>

    @Update
    fun updateNote(note: NoteEntity)

    @Query("UPDATE NoteEntity SET priority = :priority WHERE id = :id")
    fun updateNote(id: Long, priority: Int)

}