<g:set var="nivelAcademicoS" bean="nivelAcademicoService"/>
<g:set var="especializacionS" bean="especializacionService"/>
<g:set var="estudianteS" bean="estudianteService"/>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'estudiante.label', default: 'Estudiante')}"/>
    <title>
        Registro de estudiante
    </title>
    <g:javascript library='jquery'/>
    <r:layoutResources/>
</head>
<body>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
    </div>

    <div id="create-estudiante-historial" class="content scaffold-create" role="main">
        <h1>Registro de estudiante</h1>

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

        <g:form controller="home" method="POST">
            <%-- Estudiante --%>
            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="nombre">Nombre<span class='required-indicator'>*</span></label>
                    <input type="text" name="estudiante.nombre" value="" required=""  />
                </div>
            </fieldset>

            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="paterno">Primer apellido<span class='required-indicator'>*</span></label>
                    <input type="text" name="estudiante.paterno" value="" required=""  />
                </div>
            </fieldset>

            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="materno">Segundo apellido<span class='required-indicator'>*</span></label>
                    <input type="text" name="estudiante.materno" value="" required=""  />
                </div>
            </fieldset>

            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="matricula">Matricula<span class='required-indicator'>*</span></label>
                    <input type="text" name="estudiante.matricula" value="" required=""  />
                </div>
            </fieldset>

            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="correo">Correo<span class='required-indicator'>*</span></label>
                    <input type="text" name="estudiante.correo" value="" required=""  />
                </div>
            </fieldset>

            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="contrasenia">Contraseña<span class='required-indicator'>*</span></label>
                    <input type="password" name="estudiante.contrasenia" value="" required=""  />
                </div>
            </fieldset>



            <%-- NivelAcademia && Especializacion --%>
            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="nivelAcademico">Nivel Académico<span class='required-indicator'>*</span></label>
                        <g:select
                                name="nivelAcademico.id"
                                from="${nivelAcademicoS.list()}"
                                optionValue="nombre"
                                optionKey="id"
                                noSelection="['':' - Elige el nivel académico - ']"
                                onchange="seleccionNivelAcademia(this.value);"
                                required="required"
                        />
                </div>
            </fieldset>


            <fieldset class="form">
                <div class='fieldcontain'>
                    <label for="especializacion">Especialización</label>
                        <g:select
                                id= "especializacion-id"
                                name= "especializacion.id"
                                from= "[]"
                                optionKey= "id"
                                noSelection= "[null: ' - Elige una especialidad - ']"
                        />
                </div>
            </fieldset>

            <%-- HistorialAcademico  --%>
            <fieldset class="form">
                <div class='fieldcontain required'>
                    <label for="semestre">Semestre<span class='required-indicator'>*</span></label>
                        <g:select
                                name="historial.semestre"
                                from="${1..15}"
                                value=""
                                noSelection="['':' - Elige el semestre - ']"
                                required="required"
                        />
                </div>
            </fieldset>

            <fieldset class="buttons">
                <g:actionSubmit  name="create" action="save" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </fieldset>
        </g:form>
    </div>

    <g:javascript>
        function seleccionNivelAcademia(nivelAcademicoId) {
            $.ajax({
                type: 'POST',
                data: { nivelAcademicoId: nivelAcademicoId },
                url: '${g.createLink( controller:'home', action:'encontrarEspecialidades')}',
                success: function(data, textStatus) {
                    jQuery('#especializacion-id').html(data);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }
    </g:javascript>
</body>
</html>
