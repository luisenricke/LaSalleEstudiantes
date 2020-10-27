<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>La Salle</title>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li class="dropdown-item"><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
            <li class="dropdown-item"><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li class="dropdown-item"><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li class="dropdown-item"><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li class="dropdown-item"><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container" style="background-color:white">
        <asset:image src="lasalle-logo.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Luis Enrique Villalobos Meléndez</h1>

        <p style="text-align:left;margin-top:8px;">El ejercicio consiste en realizar un formulario de nuevos estudiantes con Groovy & Grails. Se crearon las siguientes tablas:</p>
        <ul>
            <li><strong>Nivel Académico</strong>: Esta tabla almacenará el nombre de todos los niveles académicos de la escuela</li>
            <li><strong>Especialidad</strong>: Esta tabla contendrá el nombre de la especialidad y se relacionará con Nivel Académico para hacerlo dinámico</li>
            <li><strong>Estudiante</strong>: Esta tabla contendrá la información personal del estudiante, el campo matrícula será único</li>
            <li><strong>Historial Académico</strong>: Esta tabla se relacionará con las demás para que se generé un expediente de los alumnos y poder generar datos estadísticos </li>
        </ul>

        <p style="text-align:left;margin-top:8px;">En el la url <g:link controller="home" action="register">/register</g:link> contendrá el formulario del registro de los estudiantes de nuevo ingreso. Además se enviará su información por correo de acuse de recibo de la información. Se evaluará los siguientes criterios:</p>
        <ul style="font-style:italic;font-size:95%;">
            <li>El alumno no debe de estar registrado y si ya se encuentra registrado se enviara a una página con un mensaje de alerta</li>
            <li>Tiene que poner un correo válido</li>
            <li>Tiene que poner su matrícula de 9 dígitos y única</li>
            <li>El único semestre válido es el primero</li>
            <li>Tiene que poner una contraseña segura</li>
            <li>Tiene que seleccionar al menos su nivel académico, y si tiene relación con alguna especialidad es requerida</li>
            <li>Antes de confirmar el registro del alumno, se le envirá una mensaje de confirmación de los datos</li>
        </ul>

        <p style="text-align:left;margin-top:8px;">Para las demás tablas se podrá manejar todas las operaciones de un CRUD básico con /{nombre-tabla}/{operacion}/{id}</p>

        <div class="w-100"></div>

        <div id="controllers" role="navigation" style="margin-bottom:40px;">
            <h2>Controladores:</h2>
            <ul >
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
                    <g:if test="${c.name != 'Home'}">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.name}</g:link>
                        </li>
                    </g:if>
                </g:each>
            </ul>
        </div>
    </section>
</div>

</body>
</html>
