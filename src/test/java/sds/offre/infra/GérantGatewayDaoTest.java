package sds.offre.infra;

import org.junit.jupiter.api.Test;
import sds.gérant.FormuleDao;
import sds.gérant.FormuleDto;
import sds.offre.concept_metier.Durée;
import sds.offre.concept_metier.IdFormule;
import sds.offre.concept_metier.Prix;
import sds.offre.tache_metier.FormuleCreee;
import sds.offre.tache_metier.PrixFormuleChangee;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GérantGatewayDaoTest {

    private final FormuleDao formuleDao = mock(FormuleDao.class);

    @Test
    void doit_créer_projection_quand_FormuleCréée() {
        FormuleCreee formuleCréée = FormuleCréée("1", 10, Durée.AU_MOIS);

        new GérantGatewayDao(formuleDao).faitProjection(formuleCréée);

        verify(formuleDao).create(FormuleDto("1", 10, "AU_MOIS"));
    }

    @Test
    void doit_màj_projection_quand_PrixFormuleChangée() {
        PrixFormuleChangee prixFormuleChangee = PrixFormuleChangée("abc", 20);

        new GérantGatewayDao(formuleDao).faitProjection(prixFormuleChangee);

        verify(formuleDao).update("abc", 20);
    }

    private FormuleCreee FormuleCréée(String id, int prix, Durée durée) {
        return FormuleCreee.de(IdFormule.de(id), Prix.de(prix), durée);
    }

    private PrixFormuleChangee PrixFormuleChangée(String id, int prix) {
        return PrixFormuleChangee.de(IdFormule.de(id), Prix.de(prix));
    }

    private FormuleDto FormuleDto(String id, int prix, String durée) {
        FormuleDto formuleDto = new FormuleDto();
        formuleDto.id = id;
        formuleDto.durée = durée;
        formuleDto.prix = prix;
        return formuleDto;
    }
}