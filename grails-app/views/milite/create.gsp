<%@ page import="it.algos.pubblica.Milite" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'milite.label', default: 'Milite')}"/>
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
    <g:hasErrors bean="${militeInstance}">
        <div class="errors">
            <g:renderErrors bean="${militeInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="cognome"><g:message code="milite.cognome.label" default="Cognome"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'cognome', 'errors')}">
                        <g:textField name="cognome" value="${militeInstance?.cognome}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="nome"><g:message code="milite.nome.label" default="Nome"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'nome', 'errors')}">
                        <g:textField name="nome" value="${militeInstance?.nome}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="autista118"><g:message code="milite.autista118.label" default="Autista118"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'autista118', 'errors')}">
                        <g:checkBox name="autista118" value="${militeInstance?.autista118}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="soccorritore118"><g:message code="milite.soccorritore118.label"
                                                                default="Soccorritore118"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: militeInstance, field: 'soccorritore118', 'errors')}">
                        <g:checkBox name="soccorritore118" value="${militeInstance?.soccorritore118}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="terzo118"><g:message code="milite.terzo118.label" default="Terzo118"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'terzo118', 'errors')}">
                        <g:checkBox name="terzo118" value="${militeInstance?.terzo118}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="autistaOrdinario"><g:message code="milite.autistaOrdinario.label"
                                                                 default="Autista Ordinario"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: militeInstance, field: 'autistaOrdinario', 'errors')}">
                        <g:checkBox name="autistaOrdinario" value="${militeInstance?.autistaOrdinario}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="soccorritoreOrdinario"><g:message code="milite.soccorritoreOrdinario.label"
                                                                      default="Soccorritore Ordinario"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: militeInstance, field: 'soccorritoreOrdinario', 'errors')}">
                        <g:checkBox name="soccorritoreOrdinario" value="${militeInstance?.soccorritoreOrdinario}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="terzoOrdinario"><g:message code="milite.terzoOrdinario.label"
                                                               default="Terzo Ordinario"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: militeInstance, field: 'terzoOrdinario', 'errors')}">
                        <g:checkBox name="terzoOrdinario" value="${militeInstance?.terzoOrdinario}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="dipendente"><g:message code="milite.dipendente.label" default="Dipendente"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'dipendente', 'errors')}">
                        <g:checkBox name="dipendente" value="${militeInstance?.dipendente}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="attivo"><g:message code="milite.attivo.label" default="Attivo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'attivo', 'errors')}">
                        <g:checkBox name="attivo" value="${militeInstance?.attivo}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="telefonoFisso"><g:message code="milite.telefonoFisso.label"
                                                              default="Telefono Fisso"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'telefonoFisso', 'errors')}">
                        <g:textField name="telefonoFisso" value="${militeInstance?.telefonoFisso}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="telefonoCellulare"><g:message code="milite.telefonoCellulare.label"
                                                                  default="Telefono Cellulare"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: militeInstance, field: 'telefonoCellulare', 'errors')}">
                        <g:textField name="telefonoCellulare" value="${militeInstance?.telefonoCellulare}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="note"><g:message code="milite.note.label" default="Note"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: militeInstance, field: 'note', 'errors')}">
                        <g:textArea name="note" cols="40" rows="5" value="${militeInstance?.note}"/>
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
