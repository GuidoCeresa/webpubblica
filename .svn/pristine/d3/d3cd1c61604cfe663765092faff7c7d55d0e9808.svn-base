<%@ page import="it.algos.pubblica.Macchina" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'macchina.label', default: 'Macchina')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
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
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${macchinaInstance}">
        <div class="errors">
            <g:renderErrors bean="${macchinaInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${macchinaInstance?.id}"/>
        <g:hiddenField name="version" value="${macchinaInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="tipo"><g:message code="macchina.tipo.label"
                                                     default="Tipo"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: macchinaInstance, field: 'tipo', 'errors')}">
                        <g:select name="tipo" from="${it.algos.pubblica.TipoMacchina?.values()}"
                                  keys="${it.algos.pubblica.TipoMacchina?.values()*.name()}"
                                  value="${macchinaInstance?.tipo?.name()}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="sigla"><g:message code="macchina.sigla.label"
                                                      default="Sigla"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: macchinaInstance, field: 'sigla', 'errors')}">
                        <g:textField name="sigla" value="${macchinaInstance?.sigla}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="targa"><g:message code="macchina.targa.label"
                                                      default="Targa"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: macchinaInstance, field: 'targa', 'errors')}">
                        <g:textField name="targa" value="${macchinaInstance?.targa}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="dataAcquisto"><g:message code="macchina.dataAcquisto.label"
                                                             default="Data Acquisto"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: macchinaInstance, field: 'dataAcquisto', 'errors')}">
                        <g:datePicker name="dataAcquisto" precision="day"
                                      value="${macchinaInstance?.dataAcquisto}" default="none"
                                      noSelection="['': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="contakilometri"><g:message code="macchina.contakilometri.label"
                                                               default="Contakilometri"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: macchinaInstance, field: 'contakilometri', 'errors')}">
                        <g:textField name="contakilometri"
                                     value="${fieldValue(bean: macchinaInstance, field: 'contakilometri')}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update"
                                                 value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
