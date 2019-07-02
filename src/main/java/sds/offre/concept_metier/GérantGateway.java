package sds.offre.concept_metier;

import sds.offre.tache_metier.FormuleCreee;
import sds.offre.tache_metier.PrixFormuleChangee;

public interface GérantGateway {
    void faitProjection(FormuleCreee formule);

    void faitProjection(PrixFormuleChangee prixFormuleChangee);
}
