package sds.offre.tache_metier

import sds.offre.concept_metier.Formule
import sds.offre.concept_metier.IdFormule
import sds.offre.concept_metier.Prix

data class PrixFormuleChangee(internal val id: IdFormule, internal val prix: Prix) {

    constructor(formule: Formule) : this(formule.id, formule.prixDeBase)

}
