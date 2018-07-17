package fr.octo.salle_de_sport.Abonnements.Query;

import fr.octo.salle_de_sport.Abonnements.Domain.Abonnement;
import fr.octo.salle_de_sport.Abonnements.Domain.AbonnementRepository;
import fr.octo.salle_de_sport.Abonnements.Domain.MaDate;
import fr.octo.salle_de_sport.Abonnements.Infrastructure.Database.AbonnementInMemoryRepository;
import fr.octo.salle_de_sport.Abonnés.Domain.Abonné;
import fr.octo.salle_de_sport.Formules.Domain.Formule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChiffreAffaireAbonnementsEnCoursQueryHandlerTest {

    @Test
    public void chiffre_d_affaire_avec_aucun_abonnement_en_cours() {

        ChiffreAffaireAbonnementsEnCoursQueryHandler tested = new ChiffreAffaireAbonnementsEnCoursQueryHandler(
            new AbonnementInMemoryRepository()
        );

        Double chiffreAffaire = tested.handle(new ChiffreAffaireAbonnementsEnCoursQuery(aujourdhui()));

        assertEquals(0, chiffreAffaire, 0);
    }

    @Test
    public void chiffre_d_affaire_avec_abonnements_en_cours() {

        Formule formule = Formule.nouvelleALAnnée(200.0);

        AbonnementRepository abonnementRepository = new AbonnementInMemoryRepository();

        abonnementRepository.store(
            new Abonnement(
                Abonné.nouveau("bob@octo.com", "Bob"),
                formule,
                aujourdhui()
            )
        );
        abonnementRepository.store(
            new Abonnement(
                Abonné.nouveau("lucy@octo.com", "Lucy"),
                formule,
                dansUnMois()
            )
        );

        ChiffreAffaireAbonnementsEnCoursQueryHandler tested = new ChiffreAffaireAbonnementsEnCoursQueryHandler(
            abonnementRepository
        );

        assertEquals(0, tested.handle(new ChiffreAffaireAbonnementsEnCoursQuery(aujourdhui())), 0);
        assertEquals(0, abonnementRepository.abonnementsEnCours(aujourdhui()).size());

        assertEquals(140, tested.handle(new ChiffreAffaireAbonnementsEnCoursQuery(dansUnMois())), 0);
        assertEquals(1, abonnementRepository.abonnementsEnCours(dansUnMois()).size());

        assertEquals(280, tested.handle(new ChiffreAffaireAbonnementsEnCoursQuery(dansDeuxMois())), 0);
        assertEquals(2, abonnementRepository.abonnementsEnCours(dansDeuxMois()).size());
    }

    private MaDate aujourdhui() {
        return new MaDate("2018-06-09");
    }

    private MaDate dansUnMois() {
        return new MaDate("2018-07-09");
    }

    private MaDate dansDeuxMois() {
        return new MaDate("2018-08-09");
    }
}
