package sds.offre.tache_metier

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sds.offre.FormuleRepositoryEnMemoire
import sds.offre.concept_metier.*

internal class ChangerLePrixDeFormuleTest {

    lateinit var changerLePrixDeFormule: ChangerLePrixDeFormule
    lateinit var formuleRepository: FormuleRepository

    @BeforeEach
    fun setUp() {
        formuleRepository = FormuleRepositoryEnMemoire()
        changerLePrixDeFormule = ChangerLePrixDeFormule(formuleRepository)
    }

    @Test
    fun `doit changer prix formule existante`() {
        val formule = Formule(ID, Durée.AU_MOIS, ANCIEN_PRIX)
        formuleRepository.addOrReplace(formule)

        assertThat(
                changerLePrixDeFormule.change(ID, NOUVEAU_PRIX)
        ).hasValue(
                PrixFormuleChangee(ID, NOUVEAU_PRIX)
        )

        assertThat(
                formuleRepository[ID].prixDeBase
        ).isEqualTo(
                NOUVEAU_PRIX)
    }

    @Test
    fun `doit pas changer prix formule quand prix est identique`() {
        val formule = Formule(ID, Durée.AU_MOIS, ANCIEN_PRIX)
        formuleRepository.addOrReplace(formule)

        assertThat(
                changerLePrixDeFormule.change(ID, ANCIEN_PRIX)
        ).isEmpty
    }

    companion object Constant {
        val ID = IdFormule("22")
        val NOUVEAU_PRIX = Prix(22)
        val ANCIEN_PRIX = Prix(32)
    }
}