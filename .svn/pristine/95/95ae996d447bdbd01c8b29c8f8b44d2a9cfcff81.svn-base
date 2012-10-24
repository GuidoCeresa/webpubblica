<%@ page import="it.algos.pubblica.Turno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><g:link class="create" action="tabellone"><g:message
            code="default.tabellone.label" args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1>${titolo}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${turnoInstance}">
        <div class="errors">
            <g:renderErrors bean="${turnoInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${turnoInstance?.id}"/>
        <g:hiddenField name="version" value="${turnoInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="turno.giorno.label" default="Giorno"/></td>
                    <td valign="top" class="grassetto"><pub:formatDateAll date="${turnoInstance?.giorno}"/></td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="turno.tipoTurno.label" default="Tipo Turno"/></td>
                    <td valign="top" class="value"><g:link controller="tipoTurno" action="show"
                                                           id="${turnoInstance?.tipoTurno?.id}">${turnoInstance?.tipoTurno?.encodeAsHTML()}</g:link></td>
                </tr>

                <tr><td></td><td></td></tr>

                <tr class="prop">
                    <td valign="top" class="grassetto">
                        <label for="inizio"><g:message code="turno.inizio.label" default="Inizio"/></label>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'inizio', 'errors')}">
                        <pub:timePicker name="inizio" value="${turnoInstance?.inizio}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2" class="label">
                        <g:message code="pub.edit.turno.centralino.inizio" default="Inizio"/>
                    </td>
                </tr>

                <tr><td></td><td></td></tr>


                <tr class="prop">
                    <td valign="top" class="grassetto">
                        <label for="inizio"><g:message code="turno.fine.label" default="Fine"/></label>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'fine', 'errors')}">
                        <pub:timePicker name="fine" value="${turnoInstance?.fine}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2" class="label">
                        <g:message code="pub.edit.turno.centralino.fine" default="Fine"/>
                    </td>
                </tr>

                <tr><td></td></tr>

                <tr class="prop">
                    <td valign="top" class="grassetto">
                        <label for="autista"><g:message code="pub.edit.turno.centralino.operatore"
                                                        default="Centralinista"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'autista', 'errors')}">
                        <g:select name="autista.id"
                                  from="${autisti}"
                                  optionKey="id"
                                  value="${turnoInstance?.autista?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr><td></td></tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="note"><g:message code="turno.note.label" default="Note"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'note', 'errors')}">
                        <g:textArea name="note" cols="80" rows="3" value="${turnoInstance?.note}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="updateTurno"
                                                 value="${message(code: 'pub.button.update.label', default: 'Registra')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="deleteTurno"
                                                 value="${message(code: 'pub.button.delete.label', default: 'Cancella')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
