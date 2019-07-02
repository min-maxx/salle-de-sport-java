package sds.offre.tache_metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.offre.FormuleRepositoryEnMemoire;
import sds.offre.concept_metier.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static sds.offre.tache_metier.ChangerLePrixDeFormuleTest.Constant.*;

class ChangerLePrixDeFormuleTest {

    private ChangerLePrixDeFormule changerLePrixDeFormule;
    private FormuleRepository formuleRepository;
    private ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées;

    @BeforeEach
    void setUp() {
        formuleRepository = new FormuleRepositoryEnMemoire();
        serviceDeProjectionDesDonnées = mock(ServiceDeProjectionDesDonnées.class);
        changerLePrixDeFormule = new ChangerLePrixDeFormule(formuleRepository, serviceDeProjectionDesDonnées);
    }

    @Test
    void doit_changer_prix_formule_existante() {
        Formule formule = new Formule(ID, ANCIEN_PRIX, Durée.AU_MOIS);
        formuleRepository.addOrReplace(formule);

        changerLePrixDeFormule.change(ID, NOUVEAU_PRIX);

        assertThat(formuleRepository.get(ID).prixDeBase()).isEqualTo(NOUVEAU_PRIX);
        verify(serviceDeProjectionDesDonnées).faitProjection(PrixFormuleChangee.de(ID, NOUVEAU_PRIX));
    }

    @Test
    void doit_pas_changer_prix_formule_quand_prix_est_identique() {
        Formule formule = new Formule(ID, ANCIEN_PRIX, Durée.AU_MOIS);
        formuleRepository.addOrReplace(formule);

        changerLePrixDeFormule.change(ID, ANCIEN_PRIX);

        verify(serviceDeProjectionDesDonnées, never()).faitProjection(any(PrixFormuleChangee.class));
    }

    static class Constant {
        static final IdFormule ID = IdFormule.de("22");
        static final Prix NOUVEAU_PRIX = Prix.de(22);
        static final Prix ANCIEN_PRIX = Prix.de(32);

    }
}