package it.algos.pubblica

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class Turno {
    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def turnoService
    def springSecurityService

    def Date lastUpdated //todo: GORM esegue automaticamentre il timestamping

    def TipoTurno tipoTurno
    def Date giorno
    def Date inizio
    def Date fine
    def Milite autista
    def Milite secondo
    def Milite terzo
    def int oreAutista
    def int oreSecondo
    def int oreTerzo
    def String localitàExtra
    def Macchina macchina
    def String note
    def boolean problemiAutista = false
    def boolean problemiSecondo = false
    def boolean problemiTerzo = false

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        giorno(nullable: false)
        tipoTurno(nullable: false)
        inizio(nullable: true)
        fine(nullable: true)
        autista(nullable: true)
        problemiAutista()
        oreAutista()
        secondo(nullable: true)
        problemiSecondo()
        oreSecondo()
        terzo(nullable: true)
        problemiTerzo()
        oreTerzo()
        localitàExtra(nullable: true, blank: true)
        macchina(nullable: true)
        note(widget: 'textarea', nullable: true, blank: true)
        lastUpdated(nullable: true)
    } // end of static constraints

    static mapping = {
        note type: 'text'
        lastUpdated column: 'lastUpdated' //todo: nome convenzionale usato da Grails
    }

    // valore di testo restituito per una istanza della classe
    String toString() {
        tipoTurno.sigla + ' del ' + giorno.toLocaleString()
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        /**
         * costruisce inizio e fine con:
         * giorno preso da giorno di questo record di Turno
         * ore e minuti presi da quelli del TipoTurno selezionato
         */
        if (tipoTurno) {
            if (!inizio) {
                inizio = tipoTurno.inizio
            }// fine del blocco if
            if (!fine) {
                fine = tipoTurno.fine
            }// fine del blocco if
        }// fine del blocco if

        // registra su database le modifiche effettuate
        this.registraLogo(ModificaRecord.inserimento)
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
        if (autista) {
            if (oreAutista == 0) {
                oreAutista = getDurata()
            }// fine del blocco if
        } else {
            problemiAutista = false
            oreAutista = 0
        }// fine del blocco if-else

        if (secondo) {
            if (oreSecondo == 0) {
                oreSecondo = getDurata()
            }// fine del blocco if
        } else {
            problemiSecondo = false
            oreSecondo = 0
        }// fine del blocco if-else

        if (terzo) {
            if (oreTerzo == 0) {
                oreTerzo = getDurata()
            }// fine del blocco if
        } else {
            problemiTerzo = false
            oreTerzo = 0
        }// fine del blocco if-else

        // registra su database le modifiche effettuate
        if (isDirty()) {
            this.registraLogo(ModificaRecord.modifica)
        }// fine del blocco if
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * prima di cancellare un record
     */
    def beforeDelete = {
        // registra su database le modifiche effettuate
        this.registraLogo(ModificaRecord.cancellazione)
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo che il record è stato letto dal database e
     * le proprietà dell'oggetto sono state aggiornate
     */
    def onLoad = {
    } // end of def onLoad

    private void registraLogo(ModificaRecord modificaRecord) {
        Logo logo
        Date giorno
        def user
        GrailsUser grailsUser
        SecUser secUser
        String username = 'centralino'  // valore di 'default'
        String data
        String ora
        String utente
        String modRecord
        String tipoTurnoTxt
        String dataTurno
        String modTurno = ''
        String tipoMilite = ''
        String nomeMilite = ''
        String altroMilite = ''
        def oldMilite
        String finePrecedente
        String fineAttuale
        String finePrevista
        def fineDbValue
        String inizioPrecedente
        String inizioAttuale
        def inizioDbValue
        String ip = ''

        //ip = request.getRemoteAddr()

        tipoTurnoTxt = this.tipoTurno.sigla
        dataTurno = this.getDataTesto(this.giorno)

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
        if (modificaRecord) {
            modRecord = modificaRecord.sigla
        } else {
            modRecord = ModificaRecord.indeterminata.sigla
        }// fine del blocco if-else

        if ((modificaRecord == ModificaRecord.inserimento) || (modificaRecord == ModificaRecord.cancellazione)) {
        }// fine del blocco if

        if (modificaRecord == ModificaRecord.modifica) {
            if (isDirty('autista')) {
                tipoMilite = "l'autista"
                oldMilite = getPersistentValue('autista')
                if (oldMilite == null) {
                    modTurno = ModificaTurno.segnatura.sigla
                    nomeMilite = ''
                    if (autista) {
                        nomeMilite = autista.toString()
                    }// fine del blocco if
                    altroMilite = ''
                }// fine del blocco if
                if ((oldMilite != null) && (autista != null)) {
                    modTurno = ModificaTurno.sostituzione.sigla
                    nomeMilite = oldMilite.toString()
                    altroMilite = 'con ' + autista.toString()
                }// fine del blocco if
                if (autista == null) {
                    modTurno = ModificaTurno.cancellazione.sigla
                    nomeMilite = oldMilite.toString()
                    altroMilite = ''
                }// fine del blocco if

                logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                logo.save(flush: false)
            }// fine del blocco if

            if (isDirty('secondo')) {
                tipoMilite = 'il secondo'
                oldMilite = getPersistentValue('secondo')
                if (oldMilite == null) {
                    modTurno = ModificaTurno.segnatura.sigla
                    nomeMilite = ''
                    if (secondo) {
                        nomeMilite = secondo.toString()
                    }// fine del blocco if
                    altroMilite = ''
                }// fine del blocco if
                if ((oldMilite != null) && (secondo != null)) {
                    modTurno = ModificaTurno.sostituzione.sigla
                    nomeMilite = oldMilite.toString()
                    altroMilite = 'con ' + secondo.toString()
                }// fine del blocco if
                if (secondo == null) {
                    modTurno = ModificaTurno.cancellazione.sigla
                    nomeMilite = oldMilite.toString()
                    altroMilite = ''
                }// fine del blocco if

                logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                logo.save(flush: false)
            }// fine del blocco if

            if (isDirty('terzo')) {
                tipoMilite = 'il terzo'
                oldMilite = getPersistentValue('terzo')
                if (oldMilite == null) {
                    modTurno = ModificaTurno.segnatura.sigla
                    nomeMilite = ''
                    if (terzo) {
                        nomeMilite = terzo.toString()
                    }// fine del blocco if
                    altroMilite = ''
                }// fine del blocco if
                if ((oldMilite != null) && (terzo != null)) {
                    modTurno = ModificaTurno.sostituzione.sigla
                    nomeMilite = oldMilite.toString()
                    altroMilite = 'con ' + terzo.toString()
                }// fine del blocco if
                if (terzo == null) {
                    modTurno = ModificaTurno.cancellazione.sigla
                    nomeMilite = oldMilite.toString()
                    altroMilite = ''
                }// fine del blocco if

                logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                logo.save(flush: false)
            }// fine del blocco if

            if (isDirty('problemiAutista')) {
                tipoMilite = "l'autista"
                if (problemiAutista) {
                    modTurno = ModificaTurno.segnalazione.sigla
                } else {
                    modTurno = ModificaTurno.rimozione.sigla
                }// fine del blocco if-else
                nomeMilite = ''
                if (autista) {
                    nomeMilite = autista.toString()
                }// fine del blocco if
                altroMilite = ''

                if (nomeMilite) {
                    logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                    logo.save(flush: false)
                }// fine del blocco if
            }// fine del blocco if

            if (isDirty('problemiSecondo')) {
                tipoMilite = 'il secondo'
                if (problemiSecondo) {
                    modTurno = ModificaTurno.segnalazione.sigla
                } else {
                    modTurno = ModificaTurno.rimozione.sigla
                }// fine del blocco if-else
                nomeMilite = ''
                if (secondo) {
                    nomeMilite = secondo.toString()
                }// fine del blocco if
                altroMilite = ''

                if (nomeMilite) {
                    logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                    logo.save(flush: false)
                }// fine del blocco if
            }// fine del blocco if

            if (isDirty('problemiTerzo')) {
                tipoMilite = 'il terzo'
                if (problemiTerzo) {
                    modTurno = ModificaTurno.segnalazione.sigla
                } else {
                    modTurno = ModificaTurno.rimozione.sigla
                }// fine del blocco if-else
                nomeMilite = ''
                if (terzo) {
                    nomeMilite = terzo.toString()
                }// fine del blocco if
                altroMilite = ''

                if (nomeMilite) {
                    logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                    logo.save(flush: false)
                }// fine del blocco if
            }// fine del blocco if

            if (isDirty('inizio')) {
                inizioAttuale = this.getOraTesto(inizio)
                modTurno = ModificaTurno.spostamento.sigla + " l'orario di partenza dalla sede alle " + inizioAttuale
                tipoMilite = ''
                nomeMilite = ''
                altroMilite = ''

                logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                logo.save(flush: false)
            }// fine del blocco if

            if (isDirty('fine')) {
                fineAttuale = this.getOraTesto(fine)
                finePrevista = this.getOraTesto(tipoTurno.fine)
                if (fineAttuale.equals(finePrevista)) {
                    modTurno = ModificaTurno.ripristino.sigla + " l'orario normale di fine turno"
                } else {
                    modTurno = ModificaTurno.spostamento.sigla + " l'orario di fine turno alle " + fineAttuale
                }// fine del blocco if-else
                tipoMilite = ''
                nomeMilite = ''
                altroMilite = ''

                logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                logo.save(flush: false)
            }// fine del blocco if

            if (isDirty('oreAutista')) {
                modTurno = ModificaTurno.ricalcolo.sigla
                tipoMilite = ''
                nomeMilite = ''
                if (autista) {
                    nomeMilite = autista.toString()
                }// fine del blocco if
                altroMilite = ''

                if (nomeMilite) {
                    logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                    logo.save(flush: false)
                }// fine del blocco if
            }// fine del blocco if

            if (isDirty('oreSecondo')) {
                modTurno = ModificaTurno.ricalcolo.sigla
                tipoMilite = ''
                nomeMilite = ''
                if (secondo) {
                    nomeMilite = secondo.toString()
                }// fine del blocco if
                altroMilite = ''

                if (nomeMilite) {
                    logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                    logo.save(flush: false)
                }// fine del blocco if
            }// fine del blocco if

            if (isDirty('oreTerzo')) {
                modTurno = ModificaTurno.ricalcolo.sigla
                tipoMilite = ''
                nomeMilite = ''
                if (terzo) {
                    nomeMilite = terzo.toString()
                }// fine del blocco if
                altroMilite = ''

                if (nomeMilite) {
                    logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                    logo.save(flush: false)
                }// fine del blocco if
            }// fine del blocco if

            if (isDirty('localitàExtra')) {
                tipoMilite = ''
                nomeMilite = ''
                altroMilite = ''
                if (localitàExtra == null || localitàExtra.equals('')) {
                    modTurno = ModificaTurno.cancellazione.sigla + ' la località'
                } else {
                    modTurno = ModificaTurno.modifica.sigla + ' la località in ' + localitàExtra
                }// fine del blocco if-else

                logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                logo.save(flush: false)
            }// fine del blocco if

            if (isDirty('note')) {
                tipoMilite = ''
                nomeMilite = ''
                altroMilite = ''
                if (note == null || note.equals('')) {
                    modTurno = ModificaTurno.cancellazione.sigla
                } else {
                    modTurno = ModificaTurno.modifica.sigla
                }// fine del blocco if-else
                modTurno += ' le note'

                logo = new Logo(data: data, ora: ora, utente: utente, modificaRecord: modRecord, tipoTurno: tipoTurnoTxt, dataTurno: dataTurno, modificaTurno: modTurno, tipoMilite: tipoMilite, nomeMilite: nomeMilite, altroMilite: altroMilite, ip: ip)
                logo.save(flush: false)
            }// fine del blocco if

        }// fine del blocco if

    }// fine del metodo

    private String getDataTesto(Date data) {
        String sep = '-'
        Calendar cal = Calendar.getInstance()
        int giorno
        int mese
        int anno
        cal.setTime(data)
        giorno = cal.get(GregorianCalendar.DAY_OF_MONTH)
        mese = cal.get(GregorianCalendar.MONTH) + 1
        anno = cal.get(GregorianCalendar.YEAR)

        // valore di ritorno
        return giorno + sep + mese + sep + anno
    }// fine del metodo

    private String getDataCorrenteTesto() {
        return getDataTesto(new Date())
    }// fine del metodo


    private String getOraTesto(Date data) {
        String sep = ':'
        Calendar cal = Calendar.getInstance()
        int ore
        int minuti
        String minutiTxt
        cal.setTime(data)
        ore = cal.get(GregorianCalendar.HOUR_OF_DAY)
        minuti = cal.get(GregorianCalendar.MINUTE)

        if (minuti < 10) {
            minutiTxt = '0' + minuti
        } else {
            minutiTxt = '' + minuti
        }// fine del blocco if-else

        // valore di ritorno
        return ore + sep + minutiTxt
    }// fine del metodo

    private String getOraCorrenteTesto() {
        return getOraTesto(new Date())
    }// fine del metodo

    // durata del turno in ore
    public int getDurata() {
        int durata
        long durataLong
        long inizioLong = inizio.time
        long fineLong = fine.time
        int minutiFine = Lib.getMinuti(fine)

        durataLong = fineLong - inizioLong
        durataLong = durataLong / 3600
        durataLong = durataLong / 1000

        durata = durataLong.intValue()

        if (minutiFine > 30) {
            durata++
        }// fine del blocco if

        return durata
    }

    // durata del turno del singolo milite
    public int getOre(Milite milite) {
        int ore = 0

        if (milite) {
            if (milite == autista) {
                ore = oreAutista
            }// fine del blocco if

            if (milite == secondo) {
                ore = oreSecondo
            }// fine del blocco if

            if (milite == terzo) {
                ore = oreTerzo
            }// fine del blocco if
        }// fine del blocco if

        return ore
    }
    // funzione del milite (autista, secondo, terzo)
    public String getFunzione(Milite milite) {
        String funzione = ''

        if (milite) {
            if (milite == autista) {
                funzione = 'autista'
            }// fine del blocco if
            if (milite == secondo) {
                funzione = 'secondo'
            }// fine del blocco if
            if (milite == terzo) {
                funzione = 'terzo'
            }// fine del blocco if

        }// fine del blocco if

        return funzione
    }

    // altri militi in turno
    public String getCompagni(Milite milite) {
        String compagni = ''
        String sep = ' e '

        if (milite) {
            if (milite == autista) {
                if (secondo) {
                    compagni += secondo.nomeCorrente() + sep
                }// fine del blocco if
                if (terzo) {
                    compagni += terzo.nomeCorrente() + sep
                }// fine del blocco if
            }// fine del blocco if
            if (milite == secondo) {
                if (autista) {
                    compagni += autista.nomeCorrente() + sep
                }// fine del blocco if
                if (terzo) {
                    compagni += terzo.nomeCorrente() + sep
                }// fine del blocco if
            }// fine del blocco if
            if (milite == terzo) {
                if (autista) {
                    compagni += autista.nomeCorrente() + sep
                }// fine del blocco if
                if (secondo) {
                    compagni += secondo.nomeCorrente() + sep
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        if (compagni.endsWith(sep)) {
            compagni = compagni.substring(0, compagni.length() - sep.length())
            compagni = compagni.trim()
        }// fine del blocco if

        return compagni
    }

} // end of Class