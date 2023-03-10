package sds.offre.concept_metier;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.offre.concept_metier.FormuleTest.Constant.*;

class FormuleTest {


    @Test
    void peut_etre_creer() {
        Formule formule = new Formule(ID, PRIX, UN_MOIS);

        assertThat(
                formule.changements().get(0)
        ).isEqualTo(
                FormuleCreee.de(ID, PRIX, UN_MOIS));
    }

    @Test
    void peut_changer_prix_s_il_est_different() {
        Formule formule = new Formule(List.of(FormuleCreee.de(ID, PRIX, UN_MOIS)));

        formule.changePrix(NOUVEAU_PRIX);

        assertThat(
                formule.changements().get(0)
        ).isEqualTo(
                PrixFormuleChangee.de(ID, NOUVEAU_PRIX));
    }

    @Test
    void peut_pas_changer_prix_s_il_est_identique() {
        Formule formule = new Formule(List.of(FormuleCreee.de(ID, PRIX, UN_MOIS)));

        formule.changePrix(PRIX);

        assertThat(
                formule.changements()
        ).isEmpty();
    }


    static class Constant {
        static final IdFormule ID = IdFormule.de("abc123");
        static final Prix NOUVEAU_PRIX = Prix.de(22);
        static final Prix PRIX = Prix.de(32);
        static final Durée UN_MOIS = Durée.AU_MOIS;

    }
}