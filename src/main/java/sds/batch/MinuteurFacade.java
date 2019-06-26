package sds.batch;

import sds.notification_email.infra.AbonnésExternes;
import sds.notification_email.infra.EnvoyeurDeEmailMailChimp;
import sds.notification_email.tache_metier.EnvoyerEmailRemerciementAutomatiquement;
import sds.souscriptions.infra.AbonnementRepositoryEnPostgreSQL;
import sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MinuteurFacade {

    private static final long MENSUELLEMENT = (long) (1000L * 60 * 60 * 24 * 365.25 / 12);
    Timer timer = new Timer();
    EnvoyerEmailRemerciementAutomatiquement envoyerEmailRemerciement = new EnvoyerEmailRemerciementAutomatiquement(new AbonnésExternes(), new EnvoyeurDeEmailMailChimp());
    RenouvellerAbonnementsAutomatiquement renouvellerAbonnements = new RenouvellerAbonnementsAutomatiquement(new AbonnementRepositoryEnPostgreSQL());

    public static void main(String[] args) throws ParseException {
        var minuteurFacade = new MinuteurFacade();
        minuteurFacade.MinuteurRenouvelleAbonnementsAutomatiquementChaqueMois();
        minuteurFacade.MinuteurEnvoieEmailDeRemerciementAutomatiquementChaqueMois();
    }

    void MinuteurRenouvelleAbonnementsAutomatiquementChaqueMois() throws ParseException {
        timer.schedule(TacheDeRenouvellement(), moisProchain(), MENSUELLEMENT);
    }


    void MinuteurEnvoieEmailDeRemerciementAutomatiquementChaqueMois() throws ParseException {
        timer.schedule(TacheEnvoiRemerciement(), moisProchain(), MENSUELLEMENT);
    }

    private TimerTask TacheEnvoiRemerciement() {
        return new TimerTask() {
            @Override
            public void run() {
                envoyerEmailRemerciement.envoie(YearMonth.now());
            }
        };
    }


    private TimerTask TacheDeRenouvellement() {
        return new TimerTask() {
            @Override
            public void run() {
                renouvellerAbonnements.renouvelle(LocalDate.now());
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
