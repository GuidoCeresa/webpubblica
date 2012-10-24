package it.algos.pubblica

import org.springframework.web.servlet.support.RequestContextUtils as RCU

import org.apache.commons.lang.time.FastDateFormat
import org.springframework.context.NoSuchMessageException
import org.springframework.util.StringUtils

import java.sql.Timestamp
import java.text.DateFormat
import java.text.DateFormatSymbols

class PubblicaTagLib {

    static namespace = "pub" //stand for pubblica
    static returnObjectForTags = ['formatBoolean', 'formatDate', 'formatNumber', 'encodeAs']

    // capodanno(1.1), epifania(6.1), carnevale(6.3), pasqua(24.4), pasquetta(25.4), liberazione(25.4), lavoro(1.5),
    // repubblica(2.6), ferragosto(15.8), santi(1.11), immacolata(8.12), natale(25.12), santo stefano(26.12)
    static festivi11 = [1, 6, 65, 114, 115, 115, 121, 153, 227, 305, 342, 359, 360]
    // capodanno(1.1), epifania(6.1), carnevale(19.2), pasqua(8.4), pasquetta(9.4), liberazione(25.4), lavoro(1.5),
    // repubblica(2.6), ferragosto(15.8), santi(1.11), immacolata(8.12), natale(25.12), santo stefano(26.12)
    static festivi12 = [1, 6, 50, 99, 100, 116, 122, 154, 228, 306, 343, 360, 361]

    public static String titoli = '339966'
    private static String turnoLiberoLontano = 'FFCC66'
    private static String turnoLiberoVicino = 'FF7733'
    private static String turnoLiberoCritico = 'FF3333'

    private static String turnoSegnatoValido = '63BA0B'
    private static String turnoSegnatoBloccato = '70AAFF'
    private static String turnoArchivio = 'c3c3c3'
    private static String turnoMancante = 'f8f8f8'
    public static String turni = 'C2E0D1'
    private static String orari = 'd3f1e2'

    private static int giorniVicini = 4
    private static int giorniCritici = 2
    private static int giorniBlocco = 2
    private static String aCapo = '<br>'
    private static String spazio = '&nbsp;'
    private static String spazioDoppio = spazio + spazio
    private static String spazioQuattro = spazioDoppio + spazioDoppio
    private static String spazioOtto = spazioQuattro + spazioQuattro
    private static String spazioLink = spazioOtto + spazioOtto + spazioQuattro
    private static String cellaVuota = '<td>' + spazio + '</td>'

    private static String SEP = '-'
    /**
     * Titolo della pagina <br>
     *
     * @return testo del tag
     */
    def titoloPagina = {
        out << Lib.getTitoloPagina('Pubblica Assistenza Val Tidone - Val Luretta')
    }// fine della closure

    /**
     * Tabella dei turni <br>
     *
     * @param mappa parametri
     *        dataInizio: giorno iniziale del periodo da considerare
     *        dataFine:   giorno finale del periodo da considerare
     * @return testo del tag
     */
    def tabella = {def params ->
        String testoOut
        String testo = ''

        testo += this.captionTabella(params)
        testo += this.titoliTabella(params)
        testo += this.corpoTabella(params)

        testoOut = Lib.getTable(testo)
        testoOut += this.legenda()
        testoOut += this.noteBottom()

        out << testoOut
    }// fine della closure

    /**
     * Caption sopra la tabella <br>
     */
    private String captionTabella(def params) {
        String testoOut = ''
        String testoCaption = ''

        testoOut += Lib.getCaption(testoCaption)

        return testoOut
    }// fine del metodo

    /**
     * Titoli di testa della tabella <br>
     *
     * @param mappa parametri
     *        dataInizio: giorno iniziale del periodo da considerare
     *        dataFine:   giorno finale del periodo da considerare
     */
    private String titoliTabella(def params) {
        String testoOut = ''
        String testoTurni
        String testoMese
        String testoGiorni = ''
        String testoRigaTitolo = ''
        def inizio
        def fine
        int giorni
        def giorno

        inizio = params.dataInizio
        fine = params.dataFine

        if (inizio && fine) {
            giorni = fine - inizio

            testoTurni = 'Turni di servizio '
            testoMese = this.getMeseCorrente(inizio);
            testoRigaTitolo += Lib.getTitoloDoppio(testoTurni + testoMese, titoli)

            for (int k = 0; k <= giorni; k++) {
                giorno = inizio + k
                testoGiorni += this.titoloGiorno(giorno)
            } // fine del ciclo for

            testoRigaTitolo += testoGiorni

            testoRigaTitolo = Lib.getRiga(testoRigaTitolo)
            testoOut = Lib.getHead(testoRigaTitolo)
        }// end of if

        return testoOut
    }// fine del metodo

    /**
     * Corpo della tabella <br>
     *
     * @param mappa parametri
     *        dataInizio: giorno iniziale del periodo da considerare
     *        dataFine:   giorno finale del periodo da considerare
     * @return testo del tag
     */
    private String corpoTabella(def params) {
        String testoOut
        String testoBody = ''
        def tipiTurno = TipoTurno.findAllByVisibile(true, [sort: "ordine", order: "asc"])

        Date inizio = null
        Date fine = null
        int riga

        if (params.dataInizio && params.dataInizio instanceof Date) {
            inizio = params.dataInizio
        }// fine del blocco if

        if (params.dataFine && params.dataFine instanceof Date) {
            fine = params.dataFine
        }// fine del blocco if

        if (inizio && fine) {
            tipiTurno?.each {
                riga = 1
                testoBody += this.turnoSingolo(it, inizio, fine, riga)
                if (it.multiplo) {
                    while (this.esisteUnTurnoNellaUltimaRiga(it, inizio, fine, riga)) {
                        riga++
                        testoBody += this.turnoSingolo(it, inizio, fine, riga)
                    }// fine del blocco while
                }// fine del blocco if
            }// end of each
        }// end of if

        testoOut = Lib.getBody(testoBody)

        return testoOut
    }// fine del metodo

    private boolean esisteUnTurnoNellaUltimaRiga(TipoTurno tipoTurno, Date inizio, Date fine, int riga) {
        boolean esiste = false
        LinkedHashMap<Date, Turno> mappaTurniDiUnTipo = this.creaMappaTurniDiUnTipo(tipoTurno, inizio, fine, riga)

        mappaTurniDiUnTipo?.each {
            if (it.value) {
                esiste = true
            }// fine del blocco if
        }

        return esiste
    }// fine del metodo

