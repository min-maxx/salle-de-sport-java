package sds.offre.tache_metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.offre.FormuleRepositoryEnMemoire;
import sds.offre.IdFormuleGenerateurDeInt;
import sds.offre.concept_metier.Durée;
import sds.offre.concept_metier.FormuleRepository;
import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.Prix;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.offre.tache_metier.CreerUneFormuleTest.Constant.*;


class CreerUneFormuleTest {

    private CreerUneFormule creerUneFormule;
    private FormuleRepository formuleRepository;

    @BeforeEach
    void setUp() {
        formuleRepository = new FormuleRepositoryEnMemoire();
        creerUneFormule = new CreerUneFormule(new IdFormuleGenerateurDeInt(), formuleRepository);
    }

    @Test
    void doit_creer_une_formule() {
        assertThat(
                creerUneFormule.crée(PRIX, DURÉE)
        ).isEqualTo(
                FormuleCreee.de(ID_GENERE, PRIX, DURÉE)
        );

        assertThat(
                formuleRepository.get(ID_GENERE)
        ).isNotNull();
    }

    static class Constant {
        static final Prix PRIX = new Prix(10);
        static final Durée DURÉE = Durée.AU_MOIS;
        static final IdFormule ID_GENERE = new IdFormule("1");

    }
}