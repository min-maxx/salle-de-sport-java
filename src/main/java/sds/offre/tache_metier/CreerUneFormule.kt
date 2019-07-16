package sds.offre.tache_metier

import sds.offre.concept_metier.*

class CreerUneFormule(private val idFormuleGenerateur: IdFormuleGenerateur, private val formuleRepository: FormuleRepository) {

    fun crée(prix: Prix, durée: Durée): FormuleCreee {
        val formule = Formule(idFormuleGenerateur.nouveauId(), durée, prix)
        formuleRepository.addOrReplace(formule)
        return FormuleCreee(formule)
    }
}