    /**
     * Righe (4) della tabella per un turno <br>
     *
     * @param tipoTurno
     * @param dataInizio : giorno iniziale del periodo da considerare
     * @param dataFine :   giorno finale del periodo da considerare
     * @return testo del tag
     */
    private String turnoSingolo(TipoTurno tipoTurno, Date inizio, Date fine, int riga) {
        String testoOut
        String testoBody
        LinkedHashMap<Date, Turno> mappaTurniDiUnTipo = this.creaMappaTurniDiUnTipo(tipoTurno, inizio, fine, riga)

        // riga descrizione/qualifica/orario
        testoBody = this.riga(mappaTurniDiUnTipo, tipoTurno, null)

        // riga autista
        if (tipoTurno.autista) {
            try { // prova ad eseguire il codice
                testoBody += this.riga(mappaTurniDiUnTipo, tipoTurno, QualificaTurno.autista)
            } catch (Exception unErrore) { // intercetta l'errore
                log.error ''
            }// fine del blocco try-catch
        }// end of if

        // riga secondo
        if (tipoTurno.secondo) {
            try { // prova ad eseguire il codice
                testoBody += this.riga(mappaTurniDiUnTipo, tipoTurno, QualificaTurno.secondo)
            } catch (Exception unErrore) { // intercetta l'errore
                log.error ''
            }// fine del blocco try-catch
        }// end of if

        // riga terzo
        if (tipoTurno.terzo) {
            try { // prova ad eseguire il codice
                testoBody += this.riga(mappaTurniDiUnTipo, tipoTurno, QualificaTurno.terzo)
            } catch (Exception unErrore) { // intercetta l'errore
                log.error ''
            }// fine del blocco try-catch
        }// end of if

        testoOut = Lib.getBody(testoBody)

        return testoOut
    }// fine del metodo

    /**
     * Mappa dei turni <br>
     *
     * @param tipoTurno
     * @param dataInizio : giorno iniziale del periodo da considerare
     * @param dataFine :   giorno finale del periodo da considerare
     * @return mappaTurni
     *        key: giorno
     *        value: turno (eventualmente nullo)
     */
    private LinkedHashMap<Date, Turno> creaMappaTurniDiUnTipo(TipoTurno tipoTurno, Date inizio, Date fine, int riga) {
        LinkedHashMap<Date, Turno> mappaTurniDiUnTipo = new LinkedHashMap<Date, Turno>()
        int giorni
        def giorno
        Turno turno
        def turni

        if (riga > 0) {
            riga--
        }// fine del blocco if

        if (inizio && fine) {
            giorni = fine - inizio

            for (int k = 0; k <= giorni; k++) {
                giorno = (inizio + k).toTimestamp()
                turni = Turno.findAllByTipoTurnoAndGiorno(tipoTurno, giorno, [sort: "id", order: "asc"])
                if (turni && turni.size() > riga) {
                    mappaTurniDiUnTipo.put(giorno, turni[riga])
                } else {
                    mappaTurniDiUnTipo.put(giorno, (Turno) null)
                }// fine del blocco if-else
            } // fine del ciclo for

        }// end of if

        return mappaTurniDiUnTipo
    }// fine del metodo

    /**
     * Riga di un turno (tipo) <br>
     * Quattro righe per ogni turno <br>
     *
     * @param mappaTurni
     *        key: giorno
     *        value: turno (eventualmente nullo)
     * @param tipoTurno
     * @return testo del tag
     */
    private String riga(LinkedHashMap<Date, Turno> mappaTurni, TipoTurno tipoTurno, QualificaTurno qualifica) {
        String testoOut
        String testoRiga = ''
        Date giorno
        Turno turno

        try { // prova ad eseguire il codice
            if (qualifica == null) {
                testoRiga += this.cellaTipoTurno(tipoTurno)
            }// fine del blocco if
            testoRiga += this.cellaQualifica(tipoTurno, qualifica)

        } catch (Exception unErrore) { // intercetta l'errore
            log.error ''
        }// fine del blocco try-catch

        if (mappaTurni) {
            mappaTurni.keySet().each {
                try { // prova ad eseguire il codice
                    giorno = it
                } catch (Exception unErrore) { // intercetta l'errore
                    log.error ''
                }// fine del blocco try-catch

                try { // prova ad eseguire il codice
                    turno = mappaTurni.get(it)
                } catch (Exception unErrore) { // intercetta l'errore
                    log.error ''
                }// fine del blocco try-catch

                try { // prova ad eseguire il codice
                    testoRiga += this.cellaTurno((Turno) turno, tipoTurno, qualifica, giorno)
                } catch (Exception unErrore) { // intercetta l'errore
                    log.error ''
                    testoRiga += this.cellaTurno((Turno) turno, tipoTurno, qualifica, giorno)
                }// fine del blocco try-catch
            }// end of each
        }// fine del blocco if

        testoOut = Lib.getRiga(testoRiga)

        return testoOut
    }// fine del metodo

    /**
     * Prima colonna <br>
     */
    private String cellaTipoTurno(TipoTurno tipoTurno) {
        String testoOut
        String testo

        testo = tipoTurno.descrizione
        testo = Lib.getBold(testo)

        if (tipoTurno.secondo && tipoTurno.terzo) {
            testoOut = Lib.getCellaQuattro(testo, PubblicaTagLib.turni)
        } else {
            if (tipoTurno.secondo) {
                testoOut = Lib.getCellaTripla(testo, PubblicaTagLib.turni)
            } else {
                testoOut = Lib.getCellaDoppia(testo, PubblicaTagLib.turni)
            }// fine del blocco if-else
        }// fine del blocco if-else

        return testoOut
    }// fine del metodo

    /**
     * Seconda colonna <br>
     */
    private String cellaQualifica(TipoTurno tipoTurno, QualificaTurno qualifica) {
        String testoOut
        String testo

        if (qualifica) {
            testo = ' (' + qualifica + ')'
            if (tipoTurno.sigla.equals(Turni.centralino.sigla)) {
                testo = ' (operatore)'
            }// fine del blocco if
            testoOut = Lib.getCella(testo, turni)
        } else {
            if (tipoTurno.sigla.equals(Turni.extra.sigla)) {
                testo = '(località)'
            } else {
                testo = '(orario)'
            }// fine del blocco if-else
            testoOut = Lib.getCella(testo, orari)
        }// fine del blocco if-else

        return testoOut
    }// fine del metodo

    /**
     * Riga di una settimana <br>
     *
     * @return testo del tag
     */
    private String cellaTurno(Turno turno, TipoTurno tipoTurno, QualificaTurno qualifica, def giorno) {
        String testo = ''

        if (turno instanceof Turno) {
            switch (qualifica) {
                case QualificaTurno.autista:
                    if (turno.autista) {
                        testo += this.cellaPiena(turno, qualifica, giorno)
                    } else {
                        testo += this.cellaVuota(turno, qualifica, giorno)
                    }// fine del blocco if-else
                    break
                case QualificaTurno.secondo:
                    if (turno.secondo) {
                        testo += this.cellaPiena(turno, qualifica, giorno)
                    } else {
                        testo += this.cellaVuota(turno, qualifica, giorno)
                    }// fine del blocco if-else
                    break
                case QualificaTurno.terzo:
                    try { // prova ad eseguire il codice
                        if (turno.terzo) {
                            testo += this.cellaPiena(turno, qualifica, giorno)
                        } else {
                            testo += this.cellaVuota(turno, qualifica, giorno)
                        }// fine del blocco if-else
                    } catch (Exception unErrore) { // intercetta l'errore
                        testo += this.cellaVuota(turno, qualifica, giorno)
                    }// fine del blocco try-catch
                    break
                default: // caso non definito
                    testo += this.cellaOrario(turno)
                    break
            } // fine del blocco switch
        } else {
            if (qualifica) {
                testo += this.coloreCellaMancante(tipoTurno, giorno)
            } else {
                testo += Lib.getCella('', orari)
            }// fine del blocco if-else
        }// fine del blocco if-else

        return testo
    }// fine del metodo


