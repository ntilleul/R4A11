package com.example.tp2.ui.theme

import java.util.Calendar

class AgeCalculator {

    companion object {
        fun calculerAge(anneeNaissance: String): Int {
            val anneeActuelle =
                Calendar.getInstance().get(Calendar.YEAR) // Récupère l'année actuelle

            val anneeInt = anneeNaissance.toIntOrNull()

            if (anneeInt != null) {
                return anneeActuelle - anneeInt
            } else {
                return -1
            }
        }
    }
}