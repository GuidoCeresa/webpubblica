<%@ page import="it.algos.pubblica.Milite" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'milite.label', default: 'Milite')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        <span class="menuButton"><g:link class="tabellonePrima" controller="milite"
                                         action="serviziAlfabetico">Ordine alfabetico</g:link></span>
        <span class="menuButton"><g:link class="tabellonePrima" controller="milite"
                                         action="serviziPerTurni">Ordine per turni</g:link></span>
    </span>
</div>

<div class="help">

    <h1>Turni effettuati dai militi dal 1° gennaio ad oggi</h1>
    ${mediaTxt}
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <br/>

    <div class="list">
        <table class="servizi">
            <thead>
            <tr class="servizi">
                <th width="10">#</th>
                <th width="250">Milite</th>
                <th width="30">Ore</th>
                <th width="30">Turni</th>
                <th width="20">Ord</th>
                <th width="20">118</th>
                <th width="20">Avi</th>
                <th width="20">Ext</th>
                <th width="20">Aut</th>
                <th width="20">Sec</th>
                <th width="20">Ter</th>
            </tr>
            </thead>

            <tbody>
            <g:each in="${militeList}" status="i" var="militeInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        ${militeInstance['num']}
                    </td>
                    <td>
                        <g:link action="turni" id="${militeInstance['id']}">${militeInstance['milite']}</g:link>
                    </td>
                    <td class="${militeInstance['turni'] >= media ? 'verde' : 'rosso'}">
                        ${militeInstance['ore']}
                    </td>
                    <td class="${militeInstance['turni'] >= media ? 'verde' : 'rosso'}">
                        ${militeInstance['turni']}
                    </td>
                    <td>
                        ${militeInstance['ordinario']}
                    </td>
                    <td>
                        ${militeInstance['118']}
                    </td>
                    <td>
                        ${militeInstance['avis']}
                    </td>
                    <td>
                        ${militeInstance['extra']}
                    </td>
                    <td>
                        ${militeInstance['autista']}
                    </td>
                    <td>
                        ${militeInstance['secondo']}
                    </td>
                    <td>
                        ${militeInstance['terzo']}
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>


    <div class="list">
        <table class="servizi">
            <thead>
            <tr class="servizi">
                <th width="10">#</th>
                <th width="250">Dipendente</th>
                <th width="30">Ore</th>
                <th width="30">Turni</th>
                <th width="20">Ord</th>
                <th width="20">118</th>
                <th width="20">Avi</th>
                <th width="20">Ext</th>
                <th width="20">Aut</th>
                <th width="20">Sec</th>
                <th width="20">Ter</th>
            </tr>
            </thead>

            <tbody>
            <g:each in="${dipendenteList}" status="i" var="militeInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        ${militeInstance['num']}
                    </td>
                    <td>
                        <g:link action="turni" id="${militeInstance['id']}">${militeInstance['milite']}</g:link>
                    </td>
                    <td class="${militeInstance['turni'] >= media ? 'verde' : 'rosso'}">
                        ${militeInstance['ore']}
                    </td>
                    <td class="${militeInstance['turni'] >= media ? 'verde' : 'rosso'}">
                        ${militeInstance['turni']}
                    </td>
                    <td>
                        ${militeInstance['ordinario']}
                    </td>
                    <td>
                        ${militeInstance['118']}
                    </td>
                    <td>
                        ${militeInstance['avis']}
                    </td>
                    <td>
                        ${militeInstance['extra']}
                    </td>
                    <td>
                        ${militeInstance['autista']}
                    </td>
                    <td>
                        ${militeInstance['secondo']}
                    </td>
                    <td>
                        ${militeInstance['terzo']}
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

</div>

<div class="nav">
    <span class="menuButton">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
</div>

</body>
</html>
