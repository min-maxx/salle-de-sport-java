package sds.souscriptions;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdAbonnement;
import sds.souscriptions.concept_metier.IdFormule;
import sds.utils.concept_metier.Event;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class AbonnementRepositoryEnMemoire implements AbonnementRepository {

    private HashMap<IdAbonnement, List<Event>> eventsStreams = new HashMap<>();

    @Override
    public Abonnement get(IdAbonnement id) {
        return new Abonnement(eventsStreams.get(id));
    }

    @Override
    public List<Event> addOrReplace(Abonnement abonnement) {
        List<Event> events = eventsStreams.getOrDefault(abonnement.id(), new ArrayList<>());
        List<Event> changements = abonnement.changements();
        events.addAll(changements);
        eventsStreams.put(abonnement.id(), events);
        return changements;
    }

    @Override
    public Map<IdAbonnement, List<Event>> addOrReplaceAll(Collection<Abonnement> abonnements) {
        Map<IdAbonnement, List<Event>> changementsParAbonnement = new HashMap<>();
        for (Abonnement abonnement : abonnements) {
            changementsParAbonnement.put(abonnement.id(), addOrReplace(abonnement));
        }
        return changementsParAbonnement;
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsFinissantLe(LocalDate jour) {
        return eventsStreams.keySet().stream()
                .map(id -> new Abonnement(eventsStreams.get(id)))
                .filter(abonnement -> mêmeJour(abonnement.jourDeFin(), jour))
                .collect(toList());
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsEnCoursSouscritsEntre(LocalDate début, LocalDate fin) {
        return eventsStreams.keySet().stream()
                .map(id -> new Abonnement(eventsStreams.get(id)))
                .filter(abonnement -> abonnement.jourDeFin().isAfter(LocalDate.now()))
                .filter(abonnement -> abonnement.jourDeSouscription().isAfter(début) && abonnement.jourDeSouscription().isBefore(fin))
                .collect(Collectors.toList());
    }

    @Override
    public Map<IdFormule, Long> compteAbonnementsParIdFormule(List<IdFormule> idFormules) {
        return eventsStreams.keySet().stream()
                .map(id -> new Abonnement(eventsStreams.get(id)))
                .map(Abonnement::idFormule)
                .collect(groupingBy(identity(), counting()));
    }

    private boolean mêmeJour(LocalDate date, LocalDate autreJour) {
        return date.atStartOfDay().equals(autreJour.atStartOfDay());
    }
}
