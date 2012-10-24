
<%@ page import="it.algos.pubblica.Servizio" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'servizio.label', default: 'Servizio')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message
            code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message
            code="default.new.label" args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>
                
                <g:sortableColumn property="id"
                                  title="${message(code: 'servizio.id.label', default: 'Id')}"/>
                
                <g:sortableColumn property="tipo"
                                  title="${message(code: 'servizio.tipo.label', default: 'Tipo')}"/>
                
                <g:sortableColumn property="macchina"
                                  title="${message(code: 'servizio.macchina.label', default: 'Macchina')}"/>
                
                <g:sortableColumn property="autista"
                                  title="${message(code: 'servizio.autista.label', default: 'Autista')}"/>
                
                <g:sortableColumn property="secondo"
                                  title="${message(code: 'servizio.secondo.label', default: 'Secondo')}"/>
                
                <g:sortableColumn property="terzo"
                                  title="${message(code: 'servizio.terzo.label', default: 'Terzo')}"/>
                
                <g:sortableColumn property="paziente"
                                  title="${message(code: 'servizio.paziente.label', default: 'Paziente')}"/>
                
                <g:sortableColumn property="uscita"
                                  title="${message(code: 'servizio.uscita.label', default: 'Uscita')}"/>
                
                <g:sortableColumn property="rientro"
                                  title="${message(code: 'servizio.rientro.label', default: 'Rientro')}"/>
                
                <g:sortableColumn property="cartellino"
                                  title="${message(code: 'servizio.cartellino.label', default: 'Cartellino')}"/>
                
                <g:sortableColumn property="durata"
                                  title="${message(code: 'servizio.durata.label', default: 'Durata')}"/>
                
                <g:sortableColumn property="kmPercorsi"
                                  title="${message(code: 'servizio.kmPercorsi.label', default: 'Km Percorsi')}"/>
                
            </tr>
            </thead>
            <tbody>
            <g:each in="${servizioInstanceList}" status="i" var="servizioInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "id")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "tipo")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "macchina")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "autista")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "secondo")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "terzo")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "paziente")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "uscita")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "rientro")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "cartellino")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "durata")}</g:link></td>
                    
                    <td><g:link action="show"
                                id="${servizioInstance.id}">${fieldValue(bean: servizioInstance, field: "kmPercorsi")}</g:link></td>
                    
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${servizioInstanceTotal}"/>
    </div>
</div>
</body>
</html>
