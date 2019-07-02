package sds.souscriptions.tache_metier;

import java.util.Collection;

public interface ServiceDeProjectionDesDonnées {

    void faitProjection(AbonnementSouscrit abonnementSouscrit);

    void faitProjection(Collection<AbonnementRenouvellé> abonnementsARenouveller);
}
