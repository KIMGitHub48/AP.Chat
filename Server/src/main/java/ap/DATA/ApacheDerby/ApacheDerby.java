package ap.DATA.ApacheDerby;

import apCommon.ApMessageEnumType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import static apCommon.ApMessageEnumType.authorization;
import static apCommon.ApMessageEnumType.chatChannelText;
import static java.lang.Class.forName;


public class ApacheDerby {

    public static ApacheDerby apacheDerby;

    enum Table {History, Log_technical};

    private static final String DB_URL = "jdbc:derby:Database/chatDB";

    private static final String DB_URL_MODIFY = ";create=true";

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    static final String TABLE_USER = "CREATE TABLE Users("
            + "UserID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "Fio VARCHAR (255), "
            + "Post VARCHAR (255), "
            + "Login VARCHAR (255), "
            + "Password VARCHAR (255), "
            + "UUID VARCHAR (36),"
            + "PRIMARY KEY (UserID))";

    static final String TABLE_HISTORY = "CREATE TABLE History("
            + "MessageID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "DateMessage TIMESTAMP, "
            + "UserID INT NOT NULL REFERENCES Users(UserID), "
            + "TypeMessage VARCHAR (2), "
            + "Message VARCHAR (3000), "
            + "UUID VARCHAR (36),"
            + "PRIMARY KEY (MessageID))";

    static final String TABLE_USER_DUPLICATED = "CREATE TABLE Users_d("
            + "UserID INT, "
            + "Fio VARCHAR (255), "
            + "Post VARCHAR (255), "
            + "Login VARCHAR (255), "
            + "Password VARCHAR (255), "
            + "UUID VARCHAR (36),"
            + "PRIMARY KEY (UserID))";

    static final String TABLE_HISTORY_DUPLICATED = "CREATE TABLE History_d("
            + "MessageID INT, "
            + "DateMessage TIMESTAMP, "
            + "UserID INT, "
            + "TypeMessage VARCHAR (2), "
            + "Message VARCHAR (3000), "
            + "UUID VARCHAR (36),"
            + "PRIMARY KEY (MessageID))";

    static final String TABLE_TECHNICAL_LOG = "CREATE TABLE Log_technical("
            + "MessageID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
            + "DateMessage TIMESTAMP, "
            + "Message VARCHAR (3000), "
            + "UUID VARCHAR (36),"
            + "PRIMARY KEY (MessageID))";

