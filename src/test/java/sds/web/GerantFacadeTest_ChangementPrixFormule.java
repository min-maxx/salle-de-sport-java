package sds.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.Prix;
import sds.offre.concept_metier.PrixFormuleChangee;
import sds.offre.tache_metier.ChangerLePrixDeFormule;

import java.net.HttpURLConnection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerantFacadeTest_ChangementPrixFormule {

    private static final PrixFormuleChangee FORMULE = PrixFormuleChangee.de(IdFormule.de("null"), null);
    private GerantFacade gerantFacade;

    @BeforeEach
    void setUp() {
        gerantFacade = new GerantFacade();
        gerantFacade.changerLePrixDeFormule = mock(ChangerLePrixDeFormule.class);
        when(gerantFacade.changerLePrixDeFormule.change(any(IdFormule.class), any(Prix.class)))
                .thenReturn(Optional.of(FORMULE));

    }

    @Test
    void doit_être_ok_quand_une_formule_est_changée() {
        int result = gerantFacade.GerantChangeLePrixDuneFormule("32", 150);

        verify(gerantFacade.changerLePrixDeFormule).change(IdFormule.de("32"), Prix.de(150));
        assertThat(result).isEqualTo(HttpURLConnection.HTTP_OK);
    }


    @Test
    void doit_être_bad_request_quand_montant_est_negatif() {
        int result = gerantFacade.GerantChangeLePrixDuneFormule("32", -150);

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
        verify(gerantFacade.changerLePrixDeFormule, never()).change(any(IdFormule.class), any(Prix.class));

    }

    @Test
    void doit_être_ko_quand_création_est_interrompu() {
        when(gerantFacade.changerLePrixDeFormule.change(any(IdFormule.class), any(Prix.class)))
                .thenThrow(new RuntimeException());

        int result = gerantFacade.GerantChangeLePrixDuneFormule("32", 150);

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

}