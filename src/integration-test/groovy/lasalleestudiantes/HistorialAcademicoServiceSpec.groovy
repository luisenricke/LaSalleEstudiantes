package lasalleestudiantes

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HistorialAcademicoServiceSpec extends Specification {

    HistorialAcademicoService historialAcademicoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new HistorialAcademico(...).save(flush: true, failOnError: true)
        //new HistorialAcademico(...).save(flush: true, failOnError: true)
        //HistorialAcademico historialAcademico = new HistorialAcademico(...).save(flush: true, failOnError: true)
        //new HistorialAcademico(...).save(flush: true, failOnError: true)
        //new HistorialAcademico(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //historialAcademico.id
    }

    void "test get"() {
        setupData()

        expect:
        historialAcademicoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<HistorialAcademico> historialAcademicoList = historialAcademicoService.list(max: 2, offset: 2)

        then:
        historialAcademicoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        historialAcademicoService.count() == 5
    }

    void "test delete"() {
        Long historialAcademicoId = setupData()

        expect:
        historialAcademicoService.count() == 5

        when:
        historialAcademicoService.delete(historialAcademicoId)
        sessionFactory.currentSession.flush()

        then:
        historialAcademicoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        HistorialAcademico historialAcademico = new HistorialAcademico()
        historialAcademicoService.save(historialAcademico)

        then:
        historialAcademico.id != null
    }
}
