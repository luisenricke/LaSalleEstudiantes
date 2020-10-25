package lasalleestudiantes

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EspecializacionServiceSpec extends Specification {

    EspecializacionService especializacionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Especializacion(...).save(flush: true, failOnError: true)
        //new Especializacion(...).save(flush: true, failOnError: true)
        //Especializacion especializacion = new Especializacion(...).save(flush: true, failOnError: true)
        //new Especializacion(...).save(flush: true, failOnError: true)
        //new Especializacion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //especializacion.id
    }

    void "test get"() {
        setupData()

        expect:
        especializacionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Especializacion> especializacionList = especializacionService.list(max: 2, offset: 2)

        then:
        especializacionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        especializacionService.count() == 5
    }

    void "test delete"() {
        Long especializacionId = setupData()

        expect:
        especializacionService.count() == 5

        when:
        especializacionService.delete(especializacionId)
        sessionFactory.currentSession.flush()

        then:
        especializacionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Especializacion especializacion = new Especializacion()
        especializacionService.save(especializacion)

        then:
        especializacion.id != null
    }
}
