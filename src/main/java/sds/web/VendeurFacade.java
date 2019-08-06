package sds.web;

import sds.notification_email.infra.AbonnéRepositoryEnPostgreSQL;
import sds.notification_email.infra.EnvoyeurDeEmailMailChimp;
import sds.notification_email.tache_metier.EnvoyerEmailRecapitulatif;
import sds.offre.infra.FormuleRepositoryEnPostgreSQL;
import sds.offre.tache_metier.ConsulterUneFormule;
import sds.souscriptions.concept_metier.Etudiant;
import sds.souscriptions.concept_metier.IdFormule;
import sds.souscriptions.concept_metier.Prospect;
import sds.souscriptions.infra.*;
import sds.souscriptions.tache_metier.AbonnementSouscrit;
import sds.souscriptions.tache_metier.AbonnerProspectAFormule;

import javax.ws.rs.POST;

import static java.net.HttpURLConnection.*;

/**
 * Pour voir un exemple de tests, se rendre à la classe
 * @see GerantFacade
 */
public class VendeurFacade {

    // dépendances indirectes
    private final FormuleGatewayOffre offreFormules = new FormuleGatewayOffre(new ConsulterUneFormule(new FormuleRepositoryEnPostgreSQL()));
    private EnvoyerEmailRecapitulatif envoyerEmailRecapitulatif = new EnvoyerEmailRecapitulatif(new AbonnéRepositoryEnPostgreSQL(), new EnvoyeurDeEmailMailChimp());

    // dépendances directes à mock dans les tests
    AbonnerProspectAFormule abonnerProspectAFormule = new AbonnerProspectAFormule(new IdAbonnementGenerateurDeUUID(), offreFormules, new DateGenerateurEnJava(), new AbonnementRepositoryEnPostgreSQL(), new ServiceDeNotificationParEmail(envoyerEmailRecapitulatif));

    @POST
    public int VendeurAbonneProspectAFormule(int indexEtudiant, String id, String email) {
        try {
            Etudiant etudiant = Etudiant.values()[indexEtudiant];
            IdFormule idFormule = IdFormule.de(id);
            try {
                //HERE Authent. du Vendeur
                AbonnementSouscrit abonnementSouscrit = abonnerProspectAFormule.abonne(Prospect.avec(email, etudiant), idFormule);
                return HTTP_OK;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }
}
