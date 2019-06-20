package sds.batch;

import org.junit.jupiter.api.Test;
import sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MinuteurFacadeTest {

    @Test
    void doit_lancer_minuteur() throws ParseException {
        MinuteurFacade minuteurFacade = new MinuteurFacade();
        minuteurFacade.timer = new TestTimer();
        minuteurFacade.renouvellerAbonnements = mock(RenouvellerAbonnementsAutomatiquement.class);
        when(minuteurFacade.renouvellerAbonnements.renouvelle(any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        minuteurFacade.MinuteurRenouvelleAbonnementsAutomatiquementChaqueMois();

        verify(minuteurFacade.renouvellerAbonnements).renouvelle(any(LocalDate.class));
        assertThat(((TestTimer) minuteurFacade.timer).firstTime).hasDayOfMonth(1).isInTheFuture();
        assertThat(((TestTimer) minuteurFacade.timer).period).isEqualTo(2629800000l);

    }

    private static class TestTimer extends Timer {
        Date firstTime;
        long period;

        @Override
        public void schedule(TimerTask task, Date firstTime, long period) {
            this.firstTime = firstTime;
            this.period = period;
            task.run();
        }
    }
}