package it.algos.pubblica

class Servizio {

    def TipoServizio tipo
    def Macchina macchina
    def Milite autista
    def Milite secondo = null
    def Milite terzo = null
    def Calendar dataInizio
    def Calendar dataFine
    def Integer durata
    def Integer kmIniziali = 0
    def Integer kmFinali = 0
    def Integer kmPercorsi = 0
    def String paziente
    def CodiceUscita uscita
    def CodiceRientro rientro
    def String cartellino

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        tipo(nullable: false)
        macchina(nullable: false)
        autista(nullable: false)
        secondo(nullable: true)
        terzo(nullable: true)
        paziente(nullable: true, blank: true)
        uscita(nullable: true)
        rientro(nullable: true)
        cartellino(nullable: true, blank: true)
        durata(nullable: true)
        kmPercorsi(nullable: true)
        dataInizio(nullable: false)
        dataFine(nullable: false)
        kmIniziali(nullable: false)
        kmFinali(nullable: false)
    } // end of static constraints

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        creaRegistra()
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
        creaRegistra()
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

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     * e
     * prima di registrare un record esistente
     */
    def creaRegistra() {
        if (dataInizio && dataFine) {
            //   durata = dataFine - dataInizio
        } else {
            durata = 0
        }// fine del blocco if-else

        if (kmIniziali && kmFinali) {
            kmPercorsi = kmFinali - kmIniziali
        } else {
            kmPercorsi = 0
        }// fine del blocco if-else
    } // fine della closure

} // end of Class
