<%@ page import="it.algos.pubblica.Dializzato" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'dializzato.label', default: 'Dializzato')}"/>
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
                                  title="${message(code: 'dializzato.id.label', default: 'Id')}"/>

                <g:sortableColumn property="cognome"
                                  title="${message(code: 'dializzato.cognome.label', default: 'Cognome')}"/>

                <g:sortableColumn property="nome"
                                  title="${message(code: 'dializzato.nome.label', default: 'Nome')}"/>

                <g:sortableColumn property="trasporto"
                                  title="${message(code: 'dializzato.trasporto.label', default: 'Trasporto')}"/>

                <g:sortableColumn property="indirizzo"
                                  title="${message(code: 'dializzato.indirizzo.label', default: 'Indirizzo')}"/>

                <g:sortableColumn property="località"
                                  title="${message(code: 'dializzato.località.label', default: 'Località')}"/>

                <g:sortableColumn property="piano"
                                  title="${message(code: 'dializzato.piano.label', default: 'Piano')}"/>

                <g:sortableColumn property="giornate"
                                  title="${message(code: 'dializzato.giornate.label', default: 'Giornate')}"/>

                <g:sortableColumn property="telefonoFisso"
                                  title="${message(code: 'dializzato.telefonoFisso.label', default: 'Telefono Fisso')}"/>

                <g:sortableColumn property="telefonoCellulare"
                                  title="${message(code: 'dializzato.telefonoCellulare.label', default: 'Telefono Cellulare')}"/>

                <g:sortableColumn property="inizioAndata"
                                  title="${message(code: 'dializzato.inizioAndata.label', default: 'Inizio Andata')}"/>

                <g:sortableColumn property="fineAndata"
                                  title="${message(code: 'dializzato.fineAndata.label', default: 'Fine Andata')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${dializzatoInstanceList}" status="i" var="dializzatoInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "id")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "cognome")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "nome")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "trasporto")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "indirizzo")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "località")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "piano")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "giornate")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "telefonoFisso")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "telefonoCellulare")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "inizioAndata")}</g:link></td>

                    <td><g:link action="show"
                                id="${dializzatoInstance.id}">${fieldValue(bean: dializzatoInstance, field: "fineAndata")}</g:link></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${dializzatoInstanceTotal}"/>
    </div>
</div>
</body>
</html>
