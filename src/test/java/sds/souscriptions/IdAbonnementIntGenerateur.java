package sds.souscriptions;

import sds.souscriptions.concept_metier.IdAbonnement;
import sds.souscriptions.concept_metier.IdAbonnementGenerateur;

public class IdAbonnementIntGenerateur implements IdAbonnementGenerateur {
    private int valeur = 0;

    @Override
    public IdAbonnement nouveauId() {
        return IdAbonnement.de(String.valueOf(++valeur));
    }
}
