package sds.offre.tache_metier;

public interface ServiceDeProjectionDesDonnées {
    void faitProjection(FormuleCreee formule);

    void faitProjection(PrixFormuleChangee prixFormuleChangee);
}
