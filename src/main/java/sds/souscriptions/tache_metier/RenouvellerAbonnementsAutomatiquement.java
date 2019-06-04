package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRenouvelle;
import sds.souscriptions.concept_metier.AbonnementRepository;

import java.time.LocalDate;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class RenouvellerAbonnementsAutomatiquement {

    private AbonnementRepository abonnementRepository;

    public RenouvellerAbonnementsAutomatiquement(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    public Collection<AbonnementRenouvelle> renouvelle(LocalDate jourDeFin) {
        Collection<Abonnement> abonnementsARenouveller = abonnementRepository.trouveAbonnementsAvec(jourDeFin);

        Collection<AbonnementRenouvelle> abonnementsRenouvelles = abonnementsARenouveller.stream().map(Abonnement::renouvelle).collect(toList());

        abonnementRepository.addOrReplaceAll(abonnementsARenouveller);

        return abonnementsRenouvelles;
    }
}
