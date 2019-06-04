package sds.souscriptions.concept_metier;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.souscriptions.concept_metier.AbonnementTest.Constant.*;

class AbonnementTest {

    @Test
    void peut_être_créer() {
        assertThat(
                new Abonnement().créé(ID_ABO, FormuleChoisie.avec(ID_FORMULE, PRIX, MENSUELLE), Prospect.avec(Etudiant.NON), LE_23_AVRIL)
        ).isEqualTo(
                AbonnementSouscrit.avec(ID_ABO, ID_FORMULE, PRIX, LE_23_AVRIL, LE_23_MAI)
        );
    }

    static class Constant {

        static final LocalDate LE_23_AVRIL = LocalDate.of(2019, Month.APRIL, 23);
        static final LocalDate LE_23_MAI = LocalDate.of(2019, Month.MAY, 23);

        static final IdAbonnement ID_ABO = IdAbonnement.de("33");
        static final IdFormule ID_FORMULE = IdFormule.de("3");

        static final Prix PRIX = Prix.de(32);
        static final Durée MENSUELLE = Durée.MENSUELLE;

    }
}