<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'estudiante.label', default: 'Estudiante')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
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

                <g:set var="academia" bean="nivelAcademicoService"/>
                <g:select
                    id="nivelAcademico"
                    name="nivelAcademico"
                    noSelection="${['':'- Elige una academia -']}"
                    from="${academia.list()}"
                />

                <g:set var="especial" bean="especializacionService"/>
                <g:select
                    id="especialidades"
                    name="especialidades"
                    noSelection="${['':'- Elige la especialidad -']}"
                    from="${especial.list()}"
                />

                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>

    <script>
        $(document).ready(function() {
            $("#nivelAcademico").change(function() {
                $.ajax({
                    url: "/lasalleestudiantes/estudiante/academiaSelected",
                    data: "id=" + this.value,
                    cache: false,
                    success: function(html) {
                        $("#especializacion").html(html);
                    }
                });
            });
        });
    </script>

</html>
