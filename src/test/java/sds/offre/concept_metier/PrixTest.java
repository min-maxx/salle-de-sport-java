package sds.offre.concept_metier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PrixTest {

    @Test
    void doit_créer_un_prix_positif() {

        assertThat(
                Prix.de(34)
        ).isEqualTo(
                Prix.de(34));

    }

    @Test
    void doit_pas_créer_un_prix_négatif() {

        assertThatThrownBy(() ->
                Prix.de(-34)
        ).isInstanceOf(
                IllegalArgumentException.class
        );

    }
}
