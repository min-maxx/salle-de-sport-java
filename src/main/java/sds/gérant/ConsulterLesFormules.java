package sds.gérant;

import java.util.Collection;

public class ConsulterLesFormules {
    private final FormuleDao formuleDao;

    public ConsulterLesFormules(FormuleDao formuleDao) {
        this.formuleDao = formuleDao;
    }

    public Collection<FormuleDto> consulte() {
        return formuleDao.readToutesLesFormules();
    }
}
