/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train_booking;

/**
 *
 * @author mahmo
 */
import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Objects;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://192.168.1.6:1433;database=BookTrain;encrypt=true;trustservercertificate=true";
    private static final String UserName = "mahmoud";
    private static final String password = "mm1234mm1234";

    private static Connection connection = null;

    // Get connected by the database
    private static Connection getConnection() {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, UserName, password);

            if (connection == null) {
                System.out.println("Connection is null");
            }
            else {
                System.out.println("Connection is not null");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // User Login and Sign Up
    public static void addUser(String first_name, String middle_name, String last_name, String email, String password, String phone) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password) VALUES" +
                    "(2, '" + first_name + "', '" + middle_name + "', '" + last_name + "', '" + email + "', '" + password + "');";
            statement.executeUpdate(sqlquery);

            try {
                Thread.sleep(1500); // Pause the execution for the specified delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            sqlquery = "SELECT * FROM Person WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            String person_id =  resultSet.getString("person_id");
            sqlquery = "INSERT INTO PhoneNumber(person_id, phone_number) VALUES(" + person_id + ", '" + phone + "');";
            statement.executeUpdate(sqlquery);

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static User loadUser(String email, String password) {
        Connection connection = DBConnection.getConnection();
        User user = null;
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT * FROM Person WHERE email = '" + email + "' AND password = '" + password + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            if (Objects.equals(resultSet.getString("email"), email)) {
                String first_name = resultSet.getString("first_name");
                String middle_name = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                user = new User(first_name, middle_name, last_name, email, password);
            }

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Admin Login and Sign u[
    public static void addAdmin(String first_name, String middle_name, String last_name, String email, String password) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password) VALUES" +
                    "(1, '" + first_name + "', '" + middle_name + "', '" + last_name + "', '" + email + "', '" + password + "');";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static Admin loadAdmin(String email, String password) {
        Connection connection = DBConnection.getConnection();
        Admin admin = null;
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT * FROM Person WHERE email = '" + email + "' AND password = '" + password + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            if (Objects.equals(resultSet.getString("email"), email)) {
                String first_name = resultSet.getString("first_name");
                String middle_name = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                admin = new Admin(first_name, middle_name, last_name, email, password);
            }

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    // Update person details
    public static void update_first_name(String email, String new_first_name) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "UPDATE Person SET first_name = '" + new_first_name + "' WHERE email = '" + email + "';";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_middle_name(String email, String new_middle_name) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "UPDATE Person SET middle_name = '" + new_middle_name + "' WHERE email = '" + email + "';";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_last_name(String email, String new_last_name) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "UPDATE Person SET last_name = '" + new_last_name + "' WHERE email = '" + email + "';";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_password(String email, String new_password) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "UPDATE Person SET password = '" + new_password + "' WHERE email = '" + email + "';";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // View trip by certain criteria
    public static ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> view_trips_by_date(String year, String month, String day) {
        Connection connection = DBConnection.getConnection();
        ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> tripInfos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "SELECT * FROM Trip INNER JOIN Train ON Trip.train_id = Train.train_id" +
                    " WHERE YEAR(trip_date) = " + year + " AND MONTH(trip_date) = " + month + " AND DAY(trip_date) = " + day
                    + ";";
            ResultSet resultSet = statement.executeQuery(sqlquery);

            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String trip_source = resultSet.getString("trip_source");
                String trip_destination = resultSet.getString("trip_destination");
                String trip_date = resultSet.getString("trip_date");
                String trip_duration = resultSet.getString("trip_duration");
                double trip_price_unit = resultSet.getDouble("trip_price_unit");
                int trip_seats = resultSet.getInt("trip_seats");
                int capacity = resultSet.getInt("capacity");
                int available_seats = capacity - trip_seats;

                TripInfo trip = new TripInfo(train_id, trip_source, trip_destination, trip_date, trip_duration, trip_price_unit, trip_seats);
                AbstractMap.SimpleEntry<TripInfo, Integer> tripInfo = new AbstractMap.SimpleEntry<>(trip, available_seats);
                tripInfos.add(tripInfo);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripInfos;
    }
    public static ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> view_trips_by_source_destination(String source, String destination) {
        Connection connection = DBConnection.getConnection();
        ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> tripInfos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT * FROM Trip INNER JOIN Train ON Trip.train_id = Train.train_id" +
                    " WHERE trip_source = '" + source + "' AND trip_destination = '" + destination + "';";


            ResultSet resultSet = statement.executeQuery(sqlquery);

            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String trip_source = resultSet.getString("trip_source");
                String trip_destination = resultSet.getString("trip_destination");
                String trip_date = resultSet.getString("trip_date");
                String trip_duration = resultSet.getString("trip_duration");
                double trip_price_unit = resultSet.getDouble("trip_price_unit");
                int trip_seats = resultSet.getInt("trip_seats");
                int capacity = resultSet.getInt("capacity");
                int available_seats = capacity - trip_seats;
                
                TripInfo trip = new TripInfo(train_id, trip_source, trip_destination, trip_date, trip_duration, trip_price_unit, trip_seats);
                
                AbstractMap.SimpleEntry<TripInfo, Integer> tripInfo = new AbstractMap.SimpleEntry<>(trip, available_seats);
                tripInfos.add(tripInfo);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripInfos;
    }
    public static ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> view_trips_by_price(double price) {
        Connection connection = DBConnection.getConnection();
        ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> tripInfos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "SELECT * FROM Trip INNER JOIN Train ON Trip.train_id = Train.train_id" +
                    " WHERE trip_price_unit <= " + price + ";";

            ResultSet resultSet = statement.executeQuery(sqlquery);

            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String trip_source = resultSet.getString("trip_source");
                String trip_destination = resultSet.getString("trip_destination");
                String trip_date = resultSet.getString("trip_date");
                String trip_duration = resultSet.getString("trip_duration");
                double trip_price_unit = resultSet.getDouble("trip_price_unit");
                int trip_seats = resultSet.getInt("trip_seats");
                
                int capacity = resultSet.getInt("capacity");
                int available_seats = capacity - trip_seats;
                
                TripInfo trip = new TripInfo(train_id, trip_source, trip_destination, trip_date, trip_duration, trip_price_unit, trip_seats);
                
                AbstractMap.SimpleEntry<TripInfo, Integer> tripInfo = new AbstractMap.SimpleEntry<>(trip, available_seats);
                tripInfos.add(tripInfo);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripInfos;
    }
    public static ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> view_trips_available_seats(int seats) {
        Connection connection = DBConnection.getConnection();
        ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> tripInfos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "SELECT * FROM Trip INNER JOIN Train ON Trip.train_id = Train.train_id " +
                    " WHERE capacity - trip_seats >= " + seats + ";";

            ResultSet resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String trip_source = resultSet.getString("trip_source");
                String trip_destination = resultSet.getString("trip_destination");
                String trip_date = resultSet.getString("trip_date");
                String trip_duration = resultSet.getString("trip_duration");
                double trip_price_unit = resultSet.getDouble("trip_price_unit");
                int trip_seats = resultSet.getInt("trip_seats");
                
                int capacity = resultSet.getInt("capacity");
                int available_seats = capacity - trip_seats;
                
                TripInfo trip = new TripInfo(train_id, trip_source, trip_destination, trip_date, trip_duration, trip_price_unit, trip_seats);
                
                AbstractMap.SimpleEntry<TripInfo, Integer> tripInfo = new AbstractMap.SimpleEntry<>(trip, available_seats);
                tripInfos.add(tripInfo);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripInfos;
    }
    public static ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> view_trips() {
        Connection connection = DBConnection.getConnection();
        ArrayList<AbstractMap.SimpleEntry<TripInfo, Integer>> tripInfos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "SELECT * FROM Trip INNER JOIN Train ON Trip.train_id = Train.train_id;";

            ResultSet resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String trip_source = resultSet.getString("trip_source");
                String trip_destination = resultSet.getString("trip_destination");
                String trip_date = resultSet.getString("trip_date");
                String trip_duration = resultSet.getString("trip_duration");
                double trip_price_unit = resultSet.getDouble("trip_price_unit");
                int trip_seats = resultSet.getInt("trip_seats");
                
                int capacity = resultSet.getInt("capacity");
                int available_seats = capacity - trip_seats;
                
                TripInfo trip = new TripInfo(train_id, trip_source, trip_destination, trip_date, trip_duration, trip_price_unit, trip_seats);
                
                AbstractMap.SimpleEntry<TripInfo, Integer> tripInfo = new AbstractMap.SimpleEntry<>(trip, available_seats);
                tripInfos.add(tripInfo);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripInfos;
    }
    public static TripInfo get_trip(TripBookingInfo tripInfo) {
        Connection connection = DBConnection.getConnection();
        TripInfo tripExtendedInfo = null;
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "SELECT * FROM Trip WHERE trip_source = '" + tripInfo.getTrip_source()
                    + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND trip_date = '" + 
                    tripInfo.getTrip_date() + "';";

            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            String train_id = resultSet.getString("train_id");
            String trip_duration = resultSet.getString("trip_duration");
            int trip_seats = resultSet.getInt("trip_seats");
            double trip_price_unit = resultSet.getDouble("trip_price_unit");
            
            tripExtendedInfo = new TripInfo(train_id, tripInfo.getTrip_source(), tripInfo.getTrip_destination(), tripInfo.getTrip_date()
            , trip_duration, trip_price_unit, trip_seats);
            
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripExtendedInfo;
    }
    public static void remove_trip(TripBookingInfo tripInfo) {
        Connection connection = DBConnection.getConnection();
        TripInfo tripExtendedInfo = null;
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "DELETE FROM Trip WHERE trip_source = '" + tripInfo.getTrip_source()
                    + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND trip_date = '" + 
                    tripInfo.getTrip_date() + "';";

            statement.executeUpdate(sqlquery);
            
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Add and delete phone numbers by user
    public static void add_phone_number(String email, String phone) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlquery = "SELECT person_id FROM Person WHERE email = '" + email + "';";

            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String person_id = resultSet.getString("person_id");

            sqlquery = "INSERT INTO PhoneNumber(person_id, phone_number) VALUES(" + person_id + ", '" + phone + "');";
            statement.executeUpdate(sqlquery);

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
        }
    }
    public static void remove_phone_number(String email, String phone) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            
            String sqlquery = "SELECT person_id FROM Person WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String person_id = resultSet.getString("person_id");

            sqlquery = "DELETE FROM PhoneNumber WHERE person_id = " + person_id + " AND phone_number = " + phone + ";";
            statement.executeUpdate(sqlquery);

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean is_phone_exist(String email, String phone) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            
            String sqlquery = "SELECT person_id FROM Person WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String person_id = resultSet.getString("person_id");

            sqlquery = "SELECT phone_number FROM PhoneNumber WHERE phone_number = '" + phone + "';";
            resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String tmp = resultSet.getString("phone_number");
            
            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            return false;
        }
        return true;
    }

    // Book or cancel reservations for user
    public static void book_trip(String email, TripBookingInfo tripInfo, int seats) {
        // Must previously check on the available seats of the trip

        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT person_id FROM Person WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String person_id = resultSet.getString("person_id");

            sqlquery = "SELECT trip_id FROM Trip WHERE trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            
            sqlquery = "INSERT INTO Reserves(person_id, trip_id, reservation_seats) VALUES(" + person_id + ", " + trip_id
                    + ", " + seats + ");";
            statement.executeUpdate(sqlquery);


            sqlquery = "UPDATE Trip SET trip_seats = trip_seats + " + seats + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void cancel_booking(String email, TripBookingInfo tripInfo) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT person_id FROM Person WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String person_id = resultSet.getString("person_id");

            sqlquery = "SELECT trip_id FROM Trip WHERE trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");

            sqlquery = "SELECT reservation_seats FROM Reserves WHERE person_id = " + person_id + " AND trip_id = " + trip_id + ";";
            resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String reservation_seats = resultSet.getString("reservation_seats");
            
            sqlquery = "UPDATE Trip SET trip_seats = trip_seats - " + reservation_seats + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);
            
            sqlquery = "DELETE FROM Reserves WHERE person_id = " + person_id + " AND trip_id = " + trip_id;
            statement.executeUpdate(sqlquery);

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isTripExist(TripBookingInfo tripInfo) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            
            String sqlquery = "SELECT * FROM Trip INNER JOIN Train ON Trip.train_id = Train.train_id WHERE trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch(SQLException e) {
            return false;
        }
        return true;
    }
    public static boolean isMadeReservation(String email, TripBookingInfo tripInfo) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            
            String sqlquery = "SELECT person_id FROM Person WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String person_id = resultSet.getString("person_id");
            
            sqlquery = "SELECT trip_id FROM Trip WHERE trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            sqlquery = "SELECT * FROM Reserves WHERE person_id = " + person_id + " AND trip_id = " + trip_id + ";";
            resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            String tmp = resultSet.getString("reservation_id");
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    public static boolean hasEnoughSeats(TripBookingInfo tripInfo, int seats) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            
            String sqlquery = "SELECT * FROM Trip INNER JOIN Train ON Trip.train_id = Train.train_id WHERE trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            
            int available_seats = resultSet.getInt("capacity") - resultSet.getInt("trip_seats");
            if (seats > available_seats)
                return false;
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    // Add train (Related to admin)
    public static void add_train(int type, int capacity) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "INSERT INTO Train(train_type_id, capacity) VALUES(" + type + ", " + capacity + ");";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean is_train_exist(String train_id) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT * FROM Train WHERE train_id = " + train_id + ";";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            String id = resultSet.getString("train_id");
            
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            return false;
        }
        return true;
    }
    public static void edit_train(TrainInfo trainInfo) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String type_id = ("Locomotive".equals(trainInfo.getType())? "1" : "2");
            String sqlquery = "UPDATE Train SET train_type_id = " + type_id
                    + " , capacity = " + trainInfo.getCapacity() + " WHERE train_id = " + trainInfo.getTrain_id() + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static TrainInfo get_train(String train_id) {
        Connection connection = DBConnection.getConnection();
        TrainInfo trainInfo = null;
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT * FROM Train WHERE train_id = " + train_id + ";";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            
            String id = resultSet.getString("train_id");
            String train_type = (resultSet.getInt("train_type_id") == 1? "Locomotive" : "Bogies");
            int capacity = resultSet.getInt("capacity");
            
            trainInfo = new TrainInfo(id, capacity, train_type);
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            return null;
        }
        return trainInfo;
    }

    // Update train details (Related to admin)
    public static void update_train_capacity(int train_id, int new_capacity) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "UPDATE Train SET capacity = " + new_capacity + " WHERE train_id = " + train_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_train_type(int train_id, int new_type) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "UPDATE Train SET train_type_id = " + new_type + " WHERE train_id = " + train_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Show trains details (Related to admin)
    public static ArrayList<TrainInfo> view_train_info() {
        Connection connection = DBConnection.getConnection();
        ArrayList<TrainInfo> tripInfos = new ArrayList<TrainInfo>();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT train_id, train_type_name, capacity FROM Train INNER JOIN TrainType ON Train.train_type_id"
                    + " = TrainType.train_type_id;";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String train_type_name = resultSet.getString("train_type_name");
                int capacity = resultSet.getInt("capacity");
                tripInfos.add(new TrainInfo(train_id, capacity, train_type_name));
            }

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripInfos;
    }

    // Add trip (Related to admin)
    public static void add_trip(TripInfo tripInfo) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "INSERT INTO Trip(train_id, trip_source, trip_destination, trip_date, trip_duration, trip_price_unit) " +
                    "VALUES('" + tripInfo.getTrain_id() + "','" + tripInfo.getTrip_source() + "', '" + tripInfo.getTrip_destination() + "', '" + tripInfo.getTrip_date()
                    + "', '" + tripInfo.getTrip_duration() + "', '" + tripInfo.getTrip_price_unit()+ "');";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void add_trip_station(TripInfo tripInfo, StationInfo stationInfo) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "INSERT INTO Station(trip_id, stop_name, duration) VALUES(" + stationInfo.getTrip_id() + ", '" +
            stationInfo.getStation_name() + "', '" + stationInfo.getDuration() + "');";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Update trip details (Related to admin)
    public static void update_trip_source(TripInfo tripInfo, String new_source) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT trip_id FROM Trip WHERE train_id = '" + tripInfo.getTrain_id() + "' AND trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            sqlquery = "UPDATE Trip SET trip_source = " + new_source + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_trip_destination(TripInfo tripInfo, String new_destination) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT trip_id FROM Trip WHERE train_id = '" + tripInfo.getTrain_id() + "' AND trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            sqlquery = "UPDATE Trip SET trip_destination = " + new_destination + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_trip_date(TripInfo tripInfo, String new_date) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT trip_id FROM Trip WHERE train_id = '" + tripInfo.getTrain_id() + "' AND trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            sqlquery = "UPDATE Trip SET trip_date = " + new_date + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_trip_duration(TripInfo tripInfo, String new_duration) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT trip_id FROM Trip WHERE train_id = '" + tripInfo.getTrain_id() + "' AND trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            sqlquery = "UPDATE Trip SET trip_duration = " + new_duration + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_trip_train_id(TripInfo tripInfo, String new_train_id) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT trip_id FROM Trip WHERE train_id = '" + tripInfo.getTrain_id() + "' AND trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            sqlquery = "UPDATE Trip SET train_id = " + new_train_id + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update_trip_price_unit(TripInfo tripInfo, double new_trip_price_unit) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = "SELECT trip_id FROM Trip WHERE train_id = '" + tripInfo.getTrain_id() + "' AND trip_source = '"
                    + tripInfo.getTrip_source() + "' AND trip_destination = '" + tripInfo.getTrip_destination() + "' AND "
                    + "trip_date = '" + tripInfo.getTrip_date() + "';";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            resultSet.next();
            String trip_id = resultSet.getString("trip_id");
            
            sqlquery = "UPDATE Trip SET trip_price_unit = " + new_trip_price_unit + " WHERE trip_id = " + trip_id + ";";
            statement.executeUpdate(sqlquery);

            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Show Statistics (Related to admin)
    public static ArrayList<AbstractMap.SimpleEntry<TrainInfo, Integer>> show_most_train_used() {
        Connection connection = DBConnection.getConnection();
        ArrayList<AbstractMap.SimpleEntry<TrainInfo, Integer>> stats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = """
                              SELECT TOP 50 PERCENT Train.train_id, train_type_name, capacity, Trips
                              FROM (
                              \tSELECT train.train_id, COUNT(train.train_id) AS Trips
                              \tFROM Train INNER JOIN Trip 
                              \tON Train.train_id = Trip.train_id
                              \tGROUP BY Train.train_id
                              ) AS a INNER JOIN Train
                              ON a.train_id = Train.train_id INNER JOIN TrainType
                              ON Train.train_type_id = TrainType.train_type_id 
                              ORDER BY Trips DESC;""";

            ResultSet resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String train_type = resultSet.getString("train_type_name");
                int capacity = resultSet.getInt("capacity");
                int trips_count = resultSet.getInt("Trips");

                TrainInfo trainInfo = new TrainInfo(train_id, capacity, train_type);
                stats.add(new AbstractMap.SimpleEntry<>(trainInfo, trips_count));
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
    public static ArrayList<AbstractMap.SimpleEntry<User, Integer>> show_most_user_reserve() {
        Connection connection = DBConnection.getConnection();
        ArrayList<AbstractMap.SimpleEntry<User, Integer>> stats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = """
                              SELECT TOP 50 PERCENT first_name, middle_name, last_name, email, [password], cnt
                              FROM (
                              \tSELECT p.person_id, COUNT(p.person_id) AS cnt
                              \tFROM Person as p INNER JOIN Reserves
                              \tON P.person_id = Reserves.person_id
                              \tGROUP BY p.person_id
                              ) AS A INNER JOIN Person
                              ON Person.person_id = A.person_id
                              ORDER BY cnt DESC;
                              
                              SELECT * FROM Reserves;""";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            while(resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String middle_name = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String Password = resultSet.getString("password");
                int reserve_count = resultSet.getInt("cnt");

                User user = new User(first_name, middle_name, last_name, email, Password);
                stats.add(new AbstractMap.SimpleEntry<>(user, reserve_count));
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
    public static ArrayList<TripInfo> show_most_trip() {
        Connection connection = DBConnection.getConnection();
        ArrayList<TripInfo> tripInfo = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            String sqlquery = """
                              SELECT TOP 50 PERCENT * FROM Trip
                              ORDER BY trip_seats DESC;""";
            ResultSet resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                String train_id = resultSet.getString("train_id");
                String trip_source = resultSet.getString("trip_source");
                String trip_destination = resultSet.getString("trip_destination");
                String trip_date = resultSet.getString("trip_date");
                String trip_duration = resultSet.getString("trip_duration");
                double trip_price_unit = resultSet.getDouble("trip_price_unit");
                int trip_seats = resultSet.getInt("trip_seats");

                tripInfo.add(new TripInfo(train_id, trip_source, trip_destination, trip_date, trip_duration, trip_price_unit, trip_seats));
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tripInfo;
    }
}

