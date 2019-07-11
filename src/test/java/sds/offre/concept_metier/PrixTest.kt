package sds.offre.concept_metier

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class PrixTest {

    @Test
    fun `doit créer un prix positif`() {
        assertThat(Prix(34))
                .isEqualTo(Prix(34))

    }

    @Test
    fun `doit pas créer un prix négatif`() {
        assertThatThrownBy { Prix(-34) }
                .isInstanceOf(IllegalArgumentException::class.java)
    }
}
