package org.DATA.ApacheDerby;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

import static java.lang.Class.forName;


public class ApacheDerby {

    private static final String DB_URL = "jdbc:derby:Database/chatDB";

    private static final String DB_URL_MODIFY = ";create=true";

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    static final String TABLE_USER = "CREATE TABLE Users("
            + "UserID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "Fio VARCHAR (255), "
            + "Post VARCHAR (255), "
            + "Login VARCHAR (255), "
            + "Password VARCHAR (255), "
            + "PRIMARY KEY (UserID))";

    static final String TABLE_HISTORY = "CREATE TABLE History("
            + "MessageID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "DateMessage TIMESTAMP, "
            + "UserID INT NOT NULL REFERENCES Users(UserID), "
            + "TypeMessage VARCHAR (2), "
            + "Message VARCHAR (3000), "
            + "PRIMARY KEY (MessageID))";

    static final String TABLE_USER_DUPLICATED = "CREATE TABLE Users_d("
            + "UserID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "Fio VARCHAR (255), "
            + "Post VARCHAR (255), "
            + "Login VARCHAR (255), "
            + "Password VARCHAR (255), "
            + "PRIMARY KEY (UserID))";

    static final String TABLE_HISTORY_DUPLICATED = "CREATE TABLE History_d("
            + "MessageID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "DateMessage TIMESTAMP, "
            + "UserID INT NOT NULL REFERENCES Users(UserID), "
            + "TypeMessage VARCHAR (2), "
            + "Message VARCHAR (3000), "
            + "PRIMARY KEY (MessageID))";

