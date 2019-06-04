package sds.application;

import sds.offre.concept_metier.*;
import sds.offre.tache_metier.ChangerLePrixDeFormule;
import sds.offre.tache_metier.ConsulterLesFormules;
import sds.offre.tache_metier.CreerUneFormule;
import sds.souscriptions.infra.FormuleRepositoryEnPostgreSQL;
import sds.souscriptions.infra.IdFormuleGenerateurDeUUID;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.*;

public class GerantFacade {

    public Collection<FormuleDTO> GerantConsulteLesFormules() {
        //TODO récupérer nombre de souscription par formule
        Collection<Formule> formules = new ConsulterLesFormules(new FormuleRepositoryEnPostgreSQL()).consulte();
        return formules.stream().map(GerantFacade::toDto).collect(Collectors.toList());
    }

    private static FormuleDTO toDto(Formule formule) {
        //FIXME obliger de créer de getter :'( -> solution CQRS
        FormuleDTO dto = new FormuleDTO();
        dto.id = formule.Id().valeur();
        dto.durée = formule.durée().toString();
        dto.prix = formule.prixDeBase().valeur();
        return dto;
    }

    public int GerantCrééUneFormule(int montant, int durée) {
        try {
            //TODO Authent. du Gérant
            CreerUneFormule creerUneFormule = new CreerUneFormule(new IdFormuleGenerateurDeUUID(), new FormuleRepositoryEnPostgreSQL());
            Optional<FormuleCreee> formuleCreee = creerUneFormule.crée(Prix.de(montant), durée == 0 ? Durée.AU_MOIS : Durée.A_L_ANNEE);
            return formuleCreee.isPresent() ?
                    HTTP_OK :
                    HTTP_BAD_REQUEST;
        } catch (Exception e) {
            return HTTP_INTERNAL_ERROR;
        }
    }

    public int GerantCrééUneFormule(String id, int montant) {
        try {
            //TODO Authent. du Gérant
            ChangerLePrixDeFormule changerLePrixDeFormule = new ChangerLePrixDeFormule(new FormuleRepositoryEnPostgreSQL());
            Optional<PrixFormuleChangee> prixFormuleChangee = changerLePrixDeFormule.change(IdFormule.de(id), Prix.de(montant));
            return prixFormuleChangee.isPresent() ?
                    HTTP_OK :
                    HTTP_BAD_REQUEST;
        } catch (Exception e) {
            return HTTP_INTERNAL_ERROR;
        }
    }
}
