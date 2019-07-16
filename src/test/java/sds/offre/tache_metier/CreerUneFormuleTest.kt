package sds.offre.tache_metier

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sds.offre.FormuleRepositoryEnMemoire
import sds.offre.IdFormuleGenerateurDeInt
import sds.offre.concept_metier.Durée
import sds.offre.concept_metier.FormuleRepository
import sds.offre.concept_metier.IdFormule
import sds.offre.concept_metier.Prix


internal class CreerUneFormuleTest {

    lateinit var creerUneFormule: CreerUneFormule
    lateinit var formuleRepository: FormuleRepository

    @BeforeEach
    fun setUp() {
        formuleRepository = FormuleRepositoryEnMemoire()
        creerUneFormule = CreerUneFormule(IdFormuleGenerateurDeInt(), formuleRepository)
    }

    @Test
    fun doit_creer_une_formule() {
        assertThat(
                creerUneFormule.crée(PRIX, DURÉE)
        ).isEqualTo(
                FormuleCreee(ID_GENERE, PRIX, DURÉE)
        )

        assertThat(formuleRepository[ID_GENERE]).isNotNull()
    }

    companion object Constant {
        val PRIX = Prix(10)
        val DURÉE = Durée.AU_MOIS
        val ID_GENERE = IdFormule("1")

    }
}