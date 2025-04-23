import java.util.*;

// Абстрактный пользователь
abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkCredentials(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    public abstract void showMenu(Scanner scanner, CarRentalSystem system);
}

// Клиент
class Customer extends User {
    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(Scanner scanner, CarRentalSystem system) {
        System.out.println("\n--- Меню клиента ---");
        // Здесь будут действия клиента
    }
}

// Арендодатель
class Owner extends User {
    public Owner(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(Scanner scanner, CarRentalSystem system) {
        System.out.println("\n--- Меню арендодателя ---");
        // Здесь будут действия арендодателя
    }
}

// Администратор
class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(Scanner scanner, CarRentalSystem system) {
        System.out.println("\n--- Меню администратора ---");
        // Здесь будут действия администратора
    }
}

// Автомобиль
class Car {
    private static int counter = 0;
    private int id;
    private String model;
    private String location;
    private double pricePerDay;
    private boolean available = true;

    public Car(String model, String location, double pricePerDay) {
        this.id = ++counter;
        this.model = model;
        this.location = location;
        this.pricePerDay = pricePerDay;
    }

    public int getId() { return id; }
    public String getModel() { return model; }
    public String getLocation() { return location; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "ID: " + id + ", Модель: " + model + ", Локация: " + location + ", Цена в день: " + pricePerDay + ", Доступен: " + available;
    }
}

// Бронирование
class Booking {
    private Customer customer;
    private Car car;
    private String startDate;
    private String endDate;
    private double totalPrice;

    public Booking(Customer customer, Car car, String startDate, String endDate, double totalPrice) {
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Бронирование: " + car.getModel() + " c " + startDate + " по " + endDate + ", Итоговая цена: " + totalPrice;
    }
}

// Главная система
public class CarRentalSystem {
    private List<User> users = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public CarRentalSystem() {
        // Предустановленные пользователи
        users.add(new Customer("client", "123"));
        users.add(new Owner("owner", "123"));
        users.add(new Admin("admin", "123"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в систему аренды автомобилей!");
        System.out.print("Введите логин: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.checkCredentials(username, password)) {
                user.showMenu(scanner, this);
                return;
            }
        }

        System.out.println("Неверный логин или пароль.");
    }

    public List<Car> getCars() { return cars; }
    public List<Booking> getBookings() { return bookings; }
    public void addCar(Car car) { cars.add(car); }
    public void addBooking(Booking booking) { bookings.add(booking); }

    public static void main(String[] args) {
        new CarRentalSystem().start();
    }
}
