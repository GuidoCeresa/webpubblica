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

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="turno.inizio.label" default="Inizio"/></td>
                    <td valign="top" colspan="2"
                        class="value ${hasErrors(bean: turnoInstance, field: 'inizio', 'errors')}">
                        <pub:datePicker name="inizio" value="${turnoInstance?.inizio}"/>
                </tr>

                <tr class="prop">
                    <td colspan="2" class="label">
                        <g:message code="turno.inizio.nota"/>
                    </td>
                </tr>

                <tr><td></td><td></td></tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="turno.fine.label" default="Fine"/></td>
                    <td valign="top" colspan="2"
                        class="value ${hasErrors(bean: turnoInstance, field: 'fine', 'errors')}">
                        <pub:datePicker name="fine" value="${turnoInstance?.fine}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2" class="label">
                        <g:message code="turno.fine.nota"/>
                    </td>
                </tr>

                <tr><td></td><td></td></tr>

                <tr class="prop">
                    <td valign="top" class="grassetto">
                        <label for="autista"><g:message code="turno.autista.label" default="Autista"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'autista', 'errors')}">
                        <g:select name="autista.id"
                                  from="${autisti}"
                                  optionKey="id"
                                  value="${turnoInstance?.autista?.id}" noSelection="['null': '']"/>
                    </td>
                    <td valign="top">
                        <label><g:message code="turno.ore.label" default="Ore"/></label>
                        <g:textField name="oreAutista" value="${turnoInstance?.oreAutista}" style='width: 50px'/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2" valign="top"
                        class="value ${hasErrors(bean: turnoInstance, field: 'problemiAutista', 'errors')}">
                        <g:checkBox name="problemiAutista" value="${turnoInstance?.problemiAutista}"/>
                        <g:message code="turno.problemi.autista.nota"/>
                    </td>
                </tr>

                <tr><td></td><td></td></tr>

                <tr class="prop">
                    <td valign="top" class="grassetto">
                        <label for="secondo"><g:message code="turno.secondo.label" default="Secondo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'secondo', 'errors')}">
                        <g:select name="secondo.id"
                                  from="${secondi}"
                                  optionKey="id"
                                  value="${turnoInstance?.secondo?.id}" noSelection="['null': '']"/>
                    </td>
                    <td valign="top">
                        <label><g:message code="turno.ore.label" default="Ore"/></label>
                        <g:textField name="oreSecondo" value="${turnoInstance?.oreSecondo}" style='width: 50px'/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2" valign="top"
                        class="value ${hasErrors(bean: turnoInstance, field: 'problemiSecondo', 'errors')}">
                        <g:checkBox name="problemiSecondo" value="${turnoInstance?.problemiSecondo}"/>
                        <g:message code="turno.problemi.secondo.nota"/>
                    </td>
                </tr>

                <tr><td></td><td></td></tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="terzo"><g:message code="turno.terzo.label" default="Terzo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'terzo', 'errors')}">
                        <g:select name="terzo.id"
                                  from="${terzi}"
                                  optionKey="id"
                                  value="${turnoInstance?.terzo?.id}" noSelection="['null': '']"/>
                    <td valign="top">
                        <label><g:message code="turno.ore.label" default="Ore"/></label>
                        <g:textField name="oreTerzo" value="${turnoInstance?.oreTerzo}" style='width: 50px'/>
                    </td>
                </td>
                </tr>

                <tr class="prop">
                    <td colspan="2"
                        class="value ${hasErrors(bean: turnoInstance, field: 'problemiTerzo', 'errors')}">
                        <g:checkBox name="problemiTerzo" value="${turnoInstance?.problemiTerzo}"/>
                        <g:message code="turno.problemi.terzo.nota"/>
                    </td>
                </tr>

                <tr><td></td><td></td></tr>

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
            <span class="button">
                <g:actionSubmit class="save" action="updateTurno"
                                value="${message(code: 'pub.button.update.label', default: 'Registra')}"/>
            </span>
            <span class="button">
                <g:actionSubmit class="cancel" action="tabellone"
                                value="${message(code: 'pub.button.cancel.label', default: 'Annulla')}"/>
            </span>
            <span class="button">
                <g:actionSubmit class="delete" action="deleteTurno"
                                value="${message(code: 'pub.button.delete.label', default: 'Cancella')}"
                                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
