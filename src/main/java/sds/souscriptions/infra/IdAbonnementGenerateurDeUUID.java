package sds.souscriptions.infra;

import sds.souscriptions.concept_metier.IdAbonnement;
import sds.souscriptions.concept_metier.IdAbonnementGenerateur;

import java.util.UUID;

public class IdAbonnementGenerateurDeUUID implements IdAbonnementGenerateur {
    public IdAbonnement nouveauId() {
        return IdAbonnement.de(UUID.randomUUID().toString());
    }
}
