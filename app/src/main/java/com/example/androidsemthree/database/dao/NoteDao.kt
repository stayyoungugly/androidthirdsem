package com.example.androidsemthree.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidsemthree.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): List<Note>

    @Query("SELECT * FROM notes")
    fun getAllLiveData(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id IN (:todoIds)")
    fun loadAllByIds(todoIds: IntArray): List<Note>

    @Query("SELECT * FROM notes WHERE title = :title LIMIT 1")
    fun findByName(title: String): Note

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    fun findById(id: Int): Note

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()

    @Query("DELETE FROM notes WHERE id=:id")
    fun deleteNoteById(id: Int)
}
