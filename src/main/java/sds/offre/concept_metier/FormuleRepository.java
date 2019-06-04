package sds.offre.concept_metier;

public interface FormuleRepository {
    Formule get(IdFormule id);

    void addOrReplace(Formule formule);
}
