package it.algos.pubblica

class Dializzato {

    def String nome
    def String cognome
    def TipoTrasporto trasporto
    def String indirizzo
    def String località
    def String piano
    def String telefonoFisso
    def String telefonoCellulare
    def Long inizioAndata
    def Long fineAndata
    def Long inizioRitorno
    def Long fineRitorno
    def String giornate

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        cognome(nullable: false, blank: false)
        nome(nullable: false, blank: false)
        trasporto(nullable: false)
        indirizzo(nullable: false, blank: false)
        località(nullable: false, blank: false)
        piano(nullable: false, blank: false)
        giornate(nullable: false, blank: false)
        telefonoFisso(nullable: true, blank: true)
        telefonoCellulare(nullable: true, blank: true)
        inizioAndata(nullable: true)
        fineAndata(nullable: true)
        inizioRitorno(nullable: true)
        fineRitorno(nullable: true)
    } // end of static constraints

    // valore di testo restituito per una istanza della classe
    String toString() {
        cognome
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * prima di cancellare un record
     */
    def beforeDelete = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo che il record è stato letto dal database e
     * le proprietà dell'oggetto sono state aggiornate
     */
    def onLoad = {
    } // end of def onLoad


} // end of Class
