package sds.notification_email.concept_metier;

import java.time.YearMonth;
import java.util.Collection;

public interface AbonnéRepository {

    void addOrReplace(Abonné abonné);

    Collection<Abonné> trouveAbonnésAyantSoucritLe(YearMonth moisDeSouscription);
}
