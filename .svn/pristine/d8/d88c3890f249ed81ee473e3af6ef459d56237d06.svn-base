<%@ page import="it.algos.pubblica.Turno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title><g:message code="pub.help.titolo" args="[entityName]"/></title>

</head>

<body>

<div class="nav">
    <span class="menuButton">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
</div>

<div class="help">

    <h1><g:message code="pub.help.titolo" args="[entityName]"/></h1>
    <br/>

    <h2>Tabellone</h2>

    <div class="help2">
        Nel tabellone vengono mostrati i turni di servizio per 7 giorni (una settimana) <br/>
        I giorni partono da lunedì, oppure da oggi <br/>
        Ci si può spostare indietro di una settimana alla volta <br/>
        Ci si può spostare avanti di una settimana alla volta <br/>
    </div>
    <br/>

    <h2>Tipi di turno</h2>

    <div class="help2">
        Nella prima colonna di sinistra sono elencati i tipi di turni previsti <br/>
        Due turni ordinari (mattina e pomeriggio) <br/>
        Tre turni 118 (mattina, pomeriggio e sera/notte) <br/>
        Un turno Avis <br/>
        Un turno (eventuale) al centralino <br/>
        Turni extra. Viene sempre mostrata una riga di turni extra (vuota), per poter inserire un ulteriore turno extra <br/>
    </div>
    <br/>

    <h2>Orari</h2>

    <div class="help2">
        Ogni tipo di turno sono segnati gli orari di inizio e fine <br/>
        Per i turni ordinari e 118, l'orario di inizio è fisso mentre è modificabile l'orario di fine turno <br/>
        Per i turni Avis, centralino ed extra sono modificabili sia l'orario di inizio che di fine servizio <br/>
        Per i turni extra, l'orario di inizio è quello previsto di partenza dalla sede. L'orario di rientro è indicativo <br/>
        Per i turni 118 sera/notte, viene specificato l'orario diverso a seconda della festività del giorno successivo <br/>
        Nei turni extra, oltre all'orario, compare la località interessata. In forma breve. Dettagli nelle note
    </div>
    <br/>

    <h2>Colore dei turni</h2>

    <div class="help2">
        I turni non previsti sono bianchi <br/>
        I turni previsti ma non ancora assegnati sono colorati. Il colore cambia in funzione di quanti giorni mancano al turno <br/>
        I turni non assegnati per autista è secondo sono nocciola chiaro fino al 5° giorno, arancione fino al 3° giorno e rosso per i prossimi due <br/>
        I turni non assegnati per il terzo milite sono sempre nocciola chiaro in quanto la presenza non è obbligatoria <br/>
        I turni non assegnati per il centralino sono sempre nocciola chiaro in quanto l'effettuazione non è obbligatoria <br/>
        I turni assegnati per tutti i militi sono verdi fino al 3° giorno. Sono liberamente modificabile ed annullabili <br/>
        I turni assegnati per tutti i militi sono celesti per i prossimi due giorni. Non ci si può cancellare <br/>
        Tutti i turni di oggi e precedenti sono grigi e sono già stati effettuati <br/>
    </div>
    <br/>


    <h2>Creazione dei turni (vuoti)</h2>

    <div class="help2">
        Sono già previsti tutti i turni ordinari e 118 per l'anno 2012 <br/>
        Non sono previsti turni ordinari la domenica. Si possono aggiungere <br/>
        I turni Avis ed extra vanno aggiunti secondo l'esigenza <br/>
        I turni centralino vanno aggiunti secondo la disponibilità <br/>
        Per aggiungere un turno, cliccare col mouse in qualunque parte nel riquadro bianco corrispondente al giorno ed al turno desiderato. Riga dell'orario esclusa <br/>
        Per i turni Avis ed extra cliccare nuovamente in qualunque parte dei riquadri colorati appena apparsi, per regolare gli orari di inizio e fine<br/>
    </div>
    <br/>

    <h2>Segnarsi</h2>

    <div class="help2">
        Per segnarsi in un turno, cliccare col mouse in qualunque parte dei riquadri colorati corrispondenti al giorno ed al turno desiderato. Riga dell'orario esclusa <br/>
        Nella maschera di inserimento, selezionare per la funzione desiderata (autista, secondo, terzo) il milite dalle liste esistenti <br/>
        Le liste mostrano solo i militi abilitati per quella funzione e per quel turno. In caso di errore, contattare il comandante <br/>
        Se un milite ha problemi di orario (ritardo in arrivo o termine anticipato), cliccare il checkbox e specificare nelle note. Nel tabellone comparirà un asterisco (rosso) per evidenziare il problema ai componenti del turno precedente/successivo <br/>
        Qualunque inconveniente/problema nella registrazione od esecuzione del turno può essere riportato nelle note <br/>
        Per i turni extra nelle note va indicata l'esatta località (città, reparto, abitazione, ecc.) e l'orario di riferimento (visita, manifestazione, ecc.) <br/>
        Per tornare al tabellone, cliccare il bottone in basso (registra) od il bottone in alto "Torna al tabellone" <br/>
    </div>
    <br/>

</div>

<div class="nav">
    <span class="menuButton">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
</div>

</body>
</html>