package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class RenouvellerAbonnementsAutomatiquement {

    private AbonnementRepository abonnementRepository;
    private ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées;

    public RenouvellerAbonnementsAutomatiquement(AbonnementRepository abonnementRepository, ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées) {
        this.abonnementRepository = abonnementRepository;
        this.serviceDeProjectionDesDonnées = serviceDeProjectionDesDonnées;
    }

    public void renouvelle(LocalDate jourDeFin) {
        Collection<Abonnement> abonnementsARenouveller = abonnementRepository.trouveAbonnementsFinissantLe(jourDeFin);

        Collection<AbonnementRenouvellé> abonnementRenouvelés = new ArrayList<>();
        for (Abonnement abonnement : abonnementsARenouveller) {
            LocalDate ancienJourDefin = abonnement.jourDeFin();
            abonnement.renouvelle(jourDeFin);
            if (ancienJourDefin.equals(abonnement.jourDeFin())) break;

            abonnementRenouvelés.add(AbonnementRenouvellé.de(abonnement));
        }

        abonnementRepository.addOrReplaceAll(abonnementsARenouveller);
        serviceDeProjectionDesDonnées.faitProjection(abonnementRenouvelés);
    }
}
