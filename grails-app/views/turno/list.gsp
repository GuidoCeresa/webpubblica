<%@ page import="it.algos.pubblica.Turno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><g:link class="create" action="create"><g:message
            code="default.new.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="tabellone" action="tabellone"><g:message
            code="default.tabellone.label" args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id"
                                  title="${message(code: 'turno.id.label', default: 'Id')}"/>

                <g:sortableColumn property="giorno"
                                  title="${message(code: 'turno.giorno.label', default: 'Giorno')}"/>

                <g:sortableColumn property="tipoTurno"
                                  title="${message(code: 'turno.tipoTurno.label', default: 'Tipo Turno')}"/>

                <g:sortableColumn property="inizio"
                                  title="${message(code: 'turno.inizio.label', default: 'Inizio')}"/>

                <g:sortableColumn property="fine"
                                  title="${message(code: 'turno.fine.label', default: 'Fine')}"/>

                <g:sortableColumn property="autista"
                                  title="${message(code: 'turno.autista.label', default: 'Autista')}"/>

                <g:sortableColumn property="secondo"
                                  title="${message(code: 'turno.secondo.label', default: 'Secondo')}"/>

                <g:sortableColumn property="terzo"
                                  title="${message(code: 'turno.terzo.label', default: 'Terzo')}"/>

                <g:sortableColumn property="problemiAutista"
                                  title="${message(code: 'turno.problemiAutista.label', default: 'Problemi Autista')}"/>

                <g:sortableColumn property="problemiSecondo"
                                  title="${message(code: 'turno.problemiSecondo.label', default: 'Problemi Secondo')}"/>

                <g:sortableColumn property="problemiTerzo"
                                  title="${message(code: 'turno.problemiTerzo.label', default: 'Problemi Terzo')}"/>

                <g:sortableColumn property="localitàExtra"
                                  title="${message(code: 'turno.localitàExtra.label', default: 'Località Extra')}"/>

                <g:sortableColumn property="macchina"
                                  title="${message(code: 'turno.macchina.label', default: 'Macchina')}"/>

                <g:sortableColumn property="note"
                                  title="${message(code: 'turno.note.label', default: 'Note')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${turnoInstanceList}" status="i" var="turnoInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "id")}</g:link></td>

                    <td><pub:formatDate date="${turnoInstance.giorno}"/></td>

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "tipoTurno")}</g:link></td>

                    <td><pub:formatDate date="${turnoInstance.inizio}"/></td>
                    <td><pub:formatDate date="${turnoInstance.fine}"/></td>

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "autista")}</g:link></td>

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "secondo")}</g:link></td>

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "terzo")}</g:link></td>

                    <td><g:formatBoolean boolean="${turnoInstance.problemiAutista}"/></td>

                    <td><g:formatBoolean boolean="${turnoInstance.problemiSecondo}"/></td>

                    <td><g:formatBoolean boolean="${turnoInstance.problemiTerzo}"/></td>

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "localitàExtra")}</g:link></td>

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "macchina")}</g:link></td>

                    <td><g:link action="show"
                                id="${turnoInstance.id}">${fieldValue(bean: turnoInstance, field: "note")}</g:link></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${turnoInstanceTotal}"/>
    </div>
</div>
</body>
</html>
