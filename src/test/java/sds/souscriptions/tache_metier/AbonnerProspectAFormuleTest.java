package sds.souscriptions.tache_metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.souscriptions.AbonnementRepositoryEnMemoire;
import sds.souscriptions.DateGenerateurEnMemoire;
import sds.souscriptions.concept_metier.*;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;
import static sds.souscriptions.tache_metier.AbonnerProspectAFormuleTest.Constant.*;

class AbonnerProspectAFormuleTest {

    private IdAbonnementGenerateur idAbonnementGenerateur;
    private OffreFormules offreFormules;
    private AbonnementRepository abonnementRepository;
    private AbonnerProspectAFormule abonnerProspectAFormule;
    private DateGenerateur dateGenerateur;


    @BeforeEach
    void setUp() {
        abonnementRepository = new AbonnementRepositoryEnMemoire();
        offreFormules = new OffreFormulesEnMemoire(list(FormuleChoisie.avec(ID_FORMULE, PRIX_DE_BASE, DURÉE)));
        idAbonnementGenerateur = new IdAbonnementGenerateurDeInt();
        dateGenerateur = new DateGenerateurEnMemoire(LE_23_AVRIL);
        abonnerProspectAFormule = new AbonnerProspectAFormule(idAbonnementGenerateur, offreFormules, dateGenerateur, abonnementRepository);
    }

    @Test
    void doit_abonner_prospect_à_une_formule() {

        assertThat(
                abonnerProspectAFormule.abonne(Prospect.avec(Etudiant.OUI), ID_FORMULE)
        ).isEqualTo(
                AbonnementSouscrit.avec(ID_GENERE, ID_FORMULE, PRIX_REDUIT, LE_23_AVRIL, LE_23_MAI)
        );

        assertThat(
                abonnementRepository.get(ID_GENERE)
        ).isNotNull();
    }


    static class Constant {
        static final IdFormule ID_FORMULE = IdFormule.de("idf");
        static final Prix PRIX_DE_BASE = Prix.de(12.23);
        static final Prix PRIX_REDUIT = Prix.de(8.56);
        static final Durée DURÉE = Durée.MENSUELLE;
        static final IdAbonnement ID_GENERE = IdAbonnement.de("1");
        static final String EMAIL = "mail@com";

        static final LocalDate LE_23_AVRIL = LocalDate.of(2019, Month.APRIL, 23);
        static final LocalDate LE_23_MAI = LocalDate.of(2019, Month.MAY, 23);


    }

}