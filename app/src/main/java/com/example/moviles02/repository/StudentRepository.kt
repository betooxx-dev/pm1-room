package com.example.moviles02.repository

import com.example.moviles02.dao.StudentDao
import com.example.moviles02.model.Student
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentDao: StudentDao
) {
    suspend fun getStudentsByCourse(courseId: Int): List<Student> {
        return studentDao.getStudentsByCourse(courseId)
    }

    suspend fun insertInitialData() {
        val initialStudents = listOf(
            Student(name = "Ana López", email = "ana.lopez@example.com"),
            Student(name = "Carlos Pérez", email = "carlos.perez@example.com"),
            Student(name = "María González", email = "maria.gonzalez@example.com"),
            Student(name = "José Rodríguez", email = "jose.rodriguez@example.com")
        )
        studentDao.insertStudents(initialStudents)
    }

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun deleteAllStudents() {
        studentDao.deleteAllStudents()
    }
}