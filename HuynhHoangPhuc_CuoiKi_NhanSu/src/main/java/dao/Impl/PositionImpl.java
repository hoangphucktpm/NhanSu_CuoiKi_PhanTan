package dao.Impl;

import dao.PositionDao;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PositionImpl extends UnicastRemoteObject implements PositionDao {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public PositionImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    // a) Liệt kê danh sách các vị trí công việc khi biết tên vị trí (tìm tương đối) và mức lương khoảng từ,
    //kết quả sắp xếp theo tên vị trí công việc.
    //+ listPositions(name: String, salaryFrom: double, salaryTo: double): List<Position>

    public List<Position> listPositions(String name, double salaryFrom, double salaryTo) throws RemoteException {
        return em.createQuery("SELECT p FROM Position p WHERE p.name LIKE :name AND p.salary BETWEEN :salaryFrom AND :salaryTo ORDER BY p.name", Position.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("salaryFrom", salaryFrom)
                .setParameter("salaryTo", salaryTo)
                .getResultList();
    }

    // e) Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.
    //+ listYearsOfExperienceByPosition(candidateID: String): Map<Position, Integer>

    @Override

    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) throws RemoteException {
        Map<Position, Integer> result = new LinkedHashMap<>();
        String query = "SELECT p, YEAR(e.toDate) - YEAR(e.fromDate) FROM Experience e JOIN e.candidate c JOIN e.position p WHERE c.id = :candidateID";
        List<?> list = em.createQuery(query).setParameter("candidateID", candidateID).getResultList();
        for (Object o : list)
        {
            Object[] arr = (Object[]) o;
            result.put((Position) arr[0], (Integer) arr[1]);
        }
        return result;
    }



}
