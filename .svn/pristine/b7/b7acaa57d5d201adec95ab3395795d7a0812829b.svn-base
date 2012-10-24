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
<g:if test="${flash.titolo}">
    <h1>${flash.titolo}</h1>
</g:if>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:hasErrors bean="${turno}">
    <div class="errors">
        <g:renderErrors bean="${turno}" as="list"/>
    </div>
</g:hasErrors>
<g:form method="post">
<g:hiddenField name="id" value="${turno?.id}"/>
<g:hiddenField name="version" value="${turno?.version}"/>
<div class="dialog">
    <table>
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="turno.giorno.label" default="Giorno"/></td>
            <g:if test="${flash.giorno}">
                <td valign="top" class="grassetto">${flash.giorno}</td>
            </g:if>
        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="turno.tipoTurno.label" default="Tipo Turno"/></td>
            <g:if test="${flash.tipoTurno}">
                <td valign="top" class="grassetto">${flash.tipoTurno}</td>
            </g:if>
        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="turno.inizio.label" default="Inizio"/></td>
            <td valign="top" colspan="2" class="value ${hasErrors(bean: turno, field: 'inizio', 'errors')}">
                <pub:datePicker name="inizio" value="${turno?.inizio}"/>
        </tr>

        <tr class="prop">
            <g:if test="${flash.inizioLabel}">
                <td colspan="2" class="label">${flash.inizioLabel}</td>
            </g:if>
        </tr>
        <tr><td></td><td></td></tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="turno.fine.label" default="Fine"/></td>
            <td valign="top" colspan="2" class="value ${hasErrors(bean: turno, field: 'fine', 'errors')}">
                <pub:datePicker name="fine" value="${turno?.fine}"/>
            </td>
        </tr>

        <tr class="prop">
            <g:if test="${flash.fineLabel}">
                <td colspan="2" class="label">${flash.fineLabel}</td>
            </g:if>
        </tr>

        <tr><td></td><td></td></tr>

        <tr class="prop">
            <td valign="top" class="grassetto">
                <label for="autista"><g:message code="turno.autista.label" default="Autista"/></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: turno, field: 'autista', 'errors')}">
                <g:select name="autista.id"
                          from="${autisti}"
                          optionKey="id"
                          value="${turno?.autista?.id}"
                          noSelection="['null': '']"/>
            </td>
            <td valign="top">
                <label><g:message code="turno.ore.label" default="Ore"/></label>
                <g:textField name="oreAutista" value="${turno?.oreAutista}" style='width: 50px'/>
            </td>
        </tr>

        <tr class="prop">
            <g:if test="${flash.listeAutisti}">
                <td colspan="2" class="label">${flash.listeAutisti}</td>
            </g:if>
        </tr>

        <tr class="prop">
            <g:if test="${flash.problemiAutista}">
                <td colspan="2" class="label">
                    <g:checkBox name="problemiAutista" value="${turno?.problemiAutista}"/>
                    ${flash.problemiAutista}</td>
            </g:if>
        </tr>

        <tr><td></td><td></td></tr>

        <tr class="prop">
            <g:if test="${flash.avis}">
                <td valign="top" class="name">
                    <label for="secondo"><g:message code="turno.secondo.label" default="Secondo"/></label>
                </td>
            </g:if>
            <g:else>
                <td valign="top" class="grassetto">
                    <label for="secondo"><g:message code="turno.secondo.label" default="Secondo"/></label>
                </td>
            </g:else>
            <td valign="top" class="value ${hasErrors(bean: turno, field: 'secondo', 'errors')}">
                <g:select name="secondo.id"
                          from="${secondi}"
                          optionKey="id"
                          value="${turno?.secondo?.id}" noSelection="['null': '']"/>
            </td>
            <td valign="top">
                <label><g:message code="turno.ore.label" default="Ore"/></label>
                <g:textField name="oreSecondo" value="${turno?.oreSecondo}" style='width: 50px'/>
            </td>
        </tr>

        <tr class="prop">
            <g:if test="${flash.listeSecondi}">
                <td colspan="2" class="label">${flash.listeSecondi}</td>
            </g:if>
        </tr>
        <tr class="prop">
            <g:if test="${flash.problemiSecondo}">
                <td colspan="2" class="label">
                    <g:checkBox name="problemiSecondo" value="${turno?.problemiSecondo}"/>
                    ${flash.problemiSecondo}</td>
            </g:if>
        </tr>

        <tr><td></td><td></td></tr>
        <g:if test="${flash.avis}">
        </g:if>
        <g:else>
            <tr class="prop">
                <td valign="top" class="name">
                    <label for="terzo"><g:message code="turno.terzo.label" default="Terzo"/></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: turno, field: 'terzo', 'errors')}">
                    <g:select name="terzo.id"
                              from="${terzi}"
                              optionKey="id"
                              value="${turno?.terzo?.id}" noSelection="['null': '']"/>
                <td valign="top">
                    <label><g:message code="turno.ore.label" default="Ore"/></label>
                    <g:textField name="oreTerzo" value="${turno?.oreTerzo}" style='width: 50px'/>
                </td>
            </tr>

            <tr class="prop">
                <g:if test="${flash.listeTerzi}">
                    <td colspan="2" class="label">${flash.listeTerzi}</td>
                </g:if>
            </tr>
            <tr class="prop">
                <g:if test="${flash.problemiTerzo}">
                    <td colspan="2" class="label">
                        <g:checkBox name="problemiTerzo" value="${turno?.problemiTerzo}"/>
                        ${flash.problemiTerzo}</td>
                </g:if>
            </tr>

            <tr><td></td><td></td></tr>
        </g:else>

        <g:if test="${flash.extra}">
            <tr class="prop">
                <td valign="top" class="grassetto">
                    <label for="localitàExtra"><g:message code="turno.localitàExtra.label"
                                                          default="Località"/></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: turno, field: 'localitàExtra', 'errors')}">
                    <g:textField name="localitàExtra" value="${turno?.localitàExtra}"/>
                </td>
            </tr>
            <tr class="prop">
                <td colspan="2" class="label">
                    <g:message code="turno.extra.località.nota" default="Solo città"/>
                </td>
            </tr>
            <tr><td></td><td></td></tr>
        </g:if>
        <g:else>
        </g:else>

        <tr class="prop">
            <td valign="top" class="name">
                <label for="note"><g:message code="turno.note.label" default="Note"/></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: turno, field: 'note', 'errors')}">
                <g:textArea name="note" cols="80" rows="3" value="${turno?.note}"/>
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
