package sds.souscriptions.tache_metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.souscriptions.AbonnementRepositoryEnMemoire;
import sds.souscriptions.DateGenerateurEnMemoire;
import sds.souscriptions.FormuleGatewayEnMemoire;
import sds.souscriptions.IdAbonnementGenerateurDeInt;
import sds.souscriptions.concept_metier.*;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static sds.souscriptions.tache_metier.AbonnerProspectAFormuleTest.Constant.*;

class AbonnerProspectAFormuleTest {

    private AbonnementRepository abonnementRepository;
    private AbonnerProspectAFormule abonnerProspectAFormule;
    private ServiceDeNotification serviceDeNotification;


    @BeforeEach
    void setUp() {
        FormuleGateway formuleGateway = new FormuleGatewayEnMemoire(list(FormuleChoisie.avec(ID_FORMULE, PRIX_DE_BASE, DURÉE)));
        IdAbonnementGenerateur idAbonnementGenerateur = new IdAbonnementGenerateurDeInt();
        DateGenerateur dateGenerateur = new DateGenerateurEnMemoire(LE_23_AVRIL);
        serviceDeNotification = mock(ServiceDeNotification.class);

        abonnementRepository = new AbonnementRepositoryEnMemoire();
        abonnerProspectAFormule = new AbonnerProspectAFormule(idAbonnementGenerateur, formuleGateway, dateGenerateur, abonnementRepository, serviceDeNotification);

        abonnerProspectAFormule.abonne(PROSPECT, ID_FORMULE);
    }

    @Test
    void doit_stocker_abonnement() {
        assertThat(abonnementRepository.get(ID_GENERE)).isNotNull();
    }


    @Test
    void doit_aussi_envoyer_email_de_récap() {
        verify(serviceDeNotification).envoieRecapitulatif(PROSPECT, AbonnementSouscrit.avec(ID_GENERE, ID_FORMULE, PRIX_REDUIT, LE_23_AVRIL, LE_23_MAI));
    }

    static class Constant {
        static final Prospect PROSPECT = Prospect.avec("@mail.com", Etudiant.OUI);
        static final IdFormule ID_FORMULE = IdFormule.de("idf");
        static final Prix PRIX_DE_BASE = Prix.de(12.23);
        static final Prix PRIX_REDUIT = Prix.de(8.56);
        static final Durée DURÉE = Durée.MENSUELLE;
        static final IdAbonnement ID_GENERE = IdAbonnement.de("1");

        static final LocalDate LE_23_AVRIL = LocalDate.of(2019, Month.APRIL, 23);
        static final LocalDate LE_23_MAI = LocalDate.of(2019, Month.MAY, 23);


    }

}