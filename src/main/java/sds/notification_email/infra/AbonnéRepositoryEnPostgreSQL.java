package sds.notification_email.infra;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.AbonnéRepository;

import java.time.YearMonth;
import java.util.Collection;

public class AbonnéRepositoryEnPostgreSQL implements AbonnéRepository {

    @Override
    public void addOrReplace(Abonné abonné) {
        //INSERT ou UPDATE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    @Override
    public Collection<Abonné> trouveAbonnésAyantSoucritPendant(YearMonth moisAnnée) {
        //SELECT * FROM ...
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
