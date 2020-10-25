package lasalleestudiantes

import grails.gorm.services.Service

@Service(NivelAcademico)
interface NivelAcademicoService {

    NivelAcademico get(Serializable id)

    List<NivelAcademico> list(Map args)

    Long count()

    void delete(Serializable id)

    NivelAcademico save(NivelAcademico nivelAcademico)

}