package sds.souscriptions.concept_metier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.souscriptions.concept_metier.FormuleChoisieTest.Constant.*;

class FormuleChoisieTest {

    @Test
    void doit_pas_appliquer_reduction_quand_mensuel_et_pour_non_etudiant() {
        assertThat(
                Formule(MENSUEL, 3.45).prixAbonnementPour(PROSPECT)
        ).isEqualTo(
                Prix.de(3.45)
        );
    }

    @Test
    void doit_appliquer_reduction_au_taux_annuel_quand_annuel_et_pour_non_etudiant() {
        assertThat(
                Formule(ANNUEL, 3.45).prixAbonnementPour(PROSPECT)
        ).isEqualTo(
                Prix.de(2.76)
        );
    }


    @Test
    void doit_appliquer_reduction_au_taux_etdudiant_quand_mensuel_et_pour_etudiant() {
        assertThat(
                Formule(MENSUEL, 3.45).prixAbonnementPour(PROSPECT_ETUDIANT)
        ).isEqualTo(
                Prix.de(2.42)
        );
    }

    @Test
    void doit_appliquer_reduction_au_taux_cumulé_quand_annuel_et_pour_etudiant() {
        assertThat(
                Formule(ANNUEL, 3.45).prixAbonnementPour(PROSPECT_ETUDIANT)
        ).isEqualTo(
                Prix.de(1.73)
        );
    }


    static class Constant {
        static final IdFormule ID = IdFormule.de("plop");
        static final Durée MENSUEL = Durée.MENSUELLE;
        static final Durée ANNUEL = Durée.ANNUELLE;
        static final Prospect PROSPECT = Prospect.avec("any@mail.com", Etudiant.NON);
        static final Prospect PROSPECT_ETUDIANT = Prospect.avec("any@mail.com", Etudiant.OUI);

        static FormuleChoisie Formule(Durée annuel, double valeur) {
            return FormuleChoisie.avec(ID, Prix.de(valeur), annuel);
        }

    }
}