package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.AbonnéRepository;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Pour voir un exemple de tests, se rendre à la classe
 *
 * @see sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement
 */
public class EnvoyerEmailRemerciementAutomatiquement {
    private final AbonnéRepository abonnéRepository;
    private final EnvoyeurDeEmail envoyeurDeEmail;

    public EnvoyerEmailRemerciementAutomatiquement(AbonnéRepository abonnéRepository, EnvoyeurDeEmail envoyeurDeEmail) {
        this.abonnéRepository = abonnéRepository;
        this.envoyeurDeEmail = envoyeurDeEmail;
    }

    public Collection<EmailRemerciementEnvoyé> envoie(YearMonth moisActuel) {
        Collection<Abonné> abonnés = abonnéRepository.trouveAbonnésAyantSoucritLe(moisActuel.minusYears(3));
        Collection<EmailRemerciementEnvoyé> list = new ArrayList<>();
        for (Abonné abonné : abonnés) {
            LocalDate dateEnvoi = envoyeurDeEmail.envoieRemerciement(abonné);
            list.add(EmailRemerciementEnvoyé.avec(dateEnvoi, abonné.email()));
        }
        return list;
    }

}
