package it.algos.pubblica

class Logo {

    /*
     * Dovrei scrivere:
     * 21-2-11 ore 7:04 Domenico ha inserito il turno Ord-mat del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha cancellato il turno Ord-mat del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha inserito un turno extra del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha cancellato un turno extra del giorno 22-2-11
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 segnando l'autista Crozza Paolo
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 cancellando l'autista Crozza Paolo
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 sostituendo il secondo Crozza Paolo con Fratti Claudia
     * 21-2-11 ore 7:04 Domenico ha modificato il turno Ord-mat del giorno 22-2-11 modificando l'orario
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 modificando la località
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 modificando le note
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 segnalando un problema di orario per l'autista
     * 21-2-11 ore 7:04 Domenico ha modificato il turno extra del giorno 22-2-11 rimuovendo un problema di orario per il secondo
     * data/ora - utente - operazioneTurno - tipoTurno - turno - giornoTurno - operazioneModifica - qualifica - milite - varie
     */

    String data             // 5-2-2011
    String ora              // 7:14
    String utente           // centralino, gac, domenicoGallarati
    String modificaRecord   // inserito/cancellato/modificato
    String tipoTurno        // ord-mat/118-mat/ord-pom/118-pom/118-ser/avis/centralino/extra
    String dataTurno        // 5-2-2011
    String modificaTurno    // segnando/cancellando/sostituendo/modificando/segnalando/rimuovendo
    String tipoMilite       // autista/secondo/terzo
    String nomeMilite       // domenicoGallarati  (segnato o cancellato o sosituito)
    String altroMilite      // pasqualeVilla (sostituito)
    String ip
    String testo

    static constraints = {
        data()
        ora()
        utente()
        modificaRecord()
        tipoTurno()
        dataTurno()
        modificaTurno()
        tipoMilite(nullable: false, blank: true)
        nomeMilite(nullable: false, blank: true)
        altroMilite(nullable: false, blank: true)
        ip(nullable: true, blank: true)
    }

    static mapping = {
        testo formula: "CONCAT(data,' alle ore ',ora,' ',utente,' ha ',modifica_record,' un turno ',tipo_turno,' del giorno ',data_turno,' ',modifica_turno,' ',tipo_milite,' ',nome_milite,' ',altro_milite)"
    }
}
