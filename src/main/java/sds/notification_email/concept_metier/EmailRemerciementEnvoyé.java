package sds.notification_email.concept_metier;


import java.time.LocalDate;
import java.util.Objects;

public class EmailRemerciementEnvoyé {
    private final IdAbonné idAbonné;
    private final LocalDate envoyéLe;
    private final AdresseEmail adresseEmail;

    private EmailRemerciementEnvoyé(IdAbonné idAbonné, LocalDate envoyéLe, AdresseEmail adresseEmail) {
        this.idAbonné = idAbonné;
        this.envoyéLe = envoyéLe;
        this.adresseEmail = adresseEmail;
    }

    public static EmailRemerciementEnvoyé avec(IdAbonné idAbonné, LocalDate envoyéLe, AdresseEmail adresseEmail) {
        return new EmailRemerciementEnvoyé(idAbonné, envoyéLe, adresseEmail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailRemerciementEnvoyé that = (EmailRemerciementEnvoyé) o;
        return Objects.equals(idAbonné, that.idAbonné) &&
                Objects.equals(envoyéLe, that.envoyéLe) &&
                Objects.equals(adresseEmail, that.adresseEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAbonné, envoyéLe, adresseEmail);
    }

    @Override
    public String toString() {
        return "EmailRemerciementEnvoyé{" +
                "idAbonné=" + idAbonné +
                ", envoyéLe=" + envoyéLe +
                ", adresseEmail=" + adresseEmail +
                '}';
    }
}
