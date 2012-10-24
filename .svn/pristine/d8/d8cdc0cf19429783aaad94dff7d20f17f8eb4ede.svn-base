<%@ page import="it.algos.pubblica.Logo" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'logo.label', default: 'Logo')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message
            code="default.home.label"/></a></span>
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
                                  title="${message(code: 'logo.id.label', default: 'Id')}"/>

                <g:sortableColumn property="testino"
                                  title="${message(code: 'logo.testino.label', default: 'Testo')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${logoInstanceList}" status="i" var="logoInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${logoInstance.id}">${fieldValue(bean: logoInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: logoInstance, field: "testo")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${logoInstanceTotal}"/>
    </div>
</div>
</body>
</html>
