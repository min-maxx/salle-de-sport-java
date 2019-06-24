package sds.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.offre.concept_metier.Durée;
import sds.offre.concept_metier.FormuleCreee;
import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.Prix;
import sds.offre.tache_metier.CreerUneFormule;

import java.net.HttpURLConnection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerantFacadeTest_CreationFormule {

    static final FormuleCreee FORMULE = FormuleCreee.de(IdFormule.de("null"), null, null);
    private GerantFacade gerantFacade;

    @BeforeEach
    void setUp() {
        gerantFacade = new GerantFacade();
        gerantFacade.creerUneFormule = mock(CreerUneFormule.class);
        when(gerantFacade.creerUneFormule.crée(any(Prix.class), any(Durée.class)))
                .thenReturn(Optional.of(FORMULE));

    }

    @Test
    void doit_être_ok_quand_une_formule_mensuelle_est_créée() {
        int result = gerantFacade.GerantCrééUneFormule(200, 0);

        verify(gerantFacade.creerUneFormule).crée(Prix.de(200), Durée.AU_MOIS);
        assertThat(result).isEqualTo(HttpURLConnection.HTTP_OK);
    }

    @Test
    void doit_être_ok_quand_une_formule_annuelle_est_créée() {
        int result = gerantFacade.GerantCrééUneFormule(200, 1);

        verify(gerantFacade.creerUneFormule).crée(Prix.de(200), Durée.A_L_ANNEE);
        assertThat(result).isEqualTo(HttpURLConnection.HTTP_OK);
    }


    @Test
    void doit_être_bad_request_quand_montant_est_negatif() {
        int result = gerantFacade.GerantCrééUneFormule(-200, 1);

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
        verify(gerantFacade.creerUneFormule, never()).crée(any(Prix.class), any(Durée.class));

    }

    @Test
    void doit_être_bad_request_quand_durée_est_negatif() {
        int result = gerantFacade.GerantCrééUneFormule(200, -1);

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
        verify(gerantFacade.creerUneFormule, never()).crée(any(Prix.class), any(Durée.class));
    }

    @Test
    void doit_être_bad_request_quand_durée_est_2() {
        int result = gerantFacade.GerantCrééUneFormule(200, 2);

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
        verify(gerantFacade.creerUneFormule, never()).crée(any(Prix.class), any(Durée.class));
    }

    @Test
    void doit_être_ko_quand_création_est_interrompu() {

        when(gerantFacade.creerUneFormule.crée(any(Prix.class), any(Durée.class)))
                .thenThrow(new RuntimeException());

        int result = gerantFacade.GerantCrééUneFormule(200, 1);

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }


    @Test
    void doit_être_ko_quand_création_échoue() {

        when(gerantFacade.creerUneFormule.crée(any(Prix.class), any(Durée.class)))
                .thenReturn(Optional.empty());

        int result = gerantFacade.GerantCrééUneFormule(200, 0);

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

}