package com.example.gymnoteayn

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gymnoteayn.R
import com.example.gymnoteayn.models.Exercise
import com.example.gymnoteayn.ExerciseDetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<Exercise>
    private val exercises: MutableList<Exercise> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Инициализируем базу данных
//        database = AppDatabase.getDatabase(this)
//        exerciseDao = database.exerciseDao()

        // UI
        val listExercises = findViewById<ListView>(R.id.listExercises)
        val inputExercise: EditText = findViewById(R.id.inputExercise)
        val button: Button = findViewById(R.id.addExerciseButton)

        //Список упражений
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exercises)
        listExercises.adapter = adapter

        // Загружаем упражнения из базы данных при запуске приложения
        //loadExercisesFromDatabase()

        //Добавляем упражнение
        button.setOnClickListener {
            val text = inputExercise.text.toString()
            if (text.isNotEmpty()) {
                val newExercise = Exercise(text, mutableListOf())
                exercises.add(newExercise)
                adapter.notifyDataSetChanged()
                //addExerciseToDatabase(newExercise)
                inputExercise.text.clear()
            }
        }

        listExercises.setOnItemClickListener { _, _, position, _ ->
            val selectedExercise = exercises[position]
            val intent = Intent(this, ExerciseDetailActivity::class.java)
            intent.putExtra("exercise", selectedExercise)
            startActivity(intent)
        }
    }

    // Функция для добавления упражнения в базу данных и обновления списка
//    private fun addExerciseToDatabase(exercise: Exercise) {
//        lifecycleScope.launch {
//            // Вставляем новое упражнение в базу данных
//            val exerciseId = exerciseDao.insertExercise(exercise)
//            exercise.id = exerciseId  // Обновляем ID в объекте после вставки
//
//            // Обновляем адаптер
//            exercises.add(exercise)
//            adapter.notifyDataSetChanged()
//        }
//    }

    // Функция для загрузки упражнений из базы данных
//    private fun loadExercisesFromDatabase() {
//        lifecycleScope.launch {
//            val savedExercises = exerciseDao.getAllExercises()
//            exercises.clear()
//            exercises.addAll(savedExercises)
//            adapter.notifyDataSetChanged()
//        }
//    }
}