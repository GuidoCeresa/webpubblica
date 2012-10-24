package it.algos.pubblica

class MiliteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    def index = {
        redirect(action: "list", params: params)
    }

    // lista in ordine alfabetico con:
    // milite
    // totTurni
    def servizi = {
        def militeList
        def dipendenteList
        def media = militeService.getMedia(new Date())
        def mediaTxt = militeService.getMediaAttuale()

        params.max = 1000
        if (params.militeList) {
            militeList = params.militeList
        } else {
            militeList = militeService.listaAlfabetica()
        }// fine del blocco if-else
        if (params.dipendenteList) {
            dipendenteList = params.dipendenteList
        } else {
            dipendenteList = militeService.listaDipendente()
        }// fine del blocco if-else

        render(view: "servizi", model: [militeList: militeList, dipendenteList: dipendenteList, mediaTxt: mediaTxt, media: media])
    }// fine della closure

    // lista in ordine alfabetico con:
    // milite
    // totTurni
    def serviziAlfabetico = {
        def militeList = militeService.listaAlfabetica()
        def dipendenteList = militeService.listaDipendente()
        def media = militeService.getMedia(new Date())
        def mediaTxt = militeService.getMediaAttuale()
        render(view: "servizi", model: [militeList: militeList, dipendenteList: dipendenteList, mediaTxt: mediaTxt, media: media])
    }// fine della closure

    // lista in ordine di turni con:
    // milite
    // totTurni
    def serviziPerTurni = {
        def militeList = militeService.listaPerTurni()
        def dipendenteList = militeService.listaDipendente()
        def media = militeService.getMedia(new Date())
        def mediaTxt = militeService.getMediaAttuale()
        render(view: "servizi", model: [militeList: militeList, dipendenteList: dipendenteList, mediaTxt: mediaTxt, media: media])
    }// fine della closure

    // lista in ordine alfabetico con:
    // milite
    // totTurni
    def serviziAlfabeticoDettaglio = {
        params.max = 1000
        def lista = militeService.listaAlfabeticaDettaglio()
        [militeList: lista]
    }// fine della closure

    // lista in ordine di turni con:
    // milite
    // totTurni
    def serviziPerTurniDettaglio = {
        params.max = 1000
        def lista = militeService.listaPerTurniDettaglio()
        [militeList: lista]
    }// fine della closure

    def turni = {
        def militeInstance = Milite.get(params.id)
        def turniList = militeService.listaDettaglioTurni(militeInstance)
        def oreTotali = militeService.getOreTotali(militeInstance)

        params.max = Math.min(params.max ? params.int('max') : 30, 100)
        [militeInstance: militeInstance, turniList: turniList, oreTotali: oreTotali]
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 30, 100)
        [militeInstanceList: Milite.list(params), militeInstanceTotal: Milite.count()]
    }

    def create = {
        def militeInstance = new Milite()
        militeInstance.properties = params
        return [militeInstance: militeInstance]
    }

    def save = {
        def militeInstance = new Milite(params)
        if (militeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'milite.label', default: 'Milite'), militeInstance.id])}"
            redirect(action: "show", id: militeInstance.id)
        }
        else {
            render(view: "create", model: [militeInstance: militeInstance])
        }
    }

    def show = {
        def militeInstance = Milite.get(params.id)
        if (!militeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), params.id])}"
            redirect(action: "list")
        }
        else {
            [militeInstance: militeInstance]
        }
    }

    def edit = {
        def militeInstance = Milite.get(params.id)
        if (!militeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [militeInstance: militeInstance]
        }
    }

    def update = {
        def militeInstance = Milite.get(params.id)
        if (militeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (militeInstance.version > version) {

                    militeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'milite.label', default: 'Milite')] as Object[], "Another user has updated this Milite while you were editing")
                    render(view: "edit", model: [militeInstance: militeInstance])
                    return
                }
            }
            militeInstance.properties = params
            if (!militeInstance.hasErrors() && militeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'milite.label', default: 'Milite'), militeInstance.id])}"
                redirect(action: "show", id: militeInstance.id)
            }
            else {
                render(view: "edit", model: [militeInstance: militeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def militeInstance = Milite.get(params.id)
        if (militeInstance) {
            try {
                militeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'milite.label', default: 'Milite'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'milite.label', default: 'Milite'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), params.id])}"
            redirect(action: "list")
        }
    }
}
