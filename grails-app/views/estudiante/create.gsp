<g:set var="nivelAcademicoService" bean="nivelAcademicoService"/>
<g:set var="especializacionService" bean="especializacionService"/>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'estudiante.label', default: 'Estudiante')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <g:javascript library='jquery' />
        <r:layoutResources/>
    </head>
    <body>
        <a href="#create-estudiante" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-estudiante" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.estudiante}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.estudiante}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.estudiante}" method="POST">
                <fieldset class="form">
                    <f:all bean="estudiante"/>
                </fieldset>

                <g:select
                    id="nivelAcademico"
                    name="nivelAcademico"
                    from="${nivelAcademicoService.list()}"
                    optionValue="nombre"
                    optionKey="id"
                    noSelection="['':' - Elige tu nivel - ']"
                    onchange="seleccionNivelAcademia(this.value);"
                    required="required"
                />

                <g:select
                     id= "especializacion"
                     name= "especializacion"
                     from= "[]"
                     optionKey= "id"
                     noSelection= "['': ' - Elige una especialidad - ']"
                />

                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>

        <g:javascript>
            function seleccionNivelAcademia(nivelAcademicoId) {
                $.ajax({
                    type: 'POST',
                    data: { nivelAcademicoId: nivelAcademicoId },
                    url: '${g.createLink( controller:'estudiante', action:'encontrarEspecialidades')}',
                    success: function(data,textStatus) {
                        jQuery('#especializacion').html(data);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log("parametro nulo")
                    }
                });
            }
        </g:javascript>
    </body>
</html>
