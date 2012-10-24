package it.algos.pubblica

class ServizioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        [servizioInstanceList: Servizio.list(params), servizioInstanceTotal: Servizio.count()]
    }

    def create = {
        def servizioInstance = new Servizio()
        servizioInstance.properties = params
        return [servizioInstance: servizioInstance]
    }

    def save = {
        def servizioInstance = new Servizio(params)
        if (servizioInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'servizio.label', default: 'Servizio'), servizioInstance.id])}"
            redirect(action: "show", id: servizioInstance.id)
        }
        else {
            render(view: "create", model: [servizioInstance: servizioInstance])
        }
    }

    def show = {
        def servizioInstance = Servizio.get(params.id)
        if (!servizioInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servizio.label', default: 'Servizio'), params.id])}"
            redirect(action: "list")
        }
        else {
            [servizioInstance: servizioInstance]
        }
    }

    def edit = {
        def servizioInstance = Servizio.get(params.id)
        if (!servizioInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servizio.label', default: 'Servizio'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [servizioInstance: servizioInstance]
        }
    }


    def update = {
        def servizioInstance = Servizio.get(params.id)
        if (servizioInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (servizioInstance.version > version) {

                    servizioInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'servizio.label', default: 'Servizio')] as Object[], "Another user has updated this Servizio while you were editing")
                    render(view: "edit", model: [servizioInstance: servizioInstance])
                    return
                }
            }
            servizioInstance.properties = params
            if (!servizioInstance.hasErrors() && servizioInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'servizio.label', default: 'Servizio'), servizioInstance.id])}"
                redirect(action: "show", id: servizioInstance.id)
            }
            else {
                render(view: "edit", model: [servizioInstance: servizioInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servizio.label', default: 'Servizio'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def servizioInstance = Servizio.get(params.id)
        if (servizioInstance) {
            try {
                servizioInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'servizio.label', default: 'Servizio'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'servizio.label', default: 'Servizio'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servizio.label', default: 'Servizio'), params.id])}"
            redirect(action: "list")
        }
    }
}
