package sds.batch;

import sds.souscriptions.infra.AbonnementRepositoryEnPostgreSQL;
import sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement;

import java.time.LocalDate;
import java.util.TimerTask;

class TacheDeRenouvellement extends TimerTask {
    @Override
    public void run() {
        new RenouvellerAbonnementsAutomatiquement(new AbonnementRepositoryEnPostgreSQL()).renouvelle(LocalDate.now());

    }
}
