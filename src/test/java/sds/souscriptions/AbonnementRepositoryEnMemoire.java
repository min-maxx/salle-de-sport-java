package sds.souscriptions;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.concept_metier.IdAbonnement;
import sds.souscriptions.concept_metier.IdFormule;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class AbonnementRepositoryEnMemoire implements AbonnementRepository {

    private HashMap<IdAbonnement, Abonnement> abonnementMap = new HashMap<>();

    @Override
    public Abonnement get(IdAbonnement id) {
        return abonnementMap.get(id);
    }

    @Override
    public void addOrReplace(Abonnement abonnement) {
        abonnementMap.put(abonnement.id(), abonnement);
    }

    @Override
    public void addOrReplaceAll(Collection<Abonnement> abonnements) {
        abonnements.forEach(this::addOrReplace);
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsFinissantLe(LocalDate jour) {
        return abonnementMap.values().stream()
                .filter(abonnement -> mêmeJour(abonnement.jourDeFin(), jour))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Abonnement> trouveAbonnementsEnCoursSouscritsEntre(LocalDate début, LocalDate fin) {
        return abonnementMap.values().stream()
                .filter(abonnement -> abonnement.jourDeFin().isAfter(LocalDate.now()))
                .filter(abonnement -> abonnement.jourDeSouscription().isAfter(début) && abonnement.jourDeSouscription().isBefore(fin))
                .collect(Collectors.toList());
    }

    @Override
    public Map<IdFormule, Long> compteAbonnementsParIdFormule(List<IdFormule> idFormules) {
        return abonnementMap.values().stream()
                .map(Abonnement::idFormule)
                .filter(idFormule -> idFormules.contains(idFormule))
                .collect(groupingBy(identity(), counting()));
    }

    private boolean mêmeJour(LocalDate date, LocalDate autreJour) {
        return date.atStartOfDay().equals(autreJour.atStartOfDay());
    }
}