    private String cellaOrario(Turno turno) {
        String testoOut
        String testo = ''
        TipoTurno tipoTurno = turno?.tipoTurno
        def inizio = 0
        def fine = 0
        def minuti = 0
        String sep = ':'
        String tempo
        String specifica = ''
        Calendar cal = Calendar.getInstance()
        cal.setTime(turno.giorno)
        int numWeek = cal.get(Calendar.DAY_OF_WEEK)
        String giornoSuccessivo = ''

        if (turno) {
            cal.setTime(turno.inizio)
            inizio = cal.get(GregorianCalendar.HOUR_OF_DAY)
            minuti = cal.get(GregorianCalendar.MINUTE)
            if (minuti) {
                inizio += sep + minuti
            }// fine del blocco if

            cal.setTime(turno.fine)
            fine = cal.get(GregorianCalendar.HOUR_OF_DAY)
            minuti = cal.get(GregorianCalendar.MINUTE)
            if (minuti) {
                fine += sep + minuti
            }// fine del blocco if
        }// fine del blocco if

        if (tipoTurno) {
            if (tipoTurno.sigla.equals(Turni.s118Sera.sigla)) {
                if (fine == 8) {
                    specifica = ' notte'
                    giornoSuccessivo = ' ' + Lib.getWeek(numWeek)
                } else {
                    fine = 24
                    specifica = ' sera'
                }// fine del blocco if-else
            }// fine del blocco if
        }// fine del blocco if

        if (turno.localitàExtra) {
            specifica = turno.localitàExtra
        }// fine del blocco if

        tempo = inizio
        if (fine) {
            tempo += SEP
            tempo += fine
        }// fine del blocco if
        tempo += giornoSuccessivo

        testo += specifica
        testo += ' '
        testo += tempo

        testoOut = Lib.getCella(testo, orari)
        return testoOut
    }// fine del metodo

    private String cellaPiena(Turno turno, QualificaTurno qualifica, Date giorno) {
        String testoOut
        String testo = ''
        String nome = ''
        boolean asterisco = false

        switch (qualifica) {
            case QualificaTurno.autista:
                if (turno.autista) {
                    nome += turno.autista.nomePuntato()
                    if (turno.problemiAutista) {
                        asterisco = true
                    }// fine del blocco if
                } else {
                    nome += spazio
                }// fine del blocco if-else
                break
            case QualificaTurno.secondo:
                if (turno.secondo) {
                    nome += turno.secondo.nomePuntato()
                    if (turno.problemiSecondo) {
                        asterisco = true
                    }// fine del blocco if
                } else {
                    nome += spazio
                }// fine del blocco if-else
                break
            case QualificaTurno.terzo:
                if (turno.terzo) {
                    nome += turno.terzo.nomePuntato()
                    if (turno.problemiTerzo) {
                        asterisco = true
                    }// fine del blocco if
                } else {
                    nome += spazio
                }// fine del blocco if-else
                break
            default: // caso non definito
                break
        } // fine del blocco switch

        if (asterisco) {
            testo = '<font color="#FF0000">'
            testo += '*'
            testo += '</font>'
        }// fine del blocco if
        testo += nome

        testoOut = this.coloreCellaPiena(turno, giorno, testo)

        return testoOut
    }// fine del metodo

    private String coloreCellaMancante(TipoTurno tipoTurno, def data) {
        String testo = ''
        String sigla = ''
        String giorno = ''
        long tempo

        if (tipoTurno) {
            sigla = tipoTurno.sigla
        }// fine del blocco if

        if (data) {
            if (data instanceof Timestamp) {
                tempo = data.time
                giorno = tempo.toString()
            }// fine del blocco if
        }// fine del blocco if

        testo += '<td bgcolor=#'
        testo += turnoMancante
        testo += ' align=center'
        testo += '>'
        testo += '<a href="/webpubblica/turno/'
        testo += TurnoService.NEW_TURNO
        testo += '?'
        testo += TurnoService.SIGLA
        testo += '='
        testo += sigla
        testo += '&'
        testo += TurnoService.GIORNO
        testo += '='
        testo += giorno
        testo += '">'
        testo += spazioLink
        testo += '</a>'
        testo += '</td>'

        return testo
    }// fine del metodo


    private String cellaVuota(Turno turno, QualificaTurno qualifica, def data) {
        String testo = ''
        Date dataCorrente = Lib.creaData()
        int distanza = data - dataCorrente
        String colore = turnoArchivio
        long tempo
        long idKey = 0.0

        if (distanza > 0 && distanza <= giorniCritici) {
            if (qualifica.equals(QualificaTurno.terzo)) {
                colore = turnoLiberoLontano
            } else {
                colore = turnoLiberoCritico
            }// fine del blocco if-else
            if (turno.tipoTurno.sigla.equals(Turni.avis.sigla) && qualifica.equals(QualificaTurno.secondo)) {
                colore = turnoLiberoLontano
            }// fine del blocco if
        }// fine del blocco if

        if (distanza > giorniCritici && distanza <= giorniVicini) {
            if (qualifica.equals(QualificaTurno.terzo)) {
                colore = turnoLiberoLontano
            } else {
                colore = turnoLiberoVicino
            }// fine del blocco if-else
            if (turno.tipoTurno.sigla.equals(Turni.avis.sigla) && qualifica.equals(QualificaTurno.secondo)) {
                colore = turnoLiberoLontano
            }// fine del blocco if
        }// fine del blocco if

        if (distanza > giorniVicini) {
            colore = turnoLiberoLontano
        }// fine del blocco if

        if (turno.tipoTurno.sigla.equals(Turni.centralino.sigla)) {
            colore = turnoLiberoLontano
        }// fine del blocco if

        if (distanza <= 0) {
            colore = turnoArchivio
        }// fine del blocco if

        if (turno) {
            idKey = turno.id
        }// fine del blocco if

        testo += '<td bgcolor=#'
        testo += colore
        testo += ' align=center'
        testo += '>'
        testo += '<a href="/webpubblica/turno/'
        testo += TurnoService.FILL_TURNO
        testo += '?'
        testo += TurnoService.ID_KEY
        testo += '='
        testo += idKey.toString()
        testo += '&'
        testo += TurnoService.QUALIFICA
        testo += '='
        testo += qualifica.toString()
        testo += '">'
        testo += spazioLink
        testo += '</a>'
        testo += '</td>'

        return testo
    }// fine del metodo

    private String coloreCellaPiena(Turno turno, Date giorno, String nome) {
        String testoOut
        String testo = ''
        Date dataCorrente = Lib.creaData()
        int distanza = giorno - dataCorrente
        String colore = turnoArchivio
        long idKey = 0.0

        if (distanza <= 0) {
            colore = turnoArchivio
        }// fine del blocco if

        if (distanza > 0 && distanza <= giorniBlocco) {
            colore = turnoSegnatoBloccato
        }// fine del blocco if

        if (distanza > giorniBlocco) {
            colore = turnoSegnatoValido
        }// fine del blocco if

        if (turno) {
            idKey = turno.id
        }// fine del blocco if

        testo += '<a href="/webpubblica/turno/'
        testo += TurnoService.FILL_TURNO
        testo += '?'
        testo += TurnoService.ID_KEY
        testo += '='
        testo += idKey.toString()
        testo += '">'
        testo += nome
        testo += '</a>'

        testoOut = Lib.getCella(testo, colore)

        return testoOut
    }// fine del metodo

