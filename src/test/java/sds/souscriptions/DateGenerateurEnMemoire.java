package sds.souscriptions;

import sds.souscriptions.concept_metier.DateGenerateur;

import java.time.LocalDate;

public class DateGenerateurEnMemoire implements DateGenerateur {
    private final LocalDate date;

    public DateGenerateurEnMemoire(LocalDate date) {
        this.date = date;
    }

    @Override
    public LocalDate aujourdhui() {
        return date;
    }
}
