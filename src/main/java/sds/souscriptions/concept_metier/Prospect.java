package sds.souscriptions.concept_metier;

import java.util.Objects;

public class Prospect {

    public static Prospect avec(Etudiant etudiant, String email) {
        return new Prospect(etudiant, email);
    }

    String email;
    Etudiant etudiant;

    private Prospect(Etudiant etudiant, String email) {
        this.etudiant = etudiant;
        this.email = email;
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
