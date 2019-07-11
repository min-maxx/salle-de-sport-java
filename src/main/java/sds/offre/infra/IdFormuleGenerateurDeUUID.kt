package sds.offre.infra

import sds.offre.concept_metier.IdFormule
import sds.offre.concept_metier.IdFormuleGenerateur
import java.util.*

class IdFormuleGenerateurDeUUID : IdFormuleGenerateur {
    override fun nouveauId(): IdFormule {
        return IdFormule(UUID.randomUUID().toString())
    }
}
