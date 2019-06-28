package sds.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.notification_email.concept_metier.AbonnementDetail;
import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.AdresseEmail;
import sds.notification_email.concept_metier.IdAbonné;
import sds.notification_email.tache_metier.EmailRecapitulatifEnvoyé;
import sds.notification_email.tache_metier.EnvoyerEmailRecapitulatif;
import sds.souscriptions.concept_metier.Etudiant;
import sds.souscriptions.concept_metier.IdAbonnement;
import sds.souscriptions.concept_metier.IdFormule;
import sds.souscriptions.concept_metier.Prospect;
import sds.souscriptions.tache_metier.AbonnementSouscrit;
import sds.souscriptions.tache_metier.AbonnerProspectAFormule;

import java.net.HttpURLConnection;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static sds.web.VendeurFacadeTest.Constant.*;

/**
 * Mockito et utilisation de 'any' :
 * - on utilise 'any' pour les 'when' pour être ouvert à l'évolution
 * - on n'utilise pas 'any' pour les 'verify' pour être strict sur le résultat
 * - on utilise 'any' pour les 'verify (never)' pour être sûr qu'aucun appel est fait quels que soient les params
 */
class VendeurFacadeTest {

    private VendeurFacade vendeurFacade;

    @BeforeEach
    void setUp() {
        vendeurFacade = new VendeurFacade();
        vendeurFacade.abonnerProspectAFormule = mock(AbonnerProspectAFormule.class);
        vendeurFacade.envoyerEmailRecapitulatif = mock(EnvoyerEmailRecapitulatif.class);

        when(vendeurFacade.abonnerProspectAFormule.abonne(any(Prospect.class), any(IdFormule.class)))
                .thenReturn(AbonnementSouscrit.avec(IdAbonnement.de(ID_ABO), null, null, SOUSCRIPTION, FIN_ABO));

        when(vendeurFacade.envoyerEmailRecapitulatif.envoie(any(Abonné.class), any(AbonnementDetail.class)))
                .thenReturn(EmailRecapitulatifEnvoyé.avec(null, null));
    }

    @Test
    void doit_être_ok_quand_abonnement_est_souscrit_et_email_de_recap_envoyé() {
        int result = vendeurFacade.VendeurAbonneProspectAFormule(INDEX_NON_ETUDIANT, ID_FORMULE, EMAIL);

        verify(vendeurFacade.abonnerProspectAFormule).abonne(Prospect.avec(Etudiant.NON), IdFormule.de(ID_FORMULE));
        verify(vendeurFacade.envoyerEmailRecapitulatif).envoie(Abonné.avec(IdAbonné.de(ID_ABO), AdresseEmail.de(EMAIL), SOUSCRIPTION), AbonnementDetail.avec(FIN_ABO));

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_OK);
    }

    @Test
    void doit_être_ok_quand_abonnement_etudiant_est_souscrit_et_email_de_recap_envoyé() {
        int result = vendeurFacade.VendeurAbonneProspectAFormule(INDEX_ETUDIANT, ID_FORMULE, EMAIL);

        verify(vendeurFacade.abonnerProspectAFormule).abonne(Prospect.avec(Etudiant.OUI), IdFormule.de(ID_FORMULE));
        verify(vendeurFacade.envoyerEmailRecapitulatif).envoie(Abonné.avec(IdAbonné.de(ID_ABO), AdresseEmail.de(EMAIL), SOUSCRIPTION), AbonnementDetail.avec(FIN_ABO));

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_OK);
    }

    @Test
    void doit_être_bad_request_quand_etudiant_est_négatif() {
        int result = vendeurFacade.VendeurAbonneProspectAFormule(-1, ID_FORMULE, EMAIL);

        verify(vendeurFacade.abonnerProspectAFormule, never()).abonne(any(Prospect.class), any(IdFormule.class));
        verify(vendeurFacade.envoyerEmailRecapitulatif, never()).envoie(any(Abonné.class), any(AbonnementDetail.class));

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test
    void doit_être_bad_request_quand_etudiant_est_trop_grand() {
        int result = vendeurFacade.VendeurAbonneProspectAFormule(2, ID_FORMULE, EMAIL);

        verify(vendeurFacade.abonnerProspectAFormule, never()).abonne(any(Prospect.class), any(IdFormule.class));
        verify(vendeurFacade.envoyerEmailRecapitulatif, never()).envoie(any(Abonné.class), any(AbonnementDetail.class));

        assertThat(result).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }


    @Test
    void doit_être_ko_quand_souscription_echoue() {

        when(vendeurFacade.abonnerProspectAFormule.abonne(any(Prospect.class), any(IdFormule.class)))
                .thenThrow(new RuntimeException()); // surcharge le setup

        int result = vendeurFacade.VendeurAbonneProspectAFormule(-INDEX_ETUDIANT, ID_FORMULE, EMAIL);

        verify(vendeurFacade.envoyerEmailRecapitulatif, never()).envoie(any(Abonné.class), any(AbonnementDetail.class));
        assertThat(result).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);

    }

    @Test
    void doit_être_ok_quand_abonnement_souscrit_même_si_email_de_recap_pas_envoyé() {
        when(vendeurFacade.envoyerEmailRecapitulatif.envoie(any(Abonné.class), any(AbonnementDetail.class)))
                .thenThrow(new RuntimeException()); // surcharge le setup


        int result = vendeurFacade.VendeurAbonneProspectAFormule(0, ID_FORMULE, EMAIL);

        verify(vendeurFacade.abonnerProspectAFormule).abonne(Prospect.avec(Etudiant.NON), IdFormule.de(ID_FORMULE));
        assertThat(result).isEqualTo(HttpURLConnection.HTTP_OK);
    }

    static class Constant {
        static final String ID_ABO = "yxz";
        static final LocalDate FIN_ABO = LocalDate.now();
        static final int INDEX_ETUDIANT = 1;
        static final LocalDate SOUSCRIPTION = LocalDate.now().minusYears(INDEX_ETUDIANT);
        static final String EMAIL = "mail@com";
        static final String ID_FORMULE = "abc";
        static final int INDEX_NON_ETUDIANT = 0;
    }

}