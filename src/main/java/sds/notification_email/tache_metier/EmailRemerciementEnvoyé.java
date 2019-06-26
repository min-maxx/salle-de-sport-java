package sds.notification_email.tache_metier;


import sds.notification_email.concept_metier.AdresseEmail;

import java.time.LocalDate;
import java.util.Objects;

public class EmailRemerciementEnvoyé {
    private final LocalDate envoyéLe;
    private final AdresseEmail adresseEmail;

    private EmailRemerciementEnvoyé(LocalDate envoyéLe, AdresseEmail adresseEmail) {
        this.envoyéLe = envoyéLe;
        this.adresseEmail = adresseEmail;
    }

    public static EmailRemerciementEnvoyé avec(LocalDate envoyéLe, AdresseEmail adresseEmail) {
        return new EmailRemerciementEnvoyé(envoyéLe, adresseEmail);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailRemerciementEnvoyé that = (EmailRemerciementEnvoyé) o;
        return Objects.equals(envoyéLe, that.envoyéLe) &&
                Objects.equals(adresseEmail, that.adresseEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(envoyéLe, adresseEmail);
    }

    @Override
    public String toString() {
        return "EmailRemerciementEnvoyé{" +
                "envoyéLe=" + envoyéLe +
                ", adresseEmail=" + adresseEmail +
                '}';
    }
}
