package dao;

import entity.Position;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface PositionDao extends Remote {
    List<Position> listPositions(String name, double salaryFrom, double salaryTo) throws RemoteException;
    Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) throws RemoteException;;
}
