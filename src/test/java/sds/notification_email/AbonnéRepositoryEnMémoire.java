package sds.notification_email;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.AbonnéRepository;
import sds.notification_email.concept_metier.IdAbonné;

import java.time.YearMonth;
import java.util.Collection;
import java.util.HashMap;

import static java.util.stream.Collectors.toList;

public class AbonnéRepositoryEnMémoire implements AbonnéRepository {
    private HashMap<IdAbonné, Abonné> abonnés = new HashMap<>();

    @Override
    public void addOrReplace(Abonné abonné) {
        abonnés.put(abonné.id(), abonné);
    }

    @Override
    public Collection<Abonné> trouveAbonnésAyantSoucritPendant(YearMonth moisAnnée) {
        return abonnés.values().stream()
                .filter(abonné -> moisAnnée.equals(YearMonth.from(abonné.dateInscription())))
                .collect(toList());
    }

}
