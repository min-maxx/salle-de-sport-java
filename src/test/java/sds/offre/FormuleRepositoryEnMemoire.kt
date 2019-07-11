package sds.offre

import sds.offre.concept_metier.Formule
import sds.offre.concept_metier.FormuleRepository
import sds.offre.concept_metier.IdFormule
import java.util.*

class FormuleRepositoryEnMemoire : FormuleRepository {

    private val formuleMap = HashMap<IdFormule, Formule>()

    override operator fun get(id: IdFormule): Formule {
        return formuleMap[id] ?: throw FormuleNonTrouvéeException("")
    }

    override fun addOrReplace(formule: Formule) {
        formuleMap[formule.id] = formule
    }

    override fun toutesLesFormules(): Collection<Formule> {
        return formuleMap.values
    }
}
