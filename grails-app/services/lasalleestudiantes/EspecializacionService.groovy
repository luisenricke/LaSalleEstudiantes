package lasalleestudiantes

import grails.gorm.services.Service

@Service(Especializacion)
interface EspecializacionService {

    Especializacion get(Serializable id)

    List<Especializacion> list(Map args)

    Long count()

    void delete(Serializable id)

    Especializacion save(Especializacion especializacion)

}