package it.algos.pubblica

class MacchinaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        [macchinaInstanceList: Macchina.list(params), macchinaInstanceTotal: Macchina.count()]
    }

    def create = {
        def macchinaInstance = new Macchina()
        macchinaInstance.properties = params
        return [macchinaInstance: macchinaInstance]
    }

    def save = {
        def macchinaInstance = new Macchina(params)
        if (macchinaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'macchina.label', default: 'Macchina'), macchinaInstance.id])}"
            redirect(action: "show", id: macchinaInstance.id)
        }
        else {
            render(view: "create", model: [macchinaInstance: macchinaInstance])
        }
    }

    def show = {
        def macchinaInstance = Macchina.get(params.id)
        if (!macchinaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'macchina.label', default: 'Macchina'), params.id])}"
            redirect(action: "list")
        }
        else {
            [macchinaInstance: macchinaInstance]
        }
    }

    def edit = {
        def macchinaInstance = Macchina.get(params.id)
        if (!macchinaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'macchina.label', default: 'Macchina'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [macchinaInstance: macchinaInstance]
        }
    }

    def update = {
        def macchinaInstance = Macchina.get(params.id)
        if (macchinaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (macchinaInstance.version > version) {

                    macchinaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'macchina.label', default: 'Macchina')] as Object[], "Another user has updated this Macchina while you were editing")
                    render(view: "edit", model: [macchinaInstance: macchinaInstance])
                    return
                }
            }
            macchinaInstance.properties = params
            if (!macchinaInstance.hasErrors() && macchinaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'macchina.label', default: 'Macchina'), macchinaInstance.id])}"
                redirect(action: "show", id: macchinaInstance.id)
            }
            else {
                render(view: "edit", model: [macchinaInstance: macchinaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'macchina.label', default: 'Macchina'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def macchinaInstance = Macchina.get(params.id)
        if (macchinaInstance) {
            try {
                macchinaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'macchina.label', default: 'Macchina'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'macchina.label', default: 'Macchina'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'macchina.label', default: 'Macchina'), params.id])}"
            redirect(action: "list")
        }
    }
}
