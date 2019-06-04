package sds.souscriptions.concept_metier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TauxReductionTest {

    @Test
    void peut_être_créé_avec_valeur_entre_0_et_100() {
        assertThat(
                TauxReduction.de(37)
        ).isNotNull();
    }

    @Test
    void peut_être_créé_avec_valeur_0() {
        assertThat(
                TauxReduction.de(0)
        ).isNotNull();
    }

    @Test
    void peut_être_créé_avec_valeur_100() {
        assertThat(
                TauxReduction.de(100)
        ).isNotNull();
    }

    @Test
    void peut_pas_être_créé_avec_valeur_inférieur_à_0() {
        assertThatThrownBy(() ->
                TauxReduction.de(-1)
        ).isInstanceOf(
                TauxReduction.ValeurTauxErroné.class
        );
    }

    @Test
    void peut_pas_être_créé_avec_valeur_supérieur_à_100() {
        assertThatThrownBy(() ->
                TauxReduction.de(101)
        ).isInstanceOf(
                TauxReduction.ValeurTauxErroné.class
        );
    }
}