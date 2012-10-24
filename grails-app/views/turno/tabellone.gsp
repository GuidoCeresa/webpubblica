<%@ page import="it.algos.pubblica.Turno" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <META HTTP-EQUIV="refresh" CONTENT="180">
    <title>Pubblica</title>
</head>

<body>
<pub:titoloPagina></pub:titoloPagina>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>


<div class="nav">
    %{--<span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>--}%
    <span class="menuButton"><g:link class="tabellonePrima" action="tabellonePrima"
                                     azione="Settimana prima">Settimana precedente</g:link></span>
    <span class="menuButton"><g:link class="tabelloneDopo" action="tabelloneLunedi">Settimana da lunedì</g:link></span>
    <span class="menuButton"><g:link class="tabellonePrima" action="tabelloneOggi">Settimana da oggi</g:link></span>
    <span class="menuButton"><g:link class="tabelloneDopo" action="tabelloneDopo">Settimana successiva</g:link></span>
    %{--<span class="menuButton"><g:link class="tabelloneDopo" action="turniVuoti">Add turni</g:link></span>--}%
    %{--<span class="menuButton"><g:link class="tabelloneDopo" action="turniPieni">Riempi turni</g:link></span>--}%
    <span class="menuButton"><g:link class="help" action="help">Aiuto</g:link></span>
    <span class="menuButton"><g:link class="tabellonePrima" controller="milite"
                                     action="servizi">Statistiche</g:link></span>
    <sec:ifNotLoggedIn>
        <span class="menuButton"><g:link class="help" controller="login">Login</g:link></span>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
        <span class="menuButton"><g:link class="help" controller="logout">Logout</g:link></span>
    </sec:ifLoggedIn>
</div>


<pub:tabella dataInizio="${dataInizio}" dataFine="${dataFine}"></pub:tabella>

</body>
</html>