<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="main"/>
    <style type="text/css" media="screen">

    #nav {
        margin-top: 20px;
        margin-left: 30px;
        width: 228px;
        float: left;

    }

    .homePagePanel * {
        margin: 0px;
    }

    .homePagePanel .panelBody ul {
        list-style-type: none;
        margin-bottom: 10px;
    }

    .homePagePanel .panelBody h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin-bottom: 10px;
    }

    .homePagePanel .panelBody {
        background: url(images/leftnav_midstretch.png) repeat-y top;
        margin: 0px;
        padding: 15px;
    }

    .homePagePanel .panelBtm {
        background: url(images/leftnav_btm.png) no-repeat top;
        height: 20px;
        margin: 0px;
    }

    .homePagePanel .panelTop {
        background: url(images/leftnav_top.png) no-repeat top;
        height: 11px;
        margin: 0px;
    }

    h2 {
        margin-top: 15px;
        margin-bottom: 15px;
        font-size: 1.4em;
    }

    #pageBody {
        margin-left: 20px;
        margin-right: 20px;
        font-size: 1.4em;
    }

    #moduli {
        margin-top: 15px;
        margin-bottom: 15px;
        font-size: 1.4em;
    }

    a:link, a:visited, a:hover {
        font-size: 24px;
        font-weight: bold;
        text-decoration: none;
    }

    </style>
</head>

<body>
<div id="pageBody">

    <div id="controllerList" class="moduli">
        <h2>Moduli per il responsabile della formazione:</h2>
        <ul>
            <li class="controller"><g:link controller="milite"
                                           action="serviziAlfabetico">Turni dei militi (alfabetico)</g:link></li>
            <li class="controller"><g:link controller="milite"
                                           action="servizioPerTurni">Turni dei militi (per turni)</g:link></li>
        </ul>
    </div>

</div>
</body>
</html>
