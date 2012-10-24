<%@ page import="it.algos.pubblica.Dializzato" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dializzato.label', default: 'Dializzato')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message
            code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message
            code="default.new.label" args="[entityName]"/></g:link></span>
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
                <td valign="top" class="name"><g:message code="dializzato.cognome.label"
                                                         default="Cognome"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "cognome")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.nome.label"
                                                         default="Nome"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "nome")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.trasporto.label"
                                                         default="Trasporto"/></td>

                <td valign="top" class="value">${dializzatoInstance?.trasporto?.encodeAsHTML()}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.indirizzo.label"
                                                         default="Indirizzo"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "indirizzo")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.località.label"
                                                         default="Località"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "località")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.piano.label"
                                                         default="Piano"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "piano")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.giornate.label"
                                                         default="Giornate"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "giornate")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.telefonoFisso.label"
                                                         default="Telefono Fisso"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "telefonoFisso")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.telefonoCellulare.label"
                                                         default="Telefono Cellulare"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "telefonoCellulare")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.inizioAndata.label"
                                                         default="Inizio Andata"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "inizioAndata")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.fineAndata.label"
                                                         default="Fine Andata"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "fineAndata")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.inizioRitorno.label"
                                                         default="Inizio Ritorno"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "inizioRitorno")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="dializzato.fineRitorno.label"
                                                         default="Fine Ritorno"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: dializzatoInstance, field: "fineRitorno")}</td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${dializzatoInstance?.id}"/>
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
