package lasalleestudiantes

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NivelAcademicoServiceSpec extends Specification {

    NivelAcademicoService nivelAcademicoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NivelAcademico(...).save(flush: true, failOnError: true)
        //new NivelAcademico(...).save(flush: true, failOnError: true)
        //NivelAcademico nivelAcademico = new NivelAcademico(...).save(flush: true, failOnError: true)
        //new NivelAcademico(...).save(flush: true, failOnError: true)
        //new NivelAcademico(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //nivelAcademico.id
    }

    void "test get"() {
        setupData()

        expect:
        nivelAcademicoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NivelAcademico> nivelAcademicoList = nivelAcademicoService.list(max: 2, offset: 2)

        then:
        nivelAcademicoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        nivelAcademicoService.count() == 5
    }

    void "test delete"() {
        Long nivelAcademicoId = setupData()

        expect:
        nivelAcademicoService.count() == 5

        when:
        nivelAcademicoService.delete(nivelAcademicoId)
        sessionFactory.currentSession.flush()

        then:
        nivelAcademicoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NivelAcademico nivelAcademico = new NivelAcademico()
        nivelAcademicoService.save(nivelAcademico)

        then:
        nivelAcademico.id != null
    }
}
