package sds.offre.infra

import sds.offre.concept_metier.Formule
import sds.offre.concept_metier.FormuleRepository
import sds.offre.concept_metier.IdFormule

class FormuleRepositoryEnPostgreSQL : FormuleRepository {


    override operator fun get(id: IdFormule): Formule {
        //SELECT * FROM ...
        throw UnsupportedOperationException("Pas encore implémenté")
    }

    override fun addOrReplace(formule: Formule) {
        //INSERT || UPDATE
        throw UnsupportedOperationException("Pas encore implémenté")
    }

    override fun toutesLesFormules(): Collection<Formule> {
        //SELECT * FROM ...
        throw UnsupportedOperationException("Pas encore implémenté")
    }
}
