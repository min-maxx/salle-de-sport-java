package sds.offre.tache_metier

import sds.offre.concept_metier.Formule
import sds.offre.concept_metier.FormuleRepository
import sds.offre.concept_metier.IdFormule

class ConsulterUneFormule(private val formuleRepository: FormuleRepository) {

    fun consulte(idFormule: IdFormule): Formule {
        return formuleRepository[idFormule]
    }

}
