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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static sds.offre.tache_metier.CreerUneFormuleTest.Constant.*;


class CreerUneFormuleTest {

    private CreerUneFormule creerUneFormule;
    private FormuleRepository formuleRepository;
    private ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées;

    @BeforeEach
    void setUp() {
        formuleRepository = new FormuleRepositoryEnMemoire();
        serviceDeProjectionDesDonnées = mock(ServiceDeProjectionDesDonnées.class);
        creerUneFormule = new CreerUneFormule(new IdFormuleGenerateurDeInt(), formuleRepository, serviceDeProjectionDesDonnées);
    }

    @Test
    void doit_creer_une_formule() {
        creerUneFormule.crée(PRIX, DURÉE);

        assertThat(formuleRepository.get(ID_GENERE)).isNotNull();
        verify(serviceDeProjectionDesDonnées).faitProjection(FormuleCreee.de(ID_GENERE, PRIX, DURÉE));
    }

    static class Constant {
        static final Prix PRIX = Prix.de(10);
        static final Durée DURÉE = Durée.AU_MOIS;
        static final IdFormule ID_GENERE = IdFormule.de("1");

    }
}