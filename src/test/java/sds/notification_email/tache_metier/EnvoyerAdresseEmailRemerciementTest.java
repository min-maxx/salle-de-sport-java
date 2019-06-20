package sds.notification_email.tache_metier;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import sds.notification_email.concept_metier.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.notification_email.tache_metier.EnvoyerAdresseEmailRemerciementTest.Constant.*;

class EnvoyerAdresseEmailRemerciementTest {

    @Test
    void doit_envoyer_email_aux_abonnés_depuis_3_ans() {
        Abonnés abonnés = new AbonnésEnMémoire(Lists.list(
                Abonné.avec(IdAbonné.de("1"), AdresseEmail.de("s@1.com"), JUILLET_2018),
                Abonné.avec(IdAbonné.de("2"), AdresseEmail.de("s@2.com"), AOUT_2018),
                Abonné.avec(IdAbonné.de("3"), AdresseEmail.de("s@3.com"), SEPTEMBRE_2018),
                Abonné.avec(IdAbonné.de("4"), AdresseEmail.de("s@4.com"), AOUT_2019)
        ));

        EnvoyeurDeEmail envoyeurDeEmail = new EnvoyeurDeEmailEnMemoire(DATE_ENVOI);

        assertThat(
                new EnvoyerEmailRemerciementAutomatiquement(abonnés, envoyeurDeEmail).envoie(AOUT_2021)
        ).contains(
                EmailRemerciementEnvoyé.avec(IdAbonné.de("2"), DATE_ENVOI, AdresseEmail.de("s@2.com"))
        ).hasSize(1);

    }

    static class Constant {

        static final LocalDate JUILLET_2018 = LocalDate.of(2018, Month.JULY, 8);
        static final LocalDate AOUT_2018 = LocalDate.of(2018, Month.AUGUST, 8);
        static final LocalDate SEPTEMBRE_2018 = LocalDate.of(2018, Month.SEPTEMBER, 8);
        static final LocalDate AOUT_2019 = LocalDate.of(2019, Month.AUGUST, 8);
        static final YearMonth AOUT_2021 = YearMonth.of(2021, Month.AUGUST);
        static final LocalDate DATE_ENVOI = LocalDate.now();

    }
}