    public static void createDataBase() {
        System.out.println("Создаем базу данных...");
        try {
            forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL + DB_URL_MODIFY);
            Statement statement = connection.createStatement();
            statement.execute(TABLE_USER);
            System.out.println("Таблица " + TABLE_USER.trim() + " создана");
            statement.execute(TABLE_USER_DUPLICATED);
            System.out.println("Таблица " + TABLE_USER_DUPLICATED.trim() + " создана");
            statement.execute(TABLE_HISTORY);
            System.out.println("Таблица " + TABLE_HISTORY.trim() + " создана");
            statement.execute(TABLE_HISTORY_DUPLICATED);
            System.out.println("Таблица " + TABLE_HISTORY_DUPLICATED.trim() + " создана");
            statement.execute(createDatabaseAdmin());
            statement.close();
            connection.close();
            System.out.println("База данных создана");


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void connectDataBase() {                // Метод для подключения к базе данных (для проверки - запрос данных из таблицы Users)
        try {
            forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL);
            System.out.println("Подключение выполнено");
            System.out.println("Выберите действие: ");                                              // проверка корректности работы методов класса
            System.out.println("1. Создать нового пользователя");
            System.out.println("2. Удалить пользователя из основной таблицы");
            System.out.println("3. Показать всех пользователей в таблице Users");
            System.out.println("4. Показать всех пользователей в дубликате таблицы Users");
            System.out.println("5. Создать новую запись");
            System.out.println("6. Удалить запись из основной таблицы");
            System.out.println("7. Показать все записи в таблице History");
            System.out.println("8. Показать все записи в дубликате таблицы History");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int i = Integer.parseInt(reader.readLine());
            Statement statement = connection.createStatement();
            switch (i) {
                case 1: {
                    System.out.println("Введите ФИО пользователя: ");
                    String fio = reader.readLine();
                    System.out.println("Введите должность пользователя: ");
                    String post = reader.readLine();
                    System.out.println("Введите логин пользователя: ");
                    String login = reader.readLine();
                    System.out.println("Введите пароль пользователя: ");
                    String password = reader.readLine();
                    statement.execute(addUserToTable(new User(fio, post, login, password)));
                    statement.execute(addUserInDuplicatedTable(new User(fio, post, login, password)));
                    statement.close();
                    break;
                }
                case 2: {
                    System.out.println("Введите ID пользователя, которого нужно удалить: ");
                    int id = Integer.parseInt(reader.readLine());
                    statement.execute(deleteUserFromTable(id));
                    statement.close();
                    break;
                }
                case 3: {
                    selectUserFromTable();
                    statement.close();
                    break;
                }
                case 4: {
                    selectUserFromDuplicatedTable();
                    statement.close();
                    break;
                }
                case 5: {
                    System.out.println("Введите сообщение: ");
                    String message = reader.readLine();
                    statement.execute(addRecordToTable(new Record(message)));
                    statement.execute(addRecordInDuplicatedTable(new Record(message)));
                    statement.close();
                    break;
                }
                case 6: {
                    System.out.println("Введите ID сообщения, которое нужно удалить: ");
                    int id = Integer.parseInt(reader.readLine());
                    statement.execute(deleteRecordFromTable(id));
                    statement.close();
                    break;
                }
                case 7: {
                    selectMessageFromTable();
                    statement.close();
                    break;
                }
                case 8: {
                    selectMessageFromDuplicatedTable();
                    statement.close();
                    break;
                }
                default:
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String addRecordToTable(Record record) {                // Метод добавления записи в таблицу TABLE_HISTORY
        int userID = record.getUserID();
        int typeMessage = record.getTypeMessage();
        String message = record.getMessage();
        String query = "INSERT INTO History VALUES (default, default, " + userID + ", " + typeMessage + ", '" + message + "')";
        return query;
    }

    public static String addRecordInDuplicatedTable(Record record) { // Метод перемещения записи в таблицу-дубликат TABLE_HISTORY_DUPLICATED
        int userID = record.getUserID();
        int typeMessage = record.getTypeMessage();
        String message = record.getMessage();
        String query = "INSERT INTO History_d VALUES (default, default, " + userID + ", " + typeMessage + ", '" + message + "')";
        return query;
    }

    public static String deleteRecordFromTable(int id) {           // Метод удаления записи в таблице TABLE_HISTORY
        String query = "DELETE FROM History WHERE MessageID = " + id + "";
        return query;
    }

    public static String addUserToTable(User user) {              // Метод создание пользователя в таблице TABLE_USER
        String fio = user.getFio();
        String post = user.getPost();
        String login = user.getLogin();
        String password = user.getPassword();
        String query = "INSERT INTO Users VALUES (default, '" + fio + "', '" + post + "', '" + login + "', '" + password + "')";
        return query;
    }

    public static String addUserInDuplicatedTable(User user) { // Метод перемещения пользователя в таблицу-дубликат TABLE_USER_DUPLICATED
        String fio = user.getFio();
        String post = user.getPost();
        String login = user.getLogin();
        String password = user.getPassword();
        String query = "INSERT INTO Users_d VALUES (default, '" + fio + "', '" + post + "', '" + login + "', '" + password + "')";
        return query;
    }

    public static String deleteUserFromTable(int id) {          // Метод удаления пользователя из таблицы TABLE_USER
        String query = "DELETE FROM Users WHERE UserID = " + id + "";
        return query;
    }

    public static void selectUserFromTable() {
        try {
        Connection connection = DriverManager.getConnection(DB_URL);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("userID");
            String fio = resultSet.getString("fio");
            String post = resultSet.getString("post");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            System.out.println("ID: " + id);
            System.out.println("ФИО: " + fio);
            System.out.println("Должность: " + post);
            System.out.println("Логин: " + login);
            System.out.println("Пароль: " + password);
        }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void selectUserFromDuplicatedTable() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users_d");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("userID");
                String fio = resultSet.getString("fio");
                String post = resultSet.getString("post");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                System.out.println("ID: " + id);
                System.out.println("ФИО: " + fio);
                System.out.println("Должность: " + post);
                System.out.println("Логин: " + login);
                System.out.println("Пароль: " + password);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void selectMessageFromTable() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM History");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String messageID = resultSet.getString("messageID");
                String dateMessage = resultSet.getString("dateMessage");
                String userID = resultSet.getString("userID");
                String typeMessage = resultSet.getString("typeMessage");
                String message = resultSet.getString("message");
                System.out.println("ID: " + messageID);
                System.out.println("Дата/время: " + dateMessage);
                System.out.println("Пользователь: " + userID);
                System.out.println("Тип сообщения: " + typeMessage);
                System.out.println("Сообщение: " + message);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void selectMessageFromDuplicatedTable() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM History_d");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String messageID = resultSet.getString("messageID");
                String dateMessage = resultSet.getString("dateMessage");
                String userID = resultSet.getString("userID");
                String typeMessage = resultSet.getString("typeMessage");
                String message = resultSet.getString("message");
                System.out.println("ID: " + messageID);
                System.out.println("Дата/время: " + dateMessage);
                System.out.println("Пользователь: " + userID);
                System.out.println("Тип сообщения: " + typeMessage);
                System.out.println("Сообщение: " + message);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static String createDatabaseAdmin() {       // Метод-команда на добавление Админа в базу данных
        String query = "INSERT INTO Users VALUES (default, 'Админ', 'Администратор', 'admin', '123')";
        return query;
    }

    public static boolean findDataBase() {              // Метод поиска базы данных (возвращает true если нашел)
        Boolean find = false;                           // Ищет пока по имени, позже поменяю на более надежный метод поиска
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL);
            ResultSet resultSet = connection.getMetaData().getTables(null, null, null, new String[] {"TABLE"});
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    find = true;
                    System.out.println("База данных найдена");
                    break;
                }
            }
            resultSet.close();
            connection.close();
            return find;
        } catch (Exception exception) {
            System.out.println("База данных не найдена");
            return find;
        }
    }

    public static void main(String[] args) {
        if (findDataBase()) {               //Проверяем, если база данных есть, то подключаемся к ней, если нет, то создаем новую
            connectDataBase();
        } else {
            createDataBase();
        }
    }
}
