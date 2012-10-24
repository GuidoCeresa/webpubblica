<%@ page import="it.algos.pubblica.Logo" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'logo.label', default: 'Logo')}"/>
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
    <g:hasErrors bean="${logoInstance}">
        <div class="errors">
            <g:renderErrors bean="${logoInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="data"><g:message code="logo.data.label" default="Data"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'data', 'errors')}">
                        <g:textField name="data" value="${logoInstance?.data}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="ora"><g:message code="logo.ora.label" default="Ora"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'ora', 'errors')}">
                        <g:textField name="ora" value="${logoInstance?.ora}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="utente"><g:message code="logo.utente.label" default="Utente"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'utente', 'errors')}">
                        <g:textField name="utente" value="${logoInstance?.utente}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="modificaRecord"><g:message code="logo.modificaRecord.label"
                                                               default="Modifica Record"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'modificaRecord', 'errors')}">
                        <g:textField name="modificaRecord" value="${logoInstance?.modificaRecord}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="tipoTurno"><g:message code="logo.tipoTurno.label" default="Tipo Turno"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'tipoTurno', 'errors')}">
                        <g:textField name="tipoTurno" value="${logoInstance?.tipoTurno}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="dataTurno"><g:message code="logo.dataTurno.label" default="Data Turno"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'dataTurno', 'errors')}">
                        <g:textField name="dataTurno" value="${logoInstance?.dataTurno}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="modificaTurno"><g:message code="logo.modificaTurno.label"
                                                              default="Modifica Turno"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'modificaTurno', 'errors')}">
                        <g:textField name="modificaTurno" value="${logoInstance?.modificaTurno}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="tipoMilite"><g:message code="logo.tipoMilite.label" default="Tipo Milite"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'tipoMilite', 'errors')}">
                        <g:textField name="tipoMilite" value="${logoInstance?.tipoMilite}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="nomeMilite"><g:message code="logo.nomeMilite.label" default="Nome Milite"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'nomeMilite', 'errors')}">
                        <g:textField name="nomeMilite" value="${logoInstance?.nomeMilite}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="altroMilite"><g:message code="logo.altroMilite.label"
                                                            default="Altro Milite"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'altroMilite', 'errors')}">
                        <g:textField name="altroMilite" value="${logoInstance?.altroMilite}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="testo"><g:message code="logo.testo.label" default="Testo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: logoInstance, field: 'testo', 'errors')}">
                        <g:textField name="testo" value="${logoInstance?.testo}"/>
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
