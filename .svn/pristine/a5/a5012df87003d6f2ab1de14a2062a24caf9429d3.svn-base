package it.algos.pubblica

class LogoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 30, 100)
        [logoInstanceList: Logo.list(params), logoInstanceTotal: Logo.count()]
    }

    def create = {
        def logoInstance = new Logo()
        logoInstance.properties = params
        return [logoInstance: logoInstance]
    }

    def save = {
        def logoInstance = new Logo(params)
        if (logoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'logo.label', default: 'Logo'), logoInstance.id])}"
            redirect(action: "show", id: logoInstance.id)
        }
        else {
            render(view: "create", model: [logoInstance: logoInstance])
        }
    }

    def show = {
        def logoInstance = Logo.get(params.id)
        if (!logoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])}"
            redirect(action: "list")
        }
        else {
            [logoInstance: logoInstance]
        }
    }

    def edit = {
        def logoInstance = Logo.get(params.id)
        if (!logoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [logoInstance: logoInstance]
        }
    }

    def update = {
        def logoInstance = Logo.get(params.id)
        if (logoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (logoInstance.version > version) {

                    logoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'logo.label', default: 'Logo')] as Object[], "Another user has updated this Logo while you were editing")
                    render(view: "edit", model: [logoInstance: logoInstance])
                    return
                }
            }
            logoInstance.properties = params
            if (!logoInstance.hasErrors() && logoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'logo.label', default: 'Logo'), logoInstance.id])}"
                redirect(action: "show", id: logoInstance.id)
            }
            else {
                render(view: "edit", model: [logoInstance: logoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def logoInstance = Logo.get(params.id)
        if (logoInstance) {
            try {
                logoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])}"
            redirect(action: "list")
        }
    }
}
