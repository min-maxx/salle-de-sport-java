package sds.souscriptions.infra;

import sds.gérant.FormuleDao;
import sds.souscriptions.tache_metier.AbonnementSouscrit;
import sds.souscriptions.tache_metier.ServiceDeProjectionDesDonnées;

public class ServiceDeProjectionDesDonnéesVersGérant implements ServiceDeProjectionDesDonnées {

    private final FormuleDao formuleDao;

    public ServiceDeProjectionDesDonnéesVersGérant(FormuleDao formuleDao) {
        this.formuleDao = formuleDao;
    }

    @Override
    public void faitProjection(AbonnementSouscrit abonnementSouscrit) {
        formuleDao.updateNbAbonnement(abonnementSouscrit.idFormule.valeur());
    }
}
