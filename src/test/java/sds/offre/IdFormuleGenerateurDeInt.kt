package sds.offre

import sds.offre.concept_metier.IdFormule
import sds.offre.concept_metier.IdFormuleGenerateur

class IdFormuleGenerateurDeInt : IdFormuleGenerateur {
    private var valeur = 0

    override fun nouveauId(): IdFormule {
        return IdFormule((++valeur).toString())
    }
}
