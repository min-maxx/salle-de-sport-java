package sds.offre.concept_metier

import java.util.*

class Formule(
        val id: IdFormule,
        val durée: Durée,
        prix: Prix) {

    var prixDeBase = prix
        private set

    fun changePrix(nouveauPrix: Prix) {
        prixDeBase = nouveauPrix
    }

    fun lePrixEstIdentique(nouveauPrix: Prix): Boolean {
        return prixDeBase == nouveauPrix
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val formule = other as Formule?
        return id == formule!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun toString(): String {
        return "Formule{" +
                "id=" + id +
                ", durée=" + durée +
                ", prix=" + prixDeBase +
                '}'.toString()
    }
}
