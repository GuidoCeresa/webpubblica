import it.algos.pubblica.*

class BootStrap {

    static int giorni = 10

    def dataSource

    def init = { servletContext ->
        // this.macchine()
        this.tipiTurno()
        //   this.militi()
        //   this.turniVuoti()
        //   this.turniPieni()
        this.securitySetup()
        this.securityPassword()

        //patch
        //   this.patch02()
        //   this.patch03()
        //   this.patch04()
        //   this.patch05()
        this.patch06Fissa()
        this.patch07Fissa()
        this.patch08Fissa()
        this.patch09Fissa()
        this.patch10Fissa()
        this.patch11Fissa()
        this.patch12Fissa()
        this.patch13Fissa()
        this.patch14()
        this.patch15()

        // nome dell'eventuale controller da invocare automaticamente
        // alla partenza del programma.
        // parte il metodo di default del controller.
        // se non definita visualizza un elenco dei moduli/controller visibili
        // vedi index.gsp
        servletContext.startController = "/webpubblica/turno/tabellone"
    }// fine della closure

    private void securitySetup() {
        def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save()
        def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save()

        // admin
        String adminNicName = 'CeresaGuido'
        String adminPass = 'fulvia'
        SecUser gac
        if (!SecUser.findAllByUsername(adminNicName)) {
            gac = new SecUser(username: adminNicName, password: adminPass, enabled: true)
            if (userRole && adminRole && gac) {
                gac.save(flush: true)
                SecUserSecRole.create gac, adminRole, true
                SecUserSecRole.create gac, userRole, true
            }// fine del blocco if
        }// fine del blocco if

        // centralino
        String centralinoNicName = 'centralino'
        String centralinoPass = ''
        SecUser centralino
        if (!SecUser.findAllByUsername(centralinoNicName)) {
            centralino = new SecUser(username: centralinoNicName, password: centralinoPass, enabled: true)
            if (userRole && adminRole && centralino) {
                centralino.save(flush: true)
                SecUserSecRole.create centralino, userRole, true
            }// fine del blocco if
        }// fine del blocco if

        //  assert SecUser.count() == 1
        assert SecRole.count() == 2
        //  assert SecUserSecRole.count() == 2
    }// fine della closure

    private void securityPassword() {
        def militi
        Milite milite
        String userName
        String password
        boolean esiste
        SecUser utente
        def userRole = SecRole.findByAuthority('ROLE_USER')

        // Check whether the test data already exists.
        if (Milite.count()) {
            militi = Milite.all
            militi.each {
                milite = it
                userName = milite.userName()
                password = milite.firstPassword()
                utente = SecUser.findByUsername(userName)
                esiste = (utente != null)

                if (!esiste) {
                    utente = new SecUser(username: userName, password: password, enabled: true)
                    utente.save(flush: true)
                    if (utente) {
                        SecUserSecRole.create utente, userRole, true
                    }// fine del blocco if
                }// fine del blocco if-else

                if (utente) {
                    if (!utente.password) {
                        utente.password = password
                    }// fine del blocco if
                }// fine del blocco if

            }// fine del blocco each
        }// fine del blocco if

    }// fine della closure


