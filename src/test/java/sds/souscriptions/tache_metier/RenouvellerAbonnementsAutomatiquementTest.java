package sds.souscriptions.tache_metier;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import sds.souscriptions.AbonnementRepositoryEnMemoire;
import sds.souscriptions.concept_metier.*;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquementTest.Constant.*;

class RenouvellerAbonnementsAutomatiquementTest {


    private AbonnementRepository abonnementRepository = new AbonnementRepositoryEnMemoire();

    @Test
    void doit_renouveller_abonnements_quand_ils_sont_finis() {
        abonnementRepository.addOrReplaceAll(Lists.list(
                Abo(IdAbonnement.de("1"), Durée.MENSUELLE, JUILLET_2019),
                Abo(IdAbonnement.de("2"), Durée.MENSUELLE, JUIN_2019),
                Abo(IdAbonnement.de("3"), Durée.ANNUELLE, AOUT_2018),
                Abo(IdAbonnement.de("4"), Durée.ANNUELLE, JUIN_2019)));

        assertThat(
                new RenouvellerAbonnementsAutomatiquement(abonnementRepository).renouvelle(AOUT_2019)
        ).contains(
                AbonnementRenouvellé.avec(IdAbonnement.de("1"), SEPTEMBRE_2019)
        ).contains(
                AbonnementRenouvellé.avec(IdAbonnement.de("3"), AOUT_2020)
        ).hasSize(2);
    }


    private Abonnement Abo(IdAbonnement id, Durée durée, LocalDate jourDeSouscription) {
        FormuleChoisie formuleChoisie = FormuleChoisie.avec(IdFormule.de("2"), Prix.de(10.99), durée);
        Prospect prospect = Prospect.avec("any", Etudiant.NON);
        return new Abonnement(id, formuleChoisie, prospect, jourDeSouscription);
    }

    static class Constant {

        static final LocalDate JUILLET_2019 = LocalDate.of(2019, Month.JULY, 8);
        static final LocalDate JUIN_2019 = LocalDate.of(2019, Month.JUNE, 8);
        static final LocalDate AOUT_2018 = LocalDate.of(2018, Month.AUGUST, 8);
        static final LocalDate AOUT_2019 = LocalDate.of(2019, Month.AUGUST, 8);
        static final LocalDate SEPTEMBRE_2019 = LocalDate.of(2019, Month.SEPTEMBER, 8);
        static final LocalDate AOUT_2020 = LocalDate.of(2020, Month.AUGUST, 8);

    }
}