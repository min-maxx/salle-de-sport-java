package sds.souscriptions.concept_metier;

import java.time.LocalDate;
import java.util.Collection;

public interface AbonnementRepository {
    Abonnement get(IdAbonnement id);

    void addOrReplace(Abonnement abonnement);


    void addOrReplaceAll(Collection<Abonnement> abonnements);

    Collection<Abonnement> trouveAbonnementsFinissantLe(LocalDate jourDeFin);

    Collection<Abonnement> trouveAbonnementsEnCoursSouscritsEntre(LocalDate début, LocalDate fin);
}
