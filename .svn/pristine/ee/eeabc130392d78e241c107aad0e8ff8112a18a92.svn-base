

<%@ page import="it.algos.pubblica.Servizio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'servizio.label', default: 'Servizio')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${servizioInstance}">
            <div class="errors">
                <g:renderErrors bean="${servizioInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipo"><g:message code="servizio.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'tipo', 'errors')}">
                                    <g:select name="tipo" from="${it.algos.pubblica.TipoServizio?.values()}" keys="${it.algos.pubblica.TipoServizio?.values()*.name()}" value="${servizioInstance?.tipo?.name()}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="macchina"><g:message code="servizio.macchina.label" default="Macchina" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'macchina', 'errors')}">
                                    <g:select name="macchina.id" from="${it.algos.pubblica.Macchina.list()}" optionKey="id" value="${servizioInstance?.macchina?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="autista"><g:message code="servizio.autista.label" default="Autista" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'autista', 'errors')}">
                                    <g:select name="autista.id" from="${it.algos.pubblica.Milite.list()}" optionKey="id" value="${servizioInstance?.autista?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="secondo"><g:message code="servizio.secondo.label" default="Secondo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'secondo', 'errors')}">
                                    <g:select name="secondo.id" from="${it.algos.pubblica.Milite.list()}" optionKey="id" value="${servizioInstance?.secondo?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="terzo"><g:message code="servizio.terzo.label" default="Terzo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'terzo', 'errors')}">
                                    <g:select name="terzo.id" from="${it.algos.pubblica.Milite.list()}" optionKey="id" value="${servizioInstance?.terzo?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paziente"><g:message code="servizio.paziente.label" default="Paziente" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'paziente', 'errors')}">
                                    <g:textField name="paziente" value="${servizioInstance?.paziente}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="uscita"><g:message code="servizio.uscita.label" default="Uscita" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'uscita', 'errors')}">
                                    <g:select name="uscita" from="${it.algos.pubblica.CodiceUscita?.values()}" keys="${it.algos.pubblica.CodiceUscita?.values()*.name()}" value="${servizioInstance?.uscita?.name()}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rientro"><g:message code="servizio.rientro.label" default="Rientro" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'rientro', 'errors')}">
                                    <g:select name="rientro" from="${it.algos.pubblica.CodiceRientro?.values()}" keys="${it.algos.pubblica.CodiceRientro?.values()*.name()}" value="${servizioInstance?.rientro?.name()}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cartellino"><g:message code="servizio.cartellino.label" default="Cartellino" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'cartellino', 'errors')}">
                                    <g:textField name="cartellino" value="${servizioInstance?.cartellino}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="durata"><g:message code="servizio.durata.label" default="Durata" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'durata', 'errors')}">
                                    <g:textField name="durata" value="${fieldValue(bean: servizioInstance, field: 'durata')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="kmPercorsi"><g:message code="servizio.kmPercorsi.label" default="Km Percorsi" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'kmPercorsi', 'errors')}">
                                    <g:textField name="kmPercorsi" value="${fieldValue(bean: servizioInstance, field: 'kmPercorsi')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dataInizio"><g:message code="servizio.dataInizio.label" default="Data Inizio" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'dataInizio', 'errors')}">
                                    <g:datePicker name="dataInizio" precision="day" value="${servizioInstance?.dataInizio}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dataFine"><g:message code="servizio.dataFine.label" default="Data Fine" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'dataFine', 'errors')}">
                                    <g:datePicker name="dataFine" precision="day" value="${servizioInstance?.dataFine}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="kmIniziali"><g:message code="servizio.kmIniziali.label" default="Km Iniziali" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'kmIniziali', 'errors')}">
                                    <g:textField name="kmIniziali" value="${fieldValue(bean: servizioInstance, field: 'kmIniziali')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="kmFinali"><g:message code="servizio.kmFinali.label" default="Km Finali" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: servizioInstance, field: 'kmFinali', 'errors')}">
                                    <g:textField name="kmFinali" value="${fieldValue(bean: servizioInstance, field: 'kmFinali')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
