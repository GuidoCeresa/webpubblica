<%@ page import="it.algos.pubblica.TipoTurno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tipoTurno.label', default: 'TipoTurno')}"/>
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
    <g:hasErrors bean="${tipoTurnoInstance}">
        <div class="errors">
            <g:renderErrors bean="${tipoTurnoInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="sigla"><g:message code="tipoTurno.sigla.label" default="Sigla"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'sigla', 'errors')}">
                        <g:textField name="sigla" value="${tipoTurnoInstance?.sigla}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="descrizione"><g:message code="tipoTurno.descrizione.label"
                                                            default="Descrizione"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: tipoTurnoInstance, field: 'descrizione', 'errors')}">
                        <g:textField name="descrizione" value="${tipoTurnoInstance?.descrizione}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="ordine"><g:message code="tipoTurno.ordine.label" default="Ordine"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'ordine', 'errors')}">
                        <g:textField name="ordine" value="${fieldValue(bean: tipoTurnoInstance, field: 'ordine')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="inizio"><g:message code="tipoTurno.inizio.label" default="Inizio"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'inizio', 'errors')}">
                        <g:datePicker name="inizio" precision="day" value="${tipoTurnoInstance?.inizio}" default="none"
                                      noSelection="['': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="fine"><g:message code="tipoTurno.fine.label" default="Fine"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'fine', 'errors')}">
                        <g:datePicker name="fine" precision="day" value="${tipoTurnoInstance?.fine}" default="none"
                                      noSelection="['': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="durata"><g:message code="tipoTurno.durata.label" default="Durata"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'durata', 'errors')}">
                        <g:textField name="durata" value="${fieldValue(bean: tipoTurnoInstance, field: 'durata')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="visibile"><g:message code="tipoTurno.visibile.label" default="Visibile"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'visibile', 'errors')}">
                        <g:checkBox name="visibile" value="${tipoTurnoInstance?.visibile}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="orario"><g:message code="tipoTurno.orario.label" default="Orario"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'orario', 'errors')}">
                        <g:checkBox name="orario" value="${tipoTurnoInstance?.orario}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="autista"><g:message code="tipoTurno.autista.label" default="Autista"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'autista', 'errors')}">
                        <g:checkBox name="autista" value="${tipoTurnoInstance?.autista}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="secondo"><g:message code="tipoTurno.secondo.label" default="Secondo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'secondo', 'errors')}">
                        <g:checkBox name="secondo" value="${tipoTurnoInstance?.secondo}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="terzo"><g:message code="tipoTurno.terzo.label" default="Terzo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'terzo', 'errors')}">
                        <g:checkBox name="terzo" value="${tipoTurnoInstance?.terzo}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="multiplo"><g:message code="tipoTurno.multiplo.label" default="Multiplo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tipoTurnoInstance, field: 'multiplo', 'errors')}">
                        <g:checkBox name="multiplo" value="${tipoTurnoInstance?.multiplo}"/>
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
