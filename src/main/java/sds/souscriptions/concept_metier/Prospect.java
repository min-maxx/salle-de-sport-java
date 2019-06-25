package sds.souscriptions.concept_metier;

import java.util.Objects;

public class Prospect {

    public static Prospect avec(Etudiant etudiant) {
        return new Prospect(etudiant);
    }

    Etudiant etudiant;

    private Prospect(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prospect prospect = (Prospect) o;
        return etudiant == prospect.etudiant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(etudiant);
    }

    @Override
    public String toString() {
        return "Prospect{" +
                "etudiant=" + etudiant +
                '}';
    }
}
