package sds.offre.infra;

import sds.gérant.FormuleDao;
import sds.gérant.FormuleDto;
import sds.offre.tache_metier.FormuleCreee;
import sds.offre.tache_metier.GérantGateway;

public class GérantGatewayDao implements GérantGateway {

    private final FormuleDao formuleDao;

    public GérantGatewayDao(FormuleDao formuleDao) {
        this.formuleDao = formuleDao;
    }

    @Override
    public void faitProjection(FormuleCreee formuleCreee) {
        FormuleDto formule = new FormuleDto();
        formule.id = formuleCreee.id.valeur();
        formule.durée = formuleCreee.durée.toString();
        formule.prix = formuleCreee.prix.valeur();
        formuleDao.create(formule);

    }
}
