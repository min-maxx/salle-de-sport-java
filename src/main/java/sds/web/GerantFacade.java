package sds.web;

import sds.gérant.ConsulterLesFormules;
import sds.gérant.FormuleDao;
import sds.gérant.FormuleDto;
import sds.offre.concept_metier.*;
import sds.offre.infra.FormuleRepositoryEnPostgreSQL;
import sds.offre.infra.IdFormuleGenerateurDeUUID;
import sds.offre.infra.ServiceDeProjectionDesDonnéesVersGérant;
import sds.offre.tache_metier.ChangerLePrixDeFormule;
import sds.offre.tache_metier.CreerUneFormule;
import sds.offre.tache_metier.ServiceDeProjectionDesDonnées;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.infra.AbonnementRepositoryEnPostgreSQL;
import sds.souscriptions.tache_metier.ConsulterAbonnementsParFormule;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Collection;

import static java.net.HttpURLConnection.*;

public class GerantFacade {

    // dépendances indirectes
    private final FormuleRepository formuleRepository = new FormuleRepositoryEnPostgreSQL();
    private final AbonnementRepository abonnementRepository = new AbonnementRepositoryEnPostgreSQL();
    private final IdFormuleGenerateur idFormuleGenerateur = new IdFormuleGenerateurDeUUID();
    private final FormuleDao formuleDao = new FormuleDao();
    private ServiceDeProjectionDesDonnées serviceDeProjectionDesDonnées = new ServiceDeProjectionDesDonnéesVersGérant(formuleDao);

    // dépendances directes à mock dans les tests
    ConsulterLesFormules consulterLesFormules = new ConsulterLesFormules(formuleDao);
    CreerUneFormule creerUneFormule = new CreerUneFormule(idFormuleGenerateur, formuleRepository, serviceDeProjectionDesDonnées);
    ChangerLePrixDeFormule changerLePrixDeFormule = new ChangerLePrixDeFormule(formuleRepository, serviceDeProjectionDesDonnées);
    ConsulterAbonnementsParFormule consulterAbonnementsParFormule = new ConsulterAbonnementsParFormule(abonnementRepository);

    @GET
    public Collection<FormuleDto> GerantConsulteLesFormules() {
        //HERE Authent. du Gérant
        return consulterLesFormules.consulte();
    }

    @POST
    public int GerantCrééUneFormule(int montant, int indexDurée) {
        try {
            Prix prix = Prix.de(montant);
            Durée durée = Durée.values()[indexDurée];
            try {
                //HERE Authent. du Gérant
                creerUneFormule.crée(prix, durée);
                return HTTP_OK;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }

    @POST
    public int GerantChangeLePrixDuneFormule(String id, int montant) {
        try {
            IdFormule idFormule = IdFormule.de(id);
            Prix prix = Prix.de(montant);
            try {
                //HERE Authent. du Gérant
                changerLePrixDeFormule.change(idFormule, prix);
                return HTTP_OK;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }

}
