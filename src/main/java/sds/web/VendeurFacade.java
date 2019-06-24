package sds.web;

import sds.offre.infra.FormuleRepositoryEnPostgreSQL;
import sds.offre.tache_metier.ConsulterUneFormule;
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
 * Pour voir un exemple de tests, se rendre à la classe GerantFacade.java
 *
 * @see GerantFacade
 */
public class VendeurFacade {

    private final FormuleRepositoryEnPostgreSQL formuleRepository = new FormuleRepositoryEnPostgreSQL();
    private final ConsulterUneFormule consulterUneFormule = new ConsulterUneFormule(formuleRepository);
    private final OffreFormulesExternes offreFormules = new OffreFormulesExternes(consulterUneFormule);
    private final AbonnementRepositoryEnPostgreSQL abonnementRepository = new AbonnementRepositoryEnPostgreSQL();

    AbonnerProspectAFormule abonnerProspectAFormule = new AbonnerProspectAFormule(new IdAbonnementGenerateurDeUUID(), offreFormules, new DateGenerateurEnJava(), abonnementRepository);

    public int VendeurAbonneProspectAFormule(int indexEtudiant, String id) {
        try {
            Etudiant etudiant = Etudiant.values()[indexEtudiant];
            IdFormule idFormule = IdFormule.de(id);
            try {
                //HERE Authent. du Vendeur
                abonnerProspectAFormule.abonne(Prospect.avec(etudiant), idFormule);
                return HTTP_OK;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }
}
