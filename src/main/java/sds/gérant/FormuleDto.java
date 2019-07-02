package sds.gérant;

import java.util.Objects;

public class FormuleDto {

    public String id;
    public String durée;
    public long prix;
    public Long nombreAbonnements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormuleDto that = (FormuleDto) o;
        return prix == that.prix &&
                Objects.equals(id, that.id) &&
                Objects.equals(durée, that.durée) &&
                Objects.equals(nombreAbonnements, that.nombreAbonnements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, durée, prix, nombreAbonnements);
    }

    @Override
    public String toString() {
        return "FormuleDto{" +
                "id='" + id + '\'' +
                ", durée='" + durée + '\'' +
                ", prix=" + prix +
                ", nombreAbonnements=" + nombreAbonnements +
                '}';
    }
}
