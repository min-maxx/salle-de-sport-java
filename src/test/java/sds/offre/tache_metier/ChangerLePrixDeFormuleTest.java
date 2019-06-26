package sds.offre.tache_metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.offre.FormuleRepositoryEnMemoire;
import sds.offre.concept_metier.*;

import static org.assertj.core.api.Assertions.assertThat;
import static sds.offre.tache_metier.ChangerLePrixDeFormuleTest.Constant.*;

class ChangerLePrixDeFormuleTest {

    private ChangerLePrixDeFormule changerLePrixDeFormule;
    private FormuleRepository formuleRepository;

    @BeforeEach
    void setUp() {
        formuleRepository = new FormuleRepositoryEnMemoire();
        changerLePrixDeFormule = new ChangerLePrixDeFormule(formuleRepository);
    }

    @Test
    void doit_changer_prix_formule_existante() {
        Formule formule = new Formule(ID, ANCIEN_PRIX, Durée.AU_MOIS);
        formuleRepository.addOrReplace(formule);

        assertThat(
                changerLePrixDeFormule.change(ID, NOUVEAU_PRIX)
        ).hasValue(
                PrixFormuleChangee.de(ID, NOUVEAU_PRIX)
        );

        assertThat(
                formuleRepository.get(ID).prixDeBase()
        ).isEqualTo(
                NOUVEAU_PRIX);
    }


    static class Constant {
        static final IdFormule ID = IdFormule.de("22");
        static final Prix NOUVEAU_PRIX = Prix.de(22);
        static final Prix ANCIEN_PRIX = Prix.de(32);

    }
}