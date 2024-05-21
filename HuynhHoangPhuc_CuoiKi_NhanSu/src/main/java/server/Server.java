package server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.text.Format;
import java.util.List;
import java.util.Map;


import dao.CadidateDao;
import dao.Impl.CadidateImpl;
import dao.Impl.PositionImpl;
import dao.PositionDao;
import entity.Candidate;
import entity.Position;


public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(6541)) {
            System.out.println("Server is running...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                System.out.println("Client IP: " + socket.getInetAddress().getHostAddress());
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
class ClientHandler implements Runnable {
    private Socket socket;
    private CadidateDao candidateDao;
    private PositionDao positionDao;


    public ClientHandler(Socket socket) throws RemoteException {
        this.socket = socket;
        candidateDao = new CadidateImpl();
        positionDao = new PositionImpl();

    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            int choice = 0;
            while (true)
            {
                try {
                    choice = dis.readInt();
                } catch (EOFException e) {
                    System.out.println("Client disconnected");
                    break;
                }
                switch (choice)
                {
                    case 1:
                        String name = dis.readUTF();
                        double salaryFrom = dis.readDouble();
                        double salaryTo = dis.readDouble();
                        System.out.println("Cau A: " + name + " " + salaryFrom + " " + salaryTo);
                        List<Position> positionList = positionDao.listPositions(name, salaryFrom, salaryTo);
                        oos.reset();  // Reset the stream before writing the object
                        oos.writeObject(positionList);
                        positionList.forEach(System.out::println);
                        break;
                    case 2:
                        String candidateID = dis.readUTF();
                        System.out.println("Cau B: " + candidateID);
                        oos.writeObject(positionDao.listYearsOfExperienceByPosition(candidateID));
                        break;
                    case 3:
                        // listCandidatesWithLongestWorking
                        System.out.println("Cau C");
                        Map<Candidate, Long> map = candidateDao.listCadidatesWithLongestWorking();
                        oos.writeObject(map);
                        break;
                    case 4:
                        // addCandidate
                        //         Candidate candidate = new Candidate("C003", "Nguyen Van A", 1999, "Male", "aa@gmail.com", "0123456787", "The best");
                        Candidate candidate = new Candidate(dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF());
                        System.out.println("Cau D: " + candidate);
                        oos.writeBoolean(candidateDao.addCandidate(candidate));

                        break;
                    case 5:
                        // listYearsOfExperienceByPosition
                        System.out.println("Cau E");
                        oos.writeObject(positionDao.listYearsOfExperienceByPosition(dis.readUTF()));

                        break;
                    case 6:
                        // listCadidatesAndCertificates
                        System.out.println("Cau F");
                        oos.writeObject(candidateDao.listCandidatesWithCertificates());

                        break;
                    case 7:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
