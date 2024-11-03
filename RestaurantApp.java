import java.util.*;

class MenuItem {
    String name;
    double price;
    String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class RestaurantApp {
    static List<MenuItem> menu = new ArrayList<>();
    static Map<MenuItem, Integer> order = new HashMap<>();
    static double totalCost = 0;
    static double tax = 0;
    static double serviceCharge = 20000;
    static double totalDiscount = 0;
    static double totalDiscountAmount = 0;
    static double drinkDiscount = 0;
    static int drinkCount = 0;

    public static void main(String[] args) {
        initializeMenu();
        displayMenu();
        takeOrder();
        calculateTotalCost();
        printReceipt();
    }

    static void initializeMenu() {
        menu.add(new MenuItem("Nasi Padang", 25000, "Makanan"));
        menu.add(new MenuItem("Nasi Rames", 15000, "Makanan"));
        menu.add(new MenuItem("Nasi Uduk", 20000, "Makanan"));
        menu.add(new MenuItem("Es Teh", 5000, "Minuman"));
        menu.add(new MenuItem("Es Coklat", 15000, "Minuman"));
        menu.add(new MenuItem("Air Putih", 2000, "Minuman"));
        
        // Tambahkan lebih banyak item menu di sini
    }

    static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("0. Selesai");
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            System.out.println((i + 1) + ". " + item.name + " - " + item.price + " (" + item.category + ")");
        }
    }

    static void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Silahkan pilih menu (0 untuk selesai): ");
            int itemNumber = scanner.nextInt();
            if (itemNumber == 0) {
                break;
            }
            MenuItem item = menu.get(itemNumber - 1);
            System.out.print("" + item.name + " = ");
            int quantity = scanner.nextInt();
            order.put(item, quantity);
            if (item.category.equals("Minuman")) {
                drinkCount += quantity;
            }
        }
    }

    static void calculateTotalCost() {
        for (Map.Entry<MenuItem, Integer> entry : order.entrySet()) {
            totalCost += entry.getKey().price * entry.getValue();
        }
        tax = totalCost * 0.1;
        totalCost += tax + serviceCharge;
        if (totalCost > 100000) {
            totalDiscount += 0.1;
            totalDiscountAmount += totalCost * 0.1;
            totalCost -= totalCost * 0.1;
        }
        if (totalCost > 50000 && drinkCount > 1) {
            drinkDiscount = drinkCount / 2 * menu.get(1).price; // Asumsikan Es Teh adalah item minuman pertama
            totalDiscount += drinkCount / 2;
            totalDiscountAmount += drinkDiscount;
            totalCost -= drinkDiscount;
        }
    }

    static void printReceipt() {
        System.out.println("\nStruk:");
        for (Map.Entry<MenuItem, Integer> entry : order.entrySet()) {
            MenuItem item = entry.getKey();
            System.out.println(item.name + " x " + entry.getValue() + " - " + item.price * entry.getValue());
        }
        System.out.println("Total Biaya: " + totalCost);
        System.out.println("Pajak: " + tax);
        System.out.println("Biaya Layanan: " + serviceCharge);
        System.out.println("Total Diskon: "  + totalDiscountAmount );
        if (drinkDiscount > 0) {
            System.out.println("Diskon Minuman: "  + drinkDiscount);
        }
    }
}

/*

[Opening Scene]

"Halo, selamat datang di video penjelasan saya tentang aplikasi restoran sederhana yang saya buat menggunakan bahasa pemrograman Java. Dalam video ini, saya akan menjelaskan bagaimana aplikasi ini bekerja, mulai dari tampilan menu, pengambilan pesanan, perhitungan total biaya, hingga pencetakan struk. Saya juga akan menjelaskan beberapa fitur tambahan yang ada dalam aplikasi ini, seperti diskon khusus untuk pesanan makanan dan minuman."

[Scene 1: Tampilan Menu]

"Pertama-tama, aplikasi ini akan menampilkan menu restoran. Menu ini berisi beberapa item makanan dan minuman dengan harga masing-masing. Pengguna dapat memilih item yang ingin dipesan dengan memasukkan nomor item tersebut. Dalam contoh ini, menu restoran kami memiliki beberapa item makanan seperti Nasi Padang, Nasi Rames, dan Nasi Uduk, serta beberapa item minuman seperti Es Teh, Es Coklat, dan Air Putih."

[Scene 2: Pengambilan Pesanan]

"Setelah pengguna memilih item, aplikasi akan meminta jumlah item yang ingin dipesan. Pengguna dapat memasukkan jumlah item tersebut, dan aplikasi akan menyimpannya dalam pesanan. Dalam contoh ini, pengguna memilih Nasi Padang sebanyak 2 porsi dan Es Teh sebanyak 3 gelas."

[Scene 3: Perhitungan Total Biaya]

"Setelah pengguna selesai memesan, aplikasi akan menghitung total biaya pesanan. Ini melibatkan penjumlahan harga setiap item yang dipesan dengan jumlahnya. Selain itu, aplikasi juga akan menambahkan pajak sebesar 10% dan biaya layanan sebesar 20.000 rupiah ke dalam total biaya. Dalam contoh ini, total biaya pesanan adalah 110.000 rupiah (2 porsi Nasi Padang x 25.000 rupiah + 3 gelas Es Teh x 5.000 rupiah + pajak 10% + biaya layanan 20.000 rupiah)."

[Scene 4: Diskon]

"Selanjutnya, aplikasi akan mengecek apakah ada diskon yang dapat diberikan. Dalam contoh ini, ada dua jenis diskon yang dapat diberikan. Pertama, jika total biaya pesanan lebih dari 100.000 rupiah, maka pengguna akan mendapatkan diskon sebesar 10% dari total biaya. Kedua, jika total biaya pesanan lebih dari 50.000 rupiah dan pengguna memesan lebih dari satu item minuman, maka pengguna akan mendapatkan diskon sebesar setengah harga item minuman yang dipesan. Dalam contoh ini, karena total biaya pesanan lebih dari 100.000 rupiah, maka pengguna akan mendapatkan diskon sebesar 10% dari total biaya, yaitu 11.000 rupiah. Selain itu, karena pengguna memesan lebih dari satu item minuman, maka pengguna akan mendapatkan diskon sebesar setengah harga item minuman yang dipesan, yaitu 3 gelas Es Teh x 5.000 rupiah / 2 = 7.500 rupiah."

[Scene 5: Pencetakan Struk]

"Terakhir, aplikasi akan mencetak struk pesanan. Struk ini akan menampilkan item yang dipesan, jumlahnya, harga per item, total biaya, pajak, biaya layanan, dan diskon yang diberikan (jika ada). Dalam contoh ini, struk akan menampilkan bahwa pengguna memesan 2 porsi Nasi Padang seharga 50.000 rupiah dan 3 gelas Es Teh seharga 15.000 rupiah. Total biaya pesanan adalah 110.000 rupiah, dengan pajak sebesar 11.000 rupiah dan biaya layanan sebesar 20.000 rupiah. Total diskon yang diberikan adalah 18.500 rupiah, terdiri dari diskon total biaya sebesar 11.000 rupiah dan diskon minuman sebesar 7.500 rupiah. Jadi, total yang harus dibayar adalah 81.500 rupiah."

[Closing Scene]

"Itulah penjelasan singkat tentang aplikasi restoran sederhana yang saya buat. Aplikasi ini dapat membantu restoran dalam mengelola pesanan, menghitung total biaya, dan memberikan diskon kepada pengguna. Semoga video ini bermanfaat bagi Anda. Terima kasih telah menonton!"

*/
