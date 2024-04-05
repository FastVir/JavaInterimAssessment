import java.util.*;
import java.util.stream.Collectors;

class Laptop {
	String maker;
	int ram;
	int hdd;
	String os;
	String color;
	
	public Laptop(String maker, int ram, int hdd, String os, String color) {
		this.maker = maker;
		this.ram = ram;
		this.hdd = hdd;
		this.os = os;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Бренд - " + maker  +
				", ОЗУ - " + ram + "GB" +
				", HDD - " + hdd + "GB" +
				", OS - " + os  +
				", Цвет - " + color ;
	}
}

public class EquipmentStore {
	public static void main(String[] args) {
		Set<Laptop> laptops = new HashSet<>();
		laptops.add(new Laptop("Samsung", 8, 512, "Windows", "Black"));
		laptops.add(new Laptop("Apple", 16, 256, "MacOS", "Silver"));
		laptops.add(new Laptop("Lenovo", 64, 1024, "Linux", "Gray"));
		laptops.add(new Laptop("Asus", 32, 512, "WithoutOS", "White"));
		
		Map<String, Object> filters = new HashMap<>();
		
		Scanner scanner = new Scanner(System.in);
		
		selectionOfParameters();
		
		int selection;
		while (true) {
			selection = scanner.nextInt();
			if (selection == 0) {
				break;
			}
			switch (selection) {
				case 1:
					System.out.println("Минимальный объем ОЗУ: ");
					filters.put("ram", scanner.nextInt());
					selectionOfParameters();
					break;
				case 2:
					System.out.println("Минимальный объем ЖД: ");
					filters.put("hdd", scanner.nextInt());
					selectionOfParameters();
					break;
				case 3:
					System.out.println("Введите наименование операционной системы: ");
					filters.put("os", scanner.next());
					selectionOfParameters();
					break;
				case 4:
					System.out.println("Цвет: ");
					filters.put("color", scanner.next());
					selectionOfParameters();
					break;
					
				default:
					System.out.println("Неверный выбор. Попробуйте снова.");
					System.out.println();
					selectionOfParameters();
			}
		}
		
		Set<Laptop> filteredLaptops = laptops.stream()
				.filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
				.filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer && laptop.hdd >= (int) filters.getOrDefault("hdd", 0))
				.filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
				.filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
				.collect(Collectors.toSet());
		
		System.out.println("Отфильтрованные ноутбуки:");
		for (Laptop laptop : filteredLaptops) {
			System.out.println(laptop);
		}
	}
	
	private static void selectionOfParameters () {
		System.out.println("Выберите критерии для фильтрации:");
		System.out.println("1 - ОЗУ: 8, 16, 32, 64");
		System.out.println("2 - Объем ЖД: 256, 512, 1024");
		System.out.println("3 - Операционная система: Windows, MacOS, Linux, WithoutOS");
		System.out.println("4 - Цвет: Black, Silver, Gray, White");
		System.out.println("0 - Вывести список ноутбуков");
	}
	
}