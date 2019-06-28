package sds.offre.concept_metier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.offre.concept_metier.FormuleTest.Constant.*;

class FormuleTest {

    @Test
    void peut_etre_creer() {
        assertThat(
                new Formule(ID, PRIX, UN_MOIS)
        ).isEqualTo(
                new Formule(ID, PRIX, UN_MOIS));
    }

    @Test
    void peut_changer_prix_s_il_est_different() {
        Formule formule = new Formule(ID, PRIX, UN_MOIS);
        formule.changePrix(NOUVEAU_PRIX);
        assertThat(
                formule.prixDeBase()
        ).isEqualTo(
                NOUVEAU_PRIX
        );
    }

    static class Constant {
        static final IdFormule ID = IdFormule.de("22");
        static final Prix NOUVEAU_PRIX = Prix.de(22);
        static final Prix PRIX = Prix.de(32);
        static final Durée UN_MOIS = Durée.AU_MOIS;

    }
}