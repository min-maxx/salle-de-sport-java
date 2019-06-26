package sds.notification_email.infra;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.Abonnés;
import sds.notification_email.concept_metier.AdresseEmail;
import sds.notification_email.concept_metier.IdAbonné;
import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.infra.AbonnementRepositoryEnPostgreSQL;
import sds.souscriptions.tache_metier.ChercherAbonnementsParPeriodeDeSouscription;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class AbonnésExternes implements Abonnés {
    @Override
    public Collection<Abonné> trouveAbonnésAyantSoucritLe(YearMonth moisDeSouscription) {
        LocalDate debutDuMois = moisDeSouscription.atDay(1);
        LocalDate finDuMois = moisDeSouscription.atEndOfMonth();

        Collection<Abonnement> abonnements = new ChercherAbonnementsParPeriodeDeSouscription(new AbonnementRepositoryEnPostgreSQL()).cherche(debutDuMois, finDuMois);

        return abonnements.stream()
                .map(this::enAbonné)
                .collect(toList());
    }

    private Abonné enAbonné(Abonnement abonnement) {
        return Abonné.avec(
                IdAbonné.de(abonnement.id().valeur()),
                AdresseEmail.de(""), //TODO récupérer email
                abonnement.jourDeSouscription());
    }
}
