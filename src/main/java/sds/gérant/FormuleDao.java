package sds.gérant;


import java.util.Collection;

public class FormuleDao {
    public Collection<FormuleDto> trouveToutesLesFormules() {
        //SELECT * FROM GERANT_FORMULE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    public void create(FormuleDto formule) {
        //INSERT INTO GERANT_FORMULE
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    public void update(String id, long prix) {
        //UPDATE GERANT_FORMULE WHERE 'id'
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

    public void updateNbAbonnement(String id) {
        //UPDATE (incrémenter nb abo) GERANT_FORMULE WHERE 'id'
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
}
