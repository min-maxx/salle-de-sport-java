package sds.offre.tache_metier

import sds.offre.concept_metier.Durée
import sds.offre.concept_metier.Formule
import sds.offre.concept_metier.IdFormule
import sds.offre.concept_metier.Prix

data class FormuleCreee(val id: IdFormule, val prix: Prix, val durée: Durée) {

    constructor(formule: Formule) : this(formule.id, formule.prixDeBase, formule.durée)

}
