package sds.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sds.offre.concept_metier.Durée;
import sds.offre.concept_metier.Formule;
import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.Prix;
import sds.offre.tache_metier.ConsulterLesFormules;
import sds.souscriptions.tache_metier.ConsulterAbonnementsParFormule;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sds.web.GerantFacadeTest_ConsultationFormules.Constant.*;

class GerantFacadeTest_ConsultationFormules {

    private HashMap<sds.souscriptions.concept_metier.IdFormule, Long> map;

    private GerantFacade gerantFacade;

    @BeforeEach
    void setUp() {
        gerantFacade = new GerantFacade();
        gerantFacade.consulterLesFormules = mock(ConsulterLesFormules.class);
        gerantFacade.consulterAbonnementsParFormule = mock(ConsulterAbonnementsParFormule.class);
        map = new HashMap<>();
    }

    @Test
    void doit_etre_ok_quand_formules() {
        when(gerantFacade.consulterLesFormules.consulte())
                .thenReturn(Arrays.asList(UNE_FORMULE, AUTRE_FORMULE));

        when(gerantFacade.consulterAbonnementsParFormule.consulte(anyList()))
                .thenReturn(entry(UN_ID_FORMULE, 2).entry(AUTRE_ID_FORMULE, 0).toMap());

        Collection<FormuleDto> result = gerantFacade.GerantConsulteLesFormules();

        assertThat(result).hasSize(2)
                .contains(dto(UN_ID_FORMULE, 2))
                .contains(dto(AUTRE_ID_FORMULE, 0));
    }


    private Map<sds.souscriptions.concept_metier.IdFormule, Long> toMap() {
        return map;
    }


    private GerantFacadeTest_ConsultationFormules entry(String id, int nombreAbo) {
        map.put(sds.souscriptions.concept_metier.IdFormule.de(id), (long) nombreAbo);
        return this;
    }


    static class Constant {
        static final String UN_ID_FORMULE = "abc";
        static final String AUTRE_ID_FORMULE = "def";
        static final Formule UNE_FORMULE = new Formule(IdFormule.de(UN_ID_FORMULE), Prix.de(0), Durée.AU_MOIS);
        static final Formule AUTRE_FORMULE = new Formule(IdFormule.de(AUTRE_ID_FORMULE), Prix.de(0), Durée.AU_MOIS);

        static FormuleDto dto(String id, int nombreAbo) {
            FormuleDto dto = new FormuleDto();
            dto.id = id;
            dto.durée = Durée.AU_MOIS.name();
            dto.prix = 0;
            dto.nombreAbonnements = (long) nombreAbo;
            return dto;
        }
    }

}