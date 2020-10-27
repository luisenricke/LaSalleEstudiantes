package lasalleestudiantes

import grails.gorm.services.Service

@Service(HistorialAcademico)
interface HistorialAcademicoService {

    HistorialAcademico get(Serializable id)

    List<HistorialAcademico> list(Map args)

    Long count()

    void delete(Serializable id)

    HistorialAcademico save(HistorialAcademico historialAcademico)

}