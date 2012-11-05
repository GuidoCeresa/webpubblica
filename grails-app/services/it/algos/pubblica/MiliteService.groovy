package it.algos.pubblica

import org.apache.commons.lang.time.FastDateFormat

class MiliteService {
    private static durataTurno = '7'
    static transactional = true

    def getMediaAttuale = {
        String testo = ''
        Date oggi = new Date()
        String mediaGiornaliera = this.getMediaTxt(oggi)
        String mediaFineMese = this.getMediaMese(oggi)
        String mese = this.getMese(oggi)

        testo += 'Turni da ' + durataTurno + ' ore.'
        testo += " Media teorica odierna:"
        testo += " <b>${mediaGiornaliera}.</b>"
        testo += " Media prevista a fine ${mese}"
        testo += " <b>${mediaFineMese}</b>"

        return testo
    }// fine del metodo

    def getMedia(Date oggi) {
        int media = this.getMediaMese(oggi) - 2
        return media
    }// fine del metodo

    def getMediaReale(Date oggi) {
        def media = null
        def quota = 24 / 365
        def prog
        int pos

        GregorianCalendar cal = new GregorianCalendar()

        try { // prova ad eseguire il codice
            if (oggi) {
                cal.setTime(oggi)
                prog = cal.get(Calendar.DAY_OF_YEAR)
                media = prog * quota
            }// fine del blocco if
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return media
    }// fine del metodo

    String getMediaTxt(Date oggi) {
        def media = getMediaReale(oggi)
        int pos

        if (oggi) {
            media += ''
            pos = media.indexOf('.')

            if (pos != -1) {
                media = media.substring(0, pos) + ',' + media.subSequence(pos + 1, pos + 2)
            }// fine del blocco if

        }// fine del blocco if

        return media
    }// fine del metodo


    def getMediaMese(Date oggi) {
        GregorianCalendar cal = new GregorianCalendar()
        int numMese = 0

        try { // prova ad eseguire il codice
            if (oggi) {
                cal.setTime(oggi)
                numMese = cal.get(Calendar.MONTH) + 1
            }// fine del blocco if
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return numMese * 2
    }// fine del metodo


    String getMese(Date oggi) {
        String mese = ''
        GregorianCalendar cal = new GregorianCalendar()
        int numMese

        try { // prova ad eseguire il codice
            if (oggi) {
                cal.setTime(oggi)
                numMese = cal.get(Calendar.MONTH)
                mese = PubblicaTagLib.Mese.values()[numMese].toString()
            }// fine del blocco if
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return mese
    }// fine del metodo

    def getDataOdierna(Date oggi) {
        def testo = ''
        def dateFormat

        dateFormat = FastDateFormat.getDateInstance(FastDateFormat.FULL)
        if (dateFormat != null) {
            testo = dateFormat.format(oggi)
        }// fine del blocco if

        return testo
    }// fine del metodo

    def listaAlfabetica = {
        def lista = new ArrayList()
        def mappa
        long idOrdMat = TipoTurno.findBySigla(Turni.ordMat.sigla).id
        long idOrdPom = TipoTurno.findBySigla(Turni.ordPom.sigla).id
        long id118Mat = TipoTurno.findBySigla(Turni.s118Mat.sigla).id
        long id118Pom = TipoTurno.findBySigla(Turni.s118Pom.sigla).id
        long id118Sera = TipoTurno.findBySigla(Turni.s118Sera.sigla).id
        long idAvis = TipoTurno.findBySigla(Turni.avis.sigla).id
        long idExtra = TipoTurno.findBySigla(Turni.extra.sigla).id
        long idMilite
        int num = 1
        def query = "from Milite as m where (m.dipendente=false and m.attivo=true) order by m.cognome asc"
        def militi = Milite.findAll(query)

        militi?.each {
            mappa = this.creaMappaMilite(it, idOrdMat, idOrdPom, id118Mat, id118Pom, id118Sera, idAvis, idExtra)
            if (mappa) {
                lista.add(mappa)
            }// fine del blocco if
        }// end of each

        lista?.each {
            it.put('num', num++)
        }

        return lista
    }// fine del metodo

    def listaDipendente = {
        def lista = new ArrayList()
        def mappa
        long idOrdMat = TipoTurno.findBySigla(Turni.ordMat.sigla).id
        long idOrdPom = TipoTurno.findBySigla(Turni.ordPom.sigla).id
        long id118Mat = TipoTurno.findBySigla(Turni.s118Mat.sigla).id
        long id118Pom = TipoTurno.findBySigla(Turni.s118Pom.sigla).id
        long id118Sera = TipoTurno.findBySigla(Turni.s118Sera.sigla).id
        long idAvis = TipoTurno.findBySigla(Turni.avis.sigla).id
        long idExtra = TipoTurno.findBySigla(Turni.extra.sigla).id
        long idMilite
        int num = 1
        def query = "from Milite as m where m.dipendente=true order by m.cognome asc"
        def militi = Milite.findAll(query)

        militi?.each {
            mappa = this.creaMappaMilite(it, idOrdMat, idOrdPom, id118Mat, id118Pom, id118Sera, idAvis, idExtra)
            if (mappa) {
                lista.add(mappa)
            }// fine del blocco if
        }// end of each

        lista?.each {
            it.put('num', num++)
        }

        return lista
    }// fine del metodo

    private HashMap creaMappaMilite(Milite milite, long idOrdMat, long idOrdPom, long id118Mat, long id118Pom, long id118Sera, long idAvis, long idExtra) {
        def mappa = null
        int numOre = this.getOreTotali(milite)
        int numTurni = numOre / 7

        if (milite && milite.attivo) {
            mappa = new HashMap()
            mappa.put('num', 0)
            mappa.put('id', milite.id)
            mappa.put('milite', milite.toString())
            mappa.put('ore', Lib.formatNum(numOre))
            mappa.put('turni', numTurni)
            mappa.put('ordinario', getTurniOrdinario(milite.id, idOrdMat, idOrdPom))
            mappa.put('118', getTurni118(milite.id, id118Mat, id118Pom, id118Sera))
            mappa.put('avis', getTurniAvis(milite.id, idAvis))
            mappa.put('extra', getTurniExtra(milite.id, idExtra))
            mappa.put('autista', getTurniAutista(milite.id))
            mappa.put('secondo', getTurniSecondo(milite.id))
            mappa.put('terzo', getTurniTerzo(milite.id))
        }// fine del blocco if

        return mappa
    }// fine del metodo

    def listaDettaglioTurni(Milite milite) {
        def lista = new ArrayList()
        int num = 1
        def mappa
        Date primo = Lib.creaData1Gennaio()
        Date oggi = Lib.creaData()
        String inizio = Lib.getDataSql(primo)
        String fine = Lib.getDataSql(oggi)
        long idMilite = milite.id


        def query = "from Turno as m where"
        query += " (autista_id=$idMilite or secondo_id=$idMilite or terzo_id=$idMilite)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        query += " order by m.giorno asc"
        def turni = Turno.findAll(query)

        turni?.each {
            mappa = this.creaMappaTurno(milite, it, num++)
            if (mappa) {
                lista.add(mappa)
            }// fine del blocco if
        }// end of each

        // aggiunge un totale settimanale
        lista = regolaListaDipendente(milite, lista)

        return lista
    }// fine del metodo

    // aggiunge un totale settimanale
    private regolaListaDipendente(Milite milite, listaIn) {
        def listaOut
        def mappa = null
        mappa = new HashMap()
        int numWeek = 0
        int numWeekOld = 0
        Turno turno
        Date giorno
        long idTurno = 0
        long idTurnoOld = 0
        String siglaSettimana
        String descrizioneSettimana
        int oreSettimanaNum = 0
        int oreTurno
        int oreTurnoOld
        String oreSettimana

        if (milite.dipendente) {
            listaOut = new ArrayList()
            listaIn?.eachWithIndex { it, idx ->
                numWeek = it.get('numWeek')
                oreTurno = it.get('oreTurno')
                if (numWeek != numWeekOld || idx == listaIn.size() - 1) { // inizia una nuova settimana
                    idTurno = it.get('idTurno')
                    mappa = new HashMap()
                    turno = Turno.get(idTurnoOld)
                    if (turno) {
                        giorno = turno.giorno
                    }// fine del blocco if

                    if (idx == listaIn.size() - 1) {
                        oreSettimanaNum += oreTurno
                    }// fine del blocco if

                    siglaSettimana = 'settimana ' + numWeek
                    siglaSettimana = Lib.setVerde(siglaSettimana)
                    descrizioneSettimana = Lib.getTestoSettimana(giorno)
                    descrizioneSettimana = Lib.setVerde(descrizioneSettimana)
                    oreSettimana = oreSettimanaNum + ''
                    oreSettimana = Lib.setVerde(oreSettimana)

                    mappa.put('num', '')
                    mappa.put('turnoGiornaliero', false)
                    mappa.put('siglaSettimana', siglaSettimana)
                    mappa.put('funzione', '')
                    mappa.put('turno', '')
                    mappa.put('oreSettimana', oreSettimana)
                    mappa.put('descrizioneSettimana', descrizioneSettimana)
                    if (idx > 0) {
                        if (idx == listaIn.size() - 1) {
                            listaOut.add(it)
                            listaOut.add(mappa)
                        } else {
                            listaOut.add(mappa)
                            listaOut.add(it)
                        }// fine del blocco if-else
                        oreSettimanaNum = 0
                    } else {
                        listaOut.add(it)
                    }// fine del blocco if-else
                    idTurnoOld = idTurno
                } else {
                    listaOut.add(it)
                }// fine del blocco if-else
                numWeekOld = numWeek
                oreTurnoOld = oreTurno
                oreSettimanaNum += oreTurnoOld
            }// fine del blocco each
        } else {
            listaOut = listaIn
        }// fine del blocco if-else

        // valore di ritorno
        return listaOut
    }// fine del metodo


    private HashMap creaMappaTurno(Milite milite, Turno turno, num) {
        def mappa = null

        if (turno) {
            mappa = new HashMap()
            mappa.put('num', num)
            mappa.put('idTurno', turno.id)
            mappa.put('turnoGiornaliero', true)
            mappa.put('data', Lib.getData(turno.giorno))
            mappa.put('numWeek', Lib.getSettimana(turno.giorno))
            mappa.put('siglaSettimana', '')
            mappa.put('funzione', turno.getFunzione(milite))
            mappa.put('turno', turno.tipoTurno)
            mappa.put('militi', turno.getCompagni(milite))
            mappa.put('descrizioneSettimana', '')
            mappa.put('oreTurno', turno.getOre(milite))
            mappa.put('oreSettimana', '')
        }// fine del blocco if

        return mappa
    }// fine del metodo

    /**
     *
     */
    def listaAlfabeticaDettaglio = {
        def lista = new ArrayList()
        def query = "from Milite as m where m.dipendente=false order by m.cognome asc"
        def militi = Milite.findAll(query)
        int num = 1
        int turniOrdAutista
        int turniOrdSecondo
        int turniOrdTerzo
        int turni118Autista
        int turni118Secondo
        int turni118Terzo
        int turniExtAutista
        int turniExtSecondo
        int turniExtTerzo
        int turniTotali
        Date primo = Lib.creaData1Gennaio()
        Date oggi = Lib.creaData()
        long idMilite

        long iniz
        long fin
        long durata
        iniz = System.currentTimeMillis()

        militi.each {
            def mappa = new HashMap()
            idMilite = it.id
            mappa.put('num', num++)
            mappa.put('id', it.id)
            mappa.put('nome', it.nome)
            mappa.put('cognome', it.cognome)
            turniOrdAutista = getTurniOrdinarioAutista(idMilite, primo, oggi)
            turniOrdSecondo = getTurniOrdinarioSecondo(idMilite, primo, oggi)
            turniOrdTerzo = getTurniOrdinarioTerzo(idMilite, primo, oggi)
            turni118Autista = getTurni118Autista(idMilite, primo, oggi)
            turni118Secondo = getTurni118Secondo(idMilite, primo, oggi)
            turni118Terzo = getTurni118Terzo(idMilite, primo, oggi)
            turniExtAutista = getTurniExtAutista(idMilite, primo, oggi)
            turniExtSecondo = getTurniExtSecondo(idMilite, primo, oggi)
            turniExtTerzo = getTurniExtTerzo(idMilite, primo, oggi)
            turniTotali = turniOrdAutista + turniOrdSecondo + turniOrdTerzo
            turniTotali += turni118Autista + turni118Secondo + turni118Terzo
            turniTotali += turniExtAutista + turniExtSecondo + turniExtTerzo
            mappa.put('turniOrdAutista', turniOrdAutista)
            mappa.put('turniOrdSecondo', turniOrdSecondo)
            mappa.put('turniOrdTerzo', turniOrdTerzo)
            mappa.put('turni118Autista', turni118Autista)
            mappa.put('turni118Secondo', turni118Secondo)
            mappa.put('turni118Terzo', turniExtTerzo)
            mappa.put('turniExtAutista', turniExtAutista)
            mappa.put('turniExtSecondo', turniExtSecondo)
            mappa.put('turniExtTerzo', turni118Terzo)
            mappa.put('turniTotali', turniTotali)

            lista.add(mappa)
        }

        fin = System.currentTimeMillis()
        durata = fin - iniz
        durata = durata / 1000

        return lista
    }// fine del metodo

    def listaPerTurni = {
        def lista = new ArrayList()
        int totale
        def listaAlfabetica = listaAlfabetica()
        def mappaTotali = new LinkedHashMap()
        def militi
        int num = 1

        listaAlfabetica.each {
            totale = it.turni
            if (mappaTotali.get(totale)) {
                def matrice = mappaTotali.get(totale)
                matrice.add(it)
            } else {
                def matrice = new ArrayList()
                matrice.add(it)
                mappaTotali.put(totale, matrice)
            }// fine del blocco if-else
        }

        def listaTotali = mappaTotali.keySet().toArray()
        Arrays.sort(listaTotali, Collections.reverseOrder())

        listaTotali.each {
            militi = new ArrayList()
            def valore = mappaTotali.get(it)
            valore.each {
                it.put('num', num++)
                lista.add(it)
            }
        }

        return lista
    }// fine del metodo

    def getTurni(long idMilite, long idTipoTurno, QualificaTurno qualifica, Date inizio, Date fine) {
        long numTurni = 0
        String query
        String funzione = ''
        String inizioTxt = Lib.getDataSql(inizio)
        String fineTxt = Lib.getDataSql(fine)
        def turni
        Turno turno
        Date giorno

        switch (qualifica) {
            case QualificaTurno.autista:
                funzione = 'autista_id'
                break
            case QualificaTurno.secondo:
                funzione = 'secondo_id'
                break
            case QualificaTurno.terzo:
                funzione = 'terzo_id'
                break
            default: // caso non definito
                break
        } // fine del blocco switch

        query = "select count(*) from Turno where "
        query += "$funzione=$idMilite"
        query += " and tipo_turno_id=$idTipoTurno"
        query += " and (giorno between $inizioTxt and $fineTxt)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Long) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    def getTurniTotali(long idMilite) {
        long numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (autista_id=$idMilite or secondo_id=$idMilite or terzo_id=$idMilite)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Long) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    public int getOreTotali(Milite milite) {
        int numOre = 0
        long id = milite.id
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "from Turno where"
        query += " (autista_id=$id or secondo_id=$id or terzo_id=$id)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.findAll(query)

        turni?.each {
            numOre += it.getOre(milite)
        }

        return numOre
    }// fine del metodo

    int getTurniOrdinario(long idMilite, long mattinaOrdinario, long pomeriggioOrdinario) {
        int numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (autista_id=$idMilite or secondo_id=$idMilite or terzo_id=$idMilite)"
        query += " and "
        query += " (tipo_turno_id=$mattinaOrdinario or tipo_turno_id=$pomeriggioOrdinario)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Integer) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo


    int getTurni118(long idMilite, long mattina118, long pomeriggio118, sera118) {
        int numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (autista_id=$idMilite or secondo_id=$idMilite or terzo_id=$idMilite)"
        query += " and "
        query += " (tipo_turno_id=$mattina118 or tipo_turno_id=$pomeriggio118 or tipo_turno_id=$sera118)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Integer) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    int getTurniAvis(long idMilite, long avis) {
        int numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (autista_id=$idMilite or secondo_id=$idMilite or terzo_id=$idMilite)"
        query += " and "
        query += " (tipo_turno_id=$avis)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Integer) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    int getTurniExtra(long idMilite, long extra) {
        int numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (autista_id=$idMilite or secondo_id=$idMilite or terzo_id=$idMilite)"
        query += " and "
        query += " (tipo_turno_id=$extra)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Integer) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    int getTurniAutista(long idMilite) {
        int numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (autista_id=$idMilite)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Integer) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    int getTurniSecondo(long idMilite) {
        int numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (secondo_id=$idMilite)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Integer) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    int getTurniTerzo(long idMilite) {
        int numTurni = 0
        def turni
        String query
        String inizio = Lib.creaData1GennaioSql()
        String fine = Lib.creaDataSql()

        query = "select count(*) from Turno where"
        query += " (terzo_id=$idMilite)"
        query += " and "
        query += " (giorno between $inizio and $fine)"
        turni = Turno.executeQuery(query)

        if (turni && turni instanceof ArrayList && turni.size() > 0) {
            numTurni = (Integer) turni[0]
        }// fine del blocco if

        return numTurni
    }// fine del metodo

    def getTurniOrdinarioAutista(long idMilite, Date inizio, Date fine) {
        def turni
        def turniMat
        def turniPom
        def idMat = TipoTurno.findBySigla(Turni.ordMat.sigla).id
        def idPom = TipoTurno.findBySigla(Turni.ordPom.sigla).id

        turniMat = getTurni(idMilite, idMat, QualificaTurno.autista, inizio, fine)
        turniPom = getTurni(idMilite, idPom, QualificaTurno.autista, inizio, fine)
        turni = turniMat + turniPom

        return turni
    }// fine del metodo

    def getTurniOrdinarioSecondo(long idMilite, Date inizio, Date fine) {
        def turni
        def turniMat
        def turniPom
        def idMat = TipoTurno.findBySigla(Turni.ordMat.sigla).id
        def idPom = TipoTurno.findBySigla(Turni.ordPom.sigla).id

        turniMat = getTurni(idMilite, idMat, QualificaTurno.secondo, inizio, fine)
        turniPom = getTurni(idMilite, idPom, QualificaTurno.secondo, inizio, fine)
        turni = turniMat + turniPom

        return turni
    }// fine del metodo

    def getTurniOrdinarioTerzo(long idMilite, Date inizio, Date fine) {
        def turni
        def turniMat
        def turniPom
        def idMat = TipoTurno.findBySigla(Turni.ordMat.sigla).id
        def idPom = TipoTurno.findBySigla(Turni.ordPom.sigla).id

        turniMat = getTurni(idMilite, idMat, QualificaTurno.terzo, inizio, fine)
        turniPom = getTurni(idMilite, idPom, QualificaTurno.terzo, inizio, fine)
        turni = turniMat + turniPom

        return turni
    }// fine del metodo

    def getTurni118Autista(long idMilite, Date inizio, Date fine) {
        def turni
        def turniMat
        def turniPom
        def turniSera
        def idMat = TipoTurno.findBySigla(Turni.s118Mat.sigla).id
        def idPom = TipoTurno.findBySigla(Turni.s118Pom.sigla).id
        def idSera = TipoTurno.findBySigla(Turni.s118Sera.sigla).id

        turniMat = getTurni(idMilite, idMat, QualificaTurno.autista, inizio, fine)
        turniPom = getTurni(idMilite, idPom, QualificaTurno.autista, inizio, fine)
        turniSera = getTurni(idMilite, idSera, QualificaTurno.autista, inizio, fine)
        turni = turniMat + turniPom + turniSera

        return turni
    }// fine del metodo

    def getTurni118Secondo(long idMilite, Date inizio, Date fine) {
        def turni
        def turniMat
        def turniPom
        def turniSera
        def idMat = TipoTurno.findBySigla(Turni.s118Mat.sigla).id
        def idPom = TipoTurno.findBySigla(Turni.s118Pom.sigla).id
        def idSera = TipoTurno.findBySigla(Turni.s118Sera.sigla).id

        turniMat = getTurni(idMilite, idMat, QualificaTurno.secondo, inizio, fine)
        turniPom = getTurni(idMilite, idPom, QualificaTurno.secondo, inizio, fine)
        turniSera = getTurni(idMilite, idSera, QualificaTurno.secondo, inizio, fine)
        turni = turniMat + turniPom + turniSera

        return turni
    }// fine del metodo

    def getTurni118Terzo(long idMilite, Date inizio, Date fine) {
        def turni
        def turniMat
        def turniPom
        def turniSera
        def idMat = TipoTurno.findBySigla(Turni.s118Mat.sigla).id
        def idPom = TipoTurno.findBySigla(Turni.s118Pom.sigla).id
        def idSera = TipoTurno.findBySigla(Turni.s118Sera.sigla).id

        turniMat = getTurni(idMilite, idMat, QualificaTurno.terzo, inizio, fine)
        turniPom = getTurni(idMilite, idPom, QualificaTurno.terzo, inizio, fine)
        turniSera = getTurni(idMilite, idSera, QualificaTurno.terzo, inizio, fine)
        turni = turniMat + turniPom + turniSera

        return turni
    }// fine del metodo

    def getTurniExtAutista(long idMilite, Date inizio, Date fine) {
        def turni
        def turniAvis
        def turniExtra
        def idAvis = TipoTurno.findBySigla(Turni.avis.sigla).id
        def idExtra = TipoTurno.findBySigla(Turni.extra.sigla).id

        turniAvis = getTurni(idMilite, idAvis, QualificaTurno.autista, inizio, fine)
        turniExtra = getTurni(idMilite, idExtra, QualificaTurno.autista, inizio, fine)
        turni = turniAvis + turniExtra

        return turni
    }// fine del metodo

    def getTurniExtSecondo(long idMilite, Date inizio, Date fine) {
        def turni
        def turniExtra
        def idExtra = TipoTurno.findBySigla(Turni.extra.sigla).id

        turniExtra = getTurni(idMilite, idExtra, QualificaTurno.secondo, inizio, fine)
        turni = turniExtra

        return turni
    }// fine del metodo

    def getTurniExtTerzo(long idMilite, Date inizio, Date fine) {
        def turni
        def turniExtra
        def idExtra = TipoTurno.findBySigla(Turni.extra.sigla).id

        turniExtra = getTurni(idMilite, idExtra, QualificaTurno.terzo, inizio, fine)
        turni = turniExtra

        return turni
    }// fine del metodo

}// fine del service
