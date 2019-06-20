package sds.notification_email.concept_metier;

import java.time.LocalDate;

public class Abonné {
    private final IdAbonné idAbonné;
    private final LocalDate dateDeSouscription;
    private final AdresseEmail adresseEmail;

    Abonné(IdAbonné idAbonné, LocalDate dateDeSouscription, AdresseEmail adresseEmail) {
        this.idAbonné = idAbonné;
        this.dateDeSouscription = dateDeSouscription;
        this.adresseEmail = adresseEmail;
    }

    public IdAbonné idAbonnement() {
        return idAbonné;
    }

    public LocalDate dateDeSouscription() {
        return dateDeSouscription;
    }

    public AdresseEmail email() {
        return adresseEmail;
    }

    public static Abonné avec(IdAbonné idAbonné, AdresseEmail adresseEmail, LocalDate dateDeSouscription) {
        return new Abonné(idAbonné, dateDeSouscription, adresseEmail);
    }
}
