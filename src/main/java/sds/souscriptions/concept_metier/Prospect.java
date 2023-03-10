package sds.souscriptions.concept_metier;

import java.util.Objects;

public class Prospect {

    public static Prospect avec(String email, Etudiant etudiant) {
        return new Prospect(email, etudiant);
    }


    public final String email;
    public final Etudiant etudiant;

    private Prospect(String email, Etudiant etudiant) {
        this.email = email;
        this.etudiant = etudiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prospect prospect = (Prospect) o;
        return Objects.equals(email, prospect.email) &&
                etudiant == prospect.etudiant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, etudiant);
    }

    @Override
    public String toString() {
        return "Prospect{" +
                "email='" + email + '\'' +
                ", etudiant=" + etudiant +
                '}';
    }
}
