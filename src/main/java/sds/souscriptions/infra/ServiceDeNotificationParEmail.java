package sds.souscriptions.infra;

import sds.notification_email.concept_metier.AbonnementDetail;
import sds.notification_email.concept_metier.Abonné;
import sds.notification_email.concept_metier.AdresseEmail;
import sds.notification_email.concept_metier.IdAbonné;
import sds.notification_email.tache_metier.EnvoyerEmailRecapitulatif;
import sds.souscriptions.concept_metier.Prospect;
import sds.souscriptions.tache_metier.AbonnementSouscrit;
import sds.souscriptions.tache_metier.ServiceDeNotification;

public class ServiceDeNotificationParEmail implements ServiceDeNotification {
    private EnvoyerEmailRecapitulatif envoyerEmailRecapitulatif;

    public ServiceDeNotificationParEmail(EnvoyerEmailRecapitulatif envoyerEmailRecapitulatif) {
        this.envoyerEmailRecapitulatif = envoyerEmailRecapitulatif;
    }

    @Override
    public void envoieRecapitulatif(Prospect prospect, AbonnementSouscrit abonnementSouscrit) {
        envoyerEmailRecapitulatif.envoie(enAbonné(abonnementSouscrit, prospect.email), enAbonnementDetail(abonnementSouscrit));

    }


    private AbonnementDetail enAbonnementDetail(AbonnementSouscrit abonnementSouscrit) {
        return AbonnementDetail.avec(abonnementSouscrit.jourDeFin);
    }

    private Abonné enAbonné(AbonnementSouscrit abonnementSouscrit, String email) {
        return Abonné.avec(IdAbonné.de(abonnementSouscrit.id.valeur()), AdresseEmail.de(email), abonnementSouscrit.jourDeSouscription);
    }
}
