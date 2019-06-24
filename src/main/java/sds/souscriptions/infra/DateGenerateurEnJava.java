package sds.souscriptions.infra;

import sds.souscriptions.concept_metier.DateGenerateur;

import java.time.LocalDate;

public class DateGenerateurEnJava implements DateGenerateur {
    @Override
    public LocalDate aujourdhui() {
        return LocalDate.now();
    }
}
