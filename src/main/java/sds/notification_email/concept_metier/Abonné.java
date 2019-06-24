package sds.notification_email.concept_metier;

import java.time.LocalDate;

public class Abonné {
    private final IdAbonné idAbonné;
    private final LocalDate dateInscription;
    private final AdresseEmail adresseEmail;

    Abonné(IdAbonné idAbonné, LocalDate dateInscription, AdresseEmail adresseEmail) {
        this.idAbonné = idAbonné;
        this.dateInscription = dateInscription;
        this.adresseEmail = adresseEmail;
    }

    public IdAbonné idAbonnement() {
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
}
