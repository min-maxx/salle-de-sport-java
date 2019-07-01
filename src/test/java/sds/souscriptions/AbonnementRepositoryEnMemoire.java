package sds.souscriptions;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdAbonnement;
import sds.souscriptions.concept_metier.IdFormule;
import sds.utils.concept_metier.Event;
import sds.utils.concept_metier.EventSourcingRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class AbonnementRepositoryEnMemoire extends EventSourcingRepository<IdAbonnement> implements AbonnementRepository {

    @Override
    public Abonnement get(IdAbonnement id) {
        return new Abonnement(eventsStreams.get(id));
    }

    @Override
    public List<Event> addOrReplace(Abonnement abonnement) {
        return appendStream(abonnement.id(), abonnement.changements());
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
