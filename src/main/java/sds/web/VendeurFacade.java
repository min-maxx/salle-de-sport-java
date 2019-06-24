package sds.web;

import sds.notification_email.concept_metier.*;
import sds.notification_email.infra.EnvoyeurDeEmailMailChimp;
import sds.notification_email.tache_metier.EnvoyerEmailRecapitulatif;
import sds.offre.infra.FormuleRepositoryEnPostgreSQL;
import sds.offre.tache_metier.ConsulterUneFormule;
import sds.souscriptions.concept_metier.AbonnementSouscrit;
import sds.souscriptions.concept_metier.Etudiant;
import sds.souscriptions.concept_metier.IdFormule;
import sds.souscriptions.concept_metier.Prospect;
import sds.souscriptions.infra.AbonnementRepositoryEnPostgreSQL;
import sds.souscriptions.infra.DateGenerateurEnJava;
import sds.souscriptions.infra.IdAbonnementGenerateurDeUUID;
import sds.souscriptions.infra.OffreFormulesExternes;
import sds.souscriptions.tache_metier.AbonnerProspectAFormule;

import static java.net.HttpURLConnection.*;

/**
 * Pour voir un exemple de tests, se rendre à la classe
 * @see GerantFacade
 */
public class VendeurFacade {

    // dépendances indirectes
    private final FormuleRepositoryEnPostgreSQL formuleRepository = new FormuleRepositoryEnPostgreSQL();
    private final ConsulterUneFormule consulterUneFormule = new ConsulterUneFormule(formuleRepository);
    private final OffreFormulesExternes offreFormules = new OffreFormulesExternes(consulterUneFormule);
    private final AbonnementRepositoryEnPostgreSQL abonnementRepository = new AbonnementRepositoryEnPostgreSQL();

    private final EnvoyeurDeEmail envoyeurDeEmailMailChimp = new EnvoyeurDeEmailMailChimp();
    // dépendances directes à mock dans les tests
    EnvoyerEmailRecapitulatif envoyerEmailRecapitulatif = new EnvoyerEmailRecapitulatif(envoyeurDeEmailMailChimp);
    AbonnerProspectAFormule abonnerProspectAFormule = new AbonnerProspectAFormule(new IdAbonnementGenerateurDeUUID(), offreFormules, new DateGenerateurEnJava(), abonnementRepository);

    public int VendeurAbonneProspectAFormule(int indexEtudiant, String id, String email) {
        try {
            Etudiant etudiant = Etudiant.values()[indexEtudiant];
            IdFormule idFormule = IdFormule.de(id);
            try {
                //HERE Authent. du Vendeur
                AbonnementSouscrit abonnementSouscrit = abonnerProspectAFormule.abonne(Prospect.avec(etudiant, email), idFormule);
                envoyerEmailRecapitulatif.envoie(enAbonné(abonnementSouscrit), enAbonnementDetail(abonnementSouscrit));
                return HTTP_OK;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }

    private AbonnementDetail enAbonnementDetail(AbonnementSouscrit abonnementSouscrit) {
        return new AbonnementDetail(abonnementSouscrit.jourDeFin);
    }

    private Abonné enAbonné(AbonnementSouscrit abonnementSouscrit) {
        return Abonné.avec(IdAbonné.de(abonnementSouscrit.id.valeur()), AdresseEmail.de(abonnementSouscrit.email), abonnementSouscrit.jourDeSouscription);
    }
}
