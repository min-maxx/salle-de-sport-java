package sds.offre.tache_metier

import sds.offre.concept_metier.FormuleRepository
import sds.offre.concept_metier.IdFormule
import sds.offre.concept_metier.Prix
import java.util.*

class ChangerLePrixDeFormule(private val formuleRepository: FormuleRepository) {

    fun change(id: IdFormule, nouveauPrix: Prix): Optional<PrixFormuleChangee> {
        val formule = formuleRepository[id]
        if (formule.lePrixEstIdentique(nouveauPrix)) return Optional.empty()

        formule.changePrix(nouveauPrix)
        formuleRepository.addOrReplace(formule)
        return Optional.of(PrixFormuleChangee(formule))
    }

}
