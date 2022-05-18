package dev.araozu.laboratorio2.model

import java.lang.IllegalArgumentException

enum class Partidos {
    // TODO: Colocar partidos

    Arequipa_Tradicion_Futuro,
    Arequipa_transformacion,
    Frente_Popular_Agricola_del_Peru,
    Movimiento_Regional_Reveladora,
    NINGUNO;

    companion object {
        fun fromString(partido: String): Partidos? {
            return try {
                valueOf(partido.uppercase().replace(" ", "_"))
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }

    override fun toString(): String {
        return when (this) {
            // TODO: Devolver nombres correctos, sin guiones
            //       ejm: PARTIDO_RENOVACION_NACIONAL -> "Partido Renovación Nacional"
            Arequipa_Tradicion_Futuro -> "Arequipa, Tradicion y Futuro"
            Arequipa_transformacion -> "Arequipa Transformacion"
            Frente_Popular_Agricola_del_Peru -> "Frente Popular Agricola del Peru"
            Movimiento_Regional_Reveladora -> "Movimiento Regional Reveladora"
            NINGUNO -> ""
            else -> super.toString()
        }
    }
}
