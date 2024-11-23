package com.example.gymnoteayn

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gymnoteayn.R
import com.example.gymnoteayn.models.ExSet
import com.example.gymnoteayn.models.Exercise
import kotlinx.coroutines.launch

class ExerciseDetailActivity : AppCompatActivity() {

    private lateinit var exercise: Exercise
    private lateinit var exSetAdapter: ArrayAdapter<ExSet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        exercise = intent.getSerializableExtra("exercise") as Exercise

        title = exercise.name

        // Инициализируем базу данных
        //database = AppDatabase.getDatabase(this)

        // Инициализируем адаптер для списка подходов
        val listSets: ListView = findViewById(R.id.listSets)
        exSetAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listSets.adapter = exSetAdapter

        // Загружаем подходы из базы данных
        //loadSetsFromDatabase()

        // Инициализация ввода веса, повторений и кнопки
        val inputWeight: EditText = findViewById(R.id.inputWeight)
        val inputReps: EditText = findViewById(R.id.inputReps)
        val buttonAddSet: Button = findViewById(R.id.buttonAddSet)

        // Добавление нового подхода
        buttonAddSet.setOnClickListener {
            val weight = inputWeight.text.toString().toDoubleOrNull()
            val reps = inputReps.text.toString().toIntOrNull()

            if (weight != null && reps != null) {
                val newSet = ExSet(weight, reps)
                exSetAdapter.add(newSet)
                //addSetToDatabase(newSet)

                inputWeight.text.clear()
                inputReps.text.clear()
            } else {
                Toast.makeText(this, "Введите корректные значения", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Функция для загрузки подходов из базы данных
//    private fun loadSetsFromDatabase() {
//        lifecycleScope.launch {
//            val sets = database.exSetDao().getSetsForExercise(exercise.id)
//            exSetAdapter.clear()
//            exSetAdapter.addAll(sets)
//            exSetAdapter.notifyDataSetChanged()
//        }
//    }

    // Функция для добавления подхода в базу данных
//    private fun addSetToDatabase(set: ExSet) {
//        lifecycleScope.launch {
//            database.exSetDao().insertSet(set)
//            exSetAdapter.add(set)
//            exSetAdapter.notifyDataSetChanged()
//        }
//    }
}