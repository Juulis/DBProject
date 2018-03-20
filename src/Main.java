import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        DBProject dbProject = new DBProject();
        try {
            dbProject.menu();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}


class DBProject {

    public void menu() throws SQLException, ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DBProject;integratedSecurity=true");

        Scanner in = new Scanner(System.in);
        System.out.println("Gör ett val:\n" +
                "1. Lägga till utbildning\n" +
                "2. Lägga till kurs\n" +
                "3. Lägga till lärare\n" +
                "4. Lägga till elev\n" +
                "5. Starta en kurs\n" +
                "6. Sätta betyg\n" +
                "7. Utvärdera en kurs (visar info om lärare, elever, kursmaterial, satta betyg, ort, utbildningsnamn, kursnamn)\n" +
                "8. ta bort elev");
        switch (in.nextInt()) {
            case 1:
                in = new Scanner(System.in);

                PreparedStatement insertUtbildning = con.prepareStatement("INSERT INTO Utbildning" +
                        "(UtbildningsID, Namn, Ort)" +
                        "VALUES(?, ?, ?)");
                System.out.print("Utbildnings ID i formatet UTB + 3 siffror [UTBNNN] : ");
                insertUtbildning.setString(1, in.nextLine());
                System.out.print("Namn på utbildningen: ");
                insertUtbildning.setString(2, in.nextLine());
                System.out.print("Utbildnings Ort: ");
                insertUtbildning.setString(3, in.nextLine());
                insertUtbildning.executeUpdate();

//                System.out.print("Utbildnings ID i formatet UTB + 3 siffror [UTBNNN] : ");
//                String utbID = in.nextLine();
//                System.out.print("Namn på utbildningen: ");
//                String namn = in.nextLine();
//                System.out.print("Utbildnings Ort: ");
//                String ort = in.nextLine();
//                updateDB("INSERT INTO Utbildning VALUES ('" + utbID + "','" + namn + "','" + ort + "')");
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
    }

    public void updateDB(String query) throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DBProject;integratedSecurity=true");
        PreparedStatement insertNewQuery = con.prepareStatement(query);
        insertNewQuery.executeUpdate();


    }

    public void queryDB(String query) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DBProject;integratedSecurity=true");

            Statement stat = con.createStatement();
            ResultSet resultSet = stat.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int nmbOfColumns = metaData.getColumnCount();
            System.out.printf("Utbildningar: %n%n");
            System.out.println();
            for (int i = 1; i <= nmbOfColumns; i++) {
                System.out.printf("%-20s\t", metaData.getColumnName(i));
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= nmbOfColumns; i++) {
                    System.out.printf("%-20s\t", resultSet.getObject(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Gick inte att koppla till db");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}