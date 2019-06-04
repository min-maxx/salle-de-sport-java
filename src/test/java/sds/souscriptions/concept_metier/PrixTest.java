package sds.souscriptions.concept_metier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrixTest {

    @Test
    void doit_réduire_le_prix_de_moitié_quand_taux_est_50() {
        assertThat(
                Prix.de(7).applique(TauxReduction.de(50))
        ).isEqualTo(
                Prix.de(3.5)
        );
    }

}