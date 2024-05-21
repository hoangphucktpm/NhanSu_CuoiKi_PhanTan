package Test;


import dao.CadidateDao;
import dao.Impl.CadidateImpl;
import entity.Candidate;
import entity.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CadidateTest {
    private CadidateDao cadidateDao;

    @BeforeAll
    public void setUp() throws RemoteException {
        cadidateDao = new CadidateImpl();
    }

    // Cau b
    @Test
    void testListCadidatesByCompanies() throws RemoteException {
//        System.out.println("Test list cadidates by companies");
//        cadidateDao.listCadidatesByCompanies().forEach((k, v) -> System.out.println(k + " " + v));

    }


        // Cau c
        @Test
        void testListCadidatesWithLongestWorking() throws RemoteException {
//        System.out.println("Test list cadidates with longest working");
//        cadidateDao.listCadidatesWithLongestWorking().forEach((k, v) -> System.out.println(k + " " + v));

    }

    // Cau d
    @Test
    void testAddCandidate() throws RemoteException {
        Candidate candidate = new Candidate("C003", "Nguyen Van A", 1999, "Male", "aa@gmail.com", "0123456787", "The best");
        assertTrue(cadidateDao.addCandidate(candidate));
    }

    @Test
    void testAddCandidate2() throws RemoteException {
        Candidate candidate = new Candidate("C003", "Nguyen Van A", 1999, "Male", "aa@gmail.com", "0123456787", "The best");
        assertEquals(false, cadidateDao.addCandidate(candidate));
    }

    @Test
    void testAddCandidate3() throws RemoteException {
        Candidate candidate = new Candidate("C03", "Nguyen Van A", 1999, "Male", "aa@gmail.com", "0123456787", "The best");
        assertEquals(false, cadidateDao.addCandidate(candidate));
    }

    // Câu f
    @Test
    // f) Liệt kê danh sách các ứng viên và danh sách bằng cấp của từng ứng viên.
    //+ listCadidatesAndCertificates(): Map<Candidate, Set<Certificate >>
    void testListCadidatesAndCertificates() throws RemoteException {

//        cadidateDao.listCandidatesWithCertificates().forEach((k, v) -> System.out.println(k + " " + v));

        assertNotNull(cadidateDao.listCandidatesWithCertificates());


    }

    @AfterAll
    public void tearDown() throws RemoteException {
        cadidateDao = null;
    }
}