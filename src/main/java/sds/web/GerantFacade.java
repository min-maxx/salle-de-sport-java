package sds.web;

import sds.offre.concept_metier.*;
import sds.offre.infra.FormuleRepositoryEnPostgreSQL;
import sds.offre.infra.IdFormuleGenerateurDeUUID;
import sds.offre.tache_metier.*;
import sds.souscriptions.concept_metier.AbonnementRepository;
import sds.souscriptions.infra.AbonnementRepositoryEnPostgreSQL;
import sds.souscriptions.tache_metier.ConsulterAbonnementsParFormule;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.net.HttpURLConnection.*;
import static java.util.stream.Collectors.toList;

public class GerantFacade {

    // dépendances indirectes
    private final FormuleRepository formuleRepository = new FormuleRepositoryEnPostgreSQL();
    private final AbonnementRepository abonnementRepository = new AbonnementRepositoryEnPostgreSQL();
    private final IdFormuleGenerateur idFormuleGenerateur = new IdFormuleGenerateurDeUUID();

    // dépendances directes à mock dans les tests
    ConsulterLesFormules consulterLesFormules = new ConsulterLesFormules(formuleRepository);
    CreerUneFormule creerUneFormule = new CreerUneFormule(idFormuleGenerateur, formuleRepository);
    ChangerLePrixDeFormule changerLePrixDeFormule = new ChangerLePrixDeFormule(formuleRepository);
    ConsulterAbonnementsParFormule consulterAbonnementsParFormule = new ConsulterAbonnementsParFormule(abonnementRepository);

    @GET
    public Collection<FormuleDto> GerantConsulteLesFormules() {
        //HERE Authent. du Gérant
        Collection<Formule> formules = consulterLesFormules.consulte();
        List<sds.souscriptions.concept_metier.IdFormule> idFormules = formules.stream().map(formule -> enIdFormule(formule.getId())).collect(toList());
        Map<sds.souscriptions.concept_metier.IdFormule, Long> nombreAbonnementsParFormule = consulterAbonnementsParFormule.consulte(idFormules);
        return formules.stream().map(formule -> enDto(formule, nombreAbonnementsParFormule.get(enIdFormule(formule.getId())))).collect(toList());
    }

    private sds.souscriptions.concept_metier.IdFormule enIdFormule(IdFormule id) {
        return sds.souscriptions.concept_metier.IdFormule.de(id.valeur());
    }

    private static FormuleDto enDto(Formule formule, Long nombreAbonnements) {
        FormuleDto dto = new FormuleDto();
        dto.id = formule.getId().valeur();
        dto.durée = formule.getDurée().toString();
        dto.prix = formule.getPrixDeBase().valeur();
        dto.nombreAbonnements = nombreAbonnements;
        return dto;
    }

    @POST
    public int GerantCrééUneFormule(int montant, int indexDurée) {
        try {
            Prix prix = Prix.de(montant);
            Durée durée = Durée.values()[indexDurée];
            try {
                //HERE Authent. du Gérant
                FormuleCreee formuleCreee = creerUneFormule.crée(prix, durée);
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
                Optional<PrixFormuleChangee> prixFormuleChangee = changerLePrixDeFormule.change(idFormule, prix);
                return HTTP_OK;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }

}
