package com.example.moviles02.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviles02.model.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM students WHERE courseId = :courseId")
    suspend fun getStudentsByCourse(courseId: Int): List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<Student>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents()
}