    private void macchine() {
        // Check whether the test data already exists.
        if (!Macchina.count()) {
            new Macchina(tipo: TipoMacchina.ambulanza, sigla: 'T1', targa: 'HJ 768 KIM', contakilometri: 12427).save(failOnError: true)
            new Macchina(tipo: TipoMacchina.ambulanza, sigla: 'T2', targa: 'pippo', contakilometri: 35000).save(failOnError: true)
            new Macchina(tipo: TipoMacchina.ambulanza, sigla: 'T4', targa: 'xyz', contakilometri: 18000).save(failOnError: true)
            new Macchina(tipo: TipoMacchina.ambulanza, sigla: 'T6', targa: 'abcd', contakilometri: 12350).save(failOnError: true)
            new Macchina(tipo: TipoMacchina.ambulanza, sigla: 'T8', targa: 'zzwwhh', contakilometri: 98500).save(failOnError: true)
            new Macchina(tipo: TipoMacchina.ambulanza, sigla: 'T9', targa: 'qwerty', contakilometri: 124350).save(failOnError: true)
            new Macchina(tipo: TipoMacchina.ambulanza, sigla: 'T10', targa: 'qwereety', contakilometri: 8200).save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo


    private void militi() {
        // Check whether the test data already exists.
        if (!Milite.count()) {
            new Milite(nome: 'Enrica', cognome: 'Albanesi', qualifica: Qualifica.infermiere, autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 7892569').save(failOnError: true)
            new Milite(nome: 'Rosa', cognome: 'Alberti', telefonoCellulare: '3291933145').save(failOnError: true)
            new Milite(nome: 'Roberto', cognome: 'Ambroggi', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 6638049').save(failOnError: true)
            new Milite(nome: 'Andrea', cognome: 'Angotti', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 3766512').save(failOnError: true)
            new Milite(nome: 'Andrea', cognome: 'Aradelli', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 7041408').save(failOnError: true)
            new Milite(nome: 'Massimo', cognome: 'Arnaldo', qualifica: Qualifica.infermiere, soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '389 8776606').save(failOnError: true)
            new Milite(nome: 'Rosella', cognome: 'Arselli', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '329 5794855').save(failOnError: true)

            new Milite(nome: 'Roberto', cognome: 'Badalotti', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '345 3010236').save(failOnError: true)
            new Milite(nome: 'Carlo', cognome: 'Baggi', qualifica: Qualifica.infermiere, autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 6925156').save(failOnError: true)
            new Milite(nome: 'Roberta', cognome: 'Baiguera', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '334 3068118').save(failOnError: true)
            new Milite(nome: 'Marco', cognome: 'Ballotta', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 5421875').save(failOnError: true)
            new Milite(nome: 'Simona', cognome: 'Ballotta', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 0453927').save(failOnError: true)
            new Milite(nome: 'Elena', cognome: 'Bargiggia', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 5419085').save(failOnError: true)
            new Milite(nome: 'Ettore', cognome: 'Barocelli', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 1382562').save(failOnError: true)
            new Milite(nome: 'Alessandro', cognome: 'Battini', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 5318065').save(failOnError: true)
            new Milite(nome: 'Elisa', cognome: 'Beccalupi').save(failOnError: true)
            new Milite(nome: 'Ernestino', cognome: 'Beccalupi', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 9281363').save(failOnError: true)
            new Milite(nome: 'Stefano', cognome: 'Benzo', autista118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '392 8440442').save(failOnError: true)
            new Milite(nome: 'Miriam', cognome: 'Beretta', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 6505254').save(failOnError: true)
            new Milite(nome: 'Nicoletta', cognome: 'Bianchi', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '340 5365166').save(failOnError: true)
            new Milite(nome: 'Caterina', cognome: 'Biggini', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 1840754').save(failOnError: true)
            new Milite(nome: 'Vinicio', cognome: 'Bisconti', telefonoCellulare: '339 7038544').save(failOnError: true)
            new Milite(nome: 'Antonio', cognome: 'Bonibaldoni', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 1053412').save(failOnError: true)
            new Milite(nome: 'Germano', cognome: 'Borelli', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '334 7775709', telefonoFisso: 'O523 771280').save(failOnError: true)
            new Milite(nome: 'Carlo', cognome: 'Borghi', autista118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '331 2280576').save(failOnError: true)
            new Milite(nome: 'Giuseppe', cognome: 'Borlenghi', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 7378397').save(failOnError: true)
            new Milite(nome: 'Franca', cognome: 'Bottazzi', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 4831474').save(failOnError: true)
            new Milite(nome: 'Wilma', cognome: 'Braga').save(failOnError: true)
            new Milite(nome: 'Claudio', cognome: 'Brizzolari', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '331 1100479').save(failOnError: true)
            new Milite(nome: 'Valentina', cognome: 'Bruno').save(failOnError: true)
            new Milite(nome: 'Ugo', cognome: 'Busatti', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 0686681').save(failOnError: true)

            new Milite(nome: 'Giuseppina', cognome: 'Caracciolo', telefonoCellulare: '338 4458942').save(failOnError: true)
            new Milite(nome: 'Camillo', cognome: 'Casaroli', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '328 9138284').save(failOnError: true)
            new Milite(nome: 'Riccarda', cognome: 'Casella', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '328 7980151').save(failOnError: true)
            new Milite(nome: 'Guido', cognome: 'Ceresa', autistaOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 9235040').save(failOnError: true)
            new Milite(nome: 'Ornella', cognome: 'Civardi', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '340 8660729').save(failOnError: true)
            new Milite(nome: 'Angelo', cognome: 'Coppeta', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoFisso: 'O523 768146').save(failOnError: true)
            new Milite(nome: 'Gaetano', cognome: 'Corleto', autista118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '328 2925310').save(failOnError: true)
            new Milite(nome: 'Valeria', cognome: 'Corni', telefonoCellulare: '338 6317807').save(failOnError: true)
            new Milite(nome: 'Andrea', cognome: 'Corradi', autista118: true, autistaOrdinario: true, telefonoCellulare: '338 8136930').save(failOnError: true)
            new Milite(nome: 'Alessandro', cognome: 'Cremona', autista118: true, terzo118: true, autistaOrdinario: true, telefonoCellulare: '349 5924938').save(failOnError: true)

            new Milite(nome: 'Giuseppe', cognome: 'Dantoni', telefonoCellulare: '338 5220697').save(failOnError: true)
            new Milite(nome: 'Alessandro', cognome: 'De Riva', telefonoCellulare: '338 7021144').save(failOnError: true)
            new Milite(nome: 'Alessandra', cognome: 'Ferrari', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '334 7896909').save(failOnError: true)
            new Milite(nome: 'Fabio', cognome: 'Ferrari', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '335 8063890').save(failOnError: true)
            new Milite(nome: 'Luigi', cognome: 'Ferraroni', autista118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 8714929').save(failOnError: true)
            new Milite(nome: 'Miriam', cognome: 'Fiorani', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 2551636').save(failOnError: true)
            new Milite(nome: 'Claudia', cognome: 'Fratti', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true).save(failOnError: true)
            new Milite(nome: 'Giuseppe', cognome: 'Fulgosi', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 3142571').save(failOnError: true)
            new Milite(nome: 'Stefano', cognome: 'Fulgosi', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 5842559').save(failOnError: true)

            new Milite(nome: 'Domenico', cognome: 'Gallarati', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 3610944').save(failOnError: true)
            new Milite(nome: 'Katiuscia', cognome: 'Gallarati', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '349 4557525').save(failOnError: true)
            new Milite(nome: 'Lorena', cognome: 'Gallarati', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '333 4910207').save(failOnError: true)
            new Milite(nome: 'Eleonora', cognome: 'Gallinari', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 9885360').save(failOnError: true)
            new Milite(nome: 'Francesco', cognome: 'Gatti', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '340 5483209').save(failOnError: true)
            new Milite(nome: 'Fabio', cognome: 'Gentili', qualifica: Qualifica.infermiere, autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '346 0809138').save(failOnError: true)
            new Milite(nome: 'Maurizio', cognome: 'Ghittoni', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '335 7700780').save(failOnError: true)
            new Milite(nome: 'Helen', cognome: 'Giagosti', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '349 8850065').save(failOnError: true)
            new Milite(nome: 'Silvano', cognome: 'Giagosti', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '340 1763112').save(failOnError: true)
            new Milite(nome: 'Piera', cognome: 'Gianesi', terzoOrdinario: true, telefonoCellulare: '347 8029668').save(failOnError: true)
            new Milite(nome: 'Carlo', cognome: 'Giuliani', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 1171055').save(failOnError: true)
            new Milite(nome: 'Valentina', cognome: 'Gongarini', terzoOrdinario: true, telefonoCellulare: '329 8615688').save(failOnError: true)
            new Milite(nome: 'Maria Teresa', cognome: 'Grasselli', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '328 7864092').save(failOnError: true)

            new Milite(nome: 'Loris', cognome: 'Huskovic', autista118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '334 1295846').save(failOnError: true)
            new Milite(nome: 'Adriana', cognome: 'Iorio', terzoOrdinario: true, telefonoCellulare: '339 4982035').save(failOnError: true)
            new Milite(nome: 'Giovanni', cognome: 'La Vecchia', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '349 5354624').save(failOnError: true)
            new Milite(nome: 'Pasquale', cognome: 'Lilla', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 3036431').save(failOnError: true)
            new Milite(nome: 'Maria Rosa', cognome: 'Livelli', telefonoCellulare: '340 7791852').save(failOnError: true)
            new Milite(nome: 'Giuseppe', cognome: 'Lucenti', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 7523909').save(failOnError: true)
            new Milite(nome: 'Manuela', cognome: 'Maffei', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 9066802').save(failOnError: true)
            new Milite(nome: 'Elena', cognome: 'Magistrali', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 2537638').save(failOnError: true)
            new Milite(nome: 'Sandro', cognome: 'Maini', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '328 1049146').save(failOnError: true)
            new Milite(nome: 'Franco', cognome: 'Marini', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '333 7624964').save(failOnError: true)
            new Milite(nome: 'Cristian', cognome: 'Marioli', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 8374404').save(failOnError: true)
            new Milite(nome: 'Antonella', cognome: 'Mazzocchi', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 3847018').save(failOnError: true)
            new Milite(nome: 'Chiara', cognome: 'Modenesi', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '334 7429891').save(failOnError: true)
            new Milite(nome: 'Simona', cognome: 'Molinelli', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 9915503').save(failOnError: true)
            new Milite(nome: 'Mauro', cognome: 'Morzone', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 5005923').save(failOnError: true)
            new Milite(nome: 'Marco', cognome: 'Mosconi', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '342 0276457').save(failOnError: true)
            new Milite(nome: 'Carlo', cognome: 'Mossotto', terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '349 6408952').save(failOnError: true)

            new Milite(nome: 'Felice', cognome: 'Petraglia', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '340 0966820').save(failOnError: true)
            new Milite(nome: 'Davide', cognome: 'Pezzenati', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 1111936').save(failOnError: true)
            new Milite(nome: 'Giuseppe', cognome: 'Pietta', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: 'xyzxyz').save(failOnError: true)
            new Milite(nome: 'Laura', cognome: 'Pisani', qualifica: Qualifica.infermiere, soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 5869483').save(failOnError: true)
            new Milite(nome: 'Marco', cognome: 'Pisani', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '3332035064').save(failOnError: true)
            new Milite(nome: 'Salvatore', cognome: 'Presta', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '349 5477238').save(failOnError: true)
            new Milite(nome: 'Morena', cognome: 'Protti', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '388 1996281').save(failOnError: true)
            new Milite(nome: 'Luca', cognome: 'Quaretti', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 3580865').save(failOnError: true)
            new Milite(nome: 'Giuseppe', cognome: 'Rocca', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 6593749').save(failOnError: true)
            new Milite(nome: 'Giorgio', cognome: 'Rossi', telefonoCellulare: '338 3131380').save(failOnError: true)
            new Milite(nome: 'Giancarlo', cognome: 'Rozza').save(failOnError: true)
            new Milite(nome: 'Giorgia', cognome: 'Ruzza', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '338 3229619').save(failOnError: true)

            new Milite(nome: 'Giovanni', cognome: 'Sabatelli', soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 2892038').save(failOnError: true)
            new Milite(nome: 'Roberto', cognome: 'Sangermani', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 5622496').save(failOnError: true)
            new Milite(nome: 'Claudia', cognome: 'Savioli', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '346 5250378').save(failOnError: true)
            new Milite(nome: 'Giacomo', cognome: 'Scotti', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '349 6108442').save(failOnError: true)
            new Milite(nome: 'Guglielmina', cognome: 'Sogni', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 2589874').save(failOnError: true)
            new Milite(nome: 'Manuela', cognome: 'Sogni', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '348 4329351').save(failOnError: true)
            new Milite(nome: 'Luciano', cognome: 'Spelta', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '335 384801').save(failOnError: true)
            new Milite(nome: 'Duc', cognome: 'Tang Minh', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoFisso: 'O523 789109').save(failOnError: true)
            new Milite(nome: 'Angela', cognome: 'Terzoni').save(failOnError: true)
            new Milite(nome: 'Alida', cognome: 'Tiengo').save(failOnError: true)
            new Milite(nome: 'Marta', cognome: 'Tiribinto', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '349 0767202').save(failOnError: true)
            new Milite(nome: 'Daniele', cognome: 'Tosca', qualifica: Qualifica.infermiere, autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '329 7391271').save(failOnError: true)
            new Milite(nome: 'Vittorio', cognome: 'Travini', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 4541411').save(failOnError: true)

            new Milite(nome: 'Massimo', cognome: 'Vaga', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 2611250').save(failOnError: true)
            new Milite(nome: 'Marisa', cognome: 'Valle', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '339 8362717').save(failOnError: true)
            new Milite(nome: 'Andrea', cognome: 'Vigevani', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '335 1986066').save(failOnError: true)
            new Milite(nome: 'Francesco Federico', cognome: 'Viola', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '377 1917648').save(failOnError: true)
            new Milite(nome: 'Luigi', cognome: 'Vitali', autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '347 2416683').save(failOnError: true)
            new Milite(nome: 'Marina', cognome: 'Vitali', soccorritore118: true, terzo118: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '345 9039661').save(failOnError: true)
            new Milite(nome: 'Antonio', cognome: 'Vercesi').save(failOnError: true)
            new Milite(nome: 'Simone', cognome: 'Zavattarelli', autista118: true, soccorritore118: true, terzo118: true, autistaOrdinario: true, soccorritoreOrdinario: true, terzoOrdinario: true, telefonoCellulare: '320 4215967').save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo


    private void tipiTurno() {
        // Check whether the test data already exists.
        if (!TipoTurno.count()) {

            for (Turni it : Turni) {
                new TipoTurno(sigla: it.sigla, descrizione: it.descrizione, ordine: it.ordinal() + 1, inizio: it.inizio, fine: it.fine, orario: it.orario, autista: it.autista, secondo: it.secondo, terzo: it.terzo).save(failOnError: true)
            } // fine del ciclo for-each

        }// fine del blocco if
    }// fine del metodo

    public void turniPieni() {
        int giorni = 10

        // Check whether the test data already exists.
        if (!Turno.count()) {
            Date data = PubblicaTagLib.creaData()
            Date dataCorrente
            int inizio = 0
            int fine = 0
            Milite autista
            Milite secondo
            Milite terzo

            //   def turni = TipoTurno.getAll()
            def turni = TipoTurno.findAll([sort: "ordine", order: "asc"])
            for (int k = 0; k < giorni; k++) {
                turni?.each {
                    dataCorrente = data + k
                    inizio = it.inizio
                    fine = it.fine
                    autista = null
                    secondo = null
                    terzo = null

                    if (it.sigla.equals(Turni.s118Sera.sigla)) {
                        if (dataCorrente.day == 5 || dataCorrente.day == 6) {
                            fine = 8
                        } else {
                            fine = 24
                        }// fine del blocco if-else
                    }// fine del blocco if

                    if (k != 1 && !it.sigla.equals(Turni.s118Mat.sigla)) {
                        autista = Milite.get(k + 2 + inizio * 2)
                        secondo = Milite.get(k + 3 + fine * 2)
                    }// fine del blocco if
                    if (k == 1 && it.sigla.equals(Turni.s118Mat.sigla)) {
                        autista = Milite.get(k + 4 + inizio * 2)
                        secondo = Milite.get(k + 5 + fine * 2)
                    }// fine del blocco if
                    if (k == 0 && it.sigla.equals(Turni.s118Mat.sigla)) {
                        autista = Milite.get(k + 20 + inizio * 2)
                        secondo = Milite.get(k + 30 + fine * 2)
                        terzo = Milite.get(68)
                    }// fine del blocco if
                    if (k == 1 && it.sigla.equals(Turni.ordMat.sigla)) {
                        autista = Milite.get(90)
                        secondo = Milite.get(65)
                        terzo = Milite.get(41)
                    }// fine del blocco if
                    if (k == 1 && it.sigla.equals(Turni.s118Pom.sigla)) {
                        autista = Milite.get(88)
                        secondo = Milite.get(79)
                    }// fine del blocco if
                    if (k == 2 && it.sigla.equals(Turni.ordPom.sigla)) {
                        terzo = Milite.get(inizio + fine + fine)
                    }// fine del blocco if
                    if (k == 3 && it.sigla.equals(Turni.s118Mat.sigla)) {
                        terzo = Milite.get(k * 2 + inizio + fine + k)
                    }// fine del blocco if
                    if (dataCorrente.day == 3 && it.sigla.equals(Turni.s118Pom.sigla)) {
                        terzo = Milite.get(k + 2 + fine)
                    }// fine del blocco if

                    //if (it.sigla.equals(Turni.avis.sigla)) {
                    //    secondo = null
                    //    terzo = null
                    //}// fine del blocco if

                    if (!it.sigla.equals(Turni.extra.sigla) && !it.sigla.equals(Turni.manifestazione.sigla)) {
                        new Turno(autista: autista, secondo: secondo, terzo: terzo, giorno: dataCorrente, tipoTurno: it, inizio: inizio, fine: fine).save(failOnError: true)
                    }// fine del blocco if
                }// fine del blocco each
            } // fine del ciclo for

            //turni extra
            TipoTurno tipoTurnoExtra = TipoTurno.findBySigla(Turni.extra.sigla)
            if (tipoTurnoExtra) {
                autista = Milite.get(56)
                secondo = Milite.get(88)
                new Turno(autista: autista, secondo: secondo, giorno: data + 1, tipoTurno: tipoTurnoExtra, localitàExtra: 'Parma', inizio: 14, fine: 17).save(failOnError: true)
                autista = null
                secondo = null
                new Turno(autista: autista, secondo: secondo, giorno: data + 3, tipoTurno: tipoTurnoExtra, localitàExtra: 'Piacenza', inizio: 10, fine: 12).save(failOnError: true)
                autista = Milite.get(91)
                secondo = Milite.get(63)
                new Turno(autista: autista, secondo: secondo, giorno: data + 4, tipoTurno: tipoTurnoExtra, localitàExtra: 'Milano', inizio: 8, fine: 15).save(failOnError: true)
            }// fine del blocco if

        }// fine del blocco if
    }// fine del metodo

    private void patch01() {
        def turni = Turno.getAll()
        long timeInizio
        long timeFine
        String txtInizio
        String txtFine

        if (false) {
            turni?.each {
                def giornoCorrente = it.giorno
                if (giornoCorrente) {
                    it.inizio = new Date(giornoCorrente.getTime())
                    it.fine = new Date(giornoCorrente.getTime())
                    it.inizio.hours = timeInizio
                    it.fine.hours = timeFine
                    it.save(flush: true)
                }// fine del blocco if
            }
        }// fine del blocco if

    }// fine della closure

    private void patch02() {
        int extra = 7
        int secondoExtra = 8
        int terzoExtra = 9
        String update
        String delete

        update = 'update TipoTurno t set t.multiplo=false'
        TipoTurno.executeUpdate(update)

        update = "update Turno set tipo_turno_id=${extra} where tipo_turno_id=${secondoExtra}"
        TipoTurno.executeUpdate(update)

        update = "update Turno set tipo_turno_id=${extra} where tipo_turno_id=${terzoExtra}"
        TipoTurno.executeUpdate(update)

        update = "update TipoTurno t set t.multiplo=true where id=${extra}"
        TipoTurno.executeUpdate(update)

        delete = "delete TipoTurno where id=${secondoExtra}"
        TipoTurno.executeUpdate(delete)

        delete = "delete TipoTurno where id=${terzoExtra}"
        TipoTurno.executeUpdate(delete)
    }// fine della closure

    private void patch03() {
        // cancella gli ordinari della domenica
        def turni = Turno.getAll()
        Date giorno
        int domenica = 0
        int giornoSettimanale
        TipoTurno tipo
        String siglaMattino = Turni.ordMat.sigla
        String siglaPomeriggio = Turni.ordPom.sigla
        String sigla
        long codice

        turni.each {
            giorno = it.giorno
            giornoSettimanale = giorno.day
            tipo = it.tipoTurno
            sigla = tipo.sigla
            if (giornoSettimanale == domenica) {
                if (sigla.equals(siglaMattino) || sigla.equals(siglaPomeriggio)) {
                    codice = it.id
                    Turno.get(codice).delete()
                }// fine del blocco if
            }// fine del blocco if
        }
    }// fine della closure

    private void patch04() {
        int cent = 8
        def delete

        for (int k = 0; k < 30; k++) {
            delete = "delete TipoTurno where id=${cent}"
            TipoTurno.executeUpdate(delete)
            cent++
        } // fine del ciclo for

        new TipoTurno(sigla: Turni.centralino.sigla, descrizione: Turni.centralino.descrizione, ordine: Turni.centralino.ordinal() + 1, inizio: Turni.centralino.inizio, fine: Turni.centralino.fine, orario: Turni.centralino.orario, autista: Turni.centralino.autista, secondo: Turni.centralino.secondo, terzo: Turni.centralino.terzo).save(failOnError: true)
        def update = "update TipoTurno t set t.ordine=7 where sigla='centralino'"
        TipoTurno.executeUpdate(update)
        update = "update TipoTurno t set t.ordine=8 where sigla='extra'"
        TipoTurno.executeUpdate(update)
    }// fine della closure

    private void patch05() {
        // controllo che ci sia il parametro 'dipendente'
        if (Milite.count()) {
            def update = "update Milite m set m.dipendente=false"
            Milite.executeUpdate(update)
        }// fine del blocco if
    }// fine della closure

    private void patch06Fissa() {
        // forza il flag (se non esisteva) senza cambiare niente
        String query
        String update
        def campi

        query = 'select attivo from Milite'
        campi = Milite.executeQuery(query)
        if (campi && campi.size() > 0 && campi[0]) {
            def stop22
        } else {
            update = "update Milite m set m.attivo=true"
            Milite.executeUpdate(update)
            //         new Milite(nome: 'Sede', cognome: 'Centralino', attivo: false, telefonoFisso: '0523 842229').save(failOnError: true)
        }// fine del blocco if-else
    }// fine della closure


    private void patch07Fissa() {
        // elimina una eventuale variabile di passaggio se erroneamente rimasta
        String database = 'Pubblica'
        String tavola = 'Turno'

        Lib.eliminaColonnaSeEsiste(database, tavola, 'inizioOld')
        Lib.eliminaColonnaSeEsiste(database, tavola, 'fineOld')

    }// fine del metodo

    private void patch08Fissa() {
        // elimina una eventuale variabile di passaggio se erroneamente rimasta
        String database = 'Pubblica'
        String tavola = 'Turno'

        Lib.eliminaColonnaSeEsiste(database, tavola, 'nickModificaAutista')
        Lib.eliminaColonnaSeEsiste(database, tavola, 'nickModificaSecondo')
        Lib.eliminaColonnaSeEsiste(database, tavola, 'nickModificaTerzo')
        Lib.eliminaColonnaSeEsiste(database, tavola, 'lastModificaAutista')
        Lib.eliminaColonnaSeEsiste(database, tavola, 'lastModificaSecondo')
        Lib.eliminaColonnaSeEsiste(database, tavola, 'lastModificaTerzo')

    }// fine del metodo

    private void patch09Fissa() {
        String query
        String update
        def campi

        query = 'select durata from TipoTurno'
        campi = TipoTurno.executeQuery(query)
        if (campi && campi.size() > 0 && campi[0]) {
        } else {
            update = "update TipoTurno t set t.durata=0"
            TipoTurno.executeUpdate(update)
        }// fine del blocco if-else
    }// fine della closure

    private void patch10Fissa() {
        // elimina una eventuale variabile di passaggio se erroneamente rimasta
        String database = 'Pubblica'
        String tavola = 'TipoTurno'

        Lib.eliminaColonnaSeEsiste(database, tavola, 'mezzo')

    }// fine del metodo

    private void patch11Fissa() {
        String query
        String update
        def campi

        query = 'select visibile from TipoTurno'
        campi = TipoTurno.executeQuery(query)
        if (campi && campi.size() > 0 && campi[0]) {
        } else {
            update = "update TipoTurno t set t.visibile=true"
            TipoTurno.executeUpdate(update)
        }// fine del blocco if-else
    }// fine della closure

    private void patch12Fissa() {
        def tipi
        long id
        int durata
        TipoTurno tipo
        String sigla

        // Check whether the test data already exists.
        if (TipoTurno.count()) {
            tipi = TipoTurno.list()

            tipi?.each {
                if (it.durata == 0) {
                    sigla = it.sigla
                    tipo = TipoTurno.get(id)
                    durata = Turni.getDurata(sigla)
                    it.durata = durata
                    it.save()
                }// fine del blocco if
            }// end del blocco each

        }// fine del blocco if
    }// fine del metodo

    private void patch13Fissa() {
        // cancella alcuni utenti/militi inseriti in una fase precedente di sviluppo:
        // gac              (user)
        // CentralinoSede   (user)
        // Centralino Sede  (milite)

        SecUser utente
        Milite milite

        utente = SecUser.findByUsername('gac')
        if (utente) {
            utente.delete(flush: true)
        }// fine del blocco if

        utente = SecUser.findByUsername('CentralinoSede')
        if (utente) {
            utente.delete(flush: true)
        }// fine del blocco if

        milite = Milite.findByCognome('Centralino')
        if (milite) {
            milite.delete(flush: true)
        }// fine del blocco if

    }// fine del metodo


    private void patch14() {
        // controlla le date inizio e fine di ogni turno
        /* variabili e costanti locali di lavoro */
        Calendar cal = Calendar.getInstance()
        def lista = Turno.getAll()
        def giorno
        def inizio
        def fine
        def dataGiorno
        def dataInizio
        def dataFine
        def oraInizio
        def minutiInizio
        def oraFine
        def minutiFine

        lista?.each {
            giorno = it.giorno
            inizio = it.inizio
            fine = it.fine

            // inizio
            oraInizio = Lib.getOre(inizio)
            minutiInizio = Lib.getMinuti(inizio)

            cal.setTime(giorno)
            cal.set(Calendar.HOUR_OF_DAY, oraInizio)
            cal.set(Calendar.MINUTE, minutiInizio)
            inizio = cal.getTime()
            it.inizio = new java.util.Date(inizio.getTime())

            // fine
            oraFine = Lib.getOre(fine)
            minutiFine = Lib.getMinuti(fine)

            if (it.tipoTurno.sigla.equals(Turni.s118Sera.sigla)) {
                giorno = giorno + 1
            }// fine del blocco if

            if (it.tipoTurno.sigla.equals(Turni.extra.sigla)) {
                if (oraFine == 0) {
                    giorno = giorno + 1
                }// fine del blocco if
                if (giorno.day == 5 || giorno.day == 6) {

//                        fine = 8
//                    } else {
//                        fine = 24
                }// fine del blocco if-else
            }// fine del blocco if

            cal.setTime(giorno)
            cal.set(Calendar.HOUR_OF_DAY, oraFine)
            cal.set(Calendar.MINUTE, minutiFine)
            fine = cal.getTime()

            it.fine = new java.util.Date(fine.getTime())

            // salva entrambi
            it.save()
        }

    }// fine del metodo

    private void patch15() {
        // controlla le ore autista, secondo e terzo di ogni turno
        // turni vuoti, mette le ore del turno
        // turni con già almeno un milite, mette solo il/i milite/i esistenti
        Calendar cal
        def lista = Turno.getAll()
        def inizio
        def fine
        def oreAutista
        def oreSecondo
        def oreTerzo
        int durata

        lista?.each {
            inizio = it.inizio
            fine = it.fine

            durata = Lib.difOre(fine, inizio)

            if (it.autista || it.secondo || it.terzo) {
                if (it.autista) {
                    it.oreAutista = durata
                } else {
                    it.oreAutista = 0
                }// fine del blocco if-else

                if (it.secondo) {
                    it.oreSecondo = durata
                } else {
                    it.oreSecondo = 0
                }// fine del blocco if-else

                if (it.terzo) {
                    it.oreTerzo = durata
                } else {
                    it.oreTerzo = 0
                }// fine del blocco if-else
            } else {
                it.oreAutista = durata
                it.oreSecondo = durata
                it.oreTerzo = durata
            }// fine del blocco if-else

            it.save()
        }

    }// fine del metodo

    def destroy = {
    }// fine della closure

}// fine della classe
