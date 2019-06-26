package sds.souscriptions.infra;

import sds.offre.concept_metier.Formule;
import sds.offre.tache_metier.ConsulterUneFormule;
import sds.souscriptions.concept_metier.*;

public class FormuleGatewayOffre implements FormuleGateway {

    private final ConsulterUneFormule consulterUneFormule;

    public FormuleGatewayOffre(ConsulterUneFormule consulterUneFormule) {
        this.consulterUneFormule = consulterUneFormule;
    }

    @Override
    public FormuleChoisie trouveFormuleChoisie(IdFormule idFormule) {
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
