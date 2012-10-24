<%@ page import="it.algos.pubblica.Dializzato" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dializzato.label', default: 'Dializzato')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message
            code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${dializzatoInstance}">
        <div class="errors">
            <g:renderErrors bean="${dializzatoInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="cognome"><g:message code="dializzato.cognome.label"
                                                        default="Cognome"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'cognome', 'errors')}">
                        <g:textField name="cognome" value="${dializzatoInstance?.cognome}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="nome"><g:message code="dializzato.nome.label"
                                                     default="Nome"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'nome', 'errors')}">
                        <g:textField name="nome" value="${dializzatoInstance?.nome}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="trasporto"><g:message code="dializzato.trasporto.label"
                                                          default="Trasporto"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'trasporto', 'errors')}">
                        <g:select name="trasporto"
                                  from="${it.algos.pubblica.TipoTrasporto?.values()}"
                                  keys="${it.algos.pubblica.TipoTrasporto?.values()*.name()}"
                                  value="${dializzatoInstance?.trasporto?.name()}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="indirizzo"><g:message code="dializzato.indirizzo.label"
                                                          default="Indirizzo"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'indirizzo', 'errors')}">
                        <g:textField name="indirizzo" value="${dializzatoInstance?.indirizzo}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="località"><g:message code="dializzato.località.label"
                                                         default="Località"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'località', 'errors')}">
                        <g:textField name="località" value="${dializzatoInstance?.località}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="piano"><g:message code="dializzato.piano.label"
                                                      default="Piano"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'piano', 'errors')}">
                        <g:textField name="piano" value="${dializzatoInstance?.piano}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="giornate"><g:message code="dializzato.giornate.label"
                                                         default="Giornate"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'giornate', 'errors')}">
                        <g:textField name="giornate" value="${dializzatoInstance?.giornate}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="telefonoFisso"><g:message code="dializzato.telefonoFisso.label"
                                                              default="Telefono Fisso"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'telefonoFisso', 'errors')}">
                        <g:textField name="telefonoFisso"
                                     value="${dializzatoInstance?.telefonoFisso}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="telefonoCellulare"><g:message
                                code="dializzato.telefonoCellulare.label"
                                default="Telefono Cellulare"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'telefonoCellulare', 'errors')}">
                        <g:textField name="telefonoCellulare"
                                     value="${dializzatoInstance?.telefonoCellulare}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="inizioAndata"><g:message code="dializzato.inizioAndata.label"
                                                             default="Inizio Andata"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'inizioAndata', 'errors')}">
                        <g:textField name="inizioAndata"
                                     value="${fieldValue(bean: dializzatoInstance, field: 'inizioAndata')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="fineAndata"><g:message code="dializzato.fineAndata.label"
                                                           default="Fine Andata"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'fineAndata', 'errors')}">
                        <g:textField name="fineAndata"
                                     value="${fieldValue(bean: dializzatoInstance, field: 'fineAndata')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="inizioRitorno"><g:message code="dializzato.inizioRitorno.label"
                                                              default="Inizio Ritorno"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'inizioRitorno', 'errors')}">
                        <g:textField name="inizioRitorno"
                                     value="${fieldValue(bean: dializzatoInstance, field: 'inizioRitorno')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="fineRitorno"><g:message code="dializzato.fineRitorno.label"
                                                            default="Fine Ritorno"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: dializzatoInstance, field: 'fineRitorno', 'errors')}">
                        <g:textField name="fineRitorno"
                                     value="${fieldValue(bean: dializzatoInstance, field: 'fineRitorno')}"/>
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
