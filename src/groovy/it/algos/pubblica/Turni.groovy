package it.algos.pubblica

/**
 * Created by IntelliJ IDEA.
 * User: Gac
 * Date: 12/12/11
 * Time: 07.31
 */
public enum Turni {

    ordMat('ord-mat', 'ordinario mattino', 7, 14),
    s118Mat('118-mat', '118 mattino', 8, 14),
    ordPom('ord-pom', 'ordinario pomeriggio', 14, 19),
    s118Pom('118-pom', '118 pomeriggio', 14, 20),
    s118Sera('118-ser', '118 sera/notte', 20, 24),
    avis('avis', 'servizio AVIS', 10, 12, false, true, false, false) {

        protected void setParameters() {
            TurnoService.esisteAvis = true
        };// end of internal overwritten method
    }, // end of single enumeration,
    centralino('centralino', 'centralino', 0, 0, false, true, false, false),
    extra('extra', 'extra/manifestazione'),
    extra2('extra', 'extra/manifestazione'),
    extra3('extra', 'extra/manifestazione')


    public String sigla = ''
    private String descrizione = ''
    def Date inizio
    def Date fine
    def int durata
    private boolean orario = false
    private boolean autista = true
    private boolean secondo = true
    private boolean terzo = true
    private TipoMacchina mezzo = TipoMacchina.ambulanza

    /**
     * Costruttore con parametri.
     *
     * @param colore
     * @param tag html per pagina log
     */
    Turni(String sigla, String descrizione) {
        this(sigla, descrizione, 0, 0, true, true, true, true)
    }// fine del metodo costruttore

    /**
     * Costruttore con parametri.
     *
     * @param colore
     * @param tag html per pagina log
     */
    Turni(String sigla, String descrizione, int inizio, int fine) {
        this(sigla, descrizione, inizio, fine, false, true, true, true)
    }// fine del metodo costruttore

    /**
     * Costruttore completo con parametri.
     *
     * @param colore
     * @param tag html per pagina log
     */
    Turni(String sigla, String descrizione, int inizio, int fine, boolean orario, boolean autista, boolean secondo, boolean terzo) {
        /* variabili e costanti locali di lavoro */
        Date giornoInizio
        Date giornoFine
        Calendar cal
        int durata

        /* regola le variabili di istanza coi parametri */
        this.sigla = sigla
        this.descrizione = descrizione
        this.orario = orario
        this.autista = autista
        this.secondo = secondo
        this.terzo = terzo
        this.setParameters();

        /* calcola il valore */
        durata = fine - inizio
        if (durata < 1) {
            durata = 0
        }// fine del blocco if
        this.durata = durata


        try { // prova ad eseguire il codice
            cal = Calendar.getInstance()
            cal.setTime(new Date())

            cal.set(Calendar.HOUR_OF_DAY, inizio)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)

            this.inizio = new java.util.Date(cal.getTime().getTime());

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        try { // prova ad eseguire il codice
            cal = Calendar.getInstance()
            cal.setTime(new Date())

            cal.set(Calendar.HOUR_OF_DAY, fine)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)

            this.fine = new java.util.Date(cal.getTime().getTime());

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch
    }// fine del metodo costruttore completo

    /**
     */
    public static Turni getTurno(String sigla) {
        Turni turno = null
        def tipi
        String siglaTurno

        if (sigla) {
            tipi = Turni.values()
            tipi?.each {
                siglaTurno = it.sigla
                if (siglaTurno.equals(sigla)) {
                    turno = it
                }// fine del blocco if
            }// fine del blocco each
        }// fine del blocco if

        return turno
    }// end of method

    /**
     */
    public static int getDurata(String sigla) {
        int durata = 0
        Turni turno

        if (sigla) {
            turno = getTurno(sigla)
            durata = turno?.durata
        }// fine del blocco if

        return durata
    }// end of method

    /**
     * Set all the (needed) parameters of field <br>
     * Empty, overridden by the subclass of each specific enumeration <br>
     */
    protected void setParameters() {
    }// end of method


} // end of Enumeration
