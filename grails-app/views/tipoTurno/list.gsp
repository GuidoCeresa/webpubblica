<%@ page import="it.algos.pubblica.TipoTurno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'tipoTurno.label', default: 'TipoTurno')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message
            code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message
            code="default.new.label" args="[entityName]"/></g:link></span>
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
                                  title="${message(code: 'tipoTurno.id.label', default: 'Id')}"/>

                <g:sortableColumn property="sigla"
                                  title="${message(code: 'tipoTurno.sigla.label', default: 'Sigla')}"/>

                <g:sortableColumn property="descrizione"
                                  title="${message(code: 'tipoTurno.descrizione.label', default: 'Descrizione')}"/>

                <g:sortableColumn property="ordine"
                                  title="${message(code: 'tipoTurno.ordine.label', default: 'Ordine')}"/>

                <g:sortableColumn property="inizio"
                                  title="${message(code: 'tipoTurno.inizio.label', default: 'Inizio')}"/>

                <g:sortableColumn property="fine"
                                  title="${message(code: 'tipoTurno.fine.label', default: 'Fine')}"/>

                <g:sortableColumn property="durata"
                                  title="${message(code: 'tipoTurno.durata.label', default: 'Durata')}"/>

                <g:sortableColumn property="visibile"
                                  title="${message(code: 'tipoTurno.visibile.label', default: 'Visibile')}"/>

                <g:sortableColumn property="orario"
                                  title="${message(code: 'tipoTurno.orario.label', default: 'Orario')}"/>

                <g:sortableColumn property="autista"
                                  title="${message(code: 'tipoTurno.autista.label', default: 'Autista')}"/>

                <g:sortableColumn property="secondo"
                                  title="${message(code: 'tipoTurno.secondo.label', default: 'Secondo')}"/>

                <g:sortableColumn property="terzo"
                                  title="${message(code: 'tipoTurno.terzo.label', default: 'Terzo')}"/>

                <g:sortableColumn property="multiplo"
                                  title="${message(code: 'tipoTurno.multiplo.label', default: 'Multiplo')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${tipoTurnoInstanceList}" status="i" var="tipoTurnoInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${tipoTurnoInstance.id}">${fieldValue(bean: tipoTurnoInstance, field: "id")}</g:link></td>

                    <td><g:link action="show"
                                id="${tipoTurnoInstance.id}">${fieldValue(bean: tipoTurnoInstance, field: "sigla")}</g:link></td>

                    <td><g:link action="show"
                                id="${tipoTurnoInstance.id}">${fieldValue(bean: tipoTurnoInstance, field: "descrizione")}</g:link></td>

                    <td><g:link action="show"
                                id="${tipoTurnoInstance.id}">${fieldValue(bean: tipoTurnoInstance, field: "ordine")}</g:link></td>

                    <td><pub:formatDate date="${tipoTurnoInstance.inizio}"/></td>

                    <td><pub:formatDate date="${tipoTurnoInstance.fine}"/></td>

                    <td><g:link action="show"
                                id="${tipoTurnoInstance.id}">${fieldValue(bean: tipoTurnoInstance, field: "durata")}</g:link></td>

                    <td><g:formatBoolean boolean="${tipoTurnoInstance.visibile}"/></td>

                    <td><g:formatBoolean boolean="${tipoTurnoInstance.orario}"/></td>

                    <td><g:formatBoolean boolean="${tipoTurnoInstance.autista}"/></td>

                    <td><g:formatBoolean boolean="${tipoTurnoInstance.secondo}"/></td>

                    <td><g:formatBoolean boolean="${tipoTurnoInstance.terzo}"/></td>

                    <td><g:formatBoolean boolean="${tipoTurnoInstance.multiplo}"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${tipoTurnoInstanceTotal}"/>
    </div>
</div>
</body>
</html>
