<%@ page import="it.algos.pubblica.Milite" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'milite.label', default: 'Milite')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
</div>

<div class="help">
    <h1>Turni effettuati dal milite ${militeInstance} dal 1° gennaio ad oggi</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <br/>

    <div class="list">
        <table class="servizi">
            <thead>
            <tr>
                <th width="10">#</th>
                <th width="140">Data</th>
                <th width="40">Ruolo</th>
                <th width="45">Turno</th>
                <th width="340">Militi</th>
                <th width="30">Ore</th>
                <th width="30">Set</th>
            </tr>
            </thead>

            <tbody>
            <g:each in="${turniList}" status="i" var="rigaTurno">
                <g:form>
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <g:hiddenField name="idTurno" value="${rigaTurno['idTurno']}"/>

                        <td>${rigaTurno['num']}</td>


                        <g:if test="${rigaTurno['turnoGiornaliero']}">
                            <td><g:link controller="turno" action="editPub"
                                        id="${rigaTurno['idTurno']}">${rigaTurno['data']}</g:link></td>
                        </g:if>
                        <g:else>
                            <td><g:link controller="turno" action="editPub"
                                        id="${rigaTurno['idTurno']}">${rigaTurno['siglaSettimana']}</g:link></td>
                        </g:else>

                        <td>${rigaTurno['funzione']}</td>

                        <td><g:link controller="turno" action="editPub"
                                    id="${rigaTurno['idTurno']}">${rigaTurno['turno']}</g:link></td>

                        <g:if test="${rigaTurno['turnoGiornaliero']}">
                            <td><g:link controller="turno" action="editPub"
                                        id="${rigaTurno['idTurno']}">${rigaTurno['militi']}</g:link></td>
                        </g:if>
                        <g:else>
                            <td><g:link controller="turno" action="editPub"
                                        id="${rigaTurno['idTurno']}">${rigaTurno['descrizioneSettimana']}</g:link></td>
                        </g:else>


                        <td><g:link controller="turno" action="editPub"
                                    id="${rigaTurno['idTurno']}">${rigaTurno['oreTurno']}</g:link></td>

                        <td><g:link controller="turno" action="editPub"
                                    id="${rigaTurno['idTurno']}">${rigaTurno['oreSettimana']}</g:link></td>

                    </tr>
                </g:form>
            </g:each>
            <tr class="servizi">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>${oreTotali}</td>
                <td>${oreTotali}</td>
            </tr>

            </tbody>
        </table>
    </div>
</div>

<div class="nav">
    <span class="menuButton">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
</div>

</body>
</html>
