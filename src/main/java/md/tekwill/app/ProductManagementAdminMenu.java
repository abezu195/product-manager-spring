//package md.tekwill.app;
//
//import md.tekwill.entity.product.FoodCategory;
//import md.tekwill.entity.product.Product;
//import md.tekwill.service.ProductService;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Scanner;
//
//class ProductManagementAdminMenu {
//
//    private final Scanner scanner;
//    private final ProductService productService;
//
//    ProductManagementAdminMenu(Scanner scanner, ProductService productService) {
//        this.scanner = scanner;
//        this.productService = productService;
//    }
//
//    public void showMenu() {
//        boolean exitOptionNotSelected;
//        do {
//            System.out.println("\n\nAvailable options:" +
//                                       "\n==============ADMIN OPTIONS==============" +
//                                       "\n[1] View all products" +
//                                       "\n[2] View all expired products" +
//                                       "\n[3] Add new product" +
//                                       "\n[4] Update food product" +
//                                       "\n[5] Update drink product" +
//                                       "\n[6] Remove product" +
//                                       "\n==========================================" +
//                                       "\n[0] Exit" +
//                                       "\n==========================================");
//            System.out.print(">> ");
//            exitOptionNotSelected = handleAdminChoice(scanner.nextInt());
//        } while (exitOptionNotSelected);
//    }
//
//    private boolean handleAdminChoice(int userChoice) {
//        scanner.nextLine();
//        switch (userChoice) {
//            case 1:
//                viewAllProducts();
//                return true;
//            case 2:
//                viewAllExpiredProducts();
//                return true;
//            case 3:
//                addNewProduct();
//                return true;
//            case 4:
//                updateFood();
//                return true;
//            case 5:
//                updateDrink();
//                return true;
//            case 6:
//                removeProduct();
//                return true;
//            case 0:
//                System.out.println("BYE!");
//                return false;
//            default:
//                System.out.println("Unknown option selected!");
//                return true;
//        }
//    }
//
//    private void viewAllProducts() {
//        System.out.println("--- ALL EXISTING PRODUCTS ---");
//        for (Product product : productService.getAll()) {
//            System.out.println(product.getPrintText());
//        }
//    }
//
//    private void viewAllExpiredProducts() {
//        System.out.println("--- ALL EXPIRED PRODUCTS ---");
//        for (Product product : productService.getAllExpired()) {
//            System.out.println(product.getPrintText());
//        }
//    }
//
//
//    private void addNewProduct() {
//        System.out.println("--- ADD NEW PRODUCT ---");
//        try {
//            System.out.println("Input the name of the product: \n>> ");
//            String name = scanner.nextLine();
//
//            System.out.println("Input the price of the product: \n>> ");
//            double price = scanner.nextDouble();
//            scanner.nextLine();
//
//            System.out.println("Input the best before of the product (such as \"2021-12-03\"): \n>> ");
//            String bestBeforeString = scanner.nextLine();
//            LocalDate bestBefore = LocalDate.parse(bestBeforeString);
//
//            System.out.println("Is it a food or a drink?\n>> ");
//
//            if ("food".equalsIgnoreCase(scanner.nextLine())) {
//                System.out.println("What category is that? (CATEGORIES: " + Arrays.toString(FoodCategory.values()) + ")\n>>");
//                FoodCategory foodCategory = FoodCategory.valueOf(scanner.nextLine());
//                productService.create(name, price, bestBefore, foodCategory, );
//                System.out.println("Food " + name + " successfully created!");
//            } else {
//                System.out.println("Input the volume of the drink: \n>> ");
//                double volume = scanner.nextDouble();
//                scanner.nextLine();
//                productService.create(name, price, bestBefore, volume, );
//                System.out.println("Drink " + name + " successfully created!");
//            }
//        } catch (RuntimeException ex) {
//            scanner.nextLine();
//            System.out.println("Error: " + ex.getMessage());
//        }
//    }
//
//
//    private void updateFood() {
//        try {
//            System.out.println("Input the id of food to update: \n>> ");
//            int id = scanner.nextInt();
//            scanner.nextLine();
//            System.out.println("Choose another category (CATEGORIES: " + Arrays.toString(FoodCategory.values()) + ")\n>>");
//            FoodCategory foodCategory = FoodCategory.valueOf(scanner.nextLine());
//            productService.update(id, foodCategory);
//            System.out.println("Product with ID " + id + " successfully updated!");
//        } catch (Exception ex) {
//            scanner.nextLine();
//            System.out.println("Error: " + ex.getMessage());
//        }
//    }
//
//    private void updateDrink() {
//        try {
//            System.out.println("Input the id of drink to update: \n>> ");
//            int id = scanner.nextInt();
//            scanner.nextLine();
//            System.out.println("Input the volume of the drink: \n>> ");
//            double volume = scanner.nextDouble();
//            scanner.nextLine();
//            productService.update(id, volume);
//            System.out.println("Product with ID " + id + " successfully updated!");
//        } catch (Exception ex) {
//            scanner.nextLine();
//            System.out.println("Error: " + ex.getMessage());
//        }
//    }
//
//    private void removeProduct() {
//        try {
//            System.out.println("Input the id of the product to remove: \n>> ");
//            int id = scanner.nextInt();
//            scanner.nextLine();
//            productService.delete(id);
//            System.out.println("Product with ID " + id + " successfully removed!");
//        } catch (Exception ex) {
//            scanner.nextLine();
//            System.out.println("Error: " + ex.getMessage());
//        }
//    }
//}
