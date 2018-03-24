import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        DBProject dbProject = new DBProject();
        while (true) {
            try {
                dbProject.menu();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}


class DBProject {

    public void menu() throws SQLException, ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DBProject;integratedSecurity=true");

        Scanner in = new Scanner(System.in);
        System.out.println("Gör ett val:\n" +
                "1. Lägg till utbildning\n" +
                "2. Lägg till kurs\n" +
                "3. Lägg till anställd\n" +
                "4. Lägg till elev\n" +
                "5. Starta en kurs\n" +
                "6. Lägg till elev i klass\n" +
                "7. Sätt betyg\n" +
                "8. Utvärdera en kurs\n" +
                "9. ta bort elev");
        switch (in.nextInt()) {
            case 1:
                in = new Scanner(System.in);

                PreparedStatement insertUtbildning = con.prepareStatement("INSERT INTO Utbildning" +
                        "(UtbildningsID, Namn, Ort)" +
                        "VALUES(?, ?, ?)");
                System.out.print("Utbildnings ID i formatet UTB + 3 siffror [UTBNNN] : ");
                insertUtbildning.setString(1, in.nextLine());
                System.out.print("Ange namn på utbildningen: ");
                insertUtbildning.setString(2, in.nextLine());
                System.out.print("Ange utbildnings Ort: ");
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
                in = new Scanner(System.in);

                PreparedStatement insertKurs = con.prepareStatement("INSERT INTO Kurs" +
                        "(KursID, Namn)" +
                        "VALUES(?, ?)");
                System.out.print("Ange kurskod (3 bokstäver) t.ex \"MaB\" : ");
                insertKurs.setString(1, in.nextLine());
                System.out.print("Ange namn på kursen: ");
                insertKurs.setString(2, in.nextLine());
                insertKurs.executeUpdate();
                break;
            case 3:
                in = new Scanner(System.in);

                PreparedStatement insertAnstalld = con.prepareStatement("INSERT INTO Anställd" +
                        "(AnstID,Namn,Mail,Adress,Ort,Befattning)" +
                        "VALUES(?,?,?,?,?,?)");
                System.out.print("Ange anställnings ID i formatet A+anställningsår+ordning t.ex [A1602] : ");
                insertAnstalld.setString(1, in.nextLine());
                System.out.print("Namn: ");
                insertAnstalld.setString(2, in.nextLine());
                System.out.print("Mail: ");
                insertAnstalld.setString(3, in.nextLine());
                System.out.print("Adress: ");
                insertAnstalld.setString(4, in.nextLine());
                System.out.print("Ort: ");
                insertAnstalld.setString(5, in.nextLine());
                System.out.print("Befattning: ");
                insertAnstalld.setString(6, in.nextLine());
                insertAnstalld.executeUpdate();
                break;
            case 4:
                in = new Scanner(System.in);

                PreparedStatement insertElev = con.prepareStatement("INSERT INTO Elev" +
                        "(ElevID,Namn,Mail,Adress,Ort)" +
                        "VALUES(?,?,?,?,?)");
                System.out.print("Ange elevID, 6 tecken, i formatet E+startår+ordning t.ex [E18112] : ");
                insertElev.setString(1, in.nextLine());
                System.out.print("Namn: ");
                insertElev.setString(2, in.nextLine());
                System.out.print("Mail: ");
                insertElev.setString(3, in.nextLine());
                System.out.print("Adress: ");
                insertElev.setString(4, in.nextLine());
                System.out.print("Ort: ");
                insertElev.setString(5, in.nextLine());
                insertElev.executeUpdate();
                break;
            case 5:
                /*TODO
                Starta ett kurstillfälle: KursTillfälleID, KursID, ToM, FoM, Kursansvarig(anstID), UtbildningsID
	            Bestäm kursmaterial: KurstillfälleID, BokID
	            Koppla elever till kurstillfälle: KurstillfälleID, ElevID
                */

                break;
            case 6:
                in = new Scanner(System.in);

                PreparedStatement insertElevKlass = con.prepareStatement("INSERT INTO KlassLista" +
                        "(KurstillfälleID, ElevID)" +
                        "VALUES(?, ?)");
                //TODO visa alla kurstillfälleID med namn och ort
                System.out.print("Ange KurstillfälleID: ");
                insertElevKlass.setString(1, in.nextLine());
                System.out.print("Ange ElevID: ");
                insertElevKlass.setString(2, in.nextLine());
                insertElevKlass.executeUpdate();
                break;
            case 7:
                break;
            case 8:
                /*
                * visa info om lärare, elever, kursmaterial, satta betyg, ort, utbildningsnamn, kursnamn)
                * */
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