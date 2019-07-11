package sds.offre;

import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.IdFormuleGenerateur;

public class IdFormuleGenerateurDeInt implements IdFormuleGenerateur {
    private int valeur = 0;

    public IdFormule nouveauId() {
        return new IdFormule(String.valueOf(++valeur));
    }
}
