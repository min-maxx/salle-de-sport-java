package sds.offre.infra;

import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.IdFormuleGenerateur;

import java.util.UUID;

public class IdFormuleGenerateurDeUUID implements IdFormuleGenerateur {
    public IdFormule nouveauId() {
        return IdFormule.de(UUID.randomUUID().toString());
    }
}
