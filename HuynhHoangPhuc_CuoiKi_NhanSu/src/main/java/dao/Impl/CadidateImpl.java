package dao.Impl;

import dao.CadidateDao;
import dao.PositionDao;
import entity.Candidate;
import entity.Certificate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class CadidateImpl extends UnicastRemoteObject implements CadidateDao {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public CadidateImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    // b) Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm.
    //+ listCadidatesByCompanies() : Map<Candidate, Long>

    public Map<Candidate, Long> listCadidatesByCompanies() throws RemoteException {
        return em.createNamedQuery("Candidate.listCadidatesByCompanies", Object[].class)
                .getResultList().stream()
                .collect(Collectors.toMap(
                        e -> (Candidate) e[0],
                        e -> (Long) e[1]
                ));
    }

    //c. Tim danh sach cac ung vien da lam viec tren mot vị tri cong viec nao do có thoi gian lam lau nhat
    // listCadidatesWithLongestWorking(): Map<Cadidate, Long>
    public Map<Candidate, Long> listCadidatesWithLongestWorking() throws RemoteException {
        List<Object[]> results = em.createNamedQuery("Candidate.listCadidatesWithLongestWorking", Object[].class)
                .getResultList();

        Map<Candidate, Long> resultMap = new HashMap<>();
        Long maxWorkingTime = null;
        for (Object[] result : results) {
            if (result[0] instanceof Candidate && result[1] instanceof Number) {
                Long workingTime = ((Number) result[1]).longValue();
                resultMap.put((Candidate) result[0], workingTime);
                if (maxWorkingTime == null || workingTime > maxWorkingTime) {
                    maxWorkingTime = workingTime;
                }
            }
        }

        final Long finalMaxWorkingTime = maxWorkingTime;
        resultMap = resultMap.entrySet().stream()
                .filter(e -> e.getValue().equals(finalMaxWorkingTime))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return resultMap;
    }

    // d) Thêm một ứng viên mới. Trong đó mã số ứng viên phải bắt đầu là C, theo sau ít nhất là 3 ký số.
    //+ addCandidate(candidate: Candidate) : boolean

    public boolean addCandidate(Candidate candidate) throws RemoteException {
        if (candidate.getId() == null || !candidate.getId().matches("^C\\d{3,}$")) {
            return false;
        }
        try {
            em.getTransaction().begin();
            em.persist(candidate);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    // f) Liệt kê danh sách các ứng viên và danh sách bằng cấp của từng ứng viên.
    //+ listCadidatesAndCertificates(): Map<Candidate, Set<Certificate >>

    public Map<Candidate, Set<Certificate>> listCandidatesWithCertificates() throws RemoteException {
        Map<Candidate, Set<Certificate>> result = new LinkedHashMap<>();
        String query = "SELECT c, ce FROM Certificate ce JOIN ce.candidate c";
        List<?> list = em.createQuery(query).getResultList();
        for (Object o : list)
        {
            Object[] arr = (Object[]) o;
            Candidate c = (Candidate) arr[0];
            Certificate ce = (Certificate) arr[1];
            if (result.containsKey(c))
            {
                result.get(c).add(ce);
            }
            else
            {
                Set<Certificate> set = new HashSet<>();
                set.add(ce);
                result.put(c, set);
            }
        }
        return result;
    }
}
