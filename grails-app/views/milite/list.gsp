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
                                  title="${message(code: 'milite.id.label', default: 'Id')}"/>

                <g:sortableColumn property="cognome"
                                  title="${message(code: 'milite.cognome.label', default: 'Cognome')}"/>

                <g:sortableColumn property="nome"
                                  title="${message(code: 'milite.nome.label', default: 'Nome')}"/>

                <g:sortableColumn property="autista118"
                                  title="${message(code: 'milite.autista118.label', default: 'Autista118')}"/>

                <g:sortableColumn property="soccorritore118"
                                  title="${message(code: 'milite.soccorritore118.label', default: 'Soccorritore118')}"/>

                <g:sortableColumn property="terzo118"
                                  title="${message(code: 'milite.terzo118.label', default: 'Terzo118')}"/>

                <g:sortableColumn property="autistaOrdinario"
                                  title="${message(code: 'milite.autistaOrdinario.label', default: 'Autista Ordinario')}"/>

                <g:sortableColumn property="soccorritoreOrdinario"
                                  title="${message(code: 'milite.soccorritoreOrdinario.label', default: 'Soccorritore Ordinario')}"/>

                <g:sortableColumn property="terzoOrdinario"
                                  title="${message(code: 'milite.terzoOrdinario.label', default: 'Terzo Ordinario')}"/>

                <g:sortableColumn property="dipendente"
                                  title="${message(code: 'milite.dipendente.label', default: 'Dipendente')}"/>

                <g:sortableColumn property="attivo"
                                  title="${message(code: 'milite.attivo.label', default: 'Attivo')}"/>

                <g:sortableColumn property="telefonoFisso"
                                  title="${message(code: 'milite.telefonoFisso.label', default: 'Telefono Fisso')}"/>

                <g:sortableColumn property="telefonoCellulare"
                                  title="${message(code: 'milite.telefonoCellulare.label', default: 'Telefono Cellulare')}"/>

                <g:sortableColumn property="note"
                                  title="${message(code: 'milite.note.label', default: 'Note')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${militeInstanceList}" status="i" var="militeInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${militeInstance.id}">${fieldValue(bean: militeInstance, field: "id")}</g:link></td>

                    <td><g:link action="show"
                                id="${militeInstance.id}">${fieldValue(bean: militeInstance, field: "cognome")}</g:link></td>

                    <td><g:link action="show"
                                id="${militeInstance.id}">${fieldValue(bean: militeInstance, field: "nome")}</g:link></td>

                    <td><g:formatBoolean boolean="${militeInstance.autista118}"/></td>

                    <td><g:formatBoolean boolean="${militeInstance.soccorritore118}"/></td>

                    <td><g:formatBoolean boolean="${militeInstance.terzo118}"/></td>

                    <td><g:formatBoolean boolean="${militeInstance.autistaOrdinario}"/></td>

                    <td><g:formatBoolean boolean="${militeInstance.soccorritoreOrdinario}"/></td>

                    <td><g:formatBoolean boolean="${militeInstance.terzoOrdinario}"/></td>

                    <td><g:formatBoolean boolean="${militeInstance.dipendente}"/></td>

                    <td><g:formatBoolean boolean="${militeInstance.attivo}"/></td>

                    <td><g:link action="show"
                                id="${militeInstance.id}">${fieldValue(bean: militeInstance, field: "telefonoFisso")}</g:link></td>

                    <td><g:link action="show"
                                id="${militeInstance.id}">${fieldValue(bean: militeInstance, field: "telefonoCellulare")}</g:link></td>

                    <td><g:link action="show"
                                id="${militeInstance.id}">${fieldValue(bean: militeInstance, field: "note")}</g:link></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${militeInstanceTotal}"/>
    </div>
</div>
</body>
</html>
