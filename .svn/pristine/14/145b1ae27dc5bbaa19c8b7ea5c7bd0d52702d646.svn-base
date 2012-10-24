package it.algos.pubblica

class TipoTurno {

    def String sigla = ''
    def String descrizione = ''
    def int ordine = 0
    def Date inizio
    def Date fine
    def int durata = 0
    def boolean visibile = true
    def boolean orario = false
    def boolean autista = true
    def boolean secondo = true
    def boolean terzo = true
    def boolean multiplo = false

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        sigla(nullable: false, blank: false)
        descrizione(nullable: true, blank: true)
        ordine()
        inizio(nullable: true)
        fine(nullable: true)
        durata()
        visibile()
        orario()
        autista()
        secondo()
        terzo()
        multiplo()
    } // end of static constraints

    // valore di testo restituito per una istanza della classe
    String toString() {
        sigla
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
