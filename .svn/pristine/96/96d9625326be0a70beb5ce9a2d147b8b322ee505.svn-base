
<%@ page import="it.algos.pubblica.Servizio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'servizio.label', default: 'Servizio')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.tipo.label" default="Tipo" /></td>
                            
                            <td valign="top" class="value">${servizioInstance?.tipo?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.macchina.label" default="Macchina" /></td>
                            
                            <td valign="top" class="value"><g:link controller="macchina" action="show" id="${servizioInstance?.macchina?.id}">${servizioInstance?.macchina?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.autista.label" default="Autista" /></td>
                            
                            <td valign="top" class="value"><g:link controller="milite" action="show" id="${servizioInstance?.autista?.id}">${servizioInstance?.autista?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.secondo.label" default="Secondo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="milite" action="show" id="${servizioInstance?.secondo?.id}">${servizioInstance?.secondo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.terzo.label" default="Terzo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="milite" action="show" id="${servizioInstance?.terzo?.id}">${servizioInstance?.terzo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.paziente.label" default="Paziente" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: servizioInstance, field: "paziente")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.uscita.label" default="Uscita" /></td>
                            
                            <td valign="top" class="value">${servizioInstance?.uscita?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.rientro.label" default="Rientro" /></td>
                            
                            <td valign="top" class="value">${servizioInstance?.rientro?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.cartellino.label" default="Cartellino" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: servizioInstance, field: "cartellino")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.durata.label" default="Durata" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: servizioInstance, field: "durata")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.kmPercorsi.label" default="Km Percorsi" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: servizioInstance, field: "kmPercorsi")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.dataInizio.label" default="Data Inizio" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${servizioInstance?.dataInizio}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.dataFine.label" default="Data Fine" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${servizioInstance?.dataFine}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.kmIniziali.label" default="Km Iniziali" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: servizioInstance, field: "kmIniziali")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="servizio.kmFinali.label" default="Km Finali" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: servizioInstance, field: "kmFinali")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${servizioInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
