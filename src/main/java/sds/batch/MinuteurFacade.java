package sds.batch;

import sds.souscriptions.infra.AbonnementRepositoryEnPostgreSQL;
import sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MinuteurFacade {

    private static final long MENSUELLEMENT = (long) (1000L * 60 * 60 * 24 * 365.25 / 12);
    Timer timer = new Timer();
    RenouvellerAbonnementsAutomatiquement renouvellerAbonnementsAutomatiquement = new RenouvellerAbonnementsAutomatiquement(new AbonnementRepositoryEnPostgreSQL());

    public static void main(String[] args) throws ParseException {
        new MinuteurFacade().MinuteurRenouvelleAbonnementsAutomatiquementChaqueMois();
    }

    public void MinuteurRenouvelleAbonnementsAutomatiquementChaqueMois() throws ParseException {
        timer.schedule(TacheDeRenouvellement(), moisProchain(), MENSUELLEMENT);
    }

    private TimerTask TacheDeRenouvellement() {
        return new TimerTask() {
            @Override
            public void run() {
                renouvellerAbonnementsAutomatiquement.renouvelle(LocalDate.now());
            }
        };
    }

    private static Date moisProchain() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd")
                .parse(LocalDate.now()
                        .with(TemporalAdjusters.firstDayOfNextMonth())
                        .atStartOfDay().toString());
    }

}
