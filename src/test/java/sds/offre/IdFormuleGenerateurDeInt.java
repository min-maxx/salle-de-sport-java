package sds.offre;

import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.IdFormuleGenerateur;

public class IdFormuleGenerateurDeInt implements IdFormuleGenerateur {
    private int valeur = 0;

    public IdFormule nouveauId() {
        return IdFormule.de(String.valueOf(++valeur));
    }
}
