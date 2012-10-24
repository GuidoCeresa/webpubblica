package it.algos.pubblica

class DializzatoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        [dializzatoInstanceList: Dializzato.list(params), dializzatoInstanceTotal: Dializzato.count()]
    }

    def create = {
        def dializzatoInstance = new Dializzato()
        dializzatoInstance.properties = params
        return [dializzatoInstance: dializzatoInstance]
    }

    def save = {
        def dializzatoInstance = new Dializzato(params)
        if (dializzatoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), dializzatoInstance.id])}"
            redirect(action: "show", id: dializzatoInstance.id)
        }
        else {
            render(view: "create", model: [dializzatoInstance: dializzatoInstance])
        }
    }

    def show = {
        def dializzatoInstance = Dializzato.get(params.id)
        if (!dializzatoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), params.id])}"
            redirect(action: "list")
        }
        else {
            [dializzatoInstance: dializzatoInstance]
        }
    }

    def edit = {
        def dializzatoInstance = Dializzato.get(params.id)
        if (!dializzatoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [dializzatoInstance: dializzatoInstance]
        }
    }

    def update = {
        def dializzatoInstance = Dializzato.get(params.id)
        if (dializzatoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (dializzatoInstance.version > version) {

                    dializzatoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'dializzato.label', default: 'Dializzato')] as Object[], "Another user has updated this Dializzato while you were editing")
                    render(view: "edit", model: [dializzatoInstance: dializzatoInstance])
                    return
                }
            }
            dializzatoInstance.properties = params
            if (!dializzatoInstance.hasErrors() && dializzatoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), dializzatoInstance.id])}"
                redirect(action: "show", id: dializzatoInstance.id)
            }
            else {
                render(view: "edit", model: [dializzatoInstance: dializzatoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def dializzatoInstance = Dializzato.get(params.id)
        if (dializzatoInstance) {
            try {
                dializzatoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dializzato.label', default: 'Dializzato'), params.id])}"
            redirect(action: "list")
        }
    }
}
