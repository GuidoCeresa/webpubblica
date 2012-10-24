<%@ page import="it.algos.pubblica.Turno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${turnoInstance}">
        <div class="errors">
            <g:renderErrors bean="${turnoInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="giorno"><g:message code="turno.giorno.label" default="Giorno"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'giorno', 'errors')}">
                        <g:datePicker name="giorno" precision="day" value="${turnoInstance?.giorno}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="tipoTurno"><g:message code="turno.tipoTurno.label" default="Tipo Turno"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'tipoTurno', 'errors')}">
                        <g:select name="tipoTurno.id" from="${it.algos.pubblica.TipoTurno.list()}" optionKey="id"
                                  value="${turnoInstance?.tipoTurno?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="inizio"><g:message code="turno.inizio.label" default="Inizio"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'inizio', 'errors')}">
                        <pub:timePicker name="inizio" value="${turnoInstance?.inizio}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="fine"><g:message code="turno.fine.label" default="Fine"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'fine', 'errors')}">
                        <pub:timePicker name="fine" value="${turnoInstance?.fine}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="autista"><g:message code="turno.autista.label" default="Autista"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'autista', 'errors')}">
                        <g:select name="autista.id" from="${it.algos.pubblica.Milite.list()}" optionKey="id"
                                  value="${turnoInstance?.autista?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="secondo"><g:message code="turno.secondo.label" default="Secondo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'secondo', 'errors')}">
                        <g:select name="secondo.id" from="${it.algos.pubblica.Milite.list()}" optionKey="id"
                                  value="${turnoInstance?.secondo?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="terzo"><g:message code="turno.terzo.label" default="Terzo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'terzo', 'errors')}">
                        <g:select name="terzo.id" from="${it.algos.pubblica.Milite.list()}" optionKey="id"
                                  value="${turnoInstance?.terzo?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="problemiAutista"><g:message code="turno.problemiAutista.label"
                                                                default="Problemi Autista"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: turnoInstance, field: 'problemiAutista', 'errors')}">
                        <g:checkBox name="problemiAutista" value="${turnoInstance?.problemiAutista}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="problemiSecondo"><g:message code="turno.problemiSecondo.label"
                                                                default="Problemi Secondo"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: turnoInstance, field: 'problemiSecondo', 'errors')}">
                        <g:checkBox name="problemiSecondo" value="${turnoInstance?.problemiSecondo}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="problemiTerzo"><g:message code="turno.problemiTerzo.label"
                                                              default="Problemi Terzo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'problemiTerzo', 'errors')}">
                        <g:checkBox name="problemiTerzo" value="${turnoInstance?.problemiTerzo}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="localitàExtra"><g:message code="turno.localitàExtra.label"
                                                              default="Località Extra"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'localitàExtra', 'errors')}">
                        <g:textField name="localitàExtra" value="${turnoInstance?.localitàExtra}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="macchina"><g:message code="turno.macchina.label" default="Macchina"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'macchina', 'errors')}">
                        <g:select name="macchina.id" from="${it.algos.pubblica.Macchina.list()}" optionKey="id"
                                  value="${turnoInstance?.macchina?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="note"><g:message code="turno.note.label" default="Note"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: turnoInstance, field: 'note', 'errors')}">
                        <g:textArea name="note" cols="40" rows="5" value="${turnoInstance?.note}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
