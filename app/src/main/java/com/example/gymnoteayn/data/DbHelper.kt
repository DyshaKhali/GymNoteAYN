package com.example.gymnoteayn.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.ViewModelProvider
import com.example.gymnoteayn.models.Exercise

class DbHelper (private val context: Context, private val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "gymnote", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE exercises (id INT PRIMARY KEY, name TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS exercises")
        onCreate(db)
    }

    fun addExercise(exercise: Exercise)
    {
        val values = ContentValues()
        values.put("name", exercise.name)

        val db = this.writableDatabase
        db.insert("exercises", null, values)

        db.close()
    }
}