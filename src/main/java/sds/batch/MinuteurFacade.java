package sds.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Timer;

public class MinuteurFacade {

    private static final long MENSUELLEMENT = 1000L * 60 * 60 * 24 * 30;

    public static void main(String[] args) throws ParseException {
        new Timer().schedule(new TacheDeRenouvellement(), moisProchain(), MENSUELLEMENT);
    }

    private static Date moisProchain() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd")
                .parse(LocalDate.now()
                        .with(TemporalAdjusters.firstDayOfNextMonth())
                        .atStartOfDay().toString());
    }

}
