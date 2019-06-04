package sds.offre.concept_metier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.offre.concept_metier.FormuleTest.Constant.Formule;
import static sds.offre.concept_metier.FormuleTest.Constant.*;

class FormuleTest {


    @Test
    void peut_etre_creer() {
        assertThat(
                new Formule().créé(ID, PRIX, UN_MOIS)
        ).hasValue(
                FormuleCreee.de(ID, PRIX, UN_MOIS));
    }

    @Test
    void peut_changer_prix_s_il_est_different() {
        assertThat(
                Formule(ID, PRIX, UN_MOIS).changePrix(NOUVEAU_PRIX)
        ).hasValue(
                PrixFormuleChangee.de(ID, NOUVEAU_PRIX));
    }

    @Test
    void peut_pas_etre_creer_2_fois() {
        assertThat(
                Formule(ID, PRIX, UN_MOIS).créé(ID, NOUVEAU_PRIX, UN_MOIS)
        ).isEmpty();
    }

    @Test
    void peut_pas_changer_prix_si_pas_creee() {
        assertThat(
                new Formule().changePrix(PRIX)
        ).isEmpty();
    }

    @Test
    void peut_pas_changer_prix_s_il_est_identique() {

        assertThat(
                Formule(ID, PRIX, UN_MOIS).changePrix(PRIX)
        ).isEmpty();
    }


    static class Constant {
        static final IdFormule ID = IdFormule.de("22");
        static final Prix NOUVEAU_PRIX = Prix.de(22);
        static final Prix PRIX = Prix.de(32);
        static final Durée UN_MOIS = Durée.AU_MOIS;

        static Formule Formule(IdFormule id, Prix prix, Durée durée) {
            Formule formule = new Formule();
            formule.créé(id, prix, durée);
            return formule;
        }
    }
}