package sds.notification_email.concept_metier;

import java.time.YearMonth;
import java.util.Collection;

public interface Abonnés {
    Collection<Abonné> trouveAbonnésAyantSoucritLe(YearMonth moisDeSouscription);
}
