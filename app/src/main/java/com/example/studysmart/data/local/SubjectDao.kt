package com.example.studysmart.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import javax.security.auth.Subject

@Dao
interface SubjectDao {
    @Upsert
    fun upsertSubject(subject: com.example.studysmart.domain.model.Subject)

    @Query("SELECT COUNT(*) FROM SUBJECT ")
    fun getTotalSubjectCount(): Int
    @Query("SELECT SUM(goalHours) FROM SUBJECT ")
    fun getTotalGoalHours(): Flow<Int>
    @Query("SELECT * FROM Subject WHERE subjectId = :subjectId ")
   suspend fun getSubjectById(subjectId: Int): Subject?

    @Query("DELETE FROM Subject WHERE subjectId = :subjectId ")
    suspend fun deleteSubject(subjectId: Int)
    @Query("SELECT * FROM Subject ")
    fun getAllSubjects(): Flow<List<Subject>>
}