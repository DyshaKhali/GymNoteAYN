package com.example.gymnoteayn.models

import java.io.Serializable

class ExSet (val weight: Double, val reps: Int) : Serializable {
    override fun toString(): String {
        return "$weight кг - $reps"
    }
}