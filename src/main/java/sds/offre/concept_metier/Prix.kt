package sds.offre.concept_metier

data class Prix constructor(val valeur: Long) {

    init {
        if (valeur < 0) throw IllegalArgumentException("La valeur doit être positive.")
    }

}
