package sds.souscriptions.concept_metier;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.souscriptions.concept_metier.AbonnementTest.Constant.*;
import static sds.souscriptions.concept_metier.Durée.ANNUELLE;
import static sds.souscriptions.concept_metier.Durée.MENSUELLE;

class AbonnementTest {


    @Test
    void peut_être_créé_sans_reduction_et_pour_1_mois() {
        Abonnement abonnement = new Abonnement(ID_ABO, FormuleChoisie.avec(ID_FORMULE, PRIX, MENSUELLE), PROSPECT, LE_23_AVRIL);

        assertThat(abonnement.id()).isEqualTo(ID_ABO);
        assertThat(abonnement.idFormule()).isEqualTo(ID_FORMULE);
        assertThat(abonnement.prix()).isEqualTo(PRIX);
        assertThat(abonnement.jourDeSouscription()).isEqualTo(LE_23_AVRIL);
        assertThat(abonnement.jourDeFin()).isEqualTo(LE_23_MAI);
    }

    @Test
    void peut_être_créé_avec_reduction_et_pour_1_an() {
        Abonnement abonnement = new Abonnement(ID_ABO, FormuleChoisie.avec(ID_FORMULE, PRIX, ANNUELLE), PROSPECT, AVRIL_2018);

        assertThat(abonnement.id()).isEqualTo(ID_ABO);
        assertThat(abonnement.idFormule()).isEqualTo(ID_FORMULE);
        assertThat(abonnement.prix()).isEqualTo(PRIX_REDUIT);
        assertThat(abonnement.jourDeSouscription()).isEqualTo(AVRIL_2018);
        assertThat(abonnement.jourDeFin()).isEqualTo(AVRIL_2019);
    }

    @Test
    void peut_être_renouvellé_de_1_mois_quand_mensuel() {
        Abonnement abonnement = new Abonnement(ID_ABO, Formule(MENSUELLE), PROSPECT, LE_23_AVRIL);

        abonnement.renouvelle(LE_23_MAI);

        assertThat(abonnement.jourDeSouscription()).isEqualTo(LE_23_AVRIL);
        assertThat(abonnement.jourDeFin()).isEqualTo(LE_23_JUIN);
    }


    @Test
    void peut_être_renouvellé_de_1_an_quand_annuel() {
        Abonnement abonnement = new Abonnement(ID_ABO, Formule(ANNUELLE), PROSPECT, AVRIL_2018);

        abonnement.renouvelle(AVRIL_2019);

        assertThat(abonnement.jourDeSouscription()).isEqualTo(AVRIL_2018);
        assertThat(abonnement.jourDeFin()).isEqualTo(AVRIL_2020);
    }

    @Test
    void peut_pas_être_renouvellé_quand_pas_fini() {
        Abonnement abonnement = new Abonnement(ID_ABO, Formule(MENSUELLE), PROSPECT, LE_23_AVRIL);

        abonnement.renouvelle(AVRIL_2018);

        assertThat(abonnement.jourDeSouscription()).isEqualTo(LE_23_AVRIL);
        assertThat(abonnement.jourDeFin()).isEqualTo(LE_23_MAI);
    }

    static class Constant {

        static final LocalDate LE_23_AVRIL = LocalDate.of(2019, Month.APRIL, 23);
        static final LocalDate LE_23_MAI = LocalDate.of(2019, Month.MAY, 23);
        static final LocalDate LE_23_JUIN = LocalDate.of(2019, Month.JUNE, 23);
        static final LocalDate AVRIL_2018 = LocalDate.of(2018, Month.APRIL, 25);
        static final LocalDate AVRIL_2019 = LocalDate.of(2019, Month.APRIL, 25);
        static final LocalDate AVRIL_2020 = LocalDate.of(2020, Month.APRIL, 25);

        static final IdAbonnement ID_ABO = IdAbonnement.de("33");
        static final IdFormule ID_FORMULE = IdFormule.de("3");

        static final Prix PRIX = Prix.de(32);
        static final Prix PRIX_REDUIT = Prix.de(25.6);

        static final Prospect PROSPECT = Prospect.avec("", Etudiant.NON);

        static FormuleChoisie Formule(Durée durée) {
            return FormuleChoisie.avec(ID_FORMULE, PRIX, durée);
        }

    }
}