package it.algos.pubblica

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class TurnoService {

    static transactional = true
    public static boolean esisteAvis = false

    public static String NEW_TURNO = 'newTurno'
    public static String FILL_TURNO = 'fillTurno'
    public static String SIGLA = 'siglaTurno'
    public static String GIORNO = 'tempoNumero'
    public static String QUALIFICA = 'qualifica'
    public static String ID_KEY = 'id'
    public static String MILITE = 'milite'

    /**
     * Crea un nuovo turno/servizio <br>
     *
     * @param mappa parametri
     *        siglaTurno:   stringa per recuperare il tipo di turno selezionato
     *        tempoNumero:  stringa per recuperare il (long) della data e costruire il giorno
     */
    def newTurno = {mappa ->
        String sigla
        TipoTurno tipoTurno = TipoTurno.findBySigla(mappa?.get(SIGLA))
        Date giorno = this.getGiorno(mappa)
        String data
        long tempo
        Date giornoInizio = new Date(giorno.getTime())
        Date giornoFine = new Date(giorno.getTime())
        int oraInizio
        int oraFine
        Date giornoIniFine

        if (tipoTurno.sigla.equals(Turni.s118Sera.sigla)) {
            oraInizio = 20
            if (giorno.day == 5 || giorno.day == 6) {
                oraFine = 8
                giornoFine = giornoFine + 1
            } else {
                oraFine = 24
            }// fine del blocco if-else
        } else {
            oraInizio = Lib.getOre(tipoTurno?.inizio)
            oraFine = Lib.getOre(tipoTurno?.fine)
        }// fine del blocco if-else

        giornoInizio.hours = oraInizio
        giornoFine.hours = oraFine

        try { // prova ad eseguire il codice
            def pippoz = new Turno(giorno: giorno, tipoTurno: tipoTurno, inizio: giornoInizio, fine: giornoFine).save(flush: true)

            def stop
        } catch (Exception unErrore) { // intercetta l'errore
            log.error 'errore nella creazione di un nuovo turno'
        }// fine del blocco try-catch
    }// fine della closure

    /**
     * Restituisce il giorno <br>
     *
     * @param mappa parametri
     *        tempoNumero:  stringa per recuperare il (long) della data e costruire il giorno
     */
    def Date getGiorno(def mappa) {
        Date giorno = null
        String data
        long tempo

        if (mappa?.get(GIORNO)) {
            data = mappa.get(GIORNO)
            try { // prova ad eseguire il codice
                tempo = new Long(data)
                giorno = new Date(tempo)
            } catch (Exception unErrore) { // intercetta l'errore
                log.error 'errore nella costruzione della data'
            }// fine del blocco try-catch
        }// fine del blocco if

        return giorno
    }// fine della closure

    /**
     * Aggiunge un milite al turno esistente <br>
     * Se loggato, inserisce il nick <br>
     * Se sloggato, presenta una lista di militi da cui scegliere <br>
     * Sarebbe bene che la lista fosse filtrata per mostrare solo i militi con la qualifica richiesta <br>
     *
     * @param mappa parametri
     *        idTurno:   stringa per recuperare il (long) del record Turno
     *        qualifica: stringa per recuperare la qualifica (autista/secondo/terzo) da riempire
     *        milite:    da inserire nel campo di record Turno a seconda della qualifica
     */
    def fillTurno = {mappa ->
        String idKeyTxt
        String qualifica = ''
        Turno turno = null
        long idKeyTurno
        Milite milite = null

        if (mappa.get(ID_KEY)) {
            idKeyTxt = mappa.get(ID_KEY)
            idKeyTurno = new Long(idKeyTxt)
            turno = Turno.findById(idKeyTurno)
        }// fine del blocco if

        if (mappa.get(QUALIFICA)) {
            qualifica = mappa.get(QUALIFICA)
        }// fine del blocco if

        if (mappa.get(MILITE)) {
            milite = mappa.get(MILITE)
        }// fine del blocco if

        try { // prova ad eseguire il codice
            switch (qualifica) {
                case QualificaTurno.autista.toString():
                    turno.autista = milite
                    break
                case QualificaTurno.secondo.toString():
                    turno.secondo = milite
                    break
                case QualificaTurno.terzo.toString():
                    turno.terzo = milite
                    break
                default: // caso non definito
                    break
            } // fine del blocco switch

            turno.save(flush: true)
        } catch (Exception unErrore) { // intercetta l'errore
            log.error 'errore nella modifica di un turno'
        }// fine del blocco try-catch
    }// fine del metodo


    public void turniVuoti() {
        int giorniFuturi = 366 + 13
        int giorniPassati = 0
        Date oggi = PubblicaTagLib.creaData() + 1
        Date dataInizio
        Date giorno = null
        int inizio = 0
        int fine = 0
        Calendar cal = Calendar.getInstance()
        int numAnno
        int numWeek
        String localitàExtra

        if (!Turno.count()) {
            dataInizio = oggi - giorniPassati
            def tipoTurno = TipoTurno.findAll([sort: "ordine", order: "asc"])
            for (int k = 0; k < giorniPassati + giorniFuturi; k++) {
                tipoTurno?.each {
                    giorno = dataInizio + k
                    cal.setTime(giorno)
                    numAnno = cal.get(Calendar.DAY_OF_YEAR)
                    numWeek = cal.get(Calendar.DAY_OF_WEEK)
                    inizio = it.inizio
                    fine = it.fine

                    if (it.sigla.equals(Turni.s118Sera.sigla)) {
                        if (numWeek == 6 || numWeek == 7 || (numAnno + 1) in PubblicaTagLib.festivi12) {
                            fine = 8
                        } else {
                            fine = 24
                        }// fine del blocco if-else
                        if (numAnno == 360) {
                            fine = 24
                        }// fine del blocco if
                    }// fine del blocco if

                    switch (it.ordine) {
                        case 1: // ord-mat
                            if (numWeek != 1) {
                                new Turno(giorno: giorno, tipoTurno: it, inizio: inizio, fine: fine).save(failOnError: true)
                            }// fine del blocco if
                            break
                        case 2: // 118-mat
                        case 3: // ord-pom
                        case 4: // 118-pom
                        case 5: // 118-ser
                            new Turno(giorno: giorno, tipoTurno: it, inizio: inizio, fine: fine).save(failOnError: true)
                            break
                        case 6: // extra
                            break
                        default: // caso non definito
                            break
                    } // fine del blocco switch

                }// fine del blocco each
            } // fine del ciclo for

            // avis
            if (esisteAvis) {
                //TipoTurno tipoTurnoAvis = TipoTurno.findBySigla(Turni.avis.sigla)
                //for (int k = 0; k < giorniPassati + giorniFuturi; k++) {
                //    giorno = dataInizio + k
                //    cal.setTime(giorno)
                //    numWeek = cal.get(Calendar.DAY_OF_WEEK)
                //    inizio = 10
                //    fine = 12
                //
                //    if (numWeek == 4 || numWeek == 6) {
                //        new Turno(giorno: giorno, tipoTurno: tipoTurnoAvis, inizio: inizio, fine: fine).save(failOnError: true)
                //    }// fine del blocco if
                //
                //} // fine del ciclo for
            }// fine del blocco if

            // extra
            //TipoTurno tipoTurnoExtra = TipoTurno.findBySigla(Turni.extra.sigla)
            //giorno = oggi + 2
            //localitàExtra = 'Parma'
            //inizio = 14
            //fine = 17
            //new Turno(giorno: giorno, tipoTurno: tipoTurnoExtra, inizio: inizio, fine: fine, localitàExtra: localitàExtra).save(failOnError: true)
            //giorno = oggi + 4
            //localitàExtra = 'Piacenza'
            //inizio = 10
            //fine = 12
            //new Turno(giorno: giorno, tipoTurno: tipoTurnoExtra, inizio: inizio, fine: fine, localitàExtra: localitàExtra).save(failOnError: true)
            //giorno = oggi + 5
            //localitàExtra = 'Milano'
            //inizio = 8
            //fine = 15
            //new Turno(giorno: giorno, tipoTurno: tipoTurnoExtra, inizio: inizio, fine: fine, localitàExtra: localitàExtra).save(failOnError: true)

        }// fine del blocco if

    }// fine del metodo


    public void turniPieni() {
        Date oggi = PubblicaTagLib.creaData()
        Milite autista
        Milite secondo
        Milite terzo
        int count = Milite.count()
        def rand = new Random()
        def idTurno
        def turni = Turno.findAll([sort: "giorno", order: "asc"])

        turni?.each {
            idTurno = rand.nextInt(count)
            autista = Milite.get(idTurno)
            idTurno = rand.nextInt(count)
            secondo = Milite.get(idTurno)
            idTurno = rand.nextInt(count)
            terzo = Milite.get(idTurno)

            it.autista = autista
            it.secondo = secondo

            if (it.giorno == (oggi + 1) && it.tipoTurno.toString().equals(Turni.ordMat.sigla)) {
                it.secondo = null
            }// fine del blocco if

            if (it.giorno == (oggi + 2) && it.tipoTurno.toString().equals(Turni.s118Pom.sigla)) {
                it.autista = null
            }// fine del blocco if

            if (it.giorno == (oggi + 1) && it.tipoTurno.toString().equals(Turni.s118Mat.sigla)) {
                it.terzo = terzo
            }// fine del blocco if
            if (it.giorno == (oggi + 1) && it.tipoTurno.toString().equals(Turni.s118Pom.sigla)) {
                it.note = "Attenzione! L'autista arriva solo alle ore 15"
            }// fine del blocco if
            if (it.giorno == (oggi + 1) && it.tipoTurno.toString().equals(Turni.s118Sera.sigla)) {
                it.terzo = terzo
            }// fine del blocco if

            if (it.giorno == (oggi + 3) && it.tipoTurno.toString().equals(Turni.ordMat.sigla)) {
                it.note = "Attenzione! Secondo disponibile solo fino alle 12"
            }// fine del blocco if
            if (it.giorno == (oggi + 3) && it.tipoTurno.toString().equals(Turni.s118Mat.sigla)) {
                it.autista = null
            }// fine del blocco if
            if (it.giorno == (oggi + 3) && it.tipoTurno.toString().equals(Turni.ordPom.sigla)) {
                it.autista = null
                it.secondo = null
            }// fine del blocco if

            if (it.giorno == (oggi + 4) && it.tipoTurno.toString().equals(Turni.ordMat.sigla)) {
                it.secondo = null
            }// fine del blocco if
            if (it.giorno == (oggi + 4) && it.tipoTurno.toString().equals(Turni.s118Mat.sigla)) {
                it.autista = null
                it.secondo = null
            }// fine del blocco if
            if (it.giorno == (oggi + 4) && it.tipoTurno.toString().equals(Turni.s118Sera.sigla)) {
                it.autista = null
            }// fine del blocco if

            if (it.giorno == (oggi + 5) && it.tipoTurno.toString().equals(Turni.ordMat.sigla)) {
                it.autista = null
                it.secondo = null
            }// fine del blocco if
            if (it.giorno == (oggi + 5) && it.tipoTurno.toString().equals(Turni.ordPom.sigla)) {
                it.autista = null
            }// fine del blocco if
            if (it.giorno == (oggi + 5) && it.tipoTurno.toString().equals(Turni.s118Pom.sigla)) {
                it.secondo = null
            }// fine del blocco if

            if (esisteAvis) {
                if (it.tipoTurno.toString().equals(Turni.avis.sigla)) {
                    it.secondo = null
                    it.terzo = null
                }// fine del blocco if
            }// fine del blocco if

            it.save(flush: true)
        }// fine del blocco each

    }// fine del metodo

    public Logo logModifiche(def mappa) {
        Logo logo
        Turno turno
        Date giorno
        ModificaRecord modificaEnum = null
        def user
        GrailsUser grailsUser
        SecUser secUser
        String username = 'centralino'  // valore di 'default'
        String data
        String ora
        String utente
        String modifica
        String tipoTurno = ''
        String dataTurno = ''
        String operazione = ''
        String tipoMilite = ''
        String nomeMilite = ''
        String altroMilite = ''

        if (mappa?.turno) {
            if (mappa.turno instanceof Turno) {
                turno = (Turno) mappa.turno
                tipoTurno = turno.tipoTurno.sigla
                dataTurno = this.getDataTesto(turno.giorno)
            }// fine del blocco if
        }// fine del blocco if

        if (mappa?.modifica) {
            modificaEnum = mappa.modifica
        }// fine del blocco if

        // recupera dal plugin
        user = springSecurityService.getPrincipal()

        // controlla prima del casting
        if (user && user instanceof GrailsUser) {
            grailsUser = (GrailsUser) user
            username = grailsUser.username
        }// fine del blocco if
        secUser = SecUser.findByUsername(username)

        // regola i parametri obbligatori
        data = this.getDataCorrenteTesto()
        ora = this.getOraCorrenteTesto()
        if (secUser) {
            utente = secUser.username
        } else {
            utente = username
        }// fine del blocco if-else
        if (modificaEnum) {
            modifica = modificaEnum.sigla
        } else {
            modifica = 'indeterminata'
        }// fine del blocco if-else


        logo = new Logo(data: data, ora: ora, utente: utente, modifica: modifica, tipoTurno: tipoTurno, dataTurno: dataTurno, operazione: operazione)

        // regola i parametri facoltativi
        if (tipoMilite) {
            logo?.tipoMilite = tipoMilite
        }// fine del blocco if

        if (nomeMilite) {
            logo?.nomeMilite = nomeMilite
        }// fine del blocco if

        if (altroMilite) {
            logo?.altroMilite = altroMilite
        }// fine del blocco if

        logo?.save(flush: true)
        return logo
    }// fine del metodo

    private Logo logModificheDatabase(Turno turno, ModificaRecord modifica) {
        Logo logo
        def user
        GrailsUser grailsUser
        SecUser utente
        String username = 'centralino'  // valore di 'default'
        Date adesso = new Date()

        // recupera dal plugin
        user = springSecurityService.getPrincipal()

        // controlla prima del casting
        if (user && user instanceof GrailsUser) {
            grailsUser = (GrailsUser) user
            username = grailsUser.username
        }// fine del blocco if
        utente = SecUser.findByUsername(username)

        logo = new Logo(utente: utente, data: adesso, turno: turno, modifica: modifica)
        logo?.save(flush: true)

        // valore di ritorno
        return logo
    }// fine del metodo

    /*
     * Dovrei scrivere:
     * 21-2-11 ore 7:04 Domenico ha inserito il turno Ord-mat del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha cancellato il turno Ord-mat del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha inserito un turno extra del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha cancellato un turno extra del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 segnando l'autista Crozza Paolo
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 cancellando l'autista Crozza Paolo
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 sostituendo il secondo Crozza Paolo con Fratti Claudia
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 modificando l'orario
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 modificando la località
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 modificando le note
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 segnalando un problema di orario per l'autista
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 rimuovendo un problema di orario per il secondo
     * data/ora - utente - operazioneTurno - tipoTurno - turno - giornoTurno - operazioneModifica - qualifica - milite - varie
     */

    private void logModificheTesto(Turno turno, ModificaRecord modifica, Logo logo) {
        String testo
        def loggato = 'Un milite generico (dal centralino in sede)'
        def militeTxt = 'il milite'
        def milite = 'non specificato'
        def turnoTxt = 'non specificato'
        Date giorno
        def giornoTxt = 'oggi'

        testo = 'Alle ore'
        testo += 'di martedì 17 febbraio '
        testo += logo.utente.username
        testo += ' ha modificato il turno '
        testo += 'ordinario mattino di giovedì 19 febbraio, '
        testo += 'inserendo '
        testo += 'Pasquale Rossi '
        testo += 'come autista'

        logo.testo = testo
        logo.save(flush: true)
        log.info testo
    }// fine del metodo


    private def autistiOrdinario() {
        return Milite.findAllByAttivoAndAutistaOrdinario(true, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo
    private def altriAutistiOrdinario() {
        return Milite.findAllByAttivoAndAutistaOrdinario(true, false, [sort: 'cognome', order: 'asc'])
    }// fine del metodo


    private def secondiOrdinario() {
        return Milite.findAllByAttivoAndSoccorritoreOrdinario(true, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo
    private def altriSecondiOrdinario() {
        return Milite.findAllByAttivoAndSoccorritoreOrdinario(true, false, [sort: 'cognome', order: 'asc'])
    }// fine del metodo


    private def terziOrdinario() {
        return Milite.findAllByAttivoAndTerzoOrdinario(true, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo
    private def altriTerziOrdinario() {
        return Milite.findAllByAttivoAndTerzoOrdinario(true, false, [sort: 'cognome', order: 'asc'])
    }// fine del metodo


    private def autisti118() {
        return Milite.findAllByAttivoAndAutista118(true, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo
    private def altriAutisti118() {
        return Milite.findAllByAttivoAndAutista118AndAutistaOrdinario(true, false, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo


    private def secondi118() {
        return Milite.findAllByAttivoAndSoccorritore118(true, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo
    private def altriSecondi118() {
        return Milite.findAllByAttivoAndSoccorritore118AndSoccorritoreOrdinario(true, false, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo


    private def terzi118() {
        return Milite.findAllByAttivoAndTerzo118(true, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo
    private def altriTerzi118() {
        return Milite.findAllByAttivoAndTerzo118AndTerzoOrdinario(true, false, true, [sort: 'cognome', order: 'asc'])
    }// fine del metodo


    def int getNumAutistiOrdinario() {
        return autistiOrdinario().size()
    }// fine del metodo
    def int getNumAltriAutistiOrdinario() {
        return altriAutistiOrdinario().size()
    }// fine del metodo


    def int getNumSecondiOrdinario() {
        return secondiOrdinario().size()
    }// fine del metodo
    def int getNumAltriSecondiOrdinario() {
        return altriSecondiOrdinario().size()
    }// fine del metodo


    def int getNumTerziOrdinario() {
        return terziOrdinario().size()
    }// fine del metodo
    def int getNumAltriTerziOrdinario() {
        return altriTerziOrdinario().size()
    }// fine del metodo


    def int getNumAutisti118() {
        return autisti118().size()
    }// fine del metodo
    def int getNumAltriAutisti118() {
        return altriAutisti118().size()
    }// fine del metodo


    def int getNumSecondi118() {
        return secondi118().size()
    }// fine del metodo
    def int getNumAltriSecondi118() {
        return altriSecondi118().size()
    }// fine del metodo


    def int getNumTerzi118() {
        return terzi118().size()
    }// fine del metodo
    def int getNumAltriTerzi118() {
        return altriTerzi118().size()
    }// fine del metodo


    def getAutistiOrdinario() {
        def autisti = this.autistiOrdinario()

        def altri = this.altriAutistiOrdinario()
        altri?.each {
            autisti.add(it)
        }// fine del blocco each

        return autisti
    }// fine del metodo

    def getSecondiOrdinario() {
        def secondi = this.secondiOrdinario()

        def altri = this.altriSecondiOrdinario()
        altri?.each {
            secondi.add(it)
        }// fine del blocco each

        return secondi
    }// fine del metodo

    def getTerziOrdinario() {
        def terzi = this.terziOrdinario()

        return terzi
    }// fine del metodo

    def getAutisti118() {
        def autisti = this.autisti118()

        def altri = this.altriAutisti118()
        altri?.each {
            autisti.add(it)
        }// fine del blocco each

        return autisti
    }// fine del metodo

    def getSecondi118() {
        def secondi = this.secondi118()

        def altri = this.altriSecondi118()
        altri?.each {
            secondi.add(it)
        }// fine del blocco each

        return secondi
    }// fine del metodo

    def getTerzi118() {
        def terzi = this.terzi118()

        def altri = this.altriTerzi118()
        altri?.each {
            terzi.add(it)
        }// fine del blocco each

        return terzi
    }// fine del metodo

}// fine del service
