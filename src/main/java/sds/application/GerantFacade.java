package sds.application;

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


    private final FormuleRepository formuleRepository = new FormuleRepositoryEnPostgreSQL();
    private final IdFormuleGenerateur idFormuleGenerateur = new IdFormuleGenerateurDeUUID();

    public Collection<FormuleDTO> GerantConsulteLesFormules() {
        Collection<Formule> formules = new ConsulterLesFormules(formuleRepository).consulte();
        //TODO récupérer nombre de souscription par formule
        //TODO new AbonnementRepositoryEnPostgreSQL().trouveAbonnementsAvec(Collection<IdFormule>)
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
            CreerUneFormule creerUneFormule = new CreerUneFormule(idFormuleGenerateur, formuleRepository);
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
            ChangerLePrixDeFormule changerLePrixDeFormule = new ChangerLePrixDeFormule(formuleRepository);
            Optional<PrixFormuleChangee> prixFormuleChangee = changerLePrixDeFormule.change(IdFormule.de(id), Prix.de(montant));
            return prixFormuleChangee.isPresent() ?
                    HTTP_OK :
                    HTTP_BAD_REQUEST;
        } catch (Exception e) {
            return HTTP_INTERNAL_ERROR;
        }
    }
}
