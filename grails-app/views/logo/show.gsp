<%@ page import="it.algos.pubblica.Logo" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'logo.label', default: 'Logo')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
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
                <td valign="top" class="name"><g:message code="logo.data.label" default="Data"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "data")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.ora.label" default="Ora"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "ora")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.utente.label" default="Utente"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "utente")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.modificaRecord.label"
                                                         default="Modifica Record"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "modificaRecord")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.tipoTurno.label" default="Tipo Turno"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "tipoTurno")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.dataTurno.label" default="Data Turno"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "dataTurno")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.modificaTurno.label" default="Modifica Turno"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "modificaTurno")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.tipoMilite.label" default="Tipo Milite"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "tipoMilite")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.nomeMilite.label" default="Nome Milite"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "nomeMilite")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.altroMilite.label" default="Altro Milite"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "altroMilite")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="logo.testo.label" default="Testo"/></td>

                <td valign="top" class="value">${fieldValue(bean: logoInstance, field: "testo")}</td>

            </tr>

            </tbody>
        </table>
    </div>

</div>
</body>
</html>
