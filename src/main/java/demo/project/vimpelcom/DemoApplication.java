package demo.project.vimpelcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 2) Реализовать API с использованием которое позволит удалять данные из реляционной БД  таблиц объемом 10 млн строк и
 * более. Ключ, по которому производится удаление данных, столбец DateTime (удаляем старые данные). Работа системы не
 * должна приводить к длительным блокировкам данных. АPI может вызываться несколькими внешними процессами одновременно.
 * (1 процесс удаляет данные в 1 таблице). Необходимо обеспечить логирование работы API.
 *
 * Основные классы:
 * demo.project.vimpelcom.controllers.DemoController - API
 * */

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
