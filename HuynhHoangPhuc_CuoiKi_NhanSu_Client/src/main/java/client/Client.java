package client;



import entity.Candidate;
import entity.Certificate;
import entity.Position;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 6541);
            Scanner scanner = new Scanner(System.in);
        ){
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server");
            int choice =0;
            while (true)
            {
                System.out.println("1. Liệt kê danh sách các vị trí công việc khi biết tên vị trí (tìm tương đối) và mức lương khoảng từ " +
                        "kết quả sắp xếp theo tên vị trí công việc.");
                System.out.println("2. Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm việc");
                System.out.println("3. Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có thời gian làm lâu nhất.");
                System.out.println("4. Thêm một ứng viên mới. Trong đó mã số ứng viên phải bắt đầu là C, theo sau ít nhất là 3 ký số.");
                System.out.println("5. Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.");
                System.out.println("6. Liệt kê danh sách các ứng viên và danh sách bằng cấp của từng ứng viên.");
                System.out.println("7. Kết thúc");
                System.out.println("Chọn chức năng:");
                choice = scanner.nextInt();
                dos.writeInt(choice);
                switch (choice)
                {
                    case 1:
                        scanner.nextLine();
                        System.out.println("Cau A: Nhập teen:");
                        String name = scanner.nextLine();
                        dos.writeUTF(name);
                        System.out.println("Nhap luong khoi dau:");
                        int salaryFrom = scanner.nextInt();
                        dos.writeDouble(salaryFrom);
                        System.out.println("Nhap luong ket thuc:");
                        int salaryTo = scanner.nextInt();
                        dos.writeDouble(salaryTo);
                        dos.flush();

                        List<Position> positionList = (List<Position>) ois.readObject();
                        positionList.forEach(System.out::println);
                        break;
                    case 2:
                        scanner.nextLine();
                        System.out.println("Cau B");
                        Map< Candidate, Long> map = (Map<Candidate, Long>) ois.readObject();
                        map.forEach((k, v) -> System.out.println(k + " " + v));
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.println("Cau C");
                        Map<Candidate, Long> map1 = (Map<Candidate, Long>) ois.readObject();
                        map1.forEach((k, v) -> System.out.println(k + " " + v));
                        break;
                    case 4:
                        scanner.nextLine();
                        System.out.println("Cau D: ");
                          //                         //         Candidate candidate = new Candidate("C003", "Nguyen Van A", 1999, "Male", "aa@gmail.com", "0123456787", "The best");
                        System.out.println("Nhap ma so ung vien:");
                        String candidateID = scanner.nextLine();
                        dos.writeUTF(candidateID);
                        System.out.println("Nhap ten:");
                        String name1 = scanner.nextLine();
                        dos.writeUTF(name1);
                        System.out.println("Nhap nam sinh:");
                        int birthYear = scanner.nextInt();
                        dos.writeInt(birthYear);
                        scanner.nextLine();
                        System.out.println("Nhap gioi tinh:");
                        String gioiTinh = scanner.nextLine();
                        dos.writeUTF(gioiTinh);
                        System.out.println("Nhap email:");
                        String email = scanner.nextLine();
                        dos.writeUTF(email);
                        System.out.println("Nhap so dien thoai:");
                        String phone = scanner.nextLine();
                        dos.writeUTF(phone);
                        System.out.println("Nhap dia chi:");
                        String address = scanner.nextLine();
                        dos.writeUTF(address);
                        dos.flush();
                        boolean result = ois.readBoolean();
                        System.out.println(result);
                        break;
                    case 5:
                        scanner.nextLine();
                        System.out.println("Cau E");
                        System.out.println("Nhap ma so ung vien:");
                        String candidateID1 = scanner.nextLine();
                        dos.writeUTF(candidateID1);
                        dos.flush();
                        Map<Position, Long> map2 = (Map<Position, Long>) ois.readObject();
                        map2.forEach((k, v) -> System.out.println(k + " " + v));

                        break;
                    case 6:
                        scanner.nextLine();
                        System.out.println("Cau F");
                        Map<Candidate, Set< Certificate>> map3 = (Map<Candidate, Set<Certificate>>) ois.readObject();
                        map3.forEach((k, v) -> System.out.println(k + " " + v));
                        break;
                    case 7:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                if(choice == 7)
                    break;

            }
        } catch (Exception e) {
            System.out.println("Lỗi h");
        }
    }
}
