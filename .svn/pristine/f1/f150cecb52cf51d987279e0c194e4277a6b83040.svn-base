<%@ page import="it.algos.pubblica.TipoTurno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tipoTurno.label', default: 'TipoTurno')}"/>
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
                <td valign="top" class="name"><g:message code="tipoTurno.sigla.label" default="Sigla"/></td>

                <td valign="top" class="value">${fieldValue(bean: tipoTurnoInstance, field: "sigla")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.descrizione.label" default="Descrizione"/></td>

                <td valign="top" class="value">${fieldValue(bean: tipoTurnoInstance, field: "descrizione")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.ordine.label" default="Ordine"/></td>

                <td valign="top" class="value">${fieldValue(bean: tipoTurnoInstance, field: "ordine")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.inizio.label" default="Inizio"/></td>

                <td valign="top" class="value"><g:formatDate date="${tipoTurnoInstance?.inizio}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.fine.label" default="Fine"/></td>

                <td valign="top" class="value"><g:formatDate date="${tipoTurnoInstance?.fine}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.durata.label" default="Durata"/></td>

                <td valign="top" class="value">${fieldValue(bean: tipoTurnoInstance, field: "durata")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.visibile.label" default="Visibile"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${tipoTurnoInstance?.visibile}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.orario.label" default="Orario"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${tipoTurnoInstance?.orario}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.autista.label" default="Autista"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${tipoTurnoInstance?.autista}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.secondo.label" default="Secondo"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${tipoTurnoInstance?.secondo}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.terzo.label" default="Terzo"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${tipoTurnoInstance?.terzo}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="tipoTurno.multiplo.label" default="Multiplo"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${tipoTurnoInstance?.multiplo}"/></td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${tipoTurnoInstance?.id}"/>
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