    /**
     * Titoli di testa della tabella <br>
     *
     * @giorno nella testata della tabella
     * @return testo del tag
     */
    private String titoloGiorno(Date giorno) {
        String nome
        String numero
        GregorianCalendar cal = new GregorianCalendar()

        cal.setTime(giorno)
        nome = this.getGiorno(giorno)
        numero = cal.get(Calendar.DAY_OF_MONTH)

        return Lib.getTitolo(nome + ' ' + numero, titoli)
    }// fine del metodo

    /**
     * Legenda in fondo <br>
     *
     * @return testo del tag
     */
    private String legenda() {
        String testo = ''

        testo += '<table>'
        testo += '<tr>'
        testo += '<th bgcolor=#'
        testo += titoli
        testo += ' width="100"'
        testo += '>'
        testo += '<FONT COLOR=WHITE FACE="Geneva, Arial" SIZE=0>'
        testo += 'Legenda'
        testo += '</font>'
        testo += '</th>'

        testo += this.cellaLegenda(turnoArchivio, 'Turno già effettuato')
        testo += this.cellaLegenda(turnoSegnatoBloccato, 'Turno assegnato bloccato e non più modificabile')
        testo += this.cellaLegenda(turnoLiberoCritico, 'Turno critico da assegnare subito')
        testo += this.cellaLegenda(turnoLiberoVicino, 'Turno da assegnare nei prossimi giorni')
        testo += this.cellaLegenda(turnoSegnatoValido, 'Turno assegnato normale')
        testo += this.cellaLegenda(turnoLiberoLontano, 'Turno previsto e non ancora assegnato')
        testo += this.cellaLegenda(turnoMancante, 'Turno non previsto')

        testo += Lib.getCella('<font color="#FF0000">*</font> Problemi di orario del milite (vedi note)', turni)

        testo += '</table>'

        return testo
    }// fine del metodo

    /**
     * Note in fondo <br>
     *
     * @return testo del tag
     */
    private String noteBottom() {
        String testo = ''
        def dateFormat
        Date ultimaData = null
        def version = grailsApplication.metadata['app.version']
        def query = "from Turno as t order by t.lastUpdated desc"
        def ultimoTurno = Turno.findAll(query, [max: 1])
        Calendar cal = Calendar.getInstance()
        def ora
        def minuto

        if (ultimoTurno && ultimoTurno.size() > 0) {
            ultimaData = ultimoTurno[0].lastUpdated
        }// fine del blocco if

        testo += 'Versione '
        testo += version
        dateFormat = FastDateFormat.getDateInstance(FastDateFormat.FULL)
        if (ultimaData && dateFormat) {
            testo += ' - Ultima modifica tabellone: '
            testo += dateFormat.format(ultimaData)

            cal.setTime(ultimaData)
            ora = cal.get(GregorianCalendar.HOUR_OF_DAY)
            minuto = cal.get(GregorianCalendar.MINUTE)
            testo += ' alle '
            testo += ora + ':' + minuto
        }// fine del blocco if

        return testo
    }// fine del metodo

    /**
     * Legenda in fondo <br>
     *
     * @return testo del tag
     */
    private String cellaLegenda(String colore, String descrizione) {
        String testoOut

        testoOut = Lib.getCella(spazio, colore)
        testoOut += Lib.getCella(descrizione, orari)

        return testoOut
    }// fine del metodo

