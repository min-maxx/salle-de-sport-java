package sds.souscriptions.concept_metier;

import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AbonnementRepository {
    Abonnement get(IdAbonnement id);

    List<Event> addOrReplace(Abonnement abonnement);

    Map<IdAbonnement, List<Event>> addOrReplaceAll(Collection<Abonnement> abonnements);

    Collection<Abonnement> trouveAbonnementsFinissantLe(LocalDate jourDeFin);

    Collection<Abonnement> trouveAbonnementsEnCoursSouscritsEntre(LocalDate début, LocalDate fin);

    Map<IdFormule, Long> compteAbonnementsParIdFormule(List<IdFormule> idFormules);
}