    public static void createDataBase() {                       // Метод для создания новой базы данных
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
            statement.execute(TABLE_TECHNICAL_LOG);
            System.out.println("Таблица " + TABLE_TECHNICAL_LOG.trim() + " создана");
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
            System.out.println("1. Создать нового пользователя");                                   // после тестирования удалить все со строки 80 до конца блока switch/case
            System.out.println("2. Удалить пользователя из основной таблицы");
            System.out.println("3. Показать всех пользователей в таблице Users");
            System.out.println("4. Показать всех пользователей в дубликате таблицы Users");
            System.out.println("5. Показать все записи в таблице Technical_Log");
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
                    showTechnicalLog();
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

    public static Statement connect() {
        try {
            System.out.println("Коннектимся");
            forName(DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            return statement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addRecord(ApMessageEnumType type, UUID id, String message) {        // Добавление в БД сообщения (нужно доработать в зависимости от типа)
        Statement statement = connect();
        System.out.println("Передаем сообщение в базу");
        System.out.println("Тип сообщения: " + type);
        String table;
        Iterator itr;
        HashMap hashMap;
        switch (type) {                                                                    // В зависимости от типа сообщения, передаем запрос в необходимую таблицу
            case authorization:
                addRecordToTable(Table.Log_technical, new Record(id, message));                    // Записываем сообщение в таблицу логов
            case chatChannelText:
                hashMap = new HashMap(addRecordToTable(Table.History, new Record(id, message)));   // Возвращаем в HashMap UUID и Сообщение
                itr = hashMap.entrySet().iterator();                                               // пробегаемся по HashMap и получаем ключ и значение
                while(itr.hasNext()) {                                                             // По значению записываем в основную таблицу
                    Map.Entry<UUID, String> entry = (Map.Entry<UUID, String>) itr.next();          // По ключу ищем запись в основной таблице и копируем в дублирующую
                    UUID uuid = entry.getKey();
                    String query = entry.getValue();
                    try {
                        statement.execute(query);
                        statement.execute(addRecordInDuplicatedTable(uuid));
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            default:
        }

    }

    public static boolean checkRecord(UUID uuid, String message) {          //Проверка сообщения на наличие его в БД
        boolean check = false;
        Statement statement = connect();
        String query = "SELECT * FROM History WHERE UUID = '" + uuid + "' AND Message = '" + message + "'";
        System.out.println(query);
        try {
            if (statement.execute(query)) {
                check = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean authorization(String login, String password) {     //Авторизация пользователя
        boolean check = false;
        Statement statement = connect();
        String query = "SELECT * FROM Users WHERE Login = '" + login + "' AND Password = '" + password + "'";
        System.out.println(query);
        try {
            ResultSet find = statement.executeQuery(query);
            while (find.next()) {
                String loginQuery = find.getString("login");
                String passwordQuery = find.getString("password");
                if (login.equals(loginQuery) && password.equals(passwordQuery)) {
                    check = true;
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static String sortTypeMessage(ApMessageEnumType type) {          //Определяем с какой таблицей будем работать в зависимости от типа сообщения
        String table = null;
        if (type == authorization) {
            table = "Users";
            if (type == chatChannelText) {
                table = "History";
            }
        }
        return table;
    }

    public static Map<UUID, String> addRecordToTable(Table table, Record record) {                // Метод добавления записи в таблицу TABLE_HISTORY
        UUID uuid = record.getId();
        int userID = record.getUserID();
        String typeMessage = record.getTypeMessage();
        String datetime = record.getTimestamp();
        String message = record.getMessage();
        HashMap<UUID, String> hashMap = new HashMap<>();
        String query;
        System.out.println(table);                                          // надо дебажить, не понимаю почему таблица одна а свич/кэйз идет на другую, где-то напортачил
        switch (table) {
            case History:
                query = "INSERT INTO " + table + " VALUES (default, '" + datetime + "', " + userID + ", '" + typeMessage + "', '" + message + "', '" + uuid + "')";
                hashMap.put(uuid, query);
                System.out.println(table);
                return hashMap;
            case Log_technical:
                query = "INSERT INTO " + table + " VALUES (default, '" + datetime + "', '" + message + "', '" + uuid + "')";
                hashMap.put(uuid, query);
                return hashMap;
        }
        return null;
    }

    public static String addRecordInDuplicatedTable(UUID uuidString) { // Метод добавления записи в таблицу-дубликат TABLE_HISTORY_DUPLICATED
        UUID uuid = UUID.fromString(String.valueOf(uuidString));
        System.out.println(uuid);
        String query = "INSERT INTO History_d SELECT * FROM History WHERE UUID = '" + uuid + "'";
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

    public static String addUserInDuplicatedTable(User user) { // Метод добавления пользователя в таблицу-дубликат TABLE_USER_DUPLICATED
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

    public static String addRecordTechnicalLog(Log message) {
        String datetime = message.getDatetime();
        String query =  "INSERT INTO Log_technical VALUES (default, '" + datetime + "', '" + message + "')";
        return query;
    }

    public static void selectUserFromTable() {                  // тестовый метод, для запроса всех данных из таблицы TABLE_USER
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

    public static void showTechnicalLog() {                                     // тестовый метод, запрос всех записей в техническом логе
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Log_technical");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("messageID");
                String date = resultSet.getString("dateMessage");
                String message = resultSet.getString("message");
                String UUID = resultSet.getString("uuid");
                System.out.println("ID: " + id + " | Дата сообщения: " + date + " | Сообщение: " + message + " | UUID: " + UUID);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void selectUserFromDuplicatedTable() {        // тестовый метод, для запроса всех данных из дубликата таблицы TABLE_USER
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

    public static void selectMessageFromTable() {               // тестовый метод, для запроса всех данных из таблицы TABLE_HISTORY
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
                String uuidMessage = resultSet.getString("UUID");
                System.out.println("ID: " + messageID);
                System.out.println("Дата/время: " + dateMessage);
                System.out.println("Пользователь: " + userID);
                System.out.println("Тип сообщения: " + typeMessage);
                System.out.println("Сообщение: " + message);
                System.out.println("UUID: " + uuidMessage);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void selectMessageFromDuplicatedTable() {         // тестовый метод, для запроса всех данных из дубликата таблицы TABLE_HISTORY
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
                String uuidMessage = resultSet.getString("UUID");
                System.out.println("ID: " + messageID);
                System.out.println("Дата/время: " + dateMessage);
                System.out.println("Пользователь: " + userID);
                System.out.println("Тип сообщения: " + typeMessage);
                System.out.println("Сообщение: " + message);
                System.out.println("UUID: " + uuidMessage);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static String createDatabaseAdmin() {       // Метод-команда на добавление Админа в базу данных (технический пользователь)
        String query = "INSERT INTO Users VALUES (default, 'Админ', 'Администратор', 'admin', '123')";
        return query;
    }

    public static boolean findDataBase() {              // Метод поиска базы данных (возвращает true если нашел)
        Boolean find = false;                           // Ищет есть ли таблицы, потом поменяю на более надежный метод поиска
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
