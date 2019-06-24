package sds.web;

import sds.offre.concept_metier.*;
import sds.offre.infra.FormuleRepositoryEnPostgreSQL;
import sds.offre.infra.IdFormuleGenerateurDeUUID;
import sds.offre.tache_metier.ChangerLePrixDeFormule;
import sds.offre.tache_metier.ConsulterLesFormules;
import sds.offre.tache_metier.CreerUneFormule;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.*;

public class GerantFacade {


    // dépendances indirectes
    private final FormuleRepository formuleRepository = new FormuleRepositoryEnPostgreSQL();
    private final IdFormuleGenerateur idFormuleGenerateur = new IdFormuleGenerateurDeUUID();
    private ConsulterLesFormules consulterLesFormules = new ConsulterLesFormules(formuleRepository);

    // dépendances directes à mock dans les tests
    CreerUneFormule creerUneFormule = new CreerUneFormule(idFormuleGenerateur, formuleRepository);
    ChangerLePrixDeFormule changerLePrixDeFormule = new ChangerLePrixDeFormule(formuleRepository);

    public Collection<FormuleDTO> GerantConsulteLesFormules() {
        //HERE Authent. du Gérant
        Collection<Formule> formules = consulterLesFormules.consulte();
        //TODO récupérer nombre de souscription par formule
        //TODO new AbonnementRepositoryEnPostgreSQL().trouveAbonnementsAvec(Collection<IdFormule>)
        return formules.stream().map(GerantFacade::toDto).collect(Collectors.toList());
    }

    private static FormuleDTO toDto(Formule formule) {
        //NOTE: obliger de créer de getter :'(
        FormuleDTO dto = new FormuleDTO();
        dto.id = formule.Id().valeur();
        dto.durée = formule.durée().toString();
        dto.prix = formule.prixDeBase().valeur();
        return dto;
    }

    public int GerantCrééUneFormule(int montant, int indexDurée) {
        try {
            Prix prix = Prix.de(montant);
            Durée durée = Durée.values()[indexDurée];
            try {
                //HERE Authent. du Gérant
                Optional<FormuleCreee> formuleCreee = creerUneFormule.crée(prix, durée);
                return formuleCreee.isPresent() ?
                        HTTP_OK :
                        HTTP_INTERNAL_ERROR;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }

    public int GerantChangeLePrixDuneFormule(String id, int montant) {
        try {
            IdFormule idFormule = IdFormule.de(id);
            Prix prix = Prix.de(montant);
            try {
                //HERE Authent. du Gérant
                Optional<PrixFormuleChangee> prixFormuleChangee = changerLePrixDeFormule.change(idFormule, prix);
                return prixFormuleChangee.isPresent() ?
                        HTTP_OK :
                        HTTP_INTERNAL_ERROR;
            } catch (Exception e) {
                return HTTP_INTERNAL_ERROR;
            }
        } catch (Exception e) {
            return HTTP_BAD_REQUEST;
        }
    }
}
