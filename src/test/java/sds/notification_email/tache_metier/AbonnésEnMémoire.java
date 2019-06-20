package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.Abonnés;

import java.time.YearMonth;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AbonnésEnMémoire implements Abonnés {
    private final List<Abonné> abonnés;

    public AbonnésEnMémoire(List<Abonné> abonnés) {
        this.abonnés = abonnés;
    }

    @Override
    public Collection<Abonné> trouveAbonnésAyantSoucritLe(YearMonth moisDeSouscription) {
        return abonnés.stream()
                .filter(abonné -> moisDeSouscription.equals(YearMonth.from(abonné.dateDeSouscription())))
                .collect(toList());
    }

}
