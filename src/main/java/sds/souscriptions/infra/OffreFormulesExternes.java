package sds.souscriptions.infra;

import sds.offre.concept_metier.Formule;
import sds.offre.infra.FormuleRepositoryEnPostgreSQL;
import sds.offre.tache_metier.ConsulterUneFormule;
import sds.souscriptions.concept_metier.*;

import java.util.Collection;
import java.util.HashMap;

public class OffreFormulesExternes implements OffreFormules {
    private HashMap<IdFormule, FormuleChoisie> formuleMap = new HashMap<>();

    public OffreFormulesExternes(Collection<FormuleChoisie> formuleChoisies) {
        formuleChoisies.forEach(formule -> formuleMap.put(formule.id, formule));
    }


    @Override
    public FormuleChoisie trouveFormuleChoisie(IdFormule idFormule) {
        ConsulterUneFormule consulterUneFormule = new ConsulterUneFormule(new FormuleRepositoryEnPostgreSQL());
        Formule formule = consulterUneFormule.consulte(sds.offre.concept_metier.IdFormule.de(idFormule.valeur()));
        return FormuleChoisie.avec(IdFormule.de(formule.Id().valeur()), Prix.de(formule.prixDeBase().valeur()), toDurée(formule.durée()));
    }

    private Durée toDurée(sds.offre.concept_metier.Durée durée) {
        switch (durée) {
            case AU_MOIS:
                return Durée.MENSUELLE;
            case A_L_ANNEE:
                return Durée.ANNUELLE;
        }
        throw new IllegalStateException();
    }
}
