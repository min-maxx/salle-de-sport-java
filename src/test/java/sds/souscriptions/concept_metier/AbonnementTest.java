package sds.souscriptions.concept_metier;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.souscriptions.concept_metier.AbonnementTest.Constant.*;
import static sds.souscriptions.concept_metier.Durée.ANNUELLE;
import static sds.souscriptions.concept_metier.Durée.MENSUELLE;

class AbonnementTest {


    @Test
    void peut_être_créé_sans_reduction_et_pour_1_mois() {
        Abonnement abonnement = new Abonnement(ID_ABO, FormuleChoisie.avec(ID_FORMULE, PRIX, MENSUELLE), PROSPECT, LE_23_AVRIL);

        assertThat(
                abonnement.changements().get(0)
        ).isEqualTo(
                AbonnementSouscrit.avec(ID_ABO, ID_FORMULE, PRIX, MENSUELLE, LE_23_AVRIL, LE_23_MAI)
        );
    }

    @Test
    void peut_être_créé_avec_reduction_et_pour_1_an() {
        Abonnement abonnement = new Abonnement(ID_ABO, FormuleChoisie.avec(ID_FORMULE, PRIX, ANNUELLE), PROSPECT, AVRIL_2018);

        assertThat(
                abonnement.changements().get(0)
        ).isEqualTo(
                AbonnementSouscrit.avec(ID_ABO, ID_FORMULE, PRIX_REDUIT, ANNUELLE, AVRIL_2018, AVRIL_2019)
        );
    }

    @Test
    void peut_être_renouvellé_de_1_mois_quand_mensuel() {
        Abonnement abonnement = new Abonnement(List.of(AboSouscrit(ID_ABO, MENSUELLE, LE_23_AVRIL, LE_23_MAI)));
        abonnement.renouvelle(LE_23_MAI);

        assertThat(
                abonnement.changements().get(0)
        ).isEqualTo(
                AbonnementRenouvellé.avec(ID_ABO, LE_23_JUIN)
        );
    }


    @Test
    void peut_être_renouvellé_de_1_an_quand_annuel() {
        Abonnement abonnement = new Abonnement(List.of(AboSouscrit(ID_ABO, ANNUELLE, AVRIL_2018, AVRIL_2019)));

        abonnement.renouvelle(AVRIL_2019);

        assertThat(
                abonnement.changements().get(0)
        ).isEqualTo(
                AbonnementRenouvellé.avec(ID_ABO, AVRIL_2020)
        );

    }

    @Test
    void peut_pas_être_renouvellé_quand_pas_fini() {
        Abonnement abonnement = new Abonnement(List.of(AboSouscrit(ID_ABO, MENSUELLE, AVRIL_2018, AVRIL_2019)));

        abonnement.renouvelle(AVRIL_2018);

        assertThat(
                abonnement.changements()
        ).isEmpty();
    }

    @Test
    void peut_être_renouvellé_plusieurs_fois() {
        Abonnement abonnement = new Abonnement(List.of(AboSouscrit(ID_ABO, MENSUELLE, LE_23_AVRIL, LE_23_MAI)));
        abonnement.renouvelle(LE_23_MAI);
        abonnement.renouvelle(LE_23_JUIN);

        assertThat(
                abonnement.changements()
        ).contains(
                AbonnementRenouvellé.avec(ID_ABO, LE_23_JUILLET)
        );
    }

    static class Constant {

        static final LocalDate LE_23_AVRIL = LocalDate.of(2019, Month.APRIL, 23);
        static final LocalDate LE_23_MAI = LocalDate.of(2019, Month.MAY, 23);
        static final LocalDate LE_23_JUIN = LocalDate.of(2019, Month.JUNE, 23);
        static final LocalDate LE_23_JUILLET = LocalDate.of(2019, Month.JULY, 23);
        static final LocalDate AVRIL_2018 = LocalDate.of(2018, Month.APRIL, 23);
        static final LocalDate AVRIL_2019 = LocalDate.of(2019, Month.APRIL, 23);
        static final LocalDate AVRIL_2020 = LocalDate.of(2020, Month.APRIL, 23);

        static final IdAbonnement ID_ABO = IdAbonnement.de("33");
        static final IdFormule ID_FORMULE = IdFormule.de("3");

        static final Prix PRIX = Prix.de(32);
        static final Prix PRIX_REDUIT = Prix.de(25.6);

        static final Prospect PROSPECT = Prospect.avec(Etudiant.NON);

        static AbonnementSouscrit AboSouscrit(IdAbonnement idAbo, Durée durée, LocalDate souscription, LocalDate fin) {
            return AbonnementSouscrit.avec(idAbo, null, null, durée, souscription, fin);
        }

    }
}