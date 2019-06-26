package sds.notification_email.concept_metier;

import java.time.LocalDate;
import java.util.Objects;

public class Abonné {
    private final IdAbonné idAbonné;
    private final LocalDate dateInscription;
    private final AdresseEmail adresseEmail;

    private Abonné(IdAbonné idAbonné, LocalDate dateInscription, AdresseEmail adresseEmail) {
        this.idAbonné = idAbonné;
        this.dateInscription = dateInscription;
        this.adresseEmail = adresseEmail;
    }

    public IdAbonné id() {
        return idAbonné;
    }

    public LocalDate dateInscription() {
        return dateInscription;
    }

    public AdresseEmail email() {
        return adresseEmail;
    }

    public static Abonné avec(IdAbonné idAbonné, AdresseEmail adresseEmail, LocalDate dateInscription) {
        return new Abonné(idAbonné, dateInscription, adresseEmail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abonné abonné = (Abonné) o;
        return Objects.equals(idAbonné, abonné.idAbonné) &&
                Objects.equals(dateInscription, abonné.dateInscription) &&
                Objects.equals(adresseEmail, abonné.adresseEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAbonné, dateInscription, adresseEmail);
    }

    @Override
    public String toString() {
        return "Abonné{" +
                "idAbonné=" + idAbonné +
                ", dateInscription=" + dateInscription +
                ", adresseEmail=" + adresseEmail +
                '}';
    }
}
