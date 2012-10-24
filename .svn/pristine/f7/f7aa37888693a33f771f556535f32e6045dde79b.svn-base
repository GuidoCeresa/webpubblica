<%@ page import="it.algos.pubblica.Turno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.giorno.label" default="Giorno"/></td>

                <td valign="top" class="value"><g:formatDate date="${turnoInstance?.giorno}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.tipoTurno.label" default="Tipo Turno"/></td>

                <td valign="top" class="value"><g:link controller="tipoTurno" action="show"
                                                       id="${turnoInstance?.tipoTurno?.id}">${turnoInstance?.tipoTurno?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.inizio.label" default="Inizio"/></td>

                <td valign="top" class="value"><g:formatDate date="${turnoInstance?.inizio}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.fine.label" default="Fine"/></td>

                <td valign="top" class="value"><g:formatDate date="${turnoInstance?.fine}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.autista.label" default="Autista"/></td>

                <td valign="top" class="value"><g:link controller="milite" action="show"
                                                       id="${turnoInstance?.autista?.id}">${turnoInstance?.autista?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.secondo.label" default="Secondo"/></td>

                <td valign="top" class="value"><g:link controller="milite" action="show"
                                                       id="${turnoInstance?.secondo?.id}">${turnoInstance?.secondo?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.terzo.label" default="Terzo"/></td>

                <td valign="top" class="value"><g:link controller="milite" action="show"
                                                       id="${turnoInstance?.terzo?.id}">${turnoInstance?.terzo?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.problemiAutista.label"
                                                         default="Problemi Autista"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${turnoInstance?.problemiAutista}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.problemiSecondo.label"
                                                         default="Problemi Secondo"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${turnoInstance?.problemiSecondo}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.problemiTerzo.label"
                                                         default="Problemi Terzo"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${turnoInstance?.problemiTerzo}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.localitàExtra.label"
                                                         default="Località Extra"/></td>

                <td valign="top" class="value">${fieldValue(bean: turnoInstance, field: "localitàExtra")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.macchina.label" default="Macchina"/></td>

                <td valign="top" class="value"><g:link controller="macchina" action="show"
                                                       id="${turnoInstance?.macchina?.id}">${turnoInstance?.macchina?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="turno.note.label" default="Note"/></td>

                <td valign="top" class="value">${fieldValue(bean: turnoInstance, field: "note")}</td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${turnoInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit"
                                                 value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
