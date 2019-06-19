package sds.souscriptions.tache_metier;

import sds.souscriptions.concept_metier.Abonnement;
import sds.souscriptions.concept_metier.AbonnementRenouvellé;
import sds.souscriptions.concept_metier.AbonnementRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class RenouvellerAbonnementsAutomatiquement {

    private AbonnementRepository abonnementRepository;

    public RenouvellerAbonnementsAutomatiquement(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    public Collection<AbonnementRenouvellé> renouvelle(LocalDate jourDeFin) {
        Collection<Abonnement> abonnementsARenouveller = abonnementRepository.trouveAbonnementsFinissant(jourDeFin);

        Collection<AbonnementRenouvellé> abonnementsRenouvelles = abonnementsARenouveller.stream()
                .map(abonnement -> abonnement.renouvelle(jourDeFin))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        abonnementRepository.addOrReplaceAll(abonnementsARenouveller);

        return abonnementsRenouvelles;
    }
}
