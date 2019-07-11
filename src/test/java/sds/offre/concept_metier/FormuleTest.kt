package sds.offre.concept_metier

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FormuleTest {

    @Test
    fun peut_etre_creer() {
        val formule = Formule(ID, UN_MOIS, PRIX)

        assertThat(formule.id).isEqualTo(ID)
        assertThat(formule.durée).isEqualTo(UN_MOIS)
        assertThat(formule.prixDeBase).isEqualTo(PRIX)
    }

    @Test
    fun peut_changer_prix_s_il_est_different() {
        val formule = Formule(ID, UN_MOIS, PRIX)

        formule.changePrix(NOUVEAU_PRIX)

        assertThat(formule.prixDeBase).isEqualTo(NOUVEAU_PRIX)
    }

    companion object Constant {
        val ID = IdFormule.de("22")
        val NOUVEAU_PRIX = Prix.de(22)
        val PRIX = Prix.de(32)
        val UN_MOIS = Durée.AU_MOIS

    }
}