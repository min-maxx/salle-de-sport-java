package sds.notification_email.concept_metier;

import java.util.Objects;

public class AdresseEmail {
    private final String adresse;

    public AdresseEmail(String adresse) {
        this.adresse = adresse;
    }

    public static AdresseEmail de(String adresse) {
        return new AdresseEmail(adresse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresseEmail adresseEmail = (AdresseEmail) o;
        return Objects.equals(adresse, adresseEmail.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse);
    }

    @Override
    public String toString() {
        return "AdresseEmail{" +
                "adresse='" + adresse + '\'' +
                '}';
    }
}
