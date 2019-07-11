package sds.offre.concept_metier

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FormuleTest {

    @Test
    fun `peut être créer`() {
        val formule = Formule(ID, UN_MOIS, PRIX)

        assertThat(formule.id).isEqualTo(ID)
        assertThat(formule.durée).isEqualTo(UN_MOIS)
        assertThat(formule.prixDeBase).isEqualTo(PRIX)
    }

    @Test
    fun `peut changer de prix s'il est différent`() {
        val formule = Formule(ID, UN_MOIS, PRIX)

        formule.changePrix(NOUVEAU_PRIX)

        assertThat(formule.prixDeBase).isEqualTo(NOUVEAU_PRIX)
    }

    companion object Constant {
        val ID = IdFormule("22")
        val NOUVEAU_PRIX = Prix(22)
        val PRIX = Prix(32)
        val UN_MOIS = Durée.AU_MOIS

    }
}