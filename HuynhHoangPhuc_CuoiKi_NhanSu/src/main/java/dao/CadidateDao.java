package dao;

import entity.Candidate;
import entity.Certificate;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

public interface CadidateDao extends Remote {
    Map<Candidate, Long> listCadidatesByCompanies() throws RemoteException ;
    Map<Candidate, Long> listCadidatesWithLongestWorking() throws RemoteException;
    boolean addCandidate(Candidate candidate) throws RemoteException;
    public Map<Candidate, Set<Certificate>> listCandidatesWithCertificates() throws RemoteException;
}
