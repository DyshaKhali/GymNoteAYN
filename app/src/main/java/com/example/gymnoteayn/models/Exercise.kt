package com.example.gymnoteayn.models

import java.io.Serializable

class Exercise (val name: String, val sets: MutableList<ExSet>) : Serializable {
    override fun toString(): String {
        return name
    }
}