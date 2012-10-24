package it.algos.pubblica

class Macchina {

    def TipoMacchina tipo
    def String sigla
    def String targa
    def Date dataAcquisto
    def Integer contakilometri = 0

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        tipo(nullable: false)
        sigla(nullable: false, blank: false)
        targa(nullable: true, blank: true)
        dataAcquisto(nullable: true)
        contakilometri(nullable: true)
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