    /**
     * Orario <br>
     */
    def formatTimeOra = { attrs ->
        String testo = ''
        def date
        def dateFormat

        if (attrs.containsKey('date')) {
            date = attrs.date
            if (date != null) {
                dateFormat = FastDateFormat.getDateInstance(FastDateFormat.FULL)
                if (dateFormat != null) {
                    testo = dateFormat.format(date)
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        out << testo
    }// fine del metodo

    /**
     * Data completa in forma estesa <br>
     */
    def formatDateAll = { attrs ->
        String testo = ''
        def date
        def dateFormat

        if (attrs.containsKey('date')) {
            date = attrs.date
            if (date != null) {
                dateFormat = FastDateFormat.getDateInstance(FastDateFormat.FULL)
                if (dateFormat != null) {
                    testo = dateFormat.format(date)
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        out << testo
    }// fine del metodo

    /**
     * Outputs the given <code>Date</code> object in the specified format. If
     * the <code>date</code> is not given, then the current date/time is used.
     * If the <code>format</code> option is not given, then the date is output
     * using the default format.<br/>
     *
     * e.g., &lt;g:formatDate date="${myDate}" format="yyyy-MM-dd HH:mm" /&gt;<br/>
     *
     * @see java.text.SimpleDateFormat
     *
     * @attr date the date object to display; defaults to now if not specified
     * @attr format The formatting pattern to use for the date, see SimpleDateFormat
     * @attr formatName Look up format from the default MessageSource / ResourceBundle (i18n/*.properties file) with this key. If format and formatName are empty, format is looked up with 'default.date.format' key. If the key is missing, 'yyyy-MM-dd HH:mm:ss z' formatting pattern is used.
     * @attr type The type of format to use for the date / time. format or formatName aren't used when type is specified. Possible values: 'date' - shows only date part, 'time' - shows only time part, 'both'/'datetime' - shows date and time
     * @attr timeZone the time zone for formatting. See TimeZone class.
     * @attr locale Force the locale for formatting.
     * @attr style Use default date/time formatting of the country specified by the locale. Possible values: SHORT (default), MEDIUM, LONG, FULL . See DateFormat for explanation.
     * @attr dateStyle Set separate style for the date part.
     * @attr timeStyle Set separate style for the time part.
     */
    def formatDate = { attrs ->
        def date
        if (attrs.containsKey('date')) {
            date = attrs.date
            if (date == null) return
        }
        else {
            date = new Date()
        }

        def locale = resolveLocale(attrs.locale)
        String timeStyle = null
        String dateStyle = null
        if (attrs.style != null) {
            String style = attrs.style.toString().toUpperCase()
            timeStyle = style
            dateStyle = style
        }

        if (attrs.dateStyle != null) {
            dateStyle = attrs.dateStyle.toString().toUpperCase()
        }
        if (attrs.timeStyle != null) {
            timeStyle = attrs.timeStyle.toString().toUpperCase()
        }
        String type = attrs.type?.toString()?.toUpperCase()
        def formatName = attrs.formatName
        def format = attrs.format
        def timeZone = attrs.timeZone
        if (timeZone != null) {
            if (!(timeZone instanceof TimeZone)) {
                timeZone = TimeZone.getTimeZone(timeZone as String)
            }
        }
        else {
            timeZone = TimeZone.getDefault()
        }

        def dateFormat
        if (!type) {
            if (!format && formatName) {
                format = messageHelper(formatName, null, null, locale)
                if (!format) {
                    throwTagError("Attribute [formatName] of Tag [formatDate] specifies a format key [$formatName] that does not exist within a message bundle!")
                }
            }
            else if (!format) {
                format = messageHelper('date.format', {
                    messageHelper('pub.date.format', 'MM-dd',
                            null, locale)
                }, null, locale)
            }
            dateFormat = FastDateFormat.getInstance(format, timeZone, locale)
        }
        else {
            if (type == 'DATE') {
                dateFormat = FastDateFormat.getDateInstance(parseStyle(dateStyle), timeZone, locale)
            }
            else if (type == 'TIME') {
                dateFormat = FastDateFormat.getTimeInstance(parseStyle(timeStyle), timeZone, locale)
            }
            else { // 'both' or 'datetime'
                dateFormat = FastDateFormat.getDateTimeInstance(parseStyle(dateStyle), parseStyle(timeStyle), timeZone, locale)
            }
        }

        //if (type == 'DATE') {
        //    dateFormat = FastDateFormat.getInstance('MM-dd')
        //}
        //else if (type == 'TIME') {
        //    dateFormat = FastDateFormat.getInstance('MM-dd')
        //}
        //else { // 'both' or 'datetime'
        //    dateFormat = FastDateFormat.getInstance('MM-dd')
        //}

        return dateFormat.format(date)
    }// fine del metodo


    def resolveLocale(localeAttr) {
        def locale = localeAttr
        if (locale != null && !(locale instanceof Locale)) {
            locale = StringUtils.parseLocaleString(locale as String)
        }
        if (locale == null) {
            locale = RCU.getLocale(request)
            if (locale == null) {
                locale = Locale.getDefault()
            }
        }
        return locale
    }


    String messageHelper(code, defaultMessage = null, args = null, locale = null) {
        if (locale == null) {
            locale = RCU.getLocale(request)
        }
        def messageSource = grailsAttributes.applicationContext.messageSource
        def message
        try {
            message = messageSource.getMessage(code, args == null ? null : args.toArray(), locale)
        }
        catch (NoSuchMessageException e) {
            if (defaultMessage != null) {
                if (defaultMessage instanceof Closure) {
                    message = defaultMessage()
                }
                else {
                    message = defaultMessage as String
                }
            }
        }
        return message
    }

/**
 * Restituisce il giorno della settimana.
 * <p/>
 * Giorno scritto per intero <br>
 *
 * @param giorno da elaborare
 *
 * @return giorno della settimana
 */
    private String getGiorno(Date giorno) {
        /* variabili e costanti locali di lavoro */
        String settimana = ""
        int pos
        GregorianCalendar cal = new GregorianCalendar()

        try { // prova ad eseguire il codice
            if (giorno) {
                cal.setTime(giorno);
                pos = cal.get(Calendar.DAY_OF_WEEK);

                // la settimana inglese comincia da domenica
                // quella italiana da lunedi

                pos--;

                // shift della domenica
                if (pos == 0) {
                    pos = 7;
                }// fine del blocco if

                // nel calendario i giorni della settimana cominciano da 1
                // la Enumeration comincia da zero
                pos--;

                if ((pos >= 0) && (pos <= 7)) {
                    settimana = Giorno.values()[pos].toString();
                }// fine del blocco if
            }// fine del blocco if

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return settimana;
    }

    /**
     * Restituisce il mese del giorno indicato.
     * <p/>
     *
     * @param giorno da elaborare
     *
     * @return mese ed anno
     */
    private String getMeseCorrente(Date giorno) {
        /* variabili e costanti locali di lavoro */
        String mese = ''
        int pos
        int posGiorno
        int anno
        GregorianCalendar cal = new GregorianCalendar()

        try { // prova ad eseguire il codice
            if (giorno) {
                cal.setTime(giorno)
                pos = cal.get(Calendar.MONTH)
                posGiorno = cal.get(Calendar.DAY_OF_MONTH)
                anno = cal.get(Calendar.YEAR)

                if (posGiorno < 24) {
                    mese = Mese.values()[pos].toString()
                } else {
                    mese = MeseBreve.values()[pos].toString()
                    mese += '/'
                    if (pos == 11) {
                        pos = -1
                    }// fine del blocco if

                    mese += MeseBreve.values()[pos + 1].toString()
                }// fine del blocco if-else

                mese += ' ' + anno
            }// fine del blocco if

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return mese
    }

    /**
     * Crea una data di lunedi.
     * <p/>
     *
     * @param giorno il giorno del mese (1 per il primo)
     * @param mese il mese dell'anno (1 per gennaio)
     * @param anno l'anno
     *
     * @return la data creata
     */
    static Timestamp creaDataLunedi() {
        /* variabili e costanti locali di lavoro */
        Date giorno = new Date()
        Calendar cal

        try { // prova ad eseguire il codice
            cal = Calendar.getInstance()
            cal.setTime(giorno)

            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)

            giorno = new java.util.Date(cal.getTime().getTime());

            while (cal.get(Calendar.DAY_OF_WEEK) != 2) {
                giorno = giorno - 1
                cal.setTime(giorno)
            }// fine del blocco while

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return giorno.toTimestamp()
    }// fine del metodo


    def timePicker = { attrs ->
        def out = out
        def xdefault = attrs['value']
        if (xdefault == null) {
            xdefault = new Date()
        }
        else if (xdefault.toString() != 'none') {
            if (xdefault instanceof String) {
                xdefault = DateFormat.getInstance().parse(xdefault)
            }
            else if (!(xdefault instanceof Date)) {
                throwTagError("Tag [datePicker] requires the default date to be a parseable String or a Date")
            }
        }
        else {
            xdefault = null
        }

        def value = attrs.value
        if (value.toString() == 'none') {
            value = null
        }
        else if (!value) {
            value = xdefault
        }
        def name = attrs.name
        def id = attrs.id ?: name

        def noSelection = attrs.noSelection
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }

        def years = attrs.years

        final PRECISION_RANKINGS = ["year": 0, "month": 10, "day": 20, "hour": 30, "minute": 40]
        def precision = (attrs.precision ? PRECISION_RANKINGS[attrs.precision] :
            (grailsApplication.config.grails.tags.datePicker.default.precision ?
                PRECISION_RANKINGS["${grailsApplication.config.grails.tags.datePicker.default.precision}"] :
                PRECISION_RANKINGS["minute"]))

        def day
        def month
        def year
        def hour
        def minute
        def dfs
        dfs = new DateFormatSymbols(RCU.getLocale(request))

        def c = null
        if (value instanceof Calendar) {
            c = value
        }
        else if (value != null) {
            c = new GregorianCalendar()
            c.setTime(value)
        }

        if (c != null) {
            day = c.get(GregorianCalendar.DAY_OF_MONTH)
            month = c.get(GregorianCalendar.MONTH)
            year = c.get(GregorianCalendar.YEAR)
            hour = c.get(GregorianCalendar.HOUR_OF_DAY)
            minute = c.get(GregorianCalendar.MINUTE)
        }

        def tempyear
        if (years == null) {
            if (year == null) {
                // If no year, we need to get current year to setup a default range... ugly
                def tempc = new GregorianCalendar()
                tempc.setTime(new Date())
                tempyear = tempc.get(GregorianCalendar.YEAR)
            }
            else {
                tempyear = year
            }
            years = (tempyear - 100)..(tempyear + 100)
        }

        out.println "<input type=\"hidden\" name=\"${name}_year\" value=\"${year}\" id=\"${id}_year\">"
        out.println "<input type=\"hidden\" name=\"${name}_month\" value=\"${month}\" id=\"${id}_month\">"
        out.println "<input type=\"hidden\" name=\"${name}_day\" value=\"${day}\" id=\"${id}_day\">"

        out.println "<input type=\"hidden\" name=\"${name}\" value=\"date.struct\" />"
        out.println "<select name=\"${name}_hour\" id=\"${id}_hour\">"
        for (i in 0..23) {
            def h
            if (i < 10) {
                h = '0' + i
            } else {
                h = '' + i
            }// fine del blocco if-else
            out.println "<option value=\"${h}\"${i == hour ? ' selected="selected"' : ''}>$h</option>"
        }
        out.println '</select>'

        out.println "<input type=\"hidden\" name=\"${name}\" value=\"date.struct\" />"
        out.println "<select name=\"${name}_minute\" id=\"${id}_minute\">"
        for (i in [0, 15, 30, 45]) {
            def h
            if (i < 10) {
                h = '0' + i
            } else {
                h = '' + i
            }// fine del blocco if-else
            out.println "<option value=\"${h}\"${i == minute ? ' selected="selected"' : ''}>$h</option>"
        }
        out.println '</select>'
    }// fine del metodo


    def timePickerFisso = { attrs ->
        def out = out
        def xdefault = attrs['value']
        if (xdefault == null) {
            xdefault = new Date()
        }
        else if (xdefault.toString() != 'none') {
            if (xdefault instanceof String) {
                xdefault = DateFormat.getInstance().parse(xdefault)
            }
            else if (!(xdefault instanceof Date)) {
                throwTagError("Tag [datePicker] requires the default date to be a parseable String or a Date")
            }
        }
        else {
            xdefault = null
        }

        def value = attrs.value
        if (value.toString() == 'none') {
            value = null
        }
        else if (!value) {
            value = xdefault
        }
        def name = attrs.name
        def id = attrs.id ?: name

        def noSelection = attrs.noSelection
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }

        def years = attrs.years

        final PRECISION_RANKINGS = ["year": 0, "month": 10, "day": 20, "hour": 30, "minute": 40]
        def precision = (attrs.precision ? PRECISION_RANKINGS[attrs.precision] :
            (grailsApplication.config.grails.tags.datePicker.default.precision ?
                PRECISION_RANKINGS["${grailsApplication.config.grails.tags.datePicker.default.precision}"] :
                PRECISION_RANKINGS["minute"]))

        def day
        def month
        def year
        def hour
        def minute
        def dfs
        dfs = new DateFormatSymbols(RCU.getLocale(request))

        def c = null
        if (value instanceof Calendar) {
            c = value
        }
        else if (value != null) {
            c = new GregorianCalendar()
            c.setTime(value)
        }

        if (c != null) {
            day = c.get(GregorianCalendar.DAY_OF_MONTH)
            month = c.get(GregorianCalendar.MONTH)
            year = c.get(GregorianCalendar.YEAR)
            hour = c.get(GregorianCalendar.HOUR_OF_DAY)
            minute = c.get(GregorianCalendar.MINUTE)
        }

        def tempyear
        if (years == null) {
            if (year == null) {
                // If no year, we need to get current year to setup a default range... ugly
                def tempc = new GregorianCalendar()
                tempc.setTime(new Date())
                tempyear = tempc.get(GregorianCalendar.YEAR)
            }
            else {
                tempyear = year
            }
            years = (tempyear - 100)..(tempyear + 100)
        }

        if (hour < 10) {
            hour = '0' + hour
        }// fine del blocco if

        if (minute.equals(0)) {
            minute = '00'
        }// fine del blocco if

        out.println "<input type=\"hidden\" name=\"${name}_year\" value=\"${year}\" id=\"${id}_year\">"
        out.println "<input type=\"hidden\" name=\"${name}_month\" value=\"${month}\" id=\"${id}_month\">"
        out.println "<input type=\"hidden\" name=\"${name}_day\" value=\"${day}\" id=\"${id}_day\">"

        out.println "<input type=\"hidden\" name=\"${name}\" value=\"date.struct\" />"
        out.println "<select name=\"${name}_hour\" id=\"${id}_hour\">"
        out.println "<option value=\"${hour}\" selected=\"selected\" >${hour}</option>"
        out.println '</select>'

        out.println "<input type=\"hidden\" name=\"${name}\" value=\"date.struct\" />"
        out.println "<select name=\"${name}_minute\" id=\"${id}_minute\">"
        out.println "<option value=\"${minute}\" selected=\"selected\" >${minute}</option>"
        out.println '</select>'
    }// fine del metodo

    def algosDatePicker = { attrs ->
        def out = out
        def xdefault = attrs['value']
        if (xdefault == null) {
            xdefault = new Date()
        }
        else if (xdefault.toString() != 'none') {
            if (xdefault instanceof String) {
                xdefault = DateFormat.getInstance().parse(xdefault)
            }
            else if (!(xdefault instanceof Date)) {
                throwTagError("Tag [datePicker] requires the default date to be a parseable String or a Date")
            }
        }
        else {
            xdefault = null
        }

        def value = attrs.value
        if (value.toString() == 'none') {
            value = null
        }
        else if (!value) {
            value = xdefault
        }
        def name = attrs.name
        def id = attrs.id ?: name

        def noSelection = attrs.noSelection
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }

        def years = attrs.years

        final PRECISION_RANKINGS = ["year": 0, "month": 10, "day": 20, "hour": 30, "minute": 40]
        def precision = (attrs.precision ? PRECISION_RANKINGS[attrs.precision] :
            (grailsApplication.config.grails.tags.datePicker.default.precision ?
                PRECISION_RANKINGS["${grailsApplication.config.grails.tags.datePicker.default.precision}"] :
                PRECISION_RANKINGS["minute"]))

        def day
        def dayWeek
        def week
        def month
        def monthName
        def year
        def hour
        def minute
        def dfs
        dfs = new DateFormatSymbols(RCU.getLocale(request))

        def c = null
        if (value instanceof Calendar) {
            c = value
        }
        else if (value != null) {
            c = new GregorianCalendar()
            c.setTime(value)
        }

        if (c != null) {
            day = c.get(GregorianCalendar.DAY_OF_MONTH)
            dayWeek = c.get(GregorianCalendar.DAY_OF_WEEK)
            week = Giorno.getGiorno(dayWeek)
            month = c.get(GregorianCalendar.MONTH)
            monthName = Mese.getMese(month)
            year = c.get(GregorianCalendar.YEAR)
            hour = c.get(GregorianCalendar.HOUR_OF_DAY)
            minute = c.get(GregorianCalendar.MINUTE)
        }

        def tempyear
        if (years == null) {
            if (year == null) {
                // If no year, we need to get current year to setup a default range... ugly
                def tempc = new GregorianCalendar()
                tempc.setTime(new Date())
                tempyear = tempc.get(GregorianCalendar.YEAR)
            }
            else {
                tempyear = year
            }
            years = (tempyear - 100)..(tempyear + 100)
        }

        out.println "<input type=\"hidden\" name=\"${name}_year\" value=\"${year}\" id=\"${id}_year\">"
//        out.println "<input type=\"hidden\" name=\"${name}_month\" value=\"${month}\" id=\"${id}_month\">"
//        out.println "<input type=\"hidden\" name=\"${name}_day\" value=\"${day}\" id=\"${id}_day\">"

        out.println "<input type=\"hidden\" name=\"${name}\" value=\"date.struct\" />"

        out.println "<select name=\"${name}_hour\" id=\"${id}_hour\">"
        for (i in 0..23) {
            def h = '' + i
            if (i < 10) h = '0' + h
            out.println "<option value=\"${h}\"${i == hour ? ' selected="selected"' : ''}>$h</option>"
        }
        out.println '</select>'

        out.println "<select name=\"${name}_minute\" id=\"${id}_minute\">"
        for (i in [0, 15, 30, 45]) {
            def h = '' + i
            if (i < 10) h = '0' + h
            out.println "<option value=\"${h}\"${i == minute ? ' selected="selected"' : ''}>$h</option>"
        }
        out.println '</select>'

        out.println ' di  '

        out.println "<select>"
//        for (i in Giorno) {
//            def h = i.toString()
//            out.println "<option value=\"${h}\"${h == week ? ' selected="selected"' : ''}>$h</option>"
//        }
        out.println "<option>${week}</option>"
        out.println '</select>'

        out.println "<select name=\"${name}_day\" id=\"${id}_day\">"
        for (i in 1..31) {
            out.println "<option value=\"${i}\"${i == day ? ' selected="selected"' : ''}>${i}</option>"
        }
        out.println '</select>'


        out.println "<select>"
        out.println "<option>${monthName}</option>"
        out.println '</select>'

//        out.println "<select name=\"${name}_month\" id=\"${id}_month\">"
//        for (i in Mese) {
//            def h = i.toString()
//            out.println "<option value=\"${h}\"${h == monthName ? ' selected="selected"' : ''}>$h</option>"
//        }
//        out.println '</select>'

//        out.println "<select name=\"${name}_year\" id=\"${id}_year\">"
//        out.println "<option value=\"${year}\" selected=\"selected\" >${year}</option>"
//        out.println '</select>'
    }// fine del metodo

    /**
     * A simple date picker that renders a date as selects.<br/>
     * e.g. &lt;g:datePicker name="myDate" value="${new Date()}" /&gt;
     *
     * @emptyTag
     *
     * @attr name REQUIRED The name of the date picker field set
     * @attr value The current value of the date picker; defaults to now if not specified
     * @attr precision The desired granularity of the date to be rendered
     * @attr noSelection A single-entry map detailing the key and value to use for the "no selection made" choice in the select box. If there is no current selection this will be shown as it is first in the list, and if submitted with this selected, the key that you provide will be submitted. Typically this will be blank.
     * @attr years A list or range of years to display, in the order specified. i.e. specify 2007..1900 for a reverse order list going back to 1900. If this attribute is not specified, a range of years from the current year - 100 to current year + 100 will be shown.
     * @attr relativeYears A range of int representing values relative to value. For example, a relativeYears of -2..7 and a value of today will render a list of 10 years starting with 2 years ago through 7 years in the future. This can be useful for things like credit card expiration dates or birthdates which should be bound relative to today.
     * @attr id the DOM element id
     * @attr disabled Makes the resulting inputs and selects to be disabled. Is treated as a Groovy Truth.
     * @attr readonly Makes the resulting inputs and selects to be made read only. Is treated as a Groovy Truth.
     */
    Closure datePicker = { attrs ->
        def out = out // let x = x ?
        def xdefault = attrs['default']
        if (xdefault == null) {
            xdefault = new Date()
        }
        else if (xdefault.toString() != 'none') {
            if (xdefault instanceof String) {
                xdefault = DateFormat.getInstance().parse(xdefault)
            }
            else if (!(xdefault instanceof Date)) {
                throwTagError("Tag [datePicker] requires the default date to be a parseable String or a Date")
            }
        }
        else {
            xdefault = null
        }
        def years = attrs.years
        def relativeYears = attrs.relativeYears
        if (years != null && relativeYears != null) {
            throwTagError 'Tag [datePicker] does not allow both the years and relativeYears attributes to be used together.'
        }

        if (relativeYears != null) {
            if (!(relativeYears instanceof IntRange)) {
                // allow for a syntax like relativeYears="[-2..5]".  The value there is a List containing an IntRage.
                if ((!(relativeYears instanceof List)) || (relativeYears.size() != 1) || (!(relativeYears[0] instanceof IntRange))) {
                    throwTagError 'The [datePicker] relativeYears attribute must be a range of int.'
                }
                relativeYears = relativeYears[0]
            }
        }
        def value = attrs.value
        if (value.toString() == 'none') {
            value = null
        }
        else if (!value) {
            value = xdefault
        }
        def name = attrs.name
        def id = attrs.id ?: name

        def noSelection = attrs.noSelection
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }

        final PRECISION_RANKINGS = ["year": 0, "month": 10, "day": 20, "hour": 30, "minute": 40]
        def precision = (attrs.precision ? PRECISION_RANKINGS[attrs.precision] :
            (grailsApplication.config.grails.tags.datePicker.default.precision ?
                PRECISION_RANKINGS["${grailsApplication.config.grails.tags.datePicker.default.precision}"] :
                PRECISION_RANKINGS["minute"]))

        def day
        def month
        def year
        def hour
        def minute
        def dfs = new DateFormatSymbols(RCU.getLocale(request))
        def dayWeek
        def week

        def c = null
        if (value instanceof Calendar) {
            c = value
        }
        else if (value != null) {
            c = new GregorianCalendar()
            c.setTime(value)
        }

        if (c != null) {
            day = c.get(GregorianCalendar.DAY_OF_MONTH)
            month = c.get(GregorianCalendar.MONTH)
            year = c.get(GregorianCalendar.YEAR)
            hour = c.get(GregorianCalendar.HOUR_OF_DAY)
            minute = c.get(GregorianCalendar.MINUTE)
            dayWeek = c.get(GregorianCalendar.DAY_OF_WEEK)
            week = Giorno.getGiorno(dayWeek)
        }

        if (years == null) {
            def tempyear
            if (year == null) {
                // If no year, we need to get current year to setup a default range... ugly
                def tempc = new GregorianCalendar()
                tempc.setTime(new Date())
                tempyear = tempc.get(GregorianCalendar.YEAR)
            }
            else {
                tempyear = year
            }
            if (relativeYears) {
                if (relativeYears.reverse) {
                    years = (tempyear + relativeYears.toInt)..(tempyear + relativeYears.fromInt)
                } else {
                    years = (tempyear + relativeYears.fromInt)..(tempyear + relativeYears.toInt)
                }
            } else {
                years = (tempyear - 100)..(tempyear + 100)
            }
        }

        booleanToAttribute(attrs, 'disabled')
        booleanToAttribute(attrs, 'readonly')

        out.println "<input type=\"hidden\" name=\"${name}_year\" value=\"${year}\" id=\"${id}_year\">"
        out.println "<input type=\"hidden\" name=\"${name}\" value=\"date.struct\" />"

        // do hour select
        if (precision >= PRECISION_RANKINGS["hour"]) {
            out.println "<select name=\"${name}_hour\" id=\"${id}_hour\""
            if (attrs.disabled) {
                out << ' disabled="disabled"'
            }
            if (attrs.readonly) {
                out << ' readonly="readonly"'
            }
            out << '>'

            if (noSelection) {
                renderNoSelectionOptionImpl(out, noSelection.key, noSelection.value, '')
                out.println()
            }

            for (i in 0..23) {
                def h = '' + i
                if (i < 10) h = '0' + h
                out.println "<option value=\"${h}\"${i == hour ? ' selected="selected"' : ''}>$h</option>"
            }
            out.println '</select> :'

            // If we're rendering the hour, but not the minutes, then display the minutes as 00 in read-only format
            if (precision < PRECISION_RANKINGS["minute"]) {
                out.println '00'
            }
        }

        // do minute select
        if (precision >= PRECISION_RANKINGS["minute"]) {
            out.println "<select name=\"${name}_minute\" id=\"${id}_minute\""
            if (attrs.disabled) {
                out << 'disabled="disabled"'
            }
            if (attrs.readonly) {
                out << 'readonly="readonly"'
            }
            out << '>'

            if (noSelection) {
                renderNoSelectionOptionImpl(out, noSelection.key, noSelection.value, '')
                out.println()
            }

            for (i in [0, 15, 30, 45]) {
                def m = '' + i
                if (i < 10) m = '0' + m
                out.println "<option value=\"${m}\"${i == minute ? ' selected="selected"' : ''}>$m</option>"
            }
            out.println '</select>'
        }

        out.println ' di  '

        out.println "<select>"
        out.println "<option>${week}</option>"
        out.println '</select>'

        // create day select
        if (precision >= PRECISION_RANKINGS["day"]) {
            out.println "<select name=\"${name}_day\" id=\"${id}_day\""
            if (attrs.disabled) {
                out << ' disabled="disabled"'
            }
            if (attrs.readonly) {
                out << ' readonly="readonly"'
            }
            out << '>'

            if (noSelection) {
                renderNoSelectionOptionImpl(out, noSelection.key, noSelection.value, '')
                out.println()
            }

            for (i in 1..31) {
                out.println "<option value=\"${i}\"${i == day ? ' selected="selected"' : ''}>${i}</option>"
            }
            out.println '</select>'
        }

        // create month select
        if (precision >= PRECISION_RANKINGS["month"]) {
            out.println "<select name=\"${name}_month\" id=\"${id}_month\""
            if (attrs.disabled) {
                out << ' disabled="disabled"'
            }
            if (attrs.readonly) {
                out << ' readonly="readonly"'
            }
            out << '>'

            if (noSelection) {
                renderNoSelectionOptionImpl(out, noSelection.key, noSelection.value, '')
                out.println()
            }

            dfs.months.eachWithIndex {m, i ->
                if (m) {
                    def monthIndex = i + 1
                    out.println "<option value=\"${monthIndex}\"${i == month ? ' selected="selected"' : ''}>$m</option>"
                }
            }
            out.println '</select>'
        }

        // create year select
//        if (precision >= PRECISION_RANKINGS["year"]) {
//            out.println "<select name=\"${name}_year\" id=\"${id}_year\""
//            if (attrs.disabled) {
//                out << ' disabled="disabled"'
//            }
//            if (attrs.readonly) {
//                out << ' readonly="readonly"'
//            }
//            out << '>'
//
//            if (noSelection) {
//                renderNoSelectionOptionImpl(out, noSelection.key, noSelection.value, '')
//                out.println()
//            }
//
//            for (i in years) {
//                out.println "<option value=\"${i}\"${i == year ? ' selected="selected"' : ''}>${i}</option>"
//            }
//            out.println '</select>'
//        }

    }

    /**
     * Classe interna Enumerazione.
     */
    public enum Giorno {

        lunedì(),
        martedì(),
        mercoledì(),
        giovedì(),
        venerdì(),
        sabato(),
        domenica()

        public static String getGiorno(ord) {
            String nome = ''
            def lista = Giorno.values()

            ord = ord - 2
            if (ord < 0) {
                ord = 6
            }// fine del blocco if

            lista?.each {
                if (it.ordinal() == ord) {
                    nome = it.toString()
                }// fine del blocco if
            }

            return nome
        }
    }// fine della classe

    /**
     * Classe interna Enumerazione.
     */
    public enum GiornoBreve {

        lun(),
        mar(),
        mer(),
        gio(),
        ven(),
        sab(),
        dom()

        public static String getGiorno(ord) {
            String nome = ''
            def lista = GiornoBreve.values()

            lista?.each {
                if (it.ordinal() == ord) {
                    nome = it.toString()
                }// fine del blocco if
            }

            return nome
        }
    }// fine della classe

    /**
     * Classe interna Enumerazione.
     */
    public enum MeseBreve {

        gen(),
        feb(),
        mar(),
        apr(),
        mag(),
        giu(),
        lug(),
        ago(),
        set(),
        ott(),
        nov(),
        dic()

    }// fine della classe

    /**
     * Classe interna Enumerazione.
     */
    public enum Mese {

        gennaio(),
        febbraio(),
        marzo(),
        aprile(),
        maggio(),
        giugno(),
        luglio(),
        agosto(),
        settembre(),
        ottobre(),
        novembre(),
        dicembre()

        public static String getMese(ord) {
            String nome = ''
            def lista = Mese.values()

            lista?.each {
                if (it.ordinal() == ord) {
                    nome = it.toString()
                }// fine del blocco if
            }

            return nome
        }

    }// fine della classe

    /**
     * Some attributes can be defined as Boolean values, but the html specification
     * mandates the attribute must have the same value as its name. For example,
     * disabled, readonly and checked.
     */
    private void booleanToAttribute(def attrs, String attrName) {
        def attrValue = attrs.remove(attrName)
        // If the value is the same as the name or if it is a boolean value,
        // reintroduce the attribute to the map according to the w3c rules, so it is output later
        if (Boolean.valueOf(attrValue) ||
                (attrValue instanceof String && attrValue?.equalsIgnoreCase(attrName))) {
            attrs.put(attrName, attrName)
        } else if (attrValue instanceof String && !attrValue?.equalsIgnoreCase('false')) {
            // If the value is not the string 'false', then we should just pass it on to
            // keep compatibility with existing code
            attrs.put(attrName, attrValue)
        }
    }

    def renderNoSelectionOptionImpl(out, noSelectionKey, noSelectionValue, value) {
        // If a label for the '--Please choose--' first item is supplied, write it out
        out << "<option value=\"${(noSelectionKey == null ? '' : noSelectionKey)}\"${noSelectionKey == value ? ' selected="selected"' : ''}>${noSelectionValue.encodeAsHTML()}</option>"
    }

} // fine della classe TagLib
