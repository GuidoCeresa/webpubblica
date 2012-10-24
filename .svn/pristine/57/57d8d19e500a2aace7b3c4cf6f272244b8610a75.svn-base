<%@ page import="it.algos.pubblica.Milite" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'milite.label', default: 'Milite')}"/>
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
                <td valign="top" class="name"><g:message code="milite.cognome.label" default="Cognome"/></td>

                <td valign="top" class="value">${fieldValue(bean: militeInstance, field: "cognome")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.nome.label" default="Nome"/></td>

                <td valign="top" class="value">${fieldValue(bean: militeInstance, field: "nome")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.autista118.label" default="Autista118"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${militeInstance?.autista118}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.soccorritore118.label"
                                                         default="Soccorritore118"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${militeInstance?.soccorritore118}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.terzo118.label" default="Terzo118"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${militeInstance?.terzo118}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.autistaOrdinario.label"
                                                         default="Autista Ordinario"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${militeInstance?.autistaOrdinario}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.soccorritoreOrdinario.label"
                                                         default="Soccorritore Ordinario"/></td>

                <td valign="top" class="value"><g:formatBoolean
                        boolean="${militeInstance?.soccorritoreOrdinario}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.terzoOrdinario.label"
                                                         default="Terzo Ordinario"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${militeInstance?.terzoOrdinario}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.dipendente.label" default="Dipendente"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${militeInstance?.dipendente}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.attivo.label" default="Attivo"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${militeInstance?.attivo}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.telefonoFisso.label"
                                                         default="Telefono Fisso"/></td>

                <td valign="top" class="value">${fieldValue(bean: militeInstance, field: "telefonoFisso")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.telefonoCellulare.label"
                                                         default="Telefono Cellulare"/></td>

                <td valign="top" class="value">${fieldValue(bean: militeInstance, field: "telefonoCellulare")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="milite.note.label" default="Note"/></td>

                <td valign="top" class="value">${fieldValue(bean: militeInstance, field: "note")}</td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${militeInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit"
                                                 value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
