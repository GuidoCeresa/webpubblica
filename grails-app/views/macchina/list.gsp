<%@ page import="it.algos.pubblica.Macchina" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'macchina.label', default: 'Macchina')}"/>
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
                                  title="${message(code: 'macchina.id.label', default: 'Id')}"/>

                <g:sortableColumn property="tipo"
                                  title="${message(code: 'macchina.tipo.label', default: 'Tipo')}"/>

                <g:sortableColumn property="sigla"
                                  title="${message(code: 'macchina.sigla.label', default: 'Sigla')}"/>

                <g:sortableColumn property="targa"
                                  title="${message(code: 'macchina.targa.label', default: 'Targa')}"/>

                <g:sortableColumn property="dataAcquisto"
                                  title="${message(code: 'macchina.dataAcquisto.label', default: 'Data Acquisto')}"/>

                <g:sortableColumn property="contakilometri"
                                  title="${message(code: 'macchina.contakilometri.label', default: 'Contakilometri')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${macchinaInstanceList}" status="i" var="macchinaInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${macchinaInstance.id}">${fieldValue(bean: macchinaInstance, field: "id")}</g:link></td>

                    <td><g:link action="show"
                                id="${macchinaInstance.id}">${fieldValue(bean: macchinaInstance, field: "tipo")}</g:link></td>

                    <td><g:link action="show"
                                id="${macchinaInstance.id}">${fieldValue(bean: macchinaInstance, field: "sigla")}</g:link></td>

                    <td><g:link action="show"
                                id="${macchinaInstance.id}">${fieldValue(bean: macchinaInstance, field: "targa")}</g:link></td>

                    <td><g:formatDate date="${macchinaInstance.dataAcquisto}"/></td>

                    <td><g:link action="show"
                                id="${macchinaInstance.id}">${fieldValue(bean: macchinaInstance, field: "contakilometri")}</g:link></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${macchinaInstanceTotal}"/>
    </div>
</div>
</body>
</html>
