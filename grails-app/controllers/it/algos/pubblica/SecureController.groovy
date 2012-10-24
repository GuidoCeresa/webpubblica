package it.algos.pubblica

import grails.plugins.springsecurity.Secured

class SecureController {

    @Secured(['ROLE_USER'])
    def index = {
        render 'Secure access only'
    }
}
