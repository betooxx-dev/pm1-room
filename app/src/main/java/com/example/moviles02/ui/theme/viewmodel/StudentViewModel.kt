package com.example.moviles02.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviles02.model.Student
import com.example.moviles02.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadStudents(courseId: Int = 1) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                // Si la base de datos está vacía, insertamos datos iniciales
                val currentStudents = studentRepository.getStudentsByCourse(courseId)
                if (currentStudents.isEmpty()) {
                    studentRepository.insertInitialData()
                }

                // Simulamos un pequeño retraso para ver el efecto de carga
                delay(1000)

                // Obtenemos los estudiantes de la base de datos
                _students.value = studentRepository.getStudentsByCourse(courseId)
            } catch (e: Exception) {
                Log.e("StudentViewModel", "Error loading students", e)
                _students.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addStudent(name: String, email: String = "", courseId: Int = 1) {
        viewModelScope.launch {
            val newStudent = Student(name = name, email = email, courseId = courseId)
            studentRepository.insertStudent(newStudent)
            _students.value = studentRepository.getStudentsByCourse(courseId)
        }
    }

    fun clearStudents() {
        viewModelScope.launch {
            studentRepository.deleteAllStudents()
            _students.value = emptyList()
        }
    }
}