package Test;


import dao.Impl.PositionImpl;
import dao.PositionDao;
import entity.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionTest {

    private PositionDao positionDao;

    @BeforeAll
    public void setUp() throws RemoteException {
        positionDao = new PositionImpl();
    }

    // Cau a:
    @Test
    void testListPositions() throws RemoteException {
        String name = "Analyst";
        double salaryFrom = 9000;
        double salaryTo = 16000;


        assertEquals(2, positionDao.listPositions(name, salaryFrom, salaryTo).size());
    }

    // Câu e:
    @Test
    void testListYearsOfExperienceByPosition2() throws RemoteException {
        String candidateID = "C002";
        Map<Position, Integer> expectedYearsOfExperience = new HashMap<>();

        List<Position> positions = new ArrayList<>();
        Map<Position, Integer> actualYearsOfExperience = positionDao.listYearsOfExperienceByPosition(candidateID);

        assertEquals(expectedYearsOfExperience, actualYearsOfExperience);
    }


    @AfterAll
    public void tearDown() throws RemoteException {
        positionDao = null;
    }
}