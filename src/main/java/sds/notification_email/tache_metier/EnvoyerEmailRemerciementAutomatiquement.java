package sds.notification_email.tache_metier;

import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.Abonnés;
import sds.notification_email.concept_metier.EmailRemerciementEnvoyé;
import sds.notification_email.concept_metier.EnvoyeurDeEmail;

import java.time.YearMonth;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Pour voir un exemple de tests, se rendre à la classe
 *
 * @see sds.souscriptions.tache_metier.RenouvellerAbonnementsAutomatiquement
 */
public class EnvoyerEmailRemerciementAutomatiquement {
    private final Abonnés abonnés;
    private final EnvoyeurDeEmail envoyeurDeEmail;

    public EnvoyerEmailRemerciementAutomatiquement(Abonnés abonnés, EnvoyeurDeEmail envoyeurDeEmail) {
        this.abonnés = abonnés;
        this.envoyeurDeEmail = envoyeurDeEmail;
    }

    public Collection<EmailRemerciementEnvoyé> envoie(YearMonth moisActuel) {
        Collection<Abonné> abonnés = this.abonnés.trouveAbonnésAyantSoucritLe(moisActuel.minusYears(3));
        return abonnés.stream()
                .map(envoyeurDeEmail::envoieRemerciement)
                .collect(toList());
    }

}
