package it.algos.pubblica

class TipoTurnoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        [tipoTurnoInstanceList: TipoTurno.list(params), tipoTurnoInstanceTotal: TipoTurno.count()]
    }

    def create = {
        def tipoTurnoInstance = new TipoTurno()
        tipoTurnoInstance.properties = params
        return [tipoTurnoInstance: tipoTurnoInstance]
    }

    def save = {
        def tipoTurnoInstance = new TipoTurno(params)
        if (tipoTurnoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), tipoTurnoInstance.id])}"
            redirect(action: "show", id: tipoTurnoInstance.id)
        }
        else {
            render(view: "create", model: [tipoTurnoInstance: tipoTurnoInstance])
        }
    }

    def show = {
        def tipoTurnoInstance = TipoTurno.get(params.id)
        if (!tipoTurnoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tipoTurnoInstance: tipoTurnoInstance]
        }
    }

    def edit = {
        def tipoTurnoInstance = TipoTurno.get(params.id)
        def titolo = 'ModificaRecord tipologia turno'
        if (!tipoTurnoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tipoTurnoInstance: tipoTurnoInstance, titolo: titolo]
        }
    }

    def update = {
        def tipoTurnoInstance = TipoTurno.get(params.id)
        if (tipoTurnoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoTurnoInstance.version > version) {

                    tipoTurnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoTurno.label', default: 'TipoTurno')] as Object[], "Another user has updated this TipoTurno while you were editing")
                    render(view: "edit", model: [tipoTurnoInstance: tipoTurnoInstance])
                    return
                }
            }
            tipoTurnoInstance.properties = params
            if (!tipoTurnoInstance.hasErrors() && tipoTurnoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), tipoTurnoInstance.id])}"
                redirect(action: "show", id: tipoTurnoInstance.id)
            }
            else {
                render(view: "edit", model: [tipoTurnoInstance: tipoTurnoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tipoTurnoInstance = TipoTurno.get(params.id)
        if (tipoTurnoInstance) {
            try {
                tipoTurnoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), params.id])}"
            redirect(action: "list")
        }
    }
}
