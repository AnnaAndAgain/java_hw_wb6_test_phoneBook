package PhoneBook;
//Реализуйте структуру телефонной книги с помощью HashMap.
//Программа также должна учитывать, что в во входной структуре будут 
//повторяющиеся имена с разными телефонами, 
//их необходимо считать, как одного человека с разными телефонами. 
//Вывод должен быть отсортирован по убыванию числа телефонов.

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> myPhoneBook = new HashMap<>(); // по ТЗ возможно несколько номеров у одного человека, поэтому ключом будет имя, а телефоны будем записывать в массив

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить номер телефона");
            System.out.println("2. Найти запись");
            System.out.println("3. Удалить запись");
            System.out.println("4. Вывести все записи");
            System.out.println("5. Выйти");
            System.out.println("Введите номер нужного действия.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: ///добавляем запись: просим ввести имя и номер, дальше либо добавляем номер к тем, что уже привязаны к этому имени, лбо создаем новую пару/запись
                    //!!!!!!!!! почему-то не воспринимает кириллицу, пишет все номера во все имена сразу!!!
                    System.out.println("Введите имя (латиницей!):");
                    String name = scanner.nextLine();
                    //scanner.nextLine();
                    System.out.println("Введите номер телефона:");
                    int phone = scanner.nextInt();
                    ArrayList<Integer> tmpPhones = myPhoneBook.getOrDefault(name, new ArrayList<Integer>());
                    tmpPhones.add(phone);
                    myPhoneBook.put(name, tmpPhones);
                    
                    break;
                case 2: //ищем запись и выводим имя + все соответсвующие ему номера телефонов
                    System.out.println("Введите имя для поиска:");
                    String searchName = scanner.nextLine();
                    //scanner.nextLine();
                    if (myPhoneBook.containsKey(searchName)){
                        System.out.println(myPhoneBook.get(searchName));
                    } else{
                        System.out.println("Запись с таким именем ("+ searchName +") не найдена.");
                    }
                    break;
                case 3: // Удаляем запись по имени (со всеми номерами сразу) -- как развитие хорошо бы выводить номера и предлагать выбрать, который удалить
                    System.out.println("Введите имя абонента, все номера которого хотите удалить:");
                    String removeName = scanner.nextLine();
                    //scanner.nextLine();
                    if (myPhoneBook.containsKey(removeName)){
                        myPhoneBook.remove(removeName);
                        System.out.println("Запись успешно удалена.");
                    } else{
                        System.out.println("Запись с таким именем ("+ removeName +") не найдена.");
                    }
                    break;
                
                case 4: // вывод всей книги по убыванию числа телефонов
                    int maxNums = 0;
                    //проверяем, есть ли вообще записи
                    if (myPhoneBook.size() == 0){
                        System.out.println("Телефонная книга пуста.");
                        System.out.println();
                    } else {
                        // если есть, ищем максимальное число номеров на абонента
                        System.out.println("---");
                        for (var item:myPhoneBook.entrySet()){
                            if (maxNums < item.getValue().size()){
                                maxNums = item.getValue().size();
                            }
                        }
                        // со счетчиком от максимального числа номеров до нуля проходим по тел.книге и выводим все записи с текущим числом номеров
                        for (int i = maxNums; i >= 0; i--){
                            for (var item:myPhoneBook.entrySet()){
                                if (item.getValue().size() == i){
                                    System.out.println("Абонент " + item.getKey() + ": " + String.valueOf(item.getValue()));
                                    
                                }
                            }
                        }
                        System.out.println("---");
                    }
                    break;
                
                case 5: // выход из программы
                    System.out.println("Выхожу из телефонной книги...");
                    scanner.close();
                    System.exit(0);
                    break;
                

                default:
                    System.out.println("Это не работает. Введите номер команды!");
                    break;
            }
        }
    }

}
