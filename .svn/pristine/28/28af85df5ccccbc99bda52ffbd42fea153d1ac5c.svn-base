package it.algos.pubblica

class Milite {

    def String nome
    def String cognome
    //   def Qualifica qualifica
    boolean autistaOrdinario = false
    boolean soccorritoreOrdinario = false
    boolean terzoOrdinario = false
    boolean autista118 = false
    boolean soccorritore118 = false
    boolean terzo118 = false
    boolean dipendente = false
    boolean attivo = true
    def String telefonoFisso
    def String telefonoCellulare
    def String note = ''

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        cognome(nullable: false, blank: false)
        nome(nullable: false, blank: false)
        autista118()
        soccorritore118()
        terzo118()
        autistaOrdinario()
        soccorritoreOrdinario()
        terzoOrdinario()
        dipendente()
        telefonoFisso(nullable: true, blank: true)
        telefonoCellulare(nullable: true, blank: true)
        attivo()
        note(widget: 'textarea', nullable: true, blank: true)
    } // end of static constraints

    static mapping = {
        note type: 'text'
    }

    // valore di testo restituito per una istanza della classe
    String toString() {
        cognome + ' ' + nome
    } // end of toString

    // valore di testo restituito per una istanza della classe
    public String nomePuntato() {
        cognome + ' ' + nome.substring(0, 1) + '.'
    } //

    // valore di testo restituito per una istanza della classe
    public String userName() {
        cognome + nome
    } // end of toString

    // valore di testo restituito per una istanza della classe
    public String firstPassword() {
        cognome + nome.substring(0, 1)
    } // end of toString

    // valore di testo restituito per una istanza della classe
    public String nomeCorrente() {
        nome + ' ' + cognome
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        cognome = primaMaiuscola(cognome)
        nome = primaMaiuscola(nome)
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

    /**
     * Forza il primo carattere della stringa a maiuscolo
     *
     * Restituisce una stringa
     * Un numero ritorna un numero
     * Un nullo ritorna un nullo
     * Un oggetto non stringa ritorna uguale
     * @param entrata stringa in ingresso
     * @return uscita string in uscita
     */
    private static primaMaiuscola = {entrata ->
        // variabili e costanti locali di lavoro
        def uscita = entrata
        String primo
        String rimanente

        if (entrata && entrata in String) {
            primo = entrata.substring(0, 1)
            primo = primo.toUpperCase()
            rimanente = entrata.substring(1)
            uscita = primo + rimanente
        }// fine del blocco if

        // valore di ritorno
        return uscita
    } // fine della closure

} // end of Class
