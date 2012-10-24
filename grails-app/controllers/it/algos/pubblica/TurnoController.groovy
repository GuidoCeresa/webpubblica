package it.algos.pubblica

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class TurnoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static Date dataInizio
    static Date dataFine

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def turnoService
    def springSecurityService

    // usato dal plugin -navigation-
    // se manca il plugin non succede nulla
    // se non si specifica la proprietà, il modulo non appare nella lista dei menu
    static navigation = true

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def newTurno = {
        turnoService.newTurno(params)
        redirect(action: "tabCorrente")
    }// fine della closure

    /**
     * Aggiunge un milite al turno esistente <br>
     * Se loggato, inserisce il nick <br>
     * Se sloggato, presenta una lista di militi da cui scegliere <br>
     * Sarebbe bene che la lista fosse filtrata per mostrare solo i militi con la qualifica richiesta <br>
     *
     * @param mappa parametri
     *        id:    stringa per recuperare il (long) del record Turno
     *        qualifica:  stringa per recuperare la qualifica (autista/secondo/terzo) da riempire
     */
    def fillTurno = {
        boolean loggato = false
        Turno turno = Turno.get(params.id)
        TipoTurno tipoTurno = turno.tipoTurno

        if (loggato) {
            redirect(action: "fillTurnoLoggato", params: params)
        } else {
            redirect(action: "editPub", params: params)

//            switch (tipoTurno.sigla) {
//                case Turni.ordMat.sigla:
//                case Turni.ordPom.sigla:
//                    redirect(action: "editOrdinario", params: params)
//                    break
//                case Turni.s118Mat.sigla:
//                case Turni.s118Pom.sigla:
//                case Turni.s118Sera.sigla:
//                    redirect(action: "edit118", params: params)
//                    break
//                case Turni.avis.sigla:
//                    redirect(action: "editAvis", params: params)
//                    break
//                case Turni.centralino.sigla:
//                    redirect(action: "editCentralino", params: params)
//                    break
//                case Turni.extra.sigla:
//                    redirect(action: "editExtra", params: params)
//                    break
//                default: // caso non definito
//                    break
//            } // fine del blocco switch

//            if (tipoTurno.sigla.equals(Turni.extra.sigla)) {
//                redirect(action: "editExtra", params: params)
//            } else {
//                if (tipoTurno.sigla.equals(Turni.s118Mat.sigla) || tipoTurno.sigla.equals(Turni.s118Pom.sigla) || tipoTurno.sigla.equals(Turni.s118Sera.sigla)) {
//                    redirect(action: "edit118", params: params)
//                } else {
//                    redirect(action: "editOrdinario", params: params)
//                }// fine del blocco if-else
//            }// fine del blocco if-else
        }// fine del blocco if-else

        //   redirect(action: "tabellone")
    }// fine della closure

    def fillTurnoLoggato = {
        Milite milite

        milite = Milite.findById(2) //@todo provvisorio
        params.milite = milite
        turnoService.fillTurno(params)
    }// fine della closure

    @Secured(['ROLE_USER'])
    def editPub = {
        int numAutisti
        int numSecondi
        int numTerzi
        int numAltriAutisti
        int numAltriSecondi
        int numAltriTerzi
        def autisti = null
        def secondi = null
        def terzi = null

        // edit unificato
        Turno turno = Turno.get(params.id)
        TipoTurno tipoTurno = turno?.tipoTurno

        String giornoTxt = turno.giorno.toLocaleString()
        giornoTxt = giornoTxt.substring(0, giornoTxt.indexOf(' ')).trim()
        flash.giorno = giornoTxt
        flash.tipoTurno = tipoTurno.sigla

        switch (tipoTurno.sigla) {
            case Turni.ordMat.sigla:
            case Turni.ordPom.sigla:
                numAutisti = turnoService.getNumAutistiOrdinario()
                numSecondi = turnoService.getNumSecondiOrdinario()
                numTerzi = turnoService.getNumTerziOrdinario()
                numAltriAutisti = turnoService.getNumAltriAutistiOrdinario()
                numAltriSecondi = turnoService.getNumAltriSecondiOrdinario()
                autisti = turnoService.getAutistiOrdinario()
                secondi = turnoService.getSecondiOrdinario()
                terzi = turnoService.getTerziOrdinario()
                flash.titolo = 'Modifica turno ordinario'
                flash.inizioLabel = 'Orario di inizio turno (eventualmente modificabile)'
                flash.fineLabel = 'Orario di fine turno (eventualmente modificabile)'
                flash.listeAutisti = "Prima la lista alfabetica dei $numAutisti militi abilitati alla guida<br>Poi la lista degli altri $numAltriAutisti militi"
                flash.listeSecondi = "Prima la lista alfabetica dei $numSecondi militi abilitati come soccorritore<br>Poi la lista degli altri $numAltriSecondi militi"
                flash.listeTerzi = "Lista alfabetica di tutti i $numTerzi militi attivi"
                flash.problemiAutista = "Eventuali problemi di orario dell'autista (specificare nelle note)"
                flash.problemiSecondo = "Eventuali problemi di orario del secondo (specificare nelle note)"
                flash.problemiTerzo = "Eventuali problemi di orario del terzo (specificare nelle note)"
                break
            case Turni.s118Mat.sigla:
            case Turni.s118Pom.sigla:
            case Turni.s118Sera.sigla:
                numAutisti = turnoService.getNumAutisti118()
                numSecondi = turnoService.getNumSecondi118()
                numTerzi = turnoService.getNumTerzi118()
                numAltriAutisti = turnoService.getNumAltriAutisti118()
                numAltriSecondi = turnoService.getNumAltriSecondi118()
                numAltriTerzi = turnoService.getNumAltriTerzi118()
                autisti = turnoService.getAutisti118()
                secondi = turnoService.getSecondi118()
                terzi = turnoService.getTerzi118()
                flash.titolo = 'Modifica turno 118'
                flash.inizioLabel = 'Orario di inizio turno (eventualmente modificabile)'
                flash.fineLabel = 'Orario di fine turno (eventualmente modificabile)'
                flash.listeAutisti = "Prima la lista alfabetica dei $numAutisti militi abilitati alla guida sul 118<br>Poi la lista degli altri $numAltriAutisti militi autisti"
                flash.listeSecondi = "Prima la lista alfabetica dei $numSecondi militi abilitati come soccorritore sul 118<br>Poi la lista degli altri $numAltriSecondi militi soccorritori"
                flash.listeTerzi = "Prima la lista alfabetica dei $numTerzi militi attivi abilitati al 118<br>Poi lista degli altri $numAltriTerzi militi"
                flash.problemiAutista = "Eventuali problemi di orario dell'autista (specificare nelle note)"
                flash.problemiSecondo = "Eventuali problemi di orario del secondo (specificare nelle note)"
                flash.problemiTerzo = "Eventuali problemi di orario del terzo (specificare nelle note)"
                break
            case Turni.avis.sigla:
                autisti = turnoService.getAutistiOrdinario()
                secondi = turnoService.getSecondiOrdinario()
                flash.titolo = 'Modifica turno avis'
                flash.inizioLabel = 'Orario di inizio turno (obbligatorio)'
                flash.fineLabel = 'Orario di fine turno (obbligatorio)'
                flash.avis = 'non serve' // serve solo che ci sia il campo - lo usa come booleano
                break
            case Turni.centralino.sigla:
                autisti = turnoService.getTerziOrdinario()
                flash.titolo = 'Modifica turno centralino'
                flash.inizioLabel = 'Orario di inizio turno (obbligatorio)'
                flash.fineLabel = 'Orario di fine turno (obbligatorio)'
                break
            case Turni.extra.sigla:
                numAutisti = turnoService.getNumAutistiOrdinario()
                numSecondi = turnoService.getNumSecondiOrdinario()
                numTerzi = turnoService.getNumTerziOrdinario()
                numAltriAutisti = turnoService.getNumAltriAutistiOrdinario()
                numAltriSecondi = turnoService.getNumAltriSecondiOrdinario()
                autisti = turnoService.getAutistiOrdinario()
                secondi = turnoService.getSecondiOrdinario()
                terzi = turnoService.getTerziOrdinario()
                flash.titolo = 'Modifica turno extra'
                flash.inizioLabel = 'Orario di partenza dalla sede (necessario)'
                flash.fineLabel = 'Orario di rientro in sede (serve per le ore del milite)'
                flash.listeAutisti = "Prima la lista alfabetica dei $numAutisti militi abilitati alla guida<br>Poi la lista degli altri $numAltriAutisti militi"
                flash.listeSecondi = "Prima la lista alfabetica dei $numSecondi militi abilitati come soccorritore<br>Poi la lista degli altri $numAltriSecondi militi"
                flash.listeTerzi = "Lista alfabetica di tutti i $numTerzi militi attivi"
                flash.extra = 'non serve' // serve solo che ci sia il campo - lo usa come booleano
                break
            default: // caso non definito
                flash.titolo = 'non pervenuto'
                break
        } // fine del blocco switch

        render(view: "editPub", model: [turno: turno, autisti: autisti, secondi: secondi, terzi: terzi])
    }// fine della closure

    @Secured(['ROLE_USER'])
    def editOrdinario = {
        def turnoInstance = Turno.get(params.id)
        def titolo = 'Modifica turno ordinario'

        def autisti = Milite.findAllByAutistaOrdinario(true, [sort: 'cognome', order: 'asc'])
        def secondi = Milite.findAllBySoccorritoreOrdinario(true, [sort: 'cognome', order: 'asc'])
        def terzi = Milite.findAllByTerzoOrdinario(true, [sort: 'cognome', order: 'asc'])
        render(view: "editOrd118", model: [turnoInstance: turnoInstance, titolo: titolo, autisti: autisti, secondi: secondi, terzi: terzi])
    }// fine della closure


    @Secured(['ROLE_USER'])
    def edit118 = {
        def turnoInstance = Turno.get(params.id)
        def titolo = 'Modifica turno 118'
        def autisti = Milite.findAllByAutista118(true, [sort: 'cognome', order: 'asc'])
        def secondi = Milite.findAllBySoccorritore118(true, [sort: 'cognome', order: 'asc'])
        def terzi = Milite.findAllByTerzo118(true, [sort: 'cognome', order: 'asc'])
        render(view: "editOrd118", model: [turnoInstance: turnoInstance, titolo: titolo, autisti: autisti, secondi: secondi, terzi: terzi])
    }// fine della closure

    @Secured(['ROLE_USER'])
    def editAvis = {
        def turnoInstance = Turno.get(params.id)
        def titolo = 'Modifica turno avis'
        def autisti = Milite.findAllByAutistaOrdinario(true, [sort: 'cognome', order: 'asc'])
        def secondi = Milite.findAllBySoccorritoreOrdinario(true, [sort: 'cognome', order: 'asc'])
        render(view: "editAvis", model: [turnoInstance: turnoInstance, titolo: titolo, autisti: autisti, secondi: secondi])
    }// fine della closure

    @Secured(['ROLE_USER'])
    def editCentralino = {
        def turnoInstance = Turno.get(params.id)
        def titolo = 'Modifica turno centralino'
        def autisti = Milite.list([sort: 'cognome', order: 'asc'])
        render(view: "editCentralino", model: [turnoInstance: turnoInstance, titolo: titolo, autisti: autisti])
    }// fine della closure

    @Secured(['ROLE_USER'])
    def editExtra = {
        def turnoInstance = Turno.get(params.id)
        def titolo = 'Modifica turno extra'
        def autisti = Milite.findAllByAutistaOrdinario(true, [sort: 'cognome', order: 'asc'])
        def secondi = Milite.findAllBySoccorritoreOrdinario(true, [sort: 'cognome', order: 'asc'])
        def terzi = Milite.findAllByTerzoOrdinario(true, [sort: 'cognome', order: 'asc'])
        render(view: "editExtra", model: [turnoInstance: turnoInstance, titolo: titolo, autisti: autisti, secondi: secondi, terzi: terzi])
    }// fine della closure

    def index = {
        redirect(action: "list", params: params)
    }

    def tabellone = {
        flash.message = getNomeVisibile()

        //      servletContext.startController = ''
        dataInizio = Lib.creaData()
        dataFine = (dataInizio + 6).toTimestamp()
        render(view: "tabellone", model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    def tabCorrente = {
        flash.message = getNomeVisibile()
        render(view: "tabellone", model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    def tabellonePrima = {
        flash.message = getNomeVisibile()
        dataInizio -= 7
        dataFine -= 7
        render(view: "tabellone", model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    def tabelloneOggi = {
        flash.message = getNomeVisibile()
        dataInizio = Lib.creaData()
        dataFine = (dataInizio + 6).toTimestamp()
        render(view: "tabellone", model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    def tabelloneLunedi = {
        flash.message = getNomeVisibile()
        dataInizio = PubblicaTagLib.creaDataLunedi()
        dataFine = (dataInizio + 6).toTimestamp()
        render(view: "tabellone", model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    def tabelloneDopo = {
        flash.message = getNomeVisibile()
        dataInizio += 7
        dataFine += 7
        render(view: "tabellone", model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    def help = {
        []
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        [turnoInstanceList: Turno.list(params), turnoInstanceTotal: Turno.count()]
    }

    def create = {
        def turnoInstance = new Turno()
        turnoInstance.properties = params
        return [turnoInstance: turnoInstance]
    }

    def save = {
        def turnoInstance = new Turno(params)
        if (!turnoInstance.inizio) {
            turnoInstance.inizio = null
        }// fine del blocco if
        if (!turnoInstance.fine) {
            turnoInstance.fine = null
        }// fine del blocco if
        if (turnoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'turno.label', default: 'Turno'), turnoInstance.id])}"
            redirect(action: "show", id: turnoInstance.id)
        }
        else {
            render(view: "create", model: [turnoInstance: turnoInstance])
        }
    }

    def show = {
        def turnoInstance = Turno.get(params.id)
        if (!turnoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
            redirect(action: "list")
        }
        else {
            [turnoInstance: turnoInstance]
        }
    }

    def edit = {
        def turnoInstance = Turno.get(params.id)
        if (!turnoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [turnoInstance: turnoInstance]
        }
    }

    def update = {
        def turnoInstance = Turno.get(params.id)
        if (turnoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (turnoInstance.version > version) {

                    turnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'turno.label', default: 'Turno')] as Object[], "Another user has updated this Turno while you were editing")
                    // render(view: "edit", model: [turnoInstance: turnoInstance])
                    redirect(action: "tabellone")
                    // return
                }
            }
            turnoInstance.properties = params
            if (!turnoInstance.hasErrors() && turnoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'turno.label', default: 'Turno'), turnoInstance.id])}"
                redirect(action: "show", id: turnoInstance.id)
            }
            else {
                //  render(view: "edit", model: [turnoInstance: turnoInstance])
                redirect(action: "tabCorrente")
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
            redirect(action: "list")
        }
    }

    def updateTurno = {
        def turnoInstance = Turno.get(params.id)
        if (turnoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (turnoInstance.version > version) {

                    turnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'turno.label', default: 'Turno')] as Object[], "Another user has updated this Turno while you were editing")
                    // render(view: "edit", model: [turnoInstance: turnoInstance])
                    redirect(action: "tabCorrente")
                }
            }
            turnoInstance.properties = params
            if (!turnoInstance.hasErrors() && turnoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'turno.label', default: 'Turno'), turnoInstance.id])}"
                //turnoService.logModifiche(turno: turnoInstance, modifica: ModificaRecord.indeterminata)
                //turnoInstance.registraLogo()
                redirect(action: "tabCorrente")
            }
            else {
                //  render(view: "edit", model: [turnoInstance: turnoInstance])
                redirect(action: "tabCorrente")
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
            redirect(action: "tabCorrente")
        }
    }

    def delete = {
        def turnoInstance = Turno.get(params.id)
        if (turnoInstance) {
            try {
                turnoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
            redirect(action: "list")
        }

    }

    def deleteTurno = {
        def turnoInstance = Turno.get(params.id)
        if (turnoInstance) {
            try {
                turnoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
                redirect(action: "tabCorrente")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])}"
            redirect(action: "tabCorrente")
        }

    }

    def turniVuoti = {
        turnoService.turniVuoti()
        redirect(action: "tabellone")
    }// fine della closure

    def turniPieni = {
        turnoService.turniPieni()
        redirect(action: "tabellone")
    }// fine della closure

    private String getNomeVisibile() {
        String nomeVisibile
        def user
        GrailsUser grailsUser
        SecUser secUser
        String username = 'Tabellone dei turni liberamente consultabile - Per segnarti o modificare un turno (cliccando) devi autenticarti (login)'
        nomeVisibile = username
        String prefix = 'Sei collegato come '

        // recupera dal plugin
        user = springSecurityService.getPrincipal()

        // controlla prima del casting
        if (user && user instanceof GrailsUser) {
            grailsUser = (GrailsUser) user
            username = grailsUser.username
        }// fine del blocco if
        secUser = SecUser.findByUsername(username)
        if (secUser) {
            nomeVisibile = prefix + secUser.username
        }// fine del blocco if

        return nomeVisibile
    }// fine del metodo

} // fine della classe controller