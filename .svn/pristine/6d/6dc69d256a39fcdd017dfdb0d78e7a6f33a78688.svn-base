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
                <th width="100">Data</th>
                <th width="60">Funzione</th>
                <th width="50">Turno</th>
                <th width="300">Militi</th>
                <th width="30">Ore</th>
            </tr>
            </thead>

            <tbody>
            <g:each in="${turniList}" status="i" var="militeInstance">
                <g:form>
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <g:hiddenField name="idTurno" value="${militeInstance['idTurno']}"/>

                        <td>${militeInstance['num']}</td>

                        <td><g:link controller="turno" action="editPub"
                                    id="${militeInstance['idTurno']}">${militeInstance['data']}</g:link></td>

                        <td>${militeInstance['funzione']}</td>

                        <td><g:link controller="turno" action="editPub"
                                    id="${militeInstance['idTurno']}">${militeInstance['turno']}</g:link></td>

                        <td><g:link controller="turno" action="editPub"
                                    id="${militeInstance['idTurno']}">${militeInstance['militi']}</g:link></td>

                        <td><g:link controller="turno" action="editPub"
                                    id="${militeInstance['idTurno']}">${militeInstance['ore']}</g:link></td>

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
