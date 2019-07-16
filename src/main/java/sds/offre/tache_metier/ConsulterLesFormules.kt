package sds.offre.tache_metier

import sds.offre.concept_metier.Formule
import sds.offre.concept_metier.FormuleRepository

class ConsulterLesFormules(private val formuleRepository: FormuleRepository) {

    fun consulte(): Collection<Formule> {
        return formuleRepository.toutesLesFormules()
    }
}
