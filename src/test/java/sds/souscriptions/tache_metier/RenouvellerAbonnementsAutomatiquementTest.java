package sds.souscriptions.tache_metier;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import sds.souscriptions.AbonnementRepositoryEnMemoire;
import sds.souscriptions.concept_metier.*;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class RenouvellerAbonnementsAutomatiquementTest {

    private AbonnementRepository abonnementRepository = new AbonnementRepositoryEnMemoire();

    @Test
    void doit_() {
        abonnementRepository.addOrReplaceAll(Lists.list(
                Abo(IdAbonnement.de("1"), Durée.MENSUELLE, LocalDate.of(2019, Month.JULY, 8)),
                Abo(IdAbonnement.de("2"), Durée.MENSUELLE, LocalDate.of(2019, Month.JUNE, 8)),
                Abo(IdAbonnement.de("3"), Durée.ANNUELLE, LocalDate.of(2018, Month.AUGUST, 8))));

        assertThat(
                new RenouvellerAbonnementsAutomatiquement(abonnementRepository).renouvelle(LocalDate.of(2019, Month.AUGUST, 8))
        ).contains(
                AbonnementRenouvelle.avec(IdAbonnement.de("1"), LocalDate.of(2019, Month.SEPTEMBER, 8))
        ).contains(
                AbonnementRenouvelle.avec(IdAbonnement.de("3"), LocalDate.of(2020, Month.AUGUST, 8))
        ).hasSize(2);
    }

    private Abonnement Abo(IdAbonnement id, Durée durée, LocalDate jourDeSouscription) {
        Abonnement abonnement = new Abonnement();
        abonnement.créé(id, FormuleChoisie.avec(IdFormule.de("2"), Prix.de(10.99), durée), Prospect.avec(Etudiant.NON), jourDeSouscription);
        return abonnement;
    }
}