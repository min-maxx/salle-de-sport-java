package sds.notification_email.concept_metier;

import java.util.Objects;

public class IdAbonné {
    private String valeur;

    private IdAbonné(String valeur) {
        this.valeur = valeur;
    }

    public static IdAbonné de(String valeur) {
        return new IdAbonné(valeur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdAbonné that = (IdAbonné) o;
        return Objects.equals(valeur, that.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return "IdAbonné{" +
                "valeur='" + valeur + '\'' +
                '}';
    }
}
